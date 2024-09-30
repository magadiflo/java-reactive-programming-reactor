package dev.magadiflo.app.sec02mono;

import dev.magadiflo.app.common.Util;
import dev.magadiflo.app.sec02mono.assignment.FileService;
import dev.magadiflo.app.sec02mono.assignment.FileServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec12Assigment {
    private static final Logger log = LoggerFactory.getLogger(Lec12Assigment.class);

    public static void main(String[] args) {
        String filename = "file.txt";
        FileService fileService = new FileServiceImpl();

        fileService.write(filename, "Este es mi contenido")
                .subscribe(Util.subscriber());

        fileService.read(filename)
                .subscribe(Util.subscriber());

        fileService.delete(filename)
                .subscribe(Util.subscriber());
    }
}
