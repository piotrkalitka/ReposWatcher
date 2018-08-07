package com.piotrkalitka.reposwatcher.activity.mainActivity;

import com.piotrkalitka.reposwatcher.api.model.RepoItem;

import java.util.List;

interface MainView {

    void showLoading();

    void hideLoading();

    void hideSwipeRefreshLoading();

    void showError();

    void updateReposList(List<RepoItem> items);

    void setSortButtonChecked(boolean state);

}
