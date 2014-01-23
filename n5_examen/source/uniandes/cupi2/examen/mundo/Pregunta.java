/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio:  n5_examen
 * Autor: Pablo Barvo - Dec 19, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.examen.mundo;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Representa una pregunta del examen
 */
public class Pregunta
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Valor de la respuesta seleccionada cuando la pregunta no ha sido respondida (cadena vacía)
     */
    public static final String SIN_RESPONDER = "";

    /**
     * Máximo de respuestas para una pregunta
     */
    public static final int MAX_RESPUESTAS = 4;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Número de la pregunta dentro del examen
     */
    private int numeroPregunta;

    /**
     * Enunciado de la pregunta
     */
    private String texto;

    /**
     * Vector que contiene las posibles respuestas a la pregunta. Almacena objetos de tipo Respuesta.
     */
    private ArrayList<Respuesta> respuestas;

    /**
     * Caracter (letra) correspondiente a la respuesta correcta. Es un String de longitud 1.
     */
    private String respuestaCorrecta;

    /**
     * Caracter (letra) correspondiente a la respuesta seleccionada por el usuario. Es una cadena vacía si no se ha seleccionado ninguna respuesta o un String de longitud 1.
     */
    private String respuestaSeleccionada;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea una nueva pregunta cargando la información de un archivo
     * @param numero Número de la pregunta
     * @param persistencia Objeto de tipo Properties que maneja el archivo con la información del examen
     */
    public Pregunta( int numero, Properties persistencia )
    {
        respuestas = new ArrayList<Respuesta>( );
        respuestaSeleccionada = SIN_RESPONDER;
        numeroPregunta = numero;
        //
        // Lee las información
        texto = persistencia.getProperty( "examen.pregunta" + numeroPregunta + ".texto" );
        int numeroRespuestas = Integer.parseInt( persistencia.getProperty( "examen.pregunta" + numeroPregunta + ".cantidadRespuestas" ) );
        for( int i = 0; i < numeroRespuestas; i++ )
        {
            Respuesta respuesta = new Respuesta( numero, i, persistencia );
            respuestas.add( respuesta );
        }
        respuestaCorrecta = persistencia.getProperty( "examen.pregunta" + numeroPregunta + ".correcta" );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el número de la pregunta en el examen
     * @return Numero de la pregunta en el examen
     */
    public int darNumeroPregunta( )
    {
        return numeroPregunta;
    }

    /**
     * Devuelve las posibles respuestas de la pregunta
     * @return Vector de respuestas posibles
     */
    public ArrayList<Respuesta> darRespuestas( )
    {
        return respuestas;
    }

    /**
     * Devuelve el enunciado de la pregunta
     * @return Texto de la pregunta
     */
    public String darTexto( )
    {
        return texto;
    }

    /**
     * Establece la respuesta seleccionada por el usuario
     * @param letraRespuesta Letra de respuesta seleccionada (String de longitud 1)
     * @throws Exception Excepción generada cuando la opción especificada no es válida
     */
    public void establecerRespuestaSeleccionada( String letraRespuesta ) throws Exception
    {
        //
        // Valida la respuesta
        String respuesta = letraRespuesta.trim( );
        if( respuesta.length( ) > 0 )
        {
            boolean valida = false;
            for( int i = 0; i < respuestas.size( ); i++ )
            {
                Respuesta posibleRespuesta = ( Respuesta )respuestas.get( i );
                if( respuesta.equals( posibleRespuesta.darLetraRespuesta( ) ) )
                {
                    valida = true;
                }
            }
            if( !valida )
            {
                throw new Exception( "La respuesta ingresada no es válida" );
            }
        }
        //
        // Guarda la respuesta
        respuestaSeleccionada = respuesta;
    }

    /**
     * Devuelve la letra de la respuesta seleccionada por el usuario actualmente.
     * @return respuesta seleccionada
     */
    public String darRespuestaSeleccionada( )
    {
        return respuestaSeleccionada;
    }

    /**
     * Evalúa si la respuesta seleccionada es la correcta
     * @return True si es correcta, False si no.
     */
    public boolean respuestaCorrecta( )
    {
        return respuestaCorrecta.equalsIgnoreCase( respuestaSeleccionada );
    }

}
