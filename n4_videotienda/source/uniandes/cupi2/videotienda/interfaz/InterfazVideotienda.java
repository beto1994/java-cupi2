/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: InterfazVideotienda.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import uniandes.cupi2.videotienda.mundo.Cliente;
import uniandes.cupi2.videotienda.mundo.Pelicula;
import uniandes.cupi2.videotienda.mundo.VideoTienda;

/**
 * Ventana principal de la aplicaci�n de la videotienda
 */
@SuppressWarnings("serial")
public class InterfazVideotienda extends JFrame
{

	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Tarifa inicial de la videotienda
     */
    private static final int TARIFA_INICIAL = 5000;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Videotienda
     */
    private VideoTienda videotienda;

    /**
     * Panel para el manejo de los clientes
     */
    private PanelClientes panelClientes;

    /**
     * Panel para el manejo de las pel�culas
     */
    private PanelPeliculas panelPeliculas;

    /**
     * Panel para el manejo de la tarifa
     */
    private PanelTarifa panelTarifa;

    /**
     * Panel para el manejo de las extensiones
     */
    private PanelOpciones panelOpciones;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea la interfaz para una videotienda <br>
     * <b>Post: </b> Se crea la nueva interfaz con los paneles en los tabs correspondientes
     */
    public InterfazVideotienda( )
    {
        setLayout( new BorderLayout( ) );

        panelTarifa = new PanelTarifa( this );
        panelTarifa.asignarTarifa( TARIFA_INICIAL );
        add( panelTarifa, BorderLayout.NORTH );
        panelPeliculas = new PanelPeliculas( this );
        panelClientes = new PanelClientes( this );
        JPanel panelMedio = new JPanel( new BorderLayout( ) );
        panelMedio.add( panelPeliculas, BorderLayout.CENTER );
        panelMedio.add( panelClientes, BorderLayout.SOUTH );
        add( panelMedio, BorderLayout.CENTER );
        panelOpciones = new PanelOpciones( this );
        add( panelOpciones, BorderLayout.SOUTH );

        //Inicializa la videotienda con valor diario por defecto
        videotienda = new VideoTienda( TARIFA_INICIAL );
        String archivo = "./data/peliculas.txt";
        try
        {
            videotienda.cargarPeliculas( archivo );
            panelPeliculas.actualizarPeliculas( videotienda.darCatalogo( ) );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "No se pudo cargar la informaci�n de las pel�culas del archivo " + archivo, "Videotienda UniAndes", JOptionPane.INFORMATION_MESSAGE );
        }

        pack( );
        setTitle( "Videotienda UniAndes" );
        setResizable( true );
        centrarVentana( this );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    }
    
    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Cambia la tarifa de alquiler de la videotienda
     * @param tarifa Nueva tarifa.
     */
    public void cambiarTarifa( int tarifa )
    {
        try
        {
            videotienda.modificarTarifa( tarifa );
            JOptionPane.showMessageDialog( this, "La tarifa fue actualizada con �xito", "Cambio de Tarifa", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Cambio de Tarifa", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Retorna el cat�logo de pel�culas
     * @return cat�logo de pel�culas de la videotienda
     */
    public ArrayList<Pelicula> darCatalogo( )
    {
        return videotienda.darCatalogo( );
    }

    /**
     * Busca y muestra la informaci�n de la pel�cula.
     * @param titulo T�tulo de la pel�cula. titulo != null.
     */
    public void mostrarPelicula( String titulo )
    {
        Pelicula p = videotienda.buscarPelicula( titulo );
        if( p == null )
            JOptionPane.showMessageDialog( this, "La pel�cula no existe", "Ver Informaci�n Pel�cula", JOptionPane.ERROR_MESSAGE );
        DialogoPelicula dialogo = new DialogoPelicula( p );
        centrarVentana( dialogo );
        dialogo.setVisible( true );
    }

    /**
     * Agrega una copia a la pel�cula del t�tulo dado
     * @param titulo T�tulo de la pel�cula. titulo != null.
     */
    public void agregarCopia( String titulo )
    {
        try
        {
            videotienda.agregarCopiaPelicula( titulo );
            Pelicula p = videotienda.buscarPelicula( titulo );
            JOptionPane.showMessageDialog( this, "La pel�cula \"" + titulo + "\" tiene " + p.darTotalCopias( ) + " copias", "Agregar Copia", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Agregar Copia", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Busca y muestra la informaci�n de un cliente.
     * @param cedula C�dula del cliente a mostrar. cedula != null.
     */
    public void mostrarCliente( String cedula )
    {
        Cliente c = videotienda.buscarCliente( cedula );
        if( c == null )
        {
            JOptionPane.showMessageDialog( this, "El cliente no est� registrado", "Ver Informaci�n Cliente", JOptionPane.ERROR_MESSAGE );
            return;
        }
        DialogoConsultaCliente dialogo = new DialogoConsultaCliente( c, this );
        centrarVentana( dialogo );
        dialogo.setVisible( true );
    }

    /**
     * Inicia el proceso de registro de un cliente
     */
    public void afiliarCliente( )
    {
        DialogoRegistroCliente dialogo = new DialogoRegistroCliente( this );
        centrarVentana( dialogo );
        dialogo.setVisible( true );
    }

    /**
     * Afilia un nuevo cliente a la videotienda.
     * @param nombre Nombre del cliente. nombre != null.
     * @param cedula C�dula del cliente. cedula != null.
     * @param direccion Direcci�n del cliente. direccion != null.
     * @param saldo Saldo inicial del cliente. saldo >0.
     */
    public void afiliarCliente( String nombre, String cedula, String direccion, int saldo )
    {
        try
        {
            videotienda.afiliarCliente( cedula, nombre, direccion );
            videotienda.cargarSaldoCliente( cedula, saldo );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Afiliaci�n de Cliente", JOptionPane.ERROR_MESSAGE );
            return;
        }

        panelClientes.actualizarClientes( videotienda.darListaClientes( ) );
    }

    /**
     * Retorna la lista de clientes de la videotienda
     * @return ArrayList con los clientes de la videotienda
     */
    public ArrayList<Cliente> darListaClientes( )
    {
        return videotienda.darListaClientes( );
    }

    /**
     * Recarga el saldo del cliente con la cantidad dada
     * @param cedula C�dula del cliente. cedula != null.
     * @param recarga Monto de la recarga.
     */
    public void cargarSaldo( String cedula, int recarga )
    {
        try
        {
            videotienda.cargarSaldoCliente( cedula, recarga );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Recarga Saldo Cliente", JOptionPane.ERROR_MESSAGE );
            return;
        }
    }

    /**
     * Inicia el alquiler de una pel�cula
     */
    public void alquilarPelicula( )
    {
        String pelicula = panelPeliculas.darPeliculaSeleccionada( );
        if( pelicula == null )
        {
            JOptionPane.showMessageDialog( this, "Debe seleccionar una pel�cula", "Alquilar Pel�cula", JOptionPane.ERROR_MESSAGE );
            return;
        }
        String cliente = panelClientes.darClienteSeleccionado( );
        if( cliente == null )
        {
            JOptionPane.showMessageDialog( this, "Debe seleccionar un cliente", "Alquilar Pel�cula", JOptionPane.ERROR_MESSAGE );
            return;
        }
        try
        {
            int copia = videotienda.alquilarPelicula( pelicula, cliente );
            JOptionPane.showMessageDialog( this, "Se alquil� la copia " + copia + " de la pel�cula \"" + pelicula + "\"", "Alquilar Pel�cula", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Alquilar Pel�cula", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Devuelve la copia alquilada por un cliente
     * @param cedula C�dula del cliente. cliente != null.
     * @param pelicula T�tulo de la pel�cula a devolver. pelicula != null.
     * @param copia N�mero de la copia a devolver.
     */
    public void devolverCopia( String cedula, String pelicula, int copia )
    {
        try
        {
            videotienda.devolverCopia( pelicula, copia, cedula );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Devolver Pel�cula", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Centra una ventana en la pantalla
     * @param ventana La ventana que se va a centrar. ventana != null.
     */
    private void centrarVentana( Component ventana )
    {
        Dimension dPantalla = Toolkit.getDefaultToolkit( ).getScreenSize( );
        Dimension dVentana = ventana.getSize( );

        int xEsquina = ( dPantalla.width / 2 ) - ( dVentana.width / 2 );
        int yEsquina = ( dPantalla.height / 2 ) - ( dVentana.height / 2 );

        ventana.setLocation( xEsquina, yEsquina );
    }

    //-----------------------------------------------------------------
    // Puntos de Extensi�n
    //-----------------------------------------------------------------

    /**
     * Muestra una lista de pel�culas con su respectivo n�mero de copias.
     */
    public void verListadoPeliculas( )
    {
        String respuesta = videotienda.mostrarListadoPeliculas();
        JOptionPane.showMessageDialog( this, respuesta, "Listado de Pel�culas", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra el promedio de copias por pel�cula
     */
    public void verPromedioCopiasPelicula( )
    {
        String respuesta = videotienda.calcularPromedioCopias();
        JOptionPane.showMessageDialog( this, respuesta, "Promedio de Copias por Pel�cula", JOptionPane.INFORMATION_MESSAGE );
    }

    //-----------------------------------------------------------------
    // Ejecuci�n
    //-----------------------------------------------------------------
    /**
     * M�todo para la ejecuci�n del programa
     * @param args Argumentos para la ejecuci�n. No se requiere ninguno.
     */
    public static void main( String[] args )
    {
        try
        {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName( ) );
        }
        catch( Exception e )
        {
            //Ignora el look & feel
        }
        InterfazVideotienda i = new InterfazVideotienda( );
        i.setVisible( true );
    }

}
