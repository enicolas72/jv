package net.erik_n;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DbImpl_Sqlite implements Db {

    Connection con;

    DbImpl_Sqlite() {

        Path sqlFile = Globals.DATA_PATH.resolve("database.sqlite");
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + sqlFile);
            Globals.log("SQLite database opened: " + sqlFile);
        } catch (Exception e) {
            Globals.err("Cannot open SQLITE connection", e);
        }

        createTables();
    }

    @Override public void addRootPath(Path path) {
        final String sql = "" +
                "INSERT INTO t_root_paths (root_path) VALUES(?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, path.toString());
            stmt.execute();
        } catch(Throwable e) {
            Globals.err("DB: Cannot add a root path", e);
        }
    }

    @Override public Collection<Path> listRootPaths() {
        final String sql = "" +
                "SELECT root_path FROM t_root_paths";
        try {
            ArrayList<Path> result = new ArrayList<>();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Path path = Paths.get(rs.getString(1));
                result.add(path);
            }
            return result;

        } catch(Throwable e) {
            Globals.err("DB: Cannot list root paths", e);
            return null;
        }
    }

    private void createTables() {
        final String sql = "" +
                "CREATE TABLE IF NOT EXISTS t_root_paths (" +
                "    pk_id INTEGER PRIMARY KEY," +
                "    root_path TEXT NOT NULL" +
                ")";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.execute();
        } catch(Throwable e) {
            Globals.err("DB: Cannot create tables", e);
        }
    }
}
