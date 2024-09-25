package dev.magadiflo.app.flux;


import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

public class Lec06Log {
    public static void main(String[] args) {
        Flux.range(1,5)
                .log()
                .map(value -> value * 10)
                .log("transforma")
                .subscribe(Util.subscriber());
    }
}
