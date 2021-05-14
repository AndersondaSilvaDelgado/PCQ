package br.com.usinasantafe.pcq.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.EquipItemBean;
import br.com.usinasantafe.pcq.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pcq.util.Tempo;

public class EquipDAO {

    public EquipDAO() {
    }

    public EquipBean getEquip(Long idEquip){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdEquip(idEquip));
        EquipBean equipBean = new EquipBean();
        List<EquipBean> equipList = equipBean.get(pesqArrayList);
        equipBean = equipList.get(0);
        equipList.clear();
        return equipBean;
    }

    public List<EquipBean> tanqueList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqTanque());
        EquipBean equipBean = new EquipBean();
        return equipBean.get(pesqArrayList);
    }

    public List<EquipBean> saveiroList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqSaveiro());
        EquipBean equipBean = new EquipBean();
        return equipBean.get(pesqArrayList);
    }

    public List<EquipItemBean> tanqueItemList(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqTanque());
        pesqArrayList.add(getPesqIdCabec(idCabec));
        EquipItemBean equipItemBean = new EquipItemBean();
        return equipItemBean.get(pesqArrayList);
    }

    public List<EquipItemBean> saveiroItemList(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqSaveiro());
        pesqArrayList.add(getPesqIdCabec(idCabec));
        EquipItemBean equipItemBean = new EquipItemBean();
        return equipItemBean.get(pesqArrayList);
    }

    public void setTanqueCabec(ArrayList<Long> tanqueCabecList, Long idCabec){
        delTanque(idCabec);
        for(Long idTanque : tanqueCabecList){
            EquipItemBean equipItemBean = new EquipItemBean();
            equipItemBean.setIdCabec(idCabec);
            equipItemBean.setIdEquip(idTanque);
            equipItemBean.setTipoEquip(1L);
            equipItemBean.setDthrEquip(Tempo.getInstance().dataComHora());
            equipItemBean.insert();
        }
    }

    public void setSaveiroCabec(ArrayList<Long> saveiroCabecList, Long idCabec){
        delSaveiro(idCabec);
        for(Long idSaveiro : saveiroCabecList){
            EquipItemBean equipItemBean = new EquipItemBean();
            equipItemBean.setIdCabec(idCabec);
            equipItemBean.setIdEquip(idSaveiro);
            equipItemBean.setTipoEquip(2L);
            equipItemBean.setDthrEquip(Tempo.getInstance().dataComHora());
            equipItemBean.insert();
        }
    }

    public JsonArray dadosEnvioEquip(Long idCabec){
        EquipItemBean equipItemBean = new EquipItemBean();
        List<EquipItemBean> equipList = equipItemBean.get("idCabec", idCabec);
        JsonArray equipJsonArray = new JsonArray();
        for (EquipItemBean equipItemBeanBD : equipList) {
            Gson equipGson = new Gson();
            equipJsonArray.add(equipGson.toJsonTree(equipItemBeanBD, equipItemBeanBD.getClass()));
        }
        equipList.clear();
        return equipJsonArray;
    }

    public void delTanque(Long idCabec){
        List<EquipItemBean> equipList = tanqueItemList(idCabec);
        for (EquipItemBean equipItemBeanBD : equipList) {
            equipItemBeanBD.delete();
        }
        equipList.clear();
    }

    public void delSaveiro(Long idCabec){
        List<EquipItemBean> equipList = saveiroItemList(idCabec);
        for (EquipItemBean equipItemBeanBD : equipList) {
            equipItemBeanBD.delete();
        }
        equipList.clear();
    }

    public void delEquip(Long idCabec){
        EquipItemBean equipItemBean = new EquipItemBean();
        List<EquipItemBean> equipList = equipItemBean.get("idCabec", idCabec);
        for (EquipItemBean equipItemBeanBD : equipList) {
            equipItemBeanBD.delete();
        }
        equipList.clear();
    }

    private EspecificaPesquisa getPesqIdEquip(Long idEquip){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idEquip");
        pesquisa.setValor(idEquip);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabec");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqTanque(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("tipoEquip");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqSaveiro(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("tipoEquip");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
