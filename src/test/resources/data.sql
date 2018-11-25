INSERT INTO address(id, street, number, city, zip_code) VALUES
(10, 'Polna', 10, 'Warszawa', '00-001'),
(11, 'Koszykowa', 12, 'Warszawa', '01-011'),
(12, 'Maslana', 3, 'Warszawa', '03-542'),
(13, 'Rzymowskiego', 27, 'Warszawa', '00-001');

INSERT INTO type (id, type, price) VALUES
(10, 'A', '149.99'),
(11, 'B', '199.99'),
(12, 'C', '249.99');

INSERT INTO rental_status (id, status) VALUES
(10, 'returned'),
(11, 'reserved'),
(12, 'rented'),
(13, 'cancelled');

INSERT INTO location (id, street, number, city, zip_code) VALUES
(10, 'Jana Brzechwy', 14, 'Warszawa', '00-001'),
(11, 'Krucza', 3, 'Białystok', '15-001'),
(12, 'Wrocławska', 22, 'Poznań', '61-883');

INSERT INTO car (id, make, model, reg_number, door_number, gearbox, capacity, year, id_type, available) VALUES
(10, 'Opel', 'Astra', 'AGD1233', 5, 'A', 5, 2010, 10, true),
(11, 'Ford', 'Fiesta', 'BGD1269', 3, 'M', 5, 2015, 10, true),
(12, 'Mazda', 'Mx6', 'WAW1233', 3, 'M', 5, 2016, 11, true),
(13, 'Subaru', 'Impreza', 'ASL2233', 5, 'M', 4, 2018, 12, true),
(14, 'Seat', 'Ateca', 'DL24321', 5, 'M', 5, 2018, 12, false);

INSERT INTO user (id, name, surname, id_address, mail, phone, login, password, role) VALUES
(10, 'Jan', 'Kowalski', 13, 'j@malpa.pl', '555123443', 'janek', 'bomba', 'ROLE_ADMIN'),
(11, 'Dawid', 'Nowak', 12, 'd@nowak.pl', '534763749', 'dawidek', 'tromba', 'ROLE_USER');

INSERT INTO rental (id, id_user, id_car, rental_date, return_date, rental_place, return_place, id_status) VALUES
(10, 10, 10, '2018-11-01', '2018-11-06', 10, 10, 10),
(11, 10, 11, '2018-10-01', '2018-10-06', 10, 11, 10),
(12, 11, 12, '2018-11-08', '2018-11-09', 10, 12, 10),
(13, 10, 12, '2018-11-10', '2018-11-11', 12, 10, 10),
(14, 10, 12, '2018-11-23', '2018-11-24', 12, 10, 11),
(15, 10, 10, '2018-11-21', '2018-11-22', 10, 10, 13),
(16, 10, 13, '2018-11-21', '2018-11-22', 10, 11, 12),
(17, 10, 14, '2018-11-15', '2018-11-22', 11, 11, 10),
(18, 10, 10, '2018-11-28', '2018-11-29', 12, 10, 13);
