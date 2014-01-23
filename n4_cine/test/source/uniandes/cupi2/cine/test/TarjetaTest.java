/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia) 
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_cine
 * Autor: Pablo Barvo - Sep 22, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.cine.test;

import junit.framework.TestCase;
import uniandes.cupi2.cine.mundo.Tarjeta;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Tarjeta 
 * est�n correctamente implementados.
 */
public class TarjetaTest extends TestCase
{
    //-------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Tarjeta tarjeta;

    //-------------------------------------------------------------------------
    // M�todos
    //-------------------------------------------------------------------------

    /**
     * Construye una nueva Tarjeta vac�a
     */
    private void setupEscenario1( )
    {
        tarjeta = new Tarjeta( 1 );
    }

    /**
     * Prueba de carga inicial
     */
    public void testCargaInicial( )
    {
        setupEscenario1( );
        assertEquals( "El saldo inicial es inv�lido.", Tarjeta.CARGA_INICIAL, 
        		tarjeta.darCantidadDisponible( ) );
    }

    /**
     * Prueba de recarga de tarjeta
     */
    public void testRecargar( )
    {
        setupEscenario1( );
        int saldoTarjeta = tarjeta.darCantidadDisponible( ) + Tarjeta.RECARGA;
        tarjeta.recargar( );
        assertEquals( "El saldo despu�s de la recarga es inv�lido.", 
        		saldoTarjeta, tarjeta.darCantidadDisponible( ) );
    }

    /**
     * Prueba de resta de cantidad
     */
    public void testResta( )
    {
        setupEscenario1( );
        int saldoTarjeta = tarjeta.darCantidadDisponible( ) - 5230;
        try
        {
            tarjeta.restarDinero( 5230 );
        }
        catch( Exception e )
        {
            fail( "Error al restar dinero de la tarjeta." );
        }
        assertEquals( "El saldo despu�s de una resta de dinero es inv�lido.", 
        		saldoTarjeta, tarjeta.darCantidadDisponible( ) );
    }

    /**
     * Prueba de resta de cantidad mayor
     */
    public void testRestaMayor( )
    {
        setupEscenario1( );
        try
        {
            tarjeta.restarDinero( Tarjeta.CARGA_INICIAL + 1 );
            fail( "No deber�a poder restar una cantidad mayor a la que " +
            		"tiene disponible" );
        }
        catch( Exception e )
        {
            assertTrue( "Esta excepci�n era esperada", true );
        }
    }
}
