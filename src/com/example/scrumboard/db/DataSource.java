package com.example.scrumboard.db;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.scrumboard.model.Member;
import com.example.scrumboard.model.Project;
import com.example.scrumboard.model.Sprint;
import com.example.scrumboard.model.Task;
import com.example.scrumboard.model.UserStory;

public class DataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;

	public DataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	/**
	 * Gets the database to enable access.
	 * 
	 * @throws SQLException
	 */
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	/**
	 * Closes the database.
	 */
	public void close() {
		dbHelper.close();
	}
	
	public long updateStatus(int taskId, String status)
	{
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_STATUS, status);
		long id = database.update(MySQLiteHelper.TABLE_TASK, values, MySQLiteHelper.COLUMN_ID + "="+taskId, null);
		return id;
	}

	
	
	public long insertProject(Project project) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_ID_USERSTORY, project.getUserstoryId());
		values.put(MySQLiteHelper.COLUMN_NAME, project.getName());
		values.put(MySQLiteHelper.COLUMN_DESCRIPTION, project.getDescription());
		values.put(MySQLiteHelper.COLUMN_START_DATE, project.getStartDate()
				.toString());
		values.put(MySQLiteHelper.COLUMN_STATUS, project.getStatus().toString());
		values.put(MySQLiteHelper.COLUMN_MASTER, project.getMaster());
		values.put(MySQLiteHelper.COLUMN_PRIORITY, project.getPriority());
		values.put(MySQLiteHelper.COLUMN_DEADLINE, project.getDeadline()
				.toString());
		long id = database.insert(MySQLiteHelper.TABLE_PROJECT, null, values);
		return id;
	}

	public long insertUserStory(UserStory userStory) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME, userStory.getName());
		values.put(MySQLiteHelper.COLUMN_DETAILS, userStory.getDetails());
		values.put(MySQLiteHelper.COLUMN_STATUS, userStory.getStatus());
		values.put(MySQLiteHelper.COLUMN_PRIORITY, userStory.getPriority());
		values.put(MySQLiteHelper.COLUMN_ESTIMATED_TIME,
				userStory.getEstimatedTime());
		values.put(MySQLiteHelper.COLUMN_ID_SPRINT, userStory.getSprintId());
		values.put(MySQLiteHelper.COLUMN_COMMENT, userStory.getComment());
		long id = database.insert(MySQLiteHelper.TABLE_USERSTORY, null, values);
		return id;
	}

	public long insertTask(Task task) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_ID_USERSTORY, task.getUserstoryId());
		values.put(MySQLiteHelper.COLUMN_ID_MEMBER, task.getMemberId());
		values.put(MySQLiteHelper.COLUMN_ESTIMATED_TIME,
				task.getEstimatedTime());
		values.put(MySQLiteHelper.COLUMN_STATUS, task.getStatus());
		values.put(MySQLiteHelper.COLUMN_NAME, task.getName());
		values.put(MySQLiteHelper.COLUMN_DEADLINE, task.getDeadline()
				.toString());
		values.put(MySQLiteHelper.COLUMN_COMMENT, task.getComment());			
		long id = database.insert(MySQLiteHelper.TABLE_TASK, null, values);
		return id;
	}

	public long insertSprint(Sprint sprint) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_ID_PROJECT, sprint.getProjectId());
		values.put(MySQLiteHelper.COLUMN_START_DATE, sprint.getStartDate()
				.toString());
		values.put(MySQLiteHelper.COLUMN_END_DATE, sprint.getEndDate()
				.toString());
		long id = database.insert(MySQLiteHelper.TABLE_SPRINT, null, values);
		return id;
	}

	public long insertMember(Member member) {
		Log.i(DataSource.class.getCanonicalName(), "Inside insertMemeber()");
		if (member != null) {
			open();
			ContentValues values = new ContentValues();

			values.put(MySQLiteHelper.COLUMN_NAME, member.getName());
			values.put(MySQLiteHelper.COLUMN_SURNAME, member.getSurname());
			values.put(MySQLiteHelper.COLUMN_PASSWORD, member.getPassword());
			values.put(MySQLiteHelper.COLUMN_EMAIL, member.getMail());
			long newId;

			newId = database.insert(MySQLiteHelper.TABLE_MEMBER, null, values);

			close();
			Log.i(DataSource.class.toString(),
					"Inserting Member: " + member.toString());
			return newId;
		} else {
			return -1;
		}
	}

	public Project selectProject(int id) {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PROJECT, null,
				null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int pid = cursor.getInt(0);
			if (pid == id) {
				DateFormat format = new SimpleDateFormat("MM d, yyyy",
						Locale.ENGLISH);
				Date date;
				Date deadline;
				try {
					date = format.parse(cursor.getString(4));
					deadline = format.parse(cursor.getString(8));
					Project p = new Project(cursor.getInt(0), cursor.getInt(1),
							cursor.getString(2), cursor.getString(3), date,
							cursor.getString(5), cursor.getInt(6),
							cursor.getInt(7), deadline);
					return p;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			cursor.moveToNext();
		}
		return null;
	}

	public UserStory selectUserStory(int id) {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PROJECT, null,
				null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int uid = cursor.getInt(0);
			if (uid == id) {

				UserStory u = new UserStory(cursor.getInt(0),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getInt(4),
						cursor.getInt(5), cursor.getInt(6), cursor.getString(7));
				return u;

			}
			cursor.moveToNext();
		}
		return null;
	}

	public Task selectTask(int id) {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PROJECT, null,
				null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int pid = cursor.getInt(0);
			if (pid == id) {
				DateFormat format = new SimpleDateFormat("MM d, yyyy",
						Locale.ENGLISH);

				Date deadline;
				try {

					deadline = format.parse(cursor.getString(6));
					Task t = new Task(cursor.getInt(0), cursor.getInt(1),
							cursor.getInt(2), cursor.getInt(3),
							cursor.getString(4), cursor.getString(5), deadline,
							cursor.getString(7));
					return t;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			cursor.moveToNext();
		}
		return null;
	}

	public Member selectMember(int id) {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PROJECT, null,
				null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int uid = cursor.getInt(0);
			if (uid == id) {

				Member m = new Member(cursor.getInt(0), cursor.getString(1),
						cursor.getString(2), cursor.getString(3),
						cursor.getString(4));
				return m;

			}
			cursor.moveToNext();
		}
		return null;
	}

	public Sprint selectSprint(int id) {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PROJECT, null,
				null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int uid = cursor.getInt(0);
			if (uid == id) {

				DateFormat format = new SimpleDateFormat("MM d, yyyy",
						Locale.ENGLISH);
				Date startDate;
				Date endDate;
				try {
					startDate = format.parse(cursor.getString(2));
					endDate = format.parse(cursor.getString(3));
					Sprint s = new Sprint(cursor.getInt(0), cursor.getInt(1),
							startDate, endDate);
					return s;

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			cursor.moveToNext();
		}
		return null;
	}

	public ArrayList<Project> selectAllProjects() {
		ArrayList <Project> result = new ArrayList <Project> ();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PROJECT, null,
				null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int pid = cursor.getInt(0);
			Project p = selectProject(pid);
			result.add(p);
			cursor.moveToNext();
		}
		return result;
	}

	public ArrayList<UserStory> selectAllUserStories() {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_USERSTORY, null, null,
				null, null, null, null);
		cursor.moveToFirst();
		ArrayList<UserStory> userStories = new ArrayList<UserStory>();
		while (!cursor.isAfterLast()) {
			
			UserStory u = new UserStory(cursor.getInt(0),
					cursor.getString(1), cursor.getString(2),
					cursor.getString(3), cursor.getInt(4),
					cursor.getInt(5), cursor.getInt(6), cursor.getString(7));
			userStories.add(u);
			cursor.moveToNext();
		}
		return userStories;
	}

	public ArrayList<Task> selectAllTasks() {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_TASK, null, null,
				null, null, null, null);
		cursor.moveToFirst();
		ArrayList<Task> tasks = new ArrayList<Task>();
		while (!cursor.isAfterLast()) {
			
			DateFormat format = new SimpleDateFormat("yyyy-mm-dd",
					Locale.ENGLISH);

			Date deadline;
			Task t = null;
			try {

				deadline = format.parse(cursor.getString(6));
				t = new Task(cursor.getInt(0), cursor.getInt(1),
						cursor.getInt(2), cursor.getInt(3),
						cursor.getString(4), cursor.getString(5), deadline,
						cursor.getString(7));
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tasks.add(t);
			System.out.println("x" + String.valueOf(tasks.get(0)));
			cursor.moveToNext();
		}
		return tasks;
	}
	
	public ArrayList<Task> selectAllToDoTasks() {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_TASK, null, null,
				null, null, null, null);
		cursor.moveToFirst();
		ArrayList<Task> tasks = new ArrayList<Task>();
		while (!cursor.isAfterLast()) {

			if(cursor.getString(4).equals("ToDo")) {
				DateFormat format = new SimpleDateFormat("yyyy-mm-dd",
						Locale.ENGLISH);
	
				Date deadline;
				Task t = null;
				try {
	
					deadline = format.parse(cursor.getString(6));
					t = new Task(cursor.getInt(0), cursor.getInt(1),
							cursor.getInt(2), cursor.getInt(3),
							cursor.getString(4), cursor.getString(5), deadline,
							cursor.getString(7));
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tasks.add(t);
			}
			cursor.moveToNext();
		}
		return tasks;
	}
	
	public ArrayList<Task> selectAllInProgressTasks() {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_TASK, null, null,
				null, null, null, null);
		cursor.moveToFirst();
		ArrayList<Task> tasks = new ArrayList<Task>();
		while (!cursor.isAfterLast()) {

			if(cursor.getString(4).equals("InProgress")) {
				DateFormat format = new SimpleDateFormat("yyyy-mm-dd",
						Locale.ENGLISH);
	
				Date deadline;
				Task t = null;
				try {
	
					deadline = format.parse(cursor.getString(6));
					t = new Task(cursor.getInt(0), cursor.getInt(1),
							cursor.getInt(2), cursor.getInt(3),
							cursor.getString(4), cursor.getString(5), deadline,
							cursor.getString(7));
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tasks.add(t);
			}
			cursor.moveToNext();
		}
		return tasks;
	}
	
	public ArrayList<Task> selectAllDoneTasks() {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_TASK, null, null,
				null, null, null, null);
		cursor.moveToFirst();
		ArrayList<Task> tasks = new ArrayList<Task>();
		while (!cursor.isAfterLast()) {

			if(cursor.getString(4).equals("Completed")) {
				DateFormat format = new SimpleDateFormat("yyyy-mm-dd",
						Locale.ENGLISH);
	
				Date deadline;
				Task t = null;
				try {
	
					deadline = format.parse(cursor.getString(6));
					t = new Task(cursor.getInt(0), cursor.getInt(1),
							cursor.getInt(2), cursor.getInt(3),
							cursor.getString(4), cursor.getString(5), deadline,
							cursor.getString(7));
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tasks.add(t);
			}
			cursor.moveToNext();
		}
		return tasks;
	}

	public ArrayList<Member> selectAllMembers() {
		ArrayList <Member> result = new ArrayList <Member> ();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PROJECT, null,
				null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int pid = cursor.getInt(0);
			Member m = selectMember(pid);
			result.add(m);
			cursor.moveToNext();
		}
		return result;
	}

	public ArrayList<Sprint> selectAllSprints() {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_SPRINT, null, null,
				null, null, null, null);
		cursor.moveToFirst();
		ArrayList<Sprint> sprints = new ArrayList<Sprint>();
		while (!cursor.isAfterLast()) {
			
			DateFormat format = new SimpleDateFormat("yyyy-mm-dd",
					Locale.ENGLISH);
			Date startDate;
			Date endDate;
			Sprint s = null;
			try {
				startDate = format.parse(cursor.getString(2));
				endDate = format.parse(cursor.getString(3));
				s = new Sprint(cursor.getInt(0), cursor.getInt(1),
						startDate, endDate);
				

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(s);
			sprints.add(s);
			cursor.moveToNext();
		}
		return sprints;
	}
	
	public boolean verifyMember(Member member) {
        if (member != null) {            
            
            String query = "SELECT " + MySQLiteHelper.COLUMN_PASSWORD + "," + 
            						   MySQLiteHelper.COLUMN_EMAIL +
            						   " FROM " + MySQLiteHelper.TABLE_MEMBER +
            						   " WHERE " + MySQLiteHelper.COLUMN_PASSWORD + "='" + member.getPassword() + "'" +
            						   " AND " + MySQLiteHelper.COLUMN_EMAIL + "='" + member.getMail() + "'";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();            
            
            if(!cursor.isNull(0)) return true;
        } 
        return false;
        
    }
}


