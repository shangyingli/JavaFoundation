package com.example.javafoundation;

import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.storage.StorageManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //申请
        try {
            getPackageSizeInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPackageSizeInfo() throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            StorageStatsManager storageStatsManager = (StorageStatsManager) getSystemService(Context.STORAGE_STATS_SERVICE);
            StorageStats storageStats = storageStatsManager.queryStatsForPackage(StorageManager.UUID_DEFAULT, "com.transsion.aicenter", Process.myUserHandle());
            Log.d(TAG, " pkgName : com.transsion.aicenter" + "pkgSize : " + storageStats.getAppBytes() + "");
        } else {
            PackageManager pm = getPackageManager();
            Method getPackageSize = PackageManager.class.getMethod("getPackageSizeInfo", String.class, IPackageStatsObserver.class);
            getPackageSize.invoke(pm, "com.transsion.phonemanager", observer);
        }

    }

    IPackageStatsObserver.Stub observer = new IPackageStatsObserver.Stub() {
        @Override
        public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
            long allSize = pStats.cacheSize + pStats.codeSize + pStats.dataSize;
            Log.d(TAG, allSize + "");
        }
    };
}
