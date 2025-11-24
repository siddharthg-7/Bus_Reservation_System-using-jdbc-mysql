@echo off
REM Run script for Bus Reservation System

echo Starting Bus Reservation System...
echo.

REM Check if compiled classes exist
if not exist "bin\BusReservationSystem.class" (
    echo ERROR: Compiled classes not found!
    echo Please run compile.bat first to compile the application.
    echo.
    pause
    exit /b 1
)

REM Check if MySQL connector jar exists
if not exist "lib\mysql-connector-j-8.0.33.jar" (
    echo ERROR: MySQL Connector JAR not found!
    echo Please download mysql-connector-j-8.0.33.jar and place it in the lib\ directory
    echo.
    pause
    exit /b 1
)

REM Run the application
java -cp ".;lib\mysql-connector-j-8.0.33.jar;bin" BusReservationSystem

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ========================================
    echo Application terminated with errors!
    echo ========================================
    echo.
    pause
)
