package dev.magadiflo.app.sec03flux;

import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {
    public static void main(String[] args) {
        Flux.just(1, 2, 3, 4)
                .subscribe(Util.subscriber());
    }
}
