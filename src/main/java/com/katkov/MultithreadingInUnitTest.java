package com.katkov;


import org.junit.Assert;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * For http://stackoverflow.com/questions/21743974/tesing-threads-with-junits
 */
public class MultithreadingInUnitTest {
    private Watcher watcher = new DefaultWatcher();

    public void runZipper() {
        exThread czct = new exThread();
        watcher.process(WatchedEvent.EVENT_1);
        czct.start();
    }

    private class exThread extends Thread {
        public void run() {
            try {
                //some business logic which generates a zipfile
            } catch (Exception e) {
            } finally {
                watcher.process(WatchedEvent.EVENT_2);
            }
        }
    }


    public void setWatcher(Watcher watcher) {
        this.watcher = watcher;
    }

    private class DefaultWatcher implements Watcher {
        @Override
        public void process(WatchedEvent event) {
            //Noop for production use
        }
    }
}

interface Watcher {
    public void process(WatchedEvent event);
}

enum WatchedEvent {
    EVENT_1, EVENT_2
}

class JunitTest {
    public void testRunZipper() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event == WatchedEvent.EVENT_2) { latch.countDown(); }
            }
        };
        MultithreadingInUnitTest testEx = new MultithreadingInUnitTest();
        testEx.setWatcher(watcher);

        testEx.runZipper();
        latch.await(10, TimeUnit.SECONDS);
        if (latch.getCount() != 0) { Assert.fail("Latch was never triggered"); }

        //assert statement to check whether zip file is created
    }

}