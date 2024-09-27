package dev.magadiflo.app.flux_emitting_items_programmatically;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec08GenerateWithState {

    private static final Logger log = LoggerFactory.getLogger(Lec08GenerateWithState.class);

    public static void main(String[] args) {
        Flux.generate(
                () -> 0,
                (counter, synchronousSink) -> {
                    String countryName = Util.faker().country().name();
                    synchronousSink.next(countryName);
                    counter++;
                    if (counter == 10 || countryName.equalsIgnoreCase("Peru")) {
                        synchronousSink.complete();
                    }
                    return counter;
                },
                count -> log.info("Último valor del contador: {}", count)
        ).subscribe(Util.subscriber());
    }
}
