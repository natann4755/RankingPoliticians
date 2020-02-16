package com.example.mynabers;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;

@Entity
public class neighbor implements Parcelable{
    private String firstName, lastName, url;
    private int age;
    private int rating;
    private boolean faivorit = false;

    public neighbor(String firstName, String lastName, String url, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.url = url;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isFaivorit() {
        return faivorit;
    }

    public void setFaivorit(boolean faivorit) {
        this.faivorit = faivorit;
    }

    protected neighbor(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        url = in.readString();
        age = in.readInt();
        rating = in.readInt();
        faivorit = in.readByte() != 0;
    }

    public static final Creator<neighbor> CREATOR = new Creator<neighbor>() {
        @Override
        public neighbor createFromParcel(Parcel in) {
            return new neighbor(in);
        }

        @Override
        public neighbor[] newArray(int size) {
            return new neighbor[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(url);
        dest.writeInt(age);
        dest.writeInt(rating);
        dest.writeByte((byte) (faivorit ? 1 : 0));
    }
}

