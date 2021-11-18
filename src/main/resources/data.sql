INSERT INTO users(name, password, email, phone, address, cpf, login) VALUES('Luis', '$2a$10$xjtKOut0QcEIoUkYSajYI.hG3fbiMZbLiAJFtnJXaEpkFJvnIa3le', 'teste@teste.com', '(54)99170-6646', 'Rua Eleuterio Roncada, 256, Apto24', '038.519.000-06', 'testelogin');

INSERT INTO restaurants(name, cnpj, login, password, email, address, phone, food_type) VALUES('Restaurant01', 'testCNPJ01', 'testLogin01', 'testPassword01', 'testEmail01', 'testAddress01', 'testPhone01', 'sushi');
INSERT INTO restaurants(name, cnpj, login, password, email, address, phone, food_type) VALUES('restaurant02', 'testCNPJ02', 'testLogin02', 'testPassword02', 'testEmail02', 'testAddress02', 'testPhone02', 'drogas');
INSERT INTO restaurants(name, cnpj, login, password, email, address) VALUES('Italo Brasiliano1', '8888888888888', 'italoLogin', '$2a$10$QfhwrOYRjnckW9vcER1Iae4BubR/YDyysjVrihBCvc0X1TzJyMDfu', 'italo@develfood.com', 'rua teste, 123');
INSERT INTO restaurants(name, cnpj, login, password, email, address, phone) VALUES('Italo Brasiliano2', '8888888888888', 'italoLogin', '$2a$10$QfhwrOYRjnckW9vcER1Iae4BubR/YDyysjVrihBCvc0X1TzJyMDfu', 'italo2@develfood.com', 'rua teste, 123', '999999999999');
INSERT INTO restaurants(name, cnpj, login, password, email, address, phone) VALUES('Italo Brasiliano3', '8888888888888', 'italoLogin', '$2a$10$QfhwrOYRjnckW9vcER1Iae4BubR/YDyysjVrihBCvc0X1TzJyMDfu', 'italo3@develfood.com', 'rua teste, 123', '999999999999');

INSERT INTO plates(restaurant_id,category, name, description, price) VALUES(1, 'SWEET', 'Torta de Morango Moreno', 'Torta de chocolate com recheio de creme de morango', '45.00');
INSERT INTO plates(restaurant_id,category, name, description, price) VALUES(1, 'SAVORY', 'Pizza Marguerita GG', 'Pizza Clássica de 16 Fatias com Queijo, Tomate e Manjericão DELICINHA', '70.00');
INSERT INTO plates(restaurant_id,category, name, description, price) VALUES(2, 'SAVORY', 'Pizza Filé c/ palha', '16 Fatias com Queijo, Tomate e borracha', '50.00');
INSERT INTO plates(restaurant_id,category, name, description, price) VALUES(3, 'SAVORY', 'Pizza Queijin Gorgonzola', 'Pizza que tu vai amar e te trancar, DELICINHA', '60.00');


INSERT INTO requests(obs) VALUES('que isso meu padrinho');

