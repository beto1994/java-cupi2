@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Licenciado bajo el esquema Academic Free License version 2.1
REM
REM Proyecto Cupi2
REM Ejercicio: n4_cine
REM Autor: Pablo Barvo - 13-Sep-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

REM ---------------------------------------------------------
REM Ejecuci�n de las pruebas
REM ---------------------------------------------------------

java -classpath ../lib/cine.jar;../test/lib/cineTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cine.test.CineTest
java -classpath ../lib/cine.jar;../test/lib/cineTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cine.test.ReservaTest
java -classpath ../lib/cine.jar;../test/lib/cineTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cine.test.TarjetaTest