package com.example.bagrutproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SolveHelper extends SQLiteOpenHelper {
    public static final String DATABASENAME="solve.db";
    public static final String TABLE_SOLVE="tblsolve";
    public static final int DATABASEVERSION=1;

    public static final String COLUMN_ID="solveId";
    public static final String COLUMN_TYPE="cubeType";
    public static final String COLUMN_PENALTY="cubeType";
    public static final String COLUMN_TIME="time";
    public static final String COLUMN_SCRAMBLE="scramble";
    public static final String COLUMN_COMMENT="comment";
    public static final String COLUMN_DATE="date";

    SQLiteDatabase database;

    private static final String CREATE_TABLE_SOLVE="CREATE TABLE IF NOT EXISTS " +
            TABLE_SOLVE + "(" + COLUMN_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TYPE + " INTEGER," + COLUMN_PENALTY + " INTEGER," + COLUMN_TIME + " REAL," + COLUMN_SCRAMBLE + " VARCHAR,"
            + COLUMN_COMMENT +" VARCHAR," + COLUMN_DATE +   " VARCHAR "  +   ");";


    public SolveHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SOLVE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOLVE);
        onCreate(db);
    }

    public void open()
    {
        database=this.getWritableDatabase();
    }

    public Solve createSolve(Solve s)
    {
        ContentValues values=new ContentValues();
        values.put(SolveHelper.COLUMN_PENALTY, s.getTime());
        values.put(SolveHelper.COLUMN_TIME, s.getTime());
        values.put(SolveHelper.COLUMN_SCRAMBLE, s.getScramble());
        values.put(SolveHelper.COLUMN_COMMENT, s.getComment());
        values.put(SolveHelper.COLUMN_DATE, s.getDate());

        long insertId=database.insert(SolveHelper.TABLE_SOLVE, null, values);
        s.setSolveId(insertId);
        return s;
    }

    public long deleteByRow(long rowId) {
        return database.delete(SolveHelper.TABLE_SOLVE, SolveHelper.COLUMN_ID + "=" + rowId, null);
    }

    public long updateByRow(Solve s) {
        ContentValues values=new ContentValues();
        values.put(SolveHelper.COLUMN_ID, s.getSolveId());
        values.put(SolveHelper.COLUMN_PENALTY, s.getSolveId());
        values.put(SolveHelper.COLUMN_TIME, s.getTime());
        values.put(SolveHelper.COLUMN_SCRAMBLE, s.getScramble());
        values.put(SolveHelper.COLUMN_COMMENT, s.getComment());
        values.put(SolveHelper.COLUMN_DATE, s.getDate());
        return database.update(SolveHelper.TABLE_SOLVE, values, SolveHelper.COLUMN_ID +"=" + s.getSolveId(), null);
    }
}
