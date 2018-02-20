package com.prueba.tecnica.controlador;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prueba.tecnica.modelo.CallCenter;
import com.prueba.tecnica.modelo.Cliente;
import com.prueba.tecnica.modelo.Empleado;
import com.prueba.tecnica.modelo.Llamada;
import com.prueba.tecnica.utilidad.Utilidades;

/**
 * Clase que cumpla la funcionalidad de despachador para ejecutar los hilos de
 * las llamadas
 * 
 * @author Oscar Alejandro Gómez Ramírez
 *
 */
public class Dispatcher {

	private static final int CAPACIDAD_LLAMADA = 10;
	private ExecutorService executor;
	private CallCenter callCenter;
	private static final Logger logger = LogManager.getLogger("com.prueba.tecnica.controlador.Dispatcher");

	public Dispatcher() {
		callCenter = new CallCenter();
		executor = Executors.newFixedThreadPool(CAPACIDAD_LLAMADA);
	}

	/**
	 * Método que permite ejecutar la llamada del cliente
	 * 
	 * @param cliente
	 *            Cliente que realiza la llamada
	 */
	public void dispatchCall(Cliente cliente) {
		Empleado empleado = this.callCenter.obtenerEmpleadoDisponible();
		if (empleado != null) {
			Runnable llamada = new Llamada(Utilidades.random(0, 10000), empleado, cliente);
			executor.execute(llamada);
		} else {
			callCenter.getClienteEnEspera().add(cliente);
			logger.info("["+cliente.getNombre()+"] Disculpa en este momento no se encuentra operadores para atender la llamada.");

		}
	}

	// -------------- Gets y Sets --------------

	/**
	 * @return the callCenter
	 */
	public CallCenter getCallCenter() {
		return callCenter;
	}

	/**
	 * @param callCenter
	 *            the callCenter to set
	 */
	public void setCallCenter(CallCenter callCenter) {
		this.callCenter = callCenter;
	}

}
