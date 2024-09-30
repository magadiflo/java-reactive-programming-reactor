package dev.magadiflo.app.sec05operators;

import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

public class Lec08SwitchIfEmpty {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .filter(number -> number > 11)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> fallback() {
        return Flux.range(100, 3);
    }
}
