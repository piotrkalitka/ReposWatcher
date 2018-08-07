package com.piotrkalitka.reposwatcher.api;

import com.piotrkalitka.reposwatcher.api.model.BitbucketReposResponse;
import com.piotrkalitka.reposwatcher.api.model.GithubRepo;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface Api {

    @GET("2.0/repositories?fields=values.name,values.owner,values.description")
    Observable<BitbucketReposResponse> getBitbucketRepos();

    @GET("https://api.github.com/repositories")
    Observable<List<GithubRepo>> getGithubRepos();

}
