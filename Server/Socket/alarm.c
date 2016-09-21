#include"my.h"
#include<time.h>
extern MACHING_TABLE arduino_table[1000000];
extern MACHING_TABLE arduino_alarm[1000000];
extern unsigned int counta;

void * alarm_thread(void *arg)
{
  MYSQL_RES   *sql_result;
  MYSQL_ROW   sql_row;
  int       query_stat,i,j; 
  MYSQL mysql,*connection  = NULL ;
  my_ulonglong resultnum;
  time_t pasttime,currenttime;
  struct tm *transtime;
  char query[200],buf_out[255];
  MACHING_TABLE tmp,*r;
  mysql_init(&mysql);
  time(&pasttime);
  connection=mysql_real_connect(&mysql, NULL, "root",PASS,"kdhong_db" ,3306, (char *)NULL, 0l);
  printf("alarm thread start\n");
  while(1)
  {
	 time(&currenttime);
	 printf("current time : %ld\n",currenttime);
	 if((currenttime-pasttime)>=60)
	 {
	   pasttime=currenttime;
	   transtime=localtime(&currenttime);
	   sprintf(query, "select * from alarm where time='%d:%d'",
		   transtime->tm_hour,transtime->tm_min);
     printf("%s\n",query);
	   query_stat = mysql_query(connection,query);
	   sql_result = mysql_store_result(connection);
     if(sql_result!=NULL) 
     resultnum = mysql_num_rows(sql_result);
     else
		{
			printf("nodata\n");
			continue;
		}
		for(i=0;i<resultnum;i++)
		{
		  sql_row = mysql_fetch_row(sql_result);
		  strcpy(tmp.pnum,sql_row[0]);//alarm table에서 제품명을 가져온다.
		  r=bsearch((void*)&tmp,arduino_alarm,counta,sizeof(MACHING_TABLE),compare_pnum);
		  for(j=0;j<counta;j++)
			  printf("%s\n",arduino_alarm[j].pnum);
		  if(r==NULL)
			{
			 printf("not connected\n");
			 continue;
			}
		  //제품명에해당하는 socket 탐색.
		  sprintf(buf_out,"%s","AAAA");
		  write(r->sock,buf_out,5);
      printf("%s to alarm\n",r->pnum);
		fflush(stdout);	

		}
	 }
	 sleep(15);
  }
  }

