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

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.asignacionTareas.mundo.PlanillaTareas;

/**
 * Es la clase principal de la aplicación
 */
public class InterfazAsignacionTareas extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la planilla de tareas que se va a usar
     */
    private PlanillaTareas planilla;

    /**
     * Es el panel donde se seleccionan las tareas
     */
    private PanelTareas panelTareas;

    /**
     * Es el panel donde se muestran los cálculos para la tarea seleccionada
     */
    private PanelCalculosTarea panelCalculosTarea;

    /**
     * Es el panel donde se seleccionan las personas
     */
    private PanelPersonas panelPersonas;

    /**
     * Es el panel donde se muestran los cálculos para la persona seleccionada
     */
    private PanelCalculosPersona panelCalculosPersona;

    /**
     * El el panel donde se introducen los datos para asignar tareas
     */
    private PanelDatosAsignacion panelDatosAsignacion;

    /**
     * Panel de opciones
     */
    private PanelOpciones panelOpciones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la ventana principal de la aplicación.
     * @param planillaTareas Es la planilla de tareas que se va a usar. planillaTareas != null.
     */
    public InterfazAsignacionTareas( PlanillaTareas planillaTareas )
    {
        planilla = planillaTareas;

        inicializar( );
        setTitle( "Planilla de Asignación de Tareas" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        pack( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa los componentes de la interfaz
     */
    private void inicializar( )
    {
        JPanel panelPanelesTareas = new JPanel( new BorderLayout( ) );
        panelTareas = new PanelTareas( this, planilla );
        panelCalculosTarea = new PanelCalculosTarea( );
        panelPanelesTareas.add( panelTareas, BorderLayout.CENTER );
        panelPanelesTareas.add( panelCalculosTarea, BorderLayout.EAST );
        panelPanelesTareas.setBorder( new TitledBorder( "Tareas" ) );
        getContentPane( ).add( panelPanelesTareas, BorderLayout.NORTH );

        JPanel panelPanelesPersonas = new JPanel( new BorderLayout( ) );
        panelPersonas = new PanelPersonas( this, planilla );
        panelCalculosPersona = new PanelCalculosPersona( );
        panelPanelesPersonas.add( panelPersonas, BorderLayout.CENTER );
        panelPanelesPersonas.add( panelCalculosPersona, BorderLayout.EAST );
        panelPanelesPersonas.setBorder( new TitledBorder( "Personas" ) );
        getContentPane( ).add( panelPanelesPersonas, BorderLayout.CENTER );

        JPanel panelPanelesAsignacion = new JPanel( new BorderLayout( ) );
        panelDatosAsignacion = new PanelDatosAsignacion( this );
        panelOpciones = new PanelOpciones( this );
        panelPanelesAsignacion.add( panelDatosAsignacion, BorderLayout.CENTER );
        panelPanelesAsignacion.add( panelOpciones, BorderLayout.SOUTH );
        getContentPane( ).add( panelPanelesAsignacion, BorderLayout.SOUTH );

        actualizarTodo( );
    }

    /**
     * Este método sirve para actualizar la interfaz cuando cambia la tarea mostrada.
     * @param nombreTarea El nombre de la tarea que se muestra ahora.
     */
    public void actualizarTarea( String nombreTarea )
    {
        String persona = panelPersonas.darPersona( );
        if( !persona.equals( "" ) )
        {
            int horas = planilla.darHorasPersonaTarea( nombreTarea, persona );
            panelPersonas.cambiarHorasTarea( horas );
        }

        panelDatosAsignacion.cambiarTarea( nombreTarea );
        actualizarCalculosTarea( nombreTarea );
    }

    /**
     * Este método sirve para actualizar la interfaz cuando cambia la persona mostrada.
     * @param nombrePersona El nombre de la persona que se muestra ahora.
     */
    public void actualizarPersona( String nombrePersona )
    {
        String tarea = panelTareas.darTarea( );
        if( !tarea.equals( "" ) )
        {
            int horas = planilla.darHorasPersonaTarea( tarea, nombrePersona );
            panelPersonas.cambiarHorasTarea( horas );
        }

        panelDatosAsignacion.cambiarPersona( nombrePersona );
        actualizarCalculosPersona( nombrePersona );
    }

    /**
     * Este método sirve para actualizar la interfaz cuando se asigna una nueva tarea.
     */
    public void actualizarTodo( )
    {
        String persona = panelPersonas.darPersona( );
        String tarea = panelTareas.darTarea( );

        // Actualizar el número de horas asignadas a una persona para una tarea
        if( !tarea.equals( "" ) && !persona.equals( "" ) )
        {
            int horas = planilla.darHorasPersonaTarea( tarea, persona );
            panelPersonas.cambiarHorasTarea( horas );
        }

        if( !tarea.equals( "" ) )
        {
            actualizarCalculosTarea( tarea );
        }
        if( !persona.equals( "" ) )
        {
            actualizarCalculosPersona( persona );
        }
    }

    /**
     * Este método sirve para actualizar los cálculos para la tarea que se muestra actualmente.
     * @param nombreTarea El nombre de la tarea que se muestra ahora.
     */
    private void actualizarCalculosTarea( String nombreTarea )
    {
        panelCalculosTarea.cambiarNumeroPersonasAsignadas( planilla.darNumeroPersonasAsignadas( nombreTarea ) );
        panelCalculosTarea.cambiarPersonaMasHoras( planilla.darPersonaConMasHorasTarea( nombreTarea ) );
        panelCalculosTarea.cambiarPorcentajeTarea( planilla.darPorcentajeTiempo( nombreTarea ) );
        panelCalculosTarea.cambiarPromedioPorPersona( planilla.darPromedioTiempoPorPersona( nombreTarea ) );
        panelCalculosTarea.cambiarTotalHoras( planilla.darTotalHorasTarea( nombreTarea ) );
    }

    /**
     * Este método sirve para actualizar los cálculos para la persona que se muestra actualmente.
     * @param nombrePersona El nombre de la persona que se muestra ahora.
     */
    private void actualizarCalculosPersona( String nombrePersona )
    {
        panelCalculosPersona.cambiarEsPersonaConMasHoras( planilla.esPersonaConMasHoras( nombrePersona ) );
        panelCalculosPersona.cambiarNumeroTareasAsignadas( planilla.darNumeroTareasAsignadas( nombrePersona ) );
        panelCalculosPersona.cambiarPromedioHorasPorTarea( planilla.darPromedioTiempoPorTarea( nombrePersona ) );
        panelCalculosPersona.cambiarTareaConMasHoras( planilla.darTareaConMasHoras( nombrePersona ) );
        panelCalculosPersona.cambiarTotalHorasAsignadas( planilla.darTotalHorasPersona( nombrePersona ) );
    }

    /**
     * Asigna una tarea a una persona con los datos introducidos en el panel panelDatosAsignacion.
     */
    public void asignarTarea( )
    {
        String tarea = panelDatosAsignacion.darTarea( );
        String persona = panelDatosAsignacion.darPersona( );
        String strHoras = panelDatosAsignacion.darHoras( );
        try
        {
            int horas = Integer.parseInt( strHoras );
            if( horas < 0 )
            {
                JOptionPane.showMessageDialog( this, "El número de horas debe ser mayor o igual a cero (0)", "Error", JOptionPane.ERROR_MESSAGE );
                return;
            }
            planilla.asignarTiempo( tarea, persona, horas );
            actualizarTodo( );
        }
        catch( NumberFormatException nfe )
        {
            JOptionPane.showMessageDialog( this, "El número de horas debe ser un valor entero válido", "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Carga el archivo de novedades
     * <b>Post: </b>El archivo de novedades se ha cargado y se ha mostrado un mensaje al usuario.
     */
    public void cargarArchivoNovedades( )
    {
		try 
		{
			planilla.cargarNovedades( "./data/novedades.properties" );
			
			// Actualiza toda la interfaz con los nuevos datos
			this.actualizarTodo();
			
			JOptionPane.showMessageDialog( this, "¡Actualización Exitosa!", "Felicitaciones", JOptionPane.INFORMATION_MESSAGE );
			
		} catch (Exception e) 
		{
			JOptionPane.showMessageDialog( this, "Error de actualización: "+e.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE );
		}
        
    }

    /**
     * Ejecuta la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String respuesta = planilla.metodo2( );
        JOptionPane.showMessageDialog( this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );

    }

    // -----------------------------------------------------------------
    // Ejecución
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicación
     * @param args Parámetros de ejecución. No se requiere ninguno.
     */
    public static void main( String[] args )
    {
        try
        {
            PlanillaTareas p = new PlanillaTareas( "./data/datosPlanilla.dat" );
            InterfazAsignacionTareas iat = new InterfazAsignacionTareas( p );
            iat.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}