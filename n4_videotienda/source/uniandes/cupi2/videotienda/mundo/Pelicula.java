/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: Pelicula.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_videotienda
 * Autor: Katalina Marcos - Diciembre 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.videotienda.mundo;

import java.util.ArrayList;

/**
 * Esta clase representa una pel�cula que se encuentra en la videotienda y
 * de la cual puede haber copias disponibles o prestadas.
 */ 
public class Pelicula
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * T�tulo de la pel�cula
     */
    private String titulo;

    /**
     * Lista de copias disponibles
     */
    private ArrayList<Copia> disponibles;

    /**
     * Lista de copias prestadas
     */
    private ArrayList<Copia> prestadas;

    /**
     * N�mero de la siguiente copia a adicionar
     */
    private int codigoSiguienteCopia;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea una pel�cula de la videotienda con el t�tulo dado. <br>
     * <b>post: </b> La pel�cula se crea sin copias disponibles ni prestadas.
     * @param unTitulo T�tulo de la pel�cula. unTitulo != null.
     */
    public Pelicula( String unTitulo )
    {
    	this.titulo = unTitulo;
    	this.disponibles = new ArrayList<Copia>();
    	this.prestadas = new ArrayList<Copia>();
    	this.codigoSiguienteCopia = 0;
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Adiciona una nueva copia de la pel�cula. <br>
     * <b>post: </b>La lista de pel�culas disponibles tiene una nueva copia.
     * @return c�digo de la copia creada. c�digo >= 1;
     */
    public int agregarCopia( )
    {
    	// Incrementa el c�digo
    	this.codigoSiguienteCopia++;
    	
    	// Crea una copia y la agrega a la lista de disponibles
    	Copia copia = new Copia(this.titulo, this.codigoSiguienteCopia);
    	this.disponibles.add(copia);
    	
    	return this.codigoSiguienteCopia;
    }

    /**
     * Retorna una copia de pel�cula para alquilar si hay disponibles. <br>
     * <b>post: </b> la copia queda en la lista de prestadas.
     * @return Copia que ha sido alquilada o null si no hay disponibles.
     */
    public Copia alquilarCopia( )
    {
    	Copia alquilada = null;
    	int tam = this.disponibles.size();
    	
    	if( tam > 0 ){
    		alquilada = (Copia)disponibles.get(tam - 1);
    		this.prestadas.add(alquilada);
    		disponibles.remove(tam-1);
    		return alquilada;
    	} 
    	else
    	{
    		return null;
    	}
    }

    /**
     * Devuelve una copia de la pel�cula y la coloca como disponible. <br>
     * <b>post: </b> regresa la copia a la lista de disponibles, s�lo si est� prestada.
     * @param codigoCopia C�digo de la copia que se quiere devolver.
     * @throws Exception Si la copia a devolver no est� prestada.
     */

    public void devolverCopia(int codigoCopia)
    {
    	Copia copiaTemp = null;
    	for(int i = 0; i < this.prestadas.size(); i++)
    	{
    		copiaTemp = (Copia)this.prestadas.get(i);
    		
    		if(copiaTemp.darCodigo() == codigoCopia)
    		{
    			this.prestadas.remove(i);
    			this.disponibles.add(copiaTemp);
    			return;
    		}
    		
    	}
    }

    /**
     * Retorna el t�tulo de la pel�cula.
     * @return t�tulo de la pel�cula.
     */
    public String darTitulo( )
    {
        return titulo;
    }

    /**
     * Retorna la cantidad total de copias que existen de la pel�cula en la videotienda
     * @return entero con la cantidad de copias que existen en la tienda
     */
    public int darTotalCopias()
    {
    	return this.codigoSiguienteCopia;
    }

    /**
     * Retorna el n�mero de copias disponibles
     * @return n�mero de copias disponibles
     */
    public int darNumeroDisponibles()
    {
    	return this.disponibles.size();
    }
    
    /**
     * Busca una copia disponible de una pel�cula dado su c�digo.<br>
     * <b>pre: </b> La lista de copias disponibles se encuentra inicializado. <br>
     * <b>post: </b> Se devuelve la copia buscada con el c�digo dado. <br>
     * @param codigo El c�digo de la copia a buscar. codigo > 0.
     * @return copia correspondiente al c�digo.
     * @throws Exception Si no existe una copia con el c�digo buscado.
     */
    public Copia buscarCopia( int codigo ) throws Exception
    {
    	if(codigo <= this.codigoSiguienteCopia)
    	{
    		for( int i=0; i < this.disponibles.size(); i++ )
    		{
    			if(this.disponibles.get(i).darCodigo() == codigo)
    			{
    				return this.disponibles.get(i);
    			}
    		}
    		
    	}
    	else
    	{
    		throw new Exception("No existe una copia con el c�digo buscado.");
    	}
    	
    	return null;
    	
    }
}