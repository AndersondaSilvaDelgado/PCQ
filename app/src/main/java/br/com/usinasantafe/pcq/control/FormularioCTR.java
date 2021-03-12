package br.com.usinasantafe.pcq.control;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.SecaoBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pcq.model.dao.CabecDAO;
import br.com.usinasantafe.pcq.model.dao.ColabDAO;
import br.com.usinasantafe.pcq.model.dao.EquipDAO;
import br.com.usinasantafe.pcq.model.dao.ItemDAO;
import br.com.usinasantafe.pcq.model.dao.OrgaoAmbDAO;
import br.com.usinasantafe.pcq.model.dao.SecaoDAO;
import br.com.usinasantafe.pcq.model.dao.TalhaoDAO;
import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.DadosEnvioBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.RespItemBean;
import br.com.usinasantafe.pcq.util.AtualDadosServ;
import br.com.usinasantafe.pcq.util.EnvioDadosServ;

public class FormularioCTR {

    private int posMsg;
    private int posCriterio;
    private RespItemBean respItemBean;

    public FormularioCTR() {
        if(respItemBean == null)
            respItemBean = new RespItemBean();
    }

    //////////////////////////////Cabecalho ///////////////////////////////////////////////////////

    public void salvarCabecIniciado(Long tipo){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = new CabecBean();
        cabecBean.setTipoCabec(tipo);
        cabecDAO.salvarCabecIniciado(cabecBean);
    }

    public void delCabecIniciado(){
        CabecDAO cabecDAO = new CabecDAO();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        EquipDAO equipDAO = new EquipDAO();
        OrgaoAmbDAO orgaoAmbDAO = new OrgaoAmbDAO();
        if(cabecDAO.verCabecIniciado()){
            talhaoDAO.delTalhao(cabecDAO.getCabecIniciado().getIdCabec());
            equipDAO.delEquip(cabecDAO.getCabecIniciado().getIdCabec());
            orgaoAmbDAO.delOrgaoAmb(cabecDAO.getCabecIniciado().getIdCabec());
            cabecDAO.delCabecInic();
        }
    }

    public void fecharCabec(){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.fecharCabec(cabecDAO.getCabecAbert());
    }

    public Boolean verEnvioDados(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.cabecFechList().size() > 0;
    }

    public boolean verCabecAbert(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.verCabecAberto();

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////Item Cabecalho////////////////////////////////////////////////

    public void delItemAberto(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecAbert();
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.delItem(cabecBean.getIdCabec());
    }

    public void setItemBean(Long idQuestao, Long idResp) {
        respItemBean = new RespItemBean();
        respItemBean.setIdQuestao(idQuestao);
        respItemBean.setIdResp(idResp);
    }

    public void salvarItem(Long idSubResp){
        respItemBean.setIdSubResp(idSubResp);
        CabecDAO cabecDAO = new CabecDAO();
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.salvarItem(respItemBean, cabecDAO.getCabecAbert().getIdCabec());
    }

    public Long getIdResp(){
        return respItemBean.getIdResp();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////COLAB ////////////////////////////////////////////////

    public boolean hasElemColab(){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.hasElements();
    }

    public boolean verColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.verColab(matricColab);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////SECAO ////////////////////////////////////////////////

    public boolean verSecao(Long codSecao){
        SecaoDAO secaoDAO = new SecaoDAO();
        return secaoDAO.verSecao(codSecao);
    }

    public SecaoBean getSecao(Long codSecao){
        SecaoDAO secaoDAO = new SecaoDAO();
        return secaoDAO.getSecao(codSecao);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////TALHÃO ////////////////////////////////////////////////

    public List<TalhaoBean> talhaoList(){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.talhaoList(getSecaoCabec());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////VEICULO ////////////////////////////////////////////////

    public List<EquipBean> tanqueList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.tanqueList();
    }

    public List<EquipBean> saveiroList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.saveiroList();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////set Cabecalho////////////////////////////////////////////////

    public void setMatricCabec(Long matricColabFunc){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setMatricColabCabec(matricColabFunc);
    }

    public void setSecaoCabec(Long secaoCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setSecaoCabec(secaoCabec);
    }

    public void setTalhaoCabec(ArrayList<Long> talhaoCabec){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecIniciado();
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
        CabecBean cabecBean = cabecDAO.getCabecIniciado();
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.setTanqueCabec(tanqueCabec, cabecBean.getIdCabec());
    }

    public void setSaveiroCabec(ArrayList<Long> saveiroCabec){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecIniciado();
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
        CabecBean cabecBean = cabecDAO.getCabecIniciado();
        OrgaoAmbDAO itemOrgAmbDAO = new OrgaoAmbDAO();
        itemOrgAmbDAO.setOrgAmbCabec(talhaoCabec, cabecBean.getIdCabec());
    }

    public void setOrigemFogoCabec(Long origemFogoCabec) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setOrigemFogoCabec(origemFogoCabec);
    }

    public void setComentCabec(String comentCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setComentCabec(comentCabec);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////get Cabecalho////////////////////////////////////////////////

    public CabecBean getCabecIniciado(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.getCabecIniciado();
    }

    public Long getSecaoCabec(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecIniciado();
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
        dadosEnvioBean.setTalhao(talhaoJsonObj.toString());

        return dadosEnvioBean;
    }

    public void delForm() {

        CabecDAO cabecDAO = new CabecDAO();
        List cabecList = cabecDAO.cabecFechList();

        for (int i = 0; i < cabecList.size(); i++) {

            CabecBean cabecBean = (CabecBean) cabecList.get(i);

            ItemDAO itemDAO = new ItemDAO();
            itemDAO.delItem(cabecBean.getIdCabec());

            EquipDAO equipDAO = new EquipDAO();
            equipDAO.delEquip(cabecBean.getIdCabec());

            OrgaoAmbDAO orgaoAmbDAO = new OrgaoAmbDAO();
            orgaoAmbDAO.delOrgaoAmb(cabecBean.getIdCabec());

            TalhaoDAO talhaoDAO = new TalhaoDAO();
            talhaoDAO.delTalhao(cabecBean.getIdCabec());

            cabecBean.delete();

        }

        EnvioDadosServ.getInstance().setEnviando(false);

    }

    /////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS POR SERVIDOR /////////////////////

    public void atualDadosColab(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("ColabBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    public void atualDadosSecao(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("SecaoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////


}
