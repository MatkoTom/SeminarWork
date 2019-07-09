package hr.tomljanovic.matko.seminarskirad;

import java.util.List;

import hr.tomljanovic.matko.seminarskirad.model.Feed;
import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HackerNewsAPI {

    @GET("v0/topstories.json?print=pretty")
    Flowable<List<Integer>> getStories();

    @GET("v0/item/{id}.json?print=pretty")
    Flowable<Feed> getFeed(@Path("id") int idStory);
}
