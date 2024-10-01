package dev.magadiflo.app.sec05operators;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Lec10Transform {

    private static final Logger log = LoggerFactory.getLogger(Lec10Transform.class);

    record Customer(int id, String name) {
    }

    record PurchaseOrder(String productName, int price, int quantity) {
    }

    public static void main(String[] args) {
        boolean isDebugEnabled = false;
        getCustomers()
                .transform(isDebugEnabled ? addDebugger() : Function.identity())
                .subscribe();

        getPurchaseOrders()
                .transform(addDebugger())
                .subscribe();
    }

    private static Flux<Customer> getCustomers() {
        return Flux.range(1, 3)
                .map(number -> new Customer(number, Util.faker().name().firstName()));
    }

    private static Flux<PurchaseOrder> getPurchaseOrders() {
        return Flux.range(1, 3)
                .map(number -> new PurchaseOrder(Util.faker().commerce().productName(), number, number * 10));
    }

    /**
     * Obtenemos un flujo, añadimos los operadores y devolvemos el flujo
     */
    private static <T> UnaryOperator<Flux<T>> addDebugger() {
        return flux -> flux
                .doOnNext(value -> log.info("recibido: {}", value))
                .doOnError(throwable -> log.error("error", throwable))
                .doOnComplete(() -> log.info("Completado"));
    }

    private static <T> Function<Flux<T>, Flux<T>> addDebugger2() {
        return flux -> flux
                .doOnNext(value -> log.info("recibido2: {}", value))
                .doOnError(throwable -> log.error("error2", throwable))
                .doOnComplete(() -> log.info("Completado2"));
    }
}
