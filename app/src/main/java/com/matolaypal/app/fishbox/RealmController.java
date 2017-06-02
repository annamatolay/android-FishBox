package com.matolaypal.app.fishbox;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

class RealmController {
    private final String TAG = MyLog.getTag(this);
    private static RealmController instance;
    private final Realm realm;

    private RealmController(Application app) {
        Realm.init(app);
        realm = Realm.getDefaultInstance();
    }

    static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController getInstance() {
        return instance;
    }

    Realm getRealm() {
        return realm;
    }

    FishModel create(FishModel model) {
        try {
            realm.beginTransaction();
            realm.copyToRealm(model);
            realm.commitTransaction();
        } catch (RealmException e) {
            Log.e(TAG, "#"+e.getMessage());
        }
        Log.i(TAG, "REALM CREATE: " + model);
        return model;
    }

    FishModel readFirst() {
        FishModel model = null;
        try {
            realm.beginTransaction();
            model = realm.where(FishModel.class).findFirst();
        } catch (RealmException e) {
            Log.e(TAG, "#"+e.getMessage());
        }
        Log.i(TAG, "REALM READ:  " + model);
        return model;
    }

    RealmResults<FishModel> readAll() {
        return realm.where(FishModel.class).findAll();
    }

    void close() {
        realm.close();
        Log.i(TAG, "REALM CLOSED");
    }
}
