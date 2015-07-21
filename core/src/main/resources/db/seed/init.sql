insert into CATEGORY(name) values("foods");
insert into CATEGORY(name) values("drink");
insert into CATEGORY(name) values("commodity");
insert into CATEGORY(name) values("groceries");

insert into GOODS(name, description, category_id, price, picture_url, status) values("bread", "delicious bread", 1, 300, "http://images.quanjing.com/fod007/thu/fod-185130.jpg", "selling");
insert into GOODS(name, description, category_id, price, picture_url, status) values("cookie", "delicious cookie", 1, 450, "http://images.quanjing.com/fod007/thu/fod-185130.jpg", "selling");
insert into GOODS(name, description, category_id, price, picture_url, status) values("beer", "delicious beer", 2, 500, "http://img7.paipaiimg.com/item-0FCA4DE7-57684C1400000000040100000FBE8D52.0.200x200.jpg", "selling");
insert into GOODS(name, description, category_id, price, picture_url, status) values("soap", "delicious soap", 3, 350, "http://en-img-dis.gcimg.net/product/day_20110412/s_f0d6e894e37a2302807e7d83cc0a802b.jpg", "selling");
insert into GOODS(name, description, category_id, price, picture_url, status) values("towel", "delicious towel", 4, 600, "http://pic.baike.soso.com/p/20120228/bki-20120228110421-1449495487.jpg", "selling");

insert into USER(tel_phone) values("18888888880");
insert into USER(tel_phone) values("18888888881");
insert into USER(tel_phone) values("18888888882");
insert into USER(tel_phone) values("18888888883");
insert into USER(tel_phone) values("18888888884");

insert into ORDERS(user_id, address, create_time, status, total_price) values(1,"Beijing community101 unit5 201", now(), "new", 300);
insert into ORDERS(user_id, address, create_time, status, total_price) values(2,"Beijing community101 unit5 302", now(), "new", 1100);
insert into ORDERS(user_id, address, create_time, status, total_price) values(3,"Beijing community101 unit5 602", now(), "dispatching", 800);

insert into ORDER_GOODS(order_id, count, goods_name, goods_description, goods_category_name, goods_picture_url, goods_price)
values(1, 1, "bread", "delecious bread", "foods", "./img/1.jpg", 300);
insert into ORDER_GOODS(order_id, count, goods_name, goods_description, goods_category_name, goods_picture_url, goods_price)
values(2, 1, "beer", "delecious beer", "drink", "./img/3.jpg", 500);
insert into ORDER_GOODS(order_id, count, goods_name, goods_description, goods_category_name, goods_picture_url, goods_price)
values(2, 1, "towel", "delecious towel", "groceries", "./img/5.jpg", 600);
insert into ORDER_GOODS(order_id, count, goods_name, goods_description, goods_category_name, goods_picture_url, goods_price)
values(3, 1, "bread", "delecious bread", "foods", "./img/1.jpg", 300);
insert into ORDER_GOODS(order_id, count, goods_name, goods_description, goods_category_name, goods_picture_url, goods_price)
values(3, 1, "beer", "delecious beer", "drink", "./img/3.jpg", 500);
