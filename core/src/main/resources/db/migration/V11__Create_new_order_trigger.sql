CREATE TRIGGER community101.addNewOrderTrigger after insert ON community101.ORDERS
FOR EACH ROW
insert into NEW_ORDERS(order_id) values(NEW.id);