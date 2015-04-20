drop table proj.Land;

create table proj.Land(
   landId INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 	
   size DOUBLE,
   buildUpArea DOUBLE,
   catastralArea VARCHAR(20),
   land_type VARCHAR(20),
   notes VARCHAR(400)
   );
   
insert into proj.Land (size,buildUpArea,catastralArea,land_type,notes )
values (4.5,2.2,'zlinsko','pole','lorem ipsum dolor de fortuna des mi diio');

insert into proj.Land (size,buildUpArea,catastralArea,land_type,notes )
values (4.5,2.2,'zlinsko','pole','lorem ipsum dolor de fortuna des mi diio');

insert into proj.Land (size,buildUpArea,catastralArea,land_type,notes )
values (4.5,2.2,'zlinsko','pole','lorem ipsum dolor de fortuna des mi diio');

insert into proj.Land (size,buildUpArea,catastralArea,land_type,notes )
values (4.5,2.2,'zlinsko','pole','lorem ipsum dolor de fortuna des mi diio');

insert into proj.Land (size,buildUpArea,catastralArea,land_type,notes )
values (4.5,2.2,'zlinsko','pole','lorem ipsum dolor de fortuna des mi diio');

insert into proj.Land (size,buildUpArea,catastralArea,land_type,notes )
values (4.5,2.2,'zlinsko','pole','lorem ipsum dolor de fortuna des mi diio');