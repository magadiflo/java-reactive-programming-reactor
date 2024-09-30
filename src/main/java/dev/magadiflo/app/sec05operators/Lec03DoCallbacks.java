package dev.magadiflo.app.sec05operators;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * do Hooks/Callbacks
 */
public class Lec03DoCallbacks {

    private static final Logger log = LoggerFactory.getLogger(Lec03DoCallbacks.class);

    public static void main(String[] args) {
        Flux.<Integer>create(fluxSink -> {
                    log.info("Inicia el productor");
                    for (int i = 0; i < 4; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
//                     fluxSink.error(new RuntimeException("oops"));
                    log.info("Finaliza el productor");
                })
                .doOnComplete(() -> log.info("doOnComplete"))
                .doFirst(() -> log.info("doFirst"))
                .doOnNext(item -> log.info("doOnNext: {}", item))
                .doOnSubscribe(subscription -> log.info("doOnSubscribe: {}", subscription))
                .doOnRequest(request -> log.info("doOnRequest: {}", request))
                .doOnError(error -> log.info("doOnError: {}", error.getMessage()))
                .doOnTerminate(() -> log.info("doOnTerminate")) // complete or error case
                .doOnCancel(() -> log.info("doOnCancel"))
                .doOnDiscard(Object.class, o -> log.info("doOnDiscard: {}", o))
                .doFinally(signal -> log.info("doFinally: {}", signal)) // finally irrespective of the reason
                .subscribe(Util.subscriber("subscriber"));
    }
}
