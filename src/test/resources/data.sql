INSERT INTO address(id, street, number, city, zip_code) VALUES
(10, 'Polna', 10, 'Warszawa', '00-001'),
(11, 'Koszykowa', 12, 'Warszawa', '01-011'),
(12, 'Maslana', 3, 'Warszawa', '03-542'),
(13, 'Rzymowskiego', 27, 'Warszawa', '00-001');

INSERT INTO type (id, type, price) VALUES
(10, 'A', '149.99'),
(11, 'B', '199.99'),
(12, 'C', '249.99');

INSERT INTO car (id, make, model, reg_number, door_number, gearbox, capacity, year, id_type) VALUES
(10, 'Opel', 'Astra', 'AGD1233', 5, 'A', 5, 2010, 10),
(11, 'Ford', 'Fiesta', 'BGD1269', 3, 'M', 5, 2015, 10),
(12, 'Mazda', 'Mx6', 'WAW1233', 3, 'M', 5, 2016, 11);

INSERT INTO user (id, name, surname, id_address, mail, phone) VALUES
(10, 'Jan', 'Kowalski', 13, 'j@malpa.pl', '555123443');

INSERT INTO rental (id, id_user, id_car, rental_date, planned_date) VALUES
(10, 10, 10, '2018-11-01', '2018-11-06');