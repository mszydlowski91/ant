package com.example.scrumboard.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	public static final String L_BRACKET = "(";
    public static final String R_BRACKET = ")";
	
	
	public static final String TABLE_USERSTORY = "userstory";	
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name"; 
	public static final String COLUMN_DETAILS = "details"; 
	public static final String COLUMN_STATUS = "status";	
	public static final String COLUMN_PRIORITY = "priority"; 
	public static final String COLUMN_ESTIMATED_TIME = "estimatedTime"; 
	public static final String COLUMN_ID_SPRINT = "sprintId";
	public static final String COLUMN_COMMENT = "comment"; 
	
	public static final String TABLE_TASK = "task";
	public static final String COLUMN_ID_PROJECT = "projectId";
	public static final String COLUMN_ID_MEMBER = "memberId";
	public static final String COLUMN_DEADLINE = "deadline"; 
	
	public static final String TABLE_PROJECT = "project";
	public static final String COLUMN_ID_USERSTORY = "userstoryId";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_START_DATE = "startDate"; 
	public static final String COLUMN_MASTER = "master"; 
	
	public static final String TABLE_SPRINT = "sprint";	
	public static final String COLUMN_END_DATE = "endDate"; 
	
	public static final String TABLE_MEMBER = "member";
	public static final String COLUMN_SURNAME = "userstoryId";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_PASSWORD = "password";
	
	public static final String TABLE_FOLDER = "folder";
	public static final String COLUMN_SIZE = "size";
	public static final String COLUMN_FILE_COUNT = "files";
	
	public static final String TABLE_MEMBER_FOLDER = "member_folder";
	public static final String COLUMN_ID_FOLDER = "folderId";
	

	// SQL creation statements
	private static final String DATABASE_CREATE_TABLE_USERSTORY = "create table " + TABLE_USERSTORY + L_BRACKET 
				  + COLUMN_ID + " integer primary key autoincrement, " 
			      + COLUMN_NAME + " text not null, " 
				  + COLUMN_DETAILS + " text not null, "
				  + COLUMN_STATUS + " text not null, "
				  + COLUMN_PRIORITY + " integer, "
				  + COLUMN_ESTIMATED_TIME + " integer, "
				  + COLUMN_ID_SPRINT + " integer, "
				  + COLUMN_COMMENT + " text "
				 // + "FOREIGN KEY(sprintId) REFERENCES sprint(_id)"
				  + R_BRACKET;
				  
	
	private static final String DATABASE_CREATE_TABLE_TASK = "create table " + TABLE_TASK + L_BRACKET 
			  + COLUMN_ID + " integer primary key autoincrement, " 
		      + COLUMN_ID_USERSTORY + " integer, " 
			  + COLUMN_ID_MEMBER + " integer, "
			  + COLUMN_ESTIMATED_TIME + " integer, "
			  + COLUMN_STATUS + " text not null, "
			  + COLUMN_NAME + " text not null, "			  		 
			  + COLUMN_DEADLINE + " text not null, "
			  + COLUMN_COMMENT + " text "
//			  + "FOREIGN KEY(userstoryId) REFERENCES userstory(_id),"
//			  + "FOREIGN KEY(memberId) REFERENCES member(_id)"
			  + R_BRACKET;
			  
	private static final String DATABASE_CREATE_TABLE_PROJECT = "create table " + TABLE_PROJECT + L_BRACKET 
			  + COLUMN_ID + " integer primary key autoincrement, " 
		      + COLUMN_ID_USERSTORY + " integer, " 
		      + COLUMN_NAME + " text not null, "	
			  + COLUMN_DESCRIPTION +  " text not null, "
			  + COLUMN_START_DATE +  " text not null, "
			  + COLUMN_STATUS + " text not null, "
			  + COLUMN_MASTER + " integer, "		
			  + COLUMN_PRIORITY + " integer, "
			  + COLUMN_DEADLINE + " text not null "
			 // + "FOREIGN KEY(master) REFERENCES member(_id)"
			  + R_BRACKET;
	
	private static final String DATABASE_CREATE_TABLE_SPRINT = "create table " + TABLE_SPRINT + L_BRACKET 
			  + COLUMN_ID + " integer primary key autoincrement, " 
		      + COLUMN_ID_PROJECT + " integer, "
			  + COLUMN_START_DATE +  " text not null, "
			  + COLUMN_END_DATE +  " text not null "	 
			 // + "FOREIGN KEY(projectId) REFERENCES project(_id)"
			  + R_BRACKET;
	
	private static final String DATABASE_CREATE_TABLE_MEMBER = "create table " + TABLE_MEMBER + L_BRACKET 
			  + COLUMN_ID + " integer primary key autoincrement, " 
			  + COLUMN_NAME + " text not null, "	
			  + COLUMN_SURNAME + " text not null, "	
			  + COLUMN_EMAIL + " text not null, "	
			  + COLUMN_PASSWORD + " text not null "
			  + R_BRACKET;
	
	private static final String DATABASE_NAME = "scrum.db";
	private static final int DATABASE_VERSION = 1;

	
	public MySQLiteHelper(Context context) 
	{		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
		
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub	
		
		
		database.execSQL(DATABASE_CREATE_TABLE_USERSTORY);	
		database.execSQL(DATABASE_CREATE_TABLE_TASK);	
		database.execSQL(DATABASE_CREATE_TABLE_PROJECT);		
		database.execSQL(DATABASE_CREATE_TABLE_SPRINT);	
		database.execSQL(DATABASE_CREATE_TABLE_MEMBER);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.i("upgrade",
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    if(newVersion == oldVersion + 1)
	    {
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERSTORY);
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT);
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPRINT);
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
	    }
	    onCreate(db);
	}
	
	


}
