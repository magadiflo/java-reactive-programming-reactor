package dev.magadiflo.app.sec05operators;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandling {

    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {
        onErrorResume3();
    }

    private static void onErrorReturn1() {
        Flux.range(1, 10)
                .map(number -> number == 5 ? 5 / 0 : number) //intencional
                .onErrorReturn(-1)
                .subscribe(Util.subscriber());
    }

    private static void onErrorReturn2() {
        Flux.range(1, 10)
                .map(number -> number == 5 ? 5 / 0 : number) //intencional
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }

    // Imaginemos que es un servicio alternativo
    private static Mono<Integer> fallback1() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 100));
    }

    private static Flux<Integer> fallback2() {
        return Flux.range(50, 5);
    }

    private static Flux<Integer> fallback3() {
        return Flux.error(new IllegalArgumentException("Ocurrió un error"));
    }

    private static void onErrorResume1() {
        Flux.range(1, 10)
                .map(number -> number == 5 ? 5 / 0 : number) //intencional
                .onErrorResume(throwable -> fallback1())
                .subscribe(Util.subscriber());
    }

    private static void onErrorResume2() {
        Flux.range(1, 10)
                .map(number -> number == 5 ? 5 / 0 : number) //intencional
                .onErrorResume(throwable -> fallback2())
                .subscribe(Util.subscriber());
    }

    private static void onErrorResume3() {
        Flux.error(new RuntimeException("Oops"))
                .onErrorResume(ArithmeticException.class, e -> fallback1())
                .onErrorResume(throwable -> fallback3())
                .onErrorReturn(-5)
                .subscribe(Util.subscriber());
    }
}
