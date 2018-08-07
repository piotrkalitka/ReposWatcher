package com.piotrkalitka.reposwatcher.api.model;

import java.util.List;

public class CombinedReposResponse {

    private List<GithubRepo> githubRepos;
    private List<BitbucketRepo> bitbucketRepos;

    public CombinedReposResponse(List<GithubRepo> githubRepos, List<BitbucketRepo> bitbucketRepos) {
        this.githubRepos = githubRepos;
        this.bitbucketRepos = bitbucketRepos;
    }

    public List<GithubRepo> getGithubRepos() {
        return githubRepos;
    }

    public void setGithubRepos(List<GithubRepo> githubRepos) {
        this.githubRepos = githubRepos;
    }

    public List<BitbucketRepo> getBitbucketRepos() {
        return bitbucketRepos;
    }

    public void setBitbucketRepos(List<BitbucketRepo> bitbucketRepos) {
        this.bitbucketRepos = bitbucketRepos;
    }
}
