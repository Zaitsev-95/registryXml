package itacademy;

import java.util.Set;

public class GeneralBasePatients implements GeneralBase {
    private Set<Patient> listPatients;
    private OnlineBase onlineBase;
    private OfflineBase offlineBase;

    @Override
    public Set<Patient> getGeneralBase() {
        onlineBase = new OnlineBase();
        offlineBase = new OfflineBase();
        listPatients = onlineBase.getOnlineInfo();
        listPatients.addAll(offlineBase.readInfo());
        return listPatients;
    }
}