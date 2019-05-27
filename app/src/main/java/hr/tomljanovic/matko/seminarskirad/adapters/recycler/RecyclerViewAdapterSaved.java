package hr.tomljanovic.matko.seminarskirad.adapters.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import hr.tomljanovic.matko.seminarskirad.MainActivity;
import hr.tomljanovic.matko.seminarskirad.R;
import hr.tomljanovic.matko.seminarskirad.WebViewActivity;
import hr.tomljanovic.matko.seminarskirad.database.dbmode.LogArticle;

import static hr.tomljanovic.matko.seminarskirad.MainActivity.URL_SENT;
import static hr.tomljanovic.matko.seminarskirad.utils.RoomApp.database;

public class RecyclerViewAdapterSaved extends RecyclerView.Adapter<RecyclerViewAdapterSaved.ViewHolder> {

    private List<LogArticle> savedArticles;
    private Context mContext;

    public RecyclerViewAdapterSaved(List<LogArticle> savedArticles, Context mContext) {
        this.savedArticles = savedArticles;
        this.mContext = mContext;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_savedlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final LogArticle log = savedArticles.get(position);
        holder.tvSaved.setText(log.getTitle());

        holder.parentLayour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, WebViewActivity.class);
                i.putExtra(URL_SENT, log.getUrl());
                mContext.startActivity(i);
            }
        });

        holder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.tableLog().delete(log);
                savedArticles.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
                MainActivity.adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return savedArticles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSaved;
        LinearLayout parentLayour;
        ImageButton ibtnDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            tvSaved = itemView.findViewById(R.id.tvSaved);
            parentLayour = itemView.findViewById(R.id.savedParentLayout);
            ibtnDelete = itemView.findViewById(R.id.ibtnDelete);
        }
    }
}
