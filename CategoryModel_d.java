package com.example.myquiz;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryModel implements Parcelable {

    private String id;
    private String name;

    public CategoryModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    protected CategoryModel(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
    }

    private void readFromParcel(Parcel in) {
        name = in.readString();
        id = in.readString();

    }
}
