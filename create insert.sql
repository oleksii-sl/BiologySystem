CREATE TABLE classification (
  id     number(10) NOT NULL, 
  name   varchar2(40), 
  parent number(10), 
  PRIMARY KEY (id));
  
CREATE TABLE living_entity (
  id           number(10) NOT NULL, 
  name         varchar2(40), 
  name_latin   varchar2(40), 
  lifespan     number(10), 
  avg_weight   number(20, 8), 
  native_range varchar2(255), 
  population   number(10), 
  class        number(10) NOT NULL, 
  PRIMARY KEY (id));
  
ALTER TABLE classification ADD CONSTRAINT FKclassifica720341 FOREIGN KEY (parent) REFERENCES classification (id);
ALTER TABLE living_entity ADD CONSTRAINT FKliving_ent281381 FOREIGN KEY (class) REFERENCES classification (id);
CREATE SEQUENCE seq_classification;
CREATE SEQUENCE seq_living_entity;

INSERT INTO CLASSIFICATION(ID, NAME, PARENT) VALUES
	(seq_classification.nextval, 'Eukaryotes', NULL);
INSERT INTO CLASSIFICATION(ID, NAME, PARENT) VALUES
	(seq_classification.nextval, 'Animals', 1);
INSERT INTO CLASSIFICATION(ID, NAME, PARENT) VALUES
	(seq_classification.nextval, 'Mushrooms', 1);
INSERT INTO CLASSIFICATION(ID, NAME, PARENT) VALUES
	(seq_classification.nextval, 'Plants', 1);
INSERT INTO CLASSIFICATION(ID, NAME, PARENT) VALUES
	(seq_classification.nextval, 'Amphibia', 2);
INSERT INTO CLASSIFICATION(ID, NAME, PARENT) VALUES
	(seq_classification.nextval, 'Birds', 2);
INSERT INTO CLASSIFICATION(ID, NAME, PARENT) VALUES
	(seq_classification.nextval, 'Mammals', 2);

INSERT INTO living_entity 
	VALUES(seq_living_entity.nextval, 'Wildcat', 'Felis silvestris', 
7, 5.5, 'Europe, Asia, North Africa', 1500, 7);
INSERT INTO living_entity 
  VALUES(seq_living_entity.nextval, 'Grey wolf',
  'Canis lupus', 15, 40, 'Eurasian', 3000, 7);
INSERT INTO living_entity 
  VALUES(seq_living_entity.nextval, 'Grizzly', 
'Ursus arctos horribilis', 40, 450, 'Alaska, western Canada', 50000, 7);
INSERT INTO living_entity 
  VALUES(seq_living_entity.nextval, 'Woodchuck', 
'Marmota Blumenbach', 25, 0.5, 'Eurasian, Asia', 8000000, 7);
INSERT INTO living_entity 
  VALUES(seq_living_entity.nextval, 'Chicken', 
'Gallus gallus domesticus', 7, 3.5, 'Eurasian, Asia, America, Africa', 9000000, 6);
INSERT INTO living_entity 
  VALUES(seq_living_entity.nextval, 'Woodpecker', 
'Dendrocopos', 6, 0.75, 'Eurasian, north Africa', 9000, 6);
INSERT INTO living_entity 
  VALUES(seq_living_entity.nextval, 'Bonellis Eagle', 
'Aquila fasciata', 7, 2.1, 'Eurasian, Africa', 1800, 6);
INSERT INTO living_entity 
  VALUES(seq_living_entity.nextval, 'Common toad', 
'Bufo bufo', 4, 0.027, 'Eurasian, north Africa', 50000, 5);
INSERT INTO living_entity 
  VALUES(seq_living_entity.nextval, 'Alpine Salamander', 
'Salamandra atra', 10, 0.0245, 'Alps', 400, 5);
INSERT INTO living_entity 
  VALUES(seq_living_entity.nextval, 'Marbled Newt', 
'Triturus marmoratus', 10, 0.011, 'Alps', 8000, 5);


