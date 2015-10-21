package domain.ormlite_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by himanshu on 10/19/2015.
 */
public class DB_connect extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    private static final String DICTIONARY_TABLE_NAME = "dictionary";
    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                    "KEY_WORD TEXT, " +
                    "KEY_DEFINITION TEXT);";

    DB_connect
            (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int a, int b) {
        // db.execSQL(SQL_DELETE_ENTRIES);
        // onCreate(db);
    }

    public void insert_Row(SQLiteDatabase db, String word, String description) {
        ContentValues values = new ContentValues();
        values.put("KEY_WORD", word);
        values.put("KEY_DEFINITION", description);
        db.insert(DICTIONARY_TABLE_NAME, null, values);
    }

    public Cursor getAllTitles(SQLiteDatabase db) {
        return db.query(DICTIONARY_TABLE_NAME, new String[]{
                        "KEY_WORD",
                        "KEY_DEFINITION",
                },
                null,
                null,
                null, null, null);
    }
}

