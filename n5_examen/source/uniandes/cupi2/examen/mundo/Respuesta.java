/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Respuesta.java,v 1.2 2005/12/21 15:31:24 k-marcos Exp $ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio:  n5_examen
 * Autor: Pablo Barvo - Dec 19, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.examen.mundo;

import java.util.Properties;

/**
 * Representa una respuesta a una pregunta
 */
public class Respuesta
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Texto de la respuesta
     */
    private String texto;

    /**
     * Caracter (letra) correspondiente a la respuesta dentro de la pregunta. Es un String de longitud 1.
     */
    private String letraRespuesta;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea una nueva respuesta leyendo los valores de un archivo
     * @param elNumeroPregunta N�mero de la pregunta a la cual pertenece la respuesta
     * @param elNumeroRespuesta N�mero de la respuesta en la pregunta
     * @param persistencia Objeto de tipo Properties que maneja el archivo con la informaci�n del examen
     */
    public Respuesta( int elNumeroPregunta, int elNumeroRespuesta, Properties persistencia )
    {
        letraRespuesta = darLetra( elNumeroRespuesta );
        texto = persistencia.getProperty( "examen.pregunta" + elNumeroPregunta + ".respuesta_" + letraRespuesta + ".texto" );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Devuelve la letra de la respuesta en la pregunta
     * @return Letra de la respuesta en la pregunta. Es un caracter (String de longitud 1).
     */
    public String darLetraRespuesta( )
    {
        return letraRespuesta;
    }

    /**
     * Devuelve el texto de la respuesta
     * @return Texto de la respuesta
     */
    public String darTexto( )
    {
        return texto;
    }

    /**
     * Devuelve la letra representada por el numero especificado. 
     * Es un m�todo privado pues s�lo lo utiliza el constructor de la clase para converir el n�mero de la respuesta en un caracter.
     * @param numero N�mero que va a ser representado como una letra. 1 <= numero <= 4.
     * @return letra que representa al n�mero
     */
    private String darLetra( int numero )
    {
        return Character.toString( ( char ) ( 'a' + numero ) );
    }
}
