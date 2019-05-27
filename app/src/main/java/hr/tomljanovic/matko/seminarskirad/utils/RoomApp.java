package hr.tomljanovic.matko.seminarskirad.utils;

import android.app.Application;
import android.arch.persistence.room.Room;

import hr.tomljanovic.matko.seminarskirad.database.RoomDb;

public class RoomApp extends Application {

    public static RoomDb database;

    @Override
    public void onCreate() {
        super.onCreate();

        database = Room.databaseBuilder(getApplicationContext(), RoomDb.class, "RoomDb")
                .allowMainThreadQueries()
                .build();
    }
}
