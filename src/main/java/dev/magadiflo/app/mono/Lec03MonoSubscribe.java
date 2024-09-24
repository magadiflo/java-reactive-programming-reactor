package dev.magadiflo.app.mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

    private static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just(1);
        mono.subscribe(
                value -> log.info("recibido: {}", value),
                error -> log.info("error: {}", error.getMessage()),
                () -> log.info("¡completado!")
        );
    }
}
