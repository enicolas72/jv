package net.erik_n;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Globals {

    static final String APP_ID = "net.erik_n.jv";
    static final Path DATA_PATH = Paths.get(System.getenv("HOME")).resolve("Library/Application Support/").resolve(APP_ID);

    static void init() {
        // create the data folder
        try {
            Files.createDirectories(DATA_PATH);
            log("Data Folder: " + DATA_PATH);
        } catch(IOException e) {
            err("Cannot create folder " + DATA_PATH, e);
        }
    }

    static void log(String message) {
        System.out.println(message);
    }

    static void err(String message, Throwable e) {
        System.err.println(message);
        e.printStackTrace(System.err);
    }
}
