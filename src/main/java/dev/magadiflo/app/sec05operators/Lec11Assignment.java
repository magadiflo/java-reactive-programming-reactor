package dev.magadiflo.app.sec05operators;

import dev.magadiflo.app.common.Util;
import dev.magadiflo.app.sec05operators.assignment.ProductService;
import reactor.core.publisher.Mono;

public class Lec11Assignment {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Mono<String> productName = productService.getProductName(1L);
        productName.subscribe(Util.subscriber());

        Util.sleepSeconds(15);
    }
}
