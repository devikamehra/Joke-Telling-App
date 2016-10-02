/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package awe.devikamehra.joke_telling_app.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import awe.devika.mehra.joketellingapp.Joker;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.joke_telling_app.devikamehra.awe",
    ownerName = "backend.joke_telling_app.devikamehra.awe",
    packagePath=""
  )
)
public class MyEndpoint {
    /**
     * A simple endpoint method that tells a joke
     */
    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke() {
        MyBean response = new MyBean();
        Joker joker = new Joker();
        response.setData(joker.getJoke());
        return response;
    }

}
