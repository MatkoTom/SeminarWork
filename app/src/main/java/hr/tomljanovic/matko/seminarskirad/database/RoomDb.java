package hr.tomljanovic.matko.seminarskirad.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import hr.tomljanovic.matko.seminarskirad.database.dbmode.LogArticle;

@Database(entities = {LogArticle.class}, version = 1, exportSchema = false)
public abstract class RoomDb extends RoomDatabase {
    public abstract LogTable tableLog();
}
