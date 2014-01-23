/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Proyecto Cupi2
 * Ejercicio: n4_cine
 * Autor: Pablo Barvo - 13-Sep-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cine.test;

import junit.framework.TestCase;
import uniandes.cupi2.cine.mundo.Cine;
import uniandes.cupi2.cine.mundo.Reserva;
import uniandes.cupi2.cine.mundo.Silla;
import uniandes.cupi2.cine.mundo.Tarjeta;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Cine est�n 
 * correctamente implementados.
 */
public class CineTest extends TestCase
{

    //-------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Cine cine;

    /**
     * Reserva para pruebas
     */
    private Reserva reserva;

    //-------------------------------------------------------------------------
    // M�todos
    //-------------------------------------------------------------------------

    /**
     * Construye una nuevo Cine con una tarjeta para la c�dula 25 y una reserva
     */
    private void setupEscenario1( )
    {
        cine = new Cine( );
        reserva = new Reserva( );
        try
        {
            cine.crearTarjeta( 25 );
        }
        catch( Exception e )
        {
            fail( "Error al crear la tarjeta" );
        }
    }

    /**
     * Construye una nuevo Cine vac�o y una Reserva
     */
    private void setupEscenario2( )
    {
        cine = new Cine( );
        reserva = new Reserva( );
    }

    /**
     * Prueba 1 - Crear Tarjeta
     */
    public void testCrearTarjeta( )
    {
        setupEscenario1( );

        try
        {
            assertEquals( "La tarjeta tiene un valor errado", 
            		Tarjeta.CARGA_INICIAL, cine.darSaldoTarjeta( 25 ) );
            assertEquals( "El cine debe tener el saldo de la tarjeta que vendi�", 
            		Tarjeta.CARGA_INICIAL, cine.darTotalDinero( ) );
            try
            {
                cine.crearTarjeta( 25 );
                fail( "No puede crear la misma tarjeta 2 veces" );
            }
            catch( Exception e )
            {
                assertTrue( "Esta excepci�n era esperada", true );
            }

        }
        catch( Exception e )
        {
            fail( "Error al crear la tarjeta" );
        }

    }

    /**
     * Prueba 2 - Pago reserva en efectivo
     */
    public void testPagoReservaEfectivo( )
    {
        setupEscenario2( );

        try
        {
            //Busca la silla A1 y la agrega a la reserva
            Silla silla = cine.darSilla( 'A', 1 );
            reserva.agregarSilla( silla );
            cine.pagarReservaEfectivo( reserva );
            assertEquals( "La silla no est� vendida.", true, 
            		silla.estaVendida( ));
            assertEquals( "El cine debe tener el saldo de la reserva que vendi�", 
            		reserva.darSaldoReserva( ), cine.darTotalDinero( ) );
        }
        catch( Exception e )
        {
            fail( "Error al agregar la silla a la reserva" );
        }
    }

    /**
     * Prueba 3 - Pago reserva con tarjeta
     */
    public void testPagoReservaTarjeta( )
    {
        setupEscenario1( );

        try
        {
            //Busca la silla A1 y la agrega a la reserva
            Silla silla = cine.darSilla( 'A', 1 );
            reserva.agregarSilla( silla );
            cine.pagarReservaTarjeta( reserva, 25 );
            int nuevoSaldo = ( int ) ( Tarjeta.CARGA_INICIAL - 
            		( reserva.darSaldoReserva( )*(1 - Tarjeta.DESCUENTO)));
            assertEquals( "La silla no est� vendida.", true, 
            		silla.estaVendida( ) );
            assertEquals( "El saldo de la tarjeta es inv�lido", nuevoSaldo, 
            		cine.darSaldoTarjeta( 25 ) );
        }
        catch( Exception e )
        {
            fail( "Error al agregar la silla a la reserva" );
        }
    }

    /**
     * Prueba 4 - GuardarReserva
     */
    public void testGuardarReserva( )
    {
        setupEscenario2( );

        try
        {
            //Busca la silla A1 y la agrega a la reserva
            Silla silla = cine.darSilla( 'A', 1 );
            reserva.agregarSilla( silla );
            cine.crearTarjeta( 25 );
            cine.guardarReserva( 25, reserva );
            assertEquals( "La c�dula de la reserva es invalida", 25, 
            		reserva.darCedula( ) );
            assertEquals( "La reserva no qued� guardada", reserva, 
            		cine.darReserva( 25 ) );
            assertEquals( "La reserva no reporta estar guardada", true, 
            		cine.estaGuardada( reserva ) );
        }
        catch( Exception e )
        {
            fail( "Error al agregar la silla a la reserva" );
        }
    }

    /**
     * Prueba 5 - Prueba de inicializaci�n
     */
    public void testInicializacion( )
    {
        setupEscenario2( );
        int totalSillas = ( Cine.FILAS_GENERAL + Cine.FILAS_PREFERENCIAL ) * 
        		Cine.SILLAS_POR_FILA;
        assertEquals( "La cantidad de sillas no es correcta", totalSillas, 
        		cine.darSillas( ).length );

        char ultimaGeneral = 'A' + Cine.FILAS_GENERAL - 1;
        char filaPreferecial = 'A' + Cine.FILAS_GENERAL;

        try
        {
            Silla silla = cine.darSilla( ultimaGeneral, 1 );
            assertEquals( "La silla debe ser de General", true, 
            		silla.esGeneral( ) );
            assertEquals( "La silla debe costar como en General", 8000, 
            		silla.darCosto( ) );

            silla = cine.darSilla( filaPreferecial, 1 );
            assertEquals( "La silla debe ser de Preferencial", true, 
            		silla.esPreferencial( ) );
            assertEquals( "La silla debe costar como en Preferencial", 11000, 
            		silla.darCosto( ) );
        }
        catch( Exception e )
        {
            fail( "Error al obtener la silla" );
        }

    }

    /**
     * Prueba 6 - Prueba de darSilla
     */
    public void testDarSilla( )
    {
        setupEscenario2( );

        int numeroInvalido = Cine.SILLAS_POR_FILA + 1;
        char filaInvalida = 'A' + Cine.FILAS_GENERAL + Cine.FILAS_PREFERENCIAL;

        try
        {
            //Busca una silla v�lida
            cine.darSilla( 'A', 1 );
        }
        catch( Exception e )
        {
            fail( "No se pudo obtener la silla" );
        }

        try
        {
            //Busca una silla en un numero inv�lido
            cine.darSilla( 'A', numeroInvalido );
            fail( "El numero de silla pedida no es v�lido" );
        }
        catch( Exception e1 )
        {
            assertTrue( "Esta excepci�n era esperada", true );
        }

        try
        {
            //Busca una silla en una fila inv�lida
            cine.darSilla( filaInvalida, 1 );
            fail( "La fila de la silla pedida no es v�lida" );
        }
        catch( Exception e1 )
        {
            assertTrue( "Esta excepci�n era esperada", true );
        }
    }
}