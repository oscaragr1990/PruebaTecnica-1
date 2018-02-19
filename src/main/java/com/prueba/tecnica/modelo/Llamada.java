package com.prueba.tecnica.modelo;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prueba.tecnica.enumerador.EstadoEmpleado;
import com.prueba.tecnica.utilidad.Utilidades;

public class Llamada implements Runnable {
	private long id;
	private Empleado empleado;
	private Cliente cliente;
	private long fecha;
	private long duracion;
	private static final Logger logger = LogManager.getLogger("com.prueba.tecnica.model.Llamada");

	// -------------- Constructores --------------
	
	public Llamada(long id, Empleado empleado, Cliente cliente) {
		this.id = id;
		this.empleado = empleado;
		this.cliente = cliente;
	}

	@Override
	public void run() {
		this.duracion = Utilidades.random(5, 10) * 1000;
		this.fecha = new Date().getTime();
		try {
			logger.info("Inicio: " + toString());
			Thread.sleep(this.duracion);
			logger.info("Fin: " + toString());
			empleado.setEstado(EstadoEmpleado.DISPONIBLE);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	// -------------- Gets y Sets --------------

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado
	 *            the empleado to set
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the fecha
	 */
	public long getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the duracion
	 */
	public long getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion
	 *            the duracion to set
	 */
	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Llamada [id=" + id + ", empleado=" + empleado + ", cliente=" + cliente + ", fecha=" + fecha
				+ ", duracion=" + duracion + "]";
	}

}
