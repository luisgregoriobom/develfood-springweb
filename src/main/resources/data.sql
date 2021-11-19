INSERT INTO users(name, password, email, phone, address, cpf, login) VALUES('Luis', '$2a$10$xjtKOut0QcEIoUkYSajYI.hG3fbiMZbLiAJFtnJXaEpkFJvnIa3le', 'teste@teste.com', '(54)99170-6646', 'Rua Eleuterio Roncada, 256, Apto24', '038.519.000-06', 'testelogin');

INSERT INTO restaurants(name, cnpj, login, password, email, address, phone) VALUES('Italo Brasiliano', '8888888888888', 'italoLogin', '$2a$10$QfhwrOYRjnckW9vcER1Iae4BubR/YDyysjVrihBCvc0X1TzJyMDfu', 'italo@develfood.com', 'rua teste, 123', '999999999999');

INSERT INTO restaurants(name, cnpj, login, password, email, address, phone) VALUES('Italo Brasiliano', '8888888888888', 'italoLogin', '$2a$10$QfhwrOYRjnckW9vcER1Iae4BubR/YDyysjVrihBCvc0X1TzJyMDfu', 'italo@develfood.com', 'rua teste, 123', '999999999999');

INSERT INTO requests(date_request, status, user_id) VALUES(NOW(),'WAITING_TO_ACCEPT', 1);
INSERT INTO requests(date_request, status, user_id) VALUES(NOW(),'IN_ROUTE', 1);

INSERT INTO plates(category, name, obs, price, restaurant_id) VALUES('SWEET', 'Torta de Morango Moreno', 'Torta de chocolate com recheio de creme de morango', '45.00', '1');
INSERT INTO plates(category, name, obs, price) VALUES('SAVORY', 'Pizza Marguerita GG', 'Pizza Clássica de 16 Fatias com Queijo, Tomate e Manjericão DELICINHA', '70.00');

