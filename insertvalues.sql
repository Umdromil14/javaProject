insert into country (name) values ('Belgium');
insert into country (name) values ('France');
insert into country (name) values ('Germany');
insert into country (name) values ('Netherlands');
insert into country (name) values ('Luxembourg');

insert into city (name,postalCode,country) values ('Brussels',1000,'Belgium');
insert into city (name,postalCode,country) values ('Antwerp', 2000,"Belgium");
insert into city (name,postalCode,country) values ('Charleroi', 6000, "Belgium");
insert into city (name,postalCode,country) values ('Liège', 4000,"Belgium");
insert into city (name,postalCode,country) values ('Bruges', 8000,"Belgium");
insert into city (name,postalCode,country) values ('Namur', 5000,"Belgium");
insert into city (name,postalCode,country) values ('Leuven', 3000,"Belgium");
insert into city (name,postalCode,country) values ('Mons', 7000,"Belgium");
insert into city (name,postalCode,country) values ('Anderlecht', 1070,"Belgium");
insert into city (name,postalCode,country) values ('Hasselt', 3500,"Belgium");

insert into city (name,postalCode,country) values ('Paris', 75000,"France");
insert into city (name,postalCode,country) values ('Marseille', 13000,"France");
insert into city (name,postalCode,country) values ('Lyon', 69000,"France");
insert into city (name,postalCode,country) values ('Toulouse', 31000,"France");
insert into city (name,postalCode,country) values ('Nice', 6000,"France");
insert into city (name,postalCode,country) values ('Nantes', 44000,"France");
insert into city (name,postalCode,country) values ('Strasbourg', 67000,"France");
insert into city (name,postalCode,country) values ('Montpellier', 34000,"France");
insert into city (name,postalCode,country) values ('Bordeaux', 33000,"France");
insert into city (name,postalCode,country) values ('Lille', 59000,"France");

insert into city (name,postalCode,country) values ('Berlin', 10115,"Germany");
insert into city (name,postalCode,country) values ('Hamburg', 20095,"Germany");
insert into city (name,postalCode,country) values ('Munich', 80331,"Germany");
insert into city (name,postalCode,country) values ('Cologne', 50667,"Germany");
insert into city (name,postalCode,country) values ('Frankfurt', 60311,"Germany");
insert into city (name,postalCode,country) values ('Stuttgart', 70173,"Germany");
insert into city (name,postalCode,country) values ('Düsseldorf', 40213,"Germany");
insert into city (name,postalCode,country) values ('Dortmund', 44135,"Germany");
insert into city (name,postalCode,country) values ('Essen', 45127,"Germany");
insert into city (name,postalCode,country) values ('Leipzig', 4109,"Germany");

insert into city (name,postalCode,country) values ('Amsterdam', 1012,"Netherlands");
insert into city (name,postalCode,country) values ('Rotterdam', 3011,"Netherlands");
insert into city (name,postalCode,country) values ('The Hague', 2511,"Netherlands");
insert into city (name,postalCode,country) values ('Utrecht', 3511,"Netherlands");
insert into city (name,postalCode,country) values ('Eindhoven', 5611,"Netherlands");
insert into city (name,postalCode,country) values ('Tilburg', 5038,"Netherlands");
insert into city (name,postalCode,country) values ('Groningen', 9711,"Netherlands");
insert into city (name,postalCode,country) values ('Almere', 1315,"Netherlands");
insert into city (name,postalCode,country) values ('Breda', 4811,"Netherlands");
insert into city (name,postalCode,country) values ('Nijmegen', 6511,"Netherlands");

insert into city (name,postalCode,country) values ('Luxembourg', 1012,"Luxembourg");
insert into city (name,postalCode,country) values ('Esch-sur-Alzette', 3011,"Luxembourg");
insert into city (name,postalCode,country) values ('Differdange', 2511,"Luxembourg");
insert into city (name,postalCode,country) values ('Dudelange', 3511,"Luxembourg");
insert into city (name,postalCode,country) values ('Ettelbruck', 5611,"Luxembourg");
insert into city (name,postalCode,country) values ('Diekirch', 5038,"Luxembourg");
insert into city (name,postalCode,country) values ('Wiltz', 9711,"Luxembourg");
insert into city (name,postalCode,country) values ('Rumelange', 1315,"Luxembourg");
insert into city (name,postalCode,country) values ('Grevenmacher', 4811,"Luxembourg");
insert into city (name,postalCode,country) values ('Vianden', 6511,"Luxembourg");

insert into product (name,actualUnitPrice,`remainingQuantity`) values ('Coca-Cola 1l', 1.5, 100);
insert into product (name,actualUnitPrice,`remainingQuantity`) values ('Fanta 1l', 1.5, 100);
insert into product (name,actualUnitPrice,`remainingQuantity`) values ('jack daniels 1l',29.99 , 100);
insert into product (name,actualUnitPrice,`remainingQuantity`) values ('jack daniels 0.5l', 19.99, 100);
insert into product (name,actualUnitPrice,`remainingQuantity`) values ('jupiler 33cl', 1.99, 300);
insert into product (name,actualUnitPrice,`remainingQuantity`) values ('jupiler 50cl', 2.99, 300);
insert into product (name,actualUnitPrice,`remainingQuantity`) values ('leffe 33cl', 2.99, 300);
insert into product (name,actualUnitPrice,`remainingQuantity`) values ('leffe 50cl', 3.99, 300);
insert into product (name,actualUnitPrice,`remainingQuantity`) values ('duvel 33cl', 2.99, 300);
insert into product (name,actualUnitPrice,`remainingQuantity`) values ('duvel 50cl', 3.99, 300);

insert into entity_status (code,desciption) values ("regular","regular customer");
insert into entity_status (code,desciption) values ("particular","particular customer");
insert into entity_status (code,desciption) values ("company","company customer");

-- à ajouter ?
-- insert into entity_status (code,desciption) values ("admin","admin user");
-- insert into entity_status (code,desciption) values ("employee","employee user");

insert into communication_type (`type`) values ("email");
insert into communication_type (`type`) values ("phone");
insert into communication_type (`type`) values ("mobile");

-- need to add more 
-- need to do document and what's around it
-- need to do communication
-- need to do contact

