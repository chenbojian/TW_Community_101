alter table GOODS
change column price price INT NULL DEFAULT null;
alter table ORDERS
change column total_price total_price int null default null;
alter table ORDER_GOODS
add column goods_price int null default null;