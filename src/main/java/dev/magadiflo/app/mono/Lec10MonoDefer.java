package dev.magadiflo.app.mono;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec10MonoDefer {

    private static final Logger log = LoggerFactory.getLogger(Lec10MonoDefer.class);

    public static void main(String[] args) {
        // Simulando un estado que cambia
        int[] counter = {0};

        Mono<Integer> mono = Mono.defer(() -> {
            // Incrementando el contador cada vez que se suscribe
            counter[0]++;
            return Mono.just(counter[0]);
        });

        // Suscribiéndonos dos veces para ver el comportamiento
        mono.subscribe(Util.subscriber("1° suscripción")); // Imprimirá 1
        mono.subscribe(Util.subscriber("2° suscripción")); // Imprimirá 2
    }

}
