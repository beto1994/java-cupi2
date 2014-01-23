/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_asignacionTareas 
 * Autor: Mario S�nchez - 20/08/2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.asignacionTareas.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import uniandes.cupi2.asignacionTareas.mundo.PlanillaTareas;

/**
 * Es el panel donde se seleccionan las personas
 */
public class PanelPersonas extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String AVANZAR = "Avanzar";

    private static final String ATRAS = "Atr�s";

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
     * Es el n�mero de la tarea mostrada actualmente
     */
    private int posicionActual;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Es el bot�n para pasar a la persona anterior
     */
    private JButton botonAtras;

    /**
     * El el bot�n para pasar a la siguiente persona
     */
    private JButton botonAvanzar;

    /**
     * Es el campo donde se muestra el nombre de la persona
     */
    private JTextField txtNombrePersona;

    /**
     * Es la etiqueta que muestra el n�mero de horas asignadas a la tarea
     */
    private JLabel labHorasAsignadasTarea;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel.
     * @param iat Es una referencia a la clase principal de la interfaz. iat != null.
     * @param planillaT Es una referencia a la planilla de donde se sacan los datos. planillaT != null.
     */
    public PanelPersonas( InterfazAsignacionTareas iat, PlanillaTareas planillaT )
    {
        ventanaPrincipal = iat;
        planilla = planillaT;
        posicionActual = 0;

        setLayout( new GridLayout( 2, 1 ) );

        JPanel panelArriba = new JPanel( );
        botonAtras = new JButton( "<<" );
        botonAtras.setActionCommand( ATRAS );
        botonAtras.addActionListener( this );
        panelArriba.add( botonAtras, BorderLayout.WEST );

        txtNombrePersona = new JTextField( 15 );
        txtNombrePersona.setEditable( false );
        panelArriba.add( txtNombrePersona, BorderLayout.CENTER );

        botonAvanzar = new JButton( ">>" );
        botonAvanzar.setActionCommand( AVANZAR );
        botonAvanzar.addActionListener( this );
        panelArriba.add( botonAvanzar, BorderLayout.EAST );

        add( panelArriba, BorderLayout.CENTER );

        labHorasAsignadasTarea = new JLabel( );
        labHorasAsignadasTarea.setHorizontalAlignment( SwingConstants.CENTER );
        add( labHorasAsignadasTarea, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza el nombre de la persona mostrada actualmente
     */
    private void actualizarPersonaMostrada( )
    {
        String persona = planilla.darPersona( posicionActual );
        txtNombrePersona.setText( persona );
        ventanaPrincipal.actualizarPersona( persona );
    }

    /**
     * Cambia el n�mero de horas asignadas para la tarea y persona que se est�n mostrando actualmente
     * @param numeroHoras El n�mero de horas.
     */
    public void cambiarHorasTarea( int numeroHoras )
    {
        labHorasAsignadasTarea.setText( "" + numeroHoras + " horas asignadas" );
    }

    /**
     * Retorna el nombre de la persona que se est� mostrando actualmente
     * @return nombrePersona
     */
    public String darPersona( )
    {
        return txtNombrePersona.getText( );
    }

    /**
     * Ejecuta una operaci�n seg�n el bot�n que se haya presionado
     * @param evento Es el evento del click sobre un bot�n. evento != null.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( ATRAS.equals( comando ) )
        {
            if( posicionActual == 0 )
            {
                posicionActual = planilla.darNumeroPersonas( ) - 1;
            }
            else
            {
                posicionActual--;
            }
            actualizarPersonaMostrada( );
        }
        else if( AVANZAR.equals( comando ) )
        {
            posicionActual = ( posicionActual + 1 ) % planilla.darNumeroPersonas( );
            actualizarPersonaMostrada( );
        }
    }

}