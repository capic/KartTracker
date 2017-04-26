package capic.com.karttracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vincent on 26/04/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "kart_tracker";
    private static final String TABLE_TRACK = "track";
    private static final String TABLE_TRACK_KEY_ID = "id";
    private static final String TABLE_TRACK_KEY_NAME = "name";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRACK_TABLE = "CREATE TABLE " + TABLE_TRACK + "(" + TABLE_TRACK_KEY_ID + " INTEGER PRIMARY KEY, " + TABLE_TRACK_KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_TRACK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRACK);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
