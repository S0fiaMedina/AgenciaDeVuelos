INSERT INTO country (name) VALUES
('United States'),
('United Kingdom'),
('Germany'),
('France'),
('Spain');


INSERT INTO city (name, id_country) VALUES
('New York', 1), -- New York, United States
('London', 2),   -- London, United Kingdom
('Berlin', 3),   -- Berlin, Germany
('Paris', 4),    -- Paris, France
('Madrid', 5);   -- Madrid, Spain

INSERT INTO airport (id, name, id_city) VALUES
('JFK', 'John F. Kennedy International Airport', 1),  -- JFK Airport in New York
('LHR', 'Heathrow Airport', 2),                      -- Heathrow Airport in London
('TXL', 'Berlin Tegel Airport', 3),                   -- Tegel Airport in Berlin
('CDG', 'Charles de Gaulle Airport', 4),              -- Charles de Gaulle Airport in Paris
('MAD', 'Adolfo Suárez Madrid–Barajas Airport', 5);   -- Barajas Airport in Madrid


INSERT INTO gate (gate_number, id_airport) VALUES
('Gate 1A', 'JFK'),
('Gate 2B', 'LHR'),
('Gate 3C', 'TXL'),
('Gate 4D', 'CDG'),
('Gate 5E', 'MAD');




INSERT INTO airline (name) VALUES
('American Airlines'),
('British Airways'),
('Lufthansa'),
('Air France'),
('Iberia');

INSERT INTO tripulation_roles (name) VALUES
('Pilot'),
('Co-Pilot'),
('Flight Attendant'),
('Engineer'),
('Navigator');


INSERT INTO employee (id, name, id_rol, ingress_date, id_airline, id_airport) VALUES
('EMP001', 'John Doe', 3, '2023-01-15', 1, 'JFK'),
('EMP002', 'Jane Smith', 1, '2022-12-01', 2, 'LHR'),
('EMP003', 'Michael Brown', 4, '2023-02-20', 3, 'TXL'),
('EMP004', 'Emily Davis', 3, '2023-03-10', 1, 'MAD'),
('EMP005', 'David Wilson', 2, '2023-04-05', 2, 'CDG');

INSERT INTO status (name) VALUES
('Active'),
('Inactive'),
('Under Maintenance'),
('In Repair'),
('Out of Service');

INSERT INTO manufacturer (name) VALUES
('Boeing'),
('Airbus'),
('Embraer'),
('Bombardier'),
('Lockheed Martin');


INSERT INTO model (name, manufacturer_id) VALUES
('737', 1),   -- Boeing 737
('747', 1),   -- Boeing 747
('A320', 2),  -- Airbus A320
('A380', 2),  -- Airbus A380
('E190', 3);  -- Embraer E190

INSERT INTO plane (plates, capacity, fabrication_date, id_status, id_model, id_airline) VALUES
('ABC123', 180, '2020-01-15', 1, 1, 1),   -- Plates: ABC123, Boeing 737, American Airlines
('DEF456', 220, '2019-07-20', 2, 2, 2),   -- Plates: DEF456, Boeing 747, British Airways
('GHI789', 160, '2021-03-10', 1, 3, 3),   -- Plates: GHI789, Airbus A320, Lufthansa
('JKL012', 300, '2018-05-05', 3, 4, 4),   -- Plates: JKL012, Airbus A380, Air France
('MNO345', 100, '2022-02-28', 1, 5, 5);   -- Plates: MNO345, Embraer E190, Iberia


INSERT INTO revision (revision_date, plane_plates, description) VALUES
('2023-01-20', 'ABC123', 'Routine maintenance check'),
('2023-02-15', 'DEF456', 'Annual inspection'),
('2023-03-10', 'GHI789', 'Repair of engine components'),
('2023-04-05', 'JKL012', 'Emergency equipment review'),
('2023-05-01', 'MNO345', 'Structural integrity assessment');

INSERT INTO rev_employee (id_employee, id_revision) VALUES
('EMP001', 1),
('EMP002', 2),
('EMP003', 3),
('EMP004', 4),
('EMP005', 5);

INSERT INTO document_type (name) VALUES
('Passport'),
('Driver License'),
('ID Card'),
('Visa');

INSERT INTO flight_fare (description, details, value) VALUES
('Economy', 'Standard seating with meals', 250.00),
('Business', 'Business class with extra legroom and premium service', 600.00),
('First Class', 'Luxury seating with gourmet meals and exclusive amenities', 1200.00);

INSERT INTO trip (tripe_date, price_tripe, departure_airport_id, arrival_airport_id) VALUES
('2023-06-15', 300.00, 'JFK', 'LHR'),
('2023-07-10', 400.00, 'LHR', 'CDG'),
('2023-08-05', 350.00, 'TXL', 'MAD'),
('2023-09-01', 280.00, 'CDG', 'JFK'),
('2023-10-20', 500.00, 'MAD', 'TXL');

INSERT INTO customer (name, age, document_number, id_document_type) VALUES
('Alice Johnson', 25, 12345678, 1),   -- Passport
('Bob Smith', 30, 87654321, 2),       -- Driver License
('Charlie Brown', 28, 98765432, 3),   -- ID Card
('Diana Williams', 35, 23456789, 1),  -- Passport
('Eva Taylor', 40, 34567890, 2);      -- Driver License


INSERT INTO trip_booking (booking_date, id_trip) VALUES
('2023-05-20', 1),
('2023-06-12', 2),
('2023-07-30', 3),
('2023-08-25', 4),
('2023-09-15', 5);


INSERT INTO trip_booking_details (id_trip_booking, id_customer, id_fare, seat_number) VALUES
(1, 1, 1, 1),  -- Booking 1: Customer 1, Economy fare
(2, 2, 2, 10),  -- Booking 1: Customer 2, Business fare
(3, 3, 1, null),  -- Booking 2: Customer 3, Economy fare
(4, 4, 3, null),  -- Booking 3: Customer 4, First Class fare
(5, 5, 2, null);  -- Booking 4: Customer 5, Business fare


INSERT INTO payment_form (description) VALUES
('Credit Card'),
('Debit Card'),
('PayPal'),
('Bank Transfer');

INSERT INTO payment (id_customer, id_payment_form, id_trip_booking) VALUES
(1, 1, 1),  -- Customer 1 paying for Booking 1 with Credit Card
(2, 2, 2),  -- Customer 2 paying for Booking 2 with Debit Card
(3, 3, 3),  -- Customer 3 paying for Booking 3 with PayPal
(4, 4, 4),  -- Customer 4 paying for Booking 4 with Bank Transfer
(5, 1, 5);  -- Customer 5 paying for Booking 5 with Credit Card

INSERT INTO flight_connection (connection_number, id_trip, plane_plates, id_airport) VALUES
('CON1', 1, 'ABC123', 'JFK'),
('CON2', 2, 'DEF456', 'CDG'),
('CON3', 3, 'GHI789', 'MAD'),
('CON4', 4, 'JKL012', 'JFK'),
('CON5', 5, 'MNO345', 'TXL');


INSERT INTO trip_crew (id_employees, id_trip) VALUES
('EMP001', 1),  -- Employee 1 (Pilot) on Connection 1
('EMP002', 2),  -- Employee 2 (Co-Pilot) on Connection 2
('EMP003', 3),  -- Employee 3 (Flight Attendant) on Connection 3
('EMP004', 4),  -- Employee 4 (Engineer) on Connection 4
('EMP005', 5);  -- Employee 5 (Navigator) on Connection 5


