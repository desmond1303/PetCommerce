# --- !Ups

INSERT INTO "user"(name, email, hash, salt, is_admin) VALUES('Dino Prašo', 'desmond1303@gmail.com', 'JUZcMpgHSG8kD4Jczpi/1roRe1aD3sndpU2BqEpZLmc=', 'RMiZBLS/BW1qEGGG1cwRrg==', true);

INSERT INTO item(name, description, price, quantity) VALUES ('Item 1', 'A Great Item!', 29.99, 509);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 2', 'A Great Item!', 29.99, 249);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 3', 'A Great Item!', 29.99, 689);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 4', 'A Great Item!', 29.99, 310);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 5', 'A Great Item!', 29.99, 389);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 6', 'A Great Item!', 29.99, 402);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 7', 'A Great Item!', 29.99, 102);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 8', 'A Great Item!', 29.99, 60);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 9', 'A Great Item!', 29.99, 29);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 10', 'A Great Item!', 29.99, 793);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 11', 'A Great Item!', 29.99, 320);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 11', 'A Great Item!', 29.99, 342);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 12', 'A Great Item!', 29.99, 689);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 13', 'A Great Item!', 29.99, 587);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 14', 'A Great Item!', 29.99, 194);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 15', 'A Great Item!', 29.99, 43);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 16', 'A Great Item!', 29.99, 69);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 17', 'A Great Item!', 29.99, 239);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 18', 'A Great Item!', 29.99, 43);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 19', 'A Great Item!', 29.99, 879);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 20', 'A Great Item!', 29.99, 57);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 21', 'A Great Item!', 29.99, 12);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 22', 'A Great Item!', 29.99, 38);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 23', 'A Great Item!', 29.99, 348);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 24', 'A Great Item!', 29.99, 982);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 25', 'A Great Item!', 29.99, 635);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 26', 'A Great Item!', 29.99, 5);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 27', 'A Great Item!', 29.99, 46);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 28', 'A Great Item!', 29.99, 87);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 29', 'A Great Item!', 29.99, 627);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 30', 'A Great Item!', 29.99, 236);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 31', 'A Great Item!', 29.99, 23);
INSERT INTO item(name, description, price, quantity) VALUES ('Item 32', 'A Great Item!', 29.99, 73);

# --- !Downs

TRUNCATE "user" CASCADE;
