package com.example.user;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Définir le nom de la table Annonces et les noms des colonnes
    public static final String TABLE_ANNONCES = "annonces";
    public static final String COLUMN_ID_ANNONCES = "id";
    public static final String COLUMN_TITLE_ANNONCES = "title";
    public static final String COLUMN_PRICE_ANNONCES = "price";
    public static final String COLUMN_DESCRIPTION_ANNONCES = "description";
    public static final String COLUMN_DATE_PUBLICATION = "date_publication";
    public static final String COLUMN_DATE_FIN_PUBLICATION = "date_fin_publication";
    public static final String COLUMN_DATE_CREATION = "date_creation";
    public static final String COLUMN_DATE_MODIFICATION = "date_modification";

    // Définir le nom de la base de données et sa version
    public static final String DATABASE_NAME = "annonces.db";
    public static final int DATABASE_VERSION = 1;

    // Requête SQL pour créer la table Annonces
    private static final String SQL_CREATE_TABLE_ANNONCES =
            "CREATE TABLE " + TABLE_ANNONCES + " (" +
                    COLUMN_ID_ANNONCES + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_TITLE_ANNONCES + " VARCHAR(255)," +
                    COLUMN_PRICE_ANNONCES + " INTEGER," +
                    COLUMN_DESCRIPTION_ANNONCES + " VARCHAR(255)," +
                    COLUMN_DATE_PUBLICATION + " DATE," +
                    COLUMN_DATE_FIN_PUBLICATION + " DATE," +
                    COLUMN_DATE_CREATION + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    COLUMN_DATE_MODIFICATION + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    // Requête SQL pour supprimer la table si elle existe déjà
    private static final String SQL_DELETE_TABLE_ANNONCES =
            "DROP TABLE IF EXISTS " + TABLE_ANNONCES;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Exécuter la requête de création de table pour la table Annonces lors de la création de la base de données
        db.execSQL(SQL_CREATE_TABLE_ANNONCES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Supprimer la table Annonces existante et créer une nouvelle version de la table si la version de la base de données change
        db.execSQL(SQL_DELETE_TABLE_ANNONCES);
        onCreate(db);
    }
}
