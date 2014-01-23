/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Proyecto Cupi2
 * Ejercicio: n4_cine
 * Autor: Pablo Barvo - 13-Sep-2005, Oscar Fabra - 02-Jun-2013
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cine.mundo;

import java.util.*;

/**
 * Clase que representa una sala de cine
 */
public class Cine
{
    //-------------------------------------------------------------------------
    // Constantes
    //-------------------------------------------------------------------------

    /**
     * Número de filas en general
     */
    public static final int FILAS_GENERAL = 8;

    /**
     * Número de filas en preferencial. Comienza en la siguiente en donde 
     * termina general
     */
    public static final int FILAS_PREFERENCIAL = 3;

    /**
     * Número de sillas por fila
     */
    public static final int SILLAS_POR_FILA = 20;

    //-------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------

    /**
     * Arreglo de sillas del teatro
     */
    private Silla[] sillas;

    /**
     * Vector de reservas pendientes en la sala
     */
    private ArrayList<Reserva> reservas;

    /**
     * Vector de tarjetas manejadas por la sala
     */
    private ArrayList<Tarjeta> tarjetas;

    /**
     * Total de dinero recaudado
     */
    private int totalDinero;

    //-------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------

    /**
     * Constructor de la sala de cine. <br>
     * <b>post: </b> Se crea un nuevo cine, con todas las sillas disponibles, 
     * sin tarjetas, y sin reservas.
     */
    public Cine( )
    {
        totalDinero = 0;
        sillas = new Silla[ ( FILAS_GENERAL + FILAS_PREFERENCIAL ) * 
                            SILLAS_POR_FILA];
        reservas = new ArrayList<Reserva>( );
        tarjetas = new ArrayList<Tarjeta>( );

        crearSillas( );
    }

    //-------------------------------------------------------------------------
    // Métodos
    //-------------------------------------------------------------------------

    /**
     * Devuelve el total de dinero recaudado. <br>
     * <b>post: </b> resultado = ({boletas vendidas} + {tarjetas vendidas}).
     * @return Total de dinero recaudado.
     */
    public int darTotalDinero( )
    {
        return totalDinero;
    }

    /**
     * Crea una nueva tarjeta en el cine. <br>
     * <b>pre: </b> No existe una tarjeta emitida para esa cédula. <br>
     * <b>post: </b> Se crea la tarjeta con la carga inicial.
     * @param cedula Cédula del cliente.
     * @throws Exception El cliente ya tiene una tarjeta.
     */
    public void crearTarjeta( int cedula ) throws Exception
    {
        //Verifica que el cliente no tiene tarjeta
        if( existeTarjeta( cedula ) )
        {
            throw new Exception( "El usuario con cédula '" + cedula + 
            		"' ya tiene tarjeta." );
        }

        //Crea la tarjeta para el cliente
        Tarjeta tarjeta = new Tarjeta( cedula );
        tarjetas.add( tarjeta );
        totalDinero += Tarjeta.CARGA_INICIAL;
    }

    /**
     * Recarga una tarjeta con el monto de una recarga. <br>
     * <b>pre: </b> Existe una tarjeta emitida para esa cédula. <br>
     * <b>post: </b> Saldo Tarjeta = Saldo Tarjeta + Recarga.
     * @param cedula Cédula del cliente.
     * @throws Exception El cliente no tiene tarjeta.
     */
    public void recargarTarjeta( int cedula ) throws Exception
    {
        Tarjeta tarjeta = darTarjeta( cedula );
        tarjeta.recargar( );
        totalDinero += Tarjeta.RECARGA;
    }

    /**
     * Devuelve el saldo actual de la tarjeta de un cliente. <br>
     * <b>pre: </b> Existe una tarjeta emitida para esa cédula. <br>
     * <b>post: </b> Devuelve el saldo. No se modifica el mundo.
     * @param cedula Cédula del cliente.
     * @return Saldo disponible en la tarjeta.
     * @throws Exception Excepción lanzada cuando no existe una tarjeta con 
     * la cédula especificada.
     */
    public int darSaldoTarjeta( int cedula ) throws Exception
    {
        Tarjeta tarjeta = darTarjeta( cedula );
        return tarjeta.darCantidadDisponible( );
    }

    /**
     * Busca si una reserva ya fue guardada en el sistema <br>
     * <b>pre: </b> reserva != null. <br>
     * @param reserva Reserva a verificar
     * @return True si ya está guardada, False si no
     */
    public boolean estaGuardada( Reserva reserva )
    {
        return reservas.contains( reserva );
    }

    /**
     * Guarda una reserva en el sistema <br>
     * <b>pre: </b> reserva != null.
     * @param cedula Cédula del cliente
     * @param reserva Reserva a guardar
     * @throws Exception
     */
    public void guardarReserva( int cedula, Reserva reserva ) throws Exception
    {
        //Valida que el cliente tenga una tarjeta
        if( !existeTarjeta( cedula ) )
        {
            throw new Exception( "El cliente no tiene una tarjeta. No puede " +
            		"guardar la reserva." );
        }
        reserva.cambiarCedula( cedula );
        reservas.add( reserva );
    }

    /**
     * Cancela la reserva y la remueve de la sala de cine
     * @param reserva Reserva a eliminar
     */
    public void cancelarReserva( Reserva reserva )
    {
        reserva.cancelar( );
        reservas.remove( reserva );
    }

    /**
     * Busca una reserva en la sala de cine <br/>
     * <b>pre: </b> Existe una reserva asociada a la cédula especificada <br />
     * <b>post: </b> Devuelve la reserva asociada
     * @param cedula Cédula del cliente
     * @return Reserva del cliente
     * @throws Exception El cliente no tiene una reserva en la sala de cine.
     */
    public Reserva darReserva( int cedula ) throws Exception
    {
        for( int i = 0; i < reservas.size( ); i++ )
        {
            Reserva reserva = ( Reserva )reservas.get( i );
            if( reserva.darCedula( ) == cedula )
            {
                return reserva;
            }
        }

        //Si no la encuentra lanza una excepción
        throw new Exception( "El usuario con cédula '" + cedula + 
        		"' no tiene una reserva registrada." );
    }

    /**
     * Paga una reserva con una tarjeta TARCINE <br/>
     * <b>pre: </b> Existe una Tarjeta asociada a la cédula especificada && 
     * reserva != null <br />
     * <b>post: </b> Todas las sillas quedan vendidas && Saldo Tarjeta -= 
     * (Reserva.Total - 10%)
     * @param reserva Reserva a pagar
     * @param cedula Cédula del dueño de la tarjeta
     * @throws Exception Error lanzado si el cliente no tiene tarjeta, no tiene
     * saldo suficiente o la reserva ya fue pagada
     * @throws Exception Si la reserva no tiene saldo a pagar.
     */
    public void pagarReservaTarjeta( Reserva reserva, int cedula ) 
    		throws Exception
    {
        if( reserva.estaPagada( ) )
        {
            throw new Exception( "La reserva ya fue pagada." );
        }
        if( reserva.darSaldoReserva( ) == 0 )
        {
            throw new Exception( "La reserva no tiene saldo a pagar." );
        }
        //Calcula el total con descuento
        int total = ( int ) ( reserva.darSaldoReserva( ) * 
        		( 1 - Tarjeta.DESCUENTO ) );
        Tarjeta tarjeta = darTarjeta( cedula );
        tarjeta.restarDinero( total );
        reserva.volverPagada( );

        //Borra la reserva si existe en las reservas
        reservas.remove( reserva );
    }

    /**
     * Paga una reserva en efectivo <br/><b>pre: </b> reserva != null <br />
     * <b>post: </b> Todas las sillas quedan vendidas && 
     * totalDinero += Reserva.Total;
     * @param reserva Reserva a pagar
     * @throws Exception La reserva ya fue pagada.
     * @throws Exception Si la reserva no tiene saldo a pagar.
     */
    public void pagarReservaEfectivo( Reserva reserva ) throws Exception
    {
        if( reserva.estaPagada( ) )
        {
            throw new Exception( "La reserva ya fue pagada." );
        }
        if( reserva.darSaldoReserva( ) == 0 )
        {
            throw new Exception( "La reserva no tiene saldo a pagar." );
        }
        int total = reserva.darSaldoReserva( );
        totalDinero += total;
        reserva.volverPagada( );

        //Borra la reserva si existe en las reservas
        reservas.remove( reserva );
    }

    /**
     * Devuelve las filas disponibles para el tipo de fila especificado<br/>
     * <b>pre: </b> tipoSilla = Silla.GENERAL || tipoSilla = Silla.PREFERENCIAL
     * <br />
     * <b>post: </b> Devuelve todas las filas del tipo de silla especificada
     * @param tipoSilla
     * @return Arreglo de letras con las letras de las filas
     */
    public char[] darFilas( String tipoSilla )
    {
        char[] filas;
        if( tipoSilla.equals( Silla.GENERAL ) )
        {
            filas = new char[FILAS_GENERAL];
            char letraActual = 'A';
            for( int i = 0; i < FILAS_GENERAL; i++ )
            {
                filas[ i ] = letraActual;
                letraActual = darSiguienteLetra( letraActual );
            }
            return filas;
        }
        else if( tipoSilla.equals( Silla.PREFERENCIAL ) )
        {
            filas = new char[FILAS_PREFERENCIAL];
            char letraActual = 'A' + FILAS_GENERAL;
            for( int i = 0; i < FILAS_PREFERENCIAL; i++ )
            {
                filas[ i ] = letraActual;
                letraActual = darSiguienteLetra( letraActual );
            }
            return filas;
        }
        else
        {
            return new char[0];
        }
    }

    /**
     * Devuelve todas las sillas del teatro
     * @return Todas las sillas del teatro
     */
    public Silla[] darSillas( )
    {
        return sillas;
    }

    /**
     * Devuelve las sillas disponibles para la fila especificada <br>
     * <b>pre: </b> fila existe en el teatro <br>
     * <b>post: </b> Devuelve las sillas disponibles en la fila especificada
     * @param fila Fila
     * @return Sillas disponibles
     * @throws Exception La fila no existe en la sala de cine
     */
    public ArrayList<Silla> darSillasDisponibles( char fila ) throws Exception
    {
        if( darFactorLetra( fila ) < 0 || darFactorLetra( fila ) > 
        	( ( FILAS_GENERAL + FILAS_PREFERENCIAL ) * SILLAS_POR_FILA ) - 1 )
        {
            throw new Exception( "La fila no existe en la sala de cine" );
        }
        int sillaInicial = darFactorLetra( fila ) * SILLAS_POR_FILA;
        int sillaFinal = sillaInicial + SILLAS_POR_FILA;
        ArrayList<Silla> respuesta = new ArrayList<Silla>( );
        for( int i = sillaInicial; i < sillaFinal; i++ )
        {
            if( sillas[ i ].estaDisponible( ) )
            {
                respuesta.add( sillas[ i ] );
            }
        }
        return respuesta;
    }

    /**
     * Devuelve la silla ubicada en la fila y numero especificado <br>
     * <b>pre: </b> fila existe en el teatro, 0 < numero < SILLAS_POR_FILA.<br>
     * <b>post: </b> Devuelve la silla de la posición especificada.
     * @param fila Fila de la silla.
     * @param numero Numero de la silla en la fila.
     * @return Silla ubicada en la posición especificada.
     * @throws Exception No existe la silla en la sala.
     */
    public Silla darSilla( char fila, int numero ) throws Exception
    {
        if( numero < 0 || numero > SILLAS_POR_FILA )
        {
            throw new Exception( "No existe esa silla en la sala" );
        }
        int posicion = ( darFactorLetra( fila ) * SILLAS_POR_FILA ) + numero - 1;
        if( posicion < 0 || posicion >= sillas.length )
        {
            throw new Exception( "No existe esa silla en la sala" );
        }
        return sillas[ posicion ];
    }

    /**
     * Inicializa la sala de cine creando las sillas. <br>
     * <b>pre: </b> sillas[] tiene tamaño ( FILAS_GENERAL + FILAS_PREFERENCIAL )
     *  * SILLAS_POR_FILA. <br>
     * <b>post: </b> Sillas inicializadas con los valores.
     */
    private void crearSillas( )
    {
        char filaActual = 'A';
        int numeroActual = 1;
        //Crea las sillas de general
        for( int i = 0; i < ( FILAS_GENERAL * SILLAS_POR_FILA ); i++ )
        {
            sillas[ i ] = new Silla( filaActual, numeroActual, Silla.GENERAL );
            if( numeroActual == SILLAS_POR_FILA )
            {
                filaActual = darSiguienteLetra( filaActual );
                numeroActual = 1;
            }
            else
            {
                numeroActual++;
            }
        }

        //Crea las sillas de preferencial
        for( int i = 0; i < ( FILAS_PREFERENCIAL * SILLAS_POR_FILA ); i++ )
        {
            sillas[ i + ( FILAS_GENERAL * SILLAS_POR_FILA ) ] = 
            		new Silla( filaActual, numeroActual, Silla.PREFERENCIAL );
            
            if( numeroActual == SILLAS_POR_FILA )
            {
                filaActual = darSiguienteLetra( filaActual );
                numeroActual = 1;
            }
            else
            {
                numeroActual++;
            }
        }
    }

    /**
     * Dada una letra, devuelve la siguiente según el código ASCII.
     * @param letra Letra inicial.
     * @return Letra siguiente.
     */
    private char darSiguienteLetra( char letra )
    {
        return ( char ) ( letra + 1 );
    }

    /**
     * Devuelve el factor de la letra siendo 'A' = 0, 'B' = 1, ...
     * @param letra Letra a buscar.
     * @return Factor de la letra.
     */
    private int darFactorLetra( char letra )
    {
        return letra - 'A';
    }

    /**
     * Busca una tarjeta dado su número de cédula.
     * @param cedula Cédula del usuario.
     * @return Tarjeta del usuario.
     * @throws Exception Excepción lanzada cuando no existe una tarjeta con la 
     * cédula especificada.
     */
    private Tarjeta darTarjeta( int cedula ) throws Exception
    {
        //Busca la tarjeta en la lista de tarjetas
        for( int i = 0; i < tarjetas.size( ); i++ )
        {
            Tarjeta tarjeta = ( Tarjeta )tarjetas.get( i );
            if( tarjeta.darCedula( ) == cedula )
            {
                return tarjeta;
            }
        }

        //Si no la encuentra lanza una excepción
        throw new Exception( "El usuario con cédula '" + cedula + 
        		"' no tiene tarjeta registrada." );
    }

    /**
     * Busca si una tarjeta existe, dado su número de cédula
     * @param cedula Cédula del usuario
     * @return True si existe, False si no.
     */
    private boolean existeTarjeta( int cedula )
    {
        //Busca la tarjeta en la lista de tarjetas
        for( int i = 0; i < tarjetas.size( ); i++ )
        {
            Tarjeta tarjeta = ( Tarjeta )tarjetas.get( i );
            if( tarjeta.darCedula( ) == cedula )
            {
                return true;
            }
        }
        return false;
    }

    //-------------------------------------------------------------------------
    // Puntos de Extensión
    //-------------------------------------------------------------------------

    /**
     * Devuelve una lista con todas las reservas hechas por un cliente. <br>
     * @param cedula La cedula del cliente. cedula != null.
     * @return String Lista de Reservas del cliente.
     */
    public String mostrarReservasCliente( int cedula )
    {
    	ArrayList<Reserva> reservasCliente = darReservasCliente( cedula );
    	return mostrarReservasCliente( reservasCliente );
    	
    }
    
    /**
     * Devuelve una lista con las reservas del cliente con la cédula dada.<br>
     * @param cedula La cédula del cliente. cedula != null.
     * @return ArrayList Lista con las reservas del cliente.
     */
    public ArrayList<Reserva> darReservasCliente( int cedula )
    {
    	ArrayList<Reserva> reservasCliente = new ArrayList<Reserva>();

    	for( int i=0; i<this.reservas.size(); i++ )
    	{
    		Reserva reserva = (Reserva)this.reservas.get(i);

    		if(reserva.darCedula()==cedula)
    		{
    			reservasCliente.add(reserva);
    		}
    	}
    	
    	return reservasCliente;
    }
    
    /**
     * Devuelve una cadena con las reservas hechas por un cliente. <br>
     * @param reservasCliente Lista con las reservas del cliente. 
     * reservasCliente != null.
     * @return String Mensaje con las reservas realizadas por el cliente.
     */
    private String mostrarReservasCliente( ArrayList<Reserva> reservasCliente )
    {
    	String respuesta = "";
    	if(reservasCliente.size()>0)
    	{
        	respuesta = "Las reservas del cliente con cédula "+
        			reservasCliente.get(0).darCedula()+" son: \n";

        	// Muestra cada reserva del cliente
        	for (int i = 0, j=0 ; i<reservasCliente.size(); i++ )
        	{
        		Reserva reserva = reservasCliente.get(i);
        		ArrayList<Silla> sillas = reserva.darSillas();
        		respuesta+="Reserva "+(i+1)+": ";
        		
        		// Muestra las sillas de la reserva
        		for(j=0; j<sillas.size(); j++ )
        		{
        			Silla silla = sillas.get(j);
        			respuesta+=Character.toString(silla.darFila());
        			respuesta+=(silla.darNumero()<10)?"0":"";
        			respuesta+=Integer.toString(silla.darNumero());
        			respuesta+=" ";
        		}
        		
        		respuesta+="\n";
        	}
    	}
    	else
    	{
    		respuesta = "No existen reservas del cliente con la cédula dada.";
    	}
    	
    	return respuesta;
    }
    

    /**
     * Indica si el cliente podría pagar todas sus reservas.<br>
     * <b>post: </b> Si hay saldo suficiente responde que sí, en caso contrario
     * indica cuánto dinero le haría falta. <br>
     * @param cedula La Cedula del cliente. cedula != null.
     * @return String Respuesta con el saldo disponible del cliente con la 
     * cedula dada.
     * @throws Exception Si no existe una tarjeta con la cédula dada.
     */
    public String mostrarCapacidadCompra( int cedula ) throws Exception
    {
    	String respuesta = "";
    	int saldoCliente = this.darSaldoTarjeta(cedula);    	
    	ArrayList<Reserva> reservasCliente = darReservasCliente(cedula);
    	
    	// Calcula la suma de saldos de las reservas del cliente
    	int sumaSaldos = 0;
    	for( int i=0; i<reservasCliente.size(); i++ )
    	{
    		Reserva reserva = reservasCliente.get(i);
    		sumaSaldos+=reserva.darSaldoReserva();
    	}
    	
    	// Construye la cadena del mensaje
    	respuesta+="Para el cliente con cédula: "+cedula+"\n";
    	respuesta+="El saldo requerido para pagar sus reservas es: "+
    	sumaSaldos +"\n";
    	respuesta += "El saldo de su tarjeta es: " + saldoCliente + "\n";
    	respuesta += (saldoCliente >= sumaSaldos)?
    			"El saldo es SUFICIENTE para pagar las reservas.":
    				"El saldo es INSUFICIENTE. Harían falta " +
    					(sumaSaldos - saldoCliente)+" para cubrirlas.";
    	
        return respuesta;
    }
}