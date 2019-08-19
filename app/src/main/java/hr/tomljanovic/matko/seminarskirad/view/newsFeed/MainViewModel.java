package hr.tomljanovic.matko.seminarskirad.view.newsFeed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import hr.tomljanovic.matko.seminarskirad.model.Feed;
import hr.tomljanovic.matko.seminarskirad.repository.FeedRepository;

public class MainViewModel extends ViewModel {
    private FeedRepository repository;

    public MainViewModel() {
        repository = FeedRepository.getInstance();
    }

    public LiveData<List<Integer>> makeIdQuery() {
        return repository.getPostIds();
    }

    public LiveData<Feed> makeFeedQuery(int id) {
        return repository.getFeed(id);
    }
}
