package com.piotrkalitka.reposwatcher.api.model;

public class BitbucketRepo {

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

        private String username;
        private Links links;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }
    }

    public static class Links {

        private Link avatar;

        public Link getAvatar() {
            return avatar;
        }

        public void setAvatar(Link avatar) {
            this.avatar = avatar;
        }
    }

    public static class Link {

        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

}
