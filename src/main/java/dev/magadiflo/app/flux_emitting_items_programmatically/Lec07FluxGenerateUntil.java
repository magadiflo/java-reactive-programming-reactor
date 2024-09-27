package dev.magadiflo.app.flux_emitting_items_programmatically;

import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUntil {
    public static void main(String[] args) {
        demo2();
    }

    public static void demo1() {
        Flux.generate(synchronousSink -> {
            String countryName = Util.faker().country().name();
            synchronousSink.next(countryName);
            if (countryName.equalsIgnoreCase("Peru")) {
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());
    }

    public static void demo2() {
        Flux<String> generate = Flux.generate(synchronousSink -> {
            String countryName = Util.faker().country().name();
            synchronousSink.next(countryName);
        });
        generate.takeUntil(countryName -> countryName.equalsIgnoreCase("Peru"))
                .subscribe(Util.subscriber());
    }
}
