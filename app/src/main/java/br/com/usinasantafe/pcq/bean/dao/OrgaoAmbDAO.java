package br.com.usinasantafe.pcq.bean.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.bean.variaveis.OrgAmbBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class OrgaoAmbDAO {

    public OrgaoAmbDAO() {
    }

    public void setOrgAmbCabec(ArrayList<Long> orgAmbCabec, Long idCabec){
        for(int i = 0; i < orgAmbCabec.size(); i++){
            OrgAmbBean orgAmbBean = new OrgAmbBean();
            orgAmbBean.setIdCabec(idCabec);
            orgAmbBean.setIdOrgAmb(orgAmbCabec.get(i));
            orgAmbBean.setDthrOrgAmb(Tempo.getInstance().dataComHora());
            orgAmbBean.insert();
        }
    }

    public JsonArray dadosEnvioOrgaoAmbiental(Long idCabec){
        OrgAmbBean orgAmbBean = new OrgAmbBean();
        List orgAmbList = orgAmbBean.get("idCabec", idCabec);
        JsonArray orgAmbJsonArray = new JsonArray();
        for (int i = 0; i < orgAmbList.size(); i++) {
            orgAmbBean = (OrgAmbBean) orgAmbList.get(i);
            Gson orgAmbGson = new Gson();
            orgAmbJsonArray.add(orgAmbGson.toJsonTree(orgAmbBean, orgAmbBean.getClass()));
        }
        orgAmbList.clear();
        return orgAmbJsonArray;
    }

}
