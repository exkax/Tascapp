package kg.geektech.android2.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import kg.geektech.android2.models.News;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news ")
    List<News> getAll();

    @Insert
    void insert(News news);

    @Delete
    void delete(News news);

    @Query("SELECT * FROM news ORDER BY title ASC")
    List<News>  sortAll();
}
