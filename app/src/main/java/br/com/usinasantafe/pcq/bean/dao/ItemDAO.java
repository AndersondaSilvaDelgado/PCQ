package br.com.usinasantafe.pcq.bean.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.List;

import br.com.usinasantafe.pcq.bean.variaveis.ItemBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class ItemDAO {

    public ItemDAO() {
    }

    public void salvarItem(ItemBean itemBean, Long idCabec){
        itemBean.setIdCabec(idCabec);
        itemBean.setDthrItem(Tempo.getInstance().dataComHora());
        itemBean.insert();
    }

    public JsonArray dadosEnvioItem(Long idCabec){
        ItemBean itemBean = new ItemBean();
        List itemList = itemBean.get("idCabec", idCabec);
        JsonArray itemJsonArray = new JsonArray();
        for (int i = 0; i < itemList.size(); i++) {
            itemBean = (ItemBean) itemList.get(i);
            Gson itemGson = new Gson();
            itemJsonArray.add(itemGson.toJsonTree(itemBean, itemBean.getClass()));
        }
        itemList.clear();
        return itemJsonArray;
    }

}
