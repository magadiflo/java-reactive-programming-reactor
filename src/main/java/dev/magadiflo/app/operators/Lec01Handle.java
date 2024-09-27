package dev.magadiflo.app.operators;

import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

/**
 * Con el operador .handle() cumplamos los siguientes criterios
 * 1 => -2
 * 4 => no enviarlo
 * 7 => error
 * en los otros casos => enviarlos
 */

public class Lec01Handle {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .filter(i -> i != 7)
                .handle((item, synchronousSink) -> {
                    switch (item) {
                        case 1 -> synchronousSink.next(-2);
                        case 4 -> {
                        }
                        case 7 -> synchronousSink.error(new RuntimeException("Valor 7, lanzando error"));
                        default -> synchronousSink.next(item);
                    }
                })
                .subscribe(Util.subscriber());
    }
}
