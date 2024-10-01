package dev.magadiflo.app.sec05operators.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private static final boolean IS_EMPTY = false;

    public Mono<String> getProductName(Long productId) {
        return this.getProductsDefault()
                .switchIfEmpty(getProductsTimeoutEmpty())
                .timeout(Duration.ofSeconds(2), getProductsTimeoutFallback())
                .doOnNext(product -> log.info("product: {}", product))
                .filter(product -> product.id().equals(productId))
                .next()
                .map(Product::name);
    }

    private Flux<Product> getProductsDefault() {
        return IS_EMPTY ?
                Flux.empty() :
                Flux.just(
                                new Product(1L, "Monitor", 15.50),
                                new Product(2L, "Teclado", 19.90),
                                new Product(3L, "Mouse", 65.50),
                                new Product(4L, "UPS", 295.80)
                        )
                        .delayElements(Duration.ofSeconds(4));
    }

    public Flux<Product> getProductsTimeoutFallback() {
        return Flux.just(
                new Product(1L, "Monitor (timeoutFallback)", 15.50),
                new Product(2L, "Teclado (timeoutFallback)", 19.90),
                new Product(3L, "Mouse (timeoutFallback)", 65.50),
                new Product(4L, "UPS (timeoutFallback)", 295.80)
        );
    }

    public Flux<Product> getProductsTimeoutEmpty() {
        return Flux.just(
                new Product(1L, "Monitor (timeoutEmpty)", 15.50),
                new Product(2L, "Teclado (timeoutEmpty)", 19.90),
                new Product(3L, "Mouse (timeoutEmpty)", 65.50),
                new Product(4L, "UPS (timeoutEmpty)", 295.80)
        );
    }

}
