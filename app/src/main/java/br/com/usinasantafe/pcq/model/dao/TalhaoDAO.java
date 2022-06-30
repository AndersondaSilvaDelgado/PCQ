package br.com.usinasantafe.pcq.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.FotoItemBean;
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
        delTalhao(idCabec);
        for(int i = 0; i < talhaoCabec.size(); i++){
            TalhaoItemBean talhaoItemBean = new TalhaoItemBean();
            talhaoItemBean.setIdCabec(idCabec);
            talhaoItemBean.setIdTalhao(talhaoCabec.get(i));
            talhaoItemBean.setDthrTalhao(Tempo.getInstance().dthrAtualString());
            talhaoItemBean.setTipoTalhao(1L);
            talhaoItemBean.setHaIncCanaTalhao(0D);
            talhaoItemBean.setAltCanaTalhao(1L);
            talhaoItemBean.setHaIncPalhadaTalhao(0D);
            talhaoItemBean.insert();
        }
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

    public void setTipoTalhao(Long tipoTalhao, TalhaoItemBean talhaoItemBean){
        talhaoItemBean.setTipoTalhao(tipoTalhao);
        talhaoItemBean.update();
    }

    public void setHaIncCanaTalhao(Double haIncCanaTalhao, TalhaoItemBean talhaoItemBean){
        talhaoItemBean.setHaIncCanaTalhao(haIncCanaTalhao);
        talhaoItemBean.update();
    }

    public void setAltCanaTalhao(Long altCanaTalhao, TalhaoItemBean talhaoItemBean){
        talhaoItemBean.setAltCanaTalhao(altCanaTalhao);
        talhaoItemBean.update();
    }

    public void setHaIncPalhadaTalhao(Double haIncPalhadaTalhao, TalhaoItemBean talhaoItemBean){
        talhaoItemBean.setHaIncPalhadaTalhao(haIncPalhadaTalhao);
        talhaoItemBean.update();
    }

    public ArrayList<String> talhaoItemAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("FOTO");
        TalhaoItemBean talhaoItemBean = new TalhaoItemBean();
        List<TalhaoItemBean> fotoItemList = talhaoItemBean.orderBy("idTalhaoItem", true);
        for (TalhaoItemBean talhaoItemBeanBD : fotoItemList) {
            dadosArrayList.add(dadosTalhao(talhaoItemBeanBD));
        }
        fotoItemList.clear();
        return dadosArrayList;
    }

    private String dadosTalhao(TalhaoItemBean talhaoItemBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(talhaoItemBean, talhaoItemBean.getClass()).toString();
    }

}
