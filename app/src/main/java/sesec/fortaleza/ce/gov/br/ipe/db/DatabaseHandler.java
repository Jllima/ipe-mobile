package sesec.fortaleza.ce.gov.br.ipe.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import sesec.fortaleza.ce.gov.br.ipe.model.Usuario;

public class DatabaseHandler extends SQLiteOpenHelper {
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "android_api";

	// Login table name
	private static final String TABLE_USER = "user";

	// Login Table Columns names
	private static final String KEY_UID = "id";
	private static final String KEY_CODE = "code";
	private static final String KEY_NAME = "name";
	private static final String KEY_PHONE = "phone";
	private static final String KEY_PHONE_CEL = "cel";
	private static final String KEY_LOGIN = "login";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_AUTH_TOKEN = "authToken";


	// private static final String KEY_FOTO = "foto";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CLIENTE_TABLE = "CREATE TABLE " + TABLE_USER+ "("
				+ KEY_UID + " INTEGER PRIMARY KEY," 
				+ KEY_CODE + " TEXT,"
				+ KEY_NAME + " TEXT," 
				+ KEY_PHONE + " TEXT,"
				+ KEY_PHONE_CEL + " TEXT,"
				+ KEY_LOGIN + "TEXT,"
				+ KEY_EMAIL + "TEXT,"
				+ KEY_PASSWORD + "TEXT,"
				+ KEY_AUTH_TOKEN + "TEXT"
				+ ")";
		db.execSQL(CREATE_CLIENTE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

		// Create tables again
		onCreate(db);
	}

	public void addCliente(Usuario user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_CODE, user.getMatricula());
		values.put(KEY_NAME, user.getNome());
		values.put(KEY_PHONE, user.getTelefone());
		values.put(KEY_PHONE_CEL, user.getCelular());
		values.put(KEY_LOGIN, user.getLogin());
		values.put(KEY_EMAIL, user.getEmail());
		values.put(KEY_PASSWORD, user.getSenha());
		values.put(KEY_AUTH_TOKEN, user.getAuthToken());
		db.insert(TABLE_USER, null, values);
		db.close();
	}

	/**
	 * Getting user data from database
	 * */
	public Usuario getUserDetails() {
		Usuario user = new Usuario();
		String selectQuery = "SELECT  * FROM " + TABLE_USER;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.setMatricula(cursor.getString(1));
			user.setNome(cursor.getString(2));
			user.setTelefone(cursor.getString(3));
			user.setCelular(cursor.getString(4));
			user.setLogin(cursor.getString(5));
			user.setEmail(cursor.getString(6));
			user.setSenha(cursor.getString(7));
			user.setAuthToken(cursor.getString(8));
		}
		cursor.close();
		db.close();
		// return user
		return user;
	}

	/**
	 * Getting user login status return true if rows are there in table
	 * */
	public int getRowCount() {
		String countQuery = "SELECT  * FROM " + TABLE_USER;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();

		// return row count
		return rowCount;
	}

	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void resetTables() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_USER, null, null);
		db.close();
	}
}
