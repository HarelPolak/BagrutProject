package com.example.bagrutproject.stats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SolveHelper extends SQLiteOpenHelper {
    public static final String DATABASENAME = "solve.db";
    public static final String TABLE_SOLVE = "tblsolve";
    public static final int DATABASEVERSION = 1;

    public static final String COLUMN_ID = "solveId";
    public static final String COLUMN_TYPE = "cubeType";
    public static final String COLUMN_PENALTY = "penalty";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_SCRAMBLE = "scramble";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PENALTY_TIME = "penaltyTime";

    String[] allColumns = {SolveHelper.COLUMN_ID, SolveHelper.COLUMN_TYPE, SolveHelper.COLUMN_PENALTY,
            SolveHelper.COLUMN_TIME, SolveHelper.COLUMN_SCRAMBLE, SolveHelper.COLUMN_COMMENT, SolveHelper.COLUMN_DATE, SolveHelper.COLUMN_PENALTY_TIME};

    SQLiteDatabase database;

    private static final String CREATE_TABLE_SOLVE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_SOLVE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TYPE + " INTEGER," + COLUMN_PENALTY + " INTEGER," + COLUMN_TIME + " BIGINT," + COLUMN_SCRAMBLE + " VARCHAR,"
            + COLUMN_COMMENT + " VARCHAR," + COLUMN_DATE + " VARCHAR," + COLUMN_PENALTY_TIME +  " BIGINT" + ");";


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

    public void open() {
        database = this.getWritableDatabase();
    }

    public Solve createSolve(Solve s) {
        ContentValues values = new ContentValues();
        values.put(SolveHelper.COLUMN_TYPE, s.getCubeType());
        values.put(SolveHelper.COLUMN_PENALTY, s.getPenalty());
        values.put(SolveHelper.COLUMN_TIME, s.getTime());
        values.put(SolveHelper.COLUMN_SCRAMBLE, s.getScramble());
        values.put(SolveHelper.COLUMN_COMMENT, s.getComment());
        values.put(SolveHelper.COLUMN_DATE, s.getDate());
        values.put(SolveHelper.COLUMN_PENALTY_TIME, s.getPenaltyTime());
        long insertId = database.insert(SolveHelper.TABLE_SOLVE, null, values);
        s.setSolveId(insertId);
        return s;
    }

    public long deleteByRow(long rowId) {
        return database.delete(SolveHelper.TABLE_SOLVE, SolveHelper.COLUMN_ID + "=" + rowId, null);
    }

    public long updateByRow(Solve s) {
        ContentValues values = new ContentValues();
        values.put(SolveHelper.COLUMN_ID, s.getSolveId());
        values.put(SolveHelper.COLUMN_TYPE, s.getCubeType());
        values.put(SolveHelper.COLUMN_PENALTY, s.getPenalty());
        values.put(SolveHelper.COLUMN_TIME, s.getTime());
        values.put(SolveHelper.COLUMN_SCRAMBLE, s.getScramble());
        values.put(SolveHelper.COLUMN_COMMENT, s.getComment());
        values.put(SolveHelper.COLUMN_DATE, s.getDate());
        values.put(SolveHelper.COLUMN_PENALTY_TIME, s.getPenaltyTime());
        return database.update(SolveHelper.TABLE_SOLVE, values, SolveHelper.COLUMN_ID + "=" + s.getSolveId(), null);
    }

    public Solve getSolveById(long rowId) {
        Cursor cursor = database.query(SolveHelper.TABLE_SOLVE, allColumns, SolveHelper.COLUMN_ID + "=" + rowId, null, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_ID));
            int type = cursor.getInt(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_TYPE));
            int penalty = cursor.getInt(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_PENALTY));
            long time = cursor.getLong(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_TIME));
            String scramble = cursor.getString(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_SCRAMBLE));
            String comment = cursor.getString(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_COMMENT));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_DATE));
            Solve s = new Solve(id, type, penalty, time, scramble, comment, date);
            return s;
        }
        return null;
    }

    public ArrayList<Solve> getAllSolvesByType(int cubeType) {
        String selection = SolveHelper.COLUMN_TYPE + " = " + cubeType;
        String orderBy = SolveHelper.COLUMN_ID + " DESC";
        Cursor cursor = database.query(SolveHelper.TABLE_SOLVE, allColumns, selection, null, null, null, orderBy);
        ArrayList<Solve> solves = convertCursorToList(cursor);
        return solves;
    }

    private ArrayList<Solve> convertCursorToList(Cursor cursor) {
        ArrayList<Solve> solves = new ArrayList<Solve>();
        if(cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_ID));
                int type = cursor.getInt(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_TYPE));
                int penalty = cursor.getInt(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_PENALTY));
                long time = cursor.getLong(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_TIME));
                String scramble = cursor.getString(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_SCRAMBLE));
                String comment = cursor.getString(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_COMMENT));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_DATE));
                Solve s = new Solve(id, type, penalty, time, scramble, comment, date);
                solves.add(s);
            }
        }
        return solves;
    }

    public boolean isBestTime(int cubeType, long solveTime){
        long best;
        String selection = SolveHelper.COLUMN_TYPE + " = " + cubeType;
        String orderBy = SolveHelper.COLUMN_PENALTY_TIME + " ASC";
        Cursor cursor = database.query(SolveHelper.TABLE_SOLVE, allColumns, selection, null, null, null, orderBy);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            best = cursor.getLong(cursor.getColumnIndexOrThrow(SolveHelper.COLUMN_TIME));
            if(solveTime < best)
                return true;
        }
        return false;
    }


}
