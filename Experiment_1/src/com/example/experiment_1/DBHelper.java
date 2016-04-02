package com.example.experiment_1;


import java.sql.ResultSet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
	private static final String DB_NAME="SQLiteAndroid.db";
	private static final int DB_VERSION=2;
	//SQLiteDatabase DB;
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		//参数分别为：
		//Context类型，上下文对象。String类型，数据库的名称。 
		//CursorFactory类型。int类型，数据库版本 。
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//建表
		//DB=db;
		db.execSQL("CREATE TABLE SignalSheet("
					//+BaseColumns._ID+" INTEGER NOT NULL PRIMARY KEY,"
					+"X INTEGER NOT NULL,"
					+"Y INTEGER NOT NULL,"
					+"Z INTEGER NOT NULL,"
					+"APName CHAR(100) NOT NULL,"
					+"SigStrenSum CHAR(100) NOT NULL,"
					//+"Count INTEGER NOT NULL,"
					+"PRIMARY KEY(X,Y,Z,APName));"
					);
		//建立触发器，建立索引
	}

/*	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS SignalSheet");
		//删除触发器，删除索引
		onCreate(db);
	}*/
	
	
	
	public String get_all(){//查询所有并打印
		SQLiteDatabase DB = this.getReadableDatabase(); 
		Cursor cursor=DB.rawQuery("SELECT * FROM SignalSheet ORDER BY X,Y,Z;",null);
		String ret="";
		while(cursor.moveToNext()){
			Node node=new Node(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),
					cursor.getString(3),cursor.getString(4));//cursor.getInt(5));
			ret+=node.toString()+"\n";
		}
		return ret;
	}

	public void insert(int x, int y,int z,String apname,String sigstren){
		//若db中没有，则插入
		/*
		ContentValues values=new ContentValues();
		values.put("X", x);
		values.put("Y", y);
		values.put("Z", z);
		values.put("APName", apname);
		values.put("SigStrenSum", sigstren);
		values.put("Count", 1);
		DB.insert("SignalSheet", null, values);
		*/
		SQLiteDatabase DB = this.getWritableDatabase(); 
		DB.execSQL("INSERT INTO SignalSheet VALUES ("+ x +","+ y +","+ z +",'"
		+apname+"','"+sigstren+"')");
		//DB.execSQL("INSERT INTO SignalSheet VALUES (1,10,1,'apname','12')");
		//若有，则修改count和sigStrenSum
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS SignalSheet");
		//删除触发器，删除索引
		onCreate(db);
	}
	
	
}
