package dev.magadiflo.app.sec02mono;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec04CreatingDefaultSubscriber {

    private static final Logger log = LoggerFactory.getLogger(Lec04CreatingDefaultSubscriber.class);

    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just(1);
        mono.subscribe(Util.subscriber());
    }
}
