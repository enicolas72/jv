package net.erik_n;

import java.nio.file.Path;
import java.util.Collection;

public interface Db {

    static Db impl() { return new DbImpl_Sqlite();  }

    void addRootPath(Path path);
    Collection<Path> listRootPaths();

}
