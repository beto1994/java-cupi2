/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia) 
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_examen
 * Autor: Oscar Fabra - 03-Jun-2013
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.examen.interfaz;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Panel de la imagen del t�tulo
 * @author Oscar
 *
 */
@SuppressWarnings("serial")
public class PanelImagen extends JPanel
{
	//-----------------------------------------------------------------
	// Atributos de la Interfaz
	//-----------------------------------------------------------------
	
	/**
	 * Imagen del t�tulo
	 */
	private JLabel imagen;
	
	//-----------------------------------------------------------------
	// Constructores
	//-----------------------------------------------------------------
	
	/**
	 * Constructor sin par�metros
	 */
	public PanelImagen( )
	{
		FlowLayout layout = new FlowLayout( );
        layout.setHgap( 0 );
        layout.setVgap( 0 );
        setLayout( layout );

        //Carga la imagen
        ImageIcon icono = new ImageIcon( "data/titulo.png" );

        //La agrega a la etiqueta
        imagen = new JLabel( "" );
        imagen.setIcon( icono );
        add( imagen );

        //Color de fondo blanco
        setBackground( Color.WHITE );
        setBorder( new LineBorder( Color.WHITE ) );
	}

}
