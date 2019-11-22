package br.com.usinasantafe.pcq.bean.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.bean.variaveis.EquipBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class EquipDAO {

    public EquipDAO() {
    }

    public void setTanqueCabec(ArrayList<Long> tanqueCabec, Long idCabec){
        for(int i = 0; i < tanqueCabec.size(); i++){
            EquipBean equipBean = new EquipBean();;
            equipBean.setIdCabec(idCabec);
            equipBean.setIdEquip(tanqueCabec.get(i));
            equipBean.setTipoEquip(1L);
            equipBean.setDthrEquip(Tempo.getInstance().dataComHora());
            equipBean.insert();
        }
    }

    public void setSaveiroCabec(ArrayList<Long> saveiroCabec, Long idCabec){
        for(int i = 0; i < saveiroCabec.size(); i++){
            EquipBean equipBean = new EquipBean();;
            equipBean.setIdCabec(idCabec);
            equipBean.setIdEquip(saveiroCabec.get(i));
            equipBean.setTipoEquip(2L);
            equipBean.setDthrEquip(Tempo.getInstance().dataComHora());
            equipBean.insert();
        }
    }

    public JsonArray dadosEnvioEquip(Long idCabec){
        EquipBean equipBean = new EquipBean();
        List equipList = equipBean.get("idCabec", idCabec);
        JsonArray equipJsonArray = new JsonArray();
        for (int i = 0; i < equipList.size(); i++) {
            equipBean = (EquipBean) equipList.get(i);
            Gson equipGson = new Gson();
            equipJsonArray.add(equipGson.toJsonTree(equipBean, equipBean.getClass()));
        }
        equipList.clear();
        return equipJsonArray;
    }

}
