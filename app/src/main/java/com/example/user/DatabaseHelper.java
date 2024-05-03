package com.example.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Définir le nom de la table Annonces et les noms des colonnes
    public static final String TABLE_ANNONCES = "annonces";
    //public static final String COLUMN_ID_ANNONCES = "id";
    public static final String COLUMN_TITLE_ANNONCES = "title";
    public static final String COLUMN_PRICE_ANNONCES = "price";
    public static final String COLUMN_DESCRIPTION_ANNONCES = "description";
    public static final String COLUMN_DATE_PUBLICATION_ANNONCES = "date_publication";
    //public static final String COLUMN_DATE_FIN_PUBLICATION = "date_fin_publication";
    //public static final String COLUMN_DATE_CREATION = "date_creation";
    //public static final String COLUMN_DATE_MODIFICATION = "date_modification";

    // Définir le nom de la table Personnes et les noms des colonnes
    //TODO FAIRE TABLE PERSONNE RÉCUPÉRER ID ANNONCE
    public static final String TABLE_PERSONNES = "personnes";
    public static final String COLUMN_LASTNAME_PERSONNES = "nom";
    public static final String COLUMN_NAME_PERSONNES = "prenom";
    public static final String COLUMN_EMAIL_PERSONNES = "email";
    public static final String COLUMN_BIRTH_PERSONNES = "date_de_naissance";
    public static final String COLUMN_DATE_ACCOUNT_CREATION_PERSONNES = "date_de_creation_de_compte";



    // Définir le nom de la base de données et sa version
    public static final String DATABASE_NAME = "db_l3";
    public static final int DATABASE_VERSION = 1;

    // Requête SQL pour créer la table Annonces
    private static final String SQL_CREATE_TABLE_ANNONCES =
            "CREATE TABLE " + TABLE_ANNONCES + " (" +
                    "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_TITLE_ANNONCES + " TEXT," +
                    COLUMN_PRICE_ANNONCES + " integer," +
                    COLUMN_DESCRIPTION_ANNONCES + " text," +
                    COLUMN_DATE_PUBLICATION_ANNONCES + " DATE)";/* +
                    /*COLUMN_DATE_FIN_PUBLICATION + " DATE," +
                    COLUMN_DATE_CREATION + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    COLUMN_DATE_MODIFICATION + " DATETIME DEFAULT CURRENT_TIMESTAMP)";*/

    // Requête SQL pour supprimer la table si elle existe déjà
    private static final String SQL_DELETE_TABLE_ANNONCES =
            "DROP TABLE IF EXISTS " + TABLE_ANNONCES;

    // Requête SQL pour créer la table Personnes
    private static final String SQL_CREATE_TABLE_PERSONNES =
            "CREATE TABLE " + TABLE_PERSONNES + " (" +
                    "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_LASTNAME_PERSONNES + " TEXT," +
                    COLUMN_NAME_PERSONNES + " TEXT," +
                    COLUMN_EMAIL_PERSONNES + " TEXT," +
                    COLUMN_BIRTH_PERSONNES + " DATE," +
                    COLUMN_DATE_ACCOUNT_CREATION_PERSONNES + " DATE)";

    // Requête SQL pour supprimer la table si elle existe déjà
    private static final String SQL_DELETE_TABLE_PERSONNES =
            "DROP TABLE IF EXISTS " + TABLE_ANNONCES;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Exécuter la requête de création de table pour la table Annonces lors de la création de la base de données
        db.execSQL(SQL_CREATE_TABLE_ANNONCES);
        db.execSQL(SQL_CREATE_TABLE_PERSONNES);

        // Insérer une date date_publication lorsque l'annonce est inséré en base
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        Annonces a = new Annonces();

        // Ajouter une entrée à la table Annonces lors de la création de la base de données
        ContentValues dataAnnounce = new ContentValues();

        dataAnnounce.put(DatabaseHelper.COLUMN_TITLE_ANNONCES, a.getTitle());
        dataAnnounce.put(DatabaseHelper.COLUMN_PRICE_ANNONCES, a.getPrice());
        dataAnnounce.put(DatabaseHelper.COLUMN_DESCRIPTION_ANNONCES, a.getDescription());
        //values.put(DatabaseHelper.COLUMN_DATE_FIN_PUBLICATION, "2022-04-30");

        String parseDateStrAnnounce = dateFormat.format(a.getDatePublication());
        dataAnnounce.put(DatabaseHelper.COLUMN_DATE_PUBLICATION_ANNONCES, parseDateStrAnnounce);

        long newRowIdAnnounce = db.insert(DatabaseHelper.TABLE_ANNONCES, null, dataAnnounce);

        Log.d("INSERT_ANNOUNCE", "onCreate: " + newRowIdAnnounce);

        // PERSONNES
        Personnes p = new Personnes();

        // Ajouter une entrée à la table Personnes lors de la création de la base de données
        ContentValues dataPerson = new ContentValues();

        dataPerson.put(DatabaseHelper.COLUMN_LASTNAME_PERSONNES, p.getLastname());
        dataPerson.put(DatabaseHelper.COLUMN_NAME_PERSONNES, p.getName());
        dataPerson.put(DatabaseHelper.COLUMN_EMAIL_PERSONNES, p.getEmail());
        dataPerson.put(DatabaseHelper.COLUMN_BIRTH_PERSONNES, p.getBirth());

        String parseDateStrPerson = dateFormat.format(p.getDateCreateAccount());
        dataPerson.put(DatabaseHelper.COLUMN_DATE_ACCOUNT_CREATION_PERSONNES, parseDateStrPerson);

        long newRowIdPerson = db.insert(DatabaseHelper.TABLE_PERSONNES, null, dataPerson);

        Log.d("INSERT_PERSON", "onCreate: " + newRowIdPerson);
    }

    public boolean addNewAnnonces(Annonces a, SimpleDateFormat dateFormat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        dateFormat.applyPattern("yyyy/MM/dd");

        values.put(DatabaseHelper.COLUMN_TITLE_ANNONCES, a.getTitle());
        values.put(DatabaseHelper.COLUMN_PRICE_ANNONCES, a.getPrice());
        values.put(DatabaseHelper.COLUMN_DESCRIPTION_ANNONCES, a.getDescription());

        String parseDateStr = dateFormat.format(a.getDatePublication());
        values.put(DatabaseHelper.COLUMN_DATE_PUBLICATION_ANNONCES, parseDateStr);

        return db.insert(DatabaseHelper.TABLE_ANNONCES, null, values) > 1 ? true : false;
    }

    public boolean addNewPersonnes(Personnes p, SimpleDateFormat dateFormat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        dateFormat.applyPattern("yyyy/MM/dd");

        values.put(DatabaseHelper.COLUMN_LASTNAME_PERSONNES, p.getLastname());
        values.put(DatabaseHelper.COLUMN_NAME_PERSONNES, p.getName());
        values.put(DatabaseHelper.COLUMN_EMAIL_PERSONNES, p.getEmail());
        values.put(DatabaseHelper.COLUMN_BIRTH_PERSONNES, p.getBirth());

        String parseDateStr = dateFormat.format(p.getDateCreateAccount());
        values.put(DatabaseHelper.COLUMN_DATE_ACCOUNT_CREATION_PERSONNES, parseDateStr);

        return db.insert(DatabaseHelper.TABLE_PERSONNES, null, values) > 1 ? true : false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Supprimer la table Annonces existante et créer une nouvelle version de la table si la version de la base de données change
        db.execSQL(SQL_DELETE_TABLE_ANNONCES);
        db.execSQL(SQL_DELETE_TABLE_PERSONNES);
        onCreate(db);
    }

    public Cursor getAllDataFromTable(String table) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + table, null);
        return res;
    }
}
