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

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
    }
}
