package dev.magadiflo.app.pubsub.subscriber;

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

public class SubscriberImpl implements Subscriber<String> {

    private static final Logger log = LoggerFactory.getLogger(SubscriberImpl.class);
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String email) {
        log.info("recibido: {}", email);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("error", throwable);
    }

    @Override
    public void onComplete() {
        log.info("¡completado!");
    }

    public Subscription getSubscription() {
        return this.subscription;
    }
}
