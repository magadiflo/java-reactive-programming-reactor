package dev.magadiflo.app.publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class PublisherImpl implements Publisher<String> {

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        SubscriptionImpl subscription = new SubscriptionImpl(subscriber);
        subscriber.onSubscribe(subscription);
    }
}
