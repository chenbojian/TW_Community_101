insert into CATEGORY(name) values("foods");
insert into CATEGORY(name) values("drink");
insert into CATEGORY(name) values("commodity");
insert into CATEGORY(name) values("groceries");

insert into GOODS(name, description, category_id, price, picture_url, status) values("bread", "delicious bread", 1, 3, "./img/1.jpg", "selling");
insert into GOODS(name, description, category_id, price, picture_url, status) values("cookie", "delicious cookie", 1, 4.5, "./img/2.jpg", "selling");
insert into GOODS(name, description, category_id, price, picture_url, status) values("beer", "delicious beer", 2, 5, "./img/3.jpg", "selling");
insert into GOODS(name, description, category_id, price, picture_url, status) values("soap", "delicious soap", 3, 3.5, "/img/4.jpg", "selling");
insert into GOODS(name, description, category_id, price, picture_url, status) values("towel", "delicious towel", 4, 6, "/img/5.jpg", "selling");

insert into USER(tel_phone) values("18888888880");
insert into USER(tel_phone) values("18888888881");
insert into USER(tel_phone) values("18888888882");
insert into USER(tel_phone) values("18888888883");
insert into USER(tel_phone) values("18888888884");

insert into ORDERS(user_id, address, create_time, status, total_price) values(1,"Beijing community101 unit5 201", 1437038663, "new", 3);
insert into ORDERS(user_id, address, create_time, status, total_price) values(2,"Beijing community101 unit5 302", 1437038863, "dispatching", 11);

insert into ORDER_GOODS(order_id, count, goods_name, goods_description, goods_category_name, goods_picture_url)
values(1, 1, "bread", "delecious bread", "foods", "./img/1.jpg");
insert into ORDER_GOODS(order_id, count, goods_name, goods_description, goods_category_name, goods_picture_url)
values(2, 1, "beer", "delecious beer", "drink", "./img/3.jpg");
insert into ORDER_GOODS(order_id, count, goods_name, goods_description, goods_category_name, goods_picture_url)
values(2, 1, "towel", "delecious towel", "groceries", "./img/5.jpg");
