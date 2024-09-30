package dev.magadiflo.app.sec05operators;

import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04Delay {
    public static void main(String[] args) {
        /**
         * El Delay Element se aplicará en un hilo separado
         */
        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        /**
         * Bloqueamos el hilo principal para ver los valores que se están emitiendo
         */
        Util.sleepSeconds(11);
    }
}
