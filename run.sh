#!/bin/bash
# Run script for Bus Reservation System

echo "Starting Bus Reservation System..."
echo ""

# Check if compiled classes exist
if [ ! -f "bin/BusReservationSystem.class" ]; then
    echo "ERROR: Compiled classes not found!"
    echo "Please run ./compile.sh first to compile the application."
    echo ""
    exit 1
fi

# Check if MySQL connector jar exists
if [ ! -f "lib/mysql-connector-j-8.0.33.jar" ]; then
    echo "ERROR: MySQL Connector JAR not found!"
    echo "Please download mysql-connector-j-8.0.33.jar and place it in the lib/ directory"
    echo ""
    exit 1
fi

# Run the application
java -cp ".:lib/mysql-connector-j-8.0.33.jar:bin" BusReservationSystem

if [ $? -ne 0 ]; then
    echo ""
    echo "========================================"
    echo "Application terminated with errors!"
    echo "========================================"
    echo ""
fi
