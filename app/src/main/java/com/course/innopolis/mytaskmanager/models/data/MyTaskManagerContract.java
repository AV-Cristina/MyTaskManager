package com.course.innopolis.mytaskmanager.models.data;

import android.provider.BaseColumns;

/**
 * Created by Cristina on 11.07.2017.
 */

public class MyTaskManagerContract {

    private MyTaskManagerContract(){
    };


    public static final class UserEntry implements BaseColumns {
        public final static String TABLE_NAME = "users";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_LOGIN = "login";
        public final static String COLUMN_PASSWORD = "password";
        public final static String COLUMN_UNSUCCESSFUL_LOGIN_COUNT = "unsuccessful_login_count";
        public final static String COLUMN_ISACTIVE = "is_active";

        public final static int ACTIVE = 1;
        public final static int INACTIVE = 0;

        public final static int NONE = 0;
    }


    public static final class TaskEntry implements BaseColumns{
        public final static String TABLE_NAME = "tasks";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "task_name";
        public final static String COLUMN_ISALLDAY = "is_all_day";
        public final static String COLUMN_DESCRIPTION = "task_description";
        public final static String COLUMN_DATEBEG = "date_beg";
        public final static String COLUMN_DATEEND = "date_end";
        public final static String COLUMN_PERIODICITY = "task_periodicity";
        public final static String COLUMN_COLOR = "task_color";
        public final static String COLUMN_USER_ID = "user_id";

        public final static int ISALLDAY = 1;
        public final static int ISNOTALLDAY = 0;
    }
}
