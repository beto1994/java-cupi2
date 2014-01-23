/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelRegistroCliente.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel para registrar los datos un cliente
 */
public class PanelRegistroCliente extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando aceptar
     */
    private static final String ACEPTAR = "aceptar";
    /**
     * Comando cancelar
     */
    private static final String CANCELAR = "cancelar";
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Di�logo donde se ubica el panel
     */
    private DialogoRegistroCliente dialogo;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------
    private JLabel labCedula;
    private JTextField txtCedula;
    private JLabel labNombre;
    private JTextField txtNombre;
    private JLabel labDireccion;
    private JTextField txtDireccion;
    private JLabel labSaldo;
    private JTextField txtSaldo;
    private JButton botonCancelar;
    private JButton botonAceptar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel para el registro de un cliente
     * @param elDialogo Di�logo donde se ubica el panel. elDialogo != null.
     */
    public PanelRegistroCliente( DialogoRegistroCliente elDialogo )
    {
        dialogo = elDialogo;

        setBorder( BorderFactory.createTitledBorder( "Datos Personales del Cliente" ) );
        setLayout( new BorderLayout( ) );

        labNombre = new JLabel( "Nombre:" );
        labCedula = new JLabel( "C�dula:" );
        labDireccion = new JLabel( "Direcci�n:" );
        labSaldo = new JLabel( "Saldo:" );
        txtNombre = new JTextField( );
        txtCedula = new JTextField( );
        txtDireccion = new JTextField( );
        txtSaldo = new JTextField( );

        JPanel panel = new JPanel( new GridLayout( 4, 2, 1, 6 ) );
        panel.add( labNombre );
        panel.add( txtNombre );
        panel.add( labCedula );
        panel.add( txtCedula );
        panel.add( labDireccion );
        panel.add( txtDireccion );
        panel.add( labSaldo );
        panel.add( txtSaldo );

        add( panel, BorderLayout.CENTER );

        botonAceptar = new JButton( );
        botonAceptar.setText( "Aceptar" );
        botonAceptar.setActionCommand( ACEPTAR );
        botonAceptar.addActionListener( this );

        botonCancelar = new JButton( );
        botonCancelar.setText( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );

        panel = new JPanel( new GridLayout( 1, 2, 1, 5 ) );
        panel.add( botonAceptar );
        panel.add( botonCancelar );
        add( panel, BorderLayout.SOUTH );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Retorna el dato C�dula del formulario
     * @return c�dula
     */
    public String darCedula( )
    {
        return txtCedula.getText( ).trim( );
    }

    /**
     * Retorna el dato Nombre del formulario
     * @return nombre
     */
    public String darNombre( )
    {
        return txtNombre.getText( ).trim( );
    }

    /**
     * Retorna el dato Direcci�n del formulario
     * @return direcci�n
     */
    public String darDireccion( )
    {
        return txtDireccion.getText( ).trim( );
    }

    /**
     * Retorna el dato Saldo del formulario
     * @return saldo
     */
    public String darSaldo( )
    {
        return txtSaldo.getText( ).trim( );
    }

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
        else if( comando.equals( CANCELAR ) )
        {
            dialogo.cancelar( );
        }
    }
}
