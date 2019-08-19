package hr.tomljanovic.matko.seminarskirad.network.retrofit;

import java.util.List;

import hr.tomljanovic.matko.seminarskirad.model.Feed;
import hr.tomljanovic.matko.seminarskirad.utils.Const;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HackerNewsAPI {

    @GET(Const.Network.GET_STORIES_URL)
    Flowable<List<Integer>> getStories();

    @GET(Const.Network.GET_STORY_ITEM)
    Flowable<Feed> getFeed(@Path("id") int idStory);
}
