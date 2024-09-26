package dev.magadiflo.app.flux_emitting_items_programmatically;

import dev.magadiflo.app.common.Util;
import dev.magadiflo.app.flux_emitting_items_programmatically.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Lec03FluxSinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);

    public static void main(String[] args) {
//        noThreadSafety();
        threadSafety();
    }

    private static void noThreadSafety() {
        List<Integer> list = new ArrayList<>();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };
        // Lanzamos 10 hilos
        for (int i = 0; i < 10; i++) {
            //new Thread(runnable).start(); //java 17
            Thread.ofPlatform().start(runnable); //java 21
        }
        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());
    }

    public static void threadSafety() {
        List<String> list = new ArrayList<>();
        NameGenerator nameGenerator = new NameGenerator();
        Flux<String> stringFlux = Flux.create(nameGenerator);
        stringFlux.subscribe(list::add);

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                nameGenerator.generate();
            }
        };
        // Lanzamos 10 hilos
        for (int i = 0; i < 10; i++) {
            //new Thread(runnable).start(); //java 17
            Thread.ofPlatform().start(runnable); //java 21
        }
        Util.sleepSeconds(3);
        log.info("FluxSink. list size: {}", list.size());
    }
}
