package hr.tomljanovic.matko.seminarskirad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.tomljanovic.matko.seminarskirad.adapters.recycler.RecyclerViewAdapter;
import hr.tomljanovic.matko.seminarskirad.model.Feed;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static final String URL_SENT = "URL_SENT";
    public static final String URL = "https://hacker-news.firebaseio.com/";
    public static final String TAG = "What happened?";

    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> urls = new ArrayList<>();
    public static RecyclerViewAdapter adapter;

    private HackerNewsAPI hackerNewsAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        hackerNewsAPI = retrofit.create(HackerNewsAPI.class);

        getItems();

//        tvResult.setText(database.tableLog().selectUrl().toString());
    }


    @OnClick(R.id.btnNext)
    public void nextAct() {
        Intent i = new Intent(MainActivity.this, SavedArticles.class);
        startActivity(i);
    }

    private void getItems() {
//        database.clearAllTables();
        Call<List<Integer>> call = hackerNewsAPI.getStories();
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                List<Integer> data = response.body();
                for (int i = 0; i < 20; i++) {
                    hackerNewsAPI.getFeed(data.get(i)).enqueue(new Callback<Feed>() {
                        @Override
                        public void onResponse(Call<Feed> call, Response<Feed> response) {
                            String title = response.body().getTitle();
                            String url = response.body().getUrl();
                            Log.d(TAG, "onResponse: " + url);
                            if (!(url == null || title == null || url.contains("pdf"))) {
                                titles.add(title);
                                urls.add(url);
                            }

                            adapter = new RecyclerViewAdapter(titles, urls, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<Feed> call, Throwable t) {

                        }
                    });
                }
                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong!" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
