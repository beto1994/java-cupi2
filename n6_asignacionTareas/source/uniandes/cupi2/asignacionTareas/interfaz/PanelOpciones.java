/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_asignacionTareas
 * Autor: Katalina Marcos - Noviembre 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.asignacionTareas.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Presenta las opciones de extensión del ejercicio
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * El comando para cargar un archivo de novedades
     */
    private final String CARGAR_ARCHIVO_NOVEDADES = "CARGAR_ARCHIVO_NOVEDADES";

    /**
     * El comando para opción 2
     */
    private final String OPCION_2 = "opción 2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazAsignacionTareas ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------
    /**
     * Botón cargar archivo de novedades
     */
    private JButton btnCargarArchivoNovedades;

    /**
     * Botón de opción 2
     */
    private JButton botonOpcion2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Crea el panel con los botones de extensión
     * @param ventanaPpal ventana principal. ventanaPpal != null
     */
    public PanelOpciones( InterfazAsignacionTareas ventanaPpal )
    {
        ventanaPrincipal = ventanaPpal;

        setLayout( new GridLayout( 1, 2, 10, 1 ) );
        setBorder( BorderFactory.createCompoundBorder( BorderFactory.createTitledBorder( "" ), BorderFactory.createEmptyBorder( 10, 100, 10, 100 ) ) );

        btnCargarArchivoNovedades = new JButton( "Cargar Archivo de Novedades" );
        btnCargarArchivoNovedades.setActionCommand( CARGAR_ARCHIVO_NOVEDADES );
        btnCargarArchivoNovedades.addActionListener( this );
        add( btnCargarArchivoNovedades );

        botonOpcion2 = new JButton( "Opción 2" );
        botonOpcion2.setActionCommand( OPCION_2 );
        botonOpcion2.addActionListener( this );
        add( botonOpcion2 );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Realiza las acciones asociadas a los botones del panel. <br>
     * <b>post: </b> Se ejecutó la acción correspondiente al botón presionado.
     * @param evento Evento del click en un botón. evento != null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( CARGAR_ARCHIVO_NOVEDADES.equals( comando ) )
        {
            ventanaPrincipal.cargarArchivoNovedades();
        }
        else if( OPCION_2.equals( comando ) )
        {
            ventanaPrincipal.reqFuncOpcion2( );
        }
    }

}
