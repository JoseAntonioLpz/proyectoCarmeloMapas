package com.izv.dam.newquip.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

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

    @DatabaseField
    String fecha;

    public Localizaciones(long id, float latitude, float longitude, String fecha) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fecha = fecha;
    }

    public Localizaciones() {
        this(0,(float)0.0,(float)0.0,"");
    }

    protected Localizaciones(Parcel in) {
        id = in.readLong();
        latitude = in.readFloat();
        longitude = in.readFloat();
        fecha = in.readString();
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Localizaciones{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", fecha=" + fecha +
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
        dest.writeString(fecha);
    }
}
