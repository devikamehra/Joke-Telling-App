package awe.devikamehra.joke_telling_app;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import awe.devikamehra.joke_telling_app.backend.myApi.MyApi;

/**
 * Created by Devika on 27-09-2016.
 */
public class AsyncTask extends android.os.AsyncTask<OnJokeReceivedListener, Void, String>{
    private static MyApi myApi = null;
    private OnJokeReceivedListener listener;

    @Override
    protected String doInBackground(OnJokeReceivedListener... onJokeReceivedListeners) {
        if(myApi == null) {
            myApi = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://joke-telling-app.appspot.com/_ah/api/").build();
        }

        listener = onJokeReceivedListeners[0];

        try {
            return myApi.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onReceived(result);
    }
}
