package dev.magadiflo.app.sec05operators;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec06ErrorHandling {

    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {
        onErrorReturn2();
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
}
