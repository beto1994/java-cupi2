@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación
REM Licenciado bajo el esquema Academic Free License version 2.1
REM
REM Proyecto Cupi2
REM Ejercicio: Asignación de Tareas
REM Autor: Mario Sánchez - 22-Ago-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM/

REM ---------------------------------------------------------
REM Ejecución de la prueba
REM ---------------------------------------------------------

cd..

java -classpath ./lib/asignacionTareas.jar;./test/lib/asignacionTareasTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.asignacionTareas.test.PlanillaTareasTest

cd bin

