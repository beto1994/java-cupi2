/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_asignacionTareas 
 * Autor: Mario S�nchez - 20/08/2005 
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Este m�todo inicializa una plantilla sin tiempos
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
     * Verifica el m�todo que da las horas asignadas a una tarea para una persona
     */
    public void testDarHorasPersonaTarea( )
    {
        setupEscenario1( );

        assertEquals( "Las horas asignadas a t1 para p1 est�n mal", 3, planilla.darHorasPersonaTarea( "t1", "p1" ) );
        assertEquals( "Las horas asignadas a t2 para p2 est�n mal", 0, planilla.darHorasPersonaTarea( "t2", "p2" ) );
        assertEquals( "Las horas asignadas a t1 para p4 est�n mal", 7, planilla.darHorasPersonaTarea( "t1", "p4" ) );
    }

    /**
     * Verifica el m�todo asignarTiempo, asignando tiempo a tareas que no ten�an y que ya ten�an tiempo asignado para una persona
     */
    public void testAsignarTiempo1( )
    {
        setupEscenario1( );

        assertEquals( "Las horas asignadas a t2 para p2 est�n mal", 0, planilla.darHorasPersonaTarea( "t2", "p2" ) );

        try
        {
            planilla.asignarTiempo( "t2", "p2", 5 );
        }
        catch( Exception e )
        {
            fail( "No deber�a fallar la asignaci�n de tiempo: " + e.getMessage( ) );
        }

        assertEquals( "Las horas asignadas a t2 para p2 est�n mal", 5, planilla.darHorasPersonaTarea( "t2", "p2" ) );
    }

    /**
     * Verifica el m�todo asignarTiempo con datos equivocados
     */
    public void testAsignarTiempo2( )
    {
        setupEscenario1( );

        try
        {
            planilla.asignarTiempo( "t100", "p2", 5 );
            fail( "Deber�a fallar la asignaci�n de tiempo porque la tarea no existe" );
        }
        catch( Exception e )
        {
            assertTrue( "Esta excepci�n era esperada", true );
        }

        try
        {
            planilla.asignarTiempo( "t1", "p100", 5 );
            fail( "Deber�a fallar la asignaci�n de tiempo porque la persona no existe" );
        }
        catch( Exception e )
        {
            assertTrue( "Esta excepci�n era esperada", true );
        }
    }

    /**
     * Verifica el m�todo que da el n�mero de personas en la planilla
     */
    public void testDarNumeroPersonas( )
    {
        setupEscenario1( );
        assertEquals( "El n�mero de personas es incorrecto", 4, planilla.darNumeroPersonas( ) );
    }

    /**
     * Verifica el m�todo para saber el n�mero de personas asignadas a una tarea
     */
    public void testDarNumeroPersonasAsignadas( )
    {
        setupEscenario1( );

        assertEquals( "El n�mero de personas asignadas a la tarea t1 es incorrecto", 4, planilla.darNumeroPersonasAsignadas( "t1" ) );
        assertEquals( "El n�mero de personas asignadas a la tarea t2 es incorrecto", 1, planilla.darNumeroPersonasAsignadas( "t2" ) );
    }

    /**
     * Verifica el m�todo que da el n�mero de tareas en la planilla
     */
    public void testDarNumeroTareas( )
    {
        setupEscenario1( );
        assertEquals( "El n�mero de tareas es incorrecto", 6, planilla.darNumeroTareas( ) );
    }

    /**
     * Verifica el m�todo para saber el n�mero de tareas que tiene asignadas una persona
     */
    public void testDarNumeroTareasAsignadas( )
    {
        setupEscenario1( );

        assertEquals( "El n�mero de tareas asignadas a la persona p1 es incorrecto", 5, planilla.darNumeroTareasAsignadas( "p1" ) );
        assertEquals( "El n�mero de tareas asignadas a la persona p2 es incorrecto", 2, planilla.darNumeroTareasAsignadas( "p2" ) );
        assertEquals( "El n�mero de tareas asignadas a la persona p3 es incorrecto", 1, planilla.darNumeroTareasAsignadas( "p3" ) );
        assertEquals( "El n�mero de tareas asignadas a la persona p4 es incorrecto", 1, planilla.darNumeroTareasAsignadas( "p4" ) );
    }

    /**
     * Verifica el m�todo para saber la persona con m�s horas asignadas a una tarea
     */
    public void testDarPersonaConMasHorasTarea( )
    {
        setupEscenario1( );

        assertEquals( "La persona con m�s horas asignadas a la tarea t1 es incorrecta", "p4", planilla.darPersonaConMasHorasTarea( "t1" ) );
        assertEquals( "La persona con m�s horas asignadas a la tarea t2 es incorrecta", "p1", planilla.darPersonaConMasHorasTarea( "t2" ) );
        assertEquals( "La persona con m�s horas asignadas a la tarea t6 es incorrecta", "p2", planilla.darPersonaConMasHorasTarea( "t6" ) );
    }

    /**
     * Verifica el m�todo para saber qu� porcentaje del total de trabajo representa una tarea
     */
    public void testDarPorcentajeTiempo( )
    {
        setupEscenario1( );

        double porcentaje = planilla.darPorcentajeTiempo( "t1" );

        assertTrue( "El porcentaje est� equivocado: " + porcentaje, porcentaje >= 41.0 && porcentaje <= 41.1 );
    }

    /**
     * Verifica el m�todo para saber el promedio de tiempo para cada persona asignada a una tarea
     */
    public void testDarPromedioTiempoPorPersona( )
    {
        setupEscenario1( );

        assertEquals( "El promedio por persona est� equivocado", 4, planilla.darPromedioTiempoPorPersona( "t1" ), 0 );
        assertEquals( "El promedio por persona est� equivocado", 4, planilla.darPromedioTiempoPorPersona( "t2" ), 0 );
        assertEquals( "El promedio por persona est� equivocado", 1, planilla.darPromedioTiempoPorPersona( "t6" ), 0 );
    }

    /**
     * Verifica el m�todo para saber el promedio de tiempo para cada tarea asignada a una persona
     */
    public void testDarPromedioTiempoPorTarea( )
    {
        setupEscenario1( );

        assertEquals( "El promedio por tarea est� equivocado", 5, planilla.darPromedioTiempoPorTarea( "p1" ), 0 );
        assertEquals( "El promedio por tarea est� equivocado", 2, planilla.darPromedioTiempoPorTarea( "p2" ), 0 );
        assertEquals( "El promedio por tarea est� equivocado", 3, planilla.darPromedioTiempoPorTarea( "p3" ), 0 );
    }

    /**
     * Verifica el m�todo para saber cual es la tarea con m�s horas asignadas de las que tiene una persona
     */
    public void testDarTareaConMasHoras( )
    {
        setupEscenario1( );

        assertEquals( "La tarea con m�s horas est� equivocada", "t5", planilla.darTareaConMasHoras( "p1" ) );
        assertEquals( "La tarea con m�s horas est� equivocada", "t1", planilla.darTareaConMasHoras( "p2" ) );
    }

    /**
     * Verifica el m�todo para saber cual es el total de horas asignadas a una persona
     */
    public void testDarTotalHorasPersona( )
    {
        setupEscenario1( );

        assertEquals( "El total de horas est� equivocado", 25, planilla.darTotalHorasPersona( "p1" ) );
        assertEquals( "El total de horas est� equivocado", 4, planilla.darTotalHorasPersona( "p2" ) );
        assertEquals( "El total de horas est� equivocado", 3, planilla.darTotalHorasPersona( "p3" ) );
    }

    /**
     * Verifica el m�todo para saber cual es el total de horas asignadas a una tarea
     */
    public void testDarTotalHorasTarea( )
    {
        setupEscenario1( );

        assertEquals( "El total de horas est� equivocado", 16, planilla.darTotalHorasTarea( "t1" ) );
        assertEquals( "El total de horas est� equivocado", 4, planilla.darTotalHorasTarea( "t2" ) );
        assertEquals( "El total de horas est� equivocado", 1, planilla.darTotalHorasTarea( "t6" ) );
    }

    /**
     * Verifica el m�todo para saber cual es la persona con m�s horas
     */
    public void testEsPersonaConMasHoras( )
    {
        setupEscenario1( );

        assertTrue( "p1 es la persona con m�s horas asignadas", planilla.esPersonaConMasHoras( "p1" ) );
        assertFalse( "p2 no es la persona con m�s horas asignadas", planilla.esPersonaConMasHoras( "p2" ) );
        assertFalse( "p3 no es la persona con m�s horas asignadas", planilla.esPersonaConMasHoras( "p3" ) );
        assertFalse( "p4 no es la persona con m�s horas asignadas", planilla.esPersonaConMasHoras( "p4" ) );
    }
}