#!/bin/bash
# Compile script for Bus Reservation System
# Make sure to update the path to mysql-connector-j jar file

echo "Compiling Bus Reservation System..."
echo ""

# Check if MySQL connector jar exists
if [ ! -f "lib/mysql-connector-j-8.0.33.jar" ]; then
    echo "ERROR: MySQL Connector JAR not found!"
    echo "Please download mysql-connector-j-8.0.33.jar and place it in the lib/ directory"
    echo "Download from: https://dev.mysql.com/downloads/connector/j/"
    echo ""
    exit 1
fi

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile Java files
javac -cp ".:lib/mysql-connector-j-8.0.33.jar" -d bin src/*.java

if [ $? -eq 0 ]; then
    echo ""
    echo "========================================"
    echo "Compilation successful!"
    echo "========================================"
    echo ""
    echo "To run the application, execute: ./run.sh"
    echo ""
else
    echo ""
    echo "========================================"
    echo "Compilation failed!"
    echo "========================================"
    echo "Please check the error messages above."
    echo ""
    exit 1
fi
