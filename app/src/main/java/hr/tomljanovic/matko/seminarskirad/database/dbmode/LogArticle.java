package hr.tomljanovic.matko.seminarskirad.database.dbmode;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class LogArticle {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "title_log")
    private String title;

    @ColumnInfo(name = "url_log")
    private String url;

    @Ignore
    public LogArticle(int id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public LogArticle(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return title + ' ' +
                url;
    }
}
