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
insert into tv_shows (title, lang, rating, user_rating, release_date) values ('Brooklyn 99', 'English', 'PG', 8.2, '2022-05-22');
insert into tv_shows (title, lang, rating, user_rating, release_date) values ('Hunger Games 2', 'English', 'NC13', 7.8, '2025-01-07');
insert into tv_shows (title, lang, rating, user_rating, release_date) values ('Hunger Games 3', 'English', 'NC13', 7.6, '2025-01-08');
insert into tv_shows (title, lang, rating, user_rating, release_date) values ('Squid Game', 'Korean', 'M18', 8, '2023-06-10');
insert into tv_shows (title, lang, rating, user_rating, release_date) values ('Test', 'English', 'PG', 7, '2024-08-11');
insert into tv_shows (title, lang, rating, user_rating, release_date) values ('Test', 'English', 'PG', 7, '2024-08-11');
insert into tv_shows (title, lang, rating, user_rating, release_date) values ('Test2', 'English', 'R21', 8.2, '2024-12-11');

select  * from tv_shows;

select * from tv_shows where user_rating >= 8 or rating in ('M18', 'NC13'); 


# PAF Day 22
select distinct lang from tv_shows;				# distinct - selects unique lang values

select distinct lang, rating from tv_shows;


select count(*) from tv_shows where lang like 'English';

select count(distinct title) from tv_shows where lang in ('English', 'Korean');

select avg(user_rating) from tv_shows where lang like 'English';

select sum(user_rating) from tv_shows where lang like 'Korean';

select sum(user_rating) / count(*) from tv_shows where lang like 'Korean';


select rating, count(rating) from tv_shows group by rating;

select rating, count(rating) as CNT from tv_shows 				# CNT - column name
group by rating 
order by count(rating) desc;

select rating, count(rating) from tv_shows
where lang in (select distinct lang from tv_shows)				# query in a query
group by rating
order by count(rating) asc;

# Usage of "having" for conditions in aggregate fn.s (filtering)
select rating, count(rating) from tv_shows
group by rating
having count(rating) > 2;					

# Nested query (similar to above)
select  * from
(select rating, count(rating) as cnt from tv_shows
group by rating
order by rating asc) as tableA
where tableA.cnt > 2;


select title, rating, lang,
max(rating) over (partition by lang) as max_rating
from tv_shows;



# OVER PARTITION BY example
create table car (
	id int not null auto_increment,
    make varchar(50),
    model varchar(50),
    cartype varchar(50),
    price float default '10000.0',
    constraint pk_car_id primary key (id)
);

insert into car (make, model, cartype, price) values ('Hyundai', 'Avante', 'sedan', 80000.0);
insert into car (make, model, cartype, price) values ('Toyota', 'Altis', 'sedan', 82850.0);
insert into car (make, model, cartype, price) values ('Ford', 'Falcom', 'low cost', 50000.0);
insert into car (make, model, cartype, price) values ('Renault', 'Megane', 'standard', 90000.0);
insert into car (make, model, cartype, price) values ('Hyundai', 'Box', 'premium', 120000.0);
insert into car (make, model, cartype, price) values ('Honda', 'Civic', 'sports', 180000.0);
insert into car (make, model, cartype, price) values ('Toyota', 'Two', 'sports', 155000.0);
insert into car (make, model, cartype, price) values ('Honda', 'Fit', 'sports', 152850.0);
insert into car (make, model, cartype, price) values ('Ford', 'Galaxy', 'standard', 79000.0);
insert into car (make, model, cartype, price) values ('Toyota', 'Penguin', 'sedan', 69000.0);
insert into car (make, model, cartype, price) values ('Renault', 'Fuego', 'sports', 65000.0);

select * from car;

select make, model, cartype, price, 
max(price) over (partition by cartype) as max_cartype
from car;

select make, model, cartype, price, 
sum(price) over (partition by cartype) as sum_cartype
from car;

select make, model, cartype, price, 
sum(price) over (partition by make) as sum_make
from car;

select make, model, cartype, price, 
avg(price) over (partition by cartype) as avg_cartype
from car;

select make, model, cartype, price, 
avg(price) over () as overall_avg_price, 
avg(price) over (partition by cartype) as avg_cartype
from car;


# Practice
create table employee (
	id int not null auto_increment,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	email varchar(255),
	job_title varchar(100),
	department varchar(100),
	employment_date date,
	salary float,
	constraint pk_employee_id primary key (id)
);

select * from employee;

# Adding variable TO existing TABLE 
alter table employee
add isActive boolean;

# Set the next auto_increment to 1 (note only one col can have auto_increment in a table)
alter table employee
auto_increment = 1;


create table dependant(
	id int not null auto_increment,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	birth_date date,
	relationship varchar(30),
	employee_id int,
	constraint pk_dependant_id primary key (id),
	constraint fk_dep_emp_id foreign key (employee_id) references employee(id)
);

# For GetMapping
select * from employee;
select * from employee where id = 1;

# For DeleteMapping
update employee set isActive = false where id = 1;
# Not recommended
delete from employee where id = 1;

# For PostMapping
insert into employee (first_name, last_name, email, job_title, department, employment_date, salary, isActive) values
('Darryl', 'Ng', 'darrylng@nus.edu.sg', 'Lecturer', 'ISS', '2021-09-08', 7000.0, true);

# For UpdateMapping
update employee set
first_name = 'Alibaba',
email = 'alibaba@alibaba.com.sg',
job_title = 'CEO',
department = 'master of no one',
employment_date = '2025-01-01',
salary = '2000000.0'
where id = 1;