package br.com.usinasantafe.pcq.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.variaveis.OrgaoAmbItemBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class OrgaoAmbDAO {

    public OrgaoAmbDAO() {
    }

    public void setOrgAmbCabec(ArrayList<Long> orgAmbCabec, Long idCabec){
        for(int i = 0; i < orgAmbCabec.size(); i++){
            OrgaoAmbItemBean orgaoAmbItemBean = new OrgaoAmbItemBean();
            orgaoAmbItemBean.setIdCabec(idCabec);
            orgaoAmbItemBean.setIdOrgAmb(orgAmbCabec.get(i));
            orgaoAmbItemBean.setDthrOrgAmb(Tempo.getInstance().dataComHora());
            orgaoAmbItemBean.insert();
        }
    }

    public JsonArray dadosEnvioOrgaoAmbiental(Long idCabec){
        OrgaoAmbItemBean orgaoAmbItemBean = new OrgaoAmbItemBean();
        List<OrgaoAmbItemBean> orgAmbList = orgaoAmbItemBean.get("idCabec", idCabec);
        JsonArray orgAmbJsonArray = new JsonArray();
        for (int i = 0; i < orgAmbList.size(); i++) {
            orgaoAmbItemBean = orgAmbList.get(i);
            Gson orgAmbGson = new Gson();
            orgAmbJsonArray.add(orgAmbGson.toJsonTree(orgaoAmbItemBean, orgaoAmbItemBean.getClass()));
        }
        orgAmbList.clear();
        return orgAmbJsonArray;
    }

    public void delOrgaoAmb(Long idCabec){
        OrgaoAmbItemBean orgaoAmbItemBean = new OrgaoAmbItemBean();
        List orgAmbList = orgaoAmbItemBean.get("idCabec", idCabec);
        for (int i = 0; i < orgAmbList.size(); i++) {
            orgaoAmbItemBean = (OrgaoAmbItemBean) orgAmbList.get(i);
            orgaoAmbItemBean.delete();
        }
        orgAmbList.clear();
    }

}
