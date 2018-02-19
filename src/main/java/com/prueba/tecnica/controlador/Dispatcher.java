package com.prueba.tecnica.controlador;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.prueba.tecnica.modelo.CallCenter;
import com.prueba.tecnica.modelo.Cliente;
import com.prueba.tecnica.modelo.Empleado;
import com.prueba.tecnica.modelo.Llamada;
import com.prueba.tecnica.utilidad.Utilidades;

public class Dispatcher {

	private static final int CAPACIDAD_LLAMADA = 10;
	private ExecutorService executor;
	private CallCenter callCenter;

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
