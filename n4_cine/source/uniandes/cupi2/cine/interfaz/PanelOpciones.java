/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia) 
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_cine
 * Autor: Pablo Barvo - Sep 19, 2005, Oscar Fabra - 02-Jun-2013
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cine.interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel de manejo de extensiones
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    //-------------------------------------------------------------------------
    // Constantes
    //-------------------------------------------------------------------------
	
	/**
     * Comando Opci�n 1
     */
    private static final String MOSTRAR_RESERVAS_CLIENTE = 
    		"MOSTRAR_RESERVAS_CLIENTE";

    /**
     * Comando Opci�n 2
     */
    private static final String MOSTRAR_CAPACIDAD_CLIENTE = 
    		"MOSTRAR_CAPACIDAD_CLIENTE";

    //-------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazCine ventana;

    //-------------------------------------------------------------------------
    // Atributos de interfaz
    //-------------------------------------------------------------------------

    /**
     * Bot�n Lista de Reservas de un Cliente
     */
    private JButton btnMostrarReservasCliente;

    /**
     * Bot�n Mostrar Capacidad Pago de un Cliente
     */
    private JButton btnMostrarCapacidadCliente;

    //-------------------------------------------------------------------------
    // Constructores
    //-------------------------------------------------------------------------

    /**
     * Constructor del panel
     * @param laVentana Ventana principal
     */
    public PanelOpciones( InterfazCine laVentana )
    {
        ventana = laVentana;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 1, 2 ) );

        //Bot�n mostrar Reservas de un Cliente
        btnMostrarReservasCliente = new JButton("Lista de Reservas de Cliente");
        btnMostrarReservasCliente.setActionCommand( MOSTRAR_RESERVAS_CLIENTE );
        btnMostrarReservasCliente.addActionListener( this );
        add( btnMostrarReservasCliente );

        //Bot�n mostrar Capacidad de Pago de un Cliente
        btnMostrarCapacidadCliente = new JButton("Capacidad de Pago de Cliente");
        btnMostrarCapacidadCliente.setActionCommand( MOSTRAR_CAPACIDAD_CLIENTE );
        btnMostrarCapacidadCliente.addActionListener( this );
        add( btnMostrarCapacidadCliente );
    }

    //-------------------------------------------------------------------------
    // M�todos
    //-------------------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( MOSTRAR_RESERVAS_CLIENTE ) )
        {
            ventana.mostrarReservasCliente( );
        }
        else if( comando.equals( MOSTRAR_CAPACIDAD_CLIENTE ) )
        {
            ventana.mostrarCapacidadCompraCliente();
        }
    }
}
