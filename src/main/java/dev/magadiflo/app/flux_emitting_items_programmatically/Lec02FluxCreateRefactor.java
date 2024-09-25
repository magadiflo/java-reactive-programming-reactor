package dev.magadiflo.app.flux_emitting_items_programmatically;

import dev.magadiflo.app.common.Util;
import dev.magadiflo.app.flux_emitting_items_programmatically.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactor {
    public static void main(String[] args) {
        NameGenerator nameGenerator = new NameGenerator();
        Flux<String> stringFlux = Flux.create(nameGenerator);
        stringFlux.subscribe(Util.subscriber());

        for (int i = 0; i < 10; i++) {
            nameGenerator.generate();
        }
    }
}
