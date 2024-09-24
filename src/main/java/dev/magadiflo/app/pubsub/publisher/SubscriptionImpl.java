package dev.magadiflo.app.pubsub.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * En la vida real no tenemos que hacer todas estas cosas. Esto es solo una simple
 * implementación para que entendamos esto y solo para jugar con esto nada más.
 * En otras palabras, la clase PublisherImpl, SubscriberImpl y SubscriptionImpl, solo son
 * clases que creamos para ver cómo es que funciona la programación reactiva con la
 * especificación de Reactive Stream.
 */

public class SubscriptionImpl implements Subscription {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private static final int MAX_ITEMS = 10;
    private final Faker faker;
    private final Subscriber<? super String> subscriber;
    private boolean isCanceled;
    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long requested) {
        if (this.isCanceled) return;
        log.info("Es subscriber ha solicitado {} items", requested);

        if (requested > MAX_ITEMS) {
            this.subscriber.onError(new RuntimeException("Falló la validación"));
            this.isCanceled = true;
            return;
        }
        int i = 0;
        while (i < requested && this.count < MAX_ITEMS) {
            this.count++;
            this.subscriber.onNext(this.faker.internet().emailAddress());
            i++;
        }
        if (this.count == MAX_ITEMS) {
            log.info("No hay más datos para producir");
            this.subscriber.onComplete();
            this.isCanceled = true;
        }
    }

    @Override
    public void cancel() {
        log.info("El subscriber ha cancelado");
        this.isCanceled = true;
    }
}
