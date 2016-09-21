#include"my.h"

extern MACHING_TABLE mobile_table[1000000];
extern MACHING_TABLE arduino_table[1000000];
extern unsigned int countm,counta;
void * mobile_thread(void *arg);
void * arduino_thread(void *arg);
void * alarm_thread(void *arg);
pthread_mutex_t mutex;
int main(void)
{
	int i,maxcount;
	pthread_t id1,id2,id3;
	pthread_mutex_init(&mutex,NULL);	
	pthread_create((void*)&id1,NULL,mobile_thread,NULL);
	pthread_create((void*)&id2,NULL,arduino_thread,NULL);
	pthread_create((void*)&id3,NULL,alarm_thread,NULL);
/*   while(1)
	{
		system("clear");
		printf("%d %d %30s   %30s\n",countm,counta,"mobile","arduino");
		(countm>counta)?(maxcount=countm):(maxcount=counta);
		for(i=0;i<maxcount;i++)
		printf("%30s %d %30s %d\n",mobile_table[i].pnum,mobile_table[i].sock,
			arduino_table[i].pnum,arduino_table[i].sock);
		sleep(1);
	}*/
	pthread_join(id2,NULL);
	pthread_mutex_destroy(&mutex);
	return 0;
}
