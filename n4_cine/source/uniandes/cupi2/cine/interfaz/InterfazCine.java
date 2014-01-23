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

package uniandes.cupi2.cine.interfaz;

import java.awt.*;

import javax.swing.*;

import uniandes.cupi2.cine.mundo.*;

/**
 * Esta es la ventana principal de la aplicación.
 */
@SuppressWarnings("serial")
public class InterfazCine extends JFrame
{
    //-------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------

	/**
     * Clase principal del modelo del mundo
     */
    private Cine cine;

    /**
     * Reserva actual que maneja la interfaz
     */
    private Reserva reservaActual;

    //-------------------------------------------------------------------------
    // Atributos de la interfaz
    //-------------------------------------------------------------------------

    /**
     * Panel con los datos de la reserva
     */
    private PanelReserva panelReserva;

    /**
     * Panel de manejo de tarjetas
     */
    private PanelTarjetas panelTarjetas;

    /**
     * Panel de extensiones
     */
    private PanelOpciones panelOpciones;

    /**
     * Panel de visualización del cine
     */
    private PanelCine panelCine;

    /**
     * Panel con la imagen
     */
    private PanelImagen panelImagen;

    //-------------------------------------------------------------------------
    // Constructores
    //-------------------------------------------------------------------------

    /**
     * Crea la ventana principal de la interfaz
     */
    public InterfazCine( )
    {
        // Crea la clase principal
        cine = new Cine( );
        reservaActual = new Reserva( );

        // Organiza el panel principal
        setLayout( new BorderLayout( ) );
        setTitle( "Manejo Sala de Cine" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        //Panel Tarjetas y Reserva
        panelTarjetas = new PanelTarjetas( this );
        panelReserva = new PanelReserva( this, cine );
        JPanel contIzq = new JPanel( new BorderLayout( ) );
        contIzq.add( panelReserva, BorderLayout.CENTER );
        contIzq.add( panelTarjetas, BorderLayout.SOUTH );
        add( contIzq, BorderLayout.WEST );

        //Panel Cine y Extensiones
        panelCine = new PanelCine( cine.darSillas( ) );
        panelOpciones = new PanelOpciones( this );
        JPanel contDer = new JPanel( new BorderLayout( ) );
        contDer.add( panelCine, BorderLayout.CENTER );
        contDer.add( panelOpciones, BorderLayout.SOUTH );
        add( contDer, BorderLayout.EAST );

        //Panel Imagen
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );
        pack( );
    }

    //-------------------------------------------------------------------------
    // Métodos
    //-------------------------------------------------------------------------

    /**
     * Creación de una nueva tarjeta en el sistema
     */
    public void crearTarjeta( )
    {
        String cedulaStr = JOptionPane.showInputDialog( this, 
        		"Cédula del cliente:", "Cédula", JOptionPane.QUESTION_MESSAGE);
        
        if( cedulaStr != null )
        {
            try
            {
                int cedula = Integer.parseInt( cedulaStr );
                cine.crearTarjeta( cedula );
                JOptionPane.showMessageDialog( this, 
                		"La tarjeta fue creada. Saldo: " + 
                				cine.darSaldoTarjeta( cedula ), "OK", 
                					JOptionPane.INFORMATION_MESSAGE );
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, 
                		"La cédula debe ser numérica", "Error", 
                			JOptionPane.ERROR_MESSAGE );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", 
                		JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Recarga una tarjeta en el sistema
     */
    public void recargarTarjeta( )
    {
        String cedulaStr = JOptionPane.showInputDialog( this, 
        		"Cédula del cliente:", "Cédula", JOptionPane.QUESTION_MESSAGE);
        
        if( cedulaStr != null )
        {
            try
            {
                int cedula = Integer.parseInt( cedulaStr );
                cine.recargarTarjeta( cedula );
                JOptionPane.showMessageDialog( this, 
                		"La tarjeta fue recargada. Nuevo saldo: " + 
                				cine.darSaldoTarjeta( cedula ), "OK", 
                					JOptionPane.INFORMATION_MESSAGE );
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, 
                		"La cédula debe ser numérica", "Error", 
                			JOptionPane.ERROR_MESSAGE );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", 
                		JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Cancela la reserva actual
     */
    public void cancelarReserva( )
    {
        int resultado = JOptionPane.showConfirmDialog( this, 
        		"La reserva actual se eliminará. Esta seguro?", "Nueva", 
        			JOptionPane.YES_NO_OPTION );
        
        if( resultado == JOptionPane.YES_OPTION )
        {
            //Cancela la reserva
            cine.cancelarReserva( reservaActual );
            reservaActual = new Reserva( );

            //Repinta
            panelReserva.repintar( reservaActual );
            panelCine.repintar( );
        }
    }

    /**
     * Paga la reserva actual con una tarjeta y crea una nueva reserva
     */
    public void pagarTarjeta( )
    {
        String cedulaStr = JOptionPane.showInputDialog( this, 
        		"Cédula del cliente:", "Cédula", 
        			JOptionPane.QUESTION_MESSAGE );
        
        if( cedulaStr != null )
        {
            try
            {
                int cedula = Integer.parseInt( cedulaStr );
                cine.pagarReservaTarjeta( reservaActual, cedula );
                JOptionPane.showMessageDialog( this, 
                		"La reserva fue pagada. Nuevo saldo: " + 
                				cine.darSaldoTarjeta( cedula ), "OK", 
                					JOptionPane.INFORMATION_MESSAGE );
                reservaActual = new Reserva( );
                panelReserva.repintar( reservaActual );
                panelCine.repintar( );
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, 
                		"La cédula debe ser numérica", "Error", 
                			JOptionPane.ERROR_MESSAGE );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", 
                		JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Paga la reserva actual en efectivo y crea una nueva reserva
     */
    public void pagarEfectivo( )
    {
        try
        {
            cine.pagarReservaEfectivo( reservaActual );
            JOptionPane.showMessageDialog( this, 
            		"La reserva fue pagada. Total pagado: " + 
            				reservaActual.darSaldoReserva( ), "OK", 
            					JOptionPane.INFORMATION_MESSAGE );
            reservaActual = new Reserva( );
            panelReserva.repintar( reservaActual );
            panelCine.repintar( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", 
            		JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Carga una reserva dado un numero de cédula
     */
    public void cargar( )
    {
        int result = JOptionPane.showConfirmDialog( this, 
        		"Esta operación borrará la reserva actual si no ha sido " +
        		"guardada.\r\n Desea Continuar?", "Confirmación", 
        		JOptionPane.YES_NO_OPTION );
        
        if( result == JOptionPane.YES_OPTION )
        {
            String cedulaStr = JOptionPane.showInputDialog( this, 
            		"Cédula del cliente:", "Cédula", 
            		JOptionPane.QUESTION_MESSAGE );
            
            if( cedulaStr != null )
            {
                try
                {
                    int cedula = Integer.parseInt( cedulaStr );
                    Reserva reservaTemp = cine.darReserva( cedula );
                    if( !cine.estaGuardada( reservaActual ) )
                    {
                        reservaActual.cancelar( );
                    }
                    reservaActual = reservaTemp;
                    panelReserva.repintar( reservaActual );
                    panelCine.repintar( );
                }
                catch( NumberFormatException e )
                {
                    JOptionPane.showMessageDialog( this, 
                    		"La cédula debe ser numérica", "Error", 
                    		JOptionPane.ERROR_MESSAGE );
                }
                catch( Exception e )
                {
                    JOptionPane.showMessageDialog( this, e.getMessage( ), 
                    		"Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
    }

    /**
     * Guarda la reserva actual en el sistema
     */
    public void guardar( )
    {
        if( !cine.estaGuardada( reservaActual ) )
        {
            String cedulaStr = JOptionPane.showInputDialog( this, 
            		"Cédula del cliente:", "Cédula", 
            		JOptionPane.QUESTION_MESSAGE );
            
            if( cedulaStr != null )
            {
                try
                {
                    int cedula = Integer.parseInt( cedulaStr );
                    cine.guardarReserva( cedula, reservaActual );
                    JOptionPane.showMessageDialog( this, 
                    		"La reserva fue guardada.", "OK", 
                    		JOptionPane.INFORMATION_MESSAGE );
                    reservaActual = new Reserva( );
                    panelReserva.repintar( reservaActual );
                    panelCine.repintar( );
                }
                catch( NumberFormatException e )
                {
                    JOptionPane.showMessageDialog( this, 
                    		"La cédula debe ser numérica", "Error", 
                    		JOptionPane.ERROR_MESSAGE );
                }
                catch( Exception e )
                {
                    JOptionPane.showMessageDialog( this, e.getMessage( ), 
                    		"Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog( this, 
            		"La reserva ya está guardada en el sistema.", "Error", 
            		JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Agrega una silla a la reserva
     * @param fila Fila de la silla
     * @param numero Numero de la silla en la fila
     */
    public void agregarSilla( char fila, int numero )
    {
        try
        {
            Silla silla = cine.darSilla( fila, numero );
            reservaActual.agregarSilla( silla );
            panelReserva.repintar( reservaActual );
            panelCine.repintar( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", 
            		JOptionPane.ERROR_MESSAGE );
        }
    }

    //-------------------------------------------------------------------------
    // Puntos de Extensión
    //-------------------------------------------------------------------------

    /**
     * Muestra las reservas que ha hecho un cliente
     */
    public void mostrarReservasCliente( )
    {
    	
    	String cedulaStr = JOptionPane.showInputDialog( this, 
    			"Cédula del cliente:", "Cédula", JOptionPane.QUESTION_MESSAGE);
    	
    	if( cedulaStr != null )
    	{
    		try
    		{
    			int cedula = Integer.parseInt( cedulaStr );
    			String resultado = cine.mostrarReservasCliente( cedula );
    			JOptionPane.showMessageDialog( this, resultado, 
    					"Reservas del cliente", JOptionPane.INFORMATION_MESSAGE);
    			
    		}
    		catch( NumberFormatException e )
    		{
    			JOptionPane.showMessageDialog( this, 
    					"La cédula debe ser numérica", "Error", 
    					JOptionPane.ERROR_MESSAGE );
    		}
    		catch( Exception e )
    		{
    			JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", 
    					JOptionPane.ERROR_MESSAGE );
    		}
    	}
    }

    /**
     * Muestra un mensaje indicando si el cliente podría pagar sus reservas. 
     * <br>
     * En caso de que no pueda, se indica cuánto dinero haría falta.
     */
    public void mostrarCapacidadCompraCliente( )
    {
    	String cedulaStr = JOptionPane.showInputDialog( this, 
    			"Cédula del cliente:", "Cédula", JOptionPane.QUESTION_MESSAGE);
    	if( cedulaStr != null )
    	{
    		try
    		{
    			int cedula = Integer.parseInt( cedulaStr );
    			
    			String resultado = cine.mostrarCapacidadCompra(cedula);
    			
    	        JOptionPane.showMessageDialog( this, resultado, 
    	        		"Capacidad de compra", JOptionPane.INFORMATION_MESSAGE);
    			
    		}
    		catch( NumberFormatException e )
    		{
    			JOptionPane.showMessageDialog( this, 
    					"La cédula debe ser numérica", "Error", 
    					JOptionPane.ERROR_MESSAGE );
    		}
    		catch( Exception e )
    		{
    			JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", 
    					JOptionPane.ERROR_MESSAGE );
    		}
    	}
    }

    //-------------------------------------------------------------------------
    // Programa principal
    //-------------------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz.
     * @param args. No se requieren argumentos
     */
    public static void main( String[] args )
    {
        InterfazCine interfaz = new InterfazCine( );
        interfaz.setVisible( true );
    }
}