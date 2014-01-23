/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: VideoTienda.java,v 1.2 2013/05/31 15:13:33 ojfabras Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_videotienda
 * Autor: Oscar Fabra - Mayo 2013
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.videotienda.mundo;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Esta clase representa a la VideoTienda
 */
public class VideoTienda
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Tarifa de alquiler diario
     */
    private int tarifaDiaria;

    /**
     * Clientes
     */
    private ArrayList<Cliente> clientes;

    /**
     * Catálogo de películas
     */
    private ArrayList<Pelicula> catalogo;
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea una videotienda sin películas registradas. <br>
     * @param unaTarifa Tarifa diaria de alquiler. tarifa > 0.
     */
    public VideoTienda( int unaTarifa )
    {
    	this.tarifaDiaria = unaTarifa;
    	this.clientes = new ArrayList<Cliente>();
    	this.catalogo = new ArrayList<Pelicula>();
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Carga en memoria los datos del archivo de películas. <br>
     * <b>post: </b> Se almacenan los datos de las películas del archivo en el catálogo eliminando las películas anteriores. <br>
     * @param archivo Nombre del archivo que contiene la información de las películas.
     * @throws Exception si hay datos inválidos en el archivo o no tiene el formato adecuado.
     */
    public void cargarPeliculas( String archivo ) throws Exception
    {
        String titulo, dato;
        int peliculas, copias;
        Pelicula pel;

        //Limpia los datos iniciales de películas
        catalogo.clear( );

        //Obtiene los datos
        try
        {
            Properties datos = new Properties( );
            FileInputStream input = new FileInputStream( archivo );
            datos.load( input );

            //Obtiene el número de películas
            peliculas = Integer.parseInt( datos.getProperty( "total.peliculas" ) );

            for( int i = 1; i <= peliculas; i++ )
            {
                dato = "pelicula" + i + ".nombre";
                //Carga una película
                titulo = datos.getProperty( dato );
                if( titulo == null )
                {
                    throw new Exception( "Falta definir la propiedad " + dato );
                }

                copias = Integer.parseInt( datos.getProperty( "pelicula" + i + ".copias" ) );
                pel = new Pelicula( titulo );
                for( int j = 1; j <= copias; j++ )
                {
                    pel.agregarCopia( );
                }

                catalogo.add( pel );
            }
        }
        catch( Exception e )
        {
            throw new Exception( "Error al cargar los datos almacenados de películas" );
        }
    }

    /**
     * Afilia un cliente a la videotienda. <br>
     * <b>post: </b> Se crea un nuevo cliente y se agrega a la lista de clientes de la videotienda.
     * @param cedula Cédula del cliente a afiliar. cedula != null.
     * @param nombre Nombre del cliente a afiliar. nombre != null.
     * @param direccion Dirección del cliente a afiliar. direccion != null.
     * @throws Exception Si la cédula del cliente ya está registrada en la videotienda.
     */
    public void afiliarCliente( String cedula, String nombre, String direccion ) throws Exception
    {
    	Cliente cliente = this.buscarCliente(cedula);
    	
    	if(cliente == null)
    	{
    		cliente = new Cliente(cedula, nombre, direccion);
    		this.clientes.add(cliente);
    	}
    	else
    	{
    		throw new Exception("El cliente con cedula "+cedula+" ya existe.");
    	}
    	
    }
    
    /**
     * Busca el cliente dada la cédula. <br>
     * @param cedula Cédula del cliente. cedula != null.
     * @return el cliente correspondiente a la cédula, o null si no hay un cliente con la cédula dada.
     */
    public Cliente buscarCliente( String cedula )
    {
    	for(int i=0; i<this.clientes.size(); i++ )
    	{
    		if(this.clientes.get(i).darCedula().equalsIgnoreCase(cedula) )
    		{
    			return this.clientes.get(i);
    		}
    		
    	}
    	
    	return null;
    }
    
    /**
     * Busca la película dado el título. <br>
     * @param titulo Título de la película. tituo != null.
     * @return la película con ese título, o null si no existe la película.
     */
    public Pelicula buscarPelicula( String titulo )
    {
    	for(int i = 0; i<this.catalogo.size(); i++ )
    	{
    		if(this.catalogo.get(i).darTitulo().equalsIgnoreCase(titulo))
    		{
    			return this.catalogo.get(i);
    		}
    	}
    	
    	return null;
    }



    /**
     * Adiciona el monto dado al saldo disponible del cliente. <br>
     * <b>post: </b> el saldo del cliente identificado con la cédula se incrementó con el monto dado. <br>
     * @param cedula Cédula del cliente. cedula != null.
     * @param monto Cantidad de dinero a adicionar en la cuenta. monto > 0.
     * @throws Exception Si el cliente no existe.
     * @throws Exception Si la recarga de saldo es menor que 0.
     */
    public void cargarSaldoCliente( String cedula, int monto ) throws Exception
    {
    	// Verifico que el monto sea mayor o igual a cero
    	if(monto > 0)
    	{
    		Cliente cliente = this.buscarCliente(cedula);
    		int i = this.clientes.indexOf(cliente);
    		
    		// Verifico que el cliente exista
    		if(cliente!=null)
    		{
    			// Adiciona el monto al cliente
    			cliente.cargarSaldo(monto);
    			
    			// Remueve el objeto cliente anterior y agrega el nuevo
    			this.clientes.remove(i);    			
    			this.clientes.add(i, cliente);
    		}
    		else
    		{
    			throw new Exception("El cliente con cédula "+cedula+" no existe.");
    		}
    	}
    	else
    	{
    		throw new Exception("El monto a recargar no puede ser negativo.");
    	}
    }

    /**
     * Alquila una película a un cliente. <br>
     * <b>post: </b> si hay copias disponibles, alquila una copia de la película, adicionándola a la lista de alquiladas del cliente y de la videotienda.
     * @param titulo Título de la película. titulo != null.
     * @param cedula Cédula del cliente. cedula != null.
     * @return número de copia alquilada.
     * @throws Exception Si la película no existe.
     * @throws Exception Si el cliente no existe.
     * @throws Exception Si no hay copias disponibles.
     * @throws Exception Si el saldo del cliente no es suficiente para el alquiler.
     */
    public int alquilarPelicula( String titulo, String cedula ) throws Exception
    {
    	
    	// Busca la película con el título dado
    	Pelicula pelicula = this.buscarPelicula(titulo);
    	
    	if( pelicula != null )
    	{
        	// Obtiene el índice del objeto película encontrado
    		int i = this.catalogo.indexOf(pelicula);
    		
    		// Busca el cliente con la cédula dada
    		Cliente cliente = this.buscarCliente(cedula);
    		
    		if( cliente != null)
    		{
    			// Obtiene el índice del objeto cliente encontrado
        		int j = this.clientes.indexOf(cliente);
        		
    			// Retorna una copia de la película dada
    			Copia copia = pelicula.alquilarCopia();
    			
    			if( copia != null )
    			{
    				// Verifica que el saldo disponible del cliente sea suficiente
    				if(cliente.darSaldo() >= this.tarifaDiaria)
    				{
    					// Substrae el valor del alquiler al cliente
    					cliente.descargarSaldo(this.tarifaDiaria);
    					
    					// Agrega la copia a la lista de alquiladas del cliente
    					cliente.alquilarCopia(copia);
    					
    					// Remueve el objeto cliente anterior y agrega el nuevo
    					this.clientes.remove(j);
    					this.clientes.add(j, cliente);
    					
    					// Remueve el objeto película anterior y agrega el nuevo
    					this.catalogo.remove(i);
    					this.catalogo.add(i, pelicula);
    					
    					return copia.darCodigo();
    				}
    				else
    				{
    					throw new Exception("El saldo del cliente no es suficiente para el alquiler.");
    				}
    			}
    			else
    			{
    				throw new Exception("No hay copias disponibles de esta película.");
    			}
    		}
    		else
    		{
    			throw new Exception("El cliente con cédula "+cedula+" no existe.");
    		}
    		
    	}
    	else
    	{
    		throw new Exception("La película con título "+titulo+" no existe.");
    	}
    	
    }

    /**
     * Devuelve a la videotienda una copia alquilada por el cliente identificado con la cédula dada. <br>
     * <b>post: </b> Si la copia está alquilada por el cliente, la copia se deja disponible, y el cliente ya no la tiene entre sus prestadas.
     * @param titulo Título de la película. titulo != null.
     * @param numeroCopia Número de copia a devolver.
     * @param cedula Cédula del cliente. cedula != null.
     * @throws Exception Si el cliente no existe.
     * @throws Exception Si el cliente no tiene la copia alquilada.
     */
    public void devolverCopia( String titulo, int numeroCopia, String cedula ) throws Exception
    {
    	Cliente cliente = this.buscarCliente(cedula);
    	
    	if(cliente != null)
    	{
    		// Obtiene el índice del objeto cliente encontrado
    		int i = this.clientes.indexOf(cliente);
    		
    		Pelicula pelicula = this.buscarPelicula(titulo);
    		
    		if(pelicula != null)
    		{
    			// Obtiene el índice del objeto película encontrado
    			int j = this.catalogo.indexOf(pelicula);
    			
    			// Devuelve la copia de la película y la coloca disponible
    			pelicula.devolverCopia(numeroCopia);
    			
    			// Remueve la copia indicada si existe, de lo contrario bota una excepción
       			cliente.devolverCopia(titulo, numeroCopia);
       			
       			// Remueve el objeto cliente anterior y agrega el nuevo
       			this.clientes.remove(i);
       			this.clientes.add(i, cliente);
       			
       			// Remueve el objeto película anterior y agrega el nuevo
       			this.catalogo.remove(j);
       			this.catalogo.add(j, pelicula);
    		}
    		else
    		{
    			throw new Exception("La pelicula no se encuentra en el catálogo.");
    		}
    				    		
    	}
    	else
    	{
    		throw new Exception("El cliente con cédula "+cedula+" no existe.");
    	}

    }
    
    /**
     * Agrega una copia a una película del catálogo. <br>
     * @param String El Título de la película
     * @throws Exception Si la película no existe.
     */
    public void agregarCopiaPelicula( String titulo ) throws Exception
    {
    	Pelicula pelicula = this.buscarPelicula(titulo);
    	
    	if(pelicula != null)
    	{
    		// Obtiene el índice de la película
    		int i = this.catalogo.indexOf(pelicula);
    		
    		// Agrega la copia al objeto película
    		pelicula.agregarCopia();
    		
    		// Remueve el objeto película anterior y agrega el nuevo
    		this.catalogo.remove(i);
    		this.catalogo.add(i, pelicula);
    	}
    	else
    	{
    		throw new Exception("La pelicula con título "+titulo+" no existe en la Videotienda.");
    	}
    	
    }
    
    /**
     * Modifica la tarifa diaria que cobra la videotienda. <br>
     * @param nuevaTarifa la nueva tarifa a colocar.
     */
    public void modificarTarifa(int nuevaTarifa)
    {
    	this.tarifaDiaria = nuevaTarifa;
    }

    /**
     * Retorna la lista de clientes de la Videotienda. <br>
     * @return ArrayLista la lista de clientes registrados.
     */
    public ArrayList<Cliente> darListaClientes()
    {
    	return this.clientes;
    }
    
    /**
     * Retorna el catálogo de peliculas disponibles en la Videotienda. <br>
     * @return La lista de películas disponibles para alquilar. lista != null.
     */
    public ArrayList<Pelicula> darCatalogo()
    {
    	return this.catalogo;
    }
    
    /**
     * Retorna la tarifa diaria de la Videotienda. <br>
     * @return El valor de la tarifa.
     */
    public int darTarifaDiaria()
    {
    	return this.tarifaDiaria;
    }

    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Retorna un string con el listado de películas y el número de copias por película. <br>
     * @return String La cadena con la lista de películas y su número de copias. 
     */
    public String mostrarListadoPeliculas( )
    {
    	String respuesta = "";
    	
    	for(int i=0; i<this.catalogo.size(); i++ )
    	{
    		String linea = "La película ";
    		linea+=this.catalogo.get(i).darTitulo()+" tiene ";
    		linea+=this.catalogo.get(i).darTotalCopias()+" copias";
    		linea+="\n";
    		
    		respuesta+=linea;
    	}
    	
        return respuesta;
    }

    /**
     * Calcula y devuelve el promedio de copias de las películas de la Videotienda. <br>
     * @return String La cadena con el promedio de copias.
     */
    public String calcularPromedioCopias( )
    {
    	double promedio = 0;
    	
    	for(int i=0; i<this.catalogo.size(); i++ )
    	{
    		Pelicula pelicula = this.catalogo.get(i);
    		
    		promedio+=pelicula.darTotalCopias();
    	}
    	
    	promedio = promedio / this.catalogo.size();
    	
        return "El promedio de copias por película es "+String.valueOf(promedio);
    }

}
