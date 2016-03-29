package itu.mmad.dttn.tingle.model.database.repositories.sqlSchema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DennisThinhTan on 24-03-2016.
 */
public class ThingBaseHelper extends SQLiteOpenHelper
{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "thingBase.db";


    public ThingBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + ThingTable.NAME + "(" +
                "id integer primary key autoincrement, " +
                ThingTable.Cols.UUID + ", " +
                ThingTable.Cols.WHAT + ", " +
                ThingTable.Cols.WHERE + ", " +
                ThingTable.Cols.DESCRIPTION + ", " +
                ThingTable.Cols.DATE +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
