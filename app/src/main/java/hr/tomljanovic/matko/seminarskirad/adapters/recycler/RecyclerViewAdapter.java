package hr.tomljanovic.matko.seminarskirad.adapters.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hr.tomljanovic.matko.seminarskirad.R;
import hr.tomljanovic.matko.seminarskirad.view.webActivity.WebViewActivity;
import hr.tomljanovic.matko.seminarskirad.database.dbmode.LogArticle;
import hr.tomljanovic.matko.seminarskirad.model.Feed;

import static hr.tomljanovic.matko.seminarskirad.view.newsFeed.MainActivity.URL_SENT;
import static hr.tomljanovic.matko.seminarskirad.utils.RoomApp.database;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private List<LogArticle> savedItems;
    private ArrayList<String> urlItems;
    private Context context;
    private List<Feed> feedList;
    private boolean isClicked = false;

    public RecyclerViewAdapter(Context context, List<Feed> feedList, ArrayList<String> urlItems) {
        this.context = context;
        this.feedList = feedList;
        this.urlItems = urlItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tvTitle.setText(feedList.get(position).getTitle());
        if (database.tableLog().selectAll().toString().contains(feedList.get(position).getTitle())) {
            holder.ibtnSave.setImageResource(R.drawable.ic_star_full);
        } else {
            holder.ibtnSave.setImageResource(R.drawable.ic_star);
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, WebViewActivity.class);
                i.putExtra(URL_SENT, urlItems.get(position));
                context.startActivity(i);
            }
        });

        holder.ibtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClicked && !(database.tableLog().selectAll().toString().contains(feedList.get(position).getTitle()))) {
                    database.tableLog().insert(new LogArticle(feedList.get(position).getTitle(), urlItems.get(position)));
                    holder.ibtnSave.setImageResource(R.drawable.ic_star_full);
                    Animation animation = AnimationUtils.loadAnimation(context, R.anim.favorite_anim);
                    animation.setInterpolator(new LinearInterpolator());
                    holder.ibtnSave.startAnimation(animation);
                    isClicked = true;
                } else {
                    savedItems = database.tableLog().selectAll();
                    String item = feedList.get(position).getTitle();
                    for (LogArticle items : savedItems) {
                        if (items.getTitle().contains(item)) {
                            database.tableLog().delete(items);
                        }
                    }
                    holder.ibtnSave.setImageResource(R.drawable.ic_star);
                    isClicked = false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        LinearLayout parentLayout;
        ImageButton ibtnSave;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            ibtnSave = itemView.findViewById(R.id.ibtnSave);
        }
    }
}
