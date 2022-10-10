package net.erik_n;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        Globals.init();
        Db db = Db.impl();
        for(Path path: db.listRootPaths()) System.out.println(path);
    }
}