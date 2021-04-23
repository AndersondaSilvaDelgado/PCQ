package br.com.usinasantafe.pcq.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.BrigadistaBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.OrigemFogoBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.QuestaoBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.RespBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.SecaoBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.TercCombBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.TipoApontBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.BrigadistaItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.EquipItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.FotoItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.TalhaoItemBean;
import br.com.usinasantafe.pcq.model.dao.BrigadistaDAO;
import br.com.usinasantafe.pcq.model.dao.CabecDAO;
import br.com.usinasantafe.pcq.model.dao.ColabDAO;
import br.com.usinasantafe.pcq.model.dao.EquipDAO;
import br.com.usinasantafe.pcq.model.dao.FotoDAO;
import br.com.usinasantafe.pcq.model.dao.ItemDAO;
import br.com.usinasantafe.pcq.model.dao.OrgaoAmbDAO;
import br.com.usinasantafe.pcq.model.dao.OrigemFogoDAO;
import br.com.usinasantafe.pcq.model.dao.QuestaoDAO;
import br.com.usinasantafe.pcq.model.dao.RespDAO;
import br.com.usinasantafe.pcq.model.dao.SecaoDAO;
import br.com.usinasantafe.pcq.model.dao.TalhaoDAO;
import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.DadosEnvioBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.RespItemBean;
import br.com.usinasantafe.pcq.model.dao.TercCombDAO;
import br.com.usinasantafe.pcq.model.dao.TipoApontDAO;
import br.com.usinasantafe.pcq.util.AtualDadosServ;

public class FormularioCTR {

    private int posTalhao;
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
        ConfigCTR configCTR = new ConfigCTR();
        cabecBean.setTipoCabec(tipo);
        cabecBean.setNroAparelhoCabec(configCTR.getConfig().getNroAparelhoConfig());
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
        cabecDAO.fecharCabec(cabecDAO.getCabecAberto());
    }

    public Boolean verEnvioDados(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.cabecFechList().size() > 0;
    }

    public boolean verCabecAberto(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.verCabecAberto();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////Item Cabecalho////////////////////////////////////////////////

    public void delItemAberto(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecAberto();
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
        itemDAO.salvarItem(respItemBean, cabecDAO.getCabecAberto().getIdCabec());
    }

    public Long getIdResp(){
        return respItemBean.getIdResp();
    }

    public List<RespItemBean> respItemList(){
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.respItemList(getCabecAbert().getIdCabec());
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

    public ColabBean getMatricColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getMatricColab(matricColab);
    }

    public ColabBean getIdFuncColab(Long idFuncColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getIdFuncColab(idFuncColab);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////BRIGADISTA ////////////////////////////////////////////

    public BrigadistaBean getBrigadista(Long idFunc){
        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        return brigadistaDAO.getBrigadista(idFunc);
    }

    public List<BrigadistaBean> brigadistaList(){
        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        return brigadistaDAO.brigadistaList();
    }

    public List<BrigadistaItemBean> brigadistaItemList(Long idCabec){
        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        return brigadistaDAO.brigadistaItemList(idCabec);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////TIPO APONT ////////////////////////////////////////////

    public List<TipoApontBean> tipoApontList(){
        TipoApontDAO tipoApontDAO = new TipoApontDAO();
        return tipoApontDAO.tipoApontList();
    }

    public TipoApontBean getTipoApont(Long idTipoApont){
        TipoApontDAO tipoApontDAO = new TipoApontDAO();
        return tipoApontDAO.getTipoApont(idTipoApont);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////ORIGEM DO FOGO ////////////////////////////////////////////

    public List<OrigemFogoBean> origemFogoList(){
        OrigemFogoDAO origemFogoDAO = new OrigemFogoDAO();
        return origemFogoDAO.origemFogoList();
    }

    public OrigemFogoBean getOrigemFogo(Long idOrigemFogo){
        OrigemFogoDAO origemFogoDAO = new OrigemFogoDAO();
        return origemFogoDAO.getOrigemFogo(idOrigemFogo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////TERCEIRO COMBATE ////////////////////////////////////////

    public List<TercCombBean> tercCombList(){
        TercCombDAO tercCombDAO = new TercCombDAO();
        return tercCombDAO.tercCombList();
    }

    public TercCombBean getTercComb(Long idTercComb){
        TercCombDAO tercCombDAO = new TercCombDAO();
        return tercCombDAO.getTercComb(idTercComb);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////SECAO ////////////////////////////////////////////////

    public boolean verSecao(Long codSecao){
        SecaoDAO secaoDAO = new SecaoDAO();
        return secaoDAO.verSecao(codSecao);
    }

    public SecaoBean getCodSecao(Long codSecao){
        SecaoDAO secaoDAO = new SecaoDAO();
        return secaoDAO.getCodSecao(codSecao);
    }

    public SecaoBean getIdSecao(Long idSecao){
        SecaoDAO secaoDAO = new SecaoDAO();
        return secaoDAO.getIdSecao(idSecao);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////TALHÃO ////////////////////////////////////////////////

    public List<TalhaoBean> talhaoList(){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.talhaoList(getSecaoCabec());
    }

    public List<TalhaoItemBean> talhaoItemCabecIniciadoList(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecIniciado();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.talhaoItemList(cabecBean.getIdCabec());
    }

    public List<TalhaoItemBean> talhaoItemCabecAbertoList(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecAberto();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.talhaoItemList(cabecBean.getIdCabec());
    }

    public TalhaoBean getTalhao(Long idTalhao){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.getTalhao(idTalhao);
    }

    public void setStatusCanavialTalhao(Long statusCanavialTalhao, TalhaoItemBean talhaoItemBean){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        talhaoDAO.setStatusCanavialTalhao(statusCanavialTalhao, talhaoItemBean);
    }

    public void setHaIncCanaTalhao(Double haIncCanaTalhao, TalhaoItemBean talhaoItemBean){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        talhaoDAO.setHaIncCanaTalhao(haIncCanaTalhao, talhaoItemBean);
    }

    public void setTipoCanaTalhao(Long tipoCanaTalhao, TalhaoItemBean talhaoItemBean){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        talhaoDAO.setTipoCanaTalhao(tipoCanaTalhao, talhaoItemBean);
    }

    public void setHaIncPalhadaTalhao(Double haIncPalhadaTalhao, TalhaoItemBean talhaoItemBean){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        talhaoDAO.setHaIncPalhadaTalhao(haIncPalhadaTalhao, talhaoItemBean);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////VEICULO ////////////////////////////////////////////////

    public EquipBean getEquip(Long idEquip){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip(idEquip);
    }

    public List<EquipBean> tanqueList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.tanqueList();
    }

    public List<EquipBean> saveiroList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.saveiroList();
    }

    public List<EquipItemBean> tanqueItemList(Long idCabec){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.tanqueItemList(idCabec);
    }

    public List<EquipItemBean> saveiroItemList(Long idCabec){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.saveiroItemList(idCabec);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////set Cabecalho////////////////////////////////////////////////

    public void setTipoApontTrabCabec(Long tipoApontTrabCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setTipoApontTrabCabec(tipoApontTrabCabec);
    }

    public void setIdFuncCabec(Long matricColabFunc){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setIdFuncCabec(matricColabFunc);
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

    public void setHaIncAppCabec(Double haIncAppCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setHaIncAppCabec(haIncAppCabec);
    }

    public void setHaIncForaAppCabec(Double haIncForaAppCabec) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setHaIncForaAppCabec(haIncForaAppCabec);
    }

    public void setBrigadistaCabec(ArrayList<Long> brigadistaCabec){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecIniciado();
        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        brigadistaDAO.setBrigadistaCabec(brigadistaCabec, cabecBean.getIdCabec());
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

    public void setTercCombCabec(Long empresaTercCabec) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setTercCombCabec(empresaTercCabec);
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

    public void setAceiroCanavialCabec(Long aceiroCanavialCabec) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setAceiroCanavialCabec(aceiroCanavialCabec);
    }

    public void setAceiroVegetNativalCabec(Long aceiroVegetNativalCabec) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setAceiroVegetNativalCabec(aceiroVegetNativalCabec);
    }

    public void setComentCabec(String comentCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setComentCabec(comentCabec);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////get Cabecalho////////////////////////////////////////////////

    public CabecBean getCabecAbert(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.getCabecAberto();
    }

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

    /////////////////////////////////////////QUESTAO //////////////////////////////////////////////

    public List<QuestaoBean> questaoList(){
        QuestaoDAO questaoDAO = new QuestaoDAO();
        return questaoDAO.questaoList();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////RESP ////////////////////////////////////////////////

    public List<RespBean> respIdQuestaoList(Long idQuestao){
        RespDAO respDAO = new RespDAO();
        return respDAO.respIdQuestaoList(idQuestao);
    }

    public RespBean getResp(Long idResp){
        RespDAO respDAO = new RespDAO();
        return respDAO.getResp(idResp);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public int getPosCriterio() {
        return posCriterio;
    }

    public void setPosCriterio(int posCriterio) {
        this.posCriterio = posCriterio;
    }

    public int getPosTalhao() {
        return posTalhao;
    }

    public void setPosTalhao(int posTalhao) {
        this.posTalhao = posTalhao;
    }

    ////////////////////////////Dados para Envio///////////////////////////////////////////////////

    public DadosEnvioBean dadosCabecFechEnvio() {

        DadosEnvioBean dadosEnvioBean = new DadosEnvioBean();

        CabecDAO cabecDAO = new CabecDAO();
        List cabecList = cabecDAO.cabecFechList();
        JsonArray cabecJsonArray = new JsonArray();
        JsonArray itemJsonArray = new JsonArray();
        JsonArray brigadistaJsonArray = new JsonArray();
        JsonArray equipJsonArray = new JsonArray();
        JsonArray fotoJsonArray = new JsonArray();
        JsonArray orgaoAmbJsonArray = new JsonArray();
        JsonArray talhaoJsonArray = new JsonArray();

        for (int i = 0; i < cabecList.size(); i++) {

            CabecBean cabecBean = (CabecBean) cabecList.get(i);
            Gson gsonCabec = new Gson();
            cabecJsonArray.add(gsonCabec.toJsonTree(cabecBean, cabecBean.getClass()));

            ItemDAO itemDAO = new ItemDAO();
            itemJsonArray = itemDAO.dadosEnvioItem(cabecBean.getIdCabec());

            BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
            brigadistaJsonArray = brigadistaDAO.dadosEnvioBrigadista(cabecBean.getIdCabec());

            EquipDAO equipDAO = new EquipDAO();
            equipJsonArray = equipDAO.dadosEnvioEquip(cabecBean.getIdCabec());

            FotoDAO fotoDAO = new FotoDAO();
            fotoJsonArray = fotoDAO.dadosEnvioFoto(cabecBean.getIdCabec());

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

        JsonObject brigadistaJsonObj = new JsonObject();
        brigadistaJsonObj.add("brigadista", brigadistaJsonArray);
        dadosEnvioBean.setBrigadista(brigadistaJsonObj.toString());

        JsonObject equipJsonObj = new JsonObject();
        equipJsonObj.add("equip", equipJsonArray);
        dadosEnvioBean.setEquip(equipJsonObj.toString());

        JsonObject fotoJsonObj = new JsonObject();
        fotoJsonObj.add("foto", fotoJsonArray);
        dadosEnvioBean.setFoto(fotoJsonObj.toString());

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

    /////////////////////////////// MANIPULAÇÃO FOTO ///////////////////////////////////////////////

    public FotoItemBean salvarFoto(Bitmap bitmap, Long tipoFoto){
        FotoDAO fotoDAO = new FotoDAO();
        return fotoDAO.salvarFoto(getCabecIniciado().getIdCabec(), bitmap, tipoFoto);
    }

    public List getListFotoCabecIniciado(Long tipoFoto){
        FotoDAO fotoAbordDAO = new FotoDAO();
        return fotoAbordDAO.getListFotoCabec(getCabecIniciado().getIdCabec(), tipoFoto);
    }

    public Bitmap getStringBitmap(String foto) {
        FotoDAO fotoDAO = new FotoDAO();
        return fotoDAO.getStringBitmap(foto);
    }

    public List getListFotoCabecAbert(Long tipoFoto){
        FotoDAO fotoAbordDAO = new FotoDAO();
        return fotoAbordDAO.getListFotoCabec(getCabecAbert().getIdCabec(), tipoFoto);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
