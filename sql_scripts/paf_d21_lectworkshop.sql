use paf_d21;

create table room (
	id int not null auto_increment,
	room_type varchar(150) not null,
	price float not null,
	constraint pk_room_id primary key (id)
);

create table customer (
	id int not null auto_increment,
	fullname varchar(255) not null,
	email varchar(255) not null,
	constraint pk_customer_id primary key (id)
);

create table reservation (
	id int not null auto_increment,
	room_id int,
	customer_id int,
	start_date date,
	end_date date,
	cost float,
	constraint pk_reservation_id primary key (id),
	constraint fk_room_id foreign key (room_id) references room(id),
	constraint fk_customer_id foreign key (customer_id) references customer(id)
);


insert into room (room_type, price) values ('standard', 150);
insert into room (room_type, price) values ('deluxe', 200);
insert into room (room_type, price) values ('super deluxe', 250);
insert into room (room_type, price) values ('president', 300);
insert into room (room_type, price) values ('suite', 350);
insert into room (room_type, price) values ('test', 100);

insert into customer (fullname, email) values ('Fred', 'fred@gmail.com');
insert into customer (fullname, email) values ('Bob', 'bob@gmail.com');
insert into customer (fullname, email) values ('George', 'geo@gmail.com');
insert into customer (fullname, email) values ('Charlie', 'charlie@gmail.com');
insert into customer (fullname, email) values ('Test', 'test@gmail.com');

select * from room;
select * from customer;

delete from customer;