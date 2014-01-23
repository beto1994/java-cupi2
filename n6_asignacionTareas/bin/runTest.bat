@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n
REM Licenciado bajo el esquema Academic Free License version 2.1
REM
REM Proyecto Cupi2
REM Ejercicio: Asignaci�n de Tareas
REM Autor: Mario S�nchez - 22-Ago-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM/

REM ---------------------------------------------------------
REM Ejecuci�n de la prueba
REM ---------------------------------------------------------

cd..

java -classpath ./lib/asignacionTareas.jar;./test/lib/asignacionTareasTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.asignacionTareas.test.PlanillaTareasTest

cd bin

