package com.example.exercici1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WordListOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;
    private static final String TAG = WordListOpenHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Usuarios";
    public static final String TABLE_NAME = "usuarios";
    public static final String KEY_ID = "_id";
    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_CORREO = "correo";
    public static final String KEY_CONTRASENA = "contrasena";
    public static final String KEY_TELEFON = "telefon";
    public static final String KEY_EDAD = "edad";
    public static final String KEY_SEXO = "sexo";
    public static final String KEY_FECHA_NACIMIENTO = "fecha_nacimiento";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NOMBRE + " TEXT, " +
                    KEY_CORREO + " TEXT, " +
                    KEY_CONTRASENA + " TEXT, " +
                    KEY_TELEFON + " TEXT, " +
                    KEY_EDAD + " TEXT, " +
                    KEY_SEXO + " TEXT, " +
                    KEY_FECHA_NACIMIENTO + " TEXT);";


    public WordListOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}