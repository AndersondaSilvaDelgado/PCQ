package br.com.usinasantafe.pcq.control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.bean.dao.CabecDAO;
import br.com.usinasantafe.pcq.bean.dao.EquipDAO;
import br.com.usinasantafe.pcq.bean.dao.ItemDAO;
import br.com.usinasantafe.pcq.bean.dao.OrgaoAmbDAO;
import br.com.usinasantafe.pcq.bean.dao.TalhaoDAO;
import br.com.usinasantafe.pcq.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.bean.variaveis.DadosEnvioBean;
import br.com.usinasantafe.pcq.bean.variaveis.ItemBean;

public class FormularioCTR {

    private int posMsg;
    private int posCriterio;
    private Long idResposta;
    private ItemBean itemBean;

    public FormularioCTR() {
        if(itemBean == null)
            itemBean = new ItemBean();
    }

    //////////////////////////////Cabecalho ///////////////////////////////////////////////////////

    public void salvarCabecIniciado(Long matricFunc){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = new CabecBean();
        cabecBean.setMatricColabCabec(matricFunc);
        cabecDAO.salvarCabecIniciado(cabecBean);
    }

    public void delCabecIniciado(){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.delCabecInic();
    }

    public void fecharCabec(){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.fecharCabec(cabecDAO.getCabecAbert());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////Item Cabecalho////////////////////////////////////////////////

    public void setItemBean(Long idQuestao, Long idResp) {
        itemBean = new ItemBean();
        itemBean.setIdQuestao(idQuestao);
        itemBean.setIdResp(idResp);
    }

    public void salvarItem(Long idSubResp){
        itemBean.setIdSubResp(idSubResp);
        CabecDAO cabecDAO = new CabecDAO();
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.salvarItem(itemBean, cabecDAO.getCabecAbert().getIdCabec());
    }

    public Long getIdResp(){
        return itemBean.getIdResp();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////set Cabecalho////////////////////////////////////////////////

    public void setSecaoCabec(Long secaoCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setSecaoCabec(secaoCabec);
    }

    public void setTalhaoCabec(ArrayList<Long> talhaoCabec){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecInic();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        talhaoDAO.setTalhaoCabec(talhaoCabec, cabecBean.getIdCabec());
    }

    public void setHaIncCanaCabec(Double haIncCanaCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setHaIncCanaCabec(haIncCanaCabec);
    }

    public void setHaIncPalhadaCabec(Double haIncPalhadaCabec) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setHaIncPalhadaCabec(haIncPalhadaCabec);
    }

    public void setHaIncResLegalCabec(Double haIncResLegalCabec) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setHaIncResLegalCabec(haIncResLegalCabec);
    }

    public void setHaIncAppCabec(Double haIncAppCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setHaIncAppCabec(haIncAppCabec);
    }

    public void setHaIncAreaComumCabec(Double haIncAreaComumCabec) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setHaIncAreaComumCabec(haIncAreaComumCabec);
    }

    public void setTanqueCabec(ArrayList<Long> tanqueCabec){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecInic();
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.setTanqueCabec(tanqueCabec, cabecBean.getIdCabec());
    }

    public void setSaveiroCabec(ArrayList<Long> saveiroCabec){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecInic();
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.setSaveiroCabec(saveiroCabec, cabecBean.getIdCabec());
    }

    public void setQtdeBrigadistaCabec(Long qtdeBrigadistaCabec) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setQtdeBrigadistaCabec(qtdeBrigadistaCabec);
    }

    public void setEmpresaTercCabec(String empresaTercCabec) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setEmpresaTercCabec(empresaTercCabec);
    }

    public void setOrgAmbCabec(ArrayList<Long> talhaoCabec){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecInic();
        OrgaoAmbDAO itemOrgAmbDAO = new OrgaoAmbDAO();
        itemOrgAmbDAO.setOrgAmbCabec(talhaoCabec, cabecBean.getIdCabec());
    }

    public void setComentCabec(String comentCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setComentCabec(comentCabec);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////get Cabecalho////////////////////////////////////////////////

    public Long getSecaoCabec(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecInic();
        return cabecBean.getSecaoCabec();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public int getPosMsg() {
        return posMsg;
    }

    public void setPosMsg(int posMsg) {
        this.posMsg = posMsg;
    }

    public int getPosCriterio() {
        return posCriterio;
    }

    public void setPosCriterio(int posCriterio) {
        this.posCriterio = posCriterio;
    }

    ////////////////////////////Dados para Envio///////////////////////////////////////////////////

    public DadosEnvioBean dadosCabecFechEnvio() {

        DadosEnvioBean dadosEnvioBean = new DadosEnvioBean();

        CabecDAO cabecDAO = new CabecDAO();
        List cabecList = cabecDAO.cabecFechList();
        JsonArray cabecJsonArray = new JsonArray();
        JsonArray itemJsonArray = new JsonArray();
        JsonArray equipJsonArray = new JsonArray();
        JsonArray orgaoAmbJsonArray = new JsonArray();
        JsonArray talhaoJsonArray = new JsonArray();

        for (int i = 0; i < cabecList.size(); i++) {

            CabecBean cabecBean = (CabecBean) cabecList.get(i);
            Gson gsonCabec = new Gson();
            cabecJsonArray.add(gsonCabec.toJsonTree(cabecBean, cabecBean.getClass()));

            ItemDAO itemDAO = new ItemDAO();
            itemJsonArray = itemDAO.dadosEnvioItem(cabecBean.getIdCabec());

            EquipDAO equipDAO = new EquipDAO();
            equipJsonArray = equipDAO.dadosEnvioEquip(cabecBean.getIdCabec());

            OrgaoAmbDAO orgaoAmbDAO = new OrgaoAmbDAO();
            orgaoAmbJsonArray = orgaoAmbDAO.dadosEnvioOrgaoAmbiental(cabecBean.getIdCabec());

            TalhaoDAO talhaoDAO = new TalhaoDAO();
            talhaoJsonArray = talhaoDAO.dadosEnvioTalhao(cabecBean.getIdCabec());

        }

        JsonObject cabecJsonObj = new JsonObject();
        cabecJsonObj.add("cabec", cabecJsonArray);
        dadosEnvioBean.setCabec(cabecJsonObj.toString());

        JsonObject itemJsonObj = new JsonObject();
        itemJsonObj.add("item", itemJsonArray);
        dadosEnvioBean.setItem(itemJsonObj.toString());

        JsonObject equipJsonObj = new JsonObject();
        equipJsonObj.add("equip", equipJsonArray);
        dadosEnvioBean.setEquip(equipJsonObj.toString());

        JsonObject orgaoAmbJsonObj = new JsonObject();
        orgaoAmbJsonObj.add("orgaoamb", orgaoAmbJsonArray);
        dadosEnvioBean.setOrgaoAmb(orgaoAmbJsonObj.toString());

        JsonObject talhaoJsonObj = new JsonObject();
        talhaoJsonObj.add("talhao", talhaoJsonArray);
        dadosEnvioBean.setCabec(talhaoJsonObj.toString());

        return dadosEnvioBean;
    }

}
