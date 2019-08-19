package hr.tomljanovic.matko.seminarskirad.view.newsFeed;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.tomljanovic.matko.seminarskirad.R;
import hr.tomljanovic.matko.seminarskirad.view.savedArticles.SavedArticles;
import hr.tomljanovic.matko.seminarskirad.adapters.recycler.RecyclerViewAdapter;
import hr.tomljanovic.matko.seminarskirad.model.Feed;
import hr.tomljanovic.matko.seminarskirad.settings.SettingsActivity;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    @BindView(R.id.tvUser)
    TextView tvUser;

    @BindView(R.id.textView)
    TextView textView;

    public static final String URL_SENT = "URL_SENT";
    public static final String TAG = "What happened?";

    private MainViewModel viewModel;
    public static RecyclerViewAdapter adapter;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ArrayList<String> urlItems = new ArrayList<>();
    private List<Feed> feedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        getUsername();
        getItems();
        swipeRefresh();
    }

    public void swipeRefresh() {
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getItems();
            }
        });
    }

    @OnClick(R.id.btnNext)
    public void nextAct() {
        Intent i = new Intent(MainActivity.this, SavedArticles.class);
        startActivity(i);
    }

    private void getItems() {
        feedList = new ArrayList<>();
        urlItems = new ArrayList<>();
        viewModel.makeIdQuery().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(@Nullable List<Integer> integers) {
                if (integers != null) {
                    for (int i = 0; i <= 20; i++) {
                        final int id = integers.get(i);
                        viewModel.makeFeedQuery(id).observe(MainActivity.this, new Observer<Feed>() {
                            @Override
                            public void onChanged(@Nullable Feed feed) {
                                feedList.add(feed);
                                urlItems.add(feed.getUrl());
                                adapter = new RecyclerViewAdapter(getApplicationContext(), feedList, urlItems);
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                                if (swipeLayout.isRefreshing()) {
                                    swipeLayout.setRefreshing(false);
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private void getUsername() {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(MainActivity.this);

        if (sp.contains("username")) {
            tvUser.setText(sp.getString("username", null));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.settings) {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUsername();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
