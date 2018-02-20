/**
 * 
 */
package com.prueba.tecnica;

import org.junit.Before;
import org.junit.Test;

import com.prueba.tecnica.controlador.Dispatcher;
import com.prueba.tecnica.enumerador.EstadoEmpleado;
import com.prueba.tecnica.enumerador.Rol;
import com.prueba.tecnica.modelo.Cliente;
import com.prueba.tecnica.modelo.Empleado;

/**
 * @author Oscar Alejandro Gómez Ramírez
 *
 */
public class DispatcherTest {

	Dispatcher dispatcher;
	int cantLlamadas = 20;

	/**
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		dispatcher = new Dispatcher();

		for (int i = 0; i < 10; i++) {
			Empleado empleado = new Empleado();
			empleado.setEstado(EstadoEmpleado.DISPONIBLE);
			empleado.setId(i);

			if (i < 3) {
				empleado.setRol(Rol.OPERADOR);
			} else if (i < 8) {
				empleado.setRol(Rol.SUPERVISOR);
			} else {
				empleado.setRol(Rol.DIRECTOR);
			}

			empleado.setNombre(empleado.getRol() + "-" + i);
			dispatcher.getCallCenter().agregarEmpleado(empleado);
		}

	}

	/**
	 * Test method for
	 * {@link com.prueba.tecnica.controlador.Dispatcher#dispatchCall(com.prueba.tecnica.modelo.Cliente)}.
	 */
	@Test
	public void testDispatchCall() {
		for (int i = 0; i < cantLlamadas; i++) {
			Cliente cliente = new Cliente();
			cliente.setId(i);
			cliente.setNombre("Cliente-" + i);
			dispatcher.dispatchCall(cliente);
		}
	}

}
