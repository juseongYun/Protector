#include <stdio.h>
 #include <stdlib.h>
 #include <unistd.h>
 #include <string.h>
 #include <sys/epoll.h>
 #include <arpa/inet.h>
 #include <sys/socket.h>
 #include <sys/types.h>
  #include <mysql/mysql.h>
  #include <errno.h>
#include<pthread.h>
 #define EPOLL_EVENT_SIZE 100
 #define PORT_MOBILE 9000
 #define PORT_ARDUINO 9001
 #define PORT_emergency 9002
  #include <mysql/mysql.h>
 typedef struct 
 {
	 int sock;
	 int errc;
	 char pnum[100];
 }MACHING_TABLE;
 
int recvn(int s, char *buf, int len)
{
  int received;
  char *ptr = buf;
  int left = len;

  while(left > 0){
    received = read(s, ptr, left);
    if(received <= 0){
      break;
    }
    left -= received;
    ptr += received;
  }
  return (len - left);
}
int recvs(int s,char*buf,int len)
{
  int i,rec,totr=0;
  char tmp;
  for(i=0;i<len;i++)
  {
    rec=read(s,&tmp,1);
    if(rec <0)
      return -1;
    else if(tmp == '&')
    {
      buf[i+1]='\0';
      return totr;
    }
    buf[i]=tmp;
    totr++;
  }
}
int compare_pnum(const void *a,const void *b)
{
	MACHING_TABLE *m1 = (MACHING_TABLE*)a, *m2 = (MACHING_TABLE*)b;
  int val=strcmp(m1->pnum,m2->pnum);
  return val;
}
int compare_sock(const void *a,const void *b)
{
	MACHING_TABLE *m1 = (MACHING_TABLE*)a, *m2 = (MACHING_TABLE*)b;
	if (m1->sock<m2->sock)
		return -1;
	if (m1->sock==m2->sock)
		return 0;
	if (m1->sock>m2->sock)
		return 1;
}

int add_sock(int sock,char* pnum,MACHING_TABLE table[],int count)
{
	int i,j,res;
	for(i=0;i<count;i++)
	{
		res=strcmp(table[i].pnum,pnum);
		if(res>0)
		break;		
	}
		memmove(&table[i+1],&table[i],sizeof(MACHING_TABLE)*(count-i));
		table[i].sock=sock;
		strcpy(table[i].pnum,pnum);
		return i;
}
int add_ardu_sock(int sock,char* pnum,MACHING_TABLE table[],int count)
{
	int i,j,res;
	for(i=0;i<count;i++)
	{
		if(sock<table[i].sock)
		break;		
	}
		memmove(&table[i+1],&table[i],sizeof(MACHING_TABLE)*(count-i));
		table[i].sock=sock;
		strcpy(table[i].pnum,pnum);
		return i;
}
int delete_sock(int sock,MACHING_TABLE table[],int count)
{
	int ind,i;
	MACHING_TABLE tmp;
	tmp.sock=sock;
	MACHING_TABLE* r=bsearch((void*)&tmp,table,count,sizeof(MACHING_TABLE),compare_sock);
    if (r ==NULL){
		printf("can not find\n");
		return -1;}
	ind = r - table;
	memmove(&table[ind],&table[ind+1],sizeof(MACHING_TABLE)*(count-ind-1));
	strcpy(table[count].pnum,"");
	table[count].sock=0;
	return 0;
}
