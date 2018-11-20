package com.example.ekelly.magicthegathering;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class MagicTheGatheringApp extends Application {
    private static final String LOGENTRIES_LOG = "LogentriesLogStorage.log";
    private static MagicTheGatheringApp mInstance;
    private static Context mContext;

    public MagicTheGatheringApp() {
        mInstance = this;
    }

    public static MagicTheGatheringApp get(Context context) {
        return (MagicTheGatheringApp) context.getApplicationContext();
    }

    public static Context getAppContext() {
        return MagicTheGatheringApp.mContext;
    }

    public static MagicTheGatheringApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MagicTheGatheringApp.mContext = getApplicationContext();
        try {
            File logFile = new File(this.getFilesDir(), LOGENTRIES_LOG);
            if (!logFile.exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            Log.i("TAG", e.getMessage());
        }
    }
}
