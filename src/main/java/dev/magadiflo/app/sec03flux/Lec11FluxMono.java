package dev.magadiflo.app.sec03flux;


import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec11FluxMono {
    public static void main(String[] args) {
        fluxToMono();
    }

    private static void fluxToMono() {
        Flux<Integer> range = Flux.range(1, 10);
        Mono<Integer> next = Mono.from(range); //range.next();
        next.subscribe(Util.subscriber()); // recibido: 1
    }

    private static void monoToFlux() {
        Mono<String> stringMono = getUsername(1);
        save(Flux.from(stringMono));
    }

    public static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Martín");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Entrada inválida"));
        };
    }

    private static void save(Flux<String> flux) {
        flux.subscribe(Util.subscriber());
    }
}
