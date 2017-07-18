package com.course.innopolis.mytaskmanager;

import com.course.innopolis.mytaskmanager.Model.data.MyTaskManagerDBHelper;

/**
 * Created by Cristina on 16.07.2017.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate(){
        super.onCreate();
        MyTaskManagerDBHelper.init(getApplicationContext());
    }
}
