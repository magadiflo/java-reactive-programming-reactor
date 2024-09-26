package dev.magadiflo.app.flux_emitting_items_programmatically;

import dev.magadiflo.app.common.Util;
import dev.magadiflo.app.pubsub.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * Flux Create: Comportamiento por defecto
 * ***************************************
 * <p>
 * ¡Flux Create NO verifica la demanda descendente de manera predeterminada! ¡Es así por diseño!
 * <p>
 * Por defecto FluxSink no espera a que el suscriptor le solicite, por lo que seguirá produciendo los
 * elementos y podemos almacenarlos dentro de la cola si queremos, y el suscriptor lo obtendrá de
 * la cola
 */
public class Lec04FluxCreateDownStreamDemand {

    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownStreamDemand.class);

    public static void main(String[] args) {
        produceOnDemand();
//        produceEarly();
    }

    // Genera los valores, solo si se le solicita
    private static void produceOnDemand() {
        SubscriberImpl subscriber = new SubscriberImpl();

        Flux<String> fluxCreate = Flux.create(fluxSink -> {
            fluxSink.onRequest(value -> {
                for (int i = 0; i < value && !fluxSink.isCancelled(); i++) {
                    String name = Util.faker().name().firstName();
                    log.info("[onDemand] Generado: {}", name);
                    fluxSink.next(name);
                }
            });
        });

        fluxCreate.subscribe(subscriber);
        subscriber.getSubscription().request(2);
        subscriber.getSubscription().request(2);
        subscriber.getSubscription().cancel();
        // Aquí ya no recibirá elementos, dado que fue cancelado en la lína anterior
        subscriber.getSubscription().request(2);
    }

    // Genera los valores temprano, sin que alguien le haya solicitado, símplemente
    // genera los valores y los almacena en una cola
    private static void produceEarly() {
        SubscriberImpl subscriber = new SubscriberImpl();

        Flux<String> fluxCreate = Flux.create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                String name = Util.faker().name().firstName();
                log.info("Generado: {}", name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        });

        fluxCreate.subscribe(subscriber);

        // Pasado 2 segundos, se le solicita 2 valores que ya tiene almacenado
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
    }
}
