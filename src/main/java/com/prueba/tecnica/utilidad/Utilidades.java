package com.prueba.tecnica.utilidad;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clases de funciones utilitarias
 * 
 * @author Oscar Alejandro Gómez Ramírez
 *
 */
public class Utilidades {

	private static final Logger logger = LogManager.getLogger("com.prueba.tecnica.utilidad.Utilidades");

	/**
	 * Método que permite convertir un Json a un Objeto
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static final <T> T jsonAObjeto(String json, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * método que generar un numero aleatorio entre un rango.
	 * 
	 * @param min
	 *            Rango inferior
	 * @param max
	 *            Rango Superior
	 * @return Numero aleatorio
	 */
	public static int random(int min, int max) {
		Random r = new Random();
		return r.nextInt(max - min) + min;
	}
}
