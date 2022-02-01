package kg.geektech.android2.models;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.io.Serializable;

@Entity
public class News implements Serializable {


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    private String title;
    private  String createdAt;
    @PrimaryKey(autoGenerate = true)
    private long id;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public News(String title, String createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }
}
