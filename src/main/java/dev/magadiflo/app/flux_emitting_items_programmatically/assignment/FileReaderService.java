package dev.magadiflo.app.flux_emitting_items_programmatically.assignment;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Path;

public interface FileReaderService {
    Flux<String> read(Path path) throws IOException;
}
