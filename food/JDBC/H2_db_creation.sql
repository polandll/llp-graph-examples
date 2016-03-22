// H2 Database
// Connect at http://localhost:8082/
drop table author;
drop table book;
drop table authorbook;
create table author(name varchar(255) primary key, gender varchar(255));
insert into author values('Julia Child','F');
insert into author values('Simone Beck','F');
insert into author values('Louisette Bertholie','F');
insert into author values('Patricia Simon','F');
insert into author values('Alice Waters','F');
insert into author values('Patricia Curtan','F');
insert into author values('Kelsie Kerr','F');
insert into author values('Fritz Streiff','M');
insert into author values('Emeril Lagasse','M');
insert into author values('James Beard','M');
select * from author;

create table book(name varchar(255) primary key, year int, ISBN varchar(255));
insert into book values('The Art of French Cooking, Vol. 1',1961,'0-000-00000-0');
insert into book values('Simca''s Cuisine: 100 Classic French Recipes for Every Occasion',1972,'0-394-40152-2');
insert into book values('The French Chef Cookbook',1968,'0-394-40135-2');
insert into book values('The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution',2007,'0-307-33679-4');
select * from book;

create table authorbook(bname varchar(255), aname varchar(255), primary key(bname,aname));
insert into authorbook values('The Art of French Cooking, Vol. 1','Julia Child');
insert into authorbook values('The Art of French Cooking, Vol. 1','Simone Beck');
insert into authorbook values('The Art of French Cooking, Vol. 1','Louisette Bertholie');
insert into authorbook values('Simca''s Cuisine: 100 Classic French Recipes for Every Occasion','Simone Beck');
insert into authorbook values('Simca''s Cuisine: 100 Classic French Recipes for Every Occasion','Patricia Simon');
insert into authorbook values('The French Chef Cookbook','Julia Child');
insert into authorbook values('The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution','Alice Waters');
insert into authorbook values('The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution','Patricia Curtan');
insert into authorbook values('The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution','Kelsie Kerr');
insert into authorbook values('The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution','Fritz Streiff');
select * from authorbook;