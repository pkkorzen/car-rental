CREATE DATABASE IF NOT EXISTS rental;

USE rental;

DROP TABLE IF EXISTS rental;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS rental_status;
DROP TABLE IF EXISTS location;

    create table address (
       id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
        city varchar(255),
        number integer,
        street varchar(255),
        zip_code varchar(255),
        primary key (id)
    );
	
    create table type (
       id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
        price decimal(19,2),
        type_name varchar(255),
        primary key (id)
    );
	
	create table user_role (
       id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
        role varchar(255),
        primary key (id)
    );
	
	create table rental_status (
       id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
        status varchar(255),
        primary key (id)
    );
		
	create table location (
       id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
        city varchar(255),
        number integer not null,
        street varchar(255),
        zip_code varchar(255),
        primary key (id)
    );
	
	create table car (
       id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
        available boolean not null,
        capacity integer,
        door_number integer,
        gearbox varchar(255),
        make varchar(255),
        model varchar(255),
        reg_number varchar(255),
        year integer,
        id_type bigint UNSIGNED NOT NULL,
        primary key (id),
		FOREIGN KEY (id_type) REFERENCES type(id)
    );

	create table user (
       id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
        login varchar(255),
        mail varchar(255),
        name varchar(255),
        password varchar(255),
        phone varchar(255),
        surname varchar(255),
        id_address bigint UNSIGNED NOT NULL,
        id_role bigint UNSIGNED NOT NULL,
        primary key (id),
		FOREIGN KEY (id_address) REFERENCES address(id),
		FOREIGN KEY (id_role) REFERENCES user_role(id)
    );
	
	create table rental (
		id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
        rental_date date,
        return_date date,
        id_car bigint UNSIGNED NOT NULL,
        rental_place bigint UNSIGNED NOT NULL,
        id_status bigint UNSIGNED NOT NULL,
        return_place bigint UNSIGNED NOT NULL,
        id_user bigint UNSIGNED NOT NULL,
        primary key (id),
		FOREIGN KEY (id_car) REFERENCES car(id) ON DELETE CASCADE,
		FOREIGN KEY (rental_place) REFERENCES location(id) ON DELETE CASCADE,
		FOREIGN KEY (id_status) REFERENCES rental_status(id),
		FOREIGN KEY (return_place) REFERENCES location(id) ON DELETE CASCADE,
		FOREIGN KEY (id_user) REFERENCES user(id) ON DELETE CASCADE
    );
	
INSERT INTO address(street, number, city, zip_code) VALUES
('Maslana', 3, 'Warszawa', '03-542'),
('Rzymowskiego', 27, 'Warszawa', '00-001');

INSERT INTO type (type_name, price) VALUES
('A', '149.99'),
('B', '199.99'),
('C', '249.99');

INSERT INTO user_role (role) VALUES
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO rental_status (status) VALUES
('returned'),
('reserved'),
('rented'),
('cancelled');

INSERT INTO location (street, number, city, zip_code) VALUES
('Jana Brzechwy', 14, 'Warszawa', '00-001'),
('Krucza', 3, 'Białystok', '15-001'),
('Wrocławska', 22, 'Poznań', '61-883');

INSERT INTO car (make, model, reg_number, door_number, gearbox, capacity, year, id_type, available) VALUES
('Opel', 'Astra', 'AGD1233', 5, 'A', 5, 2010, (SELECT id from type WHERE type_name = 'A'), true),
('Fiat', 'Punto', 'BGD1269', 5, 'M', 5, 2015, (SELECT id from type WHERE type_name = 'A'), true),
('Ford', 'Mondeo', 'WAW1233', 5, 'M', 5, 2016, (SELECT id from type WHERE type_name = 'B'), true),
('Ford', 'Mustang', 'ASL2233', 5, 'M', 2, 2018, (SELECT id from type WHERE type_name = 'C'), true),
('Opel', 'Insignia', 'DL24321', 5, 'M', 5, 2018, (SELECT id from type WHERE type_name = 'C'), false),
('Opel', 'Zafira', 'DL24321', 5, 'M', 7, 2018, (SELECT id from type WHERE type_name = 'C'), true),
('Ford', 'Fiesta', 'WAW8764', 5, 'M', 5, 2018, (SELECT id from type WHERE type_name = 'A'), false);

INSERT INTO user (name, surname, id_address, mail, phone, login, password, id_role) VALUES
('Jan', 'Kowalski', (SELECT id from address WHERE street = 'Rzymowskiego'), 'j@malpa.pl', '555123443', 'admin', 'admin', (SELECT id from user_role WHERE role = 'ROLE_ADMIN')),
('Dawid', 'Nowak', (SELECT id from address WHERE street = 'Maslana'), 'd@nowak.pl', '534763749', 'user', 'user', (SELECT id from user_role WHERE role = 'ROLE_USER'));

INSERT INTO rental (id_user, id_car, rental_date, return_date, rental_place, return_place, id_status) VALUES
((SELECT id from user WHERE login = 'janek'), (SELECT id from car WHERE model = 'Astra'), '2018-11-01', '2018-11-06', (SELECT id from location WHERE city = 'Warszawa'), (SELECT id from location WHERE city = 'Warszawa'), (SELECT id from rental_status WHERE status = 'returned')),
((SELECT id from user WHERE login = 'janek'), (SELECT id from car WHERE model = 'Punto'), '2018-10-01', '2018-10-06', (SELECT id from location WHERE city = 'Warszawa'), (SELECT id from location WHERE city = 'Białystok'), (SELECT id from rental_status WHERE status = 'returned')),
((SELECT id from user WHERE login = 'dawidek'), (SELECT id from car WHERE model = 'Mondeo'), '2018-11-08', '2018-11-09', (SELECT id from location WHERE city = 'Warszawa'), (SELECT id from location WHERE city = 'Poznań'), (SELECT id from rental_status WHERE status = 'returned')),
((SELECT id from user WHERE login = 'janek'), (SELECT id from car WHERE model = 'Mondeo'), '2018-11-10', '2018-11-11', (SELECT id from location WHERE city = 'Poznań'), (SELECT id from location WHERE city = 'Warszawa'), (SELECT id from rental_status WHERE status = 'returned')),
((SELECT id from user WHERE login = 'janek'), (SELECT id from car WHERE model = 'Mondeo'), '2018-11-23', '2018-11-24', (SELECT id from location WHERE city = 'Poznań'), (SELECT id from location WHERE city = 'Warszawa'), (SELECT id from rental_status WHERE status = 'reserved')),
((SELECT id from user WHERE login = 'janek'), (SELECT id from car WHERE model = 'Astra'), '2018-11-21', '2018-11-22', (SELECT id from location WHERE city = 'Warszawa'), (SELECT id from location WHERE city = 'Warszawa'), (SELECT id from rental_status WHERE status = 'cancelled')),
((SELECT id from user WHERE login = 'janek'), (SELECT id from car WHERE model = 'Mustang'), '2018-11-21', '2018-11-22', (SELECT id from location WHERE city = 'Warszawa'), (SELECT id from location WHERE city = 'Białystok'), (SELECT id from rental_status WHERE status = 'rented')),
((SELECT id from user WHERE login = 'janek'), (SELECT id from car WHERE model = 'Insignia'), '2018-11-15', '2018-11-22', (SELECT id from location WHERE city = 'Białystok'), (SELECT id from location WHERE city = 'Białystok'), (SELECT id from rental_status WHERE status = 'returned')),
((SELECT id from user WHERE login = 'janek'), (SELECT id from car WHERE model = 'Astra'), '2018-11-28', '2018-11-29', (SELECT id from location WHERE city = 'Poznań'), (SELECT id from location WHERE city = 'Warszawa'), (SELECT id from rental_status WHERE status = 'cancelled')),
((SELECT id from user WHERE login = 'janek'), (SELECT id from car WHERE model = 'Fiesta'), '2018-11-28', '2018-11-29', (SELECT id from location WHERE city = 'Poznań'), (SELECT id from location WHERE city = 'Warszawa'), (SELECT id from rental_status WHERE status = 'cancelled'));