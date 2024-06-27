DROP DATABASE IF EXISTS AGENCIA_VUELOS;
CREATE DATABASE AGENCIA_VUELOS;
USE AGENCIA_VUELOS;


-- -----------------------------------------------------
-- AEROPUERTOS	
-- -----------------------------------------------------

/*
----------------------
pAIS
----------------------
*/


CREATE TABLE country(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL,
	CONSTRAINT countries_pk PRIMARY KEY(id)
) ENGINE = INNODB;


/*
----------------------
ciudades
----------------------
*/

CREATE TABLE city(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL,
	id_country INT NOT NULL,
	CONSTRAINT cities_pk PRIMARY KEY(id),
	CONSTRAINT cities_countries_fk FOREIGN KEY(id_country) REFERENCES country(id) ON DELETE CASCADE
)ENGINE = INNODB;

/*
----------------------
aeropuertos
----------------------
*/

CREATE TABLE airport(
	id VARCHAR(5) NOT NULL,
	name VARCHAR(50) NOT NULL,
	id_city INT NOT NULL,
	CONSTRAINT airports_pk PRIMARY KEY(id),
	CONSTRAINT airports_cities_fk FOREIGN KEY(id_city) REFERENCES city(id) ON DELETE CASCADE
)ENGINE = INNODB;

/*
----------------------
puertas de embarque de aeropuertos
----------------------
*/

CREATE TABLE gate(
	id INT NOT NULL AUTO_INCREMENT,
	gate_number VARCHAR(10) NOT NULL,
	id_airport VARCHAR(5) NOT NULL,
	CONSTRAINT gates_pk PRIMARY KEY(id),
	CONSTRAINT gates_airports_fk FOREIGN KEY(id_airport) REFERENCES airport(id) ON DELETE CASCADE
)ENGINE = INNODB;



-- -----------------------------------------------------
-- EMPLEADOS
-- -----------------------------------------------------

/*
----------------------
AEROLINEA
----------------------
*/

CREATE TABLE airline(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL,
	CONSTRAINT airlines_pk PRIMARY KEY(id)
)ENGINE = INNODB;




/*
----------------------
roles de tripulacion
----------------------
*/

CREATE TABLE tripulation_roles(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(40) NOT NULL,
	CONSTRAINT tripulation_roles_pk PRIMARY KEY(id)
)ENGINE = INNODB;

/*
----------------------
Empleados
----------------------
*/

CREATE TABLE employee(
	id VARCHAR(20) NOT NULL,
	name VARCHAR(40) NOT NULL,
	id_rol INT NOT NULL,
	ingress_date DATE NOT NULL,
	id_airline INT NOT NULL,
	id_airport VARCHAR(5) NOT NULL,
	CONSTRAINT employees_PK PRIMARY KEY(id),
	CONSTRAINT employees_airlines_fk FOREIGN KEY(id_airline) REFERENCES airline(id),
	CONSTRAINT employees_airports_fk FOREIGN KEY(id_airport) REFERENCES airport(id) ON DELETE CASCADE,
	CONSTRAINT employees_roles_fk FOREIGN KEY(id_rol) REFERENCES tripulation_roles(id)
)ENGINE = INNODB;


-- -----------------------------------------------
-- AVIONES
-- -----------------------------------------------


/*
----------------------
manufacturadores de un avion (proovedores)
----------------------
*/
CREATE TABLE 	manufacturer(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(40) NOT NULL,
	CONSTRAINT manufacturers_pk PRIMARY KEY(id)
)ENGINE =INNODB;


/*
----------------------
modelos de un avion 
----------------------
*/
CREATE TABLE model(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL,
	manufacturer_id INT NOT NULL,
	CONSTRAINT models_pk PRIMARY KEY(id),
	CONSTRAINT models_manofacturers_fk FOREIGN KEY(manufacturer_id) REFERENCES manufacturer(id)
)ENGINE =INNODB;


/*
----------------------
estados de un avion
----------------------
*/
CREATE TABLE status(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL,
	CONSTRAINT statuses_pk PRIMARY KEY(id)
)ENGINE =INNODB;

/*
----------------------
Aviones
----------------------
*/
CREATE TABLE plane(
	id INT NOT NULL AUTO_INCREMENT,
	plates VARCHAR(30) NOT NULL,
	capacity INT NOT NULL,
	fabrication_date DATE NOT NULL,
	id_status INT NOT NULL,
	id_model  INT NOT NULL,
	id_airline INT NOT NULL,
	CONSTRAINT planes_pk PRIMARY KEY(id),
	CONSTRAINT planes_plates_key UNIQUE(plates),
	CONSTRAINT planes_statuses_fk FOREIGN KEY(id_status) REFERENCES status(id),
	CONSTRAINT planes_models_fk FOREIGN KEY(id_model) REFERENCES model(id),
	CONSTRAINT planes_airlines_fk FOREIGN KEY(id_airline) REFERENCES airline(id)
)ENGINE =INNODB;

/*
----------------------
revisiones de un avion
----------------------
*/
CREATE TABLE revision(
	id INT NOT NULL AUTO_INCREMENT,
	revision_date DATE NOT NULL,
	plane_plates VARCHAR(30) NOT NULL,
	description TEXT NOT NULL, /*campo agregado*/
	CONSTRAINT revisions_pk PRIMARY KEY(id),
	CONSTRAINT revisions_planes_fk FOREIGN KEY(plane_plates) REFERENCES plane(plates) ON DELETE CASCADE
)ENGINE =INNODB;

/*
----------------------
empleado que se ha encargado de una revision del avion

(tabla intermedia)
----------------------
*/
CREATE TABLE rev_employee(
	id_employee VARCHAR(20) NOT NULL,
	id_revision INT NOT NULL,
	CONSTRAINT rev_employee_pk PRIMARY KEY(id_employee, id_revision),
	CONSTRAINT rev_employee_employees FOREIGN KEY(id_employee) REFERENCES employee(id),
	CONSTRAINT rev_employee_revision FOREIGN KEY(id_revision) REFERENCES revision(id) ON DELETE CASCADE
)ENGINE =INNODB;


/*
----------------------
detalles de la revision
(no se porque esta tabla xd)
----------------------

CREATE TABLE revision_details(
	id VARCHAR(20) NOT NULL,
	description TEXT,
	id_employee INT NOT NULL,
	CONSTRAINT revision_details_pk PRIMARY KEY(id),
	CONSTRAINT revision_details_employees_fk FOREIGN KEY(id_employee) REFERENCES employees(id)
)ENGINE =INNODB;


*/

-- --------------------------------
-- RESERVAS Y VIAJES
-- --------------------------------



/*
----------------------
Tipos de documentos
----------------------
*/
CREATE TABLE document_type(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(40) NOT NULL,
	CONSTRAINT document_types_pk PRIMARY KEY(id)
)ENGINE = INNODB;



/*
----------------------
clientes
----------------------
*/
CREATE TABLE customer(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL,
	age INT NOT NULL,
	document_number INT NOT NULL,
	id_document_type INT NOT NULL, /*campo agregado*/
	CONSTRAINT customers_pk PRIMARY KEY(id),
	CONSTRAINT unique_number UNIQUE(document_number),
	CONSTRAINT customer_document_type_id_fk FOREIGN KEY(id_document_type) REFERENCES document_type(id)/*llave agregada */
)ENGINE = INNODB;

/*
----------------------
tarifas de vuelo para una reserva
----------------------
*/
CREATE TABLE flight_fare(
	id INT NOT NULL AUTO_INCREMENT,
	description VARCHAR(20) NOT NULL,
	details TEXT,
	value DOUBLE(7,3) NOT NULL,
	CONSTRAINT flight_fare_pk PRIMARY KEY(id)
)ENGINE = INNODB;

/*
----------------------
viajes
----------------------
*/
-- esta tabla representa los viajes
CREATE TABLE trip(
	id INT NOT NULL AUTO_INCREMENT,
	tripe_date DATE NOT NULL,
	price_tripe DOUBLE NOT NULL,
	departure_airport_id VARCHAR(5) NOT NULL, /*campo agregado*/
	arrival_airport_id VARCHAR(5) NOT NULL, /*campo agregado*/
	CONSTRAINT trip_departure_fk FOREIGN KEY(departure_airport_id) REFERENCES airport(id) ON DELETE CASCADE, /*llave agregada */
	CONSTRAINT trip_arrival_fk FOREIGN KEY(arrival_airport_id) REFERENCES airport(id) ON DELETE CASCADE, /*llave agregada */
	CONSTRAINT trip_pk PRIMARY KEY(id)
)ENGINE = INNODB;

/*
----------------------
reserva de un vuelo
----------------------
*/
CREATE TABLE trip_booking(
	id INT NOT NULL AUTO_INCREMENT,
	booking_date DATE NOT NULL,
	id_trip INT NOT NULL,
	CONSTRAINT trip_booking_pk PRIMARY KEY(id),
	CONSTRAINT trip_booking_trip_fk FOREIGN KEY(id_trip) REFERENCES trip(id) ON DELETE CASCADE
)ENGINE = INNODB;



/*
----------------------
detalles de reserva de viaje (tabla SEMI-intermedia)
----------------------
esta tabla es la relacion entre clientes y la reserva (n clientes tienen n reservaciones , (como rpor ejemplo, reservaciones grupales))
*/
CREATE TABLE trip_booking_details(
	id INT NOT NULL AUTO_INCREMENT,
	id_trip_booking INT NOT NULL,
	id_customer INT NOT NULL,
	id_fare INT NOT NULL,
	CONSTRAINT trip_booking_details_pk PRIMARY KEY(id),
	CONSTRAINT booking_details_customers_fk FOREIGN KEY(id_customer) REFERENCES customer(id) ON DELETE CASCADE,
	CONSTRAINT booking_details_flight_fares_fk FOREIGN KEY(id_fare) REFERENCES flight_fare(id),
	CONSTRAINT booking_details_booking_fk FOREIGN KEY(id_trip_booking) REFERENCES trip_booking(id) ON DELETE CASCADE
)ENGINE = INNODB;

/*
------------------
	FORMA DE PAGO (tabla agregada)
------------------
*/
CREATE TABLE payment_form(
	id INT NOT NULL AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL,
	CONSTRAINT payment_form_pk PRIMARY KEY(id)
);

/*
------------------
PAGO (tabla agregada)
------------------
*/
CREATE TABLE payment(
	id INT NOT NULL AUTO_INCREMENT,
	id_customer INT NOT NULL,
	id_payment_form INT NOT NULL,
	id_trip_booking INT NOT NULL,
	CONSTRAINT payment_pk PRIMARY KEY(id),
	CONSTRAINT payment_trip_booking_fk FOREIGN KEY(id_trip_booking) REFERENCES trip_booking(id) ON DELETE CASCADE,
	CONSTRAINT payment_customer_fk FOREIGN KEY(id_customer) REFERENCES customer(id) ON DELETE CASCADE,
	CONSTRAINT payment_form_fk FOREIGN KEY(id_payment_form) REFERENCES payment_form(id)
) ENGINE = INNODB;

/*
----------------------
Conexiones
----------------------
*/
-- esta tabla representa las conexiones (o escalas) que hay en un vuelo.
CREATE TABLE flight_connection(
	id INT NOT NULL AUTO_INCREMENT,
	connection_number VARCHAR(10) NOT NULL, 
	id_trip INT NOT NULL,
	plane_plates VARCHAR(30) NOT NULL,
	id_airport VARCHAR(5) NOT NULL,
	CONSTRAINT connections_pk PRIMARY KEY(id),
	CONSTRAINT connections_airports_fk FOREIGN KEY(id_airport) REFERENCES airport(id) ON DELETE CASCADE,
	CONSTRAINT connections_planes_fk FOREIGN KEY(plane_plates) REFERENCES plane(plates) ON DELETE CASCADE,
	CONSTRAINT connections_trips_fk FOREIGN KEY(id_trip) REFERENCES trip(id) ON DELETE CASCADE
)ENGINE = INNODB;	






/*
----------------------
tripulacion de una conexión (TABLA INTERMEDIA)
----------------------
*/

CREATE TABLE trip_crew(
	id_employees VARCHAR(20)  NOT NULL,
	id_trip INT NOT NULL,
	CONSTRAINT tripcrews_pk PRIMARY KEY(id_employees, id_trip),
	CONSTRAINT tripcrews_trips_fk FOREIGN KEY(id_trip) REFERENCES trip(id) ON DELETE CASCADE,
	CONSTRAINT tripcrews_employees_fk FOREIGN KEY(id_employees) REFERENCES employee(id)
)ENGINE = INNODB;


