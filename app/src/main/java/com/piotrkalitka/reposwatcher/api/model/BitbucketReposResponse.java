package com.piotrkalitka.reposwatcher.api.model;

import java.util.List;

public class BitbucketReposResponse {

    private List<BitbucketRepo> values;

    public List<BitbucketRepo> getValues() {
        return values;
    }

    public void setValues(List<BitbucketRepo> values) {
        this.values = values;
    }
}
