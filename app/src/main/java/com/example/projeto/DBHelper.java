package com.example.projeto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static String nomeDB = "projeto.db";
    private static int versao = 1;

    private String[] sql = {
            "CREATE TABLE utilizador (id INTEGER NOT NULL UNIQUE, username TEXT NOT NULL UNIQUE, pass TEXT NOT NULL, PRIMARY KEY(id AUTOINCREMENT));"
    };

    public DBHelper(@Nullable Context context) {
        super(context, nomeDB, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i = 0; i < sql.length; i++) {
            db.execSQL(sql[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //========================================================================================
    //                                  UTILIZADOR

    //INSERT
    public long Utilizador_Insert(String username, String pass) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("pass", pass);
        return db.insert("utilizador", null, cv);
    }

    //UPDATE
    public long Utilizador_Update(int id, String username, String pass) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("pass", pass);
        return db.update("utilizador", cv, "id=?", new String[]{String.valueOf(id)});
    }

    //DELETE
    public long Utilizador_Delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("utilizador", "id=?", new String[]{String.valueOf(id)});
    }

    //SELECT LOGIN
    public int Utilizador_Login(String username, String pass) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM utilizador WHERE username = ? AND pass = ?",
                new String[]{username, pass});
        c.moveToFirst();
        if (c.getCount() == 1)
            return c.getInt(c.getColumnIndex("id"));
        return -1;
    }

    //SELECT ALL
    public List<Utilizador> Utilizador_SelectAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM utilizador", null);

        List<Utilizador> listaUtilizadores = new ArrayList<>();
        c.moveToFirst();
        if (c.getCount() > 0) {
            do {

                int id = c.getInt(c.getColumnIndex("id"));
                String username = c.getString(c.getColumnIndex("username"));
                String pass = c.getString(c.getColumnIndex("pass"));
                listaUtilizadores.add(new Utilizador(id, username, pass));

            } while (c.moveToNext());
        }
        return listaUtilizadores;
    }

    //SELECT BY ID
    public Utilizador Utilizador_SelectByID(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM utilizador WHERE id=?", new String[]{String.valueOf(id)});
        c.moveToFirst();
        if (c.getCount() == 1) {
            String username = c.getString(c.getColumnIndex("username"));
            String pass = c.getString(c.getColumnIndex("pass"));
            return new Utilizador(id, username, pass);
        }
        return null;
    }

    //========================================================================================
}
