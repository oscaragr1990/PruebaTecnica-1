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
import com.prueba.tecnica.utilidad.Utilidad;

@RestController
@RequestMapping(value = "/callcenter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

public class ServicioCallCenter {

	private Dispatcher dispatcher = new Dispatcher();

	@RequestMapping(value = "/empleados", method = RequestMethod.GET)
	public List<Empleado> obtenerEmpleados() {
		return dispatcher.getCallCenter().getEmpleados().values().stream().flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@RequestMapping(value = "/empleados", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void agregarEmpleado(@RequestBody MultiValueMap<String, String> formParams) {
		Empleado e = Utilidad.jsonAObjeto(formParams.getFirst("empleado"), Empleado.class);
		dispatcher.getCallCenter().agregarEmpleado(e);
	}

	@RequestMapping(value = "/clientes/espera", method = RequestMethod.GET)
	public List<Cliente> obtenerCliente() {
		return dispatcher.getCallCenter().getClienteEnEspera();
	}

	@RequestMapping(value = "/llamadas", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void realizarLlamada(@RequestBody MultiValueMap<String, String> formParams) {
		Cliente cliente = Utilidad.jsonAObjeto(formParams.getFirst("cliente"), Cliente.class);
		dispatcher.dispatchCall(cliente);

	}

}
