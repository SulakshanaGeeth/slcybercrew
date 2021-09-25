package com.example.autocare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ServiceMyDatabase extends SQLiteOpenHelper{
    private Context context;
    private static final String DATABASE_NAME = "AutoCare.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "service";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TYPE = "service_type";
    private static final String COLUMN_DATE = "service_date";
    private static final String COLUMN_DESCRIPTION = "service_description";
    private static final String COLUMN_PRESENT = "service_present";
    private static final String COLUMN_NEXT = "service_next";
    private static final String COLUMN_COST = "service_cost";


    ServiceMyDatabase(@Nullable Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TYPE + " TEXT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_PRESENT + " INTEGER, " +
                        COLUMN_NEXT + " INTEGER, " +
                        COLUMN_COST + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //insert
    void addService(String type, String date, String desc, int present, int next, int cost){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_DESCRIPTION, desc);
        cv.put(COLUMN_PRESENT, present);
        cv.put(COLUMN_NEXT, next);
        cv.put(COLUMN_COST, cost);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed to add Service", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Service added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    //retrieve
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    //update
    void updateData(String row_id, String type, String date, String description, String present, String next, String cost){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_PRESENT, present);
        cv.put(COLUMN_NEXT, next);
        cv.put(COLUMN_COST, cost);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }

    }

    //delete
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
