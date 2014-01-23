@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n5_examen
REM Autor: Pablo Barvo - 19-Dec-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

REM ---------------------------------------------------------
REM Ejecución de las pruebas
REM ---------------------------------------------------------

cd ..\test
java -classpath ../lib/examen.jar;../test/lib/examenTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.examen.test.ExamenTest
cd ..\bin