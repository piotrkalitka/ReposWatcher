package com.piotrkalitka.reposwatcher.activity.mainActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.piotrkalitka.reposwatcher.activity.detailsActivity.DetailsActivity;
import com.piotrkalitka.reposwatcher.api.model.RepoItem;
import com.piotrkalitka.reposwatcher.util.Constants;

import java.util.List;

public class ReposListAdapter extends RecyclerView.Adapter<RepoViewHolder> {

    private Context context;
    private List<RepoItem> items;

    ReposListAdapter(Context context, List<RepoItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RepoViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(items.get(position), () -> onItemClicked(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void setItems(List<RepoItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    private void onItemClicked(int position) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(Constants.BUNDLE_DATA, items.get(position));
        context.startActivity(intent);
    }
}
