package hr.tomljanovic.matko.seminarskirad.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import hr.tomljanovic.matko.seminarskirad.database.dbmode.LogArticle;

@Dao
public interface LogTable {

    @Query("SELECT * FROM LogArticle ORDER BY id DESC")
    List<LogArticle> selectAllTitles();

    @Query("SELECT * FROM LogArticle ORDER BY id DESC")
    List<LogArticle> selectAllUrls();

    @Query("SELECT * FROM LogArticle")
    List<LogArticle> selectAll();

    @Insert
    void insert(LogArticle article);

    @Delete
    void delete(LogArticle article);
}
