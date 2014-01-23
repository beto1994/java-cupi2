/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * PanelPregunta.java $Id$ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_examen
 * Autor: Oscar Fabra - 03-Jun-2013
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */


package uniandes.cupi2.examen.interfaz;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import uniandes.cupi2.examen.mundo.Pregunta;
import uniandes.cupi2.examen.mundo.Respuesta;

/**
 * Panel que muestra una pregunta y sus posibles respuestas
 * @author Oscar Fabra
 */
public class PanelPregunta  extends JPanel
{
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
	 * Etiqueta del número de la pregunta
	 */
	private JLabel etiquetaNumeroPregunta;
	
	/**
	 * Etiqueta del texto de la pregunta
	 */
	private JLabel etiquetaTextoPregunta;
	
	/**
	 * Etiqueta con las posibles respuestas
	 */
	private JTextArea textoPosiblesRespuestas;
	
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	
	/**
	 * Construye la pregunta y las posibles respuestas
	 */
	public PanelPregunta( Pregunta pregunta )
	{
		
		// Muestra el número de la pregunta
		this.etiquetaNumeroPregunta = new JLabel("  "+Integer.toString(pregunta.darNumeroPregunta()));
		
		// Muestra el texto de la pregunta
		this.etiquetaTextoPregunta = new JLabel("  "+pregunta.darTexto());
		
		// Muestra las posibles respuestas
		String respuestas="";
		for( int i=0; i<pregunta.darRespuestas().size(); i++ )
		{
			Respuesta respuesta = pregunta.darRespuestas().get(i);
			respuestas+= respuesta.darLetraRespuesta()+". "+respuesta.darTexto()+'\n';
		}
		
		this.textoPosiblesRespuestas = new JTextArea(respuestas);
		
		// Agrega los elementos
		
		setLayout(new BorderLayout());
		
		JPanel panelTextoPregunta = new JPanel();
		
		panelTextoPregunta.setLayout(new FlowLayout( FlowLayout.LEADING ));	
		
		panelTextoPregunta.setBackground( Color.GRAY );
		this.etiquetaNumeroPregunta.setFont( new Font("Arial", Font.BOLD, 28) );
		this.etiquetaNumeroPregunta.setForeground(Color.WHITE);
		
		this.etiquetaTextoPregunta.setForeground(Color.WHITE);
		
		panelTextoPregunta.add(this.etiquetaNumeroPregunta);
		panelTextoPregunta.add(this.etiquetaTextoPregunta);
		
		add(panelTextoPregunta, BorderLayout.NORTH);
		
		JPanel panelPosiblesRespuestas = new JPanel();
		panelPosiblesRespuestas.setBackground( Color.WHITE );
		
		panelPosiblesRespuestas.setLayout(new FlowLayout(FlowLayout.LEADING, 100, 10));
		panelPosiblesRespuestas.add(this.textoPosiblesRespuestas);
		add(panelPosiblesRespuestas, BorderLayout.CENTER);
		
	}
	
	//-----------------------------------------------------------------
	// Métodos
	//-----------------------------------------------------------------
	
	/**
	 * Muestra la pregunta correspondiente en el panel de preguntas
	 * <b>Pre: </b> El panel pregunta ha sido inicializado.
	 * <b>Post: </b> La pregunta correspondiente está siendo mostrada en el panel de preguntas
	 * @param pregunta El objeto Pregunta que va a ser mostrado al usuario. pregunta != null.
	 */
	public void repintar(Pregunta pregunta)
	{
		
		// Muestra el número de la pregunta
		this.etiquetaNumeroPregunta.setText("  "+Integer.toString(pregunta.darNumeroPregunta()));
		
		// Muestra el texto de la pregunta
		this.etiquetaTextoPregunta.setText("  "+pregunta.darTexto());
		
		// Muestra las posibles respuestas
		String respuestas="";
		for( int i=0; i<pregunta.darRespuestas().size(); i++ )
		{
			Respuesta respuesta = pregunta.darRespuestas().get(i);
			respuestas+= respuesta.darLetraRespuesta()+". "+respuesta.darTexto()+'\n';
		}
		this.textoPosiblesRespuestas.setText(respuestas);
	}

}
