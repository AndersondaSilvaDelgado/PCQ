package br.com.usinasantafe.pcq.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.TalhaoItemBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class TalhaoDAO {

    public TalhaoDAO() {
    }

    public List<TalhaoBean> talhaoList(Long idSecao){
        TalhaoBean talhaoBean = new TalhaoBean();
        return talhaoBean.get("idSecao", idSecao);
    }

    public void setTalhaoCabec(ArrayList<Long> talhaoCabec, Long idCabec){
        for(int i = 0; i < talhaoCabec.size(); i++){
            TalhaoItemBean talhaoItemBean = new TalhaoItemBean();
            talhaoItemBean.setIdCabec(idCabec);
            talhaoItemBean.setIdTalhao(talhaoCabec.get(i));
            talhaoItemBean.setDthrTalhao(Tempo.getInstance().dataComHora());
            talhaoItemBean.insert();
        }
    }

    public JsonArray dadosEnvioTalhao(Long idCabec){
        TalhaoItemBean talhaoBean = new TalhaoItemBean();
        List<TalhaoItemBean> talhaoList = talhaoBean.get("idCabec", idCabec);
        JsonArray talhaoJsonArray = new JsonArray();
        for (int i = 0; i < talhaoList.size(); i++) {
            talhaoBean = talhaoList.get(i);
            Gson cabecGson = new Gson();
            talhaoJsonArray.add(cabecGson.toJsonTree(talhaoBean, talhaoBean.getClass()));
        }
        talhaoList.clear();
        return talhaoJsonArray;
    }

    public void delTalhao(Long idCabec){
        List<TalhaoItemBean> talhaoItemList = talhaoItemList(idCabec);
        for (TalhaoItemBean talhaoItemBean : talhaoItemList) {
            talhaoItemBean.delete();
        }
        talhaoItemList.clear();
    }

    public List<TalhaoItemBean> talhaoItemList(Long idCabec){
        TalhaoItemBean talhaoItemBean = new TalhaoItemBean();
        return talhaoItemBean.get("idCabec", idCabec);
    }

    public TalhaoBean getTalhao(Long idTalhao){
        TalhaoBean talhaoBean = new TalhaoBean();
        List<TalhaoBean> talhaoList = talhaoBean.get("idTalhao", idTalhao);
        talhaoBean = talhaoList.get(0);
        talhaoList.clear();
        return talhaoBean;
    }

    public TalhaoItemBean getTalhaoItem(Long idTalhaoItem){
        TalhaoItemBean talhaoItemBean = new TalhaoItemBean();
        List<TalhaoItemBean> talhaoItemList = talhaoItemBean.get("idTalhaoItem", idTalhaoItem);
        talhaoItemBean = talhaoItemList.get(0);
        talhaoItemList.clear();
        return talhaoItemBean;
    }

    public void setStatusCanavialTalhao(Long statusCanavialTalhao, TalhaoItemBean talhaoItemBean){
        talhaoItemBean.setStatusCanavialTalhao(statusCanavialTalhao);
        talhaoItemBean.update();
    }

    public void setHaIncCanaTalhao(Double haIncCanaTalhao, TalhaoItemBean talhaoItemBean){
        talhaoItemBean.setHaIncCanaTalhao(haIncCanaTalhao);
        talhaoItemBean.update();
    }

    public void setTipoCanaTalhao(Long tipoCanaTalhao, TalhaoItemBean talhaoItemBean){
        talhaoItemBean.setTipoCanaTalhao(tipoCanaTalhao);
        talhaoItemBean.update();
    }

    public void setHaIncPalhadaTalhao(Double haIncPalhadaTalhao, TalhaoItemBean talhaoItemBean){
        talhaoItemBean.setHaIncPalhadaTalhao(haIncPalhadaTalhao);
        talhaoItemBean.update();
    }

}
