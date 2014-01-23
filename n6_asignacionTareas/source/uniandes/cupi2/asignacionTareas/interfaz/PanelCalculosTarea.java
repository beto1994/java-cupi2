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
 * Es el panel donde se muestran los c�lculos para la tarea seleccionada
 */
public class PanelCalculosTarea extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Etiqueta "N�mero de personas asignadas"
     */
    private JLabel txtNumeroPersonasAsignadas;

    /**
     * Etiqueta para mostrar el n�mero de personas asignadas a la tarea
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
     * Etiqueta "Persona con m�s horas"
     */
    private JLabel txtPersonaConMasHoras;

    /**
     * Etiqueta para mostrar el nombre de la persona con m�s horas asignadas a la tarea
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

        // N�mero de personas asignadas
        txtNumeroPersonasAsignadas = new JLabel( );
        txtNumeroPersonasAsignadas.setText( "N�mero de personas asignadas:" );
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

        // Persona con m�s horas
        txtPersonaConMasHoras = new JLabel( );
        txtPersonaConMasHoras.setText( "Persona con m�s horas:" );
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
    // M�todos
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
     * Cambia el n�mero de personas.
     * @param numero El n�mero de personas asignadas a la tarea mostrada.
     */
    public void cambiarNumeroPersonasAsignadas( int numero )
    {
        txtValorNumeroPersonasAsignadas.setText( "" + numero );
    }

    /**
     * Cambia el nombre de la persona con m�s horas.
     * @param persona El nombre de la persona con m�s horas asignadas a la tarea. persona != null.
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