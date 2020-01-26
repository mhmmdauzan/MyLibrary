package id.ac.poliban.mi.auzan;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;



public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "student_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENT = "students";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "name";

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE "
            + TABLE_STUDENT + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_FIRSTNAME + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    Log.d("table", CREATE_TABLE_STUDENT);
}

public void onCreate(SQLiteDatabase db){
    db.execSQL(CREATE_TABLE_STUDENT);
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS'" + TABLE_STUDENT + "'");
        onCreate(db);
}

public long addStudentDetail(String student){
    SQLiteDatabase db = this.getWritableDatabase();
    // Creating content values
    ContentValues values = new ContentValues();
    values.put(KEY_FIRSTNAME, student);
    //insert row in students table
    long insert = db.insert(TABLE_STUDENT,
            null,values);
    return insert;
}

public ArrayList<String> getAllStudentsList() {
    ArrayList<String> studentsArrayList = new ArrayList<String>();
    String name = "";
    String selectQuery = "SELECT * FROM " + TABLE_STUDENT;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c = db.rawQuery(selectQuery, null);
    //looping through all rows and adding to list
    if (c.moveToFirst()){
        do{
            name = c.getString(c.getColumnIndex(KEY_FIRSTNAME));
            //adding to Students list
            studentsArrayList.add(name);
        } while (c.moveToNext());
        Log.d("array", studentsArrayList.toString());
    }
    return  studentsArrayList;
}
}


