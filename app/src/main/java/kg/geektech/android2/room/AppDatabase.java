package kg.geektech.android2.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kg.geektech.android2.models.News;


@Database(entities =  {News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public  abstract  NewsDao newsDao();
}
