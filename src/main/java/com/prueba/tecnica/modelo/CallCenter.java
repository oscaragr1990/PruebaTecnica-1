package com.prueba.tecnica.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.prueba.tecnica.enumerador.EstadoEmpleado;
import com.prueba.tecnica.enumerador.Rol;;

public class CallCenter {
	private long id;
	private Map<Rol, List<Empleado>> empleados;
	private List<Cliente> clienteEnEspera;

	// -------------- Constructores --------------

	public CallCenter() {
		clienteEnEspera = new ArrayList<>();
		empleados = new HashMap<Rol, List<Empleado>>();
	}

	// -------------- Logica --------------

	/**
	 * Método que permite obtener un empleado disponible, según la regla definida.
	 * 
	 * @return
	 */
	public Empleado obtenerEmpleadoDisponible() {
		Empleado empleado = null;
		List<Empleado> listaEmpleados = obtenerEmpleadoEstado(EstadoEmpleado.DISPONIBLE);

		if (listaEmpleados != null && !listaEmpleados.isEmpty()) {
			empleado = listaEmpleados.stream().filter(e -> e.getRol() == Rol.OPERADOR).findAny().orElse(null);

			if (empleado == null) {
				empleado = listaEmpleados.stream().filter(e -> e.getRol() == Rol.SUPERVISOR).findAny().orElse(null);
			}

			if (empleado == null) {
				empleado = listaEmpleados.stream().filter(e -> e.getRol() == Rol.DIRECTOR).findAny().orElse(null);
			}
		}

		if (empleado != null) {
			cambiarEstadoEmpleado(empleado, EstadoEmpleado.OCUPADO);
		}
		return empleado;
	}

	/**
	 * Método que permite cambiar el estado de un empleado.
	 * 
	 * @param empleado
	 * @param estado
	 */
	public void cambiarEstadoEmpleado(Empleado empleado, EstadoEmpleado estado) {
		empleado.setEstado(estado);
		List<Empleado> lista = this.empleados.get(empleado.getRol());
		lista.set(lista.indexOf(empleado), empleado);
		this.empleados.replace(empleado.getRol(), lista);
	}

	/**
	 * Obtiene los empleados que estén con un estado específico.
	 * 
	 * @param estado
	 * @return
	 */
	public List<Empleado> obtenerEmpleadoEstado(EstadoEmpleado estado) {
		return empleados.values().stream().flatMap(Collection::stream)
				.filter(empleado -> estado.equals(empleado.getEstado())).collect(Collectors.toList());
	}

	/**
	 * Método que permite el registro de un nuevo empleado, a la lista de espera
	 * para su asignación
	 * 
	 * @param empleado
	 */
	public void agregarEmpleado(Empleado empleado) {
		if (empleado != null) {
			List<Empleado> listaEmpleado = empleados.get(empleado.getRol());

			if (listaEmpleado == null) {
				listaEmpleado = new ArrayList<Empleado>();
			}

			listaEmpleado.add(empleado);
			empleados.put(empleado.getRol(), listaEmpleado);
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
	 * @return the empleados
	 */
	public Map<Rol, List<Empleado>> getEmpleados() {
		return empleados;
	}

	/**
	 * @param empleados
	 *            the empleados to set
	 */
	public void setEmpleados(Map<Rol, List<Empleado>> empleados) {
		this.empleados = empleados;
	}

	/**
	 * @return the clienteEnEspera
	 */
	public List<Cliente> getClienteEnEspera() {
		return clienteEnEspera;
	}

	/**
	 * @param clienteEnEspera
	 *            the clienteEnEspera to set
	 */
	public void setClienteEnEspera(List<Cliente> clienteEnEspera) {
		this.clienteEnEspera = clienteEnEspera;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CallCenter other = (CallCenter) obj;
		return id == other.id;
	}

}
