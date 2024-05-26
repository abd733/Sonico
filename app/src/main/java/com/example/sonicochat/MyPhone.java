package com.example.sonicochat;

import android.os.Parcel;
import android.os.Parcelable;

public class MyPhone implements Parcelable {
    private int image;
    private String text;

    public MyPhone(int image, String name) {
        this.image = image;
        this.text = name;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    protected MyPhone(Parcel in) {
        image = in.readInt();
        text = in.readString();
    }

    public static final Creator<MyPhone> CREATOR = new Creator<MyPhone>() {
        @Override
        public MyPhone createFromParcel(Parcel in) {
            return new MyPhone(in);
        }

        @Override
        public MyPhone[] newArray(int size) {
            return new MyPhone[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(text);
    }
}
