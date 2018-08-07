package com.piotrkalitka.reposwatcher.activity.detailsActivity;

import android.app.Activity;

import com.piotrkalitka.reposwatcher.api.model.RepoItem;
import com.piotrkalitka.reposwatcher.util.Constants;

class DetailsPresenter {

    private DetailsView detailsView;

    DetailsPresenter(DetailsView detailsView) {
        this.detailsView = detailsView;
    }

    void bindData(Activity activity) {
        RepoItem item = activity.getIntent().getParcelableExtra(Constants.BUNDLE_DATA);
        detailsView.bindData(item.getName(), item.getUsername(), item.getDescription(), item.getAvatar());
    }

}