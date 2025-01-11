create user replicator with replication encrypted password 'replicator_password';
select pg_create_physical_replication_slot('replication_slot');
create table if not exists orders(
    order_id serial primary key,
    username varchar(100),
    product varchar(100),
    quantity integer,
    status varchar(100),
    address varchar(100)
);
insert into orders(order_id, username, product, quantity, status, address) values (1, 'mahmood', 'cap', 1, 'pending', 'test');