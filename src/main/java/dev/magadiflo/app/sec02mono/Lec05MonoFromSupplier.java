package dev.magadiflo.app.sec02mono;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromSupplier {

    private static final Logger log = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3);
        Mono.fromSupplier(() -> sum(list))
                .subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> list) {
        log.info("Encontrando la suma de {}", list);
        return list.stream().mapToInt(value -> value).sum();
    }
}
