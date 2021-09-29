package com.example.autocare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AddVehicleDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "AutoCare.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME_MY_VEHICLE = "my_vehicle";
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_VEHICLE_NAME = "vehicle_name";
    private static final String COLUMN_VEHICLE_YEAR ="vehicle_year";
    private static final String COLUMN_VEHICLENO ="vehicle_no";
    private static final String COLUMN_VEHICLE_DETAILS = "vehicle_details";
    private static final String COLUMN_INSURANCE_NO = "vehicle_insuranceno";
    private static final String COLUMN_FUEL_TYPE = "vehicle_fuel_type";

    private static final String TABLE_NAME_FILL_UP = "fill_ups";
    private static final String COLUMN_ID_FILL_UP = "_id";
    private static final String COLUMN_MODEL_FILL_UP = "model";
    private static final String COLUMN_DATE_FILL_UP = "date";
    private static final String COLUMN_QUANTITY_FILL_UP = "qty";
    private static final String COLUMN_PRICE_FILL_UP = "price";
    private static final String COLUMN_ODOMETER_FILL_UP = "odometer";

    private static final String TABLE_NAME_SERVICE = "service";
    private static final String SERVICE_COLUMN_ID = "_id";
    private static final String COLUMN_TYPE = "service_type";
    private static final String COLUMN_DATE_SERVICE = "service_date";
    private static final String COLUMN_DESCRIPTION = "service_description";
    private static final String COLUMN_PRESENT = "service_present";
    private static final String COLUMN_NEXT = "service_next";
    private static final String COLUMN_COST = "service_cost";

    private static final String TABLE_NAME_REMINDER = "my_reminder";
    private static final String REMINDER_COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "car_title";
    private static final String COLUMN_DATE_REMINDER = "service_date";
    private static final String COLUMN_MILEAGE = "service_mileage";

    AddVehicleDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME_MY_VEHICLE +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_VEHICLE_NAME + " TEXT, " +
                        COLUMN_VEHICLE_YEAR + " INTEGER, " +
                        COLUMN_VEHICLENO + " INTEGER, " +
                        COLUMN_VEHICLE_DETAILS + " TEXT, " +
                        COLUMN_INSURANCE_NO + " INTEGER, " +
                        COLUMN_FUEL_TYPE + " TEXT); ";

        db.execSQL(query);

        String query2 =
                "CREATE TABLE " + TABLE_NAME_FILL_UP +
                        " (" + COLUMN_ID_FILL_UP + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_MODEL_FILL_UP + " TEXT, " +
                        COLUMN_DATE_FILL_UP + " TEXT, " +
                        COLUMN_QUANTITY_FILL_UP + " INTEGER, " +
                        COLUMN_PRICE_FILL_UP + " INTEGER, " +
                        COLUMN_ODOMETER_FILL_UP + " INTEGER); ";

        db.execSQL(query2);

        String query3 =
                "CREATE TABLE " + TABLE_NAME_SERVICE +
                        " (" + SERVICE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TYPE + " TEXT, " +
                        COLUMN_DATE_SERVICE + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_PRESENT + " INTEGER, " +
                        COLUMN_NEXT + " INTEGER, " +
                        COLUMN_COST + " INTEGER);";
        db.execSQL(query3);

        String query4 = "CREATE TABLE " + TABLE_NAME_REMINDER +
                " (" + REMINDER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DATE_REMINDER + " TEXT, " +
                COLUMN_MILEAGE + " INTEGER);";
        db.execSQL(query4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MY_VEHICLE);
        onCreate(db);
    }

    void addVehicle(String name,int year,int no,String details,int insuNo,String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues(); //store all data

        cv.put(COLUMN_VEHICLE_NAME,name);
        cv.put(COLUMN_VEHICLE_YEAR,year);
        cv.put(COLUMN_VEHICLENO,no);
        cv.put(COLUMN_VEHICLE_DETAILS,details);
        cv.put(COLUMN_INSURANCE_NO, insuNo);
        cv.put(COLUMN_FUEL_TYPE, type);

        long result = db.insert(TABLE_NAME_MY_VEHICLE,null,cv);
        if(result == -1){
            Toast.makeText(context, "Failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added Successful",Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME_MY_VEHICLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void updateData(String row_id,String name,String year, String no,String details,String insurance,String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_VEHICLE_NAME,name);
        cv.put(COLUMN_VEHICLE_YEAR, year );
        cv.put(COLUMN_VEHICLENO, no);
        cv.put(COLUMN_VEHICLE_DETAILS, details);
        cv.put(COLUMN_INSURANCE_NO, insurance);
        cv.put(COLUMN_FUEL_TYPE, type);


        long result = db.update(TABLE_NAME_MY_VEHICLE,cv,"_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Updated",Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db= this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_MY_VEHICLE,"_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Delete",Toast.LENGTH_SHORT).show();
        }
    }
}
