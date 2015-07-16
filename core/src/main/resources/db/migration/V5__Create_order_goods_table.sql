create table ORDER_GOODS(
  id int not null auto_increment,
  order_id int,
  count int,
  goods_name varchar(50),
  goods_description text,
  goods_category_name varchar(50),
  goods_picture_url varchar(50),
  primary key(id),
  foreign key(order_id) references ORDERS(id)
);