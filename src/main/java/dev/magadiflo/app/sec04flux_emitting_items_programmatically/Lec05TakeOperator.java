package dev.magadiflo.app.sec04flux_emitting_items_programmatically;

import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

public class Lec05TakeOperator {
    public static void main(String[] args) {
        takeUntil();
    }

    private static void take() {
        Flux.range(1, 10)
                .log("take")
                .take(3)
                .log("subs")
                .subscribe(Util.subscriber());
    }

    private static void takeWhile() {
        Flux.range(1, 10)
                .log("take")
                .takeWhile(i -> i < 2)
                .log("subs")
                .subscribe(Util.subscriber());
    }

    private static void takeUntil() {
        Flux.range(1, 10)
                .log("take")
                .takeUntil(i -> i == 2) // Se detiene cuando se cumple la condición + permitir el último elemento
                .log("subs")
                .subscribe(Util.subscriber());
    }
}
