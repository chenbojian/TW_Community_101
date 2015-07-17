alter table community101.GOODS
change column price price INT NULL DEFAULT null;
alter table community101.ORDERS
change column total_price total_price int null default null;
alter table community101.ORDER_GOODS
add column goods_price int null default null;