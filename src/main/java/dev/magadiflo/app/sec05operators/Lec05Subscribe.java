package dev.magadiflo.app.sec05operators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec05Subscribe {

    private static final Logger log = LoggerFactory.getLogger(Lec05Subscribe.class);

    public static void main(String[] args) {
        Flux.range(1, 10)
                .doOnNext(number -> log.info("recibido: {}", number))
                .doOnError(err -> log.error("error", err))
                .doOnComplete(() -> log.info("Completado"))
                .subscribe();
    }
}
