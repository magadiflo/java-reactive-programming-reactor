package dev.magadiflo.app.sec07_opt_threading_schedulers;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03MultipleSubscribeOn {

    private static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscribeOn.class);

    public static void main(String[] args) {
        Flux<Integer> integerFlux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generando: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .subscribeOn(Schedulers.parallel()) // El más cercano a la fuente acabará haciendo todo el trabajo
                .cast(Integer.class)
                .doOnNext(number -> log.info("value: {}", number));

        integerFlux
                .doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"))
                .doFirst(() -> log.info("first3"))
                .doFirst(() -> log.info("first4"))
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(2);
    }
}
