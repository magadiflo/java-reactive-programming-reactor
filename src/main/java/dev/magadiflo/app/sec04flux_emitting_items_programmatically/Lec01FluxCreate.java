package dev.magadiflo.app.sec04flux_emitting_items_programmatically;

import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {
    public static void main(String[] args) {
        /**
         * A veces podemos tener un requisito donde nos pidan seguir haciendo algo
         * hasta que se cumpla cierta condición.
         */
        Flux<String> nameFlux = Flux.create(fluxSink -> {
            String country;
            do {
                country = Util.faker().country().name();
                fluxSink.next(country);
            } while (!country.equalsIgnoreCase("Peru"));
            fluxSink.complete();
        });

        nameFlux.subscribe(Util.subscriber("A"));
        nameFlux.subscribe(Util.subscriber("B"));
    }
}
