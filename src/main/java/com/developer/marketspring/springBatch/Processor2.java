package com.developer.marketspring.springBatch;


import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.logging.Logger;

public class Processor implements ItemProcessor<Cliente1, Cliente1>{

    private static final Logger log = (Logger) LoggerFactory.getLogger(Processor.class);

    @Override
    public Cliente1 process(final Cliente1 cliente) throws Exception {
        final String nombre = cliente.getNombre().toUpperCase();
        final String apellidos  = cliente.getApellidos().toUpperCase();

        final Cliente1 transformedPerson = new Cliente1(nombre, apellidos);

        log.info("Converting (" + cliente + ") into (" + transformedPerson + ")");
        return transformedPerson;
    }
}
