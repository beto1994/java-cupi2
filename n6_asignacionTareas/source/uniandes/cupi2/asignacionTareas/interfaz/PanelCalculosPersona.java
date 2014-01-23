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

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Es el panel donde se muestran los cálculos para la persona seleccionada
 */
public class PanelCalculosPersona extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Etiqueta "Número de tareas asignadas"
     */
    private JLabel txtNumeroTareasAsignadas;

    /**
     * El campo para mostrar el número de tareas asignadas
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
     * Etiqueta "Tarea con más horas"
     */
    private JLabel txtTareaConMasHoras;

    /**
     * El campo para mostrar la tarea con más horas asignadas
     */
    private JLabel txtValorTareaConMasHoras;

    /**
     * Etiqueta "¿Es la persona con más horas asignadas?"
     */
    private JLabel txtEsPersonaMasHoras;

    /**
     * El campo para mostrar si es la persona con más horas asignadas
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

        // Número de tareas asignadas
        txtNumeroTareasAsignadas = new JLabel( );
        txtNumeroTareasAsignadas.setText( "Número de tareas asignadas:" );
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

        // Tarea con más horas
        txtTareaConMasHoras = new JLabel( );
        txtTareaConMasHoras.setText( "Tarea con más horas:" );
        add( txtTareaConMasHoras );

        txtValorTareaConMasHoras = new JLabel( );
        txtValorTareaConMasHoras.setText( "" );
        txtValorTareaConMasHoras.setHorizontalAlignment( SwingConstants.RIGHT );
        add( txtValorTareaConMasHoras );

        // Es la persona con más horas
        txtEsPersonaMasHoras = new JLabel( );
        txtEsPersonaMasHoras.setText( "¿Es la persona con más horas asignadas?:" );
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
     * Cambia la tarea con más horas.
     * @param tarea El nombre de la tarea para el que la persona tiene más horas asignadas. tarea != null.
     */
    public void cambiarTareaConMasHoras( String tarea )
    {
        txtValorTareaConMasHoras.setText( tarea );
    }

    /**
     * Cambia el indicador que dice si la persona mostrada es la que tiene más horas asignadas.
     * @param masHoras Indica si es la persona con más horas asignadas.
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
     * @param promedio El promedio de duración de las tareas asignadas a esta persona.
     */
    public void cambiarPromedioHorasPorTarea( double promedio )
    {
        DecimalFormat df = new DecimalFormat( "0.##" );
        txtValorPromedioPorTarea.setText( df.format( promedio ) );
    }

    /**
     * Cambia el número de tareas asignadas a esta persona.
     * @param numero El número de tareas asignadas a esta persona.
     */
    public void cambiarNumeroTareasAsignadas( int numero )
    {
        txtValorNumeroTareasAsignadas.setText( "" + numero );
    }
}