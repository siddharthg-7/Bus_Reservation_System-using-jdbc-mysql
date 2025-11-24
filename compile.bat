@echo off
REM Compile script for Bus Reservation System
REM Make sure to update the path to mysql-connector-j jar file

echo Compiling Bus Reservation System...
echo.

REM Check if MySQL connector jar exists
if not exist "lib\mysql-connector-j-8.0.33.jar" (
    echo ERROR: MySQL Connector JAR not found!
    echo Please download mysql-connector-j-8.0.33.jar and place it in the lib\ directory
    echo Download from: https://dev.mysql.com/downloads/connector/j/
    echo.
    pause
    exit /b 1
)

REM Compile Java files
javac -cp ".;lib\mysql-connector-j-8.0.33.jar" -d bin src\*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo Compilation successful!
    echo ========================================
    echo.
    echo To run the application, execute: run.bat
    echo.
) else (
    echo.
    echo ========================================
    echo Compilation failed!
    echo ========================================
    echo Please check the error messages above.
    echo.
)

pause
