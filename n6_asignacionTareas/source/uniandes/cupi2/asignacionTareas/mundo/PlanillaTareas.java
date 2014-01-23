/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_asignacionTareas 
 * Autor: Mario S�nchez - 20/08/2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.asignacionTareas.mundo;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Es la clase que maneja toda la informaci�n de la planilla y realiza los c�lculos necesarios
 */
public class PlanillaTareas
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la lista con las tareas
     */
    private ArrayList tareas;

    /**
     * Es la lista con las personas a los que se les asignan las tareas
     */
    private ArrayList personas;

    /**
     * Es la matriz en la cual se guarda la informaci�n sobre las horas que debe trabajar cada persona en cada tarea. <br>
     * Las tareas y las personas est�n organizadas en el mismo orden que en las listas correspondientes.
     */
    private int[][] planillaHoras;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva planilla de tareas con la informaci�n contenida en el archivo. <br>
     * <b>post: <b>todas las tareas y personas definidas en el archivo se han cargado.
     * 
     * @param archivo Es la ruta hasta el archivo que contiene la informaci�n. archivo != null.
     * @throws Exception Se lanza esta excepci�n si hay problemas cargando el archivo.
     */
    public PlanillaTareas( String archivo ) throws Exception
    {
        tareas = new ArrayList( );
        personas = new ArrayList( );

        cargarDatos( archivo );

        int numeroTareas = tareas.size( );
        int numeroEmpleados = personas.size( );

        planillaHoras = new int[numeroTareas][numeroEmpleados];
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Carga la informaci�n de las tareas y las personas que se encuentra en un archivo de propiedades. <br>
     * <b>post: </b>Se carg� la informaci�n de tareas y personas en las listas correspondientes.
     * 
     * @param archivo Es la ruta hasta el archivo que contiene la informaci�n. archivo != null.
     * @throws Exception Se lanza esta excepci�n si hay problemas cargando el archivo.
     */
    private void cargarDatos( String archivo ) throws Exception
    {
        // Obtiene los datos
        Properties datos = new Properties( );
        FileInputStream input = new FileInputStream( archivo );
        datos.load( input );

        int numeroTareas = Integer.parseInt( datos.getProperty( "tareas.numero" ) );
        for( int i = 1; i <= numeroTareas; i++ )
        {
            String nombreTarea = datos.getProperty( "tareas.tarea" + i + ".nombre" );
            tareas.add( nombreTarea );
        }

        int numeroPersonas = Integer.parseInt( datos.getProperty( "personas.numero" ) );
        for( int i = 1; i <= numeroPersonas; i++ )
        {
            String nombrePersona = datos.getProperty( "personas.persona" + i + ".nombre" );
            personas.add( nombrePersona );
        }
    }
    

    /**
     * Retorna la tarea que se encuentra en la posici�n indicada.
     * 
     * @param numero La posici�n de la tarea que se quiere obtener. 0 <= numero < tareas.size().
     * @return tarea encontrada. tarea != null.
     */
    public String darTarea( int numero )
    {
        return ( String )tareas.get( numero );
    }

    /**
     * Retorna el n�mero de tareas.
     * 
     * @return N�mero de tareas.
     */
    public int darNumeroTareas( )
    {
        return tareas.size( );
    }

    /**
     * Retorna la persona que se encuentra en la posici�n indicada.
     * 
     * @param numero La posici�n de la persona que se quiere obtener. 0 <= numero < personas.size()
     * @return persona encontrada. persona !=null.
     */
    public String darPersona( int numero )
    {
        return ( String )personas.get( numero );
    }

    /**
     * Retorna el n�mero de personas.
     * 
     * @return N�mero de personas.
     */
    public int darNumeroPersonas( )
    {
        return personas.size( );
    }

    /**
     * Retorna el n�mero de horas asignadas a una tarea para una persona.
     * 
     * @param nombreTarea El nombre de la tarea para el que se quiere averiguar el valor. nombreTarea es el nombre de una de las tareas.
     * @param nombrePersona El nombre de la persona para el que se quiere averiguar el valor. nombrePersona es el nombre de una de las personas.
     * @return n�mero de horas. n�mero >= 0.
     */
    public int darHorasPersonaTarea( String nombreTarea, String nombrePersona )
    {
        int posicionTarea = darNumeroTarea( nombreTarea );
        int posicionPersona = darNumeroPersona( nombrePersona );

        return planillaHoras[ posicionTarea ][ posicionPersona ];
    }

    /**
     * Retorna la posici�n en la lista de tareas en la cual se encuentra la que se est� preguntando.
     * 
     * @param nombreTarea El nombre de la tarea buscada. ESta tarea existe en el sistema.
     * @return Retorna la posici�n en la lista de tareas en la que se encuentra una tarea espec�fica.
     */
    private int darNumeroTarea( String nombreTarea )
    {
        boolean termino = false;
        int posicionTarea = -1;
        for( int i = 0; i < tareas.size( ) && !termino; i++ )
        {
            String nombreT = ( String )tareas.get( i );
            if( nombreTarea.equals( nombreT ) )
            {
                termino = true;
                posicionTarea = i;
            }
        }
        return posicionTarea;
    }

    /**
     * Retorna la posici�n en la lista de personas en la cual se encuentra la que se est� preguntando.
     * 
     * @param nombrePersona El nombre de la persona buscada. EL nombre existe en el sistema.
     * @return Retorna la posici�n en la lista de personas en la que se encuentra una persona espec�fica.
     */
    private int darNumeroPersona( String nombrePersona )
    {
        boolean termino = false;
        int posicionPersona = -1;
        for( int i = 0; i < personas.size( ) && !termino; i++ )
        {
            String nombreP = ( String )personas.get( i );
            if( nombrePersona.equals( nombreP ) )
            {
                termino = true;
                posicionPersona = i;
            }
        }
        return posicionPersona;
    }

    /**
     * Retorna el n�mero de horas totales asignadas a una tarea.
     * 
     * @param nombreTarea El nombre de la tarea para el que se quiere averiguar el valor. nombreTarea es el nombre de una de las tareas.
     * @return El total de horas asignadas. total >= 0.
     */
    public int darTotalHorasTarea( String nombreTarea )
    {
        int posicionTarea = darNumeroTarea( nombreTarea );
        int totalHoras = 0;

        for( int j = 0; j < personas.size( ); j++ )
        {
            totalHoras += planillaHoras[ posicionTarea ][ j ];
        }

        return totalHoras;
    }

    /**
     * Retorna el n�mero de personas que han sido asignadas a una tarea (tienen m�s de 0 horas asignadas en la planilla para esa tarea).
     * 
     * @param nombreTarea El nombre de la tarea para el que se quiere averiguar el valor. nombreTarea es el nombre de una de las tareas.
     * @return El n�mero de personas con m�s de 0 horas asignadas a la tarea. n�mero >= 0.
     */
    public int darNumeroPersonasAsignadas( String nombreTarea )
    {
        int posicionTarea = darNumeroTarea( nombreTarea );
        int numeroPersonas = 0;

        for( int j = 0; j < personas.size( ); j++ )
        {
            if( planillaHoras[ posicionTarea ][ j ] > 0 )
                numeroPersonas++;
        }

        return numeroPersonas;
    }

    /**
     * Retorna el nombre de la persona que tiene m�s horas asignadas a la tarea.
     * 
     * @param nombreTarea El nombre de la tarea para el que se quiere averiguar el valor. nombreTarea es el nombre de una de las tareas.
     * @return El nombre de la persona con m�s horas asignadas a la tarea. Si hay un empate se retorna cualquiera de las personas que est�n empatadas.
     */
    public String darPersonaConMasHorasTarea( String nombreTarea )
    {
        int posicionTarea = darNumeroTarea( nombreTarea );
        int maximoHorasPersona = 0;
        int numeroPersonaMaximo = -1;

        for( int j = 0; j < personas.size( ); j++ )
        {
            int tiempoPersona = planillaHoras[ posicionTarea ][ j ];
            if( tiempoPersona > maximoHorasPersona )
            {
                maximoHorasPersona = tiempoPersona;
                numeroPersonaMaximo = j;
            }
        }

        if( numeroPersonaMaximo == -1 )
            return null;
        else
            return ( String )personas.get( numeroPersonaMaximo );
    }

    /**
     * Retorna el promedio de tiempo asignado por persona a esta tarea. Solamente se tienen en cuenta las personas que tienen algo de tiempo asignado a la tarea.
     * 
     * @param nombreTarea El nombre de la tarea para el que se quiere averiguar el valor. nombreTarea es el nombre de una de las tareas.
     * @return El promedio de tiempo entre las personas asignadas a la tarea. Si no hay nadie asignado a la tarea retorna 0.
     */
    public double darPromedioTiempoPorPersona( String nombreTarea )
    {
        int posicionTarea = darNumeroTarea( nombreTarea );
        int numeroPersonas = 0;
        double totalTiempo = 0.0;

        for( int j = 0; j < personas.size( ); j++ )
        {
            if( planillaHoras[ posicionTarea ][ j ] > 0 )
            {
                numeroPersonas++;
                totalTiempo += planillaHoras[ posicionTarea ][ j ];
            }
        }

        double promedio = 0.0;
        if( numeroPersonas != 0 )
        {
            promedio = totalTiempo / numeroPersonas;
        }

        return promedio;
    }

    /**
     * Retorna el porcentaje que representa el tiempo asignado a una tarea respecto al total de tiempo asignado a todas las tareas.
     * 
     * @param nombreTarea El nombre de la tarea para el que se quiere averiguar el valor. nombreTarea es el nombre de una de las tareas
     * @return Retorna el porcentaje como un n�mero entre 0 y 100. Si no hay ning�n tiempo asignado a ninguna tarea entonces retorna 0.
     */
    public double darPorcentajeTiempo( String nombreTarea )
    {
        int tiempoTarea = darTotalHorasTarea( nombreTarea );
        int totalTiempo = 0;

        for( int i = 0; i < tareas.size( ); i++ )
        {
            for( int j = 0; j < personas.size( ); j++ )
            {
                totalTiempo += planillaHoras[ i ][ j ];
            }
        }
        double porcentaje = 0;
        if( totalTiempo != 0 )
            porcentaje = 100 * tiempoTarea / totalTiempo;

        return porcentaje;
    }

    /**
     * Retorna el n�mero de horas totales asignadas a una persona.
     * 
     * @param nombrePersona El nombre de la persona para el que se quiere averiguar el valor. nombrePersona es el nombre de una de las personas.
     * @return El total de horas asignadas. total >= 0.
     */
    public int darTotalHorasPersona( String nombrePersona )
    {
        int posicionPersona = darNumeroPersona( nombrePersona );
        int totalTiempo = 0;

        for( int i = 0; i < tareas.size( ); i++ )
        {
            totalTiempo += planillaHoras[ i ][ posicionPersona ];
        }

        return totalTiempo;
    }

    /**
     * Retorna el nombre de la tarea con m�s horas asignadas para la persona.
     * 
     * @param nombrePersona El nombre de la persona para el que se quiere averiguar el valor. nombrePersona es el nombre de una de las personas.
     * @return El nombre de la tarea con m�s horas asignadas para la persona. Si hay un empate se retorna cualquiera de las tareas que est�n empatadas.
     */
    public String darTareaConMasHoras( String nombrePersona )
    {
        int posicionPersona = darNumeroPersona( nombrePersona );
        int maximoHorasTarea = 0;
        int numeroTareaMaximo = -1;

        for( int i = 0; i < tareas.size( ); i++ )
        {
            int tiempoTarea = planillaHoras[ i ][ posicionPersona ];
            if( tiempoTarea > maximoHorasTarea )
            {
                maximoHorasTarea = tiempoTarea;
                numeroTareaMaximo = i;
            }
        }

        if( numeroTareaMaximo == -1 )
            return null;
        else
            return ( String )tareas.get( numeroTareaMaximo );
    }

    /**
     * Sirve para saber si una persona es la que tiene el mayor n�mero de horas asignadas.
     * 
     * @param nombrePersona El nombre de la persona para el que se quiere averiguar el valor. nombrePersona es el nombre de una de las personas.
     * @return Retorna true si esta persona es la que tiene el mayor n�mero de horas asignadas: puede ser la �nica o estar empatada. Retorna false en caso contrario.
     */
    public boolean esPersonaConMasHoras( String nombrePersona )
    {
        int horasPersona = darTotalHorasPersona( nombrePersona );

        int maximoHorasPersona = 0;

        for( int j = 0; j < personas.size( ); j++ )
        {
            int tiempoPersona = 0;

            for( int i = 0; i < tareas.size( ); i++ )
            {
                tiempoPersona += planillaHoras[ i ][ j ];
            }

            if( tiempoPersona > maximoHorasPersona )
            {
                maximoHorasPersona = tiempoPersona;
            }
        }

        return horasPersona == maximoHorasPersona && horasPersona != 0;
    }

    /**
     * Retorna el promedio de tiempo asignado por tarea a esta persona. Solamente se tienen en cuenta las tareas que tienen algo de tiempo asignado.
     * 
     * @param nombrePersona El nombre de la persona para el que se quiere averiguar el valor. nombrePersona es el nombre de una de las personas.
     * @return El promedio de tiempo entre las tareas asignadas a la persona. Si no hay ninguna tarea asignada a la persona retorna 0.
     */
    public double darPromedioTiempoPorTarea( String nombrePersona )
    {
        int posicionPersona = darNumeroPersona( nombrePersona );

        int numeroTareas = 0;
        double totalTiempo = 0.0;

        for( int i = 0; i < tareas.size( ); i++ )
        {
            if( planillaHoras[ i ][ posicionPersona ] > 0 )
            {
                numeroTareas++;
                totalTiempo += planillaHoras[ i ][ posicionPersona ];
            }
        }

        double promedio = 0.0;
        if( numeroTareas != 0 )
        {
            promedio = totalTiempo / numeroTareas;
        }

        return promedio;
    }

    /**
     * Retorna el n�mero de tareas que han sido asignadas a una persona(tiene m�s de 0 horas asignadas en la planilla para esas tareas).
     * 
     * @param nombrePersona El nombre de la persona para el que se quiere averiguar el valor. nombrePersona es el nombre de una de las personas que existen en el sistema.
     * @return El n�mero de tareas para las que la persona tiene m�s de 0 horas asignadas. numero >= 0.
     */
    public int darNumeroTareasAsignadas( String nombrePersona )
    {
        int posicionPersona = darNumeroPersona( nombrePersona );
        int numeroTareas = 0;

        for( int i = 0; i < tareas.size( ); i++ )
        {
            if( planillaHoras[ i ][ posicionPersona ] > 0 )
            {
                numeroTareas++;
            }
        }

        return numeroTareas;
    }

    /**
     * Asigna a una persona trabajo por un tiempo determinado en una tarea. <br>
     * <b>Post: </b> Se asign� el tiempo en la planilla. <br>
     * Si ya hab�a tiempo asignado para esa persona en esa tarea entonces se cambi� el valor anterior.
     * 
     * @param nombreTarea El nombre de la tarea para la que se va a asignar el tiempo. El nombre corresponde a una de las tareas del sistema. nombreTarea != null.
     * @param nombrePersona El nombre de una persona a la cual se le va a asignar el tiempo. El nombre corresponde a una de las personas del sistema. nombrePersona != null.
     * @param horas. El n�mero de horas asignadas a la tarea para la persona. horas >=0.
     */
    public void asignarTiempo( String nombreTarea, String nombrePersona, int horas )
    {
        int posicionTarea = darNumeroTarea( nombreTarea );
        int posicionPersona = darNumeroPersona( nombrePersona );

        planillaHoras[ posicionTarea ][ posicionPersona ] = horas;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * Carga el archivo de Novedades y realiza los procedimientos de actualizaci�n. <br>
     * Tipo1: Incrementa las horas de todos los participantes en una tarea. <br>
     * Tipo2: Incrementa las horas de todas las tareas de un participante. <br>
     * <b>Post: <b>El archivo se ha cargado y se han actualizado las horas de las tareas y personas correspondientes. <br>
     * @param archivo Ruta del archivo de propiedades novedades.properties. archivo != null.
     * @throws Exception Si hay problemas leyendo el archivo.
     */
    public void cargarNovedades(String archivo) throws Exception
    {
    	// Lee los datos del archivo
    	Properties datos = new Properties( );
    	FileInputStream input = new FileInputStream( archivo );
    	datos.load( input );
    	
    	// Carga las novedades tipo1
    	int numeroNovedadesTipo1 = Integer.parseInt( datos.getProperty( "tipo1.numero" ) );
    	
    	for(int i = 1; i<= numeroNovedadesTipo1; i++)
    	{
    		String nombreTarea = datos.getProperty("tipo1.novedad"+i+".tarea");
    		int incrementoHoras = Integer.parseInt( datos.getProperty( "tipo1.novedad"+i+".incremento" ) );
    		int posicionTarea = this.darNumeroTarea(nombreTarea);
    		
    		// Incrementa en incrementoHoras el numero de horas asignadas a nombreTarea
    		for(int j=0; j< this.personas.size(); j++)
    		{
    			this.planillaHoras[ posicionTarea ][ j ] += incrementoHoras; 
    		}
    	}
    	
    	// Carga las novedades tipo2
    	int numeroNovedadesTipo2 = Integer.parseInt( datos.getProperty( "tipo2.numero" ) );
    	
    	for(int i = 1; i<= numeroNovedadesTipo2; i++)
    	{
    		String nombrePersona = datos.getProperty("tipo2.novedad"+i+".participante");
    		int incrementoHoras = Integer.parseInt( datos.getProperty("tipo2.novedad"+i+".incremento") );
    		int posicionPersona = this.darNumeroPersona(nombrePersona);
    		
    		// Incrementa en incrementoHoras el numero de horas asignadas a nombrePersona
    		for(int j = 0; j < this.tareas.size(); j++)
    		{
    			this.planillaHoras[ j ][ posicionPersona ] += incrementoHoras;
    		}
    	}

    }

    /**
     * M�todo de extensi�n 2
     * 
     * @return respuesta 2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}