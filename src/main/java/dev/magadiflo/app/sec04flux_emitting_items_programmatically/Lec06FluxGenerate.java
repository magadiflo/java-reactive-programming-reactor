package dev.magadiflo.app.sec04flux_emitting_items_programmatically;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {

    private static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);

    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
                    synchronousSink.next(1);
                    synchronousSink.complete();
                })
                .subscribe(Util.subscriber());
    }
}
