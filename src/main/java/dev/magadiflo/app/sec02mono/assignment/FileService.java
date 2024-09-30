package dev.magadiflo.app.sec02mono.assignment;

import reactor.core.publisher.Mono;

public interface FileService {
    Mono<String> read(String fileName);

    Mono<Void> write(String fileName, String content);

    Mono<Void> delete(String fileName);
}
