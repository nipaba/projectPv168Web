drop table proj.Ownership;

create table proj.Ownership(

	ownerShipID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 	
	
	personID INTEGER NOT NULL,
	landId INTEGER NOT NULL,
	start_date DATE,
	end_date DATE,
	
	FOREIGN KEY (personID) REFERENCES proj.Person(personId), 
	FOREIGN KEY (landId) REFERENCES proj.Land(landId)

 );
 
 
 insert into proj.Ownership (personID,landId,start_date,end_date) 
 values(1,1, '1996-05-05','1999-05-05');
 
insert into proj.Ownership (personID,landId,start_date,end_date) 
 values(1,1, '1996-05-05','1999-05-05');
 
 insert into proj.Ownership (personID,landId,start_date,end_date) 
 values(1,1, '1996-05-05','1999-05-05');
 
 insert into proj.Ownership (personID,landId,start_date,end_date) 
 values(1,1, '1996-05-05','1999-05-05');
 