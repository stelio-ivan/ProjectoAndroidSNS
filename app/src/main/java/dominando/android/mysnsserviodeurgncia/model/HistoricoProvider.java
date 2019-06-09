package dominando.android.mysnsserviodeurgncia.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dominando.android.mysnsserviodeurgncia.model.CheckIn;
import dominando.android.mysnsserviodeurgncia.model.Historico;

public class HistoricoProvider  {
// Singleton pattern

    private static HistoricoProvider _instance;
    private CheckInProviderRealm mCheckInprovider;
    private CheckOutProviderRealm mCheckOutprovider;


    private HistoricoProvider() {
        initializeData();
    }


    public static HistoricoProvider getInstance() {
        if (_instance == null) {
            _instance = new HistoricoProvider();
        }
        return _instance;
    }


    private List<Historico> historicolData = new ArrayList<>();


    private void initializeData() {




        List<CheckIn> checkInList=mCheckInprovider.getCheckInData();
        List<CheckOut> checkOutList=mCheckOutprovider.getCheckOutData();

        for(int ci=0, co=0; ci<checkInList.size() || co<checkOutList.size(); ci++, co++){
            CheckIn checkIn=checkInList.get(ci);
            CheckOut checkOut=checkOutList.get(co);
            Historico h1= new Historico(checkIn.getUuid(), checkOut.getNomeHospital());
            h1.setCheckIn(checkIn);
            h1.setCheckOut(checkOut);
            historicolData.add(h1);

        }




//        Historico hist1 = new Historico("001", "Santa Maria");
//        CheckIn checkStaMaria1= new CheckIn();
//        CheckOut checkOutStaMaria1= new CheckOut();
//        hist1.setCheckIn(checkStaMaria1);
//        hist1.setCheckOut(checkOutStaMaria1);

//        Historico hist2 = new Historico("002", "São Jose");
//        hist2.CheckIn();
//        hist2.CheckOut();
//        Historico hist3 = new Historico("003", "São Jose");
//        hist3.CheckIn();
//        hist3.CheckOut();
//        Historico hist4 = new Historico("004", "Santa Maria");
//        hist4.CheckIn();
//        hist4.CheckOut();

  //      historicolData.add(hist1);
//        historicolData.add(hist2);
//        historicolData.add(hist3);
//        historicolData.add(hist4);
    }

    public List<Historico> getHistorico() {
        return historicolData;
    }

    public Historico getHistorico(String historicoUuid) {
        for (int i=0; i < historicolData.size(); i++) {
            if (historicolData.get(i).getUuid() != null && historicolData.get(i).getUuid().equalsIgnoreCase(historicoUuid)) {
                return historicolData.get(i);
            }
        }
        return null;
    }
}