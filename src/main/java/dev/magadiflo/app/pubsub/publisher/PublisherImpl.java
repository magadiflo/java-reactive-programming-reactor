package dev.magadiflo.app.pubsub.publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/**
 * En la vida real no tenemos que hacer todas estas cosas. Esto es solo una simple
 * implementación para que entendamos esto y solo para jugar con esto nada más.
 * En otras palabras, la clase PublisherImpl, SubscriberImpl y SubscriptionImpl, solo son
 * clases que creamos para ver cómo es que funciona la programación reactiva con la
 * especificación de Reactive Stream.
 */

public class PublisherImpl implements Publisher<String> {

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        SubscriptionImpl subscription = new SubscriptionImpl(subscriber);
        subscriber.onSubscribe(subscription);
    }
}
