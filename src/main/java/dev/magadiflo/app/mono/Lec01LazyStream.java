package dev.magadiflo.app.mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class Lec01LazyStream {

    private static final Logger log = LoggerFactory.getLogger(Lec01LazyStream.class);

    public static void main(String[] args) {
        /**
         * Por defecto un Stream de java 8 es Lazy, eso significa que no se ejecutará hasta
         * que se le agregue algún operador terminal, como en nuestro caso el toList().
         * Un concepto similar lo aplicaremos para los publisher Mono y Flux que veremos en
         * las siguientes lecciones.
         */
        Stream.of(1)
                .peek(value -> log.info("recibido: {}", value))
                .toList();
    }
}
