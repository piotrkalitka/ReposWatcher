package com.piotrkalitka.reposwatcher.api.model;

public class GithubRepo {

    private String name;
    private String description;
    private Owner owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public static class Owner {
        private String login;
        private String avatar_url;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getAvatarUrl() {
            return avatar_url;
        }

        public void setAvatarUrl(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }

}
