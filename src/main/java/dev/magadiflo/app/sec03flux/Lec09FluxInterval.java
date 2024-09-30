package dev.magadiflo.app.sec03flux;


import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09FluxInterval {
    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1))
                .take(3)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
