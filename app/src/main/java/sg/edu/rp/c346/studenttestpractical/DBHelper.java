package sg.edu.rp.c346.studenttestpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // Start with version with 1
    // increment by 1 whenever database schema changes.
    private static final int DATABASE_VER = 1;
    // Filename of the database
    private static final String DATABASE_NAME = "stu.db";

    private static final String TABLE_STUDENT = "Student";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "student_name";
    private static final String COLUMN_GPA = "gpa";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableSql = "CREATE TABLE " + TABLE_STUDENT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_GPA + " INTEGER )";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        // Create table(s) again
        onCreate(db);

    }

    public void insertStudent(String name, Integer gpa) {

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_NAME, name );
        // Store the column name as key and the date as value
        values.put(COLUMN_GPA, gpa);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_STUDENT, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<String> getStudentContent() {
        // Create an ArrayList that holds String objects
        ArrayList<String> tasks = new ArrayList<String>();
        // Select all the tasks' description
        String selectQuery = "SELECT " + COLUMN_NAME + "," + COLUMN_GPA
                + " FROM " + TABLE_STUDENT;

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);

        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row
            //  and returns true; moveToNext() returns false
            //  when no more next row to move to
            do {
                // Add the task content to the ArrayList object
                //  0 in getString(0) return the data in the first
                //  column in the Cursor object. getString(1)
                //  return second column data and so on.
                //  Use getInt(0) if data is an int
                tasks.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }


        // Close connection
        cursor.close();
        db.close();

        return tasks;
    }

    public ArrayList<Student> getStudent() {
        ArrayList<Student> students = new ArrayList<Student>();

        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_NAME + ", "
                + COLUMN_GPA
                + " FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int gpa = cursor.getInt(2);
                Student obj = new Student(id, name, gpa);
                students.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return students;
    }

}
