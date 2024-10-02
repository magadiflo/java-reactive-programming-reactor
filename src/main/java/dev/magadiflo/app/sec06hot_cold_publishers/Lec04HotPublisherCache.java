package dev.magadiflo.app.sec06hot_cold_publishers;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;


public class Lec04HotPublisherCache {

    private static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    public static void main(String[] args) {
        Flux<Integer> stockFlux = stockStream()
                .replay(2).autoConnect(0);

        Util.sleepSeconds(4);

        log.info("Martín se unió");
        stockFlux.subscribe(Util.subscriber("Martín"));

        Util.sleepSeconds(4);

        log.info("Milagros se unió");
        stockFlux.subscribe(Util.subscriber("Milagros"));

        Util.sleepSeconds(15);
    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(10, 100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("Emitiendo precio: {}", price))
                .cast(Integer.class);
    }
}
