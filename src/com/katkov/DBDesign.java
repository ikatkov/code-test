package com.katkov;

import java.util.HashSet;
import java.util.Set;

/**
 * @author katkovi
 */
public class DBDesign {


    private interface Server {
        public String getFriendlyName();

        public String getMachineNameName();

        public Set<Database> getDatabases();

        public void createDatabase(String name);
    }

    private class ProdServer implements Server {
        private StuffThatLogfileNeeds stuffThatLogfileNeeds;
        private HashSet<Database> dbs = new HashSet<Database>();

        private ProdServer() {
            stuffThatLogfileNeeds = new StuffThatLogfileNeeds("bla bla");
        }

        @Override
        public String getFriendlyName() {
            throw new RuntimeException("Not implemented yet");
        }

        @Override
        public String getMachineNameName() {
            throw new RuntimeException("Not implemented yet");
        }

        @Override
        public Set<Database> getDatabases() {
            return new HashSet<Database>(dbs);
        }

        @Override
        public void createDatabase(String name) {
            Database database = new Database(name, stuffThatLogfileNeeds);
            dbs.add(database);
        }
    }

    private class Database {
        private String name;
        private StuffThatLogfileNeeds stuffThatLogfileNeeds;

        public Database(String name, StuffThatLogfileNeeds stuffThatLogfileNeeds) {
            this.name = name;
            this.stuffThatLogfileNeeds = stuffThatLogfileNeeds;
        }

        public String getName() {
            throw new RuntimeException("Not implemented yet");
        }
    }

    private class StuffThatLogfileNeeds {
        private String foo;

        private StuffThatLogfileNeeds(String foo) {
            this.foo = foo;
        }

        public String getFoo() {
            return foo;
        }
    }
}
