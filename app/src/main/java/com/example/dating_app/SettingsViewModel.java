package com.example.dating_app;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class SettingsViewModel extends ViewModel {

    public static LiveData<List<Settings>> loadSettings(Context context) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        return db.daoSetting().getSettings();
    }

    public void saveSettings(Context context, Settings... settings) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.daoSetting().saveSettings(settings);
        });
    }

    public void deleteSettings(Context context) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(db::clearAllTables);
    }
}
