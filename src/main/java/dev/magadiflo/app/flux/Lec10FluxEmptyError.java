package dev.magadiflo.app.flux;


import dev.magadiflo.app.common.Util;
import reactor.core.publisher.Flux;

public class Lec10FluxEmptyError {
    public static void main(String[] args) {
        /**
         * Crea un Flux que se completa sin emitir ningún elemento.
         */
        Flux.empty()
                .subscribe(Util.subscriber());

        /**
         * Crea un Flux que finaliza con el error especificado inmediatamente después de suscribirte.
         */
        Flux.error(new RuntimeException("Ocurrió un error"))
                .subscribe(Util.subscriber());
    }
}
