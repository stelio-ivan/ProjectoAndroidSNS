package dominando.android.mysnsserviodeurgncia.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class HistoricoProvider {
// Singleton pattern

    private static HistoricoProvider _instance;





    private HistoricoProvider() {

    }


    public static HistoricoProvider getInstance(Context context) {
        if (_instance == null) {
            _instance = new HistoricoProvider();
            _instance.initializeData(context);
        }
        return _instance;
    }

    private static final String REALM_ASSET_FILEPATH = "historico_import.realm"; // Path to Realm file in assets folder
    private static final String REALM_FILENAME = "historico_data_v01.realm"; // Name for app internal Real file

    private Realm realm;


    private List<Historico> historicoData = new ArrayList<>();

    private void initializeData(Context context) {
        // Initialize Realm (just once per application)
        Realm.init(context);

        // Create a custom configuration
        // Read more on https://realm.io/docs/java/latest/api/io/realm/RealmConfiguration.Builder.html
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name(REALM_FILENAME) // Name to be used internally
                .assetFile(REALM_ASSET_FILEPATH) // Copy Realm file from this location, searching in Android asset folder
                .schemaVersion(0) // If Realm Objects change, schema version needs to be increased
                .build();

        // Get instance
        realm = Realm.getInstance(realmConfig);
        Log.d("REALM DEBUG", "Realm file path: " + realm.getPath() + ", version: " + realm.getVersion() + " is " + ((!realm.isEmpty())?"not ":"") + "empty");

        RealmResults<Historico> historicosRealmResults = realm.where(Historico.class).findAll();
        Log.d("REALM DEBUG", "Read from " +  historicosRealmResults.size() + " records from Realm file.");


        historicoData = new ArrayList<Historico>(historicosRealmResults);

        Historico hist2 = new Historico( "São Spalla");
        hist2.setCheckInDate();
        hist2.setCheckOutDate();
        //guardarHistorico(hist2);

        //historicoData.add(hist1);
        historicoData.add(hist2);

    }

    public void beginTransaction() {
        realm.beginTransaction();
    }

    public void commitTransaction() {
        realm.commitTransaction();
    }

    public void guardarHistorico(Historico newhistorico){
        Log.d("REALM DEBUG", "Transaction Init for adding hospital '" + newhistorico.getNomeHospital() + "'");
        realm.beginTransaction();

        Historico realmHistorico=realm.copyToRealm(newhistorico);

        realm.commitTransaction();
        Log.d("REALM DEBUG", "Transaction Committed");


        historicoData.add(realmHistorico);
    }

    public List<Historico> getHistoricos() {
        // Get all items from given class
        RealmResults<Historico> historicosRealmResults = realm.where(Historico.class).findAll();
        Log.d("REALM DEBUG", "Read from " + historicosRealmResults.size() + " records from Realm file.");

        // Save results to class instance ArrayList (to abstract from Realm)
        historicoData = new ArrayList<Historico>(historicosRealmResults);
        return historicoData;
    }




}