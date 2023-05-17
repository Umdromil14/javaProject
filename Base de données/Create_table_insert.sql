-- Active: 1682586395608@@127.0.0.1@3306@barasserie

CREATE TABLE PRODUCT(  
    id INT NOT NULL AUTO_INCREMENT ,
    name VARCHAR(255) NOT NULL,
    actualUnitPrice DECIMAL(4,2) NOT NULL,
    remainingQuantity INT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE DOC_TYPE(
    code VARCHAR(32) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (code)
) Engine=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE DOC_STATUS(
    code VARCHAR(32) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (code)
) Engine=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE WORKFLOW_TYPE(
    code VARCHAR(32) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (code)
) Engine=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE COMMUNICATION_TYPE(
    type VARCHAR(32) NOT NULL,
    PRIMARY KEY (type)
) Engine=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ENTITY_STATUS(
    code VARCHAR(32) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (code)
) Engine=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE COUNTRY(
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE CITY(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    postalCode INT NOT NULL,
    country VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (country) REFERENCES COUNTRY(name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ADDRESS(
    id INT NOT NULL AUTO_INCREMENT,
    street VARCHAR(100) NOT NULL,
    number INT NOT NULL,
    city INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (city) REFERENCES CITY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE BUSINESS_ENTITY(
    id INT NOT NULL AUTO_INCREMENT,
    address INT NOT NULL,
    tier VARCHAR(32) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    firstname VARCHAR(50),
    isClient BOOLEAN NOT NULL,
    isSupplier BOOLEAN NOT NULL,
    registrationDate DATE,
    creditLimit DECIMAL(5,2),
    hashedPassword VARCHAR(255),
    salt VARCHAR(5),
    PRIMARY KEY (id),
    FOREIGN KEY (address) REFERENCES ADDRESS(id),
    Foreign Key (tier) REFERENCES ENTITY_STATUS(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE BANKING_INFO(
    entity INT NOT NULL,
    iban VARCHAR(34) NOT NULL,
    bic VARCHAR(11),
    PRIMARY KEY (entity),
    FOREIGN KEY (entity) REFERENCES BUSINESS_ENTITY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE CONTACT(
    contact1 INT NOT NULL,
    contact2 INT NOT NULL,
    PRIMARY KEY (contact1, contact2),
    FOREIGN KEY (contact1) REFERENCES BUSINESS_ENTITY(id),
    FOREIGN KEY (contact2) REFERENCES BUSINESS_ENTITY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE COMMUNICATION(
    entity INT NOT NULL,
    type VARCHAR(32) NOT NULL,
    communicationDetails VARCHAR(255) NOT NULL,
    PRIMARY KEY (entity, type),
    FOREIGN KEY (entity) REFERENCES BUSINESS_ENTITY(id),
    FOREIGN KEY (type) REFERENCES COMMUNICATION_TYPE(type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE WORKFLOW(
    id INT NOT NULL AUTO_INCREMENT,
    agent INT NOT NULL,
    type VARCHAR(32) NOT NULL,
    isClosed BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (agent) REFERENCES BUSINESS_ENTITY(id),
    FOREIGN KEY (type) REFERENCES WORKFLOW_TYPE(code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE DOCUMENT(
    id INT NOT NULL AUTO_INCREMENT,
    docType VARCHAR(32) NOT NULL,
    docStatus VARCHAR(32) NOT NULL,
    workflow INT NOT NULL,
    date DATE NOT NULL,
    exclVATTotal DECIMAL(5,2) NOT NULL,
    inclVATTotal DECIMAL(5,2) NOT NULL,
    reminderCount INT,
    payementDeadline DATE,
    PRIMARY KEY (id,docType),
    FOREIGN KEY (docType) REFERENCES DOC_TYPE(code),
    FOREIGN KEY (docStatus) REFERENCES DOC_STATUS(code),
    FOREIGN KEY (workflow) REFERENCES WORKFLOW(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE TRANSACTION_DETAIL(
    id INT NOT NULL AUTO_INCREMENT,
    document INT NOT NULL,
    docType VARCHAR(32) NOT NULL,
    product INT NOT NULL,
    quantity INT NOT NULL,
    unitPrice DECIMAL(4,2) NOT NULL,
    PRIMARY KEY (id, document, docType),
    FOREIGN KEY (document, docType) REFERENCES DOCUMENT(id, docType),
    FOREIGN KEY (product) REFERENCES PRODUCT(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

insert into entity_status (code,description) values ("regular","regular customer");
insert into entity_status (code,description) values ("particular","particular customer");
insert into entity_status (code,description) values ("company","company customer");

insert into communication_type (`type`) values ("email");

INSERT INTO workflow_type (code, description) VALUES ("client_transaction", "client transaction");

INSERT INTO doc_type (code, description) VALUES ("invoice", "invoice");

INSERT INTO doc_status (code, description) VALUES ("paid", "paid");
INSERT INTO doc_status (code, description) VALUES ("unpaid", "unpaid");

INSERT INTO address (id, street, number, city) VALUES
(1, 'Rue de la Loi', 100, 1);

-- Inserting addresss for Antwerp (city = 2)
INSERT INTO address (id, street, number, city) VALUES
(2, 'Meir', 100, 2);

-- Inserting streets for Charleroi (city = 3)
INSERT INTO address (id, street, number, city) VALUES
(3, 'Rue de la Montagne', 100, 3);

-- Inserting streets for Liège (city = 4)
INSERT INTO address (id, street, number, city) VALUES
(4, 'Rue des Carmes', 100, 4);

-- Inserting streets for Bruges (city = 5)
INSERT INTO address (id, street, number, city) VALUES
(5, 'Steenstraat', 100, 5);

-- Inserting streets for Namur (city = 6)
INSERT INTO address (id, street, number, city) VALUES
(6, 'Rue de Fer', 100, 6);

-- Inserting streets for Leuven (city = 7)
INSERT INTO address (id, street, number, city) VALUES
(7, 'Bondgenotenlaan', 100, 7);

-- Inserting streets for Mons (city = 8)
INSERT INTO address (id, street, number, city) VALUES
(8, 'Rue de la Cité', 100, 8);

-- Inserting streets for Anderlecht (city = 9)
INSERT INTO address (id, street, number, city) VALUES
(9, 'Rue Wayez', 100, 9);

-- Inserting streets for Hasselt (city = 10)
INSERT INTO address (id, street, number, city) VALUES
(10, 'Dorpsstraat', 100, 10);

-- Inserting streets for Paris (city = 11)
INSERT INTO address (id, street, number, city) VALUES
(11, 'Champs-Élysées', 100, 11);

-- Inserting streets for Marseille (city = 12)
INSERT INTO address (id, street, number, city) VALUES
(12, 'Cours Julien', 100, 12);

-- Inserting streets for Lyon (city = 13)
INSERT INTO address (id, street, number, city) VALUES
(13, 'Rue de la République', 100, 13);

-- Inserting streets for Toulouse (city = 14)
INSERT INTO address (id, street, number, city) VALUES
(14, 'Place du Capitole', 100, 14);

-- Inserting streets for Nice (city = 15)
INSERT INTO address (id, street, number, city) VALUES
(15, 'Promenade des Anglais', 100, 15);

-- Inserting streets for Nantes (city = 16)
INSERT INTO address (id, street, number, city) VALUES
(16, 'Rue du Château', 100, 16);

-- Inserting streets for Strasbourg (city = 17)
INSERT INTO address (id, street, number, city) VALUES
(17, 'Rue des Juifs', 100, 17);

-- Inserting streets for Montpellier (city = 18)
INSERT INTO address (id, street, number, city) VALUES
(18, 'Place de la Comédie', 100, 18);

-- Inserting streets for Bordeaux (city = 19)
INSERT INTO address (id, street, number, city) VALUES
(19, 'Rue Sainte-Catherine', 100, 19);

-- Inserting streets for Lille (city = 20)
INSERT INTO address (id, street, number, city) VALUES
(20, 'Rue de Béthune', 100, 20);

-- Inserting streets for Berlin (city = 21)
INSERT INTO address (id, street, number, city) VALUES
(21, 'Unter den Linden', 100, 21);

-- Inserting streets for Hamburg (city = 22)
INSERT INTO address (id, street, number, city) VALUES
(22, 'Mönckebergstraße', 100, 22);

-- Inserting streets for Munich (city = 23)
INSERT INTO address (id, street, number, city) VALUES
(23, 'Maximilianstraße', 100, 23);

-- Inserting streets for Cologne (city = 24)
INSERT INTO address (id, street, number, city) VALUES
(24, 'Hohe Straße', 100, 24);

-- Inserting streets for Frankfurt (city = 25)
INSERT INTO address (id, street, number, city) VALUES
(25, 'Zeil', 100, 25);

-- Inserting streets for Stuttgart (city = 26)
INSERT INTO address (id, street, number, city) VALUES
(26, 'Königstraße', 100, 26);

-- Inserting streets for Düsseldorf (city = 27)
INSERT INTO address (id, street, number, city) VALUES
(27, 'Königsallee', 100, 27);

-- Inserting streets for Dortmund (city = 28)
INSERT INTO address (id, street, number, city) VALUES
(28, 'Westenhellweg', 100, 28);

-- Inserting streets for Essen (city = 29)
INSERT INTO address (id, street, number, city) VALUES
(29, 'Limbecker Straße', 100, 29);

-- Inserting streets for Leipzig (city = 30)
INSERT INTO address (id, street, number, city) VALUES
(30, 'Grimmaische Straße', 100, 30);

-- Inserting streets for Amsterdam (city = 31)
INSERT INTO address (id, street, number, city) VALUES
(31, 'Damrak', 100, 31);

-- Inserting streets for Rotterdam (city = 32)
INSERT INTO address (id, street, number, city) VALUES
(32, 'Coolsingel', 100, 32);

-- Inserting streets for The Hague (city = 33)
INSERT INTO address (id, street, number, city) VALUES
(33, 'Lange Voorhout', 100, 33);

-- Inserting streets for Utrecht (city = 34)
INSERT INTO address (id, street, number, city) VALUES
(34, 'Oudegracht', 100, 34);

-- Inserting streets for Eindhoven (city = 35)
INSERT INTO address (id, street, number, city) VALUES
(35, 'Demer', 100, 35);

-- Inserting streets for Tilburg (city = 36)
INSERT INTO address (id, street, number, city) VALUES
(36, 'Heuvelstraat', 100, 36);

-- Inserting streets for Groningen (city = 37)
INSERT INTO address (id, street, number, city) VALUES
(37, 'Grote Markt', 100, 37);

-- Inserting streets for Almere (city = 38)
INSERT INTO address (id, street, number, city) VALUES
(38, 'De Diagonaal', 100, 38);

-- Inserting streets for Breda (city = 39)
INSERT INTO address (id, street, number, city) VALUES
(39, 'Grote Markt', 100, 39);

-- Inserting streets for Nijmegen (city = 40)
INSERT INTO address (id, street, number, city) VALUES
(40, 'Lange Hezelstraat', 100, 40);

-- Inserting streets for Luxembourg (city = 41)
INSERT INTO address (id, street, number, city) VALUES
(41, 'Grand-Rue', 100, 41);

-- Inserting streets for Esch-sur-Alzette (city = 42)
INSERT INTO address (id, street, number, city) VALUES
(42, 'Rue de l''Alzette', 100, 42);

-- Inserting streets for Differdange (city = 43)
INSERT INTO address (id, street, number, city) VALUES
(43, 'Place du Marché', 100, 43);

-- Inserting streets for Dudelange (city = 44)
INSERT INTO address (id, street, number, city) VALUES
(44, 'Rue de la Libération', 100, 44);

-- Inserting streets for Ettelbruck (city = 45)
INSERT INTO address (id, street, number, city) VALUES
(45, 'Rue du Parc', 100, 45);

-- Inserting streets for Diekirch (city = 46)
INSERT INTO address (id, street, number, city) VALUES
(46, 'Rue de l''Indépendance', 100, 46);

-- Inserting streets for Wiltz (city = 47)
INSERT INTO address (id, street, number, city) VALUES
(47, 'Grand-Rue', 100, 47);

-- Inserting streets for Rumelange (city = 48)
INSERT INTO address (id, street, number, city) VALUES
(48, 'Rue de l''Alzette', 100, 48);

-- Inserting streets for Grevenmacher (city = 49)
INSERT INTO address (id, street, number, city) VALUES
(49, 'Rue de Trèves', 100, 49);

-- Inserting streets for Vianden (city = 50)
INSERT INTO address (id, street, number, city) VALUES
(50, 'Rue du Vieux Marché', 100, 50);

INSERT INTO business_entity (id, address,tier, lastname, firstname, isClient, isSupplier, registrationDate,creditLimit, hashedPassword, salt) VALUES
(1, 1, 'regular', 'John', 'Doe', 1, 0, '2022-05-10', null, '465e2b9fbfc66b8a6bd01608afe4727e860e4d804277dc54111e34e4fa2e06d8', 'qtfdv'),
(2, 2, 'regular', 'Jane', 'Smith', 1, 0, '2022-05-11', null, 'ae1e30d2db8f9deaa7a898c2b89a604e091a072c4fc2e260fc65e3c610ee83d6', 'ybn9w'),
(3, 3, 'regular', 'Michael', 'Johnson', 1, 0, '2022-05-12', null, 'c7ef4b9b933d600d0c2734e9c19d77985fcd7a0c541036693f3c56d9a44964e1', 'p35t7'),
(4, 4, 'regular', 'Emily', 'Wilson', 1, 0, '2022-05-13', null, '8fb02e5efc0b8a9a8af660933dfde67b165c2db0dfb947db5dbca981a1842278', 'fdx2e'),
(5, 5, 'regular', 'Robert', 'Brown', 1, 0, '2022-05-14', null, 'ecab7680ac5fffebc3a47a2c6b4f19bda7e50e40d6ce79b4b78c85fc8d7b5540', 'gk1rp'),
(6, 6, 'regular', 'Olivia', 'Davis', 1, 0, '2022-05-15', null, 'a4b3cc66a7718e0175c58300b32710c4e5164a4395f70da8f7293c4c4566d4eb', 'mht2l'),
(7, 7, 'regular', 'William', 'Miller', 1, 0, '2022-05-16', null, 'e48f55a87ff5b9b57147a3705fcd77e0d262d5f6022272f3d73a92746467e5bc', '7lgq9'),
(8, 8, 'regular', 'Emma', 'Anderson', 1, 0, '2022-05-17', null, '169a152af557d930fc5f34260a6d3d1691f59eef1451ce21d3c5ed81b48b84af', 'fzrk4'),
(9, 9, 'regular', 'James', 'Thompson', 1, 0, '2022-05-18', null, '040d4e850a7f9138824f9ab96a940efb2ff1b2bfeef61651b94a7a4adfa34d9b', '6j3hy'),
(10, 10, 'regular', 'Sophia', 'White', 1, 0, '2022-05-19', null, '07a8317e2cb160c8a4b571bca91d2a551bea8d6e5fde7e87a727b5c6ad0b7c3f', 'vx4d8'),
(11, 11, 'regular', 'Benjamin', 'Harris', 1, 0, '2022-05-20', null, 'a1db5b88a209b6c982a4a90b6ab9b7175f7ff4d5f6a5c24db61462ce53132e3c', 'trf2n'),
(12, 12, 'regular', 'Isabella', 'Martin', 1, 0, '2022-05-21', null, '1b88ef4cbb5265f7dc429e1171ccf237b68c8c68f7d3f196c0c12d614b5040f7', 'ldz6p'),
(13, 13, 'regular', 'Henry', 'Lewis', 1, 0, '2022-05-22', null, 'bc019593b860a7c2007532fc6672ab87c25507e6daeb9d14c6c71a0b055c83d7', '85fjt'),
(14, 14, 'regular', 'Ava', 'Clark', 1, 0, '2022-05-23', null, '0dafe2073c54e7996035eaa6f8af6bbf97ab1338ff651bf46fcb3e1d4e0b2004', 'tn4xd'),
(15, 15, 'regular', 'Jackson', 'Hall', 1, 0, '2022-05-24', null, 'd0b65a0e204c0d2f19491cc2e1842fde7ef20a0f4992e58e3e19a0a1d066b55f', 'd9ykp'),
(16, 16, 'regular', 'Mia', 'Walker', 1, 0, '2022-05-25', null, '5e63de52f69be8307d6e1a3093ebbf01e06f81e1c9aa59d615d68a9a7aae5c6f', 'q1lr7'),
(17, 17, 'regular', 'Sebastian', 'Allen', 1, 0, '2022-05-26', null, 'ba7881c3e947bd6595b0b47b8af26e2c4e2697be8a6c138dfb5ad0d582c03d41', '7hvx9'),
(18, 18, 'regular', 'Amelia', 'Hill', 1, 0, '2022-05-27', null, 'd798d0d4b27e780c62f67d7ee6fdcf7f5a8e6db798b0abf4f59fcd5b6423b68b', '2fkqy'),
(19, 19, 'regular','Elijah', 'Young', 1, 0, '2022-05-28', null, 'd72c382dd5ab9ea620e087a328af41f8246468c17e47e56c276e68138b7df75c', 'lpqk3'),
(20, 20, 'regular', 'Charlotte', 'Wright', 1, 0, '2022-05-29', null, '18b8b63cd02a7a0f2975e2490fd58bc6e3c32cbf00f79df87c77bc4dd88eb199', 'gj7t6'),
(21, 21, 'regular', 'Matthew', 'Green', 1, 0, '2022-05-30', null, 'fd3e43979e6d8d62bd8b47a9630ca7cc5e7e7bd23d8dddfef464e9a9d982a464', 'q0sr4'),
(22, 22, 'regular', 'Chloe', 'Carter', 1, 0, '2022-05-31', null, 'f53ea5b7ed1fc968808d20b7f0b3ad34e3d3afaf7aa28e06f2596dc1f3291fd5', 'y5nsj'),
(23, 23, 'regular', 'Daniel', 'Adams', 1, 0, '2022-06-01', null, '92a3e8bc2e4c62d3e6f04a847b6d5b2c1dc70c46c9c33fc5e0bd3ed1292fa781', 'jpx2w'),
(24, 24, 'regular', 'Grace', 'Bell', 1, 0, '2022-06-02', null, '2029c369f8d4da35e7348e11bc49f663f92c1358d994951d6a8a35e72686f2c1', 'yf6gb'),
(25, 25, 'regular', 'Samuel', 'Cook', 1, 0, '2022-06-03', null, 'dfb2a9cb2b997aa02308c71013dd7a854bc789e9c1ccf450f68b9194ea944ba2', 'q2p9e'),
(26, 26, 'regular', 'Avery', 'Ross', 1, 0, '2022-06-04', null, '9e1e43eaf5125f33b5a06f48f9f19b9e4026c9a75d0c5b45e092ab50e7421273', '6k7ty'),
(27, 27, 'regular', 'Elizabeth', 'Hughes', 1, 0, '2022-06-05', null, '3c066b52572a738b18022d96184d3b2eddb4cdcb000a0c219f615eb0a22f6d7c', '8vzfw'),
(28, 28, 'regular', 'Christopher', 'Butler', 1, 0, '2022-06-06', null, 'b7ab3f39e7be3c6b0c6f9a69506e9e62f14ad6edfb7e57a755c381daefbc5f4c', '9mfd6'),
(29, 29, 'regular', 'Victoria', 'Simmons', 1, 0, '2022-06-07', null, '7d7ed21cc03e10f1da89c5b349bdc0321ffdc94debae4a7fbab40e9063aa5b1d', '15wgj'),
(30, 30, 'regular', 'Andrew', 'Perry', 1, 0, '2022-06-08', null, '786a5b8d268680aa631e46be63a4b155d28d09cd7a97eaa5d6b730f07015b992', 'dt9v7'),
(31, 31, 'regular', 'Abigail', 'Morris', 1, 0, '2022-06-09', null, '6e1c630f488a630eab9b4f9812bbadad254ae619964b79a99469ef6630de56e3', 'f6s4z'),
(32, 32, 'regular', 'David', 'Jenkins', 1, 0, '2022-06-10', null, 'fce8e9c2d1e7a2386f3b82af2e9bc2d914b8303abef03fa8ac0b41d82c4a49f3', 'r1xgn'),
(33, 33, 'regular', 'Sofia', 'Russell', 1, 0, '2022-06-11', null, 'e7ffea788cbaf78e2bdc55e5700c620d868b15a81d3e6fabe12d9476e830d7e4', 'y2fq5'),
(34, 34, 'regular', 'Joseph', 'Ward', 1, 0, '2022-06-12', null, '9dd9beac88f14be18963d416063a5fa84fe1ae11c3e8c9b772f7a20f882e0b9b', 'p7k9t'),
(35, 35, 'regular', 'Scarlett', 'Watson', 1, 0, '2022-06-13', null, '72e7b4f3c68a314f508ae21f8f96717977ee8e0b12a44bc27f25812f282c4eaa', 'fj9zd'),
(36, 36, 'regular', 'Jackson', 'Harrison', 1, 0, '2022-06-14', null, 'ed21d0e87c38b5e40246bce2c3e32be44ebe3a5a86103d9245c0e3d73a47e1fa', 'v7gh2'),
(37, 37, 'regular', 'Lily', 'Evans', 1, 0, '2022-06-15', null, 'e0b9c2dfecb7a6c2d7bb2af1447de5905f3e9b6e7cccb1a32dbaa0f9a4cadd12', 'bzlv6'),
(38, 38, 'regular', 'Michael', 'Cox', 1, 0, '2022-06-16', null, 'd0d8e214108cf202f176a489e2d3897d8e04b8afbed511cb77c03273af449b6d', '7xqhg'),
(39, 39, 'regular', 'Emily', 'Barnes', 1, 0, '2022-06-17', null, '08be3c70b967aa0e7c7d18d40b97a81fae5f6d1fb4f6c487feaa3609b165b1a6', 'p9zqs'),
(40, 40, 'regular', 'Daniel', 'Stewart', 1, 0, '2022-06-18', null, 'dfa88ae27567d322f4475c53bc06454c78a8649e6f134390f4e4474a9cd8d02a', '6dgt9'),
(41, 41, 'regular', 'Victoria', 'Kelly', 1, 0, '2022-06-19', null, '6a2f3bcdd6c78c51daa85a92e774f7ed52af9bce854f07f34f63d9805fd68a53', 'bk6jw'),
(42, 42, 'regular', 'Samuel', 'Turner', 1, 0, '2022-06-20', null, 'eff8b8a7e8ae1ffccf5f14e41582a11c768ea93126349a6603bfb9b5416db698', 'r7l8s'),
(43, 43, 'regular', 'Madison', 'Parker',1, 0, '2022-06-21', null, 'a3946aebd4aae5c16e3d9c57ed4f8b4a33d9558b9b200ce08f11b329f99b3c3f', 'gyxlm'),
(44, 44, 'regular', 'Henry', 'Collins', 1, 0, '2022-06-22', null, 'abd214d20e0eaa7925b39a835f9d50a309c72498d316d41f3fb6c76a4f743ac5', 't3rj5'),
(45, 45, 'regular', 'Aria', 'Edwards', 1, 0, '2022-06-23', null, '2ce6e13e01c7c942db11bbf1c0bc8fcd8335f8ce06636ac0289ab0d9b9d3379b', 'h4pg9'),
(46, 46, 'regular', 'Jack', 'Flores', 1, 0, '2022-06-24', null, '006f90f3dc4334a24e551dbe2a4fd4a8b6a0674226d96fcab7d4118f3c8d01c0', 'n7yts'),
(47, 47, 'regular', 'Luna', 'Price', 1, 0, '2022-06-25', null, '07360f33e090a0983e43c35b98884825c75267b2c8688f56a207fe3e6059de80', 'x5lzq'),
(48, 48, 'regular', 'Carter', 'Simmons', 1, 0, '2022-06-26', null, 'b07e5febea4c2a2c0f1f2e34fc8258f02ed9da51593e4f132e0e682fb4e5a1ad', '7dt1z'),
(49, 49, 'regular', 'Penelope', 'Russell', 1, 0, '2022-06-27', null, '6b01ad0af4b833d0c15d3f0f6d7b31d0d6c96fcad982ce30516e4e3a3b13bc13', '15jrt'),
(50, 50, 'regular', 'Gabriel', 'Gray', 1, 0, '2022-06-28', null, 'b7dabda727c8efb16e64d4d15a468f8ccf406529f5a982c7c39b9e149db1d805', 'g4nky');


-- Inserting communication records
INSERT INTO communication (entity, type, communicationDetails) VALUES
(1, 'email', 'john.doe@gmail.com'),
(2, 'email', 'jane.smith@gmail.com'),
(3, 'email', 'mark.wilson@gmail.com'),
(4, 'email', 'emma.johnson@gmail.com'),
(5, 'email', 'robert.brown@gmail.com'),
(6, 'email', 'sarah.davis@gmail.com'),
(7, 'email', 'michael.miller@gmail.com'),
(8, 'email', 'laura.anderson@gmail.com'),
(9, 'email', 'william.thompson@gmail.com'),
(10, 'email', 'olivia.white@gmail.com'),
(11, 'email', 'james.harris@gmail.com'),
(12, 'email', 'ava.martin@gmail.com'),
(13, 'email', 'charles.lewis@gmail.com'),
(14, 'email', 'sophia.clark@gmail.com'),
(15, 'email', 'daniel.hall@gmail.com'),
(16, 'email', 'chloe.walker@gmail.com'),
(17, 'email', 'matthew.allen@gmail.com'),
(18, 'email', 'amelia.hill@gmail.com'),
(19, 'email', 'joseph.young@gmail.com'),
(20, 'email', 'mia.wright@gmail.com'),
(21, 'email', 'david.green@gmail.com'),
(22, 'email', 'emily.carter@gmail.com'),
(23, 'email', 'jackson.adams@gmail.com'),
(24, 'email', 'abigail.bell@gmail.com'),
(25, 'email', 'benjamin.cook@gmail.com'),
(26, 'email', 'harper.ross@gmail.com'),
(27, 'email', 'andrew.hughes@gmail.com'),
(28, 'email', 'evelyn.butler@gmail.com'),
(29, 'email', 'samuel.turner@gmail.com'),
(30, 'email', 'mia.edwards@gmail.com'),
(31, 'email', 'josephine.murphy@gmail.com'),
(32, 'email', 'henry.collins@gmail.com'),
(33, 'email', 'victoria.hill@gmail.com'),
(34, 'email', 'owen.kelly@gmail.com'),
(35, 'email', 'scarlett.richardson@gmail.com'),
(36, 'email', 'sebastian.bailey@gmail.com'),
(37, 'email', 'grace.nelson@gmail.com'),
(38, 'email', 'ryan.ward@gmail.com'),
(39, 'email', 'zoey.cooper@gmail.com'),
(40, 'email', 'nathan.rogers@gmail.com'),
(41, 'email', 'audrey.brooks@gmail.com'),
(42, 'email', 'lucas.long@gmail.com'),
(43, 'email', 'eleanor.powell@gmail.com'),
(44, 'email', 'gabriel.morris@gmail.com'),
(45, 'email', 'sophie.wood@gmail.com'),
(46, 'email', 'dylan.myers@gmail.com'),
(47, 'email', 'hazel.king@gmail.com'),
(48, 'email', 'brayden.richards@gmail.com'),
(49, 'email', 'lily.howard@gmail.com'),
(50, 'email', 'carter.adams@gmail.com');

-- Inserting business_entity records

INSERT INTO banking_info (entity, iban, bic)
VALUES
  (1, 'BE01 2345 6789 0123', null),
  (2, 'BE98 7654 3210 9876', null),
  (3, 'BE12 3456 7890 1234', null),
  (4, 'BE87 6543 2109 8765', null),
  (5, 'BE45 6789 0123 4567', null),
  (6, 'BE54 3210 9876 5432', null),
  (7, 'BE23 4567 8901 2345', null),
  (8, 'BE98 7654 3210 9876', null),
  (9, 'BE76 5432 1098 7654', null),
  (10, 'BE12 3456 7890 1234', null),
  (11, 'FR12 3456 7890 1234', null),
  (12, 'FR98 7654 3210 9876', null),
  (13, 'FR23 4567 8901 2345', null),
  (14, 'FR87 6543 2109 8765', null),
  (15, 'FR45 6789 0123 4567', null),
  (16, 'FR54 3210 9876 5432', null),
  (17, 'FR23 4567 8901 2345', null),
  (18, 'FR98 7654 3210 9876', null),
  (19, 'FR76 5432 1098 7654', null),
  (20, 'FR12 3456 7890 1234', null),
  (21, 'DE34 5678 9012 3456', null),
  (22, 'DE67 8901 2345 6789', null),
  (23, 'DE45 6789 0123 4567', null),
  (24, 'DE89 0123 4567 8901', null),
  (25, 'DE56 7890 1234 5678', null),
  (26, 'DE65 4321 0987 6543', null),
  (27, 'DE34 5678 9012 3456', null),
  (28, 'DE67 8901 2345 6789', null),
  (29, 'DE45 6789 0123 4567', null),
  (30, 'DE89 0123 4567 8901', null),
  (32, 'NL90 1234 5678 9012', null),
  (33, 'NL67 8901 2345 6789', null),
  (34, 'NL23 4567 8901 2345', null),
  (35, 'NL98 7654 3210 9876',null),
  (36, 'NL45 6789 0123 4567', null),
  (37, 'NL56 7890 1234 5678', null),
  (38, 'NL90 1234 5678 9012', null),
  (39, 'NL67 8901 2345 6789', null),
  (40, 'NL23 4567 8901 2345', null),
  (41, 'LU12 3456 7890 1234', null),
  (42, 'LU98 7654 3210 9876', null),
  (43, 'LU23 4567 8901 2345', null),
  (44, 'LU87 6543 2109 8765', null),
  (45, 'LU45 6789 0123 4567', null),
  (46, 'LU54 3210 9876 5432', null),
  (47, 'LU23 4567 8901 2345', null),
  (48, 'LU98 7654 3210 9876', null),
  (49, 'LU76 5432 1098 7654', null),
  (50, 'LU12 3456 7890 1234', null);

INSERT INTO workflow (id, agent, type, isClosed)
VALUES
  (1, 1, 'client_transaction', 0),
  (2, 2, 'client_transaction', 0),
  (3, 3, 'client_transaction', 0),
  (4, 4, 'client_transaction', 0),
  (5, 5, 'client_transaction', 0),
  (6, 6, 'client_transaction', 0),
  (7, 7, 'client_transaction', 0),
  (8, 8, 'client_transaction', 0),
  (9, 9, 'client_transaction', 0),
  (10, 10, 'client_transaction', 0),
  (11, 11, 'client_transaction', 0),
  (12, 12, 'client_transaction', 0),
  (13, 13, 'client_transaction', 0),
  (14, 14, 'client_transaction', 0),
  (15, 15, 'client_transaction', 0),
  (16, 16, 'client_transaction', 0),
  (17, 17, 'client_transaction', 0),
  (18, 18, 'client_transaction', 0),
  (19, 19, 'client_transaction', 0),
  (20, 20, 'client_transaction', 0),
  (21, 21, 'client_transaction', 0),
  (22, 22, 'client_transaction', 0),
  (23, 23, 'client_transaction', 0),
  (24, 24, 'client_transaction', 0),
  (25, 25, 'client_transaction', 0),
  (26, 26, 'client_transaction', 1),
  (27, 27, 'client_transaction', 1),
  (28, 28, 'client_transaction', 1),
  (29, 29, 'client_transaction', 1),
  (30, 30, 'client_transaction', 1),
  (31, 31, 'client_transaction', 1),
  (32, 32, 'client_transaction', 1),
  (33, 33, 'client_transaction', 1),
  (34, 34, 'client_transaction', 1),
  (35, 35, 'client_transaction', 1),
  (36, 36, 'client_transaction', 1),
  (37, 37, 'client_transaction', 1),
  (38, 38, 'client_transaction', 1),
  (39, 39, 'client_transaction', 1),
  (40, 40, 'client_transaction', 1),
  (41, 41, 'client_transaction', 1),
  (42, 42, 'client_transaction', 1),
  (43, 43, 'client_transaction', 1),
  (44, 44, 'client_transaction', 1),
  (45, 45, 'client_transaction', 1),
  (46, 46, 'client_transaction', 1),
  (47, 47, 'client_transaction', 1),
  (48, 48, 'client_transaction', 1),
  (49, 49, 'client_transaction', 1),
  (50, 50, 'client_transaction', 1);

INSERT INTO document (id, docType, docStatus, workflow, date, exclVATTotal, inclVATTotal, reminderCount, payementDeadline)
VALUES
  (1, 'invoice', 'unpaid', 1, '2021-10-08', 20, 15.8, 0, '2024-10-18'),
  (2, 'invoice', 'unpaid', 2, '2022-01-15', 50, 39.5, 0, '2024-02-15'),
  (3, 'invoice', 'unpaid', 3, '2022-03-22', 100, 79, 0, '2024-04-22'),
  (4, 'invoice', 'unpaid', 4, '2022-05-09', 75, 59.5, 0, '2024-06-09'),
  (5, 'invoice', 'unpaid', 5, '2022-07-18', 30, 23.8, 0, '2024-08-18'),
  (6, 'invoice', 'unpaid', 6, '2022-09-27', 60, 47.4, 0, '2024-10-27'),
  (7, 'invoice', 'unpaid', 7, '2022-11-04', 25, 19.8, 0, '2024-12-04'),
  (8, 'invoice', 'unpaid', 8, '2022-01-12', 80, 63.2, 0, '2024-02-12'),
  (9, 'invoice', 'unpaid', 9, '2022-03-21', 40, 31.6, 0, '2024-04-21'),
  (10, 'invoice', 'unpaid', 10, '2022-05-08', 90, 71.1, 0, '2024-06-08'),
  (11, 'invoice', 'unpaid', 11, '2022-07-17', 55, 43.5, 0, '2024-08-17'),
  (12, 'invoice', 'unpaid', 12, '2022-09-26', 70, 55.3, 0, '2024-10-26'),
  (13, 'invoice', 'unpaid', 13, '2022-11-03', 35, 27.7, 0, '2024-12-03'),
  (14, 'invoice', 'unpaid', 14, '2022-01-11', 65, 51.4, 0, '2024-02-11'),
  (15, 'invoice', 'unpaid', 15, '2022-03-20', 85, 67.2, 0, '2024-04-20'),
  (16, 'invoice', 'unpaid', 16, '2021-03-21', 90, 71.1, 0, '2024-04-21'),
  (17, 'invoice', 'paid', 16, '2022-05-29', 120, 94.8, 0, '2023-06-29'),
  (18, 'invoice', 'paid', 17, '2022-08-07', 150, 118.5, 0, '2023-09-07'),
  (19, 'invoice', 'paid', 18, '2022-10-15', 200, 158, 0, '2023-11-15'),
  (20, 'invoice', 'paid', 19, '2022-12-24', 175, 138.3, 0, '2023-10-24'),
  (21, 'invoice', 'paid', 20, '2022-02-01', 130, 102.9, 0, '2023-12-01'),
  (22, 'invoice', 'paid', 21, '2022-04-11', 90, 71.1, 0, '2023-05-11'),
  (23, 'invoice', 'paid', 22, '2022-06-20', 145, 114.6, 0, '2022-07-20'),
  (24, 'invoice', 'paid', 23, '2022-08-29', 95, 75.1, 0, '2023-09-29'),
  (25, 'invoice', 'paid', 24, '2022-11-07', 160, 126.4, 0, '2023-12-07');

  INSERT INTO transaction_detail (id, document, docType, product, quantity, unitPrice)
    VALUES 
    (1, 1, "invoice", 5, 24, 1.99),
    (2, 1, "invoice", 7, 47, 2.99),
    (3, 1, "invoice", 1, 58, 1.49),
    (4, 2, "invoice", 2, 84, 1.49),
    (5, 2, "invoice", 4, 14, 1.99),
    (6, 2, "invoice", 8, 33, 3.99),
    (7, 3, "invoice", 9, 69, 2.99),
    (8, 3, "invoice", 10, 7, 3.99),
    (9, 3, "invoice", 1, 4, 1.99),
    (10, 4, "invoice", 4, 1, 19.99),
    (11, 4, "invoice", 7, 99, 2.99),
    (12, 4, "invoice", 8, 14, 3.99),
    (13, 5, "invoice", 4, 45, 19.99),
    (14, 5, "invoice", 7, 21, 2.99),
    (15, 5, "invoice", 6, 47, 2.99),
    (16, 6, "invoice", 8, 74, 3.99),
    (17, 6, "invoice", 2, 25, 1.49),
    (18, 6, "invoice", 4, 6, 19.99),
    (19, 7, "invoice", 6, 38, 2.99),
    (20, 7, "invoice", 8, 84, 3.99),
    (21, 7, "invoice", 7, 75, 2.99),
    (22, 8, "invoice", 1, 95, 1.49),
    (23, 8, "invoice", 4, 21, 19.99),
    (24, 8, "invoice", 6, 9, 2.99),
    (25, 9, "invoice", 8, 7, 3.99),
    (26, 9, "invoice", 2, 4, 1.49),
    (27, 9, "invoice", 1, 58, 1.49),
    (28, 10, "invoice", 7, 68, 2.99),
    (29, 10, "invoice", 6, 42, 2.99),
    (30, 10, "invoice", 10, 24, 3.99),
    (31, 11, "invoice", 7, 4, 2.99),
    (32, 11, "invoice", 8, 8, 3.99),
    (33, 11, "invoice", 2, 7, 1.49),
    (34, 12, "invoice", 4, 69, 19.99),
    (35, 12, "invoice", 8, 96, 3.99),
    (36, 12, "invoice", 6, 100, 2.99),
    (37, 13, "invoice", 3, 124, 29.99),
    (38, 13, "invoice", 7, 3, 2.99),
    (39, 13, "invoice", 1, 7, 1.49),
    (40, 14, "invoice", 3, 1, 29.99),
    (41, 14, "invoice", 9, 2, 2.99),
    (42, 14, "invoice", 7, 4, 2.99),
    (43, 15, "invoice", 5, 47, 1.99),
    (44, 15, "invoice", 7, 200, 2.99),
    (45, 15, "invoice", 9, 199, 2.99),
    (46, 16, "invoice", 6, 190, 2.99),
    (47, 16, "invoice", 2, 200, 1.49),
    (48, 16, "invoice", 7, 1, 2.99),
    (49, 17, "invoice", 6, 7, 2.99),
    (50, 17, "invoice", 7, 4, 2.99),
    (51, 17, "invoice", 1, 78, 1.49),
    (52, 18, "invoice", 10, 65, 3.99),
    (53, 18, "invoice", 7, 66, 2.99),
    (54, 18, "invoice", 8, 47, 3.99),
    (55, 19, "invoice", 9, 67, 2.99),
    (56, 19, "invoice", 10, 87, 3.99),
    (57, 19, "invoice", 1, 49, 1.49),
    (58, 20, "invoice", 2, 42, 1.49),
    (59, 20, "invoice", 3, 85, 29.99),
    (60, 20, "invoice", 4, 85, 18.99),
    (61, 21, "invoice", 5, 20, 1.99),
    (62, 21, "invoice", 6, 35, 2.99),
    (63, 21, "invoice", 7, 1, 2.99),
    (64, 22, "invoice", 8, 78, 3.99),
    (65, 22, "invoice", 9, 9, 2.99),
    (66, 22, "invoice", 10, 25, 3.99),
    (67, 23, "invoice", 1, 14, 1.49),
    (68, 23, "invoice", 2, 6, 1.49),
    (69, 23, "invoice", 3, 7, 29.99),
    (70, 24, "invoice", 4, 8, 19.99),
    (71, 24, "invoice", 5, 7, 1.99),
    (72, 24, "invoice", 6, 10, 2.99),
    (73, 25, "invoice", 7, 11, 2.99),
    (74, 25, "invoice", 8, 14, 3.99),
    (75, 25, "invoice", 9, 15, 2.99);