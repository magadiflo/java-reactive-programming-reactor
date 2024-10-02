package dev.magadiflo.app.sec06hot_cold_publishers;

import dev.magadiflo.app.common.Util;
import dev.magadiflo.app.sec04flux_emitting_items_programmatically.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec05FluxCreateIssueFix {
    public static void main(String[] args) {
        NameGenerator nameGenerator = new NameGenerator();
        Flux<String> stringFlux = Flux.create(nameGenerator).share();
        stringFlux.subscribe(Util.subscriber("A"));
        stringFlux.subscribe(Util.subscriber("B"));

        for (int i = 0; i < 10; i++) {
            nameGenerator.generate();
        }
    }
}
