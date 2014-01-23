/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelBotonesCliente.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
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

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel de opciones los datos de un cliente
 */
public class PanelBotonesCliente extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando aceptar
     */
    private static final String ACEPTAR = "aceptar";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Di�logo donde se ubica el panel
     */
    private DialogoConsultaCliente dialogo;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------
    private JButton botonAceptar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel para los datos de un cliente
     * @param elDialogo Di�logo donde se ubica el panel. elDialogo != null.
     */
    public PanelBotonesCliente( DialogoConsultaCliente elDialogo )
    {
        dialogo = elDialogo;
        botonAceptar = new JButton( );
        botonAceptar.setText( "Cerrar" );
        botonAceptar.setActionCommand( ACEPTAR );
        botonAceptar.addActionListener( this );
        add( botonAceptar );
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
        if( comando.equals( ACEPTAR ) )
        {
            dialogo.aceptar( );
        }
    }
}
