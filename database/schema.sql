-- Bus Reservation System Database Schema
-- Database: bus_reservation

-- Create database if not exists
CREATE DATABASE IF NOT EXISTS bus_reservation;
USE bus_reservation;

-- Table: bus_info
-- Stores information about available buses
CREATE TABLE IF NOT EXISTS bus_info (
    bus_number VARCHAR(20) PRIMARY KEY,
    bus_name VARCHAR(100) NOT NULL,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    departure_time TIME NOT NULL,
    arrival_time TIME NOT NULL,
    fare DECIMAL(10, 2) NOT NULL,
    total_seats INT NOT NULL
);

-- Table: reservations
-- Stores passenger reservation details
CREATE TABLE IF NOT EXISTS reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    passenger_name VARCHAR(100) NOT NULL,
    bus_number VARCHAR(20) NOT NULL,
    travel_date DATE NOT NULL,
    seats INT NOT NULL,
    FOREIGN KEY (bus_number) REFERENCES bus_info(bus_number)
);

-- Insert sample bus data for testing
INSERT INTO bus_info (bus_number, bus_name, source, destination, departure_time, arrival_time, fare, total_seats) 
VALUES 
    ('BUS001', 'Express Deluxe', 'Mumbai', 'Pune', '06:00:00', '09:30:00', 350.00, 40),
    ('BUS002', 'Volvo AC', 'Delhi', 'Jaipur', '08:00:00', '13:00:00', 800.00, 45),
    ('BUS003', 'Sleeper Coach', 'Bangalore', 'Chennai', '22:00:00', '06:00:00', 950.00, 35),
    ('BUS004', 'Semi Sleeper', 'Hyderabad', 'Vijayawada', '14:00:00', '18:30:00', 450.00, 50),
    ('BUS005', 'Luxury AC', 'Kolkata', 'Bhubaneswar', '07:30:00', '15:00:00', 750.00, 38)
ON DUPLICATE KEY UPDATE bus_number=bus_number;
