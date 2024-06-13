package com.maryam.ecommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "ecommercedb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "user_accounts";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String FIRSTNAME_COL = "firstname";

    // below variable id for our course duration column.
    private static final String LASTNAME_COL = "lastname";

    // below variable for our course description column.
    private static final String USERNAME_COL = "username";

    // below variable is for our course tracks column.
    private static final String PASSWORD_COL = "password";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRSTNAME_COL + " TEXT,"
                + LASTNAME_COL + " TEXT,"
                + USERNAME_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public boolean addNewShopper(String firstname, String lastname, String username, String password) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        if (existingUser(username,password)){
            return false;
        }

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(FIRSTNAME_COL, firstname);
        values.put(LASTNAME_COL, lastname);
        values.put(USERNAME_COL, username);
        values.put(PASSWORD_COL, password);

        //also create a new shopper object

        Shopper shopper = new Shopper(firstname, lastname, username, password);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
        return true;
    }

    public boolean existingUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        //check if the username already exists
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + USERNAME_COL +  " = ? AND " + PASSWORD_COL + " = ?"; //? is a placeholder for a parameter
        Cursor cursor = db.rawQuery(query, new String[]{username,password});

        if (cursor.getCount() > 0){
            cursor.close();
            db.close();
            return true;
        }

        return false;
    };


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}