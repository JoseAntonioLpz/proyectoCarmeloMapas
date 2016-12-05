package com.izv.dam.newquip.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.izv.dam.newquip.pojo.Localizaciones;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by josea on 03/12/2016.
 */

public class AyudanteLocalizaciones extends OrmLiteSqliteOpenHelper {
    public static final int VERSION = 2;

    private Dao<Localizaciones,Integer> simpleDao = null;
    private RuntimeExceptionDao<Localizaciones,Integer> simpleRunTimeDao=null;

    public AyudanteLocalizaciones(Context context) {
        super(context, "localizaciones", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Localizaciones.class);
        }catch (java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Localizaciones.class, true);
            onCreate(database,connectionSource);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Localizaciones, Integer> getSimpleDao() throws java.sql.SQLException {
        if (simpleDao == null){
            simpleDao=getDao(Localizaciones.class);
        }
        return simpleDao;
    }

    public RuntimeExceptionDao<Localizaciones, Integer> getSimpleRunTimeDao() {
        if (simpleRunTimeDao==null){
            simpleRunTimeDao=getRuntimeExceptionDao(Localizaciones.class);
        }
        return simpleRunTimeDao;
    }

    @Override
    public void close(){
        super.close();
        simpleDao = null;
        simpleRunTimeDao = null;
    }
}
