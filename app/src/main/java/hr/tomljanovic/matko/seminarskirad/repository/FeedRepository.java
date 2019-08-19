package hr.tomljanovic.matko.seminarskirad.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;

import java.util.List;

import hr.tomljanovic.matko.seminarskirad.network.retrofit.RetrofitAdapter;
import hr.tomljanovic.matko.seminarskirad.model.Feed;
import io.reactivex.schedulers.Schedulers;

public class FeedRepository {
    private static FeedRepository instance;

    private FeedRepository() {
    }

    public static synchronized FeedRepository getInstance() {
        if (instance == null) {
            instance = new FeedRepository();
        }
        return instance;
    }

    public LiveData<List<Integer>> getPostIds() {
        return LiveDataReactiveStreams.fromPublisher(RetrofitAdapter.getRequestApi()
                .getStories()
                .take(20)
                .subscribeOn(Schedulers.io()));
    }

    public LiveData<Feed> getFeed(int id) {
        return LiveDataReactiveStreams.fromPublisher(RetrofitAdapter.getRequestApi()
                .getFeed(id)
                .subscribeOn(Schedulers.io()));
    }
}
