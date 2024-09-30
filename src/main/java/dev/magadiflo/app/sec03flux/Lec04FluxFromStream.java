package dev.magadiflo.app.sec03flux;

import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Lec04FluxFromStream {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 3, 4);
        Stream<Integer> integerStream = numbers.stream();
        Flux<Integer> integerFlux = Flux.fromStream(integerStream);
        integerFlux.subscribe(Util.subscriber());

        List<String> letters = List.of("a", "b", "c");
        Flux<String> stringFlux = Flux.fromStream(letters::stream); //() -> letters.stream()
        stringFlux.subscribe(Util.subscriber("sub1"));
        stringFlux.subscribe(Util.subscriber("sub2"));
    }
}
