package dev.magadiflo.app.sec06hot_cold_publishers;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;


public class Lec02HotPublisher {

    private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {
        Flux<String> stringFlux = movieStream()
                .share();

        //Imaginemos que luego de 2 segundos una persona empieza a ver la película
        Util.sleepSeconds(2);
        stringFlux
                .take(4)
                .subscribe(Util.subscriber("Martín"));

        //3 minutos después se une otra persona
        Util.sleepSeconds(3);
        stringFlux
                .take(1)
                .subscribe(Util.subscriber("Milagros"));


        //Detenemos el hilo principal dado que estamos usando retraso en la emisión de elementos delayElements()
        Util.sleepSeconds(15);
    }

    // Imaginemos que es Netflix donde los usuarios pueden ver las mismas películas
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
                .take(5)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
