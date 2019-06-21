package dominando.android.mysnsserviodeurgncia.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class CheckOutProviderRealm {

    // Singleton pattern

    private static CheckOutProviderRealm _instance;

    private CheckOutProviderRealm() {}

    public static CheckOutProviderRealm getInstance(Context context) {
        if (_instance == null) {
            _instance = new CheckOutProviderRealm();
            _instance.initializeData(context);
        }
        return _instance;
    }


    private static final String REALM_ASSET_FILEPATH = "checkout_import_20190407.realm"; // Path to Realm file in assets folder
    private static final String REALM_FILENAME = "checkout_data_v01.realm"; // Name for app internal Real file


    private Realm realm;

    ArrayList<CheckOut> checkOutData = new ArrayList<CheckOut>();




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
        RealmResults<CheckOut> checkOutRealmResults = realm.where(CheckOut.class).findAll();
        Log.d("REALM DEBUG", "Read from " + checkOutRealmResults.size() + " records from Realm file.");


        checkOutData = new ArrayList<CheckOut>( checkOutRealmResults);
    }

    public void beginTransaction() {
        realm.beginTransaction();
    }

    public void commitTransaction() {
        realm.commitTransaction();
    }

    public void guardarCheckout(CheckOut checkOut){
        realm.beginTransaction();

        CheckOut realmCheckout= realm.copyToRealm(checkOut);
        realm.commitTransaction();
        Log.d("REALM DEBUG", "Transaction Committed");

        checkOutData.add(checkOut);
    }

    public CheckOut getCheckOut() {

        CheckOut checkOut = realm.where(CheckOut.class).findFirst();
        Log.d("REALM DEBUG", "Got CheckOut: " + ((checkOut != null)? checkOut.getUuid() : "NONE") );
        return checkOut;
    }

    public ArrayList<CheckOut> getCheckOutData() {
        return checkOutData;
    }
}
