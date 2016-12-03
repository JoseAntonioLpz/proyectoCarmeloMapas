package com.izv.dam.newquip.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by josea on 03/12/2016.
 */

@DatabaseTable(tableName = "localizaciones")
public class Localizaciones implements Parcelable {

    @DatabaseField
    long id;

    @DatabaseField
    float latitude;

    @DatabaseField
    float longitude;

    public Localizaciones(long id, float latitude, float longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Localizaciones() {
        this(0,(float)0.0,(float)0.0);
    }

    protected Localizaciones(Parcel in) {
        id = in.readLong();
        latitude = in.readFloat();
        longitude = in.readFloat();
    }

    public static final Creator<Localizaciones> CREATOR = new Creator<Localizaciones>() {
        @Override
        public Localizaciones createFromParcel(Parcel in) {
            return new Localizaciones(in);
        }

        @Override
        public Localizaciones[] newArray(int size) {
            return new Localizaciones[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Localizaciones{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
    }
}
