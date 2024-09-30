package dev.magadiflo.app.sec03flux;

import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {
    public static void main(String[] args) {
        Flux<Integer> range = Flux.range(1, 10);
        range.subscribe(Util.subscriber());

        // Generamos 10 nombres aleatorios usando la librería Faker
        Flux<String> names = Flux.range(1, 10)
                .map(value -> Util.faker().name().firstName());
        names.subscribe(Util.subscriber());
    }
}
