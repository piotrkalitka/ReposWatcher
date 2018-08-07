package com.piotrkalitka.reposwatcher.api.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RepoItem implements Parcelable {

    private String name;
    private String username;
    private String avatar;
    private String description;
    private boolean bitbucketRepo;

    public RepoItem(String name, String username, String avatar, String description, boolean bitbucketRepo) {
        this.name = name;
        this.username = username;
        this.avatar = avatar;
        this.description = description;
        this.bitbucketRepo = bitbucketRepo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBitbucketRepo() {
        return bitbucketRepo;
    }

    public void setBitbucketRepo(boolean bitbucketRepo) {
        this.bitbucketRepo = bitbucketRepo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.username);
        dest.writeString(this.avatar);
        dest.writeString(this.description);
        dest.writeByte(this.bitbucketRepo ? (byte) 1 : (byte) 0);
    }

    private RepoItem(Parcel in) {
        this.name = in.readString();
        this.username = in.readString();
        this.avatar = in.readString();
        this.description = in.readString();
        this.bitbucketRepo = in.readByte() != 0;
    }

    public static final Parcelable.Creator<RepoItem> CREATOR = new Parcelable.Creator<RepoItem>() {
        @Override
        public RepoItem createFromParcel(Parcel source) {
            return new RepoItem(source);
        }

        @Override
        public RepoItem[] newArray(int size) {
            return new RepoItem[size];
        }
    };
}
