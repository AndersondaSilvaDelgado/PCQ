package br.com.usinasantafe.pcq.bean.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.List;

import br.com.usinasantafe.pcq.bean.variaveis.RespItemBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class ItemDAO {

    public ItemDAO() {
    }

    public void salvarItem(RespItemBean respItemBean, Long idCabec){
        respItemBean.setIdCabec(idCabec);
        respItemBean.setDthrItem(Tempo.getInstance().dataComHora());
        respItemBean.insert();
    }

    public JsonArray dadosEnvioItem(Long idCabec){
        RespItemBean respItemBean = new RespItemBean();
        List itemList = respItemBean.get("idCabec", idCabec);
        JsonArray itemJsonArray = new JsonArray();
        for (int i = 0; i < itemList.size(); i++) {
            respItemBean = (RespItemBean) itemList.get(i);
            Gson itemGson = new Gson();
            itemJsonArray.add(itemGson.toJsonTree(respItemBean, respItemBean.getClass()));
        }
        itemList.clear();
        return itemJsonArray;
    }

    public void delItem(Long idCabec){
        RespItemBean respItemBean = new RespItemBean();
        List itemList = respItemBean.get("idCabec", idCabec);
        for (int i = 0; i < itemList.size(); i++) {
            respItemBean = (RespItemBean) itemList.get(i);
            respItemBean.delete();
        }
        itemList.clear();
    }

}
