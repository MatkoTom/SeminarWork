package hr.tomljanovic.matko.seminarskirad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.tomljanovic.matko.seminarskirad.adapters.recycler.RecyclerViewAdapterSaved;

import static hr.tomljanovic.matko.seminarskirad.utils.RoomApp.database;

public class SavedArticles extends AppCompatActivity {

    @BindView(R.id.recyclerViewSaved)
    RecyclerView recyclerViewSaved;

    @BindView(R.id.tvNothing)
    TextView tvNothing;

    @BindView(R.id.tvBack)
    TextView tvBack;

    private RecyclerViewAdapterSaved adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_articles);

        ButterKnife.bind(this);

        adapter = new RecyclerViewAdapterSaved(database.tableLog().selectAll(), getApplicationContext());
        recyclerViewSaved.setAdapter(adapter);
        recyclerViewSaved.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter.notifyDataSetChanged();

        if (adapter.getItemCount() == 0) {
            tvNothing.setText("Nothing here!");
        }
    }

    @OnClick(R.id.recyclerViewSaved)
    public void refresh() {
        adapter = new RecyclerViewAdapterSaved(database.tableLog().selectAll(), getApplicationContext());
    }

    @OnClick(R.id.ivBack)
    public void goBack() {
        finish();
    }

    @OnClick(R.id.tvBack)
    public void textBack() {
        finish();
    }

}
