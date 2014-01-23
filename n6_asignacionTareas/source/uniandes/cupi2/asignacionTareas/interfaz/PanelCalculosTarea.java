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
 * Es el panel donde se muestran los cálculos para la tarea seleccionada
 */
public class PanelCalculosTarea extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Etiqueta "Número de personas asignadas"
     */
    private JLabel txtNumeroPersonasAsignadas;

    /**
     * Etiqueta para mostrar el número de personas asignadas a la tarea
     */
    private JLabel txtValorNumeroPersonasAsignadas;

    /**
     * Etiqueta "Total de Horas Asignadas"
     */
    private JLabel txtTotalHoras;

    /**
     * Etiqueta para mostrar el total de horas asignadas
     */
    private JLabel txtValorTotalHoras;

    /**
     * Etiqueta "Persona con más horas"
     */
    private JLabel txtPersonaConMasHoras;

    /**
     * Etiqueta para mostrar el nombre de la persona con más horas asignadas a la tarea
     */
    private JLabel txtValorPersonaConMasHoras;

    /**
     * Etiqueta "Promedio de horas por persona"
     */
    private JLabel txtPromedioPorPersona;

    /**
     * Etiqueta con el promedio de horas por persona
     */
    private JLabel txtValorPromedioPorPersona;

    /**
     * Etiqueta "Porcentaje de trabajo que representa la tarea"
     */
    private JLabel txtPorcentajeTrabajo;

    /**
     * Etiqueta que muestra el porcentaje de trabajo que representa la tarea
     */
    private JLabel txtValorPorcentajeTrabajo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     */
    public PanelCalculosTarea( )
    {
        setLayout( new GridLayout( 10, 1 ) );
        setPreferredSize( new Dimension( 300, 160 ) );

        // Número de personas asignadas
        txtNumeroPersonasAsignadas = new JLabel( );
        txtNumeroPersonasAsignadas.setText( "Número de personas asignadas:" );
        add( txtNumeroPersonasAsignadas );

        txtValorNumeroPersonasAsignadas = new JLabel( );
        txtValorNumeroPersonasAsignadas.setText( "" );
        txtValorNumeroPersonasAsignadas.setHorizontalAlignment( SwingConstants.RIGHT );
        add( txtValorNumeroPersonasAsignadas );

        // Total de horas asignadas
        txtTotalHoras = new JLabel( );
        txtTotalHoras.setText( "Total de Horas Asignadas:" );
        add( txtTotalHoras );

        txtValorTotalHoras = new JLabel( );
        txtValorTotalHoras.setText( "" );
        txtValorTotalHoras.setHorizontalAlignment( SwingConstants.RIGHT );
        add( txtValorTotalHoras );

        // Persona con más horas
        txtPersonaConMasHoras = new JLabel( );
        txtPersonaConMasHoras.setText( "Persona con más horas:" );
        add( txtPersonaConMasHoras );

        txtValorPersonaConMasHoras = new JLabel( );
        txtValorPersonaConMasHoras.setText( "" );
        txtValorPersonaConMasHoras.setHorizontalAlignment( SwingConstants.RIGHT );
        add( txtValorPersonaConMasHoras );

        // Promedio de horas por persona
        txtPromedioPorPersona = new JLabel( );
        txtPromedioPorPersona.setText( "Promedio de horas por persona:" );
        add( txtPromedioPorPersona );

        txtValorPromedioPorPersona = new JLabel( );
        txtValorPromedioPorPersona.setText( "" );
        txtValorPromedioPorPersona.setHorizontalAlignment( SwingConstants.RIGHT );
        add( txtValorPromedioPorPersona );

        // Porcentaje del total
        txtPorcentajeTrabajo = new JLabel( );
        txtPorcentajeTrabajo.setText( "Porcentaje de trabajo que representa la tarea:" );
        add( txtPorcentajeTrabajo );

        txtValorPorcentajeTrabajo = new JLabel( );
        txtValorPorcentajeTrabajo.setText( "" );
        txtValorPorcentajeTrabajo.setHorizontalAlignment( SwingConstants.RIGHT );
        add( txtValorPorcentajeTrabajo );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el total de horas.
     * @param total El total de horas para la tarea mostrada.
     */
    public void cambiarTotalHoras( int total )
    {
        txtValorTotalHoras.setText( "" + total );
    }

    /**
     * Cambia el número de personas.
     * @param numero El número de personas asignadas a la tarea mostrada.
     */
    public void cambiarNumeroPersonasAsignadas( int numero )
    {
        txtValorNumeroPersonasAsignadas.setText( "" + numero );
    }

    /**
     * Cambia el nombre de la persona con más horas.
     * @param persona El nombre de la persona con más horas asignadas a la tarea. persona != null.
     */
    public void cambiarPersonaMasHoras( String persona )
    {
        txtValorPersonaConMasHoras.setText( persona );
    }

    /**
     * Cambia el promedio de horas por persona.
     * @param promedio El promedio de horas de trabajo para cada persona asignada a la tarea.
     */
    public void cambiarPromedioPorPersona( double promedio )
    {
        DecimalFormat df = new DecimalFormat( "0.##" );
        txtValorPromedioPorPersona.setText( df.format( promedio ) );
    }

    /**
     * Cambia el porcentaje de trabajo que representa esta tarea.
     * @param porcentaje El porcentaje de trabajo que representa esta tarea respecto al total de trabajo.
     */
    public void cambiarPorcentajeTarea( double porcentaje )
    {
        DecimalFormat df = new DecimalFormat( "##0.##" );
        txtValorPorcentajeTrabajo.setText( df.format( porcentaje ) + "%" );
    }
}