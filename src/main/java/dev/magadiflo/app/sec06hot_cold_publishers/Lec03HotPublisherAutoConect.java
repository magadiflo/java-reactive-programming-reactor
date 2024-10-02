package dev.magadiflo.app.sec06hot_cold_publishers;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;


public class Lec03HotPublisherAutoConect {

    private static final Logger log = LoggerFactory.getLogger(Lec03HotPublisherAutoConect.class);

    public static void main(String[] args) {
        Flux<String> stringFlux = movieStream()
                .publish().autoConnect(0); //Con el valor 0, empezará a emitir datos sin esperar a que haya algún subscriptor suscrito.

        Util.sleepSeconds(15);
    }

    private static Flux<String> movieStream() {
        return Flux.generate(
                        () -> {
                            log.info("Solicitud recibida");
                            return 1;
                        }, (number, synchronousSink) -> {
                            String scene = "movie scene " + number;
                            log.info("playing {}", scene);
                            synchronousSink.next(scene);
                            return ++number;
                        },
                        number -> {
                            log.info("Último valor: {}", number);
                        })
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
