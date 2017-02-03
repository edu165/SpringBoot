package es.salesianos.edu.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import es.salesianos.edu.model.Author;

@Service
public class SimulacroService {

	private static final Logger logger = LogManager.getLogger(SimulacroService.class.getName());

	public boolean insert(Author author) {
		logger.debug("simulando insercion");
		return true;
	}

}
