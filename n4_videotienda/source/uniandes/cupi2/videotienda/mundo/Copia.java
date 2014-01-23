/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: Copia.java,v 1.1 2013/05/28 15:13:33 ojfabras Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_videotienda
 * Autor: Oscar Fabra - 28 de Mayo 2013
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.videotienda.mundo;

/**
 * Esta clase representa una copia de una pel�cula de la videotienda y
 * puede estar disponible o prestada
 */ 

public class Copia 
{
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	/**
	 * T�tulo de la pel�cula
	 */
	private String tituloPelicula;
	
	/**
	 * C�digo consecutivo de la copia, empieza en 1.
	 */
	private int codigo;
	
	
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	
    /**
     * Crea una copia de una pel�cula con el t�tulo y el c�digo dado. <br>
     * <b>post: </b> Se crea la copia de la pel�cula.
     * @param laPelicula T�tulo de la pel�cula. laPelicula != null.
     * @param elCodigo Codigo consecutivo de la copia de esta pel�cula. elCodigo != null.
     */
	
	public Copia(String laPelicula, int elCodigo)
	{
		this.tituloPelicula = laPelicula;
		this.codigo = elCodigo;		
	}
	
	
    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
	
    /**
     * Retorna el c�digo de la copia.
     * @return c�digo de la copia de la pel�cula.
     */
	public int darCodigo()
	{
		return this.codigo;
	}
	
    /**
     * Retorna el t�tulo de la pel�cula copiada.
     * @return t�tulo de la pel�cula.
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
