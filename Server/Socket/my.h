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
#define PASS "sungkyulcpu"
 typedef struct 
 {
	 int sock;
	 int errc;
	 char pnum[100];
 }MACHING_TABLE; 
int add_sock(int sock,char* pnum,MACHING_TABLE table[],int count);
int add_ardu_sock(int sock,char* pnum,MACHING_TABLE table[],int count);
int delete_sock(int sock,MACHING_TABLE table[],int count);
int recvn(int s, char *buf, int len);
int recvs(int s, char *buf, int len);
int compare_pnum(const void *a,const void *b);
int compare_sock(const void *a,const void *b);
