/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * InterfazExamen.java $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Proyecto Cupi2
 * Ejercicio: n5_examen
 * Autor: Oscar Fabra - 03-Jun-2013
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.examen.interfaz;

import java.awt.*;

import javax.swing.*;

import uniandes.cupi2.examen.mundo.*;

/**
 * Ventana principal de la aplicaci�n para responder un examen
 * @author Oscar
 *
 */
@SuppressWarnings("serial")
public class InterfazExamen extends JFrame 
{
	
	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------
	
	/**
	 * Clase principal del modelo del mundo
	 */
	private Examen examen;
	
	/**
	 * N�mero de pregunta que se est� mostrando
	 */
	private int numeroPreguntaActual;
	
	
	//-----------------------------------------------------------------
	// Atributos de la Interfaz
	//-----------------------------------------------------------------
	
	/**
	 * Panel con la imagen de t�tulo
	 */
	private PanelImagen panelImagen;
	
	/**
	 * Panel con la pregunta y las posibles respuestas
	 */
	private PanelPregunta panelPregunta;
	
	/**
	 * Panel con las opciones de respuesta y extensiones
	 */
	private PanelOpciones panelOpciones;
	
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	
	/**
	 * Crea la ventana principal de la Interfaz <br>
	 * @throws Exception Si no se puede leer el archivo del Examen
	 * <b>Post: </b> Se crea la nueva interfaz del Examen con los paneles en los tabs correspondientes
	 */
	public InterfazExamen()
	{
		try 
		{
			// Crea la clase principal
			this.examen = new Examen();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog( this, "No se pudo leer el archivo del ex�men", "Error", JOptionPane.ERROR_MESSAGE );
		}
		
		// Inicializa la primera pregunta
		this.numeroPreguntaActual = 0;

		// Organiza el panel principal
		setLayout(new BorderLayout());

		// Panel Imagen
		this.panelImagen = new PanelImagen( );
		add( this.panelImagen, BorderLayout.NORTH );
		pack();
		
		// Panel Pregunta
		Pregunta pregunta = this.examen.darPregunta(0);
		this.panelPregunta = new PanelPregunta(pregunta);
		add(this.panelPregunta, BorderLayout.CENTER);	
		pack();
		
		// Panel Opciones
		this.panelOpciones = new PanelOpciones(this);
		add(this.panelOpciones, BorderLayout.SOUTH);
		pack();
		
		setTitle("Examen");
		centrarVentana( this );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//-----------------------------------------------------------------
	// M�todos
	//-----------------------------------------------------------------
	
	/**
	 * Devuelve la pregunta actual que est� mostrando la interfaz <br>
	 * @return Pregunta La pregunta actual.
	 */
	public Pregunta darPreguntaActual( )
	{
		return this.examen.darPregunta(this.numeroPreguntaActual);
	}
	
	/**
	 * Devuelve el n�mero de preguntas del examen <br>
	 * @return Int N�mero de preguntas del examen.
	 */
	public int darCantidadPreguntasExamen()
	{
		return this.examen.darCantidadPreguntas();
	}
	
	/**
	 * Incrementa la pregunta actual del Examen en 1 <br>
	 * <b>Post: </b> numeroPreguntaActual <= examen.darCantidadPregunta
	 */
	public void siguientePregunta( String respuestaSeleccionada)
	{	
		if(this.guardarRespuestaSeleccionada(respuestaSeleccionada))
		{
			if(this.numeroPreguntaActual < this.examen.darCantidadPreguntas()-1)
			{
				this.numeroPreguntaActual++;

				// Muestra la nueva pregunta
				this.mostrarPreguntaActual();

				// Obtiene la respuesta ya seleccionada, si la hay
				String respuestaActual = this.darPreguntaActual().darRespuestaSeleccionada(); 

				// Coloca la respuesta que ya hab�a seleccionado el usuario en el panel de opciones
				this.panelOpciones.establecerRespuestaSeleccionada(respuestaActual);

			}
			else
			{
				int opc = JOptionPane.showConfirmDialog(this, "Esta fue la �ltima pregunta del examen, �desea terminar?","�Terminar?", JOptionPane.YES_NO_OPTION);

				if (opc == JOptionPane.YES_OPTION)
				{
					String respuesta = "Su puntaje es: "+this.examen.darPuntaje()+" sobre 100 puntos.";
					JOptionPane.showMessageDialog(this, respuesta, "Puntaje Obtenido", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

	}
	
	/**
	 * Reduce la pregunta actual del Examen en 1 <br>
	 * <b>Post: </b> numeroPreguntaActual >= 0 <br>
	 */
	public void anteriorPregunta( String respuestaSeleccionada )
	{
		if(this.guardarRespuestaSeleccionada(respuestaSeleccionada))
		{			
			if(this.numeroPreguntaActual > 0)
			{
				this.numeroPreguntaActual--;

				// Muestra la nueva pregunta
				this.mostrarPreguntaActual();

				// Obtiene la respuesta ya seleccionada, si la hay
				String respuestaActual = this.darPreguntaActual().darRespuestaSeleccionada(); 

				// Coloca la respuesta que ya hab�a seleccionado el usuario en el panel de opciones
				this.panelOpciones.establecerRespuestaSeleccionada(respuestaActual);

			}
			else
			{
				JOptionPane.showMessageDialog(this, "Esta es la primera pregunta", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/**
	 * Reinicia el examen despu�s de tomar la respuesta seleccionada de la pregunta actual. <br>
	 * <b>Post: </b> El puntaje parcial ha sido mostrado, las preguntas reiniciadas y la primera
	 * pregunta est� siendo mostrada. <br>
	 * @param respuestaSeleccionada La respuesta seleccionada de la pregunta actual.
	 */
    public void reiniciarExamen( String respuestaSeleccionada)
    {
    	if(this.guardarRespuestaSeleccionada(respuestaSeleccionada))
    	{
    		int opc = JOptionPane.showConfirmDialog(this, "�Confirma que desea reiniciar?","�Reiniciar?", JOptionPane.YES_NO_OPTION);
			
			if (opc == JOptionPane.YES_OPTION)
			{
				String respuesta = "Su puntaje hasta el momento era: "+this.examen.darPuntaje()+" sobre 100 puntos.";
				JOptionPane.showMessageDialog(this, respuesta, "Puntaje Parcial Obtenido", JOptionPane.INFORMATION_MESSAGE);
				
	    		try 
	    		{
	    			// Reinicia el examen
					this.examen.reiniciar();
				} 
	    		catch (Exception e) 
				{
					JOptionPane.showMessageDialog(this, "No se pudo leer el archivo de preguntas.", "Error", JOptionPane.ERROR_MESSAGE);
				}
	    		
	    		// Inicializa la primera pregunta
	    		this.numeroPreguntaActual = 0;

	    		// Panel Pregunta
	    		Pregunta pregunta = this.examen.darPregunta(0);
	    		this.panelPregunta.repintar(pregunta);
	    		
	    		// Panel Opciones
	    		this.panelOpciones.establecerRespuestaSeleccionada("");
			}
    	}
    	
    }
	
	/**
	 * Guarda la respuesta seleccionada y muestra el puntaje al usuario <br>
	 * <b>Post: </b> La �ltima respuesta seleccionada ha sido guardada <br>
	 * @param respuestaSeleccionada La respuesta seleccionada para la pregunta actual.
	 */
	public void terminarExamen( String respuestaSeleccionada)
	{
		if(this.guardarRespuestaSeleccionada(respuestaSeleccionada))
		{
			int opc = JOptionPane.showConfirmDialog(this, "�Confirma que desea terminar?","�Terminar?", JOptionPane.YES_NO_OPTION);
			
			if (opc == JOptionPane.YES_OPTION)
			{
				String respuesta = "Su puntaje es: "+this.examen.darPuntaje()+" sobre 100 puntos.";
				JOptionPane.showMessageDialog(this, respuesta, "Puntaje Obtenido", JOptionPane.INFORMATION_MESSAGE);
				
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}	
		}
		
	}
	
	/**
	 * Muestra al usuario la pregunta del n�mero seleccionado. <br>
	 * <b>Post: </b> Ha sido mostrado error si el n�mero es incorrecto. En caso contrario, se est� mostrando la pregunta seleccionada. <br>
	 * @param numeroPreguntaSeleccionado N�mero de pregunta ingresado en el campo de N�mero de Pregunta.
	 */
	public void irAPreguntaExamen( String numeroPreguntaSeleccionado )
	{
		if(numeroPreguntaSeleccionado.equals(""))
		{
			JOptionPane.showMessageDialog(this, "No ha ingresado un n�mero de pregunta.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			int numeroPregunta = 0;
			
			// Flag para mostrar Error al usuario una sola vez, en caso de que los haya
			boolean errorDeFormato = false;
			
			// Verifica que el n�mero de Pregunta sea v�lido
			try
			{
			numeroPregunta = Integer.parseInt(numeroPreguntaSeleccionado);
			}
			catch (NumberFormatException ne)
			{
				errorDeFormato = true;
				JOptionPane.showMessageDialog(this, "El n�mero ingresado no es v�lido.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			if(numeroPregunta <= 0 || numeroPregunta > this.examen.darCantidadPreguntas())
			{
				if(!errorDeFormato)
				{
					JOptionPane.showMessageDialog(this, "Ese n�mero de pregunta no existe.", "Error", JOptionPane.ERROR_MESSAGE);	
				}
			}
			else
			{
				// Establece el n�mero de pregunta actual
				this.numeroPreguntaActual = numeroPregunta - 1;
				
				// Muestra la nueva pregunta
				this.mostrarPreguntaActual();

				// Obtiene la respuesta ya seleccionada, si la hay
				String respuestaActual = this.darPreguntaActual().darRespuestaSeleccionada(); 

				// Coloca la respuesta que ya hab�a seleccionado el usuario en el panel de opciones
				this.panelOpciones.establecerRespuestaSeleccionada(respuestaActual);
				
			}
			
		}
		
		
	}
	
	/**
	 * Guarda la respuesta seleccionada que el usuario seleccion�, despu�s de dar clic en "Anterior" o "Siguiente". <br>
	 * <b>Pre: </b> El usuario hizo clic en "Anterior", "Siguiente" o "Terminar" <br>
	 * <b>Post: </b> La respuesta que el usuario escribi� ha sido guardada. <br>
	 * @param respuestaSeleccionada La respuesta a la pregunta actual en pantalla.
	 * @return Si se pudo realizar la operaci�n o no.
	 */
	
	public boolean guardarRespuestaSeleccionada( String respuestaSeleccionada )
	{
		// Asigna la respuesta a la pregunta actual
		Pregunta pregunta = this.darPreguntaActual();
		
		try
		{
			pregunta.establecerRespuestaSeleccionada(respuestaSeleccionada);			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		// Guarda la pregunta en el examen
		this.examen.guardarPregunta(this.numeroPreguntaActual, pregunta);
		return true;
		
	}
	
	/**
	 * Asigna al panel de Preguntas la pregunta que corresponde. <br>
	 * <b>Post: </b> La pregunta adecuada est� siendo mostrada al usuario en el panel de preguntas.
	 */	
	public void mostrarPreguntaActual()
	{
		
		// Obtiene la pregunta
		Pregunta pregunta = this.examen.darPregunta(this.numeroPreguntaActual);
		
		// Muestra la pregunta en el panel de la pregunta
		this.panelPregunta.repintar(pregunta);
		
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
	 * M�todo para la extensi�n 1
	 */
	public void mostrarMensaje1()
	{
		String respuesta = this.examen.metodo1();
		JOptionPane.showMessageDialog( this, respuesta, "Respuesta 1", JOptionPane.INFORMATION_MESSAGE );
	}
	
	/**
	 * M�todo para la extensi�n 2
	 */
	public void mostrarMensaje2()
	{
		String respuesta = this.examen.metodo2();
		JOptionPane.showMessageDialog( this, respuesta, "Respuesta 2", JOptionPane.INFORMATION_MESSAGE );
	}
	
	
	//-----------------------------------------------------------------
	// Programa principal
	//-----------------------------------------------------------------
	
	/**
	 * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz.
	 * @param args. No se requieren argumentos
	 */
	public static void main( String[] args)
	{
		InterfazExamen interfaz = new InterfazExamen( );		
		interfaz.setVisible( true );
	}

}
