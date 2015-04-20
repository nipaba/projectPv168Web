drop table proj.person;

create table proj.Person (
	personId INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 	
	name VARCHAR(50),
	surname VARCHAR(50),
    birth_date date ,
    birth_number VARCHAR(10),
    country VARCHAR(20)
 );
 
 
 insert into proj.Person (name,surname,birth_date,birth_number,country) 
 values('Tomas','Nesvadba', '1996-05-05','9605054486','Czech Republic');
 
 insert into proj.Person (name,surname,birth_date,birth_number,country) 
 values('Jan','Dlouhy', '1964-05-05','9605846486','Czech Republic');
 
 insert into proj.Person (name,surname,birth_date,birth_number,country) 
 values('Alexander','Veliky', '1945-04-05','9605481486','Greece');
 
 insert into proj.Person (name,surname,birth_date,birth_number,country) 
 values('Filipe','Gerare', '1918-04-05','9649881486','France');
 
 
