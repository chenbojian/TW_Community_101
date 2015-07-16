create table ORDERS(
  id int not null auto_increment,
  user_id int,
  address varchar(100),
  create_time timestamp,
  status varchar(20),
  total_price double,
  primary key(id),
  foreign key(user_id) references USER(id)
);