create table GOODS(
  id int not null auto_increment,
  name varchar(50) unique,
  description text,
  category_id int,
  price double,
  picture_url varchar(50),
  status int,
  primary key(id),
  foreign key(category_id) references CATEGORY(id)
)