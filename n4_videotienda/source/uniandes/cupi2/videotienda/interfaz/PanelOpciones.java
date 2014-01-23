/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelOpciones.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $  
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_videotienda
 * Autor: Katalina Marcos - Diciembre 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.videotienda.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel para el manejo de las extensiones del ejercicio
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Muestra un listado de las pel�culas que hay y su n�mero de copias
     */
    private final static String LISTADO_PELICULAS = "LISTADO_PELICULAS";

    /**
     * Constante OPCION_2, Usada para realizar la opci�n de la extensi�n 2
     */
    private final static String PROMEDIO_COPIAS_PELICULA = "PROMEDIO_COPIAS_PELICULA";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazVideotienda ventanaPrincipal;

    //-----------------------------------------------------------------
    // Atributos de Interfaz
    //-----------------------------------------------------------------

    private JButton botonListadoPeliculas;
    private JButton botonPromedioCopiasPelicula;

    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Crea un nuevo panel con los botones para realizar las extensiones
     * @param interfaz Ventana principal. interfaz != null.
     */
    public PanelOpciones( InterfazVideotienda interfaz )
    {
        ventanaPrincipal = interfaz;

        setBorder( BorderFactory.createTitledBorder( "Extensiones" ) );

        botonListadoPeliculas = new JButton( );
        botonListadoPeliculas.setText( "Ver Listado Pel�culas" );
        botonListadoPeliculas.setActionCommand( LISTADO_PELICULAS );
        botonListadoPeliculas.addActionListener( this );
        add( botonListadoPeliculas );

        botonPromedioCopiasPelicula = new JButton( );
        botonPromedioCopiasPelicula.setText( "Ver Promedio de Copias por Pel�cula" );
        botonPromedioCopiasPelicula.setActionCommand( PROMEDIO_COPIAS_PELICULA );
        botonPromedioCopiasPelicula.addActionListener( this );
        add( botonPromedioCopiasPelicula );

    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Responde a los eventos de los botones del panel
     * @param evento Evento generado por un bot�n. evento != null.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( comando.equals( LISTADO_PELICULAS ) )
        {
            ventanaPrincipal.verListadoPeliculas();
        }
        else if( comando.equals( PROMEDIO_COPIAS_PELICULA ) )
        {
            ventanaPrincipal.verPromedioCopiasPelicula();
        }
    }
}
