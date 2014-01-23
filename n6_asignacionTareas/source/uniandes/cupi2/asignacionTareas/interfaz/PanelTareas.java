/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_asignacionTareas 
 * Autor: Mario Sánchez - 20/08/2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.asignacionTareas.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.asignacionTareas.mundo.PlanillaTareas;

/**
 * Es el panel donde se seleccionan las tareas
 */
public class PanelTareas extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String AVANZAR = "Avanzar";

    private static final String ATRAS = "Atrás";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazAsignacionTareas ventanaPrincipal;

    /**
     * Es la planilla de la que se sacan las tareas
     */
    private PlanillaTareas planilla;

    /**
     * Es el número de la tarea mostrada actualmente
     */
    private int posicionActual;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Es el botón para pasar a la tarea anterior
     */
    private JButton botonAtras;

    /**
     * El el botón para pasar a la siguiente tarea
     */
    private JButton botonAvanzar;

    /**
     * Es el campo donde se muestra el nombre de la tarea
     */
    private JTextField txtNombreTarea;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel.
     * @param iat Es una referencia a la clase principal de la interfaz. iat != null.
     * @param planillaTareas Es una referencia a la planilla de donde se sacan los datos. planillaTareas != null.
     */
    public PanelTareas( InterfazAsignacionTareas iat, PlanillaTareas planillaTareas )
    {
        ventanaPrincipal = iat;
        planilla = planillaTareas;
        posicionActual = 0;

        setLayout( new GridLayout( 2, 1 ) );

        JPanel panelArriba = new JPanel( );
        botonAtras = new JButton( "<<" );
        botonAtras.setActionCommand( ATRAS );
        botonAtras.addActionListener( this );
        panelArriba.add( botonAtras, BorderLayout.WEST );

        txtNombreTarea = new JTextField( 15 );
        txtNombreTarea.setEditable( false );
        panelArriba.add( txtNombreTarea, BorderLayout.CENTER );

        botonAvanzar = new JButton( ">>" );
        botonAvanzar.setActionCommand( AVANZAR );
        botonAvanzar.addActionListener( this );
        panelArriba.add( botonAvanzar, BorderLayout.EAST );

        add( panelArriba, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el nombre de la tarea mostrada actualmente.
     */
    private void actualizarTareaMostrada( )
    {
        String tarea = planilla.darTarea( posicionActual );
        txtNombreTarea.setText( tarea );
        ventanaPrincipal.actualizarTarea( tarea );
    }

    /**
     * Retorna el nombre de la tarea que se está mostrando actualmente.
     * @return nombreTarea.
     */
    public String darTarea( )
    {
        return txtNombreTarea.getText( );
    }

    /**
     * Ejecuta una operación según el botón que se haya presionado.
     * @param evento Es el evento del click sobre un botón. evento != null.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( ATRAS.equals( comando ) )
        {
            if( posicionActual == 0 )
            {
                posicionActual = planilla.darNumeroTareas( ) - 1;
            }
            else
            {
                posicionActual--;
            }
            actualizarTareaMostrada( );
        }
        else if( AVANZAR.equals( comando ) )
        {
            posicionActual = ( posicionActual + 1 ) % planilla.darNumeroTareas( );
            actualizarTareaMostrada( );
        }
    }

}