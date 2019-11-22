package br.com.usinasantafe.pcq.bean.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.bean.variaveis.TalhaoBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class TalhaoDAO {

    public TalhaoDAO() {
    }

    public void setTalhaoCabec(ArrayList<Long> talhaoCabec, Long idCabec){
        for(int i = 0; i < talhaoCabec.size(); i++){
            TalhaoBean talhaoBean = new TalhaoBean();
            talhaoBean.setIdCabec(idCabec);
            talhaoBean.setIdTalhao(talhaoCabec.get(i));
            talhaoBean.setDthrTalhao(Tempo.getInstance().dataComHora());
            talhaoBean.insert();
        }
    }

    public JsonArray dadosEnvioTalhao(Long idCabec){
        TalhaoBean talahoBean = new TalhaoBean();
        List talhaoList = talahoBean.get("idCabec", idCabec);
        JsonArray talhaoJsonArray = new JsonArray();
        for (int i = 0; i < talhaoList.size(); i++) {
            talahoBean = (TalhaoBean) talhaoList.get(i);
            Gson cabecGson = new Gson();
            talhaoJsonArray.add(cabecGson.toJsonTree(talahoBean, talahoBean.getClass()));
        }
        talhaoList.clear();
        return talhaoJsonArray;
    }

}
