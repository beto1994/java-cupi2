/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ExamenTest.java,v 1.2 2005/12/21 15:31:24 k-marcos Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_examen
 * Autor: Pablo Barvo - 19-Dec-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.examen.test;

import junit.framework.TestCase;
import uniandes.cupi2.examen.mundo.Examen;
import uniandes.cupi2.examen.mundo.Pregunta;
import uniandes.cupi2.examen.mundo.Respuesta;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Examen estén correctamente implementados
 */
public class ExamenTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Examen examen;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo Examen vacío
     * @throws Exception si ocurre un error al crear el examen
     */
    private void setupEscenario1( ) throws Exception
    {
        examen = new Examen( );
    }

    /**
     * Prueba 1 - Inicialización
     */
    public void testInicializacion( )
    {
        try
        {
            setupEscenario1( );
            assertEquals( "El número de preguntas debe ser 5", 5, examen.darCantidadPreguntas( ) );
            assertNotNull( "La pregunta 1 no debe ser null", examen.darPregunta( 0 ) );
            assertNotNull( "La pregunta 2 no debe ser null", examen.darPregunta( 1 ) );
            assertNotNull( "La pregunta 3 no debe ser null", examen.darPregunta( 2 ) );
            assertNotNull( "La pregunta 4 no debe ser null", examen.darPregunta( 3 ) );
            assertNotNull( "La pregunta 5 no debe ser null", examen.darPregunta( 4 ) );
        }
        catch( Exception e )
        {
            fail( "Error al cargar el examen" );
        }
    }

    /**
     * Prueba 2 - DarPreguntas
     */
    public void testDarPreguntas( )
    {
        try
        {
            setupEscenario1( );
            assertEquals( "La pregunta 1 no tiene el numero correcto", 1, examen.darPregunta( 0 ).darNumeroPregunta( ) );
            assertEquals( "La pregunta 2 no tiene el numero correcto", 2, examen.darPregunta( 1 ).darNumeroPregunta( ) );
            assertEquals( "La pregunta 3 no tiene el numero correcto", 3, examen.darPregunta( 2 ).darNumeroPregunta( ) );
            assertEquals( "La pregunta 4 no tiene el numero correcto", 4, examen.darPregunta( 3 ).darNumeroPregunta( ) );
            assertEquals( "La pregunta 5 no tiene el numero correcto", 5, examen.darPregunta( 4 ).darNumeroPregunta( ) );
        }
        catch( Exception e )
        {
            fail( "Error al cargar el examen" );
        }
    }

    /**
     * Prueba 3 - Verifica la pregunta
     */
    public void testPregunta( )
    {
        try
        {
            setupEscenario1( );
            Pregunta pregunta = examen.darPregunta( 0 );
            assertEquals( "El texto de la pregunta no es correcto", "¿Cuál es el coeficiente mental de Kiko según Don Ramón?", pregunta.darTexto( ) );
            assertEquals( "El numero de respuestas no es correcto", 3, pregunta.darRespuestas( ).size( ) );
            pregunta.establecerRespuestaSeleccionada( "a" );
            assertEquals( "La respuesta seleccionada es inválida", "a", pregunta.darRespuestaSeleccionada( ) );
            assertTrue( "El resultado correcto no es el esperado", pregunta.respuestaCorrecta( ) );
        }
        catch( Exception e )
        {
            fail( "Error al cargar el examen" );
        }
    }

    /**
     * Prueba 3 - Verifica la Respuesta
     */
    public void testRespuesta( )
    {
        try
        {
            setupEscenario1( );
            Pregunta pregunta = examen.darPregunta( 0 );
            Respuesta respuesta = ( Respuesta )pregunta.darRespuestas( ).get( 0 );
            assertEquals( "La letra de la respuesta es inválida", "a", respuesta.darLetraRespuesta( ) );
            assertEquals( "El texto de la respuesta es inválido", "Super Retrasado", respuesta.darTexto( ) );
        }
        catch( Exception e )
        {
            fail( "Error al cargar el examen" );
        }
    }

}