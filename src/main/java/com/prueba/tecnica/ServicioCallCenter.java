package com.prueba.tecnica;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.controlador.Dispatcher;
import com.prueba.tecnica.modelo.Cliente;
import com.prueba.tecnica.modelo.Empleado;
import com.prueba.tecnica.utilidad.Utilidades;

@RestController
@RequestMapping(value = "/callcenter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

public class ServicioCallCenter {

	private Dispatcher dispatcher = new Dispatcher();

	/**
	 * Operación que permite listar los empleados que estén registrados en el Call
	 * Center
	 * 
	 * @return Lista de empleados
	 */
	@RequestMapping(value = "/empleados", method = RequestMethod.GET)
	public List<Empleado> obtenerEmpleados() {
		return dispatcher.getCallCenter().getEmpleados().values().stream().flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	/**
	 * Operación que permite el registro de un nuevo empleado, a la lista de espera
	 * para su asignación
	 * 
	 * @param formParams
	 *            Json con la información del empleado a registrar.
	 */
	@RequestMapping(value = "/empleados", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void agregarEmpleado(@RequestBody MultiValueMap<String, String> formParams) {
		Empleado e = Utilidades.jsonAObjeto(formParams.getFirst("empleado"), Empleado.class);
		dispatcher.getCallCenter().agregarEmpleado(e);
	}

	/**
	 * Operacion que permite consulta los clientes, que se encuentra en espera de un
	 * operador
	 * 
	 * @return Lista de clientes
	 */
	@RequestMapping(value = "/clientes/espera", method = RequestMethod.GET)
	public List<Cliente> obtenerCliente() {
		return dispatcher.getCallCenter().getClienteEnEspera();
	}

	/**
	 * Operación que permite registra la llamada del cliente.
	 * 
	 * @param formParams
	 *            Json con la información del cliente.
	 */
	@RequestMapping(value = "/llamadas", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void realizarLlamada(@RequestBody MultiValueMap<String, String> formParams) {
		Cliente cliente = Utilidades.jsonAObjeto(formParams.getFirst("cliente"), Cliente.class);
		dispatcher.dispatchCall(cliente);

	}

}
