package hr.tomljanovic.matko.seminarskirad;

import java.util.List;

import hr.tomljanovic.matko.seminarskirad.model.Feed;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HackerNewsAPI {

    @GET("v0/topstories.json?print=pretty")
    Call<List<Integer>> getStories();

    @GET("v0/item/{id}.json?print=pretty")
    Call<Feed> getFeed(@Path("id") int idStory);
}
