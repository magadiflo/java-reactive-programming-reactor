package dev.magadiflo.app.sec04flux_emitting_items_programmatically.helper;

import dev.magadiflo.app.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGenerator implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = stringFluxSink;
    }

    public void generate() {
        this.fluxSink.next(Util.faker().name().firstName());
    }
}
