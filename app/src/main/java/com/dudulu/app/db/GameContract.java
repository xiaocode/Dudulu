package com.dudulu.app.db;

import android.provider.BaseColumns;

/**
 * Created by Vincent on 4/14/14.
 */
public final class GameContract {
    public GameContract() {}
    public static abstract class GameEntry implements BaseColumns {
        public static final String TABLE_NAME = "game";
        public static final String COLUMN_NAME_GAME_ID = "game_id";
        public static final String COLUMN_NAME_ICON = "icon";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        public static final String COLUMN_NAME_DOWNLOAD_URL = "download_url";
//        public static final String COLUMN_NAME_GUIDE_URL = "game";
//        public static final String COLUMN_NAME_CIRCLE_URL = "game";
        public static final String COLUMN_NAME_VERSION_CODE = "version_code";
        public static final String COLUMN_NAME_VERSION_NAME = "version_name";
        public static final String COLUMN_NAME_PACKAGE_NAME = "package_name";
        public static final String COLUMN_NAME_PACKAGE_SIZE = "package_size";
        public static final String COLUMN_NAME_MIN_SDK = "min_SDK";
        public static final String COLUMN_NAME_TARGET_SDK = "target_SDK";
        public static final String COLUMN_NAME_UPDATED_TIME = "updated_time";
        public static final String COLUMN_NAME_INTRO = "intro";
        public static final String COLUMN_NAME_INTRO_GALLERY = "intro_gallery";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_LOCALIZATION = "localization";
    }
    public static final String INT_TYPE = " INTEGER";
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + GameEntry.TABLE_NAME + " (" +
                    GameEntry._ID + " INTEGER PRIMARY KEY," +
                    GameEntry.COLUMN_NAME_GAME_ID + INT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_ICON + TEXT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_SUBTITLE + TEXT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_DOWNLOAD_URL + TEXT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_VERSION_CODE + INT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_VERSION_NAME + TEXT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_PACKAGE_NAME + TEXT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_PACKAGE_SIZE + TEXT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_UPDATED_TIME + INT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_INTRO + TEXT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_INTRO_GALLERY + TEXT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_COMPANY + INT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_LOCALIZATION + INT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_MIN_SDK + INT_TYPE + COMMA_SEP +
                    GameEntry.COLUMN_NAME_TARGET_SDK + INT_TYPE +
            " )";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + GameEntry.TABLE_NAME;
}
