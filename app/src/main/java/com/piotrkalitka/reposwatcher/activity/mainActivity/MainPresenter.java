package com.piotrkalitka.reposwatcher.activity.mainActivity;

import com.piotrkalitka.reposwatcher.api.ApiProvider;
import com.piotrkalitka.reposwatcher.api.model.BitbucketRepo;
import com.piotrkalitka.reposwatcher.api.model.BitbucketReposResponse;
import com.piotrkalitka.reposwatcher.api.model.CombinedReposResponse;
import com.piotrkalitka.reposwatcher.api.model.GithubRepo;
import com.piotrkalitka.reposwatcher.api.model.RepoItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class MainPresenter {

    private MainView mainView;
    private List<RepoItem> originReposListItems;
    private boolean isSorted;

    MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void getData(boolean showLoading) {
        if (showLoading) mainView.showLoading();

        Observable<BitbucketReposResponse> bitbucketReposRequest = ApiProvider
                .provideApi()
                .getBitbucketRepos();

        Observable<List<GithubRepo>> githubReposRequest = ApiProvider
                .provideApi()
                .getGithubRepos();

        Observable.zip(bitbucketReposRequest, githubReposRequest, (bitbucketReposResponse, githubRepos) -> new CombinedReposResponse(githubRepos, bitbucketReposResponse.getValues()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(combinedReposResponse -> {
                    originReposListItems = mapCombinedResults(combinedReposResponse);
                    isSorted = false;
                    mainView.setSortButtonChecked(false);
                    mainView.updateReposList(originReposListItems);
                    if (showLoading) mainView.hideLoading();
                    else mainView.hideSwipeRefreshLoading();
                }, throwable -> {
                    mainView.hideLoading();
                    if (showLoading) mainView.showError();
                    else mainView.hideSwipeRefreshLoading();
                });
    }

    private List<RepoItem> mapCombinedResults(CombinedReposResponse combinedReposResponse) {
        List<RepoItem> items = new ArrayList<>();
        for (BitbucketRepo bitbucketRepo : combinedReposResponse.getBitbucketRepos()) {
            items.add(new RepoItem(bitbucketRepo.getName(), bitbucketRepo.getOwner().getUsername(), bitbucketRepo.getOwner().getLinks().getAvatar().getHref(), bitbucketRepo.getDescription(), true));
        }
        for (GithubRepo githubRepo : combinedReposResponse.getGithubRepos()) {
            items.add(new RepoItem(githubRepo.getName(), githubRepo.getOwner().getLogin(), githubRepo.getOwner().getAvatarUrl(), githubRepo.getDescription(), false));
        }
        return items;
    }

    public void sortReposList() {
        if (!isSorted) {
            List<RepoItem> sortedReposListItems = new ArrayList<>(originReposListItems);
            Collections.sort(sortedReposListItems, new RepoItemComparator());
            mainView.updateReposList(sortedReposListItems);
        } else {
            mainView.updateReposList(originReposListItems);
        }
        isSorted = !isSorted;
        mainView.setSortButtonChecked(isSorted);
    }

    private static class RepoItemComparator implements Comparator<RepoItem> {

        @Override
        public int compare(RepoItem item1, RepoItem item2) {
            return item1.getName().compareToIgnoreCase(item2.getName());
        }
    }

}
