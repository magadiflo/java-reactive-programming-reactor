package dev.magadiflo.app.flux_emitting_items_programmatically;

import dev.magadiflo.app.common.Util;
import dev.magadiflo.app.flux_emitting_items_programmatically.assignment.FileReaderService;
import dev.magadiflo.app.flux_emitting_items_programmatically.assignment.FileReaderServiceImpl;

import java.io.IOException;
import java.nio.file.Path;

public class Lec09Assignment {
    public static void main(String[] args) throws IOException {
        /**
         * Assignment
         * - Realizar el trabajo solo cuando se le suscriba.
         * - Realizar el trabajo en función de la demanda.
         * - Dejar de producir cuando el suscriptor cancele.
         * - Producir solo los elementos solicitados.
         * - El archivo debe cerrarse una vez realizado.
         */
        Path path = Path.of("src/main/resources/sec04/file.txt");
        FileReaderService fileReaderService = new FileReaderServiceImpl();

        fileReaderService.read(path)
                .take(5)
                .subscribe(Util.subscriber());


    }
}
