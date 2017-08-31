// MySQL Database
// Connect at http://localhost:3306/

drop database if exists jdbcexample;
create database jdbcexample;
use `jdbcexample`;

//drop table author;
//drop table book;
//drop table authorbook;
create table author(
	name varchar(255) primary key, 
	gender varchar(255)
);
create table book(
	name varchar(255) primary key, 
	year int, 
	ISBN varchar(255)
);

create table authorbook(
	bname varchar(255), 
	aname varchar(255), 
	primary key(bname,aname)
);
