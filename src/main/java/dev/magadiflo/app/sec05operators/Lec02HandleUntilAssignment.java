package dev.magadiflo.app.sec05operators;

import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleUntilAssignment {
    public static void main(String[] args) {
        Flux<String> generate = Flux.generate(synchronousSink -> {
            synchronousSink.next(Util.faker().country().name());
        });

        Flux<String> handle = generate.handle((countryName, synchronousSink) -> {
            synchronousSink.next(countryName);
            if (countryName.equalsIgnoreCase("Peru")) {
                synchronousSink.complete();
            }
        });

        handle.subscribe(Util.subscriber());
    }
}
