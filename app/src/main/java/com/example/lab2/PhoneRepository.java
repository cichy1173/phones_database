package com.example.lab2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneRepository {
    private ElementDao mElementDao;
    private LiveData<List<Phone>> mAllPhones;

    public PhoneRepository(Application application) {
        PhoneRoomDatabase phoneRoomDatabase =
                PhoneRoomDatabase.getDatabase(application);

        mElementDao = phoneRoomDatabase.elementDao();
        mAllPhones = mElementDao.getAlphabetizedElements();
    }

    public LiveData<List<Phone>> getAllPhones()
    {
        return mAllPhones;
    }

    public void deleteAll()
    {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            mElementDao.deleteAll();
        });
    }

    public void insert(Phone phone)
    {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mElementDao.insert(phone);
        });
    }

    public void deletePhone(Phone phone)
    {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mElementDao.deletePhone(phone);
        });
    }
}
