/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: Cliente.java,v 1.1 2013/05/30 15:13:33 ojfabras Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_videotienda
 * Autor: Oscar Fabra - 30 de Mayo 2013
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.videotienda.mundo;

import java.util.ArrayList;

/**
 * Esta clase representa un cliente de la videotienda. Cada cliente puede alquilar
 * varias copias de diferentes películas y pagar con su saldo disponible
 * @author Oscar
 *
 */
public class Cliente {
	
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	/**
	 * Cédula del cliente
	 */
	private String cedula;
	
	/**
	 * Nombre del cliente
	 */
	private String nombre;
	
	/**
	 * Dirección de residencia del cliente
	 */
	private String direccion;
	
	/**
	 * Lista de películas alquiladas
	 */
	private ArrayList<Copia> alquiladas;
	
	/**
	 * Saldo disponible para alquilar películas
	 */
	private int saldo;	
	
	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	
	/**
	 * Constructor principal de la clase. <br>
	 * <b>post: </b> El Cliente se crea sin saldo disponible ni películas alquiladas.
     * @param laCedula String Cédula del cliente. laCedula != null.
     * @param elNombre String Nombre del cliente. elNombre != null.
     * @param laDireccion String Dirección de residencia del cliente. laDireccion != null. 
	 */
	public Cliente(String laCedula, String elNombre, String laDireccion )
	{
		this.cedula = laCedula;
		this.nombre = elNombre;
		this.direccion = laDireccion;
		this.alquiladas = new ArrayList<Copia>();
		this.saldo = 0;
	}
	
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	
	/**
	 * Retorna la cedula del cliente. <br>
	 * @return String Cedula del cliente
	 */
	public String darCedula()
	{
		return this.cedula;
	}
	
	/**
	 * Retorna el saldo del cliente. <br>
	 * @return Saldo del cliente
	 */
	public int darSaldo()
	{
		return this.saldo;
	}
	
	/**
	 * Retorna el nombre del cliente. <br>
	 * @return Nombre del cliente
	 */
	public String darNombre()
	{
		return this.nombre;
	}
	
	/**
	 * Retorna la dirección de residencia del cliente. <br>
	 * @return Dirección del cliente
	 */
	public String darDireccion()
	{
		return this.direccion;
	}
	
	/**
	 * Agrega una copia a la lista de películas alquiladas del cliente. <br>
	 * <b>post: </b> La lista de alquiladas contiene la copia.
	 * @param copia La copia de la película que va a alquilar. copia != null.
	 */
	public void alquilarCopia(Copia copia)
	{
		this.alquiladas.add(copia);
	}
	
	/**
	 * Agrega saldo al client.e <br>
	 * <b>post: </b> Se agregó el monto al saldo del cliente.
	 * @param monto Valor a agregar. monto > 0.
	 */
	public void cargarSaldo(int monto)
	{
		this.saldo+= monto;
	}
	
	/**
	 * Substrae del saldo del cliente <br>
	 * <b>post: </b> Se substrajo el monto del saldo del cliente. saldo >= 0.
	 * @param monto Valor a substraer. monto > 0.
	 */
	public void descargarSaldo(int monto)
	{
		this.saldo-= monto;
	}
	
	/**
	 * Indica el número de películas alquiladas que tiene el cliente <br>
	 * @return El número de películas alquiladas por el cliente
	 */
	public int darNumeroAlquiladas()
	{
		return this.alquiladas.size();
	}
	
	/**
	 * Retorna la lista de copias de películas alquiladas por el cliente.
	 * @return La lista de copias alquiladas por el cliente
	 */
	
	public ArrayList<Copia> darAlquiladas()
	{
		return this.alquiladas;
	}
	
	/**
	 * Busca una película alquilada por el cliente.
	 * @param pelicula Nombre de la película. pelicula != null.
	 * @param codigo Código de la película. codigo != null.
	 * @return La copia alquilada si existe, de lo contrario devuelve null.
	 */
	public Copia buscarPeliculaAlquilada(String pelicula, int codigo)
	{
		Copia copia = new Copia(pelicula, codigo);
		
		for(int i = 0; i < this.alquiladas.size(); i++ )
		{
			if(this.alquiladas.get(i).esIgualA(copia))
			{
				return alquiladas.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * Remueve una copia de la lista de alquiladas del cliente <br>
	 * <b>post: </b> La lista de alquiladas ya no contiene la copia indicada.
	 * @param pelicula El nombre de la película. pelicula != null.
	 * @param codigo El código de la copia de la película. codigo != null.
	 * @throws Exception si la pelicula no existe.
	 */
	
	public void devolverCopia( String pelicula, int codigo) throws Exception
	{
		Copia copia = this.buscarPeliculaAlquilada(pelicula, codigo);
		
		if(copia != null )
		{
			// Obtiene el índice de la copia
			int i = this.alquiladas.indexOf(copia);
			
			// Remueve la copia ubicada en el índice dado
			this.alquiladas.remove(i);
		}
		else
		{
			throw new Exception("La película con código "+codigo+" no existe en la Videotienda.");
		}

	}

}
