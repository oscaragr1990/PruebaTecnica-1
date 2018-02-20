/**
 * 
 */
package com.prueba.tecnica;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.prueba.tecnica.enumerador.EstadoEmpleado;
import com.prueba.tecnica.enumerador.Rol;
import com.prueba.tecnica.modelo.CallCenter;
import com.prueba.tecnica.modelo.Empleado;

/**
 * @author Windows 10
 *
 */
public class CallCenterTests {

	CallCenter callCenter;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		callCenter = new CallCenter();
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
			callCenter.agregarEmpleado(empleado);
		}
	}

	/**
	 * Test method for
	 * {@link com.prueba.tecnica.modelo.CallCenter#obtenerEmpleadoDisponible()}.
	 */
	@Test
	public void testObtenerEmpleadoDisponible() {
		assertNotEquals(callCenter.obtenerEmpleadoDisponible(), null);
	}

	/**
	 * Test method for
	 * {@link com.prueba.tecnica.modelo.CallCenter#obtenerEmpleadoEstado(com.prueba.tecnica.enumerador.EstadoEmpleado)}.
	 */
	@Test
	public void testObtenerEmpleadoEstadoDisponible() {
		assertTrue(callCenter.obtenerEmpleadoEstado(EstadoEmpleado.DISPONIBLE).size() > 0);
	}

	@Test
	public void testObtenerEmpleadoEstadoOcupado() {
		Empleado e = callCenter.obtenerEmpleadoDisponible();
		callCenter.cambiarEstadoEmpleado(e, EstadoEmpleado.OCUPADO);
		assertTrue(callCenter.obtenerEmpleadoEstado(EstadoEmpleado.OCUPADO).size() > 0);
	}

}
