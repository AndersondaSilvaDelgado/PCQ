package br.com.usinasantafe.pcq.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.variaveis.EquipItemBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class EquipDAO {

    public EquipDAO() {
    }

    public void setTanqueCabec(ArrayList<Long> tanqueCabec, Long idCabec){
        for(int i = 0; i < tanqueCabec.size(); i++){
            EquipItemBean equipItemBean = new EquipItemBean();;
            equipItemBean.setIdCabec(idCabec);
            equipItemBean.setIdEquip(tanqueCabec.get(i));
            equipItemBean.setTipoEquip(1L);
            equipItemBean.setDthrEquip(Tempo.getInstance().dataComHora());
            equipItemBean.insert();
        }
    }

    public void setSaveiroCabec(ArrayList<Long> saveiroCabec, Long idCabec){
        for(int i = 0; i < saveiroCabec.size(); i++){
            EquipItemBean equipItemBean = new EquipItemBean();;
            equipItemBean.setIdCabec(idCabec);
            equipItemBean.setIdEquip(saveiroCabec.get(i));
            equipItemBean.setTipoEquip(2L);
            equipItemBean.setDthrEquip(Tempo.getInstance().dataComHora());
            equipItemBean.insert();
        }
    }

    public JsonArray dadosEnvioEquip(Long idCabec){
        EquipItemBean equipItemBean = new EquipItemBean();
        List equipList = equipItemBean.get("idCabec", idCabec);
        JsonArray equipJsonArray = new JsonArray();
        for (int i = 0; i < equipList.size(); i++) {
            equipItemBean = (EquipItemBean) equipList.get(i);
            Gson equipGson = new Gson();
            equipJsonArray.add(equipGson.toJsonTree(equipItemBean, equipItemBean.getClass()));
        }
        equipList.clear();
        return equipJsonArray;
    }

    public void delEquip(Long idCabec){
        EquipItemBean equipItemBean = new EquipItemBean();
        List equipList = equipItemBean.get("idCabec", idCabec);
        for (int i = 0; i < equipList.size(); i++) {
            equipItemBean = (EquipItemBean) equipList.get(i);
            equipItemBean.delete();
        }
        equipList.clear();
    }

}
