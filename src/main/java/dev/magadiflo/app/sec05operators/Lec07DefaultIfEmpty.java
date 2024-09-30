package dev.magadiflo.app.sec05operators;

import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

public class Lec07DefaultIfEmpty {
    public static void main(String[] args) {
        Flux.empty()
                .defaultIfEmpty("fallback")
                .subscribe(Util.subscriber());
    }
}
