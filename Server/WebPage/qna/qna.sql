create table qna (
   num int not null auto_increment,
   group_num int not null,
   depth int not null,
   ord int not null,
   id char(15) not null,
   name  char(10) not null,
   nick  char(10) not null,
   subject char(100) not null,
   content text not null,
   regist_day char(20),
   hit int,
   is_html char(1),
   primary key(num)
);

