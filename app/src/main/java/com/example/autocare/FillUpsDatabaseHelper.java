package com.example.autocare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

class FillUpsDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "AutoCare.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME_FILL_UP = "fill_ups";
    private static final String COLUMN_ID_FILL_UP = "_id";
    private static final String COLUMN_MODEL_FILL_UP = "model";
    private static final String COLUMN_DATE_FILL_UP = "date";
    private static final String COLUMN_QUANTITY_FILL_UP = "qty";
    private static final String COLUMN_PRICE_FILL_UP = "price";
    private static final String COLUMN_ODOMETER_FILL_UP = "odometer";

    FillUpsDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME_FILL_UP +
                        " (" + COLUMN_ID_FILL_UP + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_MODEL_FILL_UP + " TEXT, " +
                        COLUMN_DATE_FILL_UP + " TEXT, " +
                        COLUMN_QUANTITY_FILL_UP + " INTEGER, " +
                        COLUMN_PRICE_FILL_UP + " INTEGER, " +
                        COLUMN_ODOMETER_FILL_UP + " INTEGER); ";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FILL_UP);
        onCreate(db);
    }

    void addFillUp(String model,String date,int qty, int price, int meter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues(); //store all data

        cv.put(COLUMN_MODEL_FILL_UP,model);
        cv.put(COLUMN_DATE_FILL_UP,date);
        cv.put(COLUMN_QUANTITY_FILL_UP,qty);
        cv.put(COLUMN_PRICE_FILL_UP,price);
        cv.put(COLUMN_ODOMETER_FILL_UP, meter);

        long result = db.insert(TABLE_NAME_FILL_UP,null,cv);
        if(result == -1){
            Toast.makeText(context, "Failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added Successful",Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME_FILL_UP;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    Cursor readOneData(String model){
//        String query = "SELECT * FROM " + TABLE_NAME_FILL_UP + "WHERE model =" + model ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("SELECT * FROM fill_ups WHERE model = ?", new String[]{model});
        }
        return cursor;
    }

    void updateData(String row_id,String date,String qty, String price,String meter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE_FILL_UP,date);
        cv.put(COLUMN_QUANTITY_FILL_UP, qty );
        cv.put(COLUMN_PRICE_FILL_UP, price);
        cv.put(COLUMN_ODOMETER_FILL_UP, meter);

        Log.d("updatedata",qty);
        Log.d("updatedata",price);
        Log.d("updatedata",meter);

        long result = db.update(TABLE_NAME_FILL_UP,cv,"_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Updated",Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db= this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_FILL_UP,"_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Delete",Toast.LENGTH_SHORT).show();
        }
    }

}
