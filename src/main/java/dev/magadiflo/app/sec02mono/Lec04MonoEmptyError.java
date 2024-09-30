package dev.magadiflo.app.sec02mono;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {

    private static final Logger log = LoggerFactory.getLogger(Lec04MonoEmptyError.class);

    public static void main(String[] args) {
        getUsername(1)
                .subscribe(Util.subscriber());
    }

    public static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Martín");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Entrada inválida"));
        };
    }
}
