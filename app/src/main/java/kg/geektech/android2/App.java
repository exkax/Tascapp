package kg.geektech.android2;

import android.app.Application;

import androidx.room.Room;

import kg.geektech.android2.room.AppDatabase;

public class App  extends Application {

    private AppDatabase appDatabase;
    private static App instance;

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance =  this;
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database.db")
                .allowMainThreadQueries()
                .build();
    }


}
