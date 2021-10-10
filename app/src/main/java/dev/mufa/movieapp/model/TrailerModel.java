package dev.mufa.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TrailerModel implements Parcelable {
    private String key;
    private String type;

    public TrailerModel(String key, String type) {
        this.key = key;
        this.type = type;
    }

    protected TrailerModel(Parcel in) {
        key = in.readString();
        type = in.readString();
    }

    public static final Creator<TrailerModel> CREATOR = new Creator<TrailerModel>() {
        @Override
        public TrailerModel createFromParcel(Parcel in) {
            return new TrailerModel(in);
        }

        @Override
        public TrailerModel[] newArray(int size) {
            return new TrailerModel[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(type);
    }
}
