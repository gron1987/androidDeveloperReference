package com.devRef.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created with IntelliJ IDEA.
 * User: gron
 * Date: 6/30/12
 * Time: 4:17 PM
 */
public class APIMenuItem implements Parcelable {
    private String _link;
    private String _title;

    public APIMenuItem(String link, String title){
        _link = link;
        _title = title;
    }

    public String getTitle(){
        return _title;
    }

    public String getLink(){
        return _link;
    }

    @Override
    public String toString(){
        return _title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_link);
        parcel.writeString(_title);
    }
}
