package db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by thekey123 on 2015/8/24.
 * 数据库操作核心类
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, int version){
        this(context, name, null, version);
    }

    public DBHelper(Context context, String name){
        this(context,name,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 创建一张数据表
     * @param db
     * @param dbname
     */
    public void creatTable(SQLiteDatabase db,String dbname){
        try {
            db.execSQL(dbname);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("database", "建表失败了");
        }
    }
}