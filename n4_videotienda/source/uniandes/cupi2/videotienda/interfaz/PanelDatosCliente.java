/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelDatosCliente.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
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
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel para presentar los datos de un cliente
 */
public class PanelDatosCliente extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando modificar saldo
     */
    private static final String MODIFICAR_SALDO = "modificar_saldo";
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
    private JLabel labCedula;
    private JTextField txtCedula;
    private JLabel labNombre;
    private JTextField txtNombre;
    private JLabel labDireccion;
    private JTextField txtDireccion;
    private JLabel labSaldo;
    private JTextField txtSaldo;
    private JButton botonModificarSaldo;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel para los datos de un cliente
     * @param elDialogo Di�logo donde se ubica el panel. elDialogo != null.
     */
    public PanelDatosCliente( DialogoConsultaCliente elDialogo )
    {
        dialogo = elDialogo;

        setBorder( BorderFactory.createTitledBorder( "Datos Personales" ) );
        setLayout( new BorderLayout( ) );

        labNombre = new JLabel( "Nombre:" );
        labCedula = new JLabel( "C�dula:" );
        labDireccion = new JLabel( "Direcci�n:" );
        labSaldo = new JLabel( "Saldo:" );
        txtNombre = new JTextField( dialogo.darNombre( ) );
        txtNombre.setEditable( false );
        txtCedula = new JTextField( dialogo.darCedula( ) );
        txtCedula.setEditable( false );
        txtDireccion = new JTextField( dialogo.darDireccion( ) );
        txtDireccion.setEditable( false );
        txtSaldo = new JTextField( );
        DecimalFormat df = new DecimalFormat( "$###,###" );
        txtSaldo.setText( df.format( dialogo.darSaldo( ) ) );
        txtSaldo.setEditable( false );

        botonModificarSaldo = new JButton( );
        botonModificarSaldo.setText( "Incrementar" );
        botonModificarSaldo.setActionCommand( MODIFICAR_SALDO );
        botonModificarSaldo.addActionListener( this );

        JPanel panel = new JPanel( new GridLayout( 5, 3, 1, 6 ) );
        panel.add( labNombre );
        panel.add( txtNombre );
        panel.add( new JLabel( "" ) );
        panel.add( labCedula );
        panel.add( txtCedula );
        panel.add( new JLabel( "" ) );
        panel.add( labDireccion );
        panel.add( txtDireccion );
        panel.add( new JLabel( "" ) );
        panel.add( labSaldo );
        panel.add( txtSaldo );
        panel.add( botonModificarSaldo );

        add( panel, BorderLayout.CENTER );
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
        if( comando.equals( MODIFICAR_SALDO ) )
        {
            String incremento = JOptionPane.showInputDialog( this, "Valor de recarga:", "Recargar Saldo Cliente", JOptionPane.QUESTION_MESSAGE );
            int saldo;
            try
            {
                saldo = Integer.parseInt( incremento );
            }
            catch( NumberFormatException nfe )
            {
                JOptionPane.showMessageDialog( this, "El valor debe ser entero", "Recargar Saldo Cliente", JOptionPane.ERROR_MESSAGE );
                return;
            }
            dialogo.incrementarSaldo( saldo );
            DecimalFormat df = new DecimalFormat( "$###,###" );
            txtSaldo.setText( df.format( dialogo.darSaldo( ) ) );
        }
    }
}
