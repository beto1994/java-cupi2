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

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Es el panel donde se muestran los c�lculos para la persona seleccionada
 */
public class PanelCalculosPersona extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Etiqueta "N�mero de tareas asignadas"
     */
    private JLabel txtNumeroTareasAsignadas;

    /**
     * El campo para mostrar el n�mero de tareas asignadas
     */
    private JLabel txtValorNumeroTareasAsignadas;

    /**
     * Etiqueta "Total de horas asignadas"
     */
    private JLabel txtTotalHoras;

    /**
     * El campo para mostrar el total de horas asignadas
     */
    private JLabel txtValorTotalHoras;

    /**
     * Etiqueta "Tarea con m�s horas"
     */
    private JLabel txtTareaConMasHoras;

    /**
     * El campo para mostrar la tarea con m�s horas asignadas
     */
    private JLabel txtValorTareaConMasHoras;

    /**
     * Etiqueta "�Es la persona con m�s horas asignadas?"
     */
    private JLabel txtEsPersonaMasHoras;

    /**
     * El campo para mostrar si es la persona con m�s horas asignadas
     */
    private JLabel txtValorEsPersonaMasHoras;

    /**
     * Etiqueta "Promedio de horas por tarea"
     */
    private JLabel txtPromedioPorTarea;

    /**
     * El campo para mostrar el promedio de horas por tarea
     */
    private JLabel txtValorPromedioPorTarea;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa todos sus componentes
     */
    public PanelCalculosPersona( )
    {
        setLayout( new GridLayout( 10, 1 ) );
        setPreferredSize( new Dimension( 300, 160 ) );

        // N�mero de tareas asignadas
        txtNumeroTareasAsignadas = new JLabel( );
        txtNumeroTareasAsignadas.setText( "N�mero de tareas asignadas:" );
        add( txtNumeroTareasAsignadas );

        txtValorNumeroTareasAsignadas = new JLabel( );
        txtValorNumeroTareasAsignadas.setText( "" );
        txtValorNumeroTareasAsignadas.setHorizontalAlignment( SwingConstants.RIGHT );
        add( txtValorNumeroTareasAsignadas );

        // Total de horas asignadas
        txtTotalHoras = new JLabel( );
        txtTotalHoras.setText( "Total de Horas Asignadas:" );
        add( txtTotalHoras );

        txtValorTotalHoras = new JLabel( );
        txtValorTotalHoras.setText( "" );
        txtValorTotalHoras.setHorizontalAlignment( SwingConstants.RIGHT );
        add( txtValorTotalHoras );

        // Tarea con m�s horas
        txtTareaConMasHoras = new JLabel( );
        txtTareaConMasHoras.setText( "Tarea con m�s horas:" );
        add( txtTareaConMasHoras );

        txtValorTareaConMasHoras = new JLabel( );
        txtValorTareaConMasHoras.setText( "" );
        txtValorTareaConMasHoras.setHorizontalAlignment( SwingConstants.RIGHT );
        add( txtValorTareaConMasHoras );

        // Es la persona con m�s horas
        txtEsPersonaMasHoras = new JLabel( );
        txtEsPersonaMasHoras.setText( "�Es la persona con m�s horas asignadas?:" );
        add( txtEsPersonaMasHoras );

        txtValorEsPersonaMasHoras = new JLabel( );
        txtValorEsPersonaMasHoras.setText( "" );
        txtValorEsPersonaMasHoras.setHorizontalAlignment( SwingConstants.RIGHT );
        add( txtValorEsPersonaMasHoras );

        // Promedio de horas por tarea
        txtPromedioPorTarea = new JLabel( );
        txtPromedioPorTarea.setText( "Promedio de horas por tarea:" );
        add( txtPromedioPorTarea );

        txtValorPromedioPorTarea = new JLabel( );
        txtValorPromedioPorTarea.setText( "" );
        txtValorPromedioPorTarea.setHorizontalAlignment( SwingConstants.RIGHT );
        add( txtValorPromedioPorTarea );
    }

    /**
     * Cambia el total de horas asignadas a la persona.
     * @param total El total de horas asignadas.
     */
    public void cambiarTotalHorasAsignadas( int total )
    {
        txtValorTotalHoras.setText( "" + total );
    }

    /**
     * Cambia la tarea con m�s horas.
     * @param tarea El nombre de la tarea para el que la persona tiene m�s horas asignadas. tarea != null.
     */
    public void cambiarTareaConMasHoras( String tarea )
    {
        txtValorTareaConMasHoras.setText( tarea );
    }

    /**
     * Cambia el indicador que dice si la persona mostrada es la que tiene m�s horas asignadas.
     * @param masHoras Indica si es la persona con m�s horas asignadas.
     */
    public void cambiarEsPersonaConMasHoras( boolean masHoras )
    {
        if( masHoras )
        {
            txtValorEsPersonaMasHoras.setText( "Si" );
        }
        else
        {
            txtValorEsPersonaMasHoras.setText( "No" );
        }
    }

    /**
     * Cambia el promedio por tarea mostrado.
     * @param promedio El promedio de duraci�n de las tareas asignadas a esta persona.
     */
    public void cambiarPromedioHorasPorTarea( double promedio )
    {
        DecimalFormat df = new DecimalFormat( "0.##" );
        txtValorPromedioPorTarea.setText( df.format( promedio ) );
    }

    /**
     * Cambia el n�mero de tareas asignadas a esta persona.
     * @param numero El n�mero de tareas asignadas a esta persona.
     */
    public void cambiarNumeroTareasAsignadas( int numero )
    {
        txtValorNumeroTareasAsignadas.setText( "" + numero );
    }
}