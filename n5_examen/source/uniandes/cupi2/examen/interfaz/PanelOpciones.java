/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * PanelOpciones.java $Id$ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_examen
 * Autor: Oscar Fabra - 10-Jun-2013
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.examen.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Panel que permite al usuario introducir una respuesta, pasar a la siguiente o
 * anterior pregunta, terminar el examen y las opciones de extensión.
 * @author Oscar
 *
 */
@SuppressWarnings("serial")
public class PanelOpciones extends JPanel implements ActionListener
{
	//-----------------------------------------------------------------
	// Constantes
	//-----------------------------------------------------------------
	
	/**
	 * Comando siguiente pregunta
	 */
	private static final String SIGUIENTE = "SIGUIENTE";
	
	/**
	 * Comando anterior pregunta
	 */
	private static final String ANTERIOR = "ANTERIOR";
	
	/**
	 * Comando reiniciar examen
	 */
	private static final String REINICIAR = "REINICIAR";
	
	/**
	 * Comando terminar examen
	 */
	private static final String TERMINAR = "TERMINAR";
	
	/**
	 * Comando Opción 1
	 */
	private static final String OPCION_1 = "OPCION_1";
	
	/**
	 * Comando Opción 2
	 */
	private static final String OPCION_2 = "OPCION_2";
	
	/**
	 * Comando ir a pregunta
	 */
	private static final String IR_A_PREGUNTA = "IR_A_PREGUNTA";
	
	
	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------
	
	/**
	 * Ventana principal de la aplicación
	 */
	private InterfazExamen ventana;
	
	
	//-----------------------------------------------------------------
	// Atributos de interfaz
	//-----------------------------------------------------------------
	
	/**
	 * Etiqueta Selección
	 */
	private JLabel etiquetaSeleccion;
	
	/**
	 * Campo para la respuesta seleccionada
	 */
	private JTextField campoSeleccion;
	
	/**
	 * Botón Siguiente pregunta
	 */
	private JButton btnSiguiente;
	
	/**
	 * Botón Anterior pregunta
	 */
	private JButton btnAnterior;
	
	/**
	 * Botón de Reiniciar
	 */
	private JButton btnReiniciar;
	
	/**
	 * Botón Terminar examen
	 */
	private JButton btnTerminar;
	
	/**
	 * Botón para extensión Opción 1
	 */
	private JButton btnOpcion1;
	
	/**
	 * Botón para extensión Opción 2
	 */
	private JButton btnOpcion2;
	
	/**
	 * Etiqueta con el número de preguntas
	 */
	private JLabel etiquetaConNumeroDePreguntas;
	
	/**
	 * Etiqueta para indicar que ingrese un número de pregunta
	 */
	private JLabel etiquetaNumeroPregunta;
	
	/**
	 * Campo para seleccionar la pregunta
	 */
	private JTextField campoNumeroPregunta;
	
	/**
	 * Botón ir a Pregunta
	 */
	private JButton btnIrAPregunta;
	
	//-----------------------------------------------------------------
	// Constructores
	//-----------------------------------------------------------------
	
	/**
	 * Constructor del panel
	 * @param laVentana Ventana principal
	 */
	
	public PanelOpciones( InterfazExamen laVentana)
	{
		this.ventana = laVentana;
		
		// Etiqueta Selección
		this.etiquetaSeleccion = new JLabel("Selección:");
		
		// Campo para Respuesta Seleccionada
		this.campoSeleccion = new JTextField( );
		
		// Botón Anterior pregunta
		this.btnAnterior = new JButton("<");
		this.btnAnterior.setActionCommand( ANTERIOR );
		this.btnAnterior.addActionListener( this );
		
		// Botón Siguiente pregunta
		this.btnSiguiente = new JButton(">");
		this.btnSiguiente.setActionCommand( SIGUIENTE );
		this.btnSiguiente.addActionListener( this );
		
		// Botón Reiniciar Examen
		this.btnReiniciar = new JButton("Reiniciar");
		this.btnReiniciar.setActionCommand( REINICIAR );
		this.btnReiniciar.addActionListener(this);
		
		// Botón Terminar Examen
		this.btnTerminar = new JButton("Terminar");
		this.btnTerminar.setActionCommand( TERMINAR );
		this.btnTerminar.addActionListener( this );
		
		// Botón para extensión Opción 1
		this.btnOpcion1 = new JButton("Opción 1");
		this.btnOpcion1.setActionCommand( OPCION_1 );
		this.btnOpcion1.addActionListener( this );
		
		// Botón para extensión Opción 2
		this.btnOpcion2 = new JButton("Opción 2");
		this.btnOpcion2.setActionCommand( OPCION_2 );
		this.btnOpcion2.addActionListener( this );
		
		// Etiqueta con el número de preguntas
		this.etiquetaConNumeroDePreguntas = new JLabel("Total Preguntas: "+this.ventana.darCantidadPreguntasExamen());
		
		// Etiqueta con el texto "Número de pregunta:"
		this.etiquetaNumeroPregunta = new JLabel("Número de la Pregunta: ");
		
		// Campo de texto que recibe el número de pregunta
		this.campoNumeroPregunta = new JTextField( 2 );
		
		// Botón Ir A Pregunta
		this.btnIrAPregunta = new JButton("Ir A Pregunta");
		this.btnIrAPregunta.setActionCommand( IR_A_PREGUNTA );
		this.btnIrAPregunta.addActionListener(this);
		
		
		// Agrega los elementos
		
		setBorder( new TitledBorder("Opciones") );
		setLayout( new BorderLayout(4, 4) );
		
		JPanel panelSeleccion = new JPanel();
		panelSeleccion.setLayout( new GridLayout(1, 2) );
		panelSeleccion.add(this.etiquetaSeleccion);
		panelSeleccion.add(this.campoSeleccion);
		
		add(panelSeleccion, BorderLayout.NORTH);
		
		JPanel panelCambioPregunta = new JPanel();
		panelCambioPregunta.setLayout( new GridLayout(1, 6) );
		panelCambioPregunta.add(this.btnAnterior);
		panelCambioPregunta.add(this.btnSiguiente);
		panelCambioPregunta.add(this.btnReiniciar);
		panelCambioPregunta.add(this.btnTerminar);
		panelCambioPregunta.add(this.btnOpcion1);
		panelCambioPregunta.add(this.btnOpcion2);
		
		add(panelCambioPregunta, BorderLayout.CENTER);
		
		JPanel panelIrAPregunta = new JPanel();
		panelIrAPregunta.setLayout( new GridLayout(1,4) );
		panelIrAPregunta.add(this.etiquetaConNumeroDePreguntas);
		panelIrAPregunta.add(this.etiquetaNumeroPregunta);
		panelIrAPregunta.add(this.campoNumeroPregunta);
		panelIrAPregunta.add(this.btnIrAPregunta);
		
		add(panelIrAPregunta, BorderLayout.SOUTH);
		
	}
	
	//-----------------------------------------------------------------
	// Métodos
	//-----------------------------------------------------------------
	
	/**
	 * Asigna el valor de la respuesta seleccionada
	 * @param laRespuesta Respuesta seleccionada
	 */
	public void establecerRespuestaSeleccionada(String laRespuesta)
	{
		this.campoSeleccion.setText(""+laRespuesta);
	}
	
	/**
	 * Responde a los eventos de los botones del panel
	 * @param evento Evento generado por un botón. evento != null.
	 */
	public void actionPerformed( ActionEvent evento )
	{
		String comando = evento.getActionCommand();
		String respuestaSeleccionada = this.campoSeleccion.getText();

		if( comando.equals( SIGUIENTE ))
		{
			// Pasa a la siguiente pregunta
			this.ventana.siguientePregunta(respuestaSeleccionada);
		} 
		else if( comando.equals( ANTERIOR ))
		{
			// Pasa a la pregunta anterior
			this.ventana.anteriorPregunta(respuestaSeleccionada);
		}
		else if( comando.equals( REINICIAR ))
		{
			// Reinicia el examen y muestra la primera pregunta
			this.ventana.reiniciarExamen(respuestaSeleccionada);
		}
		else if( comando.equals( TERMINAR ))
		{
			// Termina el examen y muestra el puntaje al usuario
			this.ventana.terminarExamen(respuestaSeleccionada);
		}
		else if( comando.equals( OPCION_1 ))
		{
			// Método para la extensión 1
			this.ventana.mostrarMensaje1();
		} 
		else if( comando.equals( OPCION_2 ))
		{
			// Método para la extensión 1
			this.ventana.mostrarMensaje2();
		}
		else if( comando.equals( IR_A_PREGUNTA ))
		{
			String numeroPreguntaSeleccionado = this.campoNumeroPregunta.getText();			
			
			// Método para ir a determinada pregunta
			this.ventana.irAPreguntaExamen(numeroPreguntaSeleccionado);
		}
	}

}
