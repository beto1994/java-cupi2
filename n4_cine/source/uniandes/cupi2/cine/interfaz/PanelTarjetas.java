/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_cine
 * Autor: Pablo Barvo - Sep 19, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.cine.interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel de manejo de tarjetas
 * @author Pablo Barvo
 */
public class PanelTarjetas extends JPanel implements ActionListener
{
    //-------------------------------------------------------------------------
    // Constantes
    //-------------------------------------------------------------------------

    /**
     * Comando crear tarjeta
     */
    private static final String CREAR = "CREAR";

    /**
     * Comando recargar tarjeta
     */
    private static final String RECARGAR = "RECARGAR";

    //-------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazCine ventana;

    //-------------------------------------------------------------------------
    // Atributos de interfaz
    //-------------------------------------------------------------------------

    /**
     * Botón Crear tarjeta
     */
    private JButton btnCrear;

    /**
     * Botón recargar tarjeta
     */
    private JButton btnRecargar;

    //-------------------------------------------------------------------------
    // Constructores
    //-------------------------------------------------------------------------

    /**
     * Constructor del panel
     * @param laVentana Ventana principal
     */
    public PanelTarjetas( InterfazCine laVentana )
    {
        ventana = laVentana;

        setBorder( new TitledBorder( "Manejo Tarjetas" ) );
        setLayout( new GridLayout( 1, 2 ) );

        //Botón crear
        btnCrear = new JButton( "Crear Tarjeta" );
        btnCrear.setActionCommand( CREAR );
        btnCrear.addActionListener( this );
        add( btnCrear );

        //Botón recargar
        btnRecargar = new JButton( "Recargar Tarjeta" );
        btnRecargar.setActionCommand( RECARGAR );
        btnRecargar.addActionListener( this );
        add( btnRecargar );
    }

    //-------------------------------------------------------------------------
    // Métodos
    //-------------------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( CREAR.equals( comando ) )
        {
            ventana.crearTarjeta( );
        }
        else if( RECARGAR.equals( comando ) )
        {
            ventana.recargarTarjeta( );
        }
    }
}
