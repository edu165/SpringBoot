commit;
create table IF NOT EXISTS AUTHOR(
	id int auto_increment PRIMARY KEY,
	name varchar(25) ,
	dateBirth date );
	
commit;	

create table IF NOT EXISTS BOOK(
	nameBook varchar(25),
	ISBN varchar(25),
	nameAuthor varchar(25)
);
commit
	



