package dominando.android.mysnsserviodeurgncia.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import io.realm.*;

public class CheckInProviderRealm {

    // Singleton pattern

    private static CheckInProviderRealm _instance;

    private CheckInProviderRealm() {}

    public static CheckInProviderRealm getInstance(Context context) {
        if (_instance == null) {
            _instance = new CheckInProviderRealm();
            _instance.initializeData(context);
        }
        return _instance;
    }


    private static final String REALM_ASSET_FILEPATH = "checkin_import_20190407.realm"; // Path to Realm file in assets folder
    private static final String REALM_FILENAME = "checkin_data_v01.realm"; // Name for app internal Real file


    private Realm realm;

    ArrayList<CheckIn> checkInData = new ArrayList<CheckIn>();




    private void initializeData(Context context) {
        // Initialize Realm (just once per application)
        Realm.init(context);


        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name(REALM_FILENAME) // Name to be used internally
                .assetFile(REALM_ASSET_FILEPATH) // Copy Realm file from this location, searching in Android asset folder
                .schemaVersion(0) // If Realm Objects change, schema version needs to be increased
                .build();

        // Get instance
        realm = Realm.getInstance(realmConfig);
        Log.d("REALM DEBUG", "Realm file path: " + realm.getPath() + ", version: " + realm.getVersion() + " is " + ((!realm.isEmpty())?"not ":"") + "empty");

        // Get all items from given class
        RealmResults<CheckIn> checkInRealmResults = realm.where(CheckIn.class).findAll();
        Log.d("REALM DEBUG", "Read from " + checkInRealmResults.size() + " records from Realm file.");


        checkInData = new ArrayList<CheckIn>( checkInRealmResults);
    }

    public void beginTransaction() {
        realm.beginTransaction();
    }

    public void commitTransaction() {
        realm.commitTransaction();
    }

    public void guardarCheckin(CheckIn checkIn){
        realm.beginTransaction();

        CheckIn realmCheckin= realm.copyToRealm(checkIn);
        realm.commitTransaction();
        Log.d("REALM DEBUG", "Transaction Committed");

        checkInData.add(checkIn);
    }

    public CheckIn getCheckIn() {

        CheckIn checkIn = realm.where(CheckIn.class).findFirst();
        Log.d("REALM DEBUG", "Got CheckIn: " + ((checkIn != null)? checkIn.getUuid() : "NONE") );
        return checkIn;
    }

    public ArrayList<CheckIn> getCheckInData() {
        return checkInData;
    }
}
