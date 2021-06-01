package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "smartphone")
public class Phone {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_phone")
    protected long mId;

    @NonNull
    @ColumnInfo(name = "OEM")
    protected String mOEM;

    @NonNull
    @ColumnInfo(name = "model")
    protected String mModel;

    @NonNull
    @ColumnInfo(name = "version")
    protected String mVersion;

    @NonNull
    @ColumnInfo(name = "website")
    protected String mWebsite;

    public Phone(@NonNull String mOEM, @NonNull String mModel, @NonNull String mVersion, @NonNull String mWebsite) {
        this.mOEM = mOEM;
        this.mModel = mModel;
        this.mVersion = mVersion;
        this.mWebsite = mWebsite;
    }


    public long getFie_mId() {
        return mId;
    }

    public void setFie_mId(long mId) {
        this.mId = mId;
    }

    @NonNull
    public String getFie_mOEM() {
        return mOEM;
    }

    public void setFie_mOEM(@NonNull String mOEM) {
        this.mOEM = mOEM;
    }

    @NonNull
    public String getFie_mModel() {
        return mModel;
    }

    public void setFie_mModel(@NonNull String mModel) {
        this.mModel = mModel;
    }

    @NonNull
    public String getFie_mVersion() {
        return mVersion;
    }

    public void setFie_mVersion(@NonNull String mVersion) {
        this.mVersion = mVersion;
    }

    @NonNull
    public String getFie_mWebsite() {
        return mWebsite;
    }

    public void setFie_mWebsite(@NonNull String mWebsite) {
        this.mWebsite = mWebsite;
    }
}
