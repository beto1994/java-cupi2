/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_asignacionTareas 
 * Autor: Mario Sánchez - 20/08/2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.asignacionTareas.test;

import uniandes.cupi2.asignacionTareas.mundo.PlanillaTareas;
import junit.framework.TestCase;

/**
 * Es la clase de prueba para la planilla de tareas
 */
public class PlanillaTareasTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la planilla que se usa para hacer las pruebas
     */
    private PlanillaTareas planilla;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Este método inicializa una plantilla sin tiempos
     */
    private void setupEscenario1( )
    {
        try
        {
            planilla = new PlanillaTareas( "./test/data/datosPlanilla.dat" );
        }
        catch( Exception e )
        {
            fail( "No se pudo cargar el archivo: " + e.getMessage( ) );
        }

        try
        {
            planilla.asignarTiempo( "t1", "p1", 3 );
            planilla.asignarTiempo( "t1", "p3", 3 );
            planilla.asignarTiempo( "t1", "p2", 3 );
            planilla.asignarTiempo( "t1", "p4", 7 );

            planilla.asignarTiempo( "t2", "p1", 4 );
            planilla.asignarTiempo( "t3", "p1", 4 );
            planilla.asignarTiempo( "t4", "p1", 4 );
            planilla.asignarTiempo( "t5", "p1", 10 );

            planilla.asignarTiempo( "t6", "p2", 1 );
        }
        catch( Exception e1 )
        {
            fail( "Problemas asignando tiempos a las tareas: " + e1.getMessage( ) );
        }
    }

    /**
     * Verifica el método que da las horas asignadas a una tarea para una persona
     */
    public void testDarHorasPersonaTarea( )
    {
        setupEscenario1( );

        assertEquals( "Las horas asignadas a t1 para p1 están mal", 3, planilla.darHorasPersonaTarea( "t1", "p1" ) );
        assertEquals( "Las horas asignadas a t2 para p2 están mal", 0, planilla.darHorasPersonaTarea( "t2", "p2" ) );
        assertEquals( "Las horas asignadas a t1 para p4 están mal", 7, planilla.darHorasPersonaTarea( "t1", "p4" ) );
    }

    /**
     * Verifica el método asignarTiempo, asignando tiempo a tareas que no tenían y que ya tenían tiempo asignado para una persona
     */
    public void testAsignarTiempo1( )
    {
        setupEscenario1( );

        assertEquals( "Las horas asignadas a t2 para p2 están mal", 0, planilla.darHorasPersonaTarea( "t2", "p2" ) );

        try
        {
            planilla.asignarTiempo( "t2", "p2", 5 );
        }
        catch( Exception e )
        {
            fail( "No debería fallar la asignación de tiempo: " + e.getMessage( ) );
        }

        assertEquals( "Las horas asignadas a t2 para p2 están mal", 5, planilla.darHorasPersonaTarea( "t2", "p2" ) );
    }

    /**
     * Verifica el método asignarTiempo con datos equivocados
     */
    public void testAsignarTiempo2( )
    {
        setupEscenario1( );

        try
        {
            planilla.asignarTiempo( "t100", "p2", 5 );
            fail( "Debería fallar la asignación de tiempo porque la tarea no existe" );
        }
        catch( Exception e )
        {
            assertTrue( "Esta excepción era esperada", true );
        }

        try
        {
            planilla.asignarTiempo( "t1", "p100", 5 );
            fail( "Debería fallar la asignación de tiempo porque la persona no existe" );
        }
        catch( Exception e )
        {
            assertTrue( "Esta excepción era esperada", true );
        }
    }

    /**
     * Verifica el método que da el número de personas en la planilla
     */
    public void testDarNumeroPersonas( )
    {
        setupEscenario1( );
        assertEquals( "El número de personas es incorrecto", 4, planilla.darNumeroPersonas( ) );
    }

    /**
     * Verifica el método para saber el número de personas asignadas a una tarea
     */
    public void testDarNumeroPersonasAsignadas( )
    {
        setupEscenario1( );

        assertEquals( "El número de personas asignadas a la tarea t1 es incorrecto", 4, planilla.darNumeroPersonasAsignadas( "t1" ) );
        assertEquals( "El número de personas asignadas a la tarea t2 es incorrecto", 1, planilla.darNumeroPersonasAsignadas( "t2" ) );
    }

    /**
     * Verifica el método que da el número de tareas en la planilla
     */
    public void testDarNumeroTareas( )
    {
        setupEscenario1( );
        assertEquals( "El número de tareas es incorrecto", 6, planilla.darNumeroTareas( ) );
    }

    /**
     * Verifica el método para saber el número de tareas que tiene asignadas una persona
     */
    public void testDarNumeroTareasAsignadas( )
    {
        setupEscenario1( );

        assertEquals( "El número de tareas asignadas a la persona p1 es incorrecto", 5, planilla.darNumeroTareasAsignadas( "p1" ) );
        assertEquals( "El número de tareas asignadas a la persona p2 es incorrecto", 2, planilla.darNumeroTareasAsignadas( "p2" ) );
        assertEquals( "El número de tareas asignadas a la persona p3 es incorrecto", 1, planilla.darNumeroTareasAsignadas( "p3" ) );
        assertEquals( "El número de tareas asignadas a la persona p4 es incorrecto", 1, planilla.darNumeroTareasAsignadas( "p4" ) );
    }

    /**
     * Verifica el método para saber la persona con más horas asignadas a una tarea
     */
    public void testDarPersonaConMasHorasTarea( )
    {
        setupEscenario1( );

        assertEquals( "La persona con más horas asignadas a la tarea t1 es incorrecta", "p4", planilla.darPersonaConMasHorasTarea( "t1" ) );
        assertEquals( "La persona con más horas asignadas a la tarea t2 es incorrecta", "p1", planilla.darPersonaConMasHorasTarea( "t2" ) );
        assertEquals( "La persona con más horas asignadas a la tarea t6 es incorrecta", "p2", planilla.darPersonaConMasHorasTarea( "t6" ) );
    }

    /**
     * Verifica el método para saber qué porcentaje del total de trabajo representa una tarea
     */
    public void testDarPorcentajeTiempo( )
    {
        setupEscenario1( );

        double porcentaje = planilla.darPorcentajeTiempo( "t1" );

        assertTrue( "El porcentaje está equivocado: " + porcentaje, porcentaje >= 41.0 && porcentaje <= 41.1 );
    }

    /**
     * Verifica el método para saber el promedio de tiempo para cada persona asignada a una tarea
     */
    public void testDarPromedioTiempoPorPersona( )
    {
        setupEscenario1( );

        assertEquals( "El promedio por persona está equivocado", 4, planilla.darPromedioTiempoPorPersona( "t1" ), 0 );
        assertEquals( "El promedio por persona está equivocado", 4, planilla.darPromedioTiempoPorPersona( "t2" ), 0 );
        assertEquals( "El promedio por persona está equivocado", 1, planilla.darPromedioTiempoPorPersona( "t6" ), 0 );
    }

    /**
     * Verifica el método para saber el promedio de tiempo para cada tarea asignada a una persona
     */
    public void testDarPromedioTiempoPorTarea( )
    {
        setupEscenario1( );

        assertEquals( "El promedio por tarea está equivocado", 5, planilla.darPromedioTiempoPorTarea( "p1" ), 0 );
        assertEquals( "El promedio por tarea está equivocado", 2, planilla.darPromedioTiempoPorTarea( "p2" ), 0 );
        assertEquals( "El promedio por tarea está equivocado", 3, planilla.darPromedioTiempoPorTarea( "p3" ), 0 );
    }

    /**
     * Verifica el método para saber cual es la tarea con más horas asignadas de las que tiene una persona
     */
    public void testDarTareaConMasHoras( )
    {
        setupEscenario1( );

        assertEquals( "La tarea con más horas está equivocada", "t5", planilla.darTareaConMasHoras( "p1" ) );
        assertEquals( "La tarea con más horas está equivocada", "t1", planilla.darTareaConMasHoras( "p2" ) );
    }

    /**
     * Verifica el método para saber cual es el total de horas asignadas a una persona
     */
    public void testDarTotalHorasPersona( )
    {
        setupEscenario1( );

        assertEquals( "El total de horas está equivocado", 25, planilla.darTotalHorasPersona( "p1" ) );
        assertEquals( "El total de horas está equivocado", 4, planilla.darTotalHorasPersona( "p2" ) );
        assertEquals( "El total de horas está equivocado", 3, planilla.darTotalHorasPersona( "p3" ) );
    }

    /**
     * Verifica el método para saber cual es el total de horas asignadas a una tarea
     */
    public void testDarTotalHorasTarea( )
    {
        setupEscenario1( );

        assertEquals( "El total de horas está equivocado", 16, planilla.darTotalHorasTarea( "t1" ) );
        assertEquals( "El total de horas está equivocado", 4, planilla.darTotalHorasTarea( "t2" ) );
        assertEquals( "El total de horas está equivocado", 1, planilla.darTotalHorasTarea( "t6" ) );
    }

    /**
     * Verifica el método para saber cual es la persona con más horas
     */
    public void testEsPersonaConMasHoras( )
    {
        setupEscenario1( );

        assertTrue( "p1 es la persona con más horas asignadas", planilla.esPersonaConMasHoras( "p1" ) );
        assertFalse( "p2 no es la persona con más horas asignadas", planilla.esPersonaConMasHoras( "p2" ) );
        assertFalse( "p3 no es la persona con más horas asignadas", planilla.esPersonaConMasHoras( "p3" ) );
        assertFalse( "p4 no es la persona con más horas asignadas", planilla.esPersonaConMasHoras( "p4" ) );
    }
}