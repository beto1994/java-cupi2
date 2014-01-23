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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Es el panel donde el usuario introduce los valores de Tarea, Persona y Horas
 */
public class PanelDatosAsignacion extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String ASIGNAR = "Asignar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazAsignacionTareas ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el botón de asignar tareas
     */
    private JButton botonAsignar;

    /**
     * Etiqueta "Tarea"
     */
    private JLabel labTarea;

    /**
     * Etiqueta "Persona"
     */
    private JLabel labPersona;

    /**
     * Etiqueta "Horas"
     */
    private JLabel labHoras;

    /**
     * Campo de texto para el nombre de la tarea
     */
    private JTextField txtTarea;

    /**
     * Campo de texto para el nombre de la persona
     */
    private JTextField txtPersona;

    /**
     * Campo de texto para la cantidad de horas
     */
    private JTextField txtHoras;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes.
     * @param iat Es una referencia a la clase principal de la interfaz. iat != null.
     */
    public PanelDatosAsignacion( InterfazAsignacionTareas iat )
    {
        ventanaPrincipal = iat;

        setLayout( new GridLayout( 3, 3, 5, 1 ) );
        setBorder( BorderFactory.createCompoundBorder( new TitledBorder( "Asignación de Tareas" ), new EmptyBorder( 10, 100, 10, 100 ) ) );

        botonAsignar = new JButton( "Asignar Tarea" );
        botonAsignar.setActionCommand( ASIGNAR );
        botonAsignar.addActionListener( this );

        labTarea = new JLabel( "Tarea: " );
        txtTarea = new JTextField( 10 );
        add( labTarea );
        add( txtTarea );
        add( new JLabel( ) );

        labPersona = new JLabel( "Persona: " );
        txtPersona = new JTextField( 10 );
        add( labPersona );
        add( txtPersona );
        add( botonAsignar );

        labHoras = new JLabel( "Horas: " );
        txtHoras = new JTextField( 10 );
        add( labHoras );
        add( txtHoras );
        add( new JLabel( ) );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la tarea escrita por el usuario.
     * @return tarea.
     */
    public String darTarea( )
    {
        return txtTarea.getText( );
    }

    /**
     * Retorna la persona escrita por el usuario.
     * @return persona.
     */
    public String darPersona( )
    {
        return txtPersona.getText( );
    }

    /**
     * Retorna el número de horas escrito por el usuario.
     * @return horas.
     */
    public String darHoras( )
    {
        return txtHoras.getText( );
    }

    /**
     * Cambia el nombre de la tarea mostrada.
     * @param tarea El nombre de la tarea.
     */
    public void cambiarTarea( String tarea )
    {
        txtTarea.setText( tarea );
    }

    /**
     * Cambia el nombre de la persona mostrada.
     * @param persona El nombre de la persona.
     */
    public void cambiarPersona( String persona )
    {
        txtPersona.setText( persona );
    }

    /**
     * Ejecuta una operación según el botón que se haya presionado.
     * @param evento Es el evento del click sobre un botón.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( ASIGNAR.equals( comando ) )
        {
            ventanaPrincipal.asignarTarea( );
        }
    }
}