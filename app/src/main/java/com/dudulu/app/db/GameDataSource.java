package com.dudulu.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 4/14/14.
 */
public class GameDataSource {
    private SQLiteDatabase database;
    private GameDbHelper dbHelper;
    private String[] allColumns = {
            GameContract.GameEntry._ID,
            GameContract.GameEntry.COLUMN_NAME_GAME_ID,
            GameContract.GameEntry.COLUMN_NAME_ICON,
            GameContract.GameEntry.COLUMN_NAME_TITLE,
            GameContract.GameEntry.COLUMN_NAME_SUBTITLE,
            GameContract.GameEntry.COLUMN_NAME_INTRO,
            GameContract.GameEntry.COLUMN_NAME_INTRO_GALLERY,
            GameContract.GameEntry.COLUMN_NAME_VERSION_CODE,
            GameContract.GameEntry.COLUMN_NAME_VERSION_NAME,
            GameContract.GameEntry.COLUMN_NAME_PACKAGE_NAME,
            GameContract.GameEntry.COLUMN_NAME_PACKAGE_SIZE,
            GameContract.GameEntry.COLUMN_NAME_DOWNLOAD_URL,
            GameContract.GameEntry.COLUMN_NAME_COMPANY,
            GameContract.GameEntry.COLUMN_NAME_LOCALIZATION,
            GameContract.GameEntry.COLUMN_NAME_MIN_SDK,
            GameContract.GameEntry.COLUMN_NAME_TARGET_SDK
    };
    private String[] baseColumn = {
            GameContract.GameEntry._ID,
            GameContract.GameEntry.COLUMN_NAME_GAME_ID,
            GameContract.GameEntry.COLUMN_NAME_ICON,
            GameContract.GameEntry.COLUMN_NAME_TITLE,
            GameContract.GameEntry.COLUMN_NAME_SUBTITLE
    };

    public GameDataSource(Context context) {
        dbHelper = new GameDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public BaseGame createBaseGame(BaseGame baseGame) {
        ContentValues values = new ContentValues();
        values.put(GameContract.GameEntry.COLUMN_NAME_GAME_ID,baseGame.getGame_id());
        values.put(GameContract.GameEntry.COLUMN_NAME_ICON,baseGame.getIcon());
        values.put(GameContract.GameEntry.COLUMN_NAME_TITLE,baseGame.getTitle());
        values.put(GameContract.GameEntry.COLUMN_NAME_SUBTITLE,baseGame.getSubtitle());
        long insertId = database.insert(GameContract.GameEntry.TABLE_NAME,null,values);
        Cursor cursor = database.query(
                GameContract.GameEntry.TABLE_NAME,
                baseColumn,
                GameContract.GameEntry._ID + "=" + insertId,
                null,
                null,
                null,
                null
                );
        cursor.moveToFirst();
        BaseGame newBaseGame = cursorToBaseGame(cursor);
        cursor.close();
        Log.v("database","insert new base game, game_id :"+newBaseGame.getGame_id());
        return newBaseGame;
    }

    public void deleteBaseGame(BaseGame baseGame) {
        long id = baseGame.getId();
        database.delete(
                GameContract.GameEntry.TABLE_NAME,
                GameContract.GameEntry._ID + "=" + id,
                null
        );
        Log.v("database","delete base game by _id :"+id);
    }

    public boolean updateBaseGame(BaseGame baseGame) {
        ContentValues values = new ContentValues();
        values.put(GameContract.GameEntry.COLUMN_NAME_ICON,baseGame.getIcon());
        values.put(GameContract.GameEntry.COLUMN_NAME_TITLE,baseGame.getTitle());
        values.put(GameContract.GameEntry.COLUMN_NAME_SUBTITLE,baseGame.getSubtitle());

        String selection = GameContract.GameEntry.COLUMN_NAME_GAME_ID + " = ?";
        String[] selectionArgs = { String.valueOf(baseGame.getGame_id()) };

        int count = database.update(
                GameContract.GameEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        return count>0?true:false;
    }

    public List<BaseGame> getBaseGameList(int page,int limit) {
        List<BaseGame> baseGameList = new ArrayList<BaseGame>();
        String orderBy = GameContract.GameEntry.COLUMN_NAME_GAME_ID;
        Cursor cursor = database.query(
                GameContract.GameEntry.TABLE_NAME,
                baseColumn,
                null,
                null,
                null,
                orderBy,
                (page-1)*limit + "," + (limit-1)
        );
        cursor.moveToFirst();
        while (cursor.isAfterLast()){
            BaseGame baseGame = cursorToBaseGame(cursor);
            baseGameList.add(baseGame);
            cursor.moveToNext();
        }
        cursor.close();
        return baseGameList;
    }

    public BaseGame cursorToBaseGame(Cursor cursor) {
        BaseGame baseGame = new BaseGame();
        baseGame.setId(
                cursor.getLong(
                        cursor.getColumnIndexOrThrow(GameContract.GameEntry._ID)
                )
        );
        baseGame.setGame_id(
                cursor.getLong(
                        cursor.getColumnIndexOrThrow(GameContract.GameEntry.COLUMN_NAME_GAME_ID)
                )
        );
        baseGame.setIcon(
                cursor.getString(
                        cursor.getColumnIndexOrThrow(GameContract.GameEntry.COLUMN_NAME_ICON)
                )
        );
        baseGame.setTitle(
                cursor.getString(
                        cursor.getColumnIndexOrThrow(GameContract.GameEntry.COLUMN_NAME_TITLE)
                )
        );
        baseGame.setSubtitle(
                cursor.getString(
                        cursor.getColumnIndexOrThrow(GameContract.GameEntry.COLUMN_NAME_SUBTITLE)
                )
        );
        return baseGame;
    }

}
