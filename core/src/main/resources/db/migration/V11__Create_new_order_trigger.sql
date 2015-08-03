CREATE TRIGGER addNewOrderTrigger after insert ON ORDERS
FOR EACH ROW
insert into NEW_ORDERS(order_id) values(NEW.id);