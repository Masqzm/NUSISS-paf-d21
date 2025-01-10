# Creation of DB
create database paf_d21;

use paf_d21;

# To delete table
drop table tv_shows;

# Creation of table
create table tv_shows (
	prog_id int not null auto_increment,
	title char(64) not null,
	lang char(64) not null,
	official_site varchar(255),
	rating enum('G', 'PG', 'NC13', 'M18', 'R21') not null,
	user_rating float default '0.0',
	release_date date,
	image blob,
	constraint pk_prog_id primary key (prog_id),
	constraint chk_user_rating check(user_rating between 0.0 and 10.0)
);

# To display table constraints
select * from information_schema.table_constraints
where table_name = 'tv_shows'

create table tutorial (
	id int not null auto_increment,
	title varchar(100) not null,
	author varchar(80) not null,
	submission_date date,
	constraint pk_tutorial_id primary key (id)
);

# Insertion of records
insert into tutorial (title, author, submission_date) values ('CS1001', 'Chuk', '2025-02-01');
insert into tutorial (title, author, submission_date) values ('CS1002', 'Cheesy', '2025-02-06');
insert into tutorial (title, author, submission_date) values ('CS1003', 'Chris', '2025-02-07');
insert into tutorial (title, author, submission_date) values ('CS1004', 'Cristy', '2025-03-08');
insert into tutorial (title, author, submission_date) values ('CS1005', 'Melvin', '2025-03-10');
insert into tutorial (title, author, submission_date) values ('CS1006', 'Mel', '2025-03-11');
insert into tutorial (title, author, submission_date) values ('CS1001', 'Daisy', '2025-04-01');
insert into tutorial (title, author, submission_date) values ('CS1002', 'Damian', '2025-04-06');
insert into tutorial (title, author, submission_date) values ('CS1003', 'Danial', '2025-04-07');
insert into tutorial (title, author, submission_date) values ('CS1004', 'Derrick', '2025-04-08');
insert into tutorial (title, author, submission_date) values ('CS1005', 'Darren', '2025-04-10');
insert into tutorial (title, author, submission_date) values ('CS1006', 'Bob', '2025-05-11');

# Selecting records
select * from tutorial;

select * from tutorial limit 5 offset 10;		# select 5 records after the 10th record 

select * from tutorial where author = 'chris';

select * from tutorial where author like 'c%';		# select records with author starting with c
select * from tutorial where author like '%el%';	# select records with author containing with el

select * from tutorial 
where submission_date >= '2025-02-01' and submission_date < '2025-02-28'
order by id desc, author asc;		# sorts by id first followed by author

select * from tutorial where submission_date between '2025-02-01' and '2025-02-28';

select * from tutorial where submission_date not between '2025-02-01' and '2025-02-28';

select * from tutorial where title in ('cs1001', 'cs1002', 'cs1003');	# select records with these titles



insert into tv_shows (title, lang, rating, user_rating, release_date) values ('AI will win', 'Chinese', 'PG', 7.5, '2025-10-25');
insert into tv_shows (title, lang, rating, user_rating, release_date) values ('James Bond', 'English', 'M18', 8.5, '2025-02-25');
insert into tv_shows (title, lang, rating, user_rating, release_date) values ('Squid Game 2', 'Korean', 'M18', 8, '2025-01-01');
insert into tv_shows (title, lang, rating, user_rating, release_date) values ('Hunger Games', 'English', 'NC13', 7.6, '2025-01-05');

select  * from tv_shows;

select * from tv_shows where user_rating >= 8 or rating in ('M18', 'NC13'); 