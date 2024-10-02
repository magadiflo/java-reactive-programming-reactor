package dev.magadiflo.app.sec07_opt_threading_schedulers;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec01DefaultBehaviorDemo {

    private static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehaviorDemo.class);

    public static void main(String[] args) {
        Flux<Integer> integerFlux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generando: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .cast(Integer.class)
                .doOnNext(number -> log.info("value: {}", number));

        //dentro del runnable nos estamos suscribiendo
        Runnable runnable = () -> integerFlux.subscribe(Util.subscriber("sub1"));

        // Crea un hilo, le pasa el runnable y lo inicia
        Thread.ofPlatform().start(runnable);
    }
}
