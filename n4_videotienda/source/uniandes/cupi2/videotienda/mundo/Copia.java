/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: Copia.java,v 1.1 2013/05/28 15:13:33 ojfabras Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_videotienda
 * Autor: Oscar Fabra - 28 de Mayo 2013
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.videotienda.mundo;

/**
 * Esta clase representa una copia de una película de la videotienda y
 * puede estar disponible o prestada
 */ 

public class Copia 
{
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	/**
	 * Título de la película
	 */
	private String tituloPelicula;
	
	/**
	 * Código consecutivo de la copia, empieza en 1.
	 */
	private int codigo;
	
	
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	
    /**
     * Crea una copia de una película con el título y el código dado. <br>
     * <b>post: </b> Se crea la copia de la película.
     * @param laPelicula Título de la película. laPelicula != null.
     * @param elCodigo Codigo consecutivo de la copia de esta película. elCodigo != null.
     */
	
	public Copia(String laPelicula, int elCodigo)
	{
		this.tituloPelicula = laPelicula;
		this.codigo = elCodigo;		
	}
	
	
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	
    /**
     * Retorna el código de la copia.
     * @return código de la copia de la película.
     */
	public int darCodigo()
	{
		return this.codigo;
	}
	
    /**
     * Retorna el título de la película copiada.
     * @return título de la película.
     */
	public String darTituloPelicula()
	{
		return this.tituloPelicula;
	}
	
	/**
	 * Indica si la Copia es igual a otra comparando sus atributos.
	 * @param otra Copia a comparar.
	 * @return boolean true si son iguales, false en caso contrario.
	 */
	public boolean esIgualA(Copia otra)
	{
		if(this.codigo == otra.codigo && this.tituloPelicula.equalsIgnoreCase(tituloPelicula))
		{
			return true;
		}
		
		return false;
	}
	
	

}
