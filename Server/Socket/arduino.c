#include"my.h"
extern MACHING_TABLE mobile_table[1000000];
MACHING_TABLE arduino_table[1000000];
MACHING_TABLE arduino_alarm[1000000];

extern unsigned int countm;
unsigned int counta = 0;
void * arduino_thread(void *arg)
{
  struct epoll_event *events; 
  struct epoll_event ev;
  struct sockaddr_in addr, clientaddr;
  struct timeval tv;
  int clilen, sfd, efd, cfd, i, j,
	  max_got_events, result,
	  readn,sendflags = 0,nSockOpt;
  char buf_in[256] = { '\0' },buf_out[256] = { '\0' },query[200];
  char temp[30],bpm[30],signal[3];
 MYSQL_RES   *sql_result;
   MYSQL_ROW   sql_row;
   int       query_stat; 
   MYSQL mysql,*connection  = NULL ;
  my_ulonglong resultnum;
   mysql_init(&mysql) ;

     connection=mysql_real_connect(&mysql, NULL, "root",PASS,"kdhong_db" ,3306, (char *)NULL, 0l);
    if(connection == NULL)
   {
     printf("%s\n",mysql_error(&mysql));
     exit(-1) ;
   }
if ((efd = epoll_create(1)) < 0) {
   perror("epoll_create (1) error");
   return (void*)-1;
  }
  // init pool
  events = (struct epoll_event *) malloc(sizeof(*events) * EPOLL_EVENT_SIZE);
  if (NULL == events) {
   perror("epoll_create error");
   return (void*)-1;
  }
  // 서버 소켓 생성
 clilen = sizeof(clientaddr);
  sfd = socket(AF_INET, SOCK_STREAM, 0);
  if (sfd == -1) {
   perror("socket error :");
   close(sfd);
   return (void*)-1;
  }
  // 소켓 정보 넣고 bind, listen 처리nSockOpt = 1;
 setsockopt(sfd, SOL_SOCKET, SO_REUSEADDR, &nSockOpt, sizeof(nSockOpt));
 addr.sin_family = AF_INET;
  addr.sin_port = htons(PORT_ARDUINO);
  addr.sin_addr.s_addr = htonl(INADDR_ANY);
  if (bind(sfd, (struct sockaddr *) &addr, sizeof(addr)) == -1) {
   close(sfd);
   return (void*)-2;
  }
  listen(sfd, 5);
  if (sfd < 0) {
   perror("init_sock error");
   return (void*)1;
  }
  printf("arduino thread start\n");
  ev.events = EPOLLIN | EPOLLERR;
  ev.data.fd = sfd;
  result = epoll_ctl(efd, EPOLL_CTL_ADD, sfd, &ev);
  if (result < 0) {
   perror("epoll error");
   return (void*)1;
  }
  // 서버 실제 루트
 while (1) {
   max_got_events = epoll_wait(efd, events, EPOLL_EVENT_SIZE, -1);
   for (i = 0; i < max_got_events; i++) {
    if (events[i].data.fd == sfd)//socket recved
	{
      cfd = accept(sfd, (struct sockaddr *) &clientaddr, &clilen);
      if (cfd < 0) {
        perror("Accept error");
        return (void*)-1;
      }
	 // tv.tv_sec =10;  // 1 Secs Timeout 
	 // tv.tv_usec =0 ;  // Not init'ing this can cause strange errors
	 // if(-1==setsockopt(cfd, SOL_SOCKET, SO_RCVTIMEO, (char *)&tv,sizeof(struct timeval)))
	//	  break;
	  if(-1 == recvs(cfd,buf_in,255))
	  {
    printf("recv error\n");
		close(cfd);
		continue;
	  } 
	  add_ardu_sock(cfd,buf_in,arduino_table,counta);
	  add_sock(cfd,buf_in,arduino_alarm,counta);
      ev.events = EPOLLIN;
      ev.data.fd = cfd;
      epoll_ctl(efd, EPOLL_CTL_ADD, cfd, &ev);
      counta++;
	  printf("[arduino]%d %s accept\n",cfd,buf_in);
    }
	else
	{
	 cfd = events[i].data.fd;
     memset(buf_in, 0x00, 256);
     readn = recvs(cfd, buf_in, 255);
     if (readn <= 0) {
      MACHING_TABLE tmp,*r;
      tmp.sock=cfd;
	    r=bsearch((void*)&tmp,arduino_table,counta,sizeof(MACHING_TABLE),compare_sock);
      printf("error from %s\n",r->pnum);
      epoll_ctl(efd, EPOLL_CTL_DEL, cfd, &ev);
	  delete_sock(cfd,arduino_table,counta);
	  delete_sock(cfd,arduino_alarm,counta);
      close(cfd);
      counta--;
     }
	 else
	 {
    printf("signal!!\n");
    memset(temp,0x00,30);
    memset(bpm,0x00,30);
    memset(signal,0x00,3);
   strcpy( signal, strtok(buf_in,"-"));
    strcpy(temp,strtok(NULL,"-"));
    strcpy(bpm ,strtok(NULL,"-"));
		MACHING_TABLE tmp,*r;
		tmp.sock=cfd;
	    r=bsearch((void*)&tmp,arduino_table,counta,sizeof(MACHING_TABLE),compare_sock);
		if (r ==NULL){
		printf("can not find\n");
		continue;
		}
		tmp=*r;		
		sprintf(query,"insert into graph value ('','%s','%s','%s',now())",tmp.pnum,temp,bpm);
    printf("%s\n",query);
    query_stat = mysql_query(connection, query);
		if(query_stat==-1)
			printf("insert falsen\n");
    if(strstr(signal,"M"))continue;//일반산황일시 브로드캐스트 생략
		printf("%d %s\n",r->sock,r->pnum);
		printf("maching start\n");
	    r=bsearch((void*)&tmp,mobile_table,countm,sizeof(MACHING_TABLE),compare_pnum);
    if (r ==NULL){
		printf("can not find\n");
		continue;
		}
	   strcpy(buf_in,"AA\r\n");
      write(r->sock,"AA\r\n",4);
      printf("%d %s",r->sock,"AA\r\n");
    fflush(stdout);
     }
	}
   }
  }
  return (void*)1;
}
