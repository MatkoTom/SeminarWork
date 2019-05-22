package hr.tomljanovic.matko.seminarskirad.adapters.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import hr.tomljanovic.matko.seminarskirad.R;
import hr.tomljanovic.matko.seminarskirad.WebViewActivity;

import static hr.tomljanovic.matko.seminarskirad.MainActivity.URL_SENT;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> listItems = new ArrayList<>();
    private ArrayList<String> urlItems = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> listItems, ArrayList<String> urlItems, Context context) {
        this.listItems = listItems;
        this.urlItems = urlItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.tvTitle.setText(listItems.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + listItems.get(position));

                Intent i = new Intent(context, WebViewActivity.class);
                i.putExtra(URL_SENT, urlItems.get(position));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        LinearLayout parentLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
