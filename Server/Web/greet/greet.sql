create table greet (
   name char(15) not null,
   medical_num char(20) not null,
   social_num char(20) not null,
   product_num char(20) not null,
   emergency_num1 char(20) not null,
   emergency_num2 char(20) not null,
   address char(80) not null,
   medicine char(200) not null,
   medical_history char(200) not null,
   special char(200), 
   regist_day char(20),
   hit int,
   is_html char(1),
   primary key(name)
);

