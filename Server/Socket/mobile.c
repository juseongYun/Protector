#include"my.h"
extern unsigned int counta;
MACHING_TABLE mobile_table[1000000];
unsigned int countm = 0;

void * mobile_thread(void *arg)
{
  struct epoll_event *events; 
  struct epoll_event ev;
  struct sockaddr_in addr, clientaddr;
  struct timeval tv;
  int clilen, sfd, efd, cfd, i, j,
	  max_got_events, result,
	  readn,sendflags = 0,nSockOpt;
  char buf_in[256] = { '\0' },buf_out[256] = { '\0' }; ; 

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
  addr.sin_port = htons(PORT_MOBILE);
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
  ev.events = EPOLLIN | EPOLLERR;
  ev.data.fd = sfd;
  result = epoll_ctl(efd, EPOLL_CTL_ADD, sfd, &ev);
  if (result < 0) {
   perror("epoll error");
   return (void*)1;
  }
  printf("mobile_thread start\n");
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
	  printf("Accpet %d\n",countm);
	  tv.tv_sec =10;  /* 1 Secs Timeout */
	  tv.tv_usec = 0;  // Not init'ing this can cause strange errors
	  if(-1==setsockopt(cfd, SOL_SOCKET, SO_RCVTIMEO, (char *)&tv,sizeof(struct timeval)))
		  break;
	  if(-1 == recvs(cfd,buf_in,30))
	  {
		close(cfd);
		continue;
	  } 
	  add_sock(cfd,buf_in,mobile_table,countm);
      ev.events = EPOLLIN;
      ev.data.fd = cfd;
      epoll_ctl(efd, EPOLL_CTL_ADD, cfd, &ev);
      countm++;
	  printf("[mobile]%d %s\n",cfd,buf_in);
    fflush(stdin);  
  }
	else
	{
	 cfd = events[i].data.fd;
     memset(buf_in, 0x00, 256);
     readn = read(cfd, buf_in, 255);
     printf("[mobile]%s read\n",buf_in);
     if (readn <= 0) {
   printf("[mobile]%d delete\n",cfd);
      epoll_ctl(efd, EPOLL_CTL_DEL, cfd, &ev);
	  delete_sock(cfd,mobile_table,countm);
      close(cfd);
      countm--;
     }
	}
   }
  }
  return (void*)1;
}
