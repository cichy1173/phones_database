package com.example.lab2;

import android.content.Context;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Phone.class}, version = 1, exportSchema = false)
public abstract class PhoneRoomDatabase extends RoomDatabase {
    public abstract ElementDao elementDao();

    private static volatile PhoneRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static PhoneRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null)
        {
            synchronized (PhoneRoomDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PhoneRoomDatabase.class, "PhoneDatabase")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool (NUMBER_OF_THREADS);

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);
            databaseWriteExecutor.execute(()-> {
                ElementDao dao = INSTANCE.elementDao();
                Phone[] phones = {
                        new Phone("Google", "Pixel 4", "10", "google.pl"),
                        new Phone("Huawei", "P9", "7", "huawei.pl")
                };
                for(Phone p: phones){
                    dao.insert(p);
                }
            });
        }
    };

}
