package awe.devikamehra.joke_telling_app;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Devika on 28-09-2016.
 */
public class AsyncTaskTest extends ApplicationTestCase<Application> implements OnJokeReceivedListener {

    CountDownLatch mutex;
    String joke;

    public AsyncTaskTest(){super(Application.class);}

    public void testJoke() {
        try {
            mutex = new CountDownLatch(1);
            new AsyncTask().execute(this);
            mutex.await(8, TimeUnit.SECONDS);
            assertNotNull("Null Joke", joke);
            assertFalse("Empty Joke", joke.isEmpty());
        } catch (Exception ex) {
            fail();
        }
    }

    @Override
    public void onReceived(String joke) {
        this.joke = joke;
        mutex.countDown();
    }
}
