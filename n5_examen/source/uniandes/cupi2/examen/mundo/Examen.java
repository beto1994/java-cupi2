/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Examen.java,v 1.2 2005/12/21 15:31:24 k-marcos Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_examen
 * Autor: Pablo Barvo - 19-Dec-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.examen.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Representa el examen completo
 */
public class Examen
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Preguntas del examen. Contiene objetos de tipo Pregunta.
     */
    private ArrayList<Pregunta> preguntas;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea un nuevo examen cargando la información del archivo
     * @throws Exception Error al cargar el archivo porque no existe o está mal construido
     */
    public Examen( ) throws Exception
    {
        preguntas = new ArrayList<Pregunta>( );
        
        //Carga la información
        Properties persistencia = new Properties( );
        FileInputStream fis = new FileInputStream( new File( "data/examen.data" ) );
        persistencia.load( fis );
        fis.close( );
        //
        //Lee la información
        int cantidadPreguntas = Integer.parseInt( persistencia.getProperty( "examen.cantidadPreguntas" ) );
        for( int i = 1; i <= cantidadPreguntas; i++ )
        {
            Pregunta pregunta = new Pregunta( i, persistencia );
            preguntas.add( pregunta );
        }
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Devuelve la cantidad de preguntas del examen
     * @return Cantidad de preguntas del examen
     */
    public int darCantidadPreguntas( )
    {
        return preguntas.size( );
    }

    /**
     * Devuelve la pregunta en el numero especificado.
     * @param numero Numero de la pregunta. 0 <= numero < darCantidadPreguntas( )
     * @return Pregunta
     */
    public Pregunta darPregunta( int numero )
    {
        return ( Pregunta )preguntas.get( numero );
    }
    
    /**
     * Guarda la pregunta dada en la lista de preguntas del examen
     * <b>Pre: </b> pregunta != null.
     * <b>Post: </b> Se reemplazó la pregunta correspondiente en la lista del Examen.
     * @param numero Número de la pregunta. 0 <= numero <= darCantidadPreguntas().
     * @param pregunta Pregunta que se va a guardar en memoria.
     */
    public void guardarPregunta( int numero, Pregunta pregunta )
    {
    	this.preguntas.set(numero, pregunta);
    }

    /**
     * Devuelve el puntaje del examen sobre 100 <br>
     * <b>pos: </b> 0 <= resultado <= 100
     * @return Puntaje del examen sobre 100
     */
    public int darPuntaje( )
    {
        int cuenta = 0;
        for( int i = 0; i < preguntas.size(); i++ )
        {
            Pregunta pregunta = ( Pregunta )preguntas.get(i);
            if( pregunta.respuestaCorrecta( ) )
            {
                cuenta++;
            }
        }
        return ( cuenta * 100 ) / preguntas.size( );
    }
    
    /**
     * Reinicia el examen creando una nueva lista de preguntas y
     * llenando la información del archivo de preguntas <br>
     * <b>Post: </b> El ArrayList de Preguntas contiene la información del archivo de preguntas <br>
     * @throws Exception Si no se pudo leer el archivo con las preguntas
     */
    public void reiniciar() throws Exception
    {
    	// Crea una nueva lista de preguntas    	
    	this.preguntas = new ArrayList<Pregunta>();
    	
    	//Carga la información
        Properties persistencia = new Properties( );
        FileInputStream fis = new FileInputStream( new File( "data/examen.data" ) );
        persistencia.load( fis );
        fis.close( );
        
        //Lee la información
        int cantidadPreguntas = Integer.parseInt( persistencia.getProperty( "examen.cantidadPreguntas" ) );
        for( int i = 1; i <= cantidadPreguntas; i++ )
        {
            Pregunta pregunta = new Pregunta( i, persistencia );
            preguntas.add( pregunta );
        }
    }

    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}