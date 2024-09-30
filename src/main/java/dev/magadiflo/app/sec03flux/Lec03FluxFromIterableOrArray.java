package dev.magadiflo.app.sec03flux;

import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromIterableOrArray {
    public static void main(String[] args) {
        List<String> letters = List.of("a", "b", "c", "d");
        Flux<String> stringFlux = Flux.fromIterable(letters);
        stringFlux.subscribe(Util.subscriber());

        Integer[] numbers = {1, 2, 3, 4};
        Flux<Integer> integerFlux = Flux.fromArray(numbers);
        integerFlux.subscribe(Util.subscriber());
    }
}
