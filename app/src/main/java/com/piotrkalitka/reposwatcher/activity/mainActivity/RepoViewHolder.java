package com.piotrkalitka.reposwatcher.activity.mainActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.piotrkalitka.reposwatcher.R;
import com.piotrkalitka.reposwatcher.api.model.RepoItem;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

class RepoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imgAvatar)
    ImageView imgAvatar;
    @BindView(R.id.imgBitbucket)
    ImageView imgBitbucket;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtAuthor)
    TextView txtAuthor;

    RepoViewHolder(ViewGroup parent) {
        super(getView(parent));
        ButterKnife.bind(this, itemView);
    }

    private static View getView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_repo, parent, false);
    }

    void bind(RepoItem item, OnItemClick onItemClick) {
        txtName.setText(item.getName());
        txtAuthor.setText(item.getUsername());
        imgBitbucket.setVisibility(item.isBitbucketRepo() ? View.VISIBLE : View.GONE);
        Picasso
                .get()
                .load(item.getAvatar())
                .into(imgAvatar);

        itemView.setOnClickListener(v -> onItemClick.onItemClick());
    }

}
