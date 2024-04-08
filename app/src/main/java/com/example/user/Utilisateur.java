package com.example.user;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Utilisateur implements Parcelable {
    String mail;
    String mdp;

    public Utilisateur(String mail, String mdp) {
        this.mail = mail;
        this.mdp = mdp;
    }

    protected Utilisateur(Parcel in) {
        mail = in.readString();
        mdp = in.readString();
    }

    public static final Creator<Utilisateur> CREATOR = new Creator<Utilisateur>() {
        @Override
        public Utilisateur createFromParcel(Parcel in) {
            return new Utilisateur(in);
        }

        @Override
        public Utilisateur[] newArray(int size) {
            return new Utilisateur[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(mail);
        dest.writeString(mdp);
    }
}
