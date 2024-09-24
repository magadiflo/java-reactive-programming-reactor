package dev.magadiflo.app.mono;

import dev.magadiflo.app.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06MonoFromCallable {

    /**
     * Diferencia entre Supplier y Callable
     * ************************************
     * Ambos son del tipo @FunctionalInterface.
     * <p>
     * La diferencia entre el Supplier y el Callable, es que el Supplier no lanzará excepción, o no tiene
     * la excepción como parte de la firma del método. Puede lanzar una excepción en tiempo de ejecución, pero no
     * tiene la excepción comprobada (checked). Si ingresamos dentro del Supplier podremos ver algo así:
     * T get();
     * <p>
     * El Callable, tiene la excepción throws como parte de la firma del método. Si ingresamos dentro del Callable
     * podremos ver algo así: V call() throws Exception;
     */

    private static final Logger log = LoggerFactory.getLogger(Lec06MonoFromCallable.class);

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3);
        Mono.fromCallable(() -> sum(list))
                .subscribe(Util.subscriber());

        /*
        Si por ejemplo, en vez del fromCallable se usa fromSupplier, habría que manejar la excepción interna si es que
        el método usado (sum()) está lanzando una excepción, ya que como vimos, por defecto la interfaz
        funcional Supplier, su método T get() no lanza ningúna excepción.
        */
        /*
        Mono.fromSupplier(() -> {
            try {
                return sum(list);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        */
    }

    private static int sum(List<Integer> list) throws Exception {
        log.info("Encontrando la suma de {}", list);
        return list.stream().mapToInt(value -> value).sum();
    }
}
