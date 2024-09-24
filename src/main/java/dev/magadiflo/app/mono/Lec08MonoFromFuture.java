package dev.magadiflo.app.mono;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec08MonoFromFuture {

    private static final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) {
        Mono.fromFuture(Lec08MonoFromFuture::getName)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(1);
    }

    public static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Generando nombre");
            return Util.faker().name().firstName();
        });
    }

}
