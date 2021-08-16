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
import br.com.usinasantafe.pcq.model.dao.OrigemFogoDAO;
import br.com.usinasantafe.pcq.model.dao.RespostaDAO;
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
    private int posCabecReaj;
    private RespItemBean respItemBean;
    private Long secao;

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
        if(cabecDAO.verCabecIniciado()){
            delForm(cabecDAO.getCabecIniciado());
        }
    }

    public void abrirCabec(){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.abrirCabec();
    }

    public void fecharCabec(Long tipoCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.fecharCabec(tipoCabec);
    }

    public void finalizarCabec(){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.finalizarCabec(cabecDAO.getCabecFechado());
    }

    public void fecharRecebidoCabec(){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.fecharRecebidoCabec(posCabecReaj);
    }

    public void finalRecebidoCabec(){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.finalRecebidoCabec(cabecDAO.getCabecFechRecebido());
    }

    public Boolean verEnvioFormComum(){
        CabecDAO cabecDAO = new CabecDAO();
        return (cabecDAO.verCabecFinalizado());
    }

    public Boolean verEnvioFormComplementar(){
        CabecDAO cabecDAO = new CabecDAO();
        return (cabecDAO.verCabecFinalRecebido());
    }

    public boolean verCabecAberto(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.verCabecAberto();
    }

    public boolean verCabecFechado(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.verCabecFechado();
    }

    public boolean verCabecFechRecebido(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.verCabecFechRecebido();
    }

    public List<CabecBean> cabecRecebidoList() {
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.cabecRecebidoList();
    }

    public void receberCabecReaj(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.receberCabecReaj(telaAtual, telaProx, progressDialog);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////Item Cabecalho////////////////////////////////////////////////

    public void delItemAberto(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecAberto();
        RespostaDAO respostaDAO = new RespostaDAO();
        respostaDAO.delItem(cabecBean.getIdCabec());
    }

    public void setItemBean(Long idQuestao, Long idResp, Long seqQuestao) {
        respItemBean = new RespItemBean();
        respItemBean.setIdQuestao(idQuestao);
        respItemBean.setIdResp(idResp);
        respItemBean.setSeqQuestao(seqQuestao);
    }

    public void salvarItem(Long idSubResp, int tipoTela){
        respItemBean.setIdSubResp(idSubResp);
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = cabecDAO.getCabecAberto();
        }
        else if(tipoTela == 2){
            cabecBean = cabecDAO.getCabecFechado();
        }
        else if(tipoTela == 3){
            cabecBean = cabecDAO.getCabecRecebido(posCabecReaj);
        }
        else{
            cabecBean = cabecDAO.getCabecFechRecebido();
        }
        RespostaDAO respostaDAO = new RespostaDAO();
        respostaDAO.delItem(respItemBean, cabecBean.getIdCabec());
        respostaDAO.salvarItem(respItemBean, cabecBean.getIdCabec());
    }

    public Long getIdResp(){
        return respItemBean.getIdResp();
    }

    public List<RespItemBean> respItemList(int tipoTela){
        RespostaDAO respostaDAO = new RespostaDAO();
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(tipoTela == 2){
            cabecBean = cabecDAO.getCabecFechado();
        }
        else{
            cabecBean = cabecDAO.getCabecFechRecebido();
        }
        return respostaDAO.respItemList(cabecBean.getIdCabec());
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

    public void setBrigadistaCabec(ArrayList<Long> brigadistaCabec, int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = cabecDAO.getCabecIniciado();
        }
        else{
            cabecBean = cabecDAO.getCabecFechado();
        }
        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        brigadistaDAO.setBrigadistaCabec(brigadistaCabec, cabecBean.getIdCabec());
    }

    public void delBrigadista(int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = cabecDAO.getCabecIniciado();
        }
        else{
            cabecBean = cabecDAO.getCabecFechado();
        }
        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        brigadistaDAO.delBrigadista(cabecBean.getIdCabec());
    }

    public BrigadistaBean getBrigadista(Long idFunc){
        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        return brigadistaDAO.getBrigadista(idFunc);
    }

    public List<BrigadistaBean> brigadistaList(){
        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        return brigadistaDAO.brigadistaList();
    }

    public List<BrigadistaItemBean> brigadistaItemCabecIniciadoList(){
        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        return brigadistaDAO.brigadistaItemList(getCabecIniciado().getIdCabec());
    }

    public List<BrigadistaItemBean> brigadistaItemCabecAbertoList(){
        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        return brigadistaDAO.brigadistaItemList(getCabecFechado().getIdCabec());
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

    public void setTalhaoCabec(ArrayList<Long> talhaoCabec, int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = cabecDAO.getCabecIniciado();
        }
        else{
            cabecBean = cabecDAO.getCabecFechado();
        }
        talhaoDAO.setTalhaoCabec(talhaoCabec, cabecBean.getIdCabec());
    }

    public List<TalhaoBean> talhaoList(){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.talhaoList(getCodSecao(getSecao()).getIdSecao());
    }

    public List<TalhaoItemBean> talhaoItemList(int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = cabecDAO.getCabecIniciado();
        }
        else{
            cabecBean = cabecDAO.getCabecFechado();
        }
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.talhaoItemList(cabecBean.getIdCabec());
    }

    public List<TalhaoItemBean> talhaoItemCabecIniciadoList(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecIniciado();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.talhaoItemList(cabecBean.getIdCabec());
    }

    public List<TalhaoItemBean> talhaoItemCabecAbertoList(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecFechado();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.talhaoItemList(cabecBean.getIdCabec());
    }

    public TalhaoBean getTalhao(Long idTalhao){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.getTalhao(idTalhao);
    }

    public void setTipoTalhao(Long tipoTalhao, TalhaoItemBean talhaoItemBean){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        talhaoDAO.setTipoTalhao(tipoTalhao, talhaoItemBean);
    }

    public void setHaIncCanaTalhao(Double haIncCanaTalhao, TalhaoItemBean talhaoItemBean){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        talhaoDAO.setHaIncCanaTalhao(haIncCanaTalhao, talhaoItemBean);
    }

    public void setAltCanaTalhao(Long altCanaTalhao, TalhaoItemBean talhaoItemBean){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        talhaoDAO.setAltCanaTalhao(altCanaTalhao, talhaoItemBean);
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

    public void setTanqueCabec(ArrayList<Long> tanqueCabec, int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = cabecDAO.getCabecIniciado();
        }
        else{
            cabecBean = cabecDAO.getCabecFechado();
        }
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.setTanqueCabec(tanqueCabec, cabecBean.getIdCabec());
    }

    public void delTanqueCabec(int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = cabecDAO.getCabecIniciado();
        }
        else{
            cabecBean = cabecDAO.getCabecFechado();
        }
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.delTanque(cabecBean.getIdCabec());
    }

    public List<EquipItemBean> tanqueItemCabecIniciadoList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.tanqueItemList(getCabecIniciado().getIdCabec());
    }

    public List<EquipItemBean> tanqueItemCabecAbertoList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.tanqueItemList(getCabecFechado().getIdCabec());
    }

    public List<EquipItemBean> tanqueItemList(Long idCabec){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.tanqueItemList(idCabec);
    }

    public List<EquipBean> saveiroList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.saveiroList();
    }

    public void setSaveiroCabec(ArrayList<Long> saveiroCabec, int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = cabecDAO.getCabecIniciado();
        }
        else{
            cabecBean = cabecDAO.getCabecFechado();
        }
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.setSaveiroCabec(saveiroCabec, cabecBean.getIdCabec());
    }

    public void delSaveiroCabec(int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = cabecDAO.getCabecIniciado();
        }
        else{
            cabecBean = cabecDAO.getCabecFechado();
        }
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.delSaveiro(cabecBean.getIdCabec());
    }

    public List<EquipItemBean> saveiroItemCabecIniciadoList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.saveiroItemList(getCabecIniciado().getIdCabec());
    }

    public List<EquipItemBean> saveiroItemCabecAbertoList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.saveiroItemList(getCabecFechado().getIdCabec());
    }

    public List<EquipItemBean> saveiroItemList(Long idCabec){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.saveiroItemList(idCabec);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////set Cabecalho////////////////////////////////////////////////

    public void setDataInsCabec(String dataInsCabec, int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setDataInsCabec(dataInsCabec, tipoTela);
    }

    public void setIdFuncCabec(Long matricColabFunc, int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setIdFuncCabec(matricColabFunc, tipoTela);
    }

    public void setTipoApontTrabCabec(Long tipoApontTrabCabec, int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setTipoApontTrabCabec(tipoApontTrabCabec, tipoTela);
    }

    public void setSecaoCabec(Long secaoCabec, int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setSecaoCabec(secaoCabec, tipoTela);
    }

    public void setHaIncAppCabec(Double haIncAppCabec, int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setHaIncAppCabec(haIncAppCabec, tipoTela);
    }

    public void setHaIncForaAppCabec(Double haIncForaAppCabec, int tipoTela) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setHaIncForaAppCabec(haIncForaAppCabec, tipoTela);
    }

    public void setTercCombCabec(Long empresaTercCabec, int tipoTela) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setTercCombCabec(empresaTercCabec, tipoTela);
    }

    public void setOrigemFogoCabec(Long origemFogoCabec, int tipoTela) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setOrigemFogoCabec(origemFogoCabec, tipoTela);
    }

    public void setAceiroCanavialCabec(Long aceiroCanavialCabec, int tipoTela) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setAceiroCanavialCabec(aceiroCanavialCabec, tipoTela);
    }

    public void setAceiroVegetNativalCabec(Long aceiroVegetNativalCabec, int tipoTela) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setAceiroVegetNativalCabec(aceiroVegetNativalCabec, tipoTela);
    }

    public void setComentCabec(String comentCabec, int tipoTela){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setComentCabec(comentCabec, tipoTela);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////get Cabecalho////////////////////////////////////////////////

    public CabecBean getCabecFechado(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.getCabecFechado();
    }

    public CabecBean getCabecIniciado(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.getCabecIniciado();
    }

    public CabecBean getCabecAberto(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.getCabecAberto();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////QUESTAO //////////////////////////////////////////////

    public List<QuestaoBean> questaoList(){
        RespostaDAO respostaDAO = new RespostaDAO();
        return respostaDAO.questaoList();
    }

    public QuestaoBean getQuestao(Long idQuestao){
        RespostaDAO respostaDAO = new RespostaDAO();
        return respostaDAO.getQuestao(idQuestao);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////RESP ////////////////////////////////////////////////

    public List<RespBean> respIdQuestaoList(Long idQuestao){
        RespostaDAO respostaDAO = new RespostaDAO();
        return respostaDAO.respIdQuestaoList(idQuestao);
    }

    public RespBean getResp(Long idResp){
        RespostaDAO respostaDAO = new RespostaDAO();
        return respostaDAO.getResp(idResp);
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

    public Long getSecao() {
        return secao;
    }

    public void setSecao(Long secao) {
        this.secao = secao;
    }

    public int getPosCabecReaj() {
        return posCabecReaj;
    }

    public void setPosCabecReaj(int posCabecReaj) {
        this.posCabecReaj = posCabecReaj;
    }

    ////////////////////////////Dados para Envio///////////////////////////////////////////////////

    public DadosEnvioBean dadosCabecFinalizadoEnvio() {

        DadosEnvioBean dadosEnvioBean = new DadosEnvioBean();

        CabecDAO cabecDAO = new CabecDAO();
        List<CabecBean> cabecList = cabecDAO.cabecFinalizadoList();
        JsonArray cabecJsonArray = new JsonArray();
        JsonArray itemJsonArray = new JsonArray();
        JsonArray brigadistaJsonArray = new JsonArray();
        JsonArray equipJsonArray = new JsonArray();
        JsonArray fotoJsonArray = new JsonArray();
        JsonArray talhaoJsonArray = new JsonArray();

        for (CabecBean cabecBean : cabecList) {

            Gson gsonCabec = new Gson();
            cabecJsonArray.add(gsonCabec.toJsonTree(cabecBean, cabecBean.getClass()));

            RespostaDAO respostaDAO = new RespostaDAO();
            itemJsonArray = respostaDAO.dadosEnvioItem(cabecBean.getIdCabec());

            BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
            brigadistaJsonArray = brigadistaDAO.dadosEnvioBrigadista(cabecBean.getIdCabec());

            EquipDAO equipDAO = new EquipDAO();
            equipJsonArray = equipDAO.dadosEnvioEquip(cabecBean.getIdCabec());

            FotoDAO fotoDAO = new FotoDAO();
            fotoJsonArray = fotoDAO.dadosEnvioFoto(cabecBean.getIdCabec());

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

        JsonObject talhaoJsonObj = new JsonObject();
        talhaoJsonObj.add("talhao", talhaoJsonArray);
        dadosEnvioBean.setTalhao(talhaoJsonObj.toString());

        return dadosEnvioBean;
    }

    public DadosEnvioBean dadosCabecFinalRecebidoEnvio() {

        DadosEnvioBean dadosEnvioBean = new DadosEnvioBean();

        CabecDAO cabecDAO = new CabecDAO();
        List<CabecBean> cabecList = cabecDAO.cabecFinalRecebidoList();
        JsonArray cabecJsonArray = new JsonArray();
        JsonArray itemJsonArray = new JsonArray();

        for (CabecBean cabecBean : cabecList) {

            Gson gsonCabec = new Gson();
            cabecJsonArray.add(gsonCabec.toJsonTree(cabecBean, cabecBean.getClass()));

            RespostaDAO respostaDAO = new RespostaDAO();
            itemJsonArray = respostaDAO.dadosEnvioItem(cabecBean.getIdCabec());

        }

        JsonObject cabecJsonObj = new JsonObject();
        cabecJsonObj.add("cabec", cabecJsonArray);
        dadosEnvioBean.setCabec(cabecJsonObj.toString());

        JsonObject itemJsonObj = new JsonObject();
        itemJsonObj.add("item", itemJsonArray);
        dadosEnvioBean.setItem(itemJsonObj.toString());

        return dadosEnvioBean;

    }

    public void delFormFechado() {
        CabecDAO cabecDAO = new CabecDAO();
        delForm(cabecDAO.cabecFechadoList());
    }

    public void delFormFinalizado() {
        CabecDAO cabecDAO = new CabecDAO();
        delForm(cabecDAO.cabecFinalizadoList());
    }

    public void delItemFechadoRecebido() {
        RespostaDAO respostaDAO = new RespostaDAO();
        CabecDAO cabecDAO = new CabecDAO();
        respostaDAO.delItem(cabecDAO.getCabecFechRecebido().getIdCabec());
        cabecDAO.reabrirRecebidoCabec(cabecDAO.getCabecFechRecebido());
    }

    public void delFormFinalRecebido() {
        CabecDAO cabecDAO = new CabecDAO();
        delForm(cabecDAO.cabecFinalRecebidoList());
    }

    public void delForm(List<CabecBean> cabecList) {

        for (CabecBean cabecBean : cabecList) {

            RespostaDAO respostaDAO = new RespostaDAO();
            respostaDAO.delItem(cabecBean.getIdCabec());

            BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
            brigadistaDAO.delBrigadista(cabecBean.getIdCabec());

            EquipDAO equipDAO = new EquipDAO();
            equipDAO.delEquip(cabecBean.getIdCabec());

            FotoDAO fotoDAO = new FotoDAO();
            fotoDAO.delFotoCabec(cabecBean.getIdCabec());

            TalhaoDAO talhaoDAO = new TalhaoDAO();
            talhaoDAO.delTalhao(cabecBean.getIdCabec());

            CabecDAO cabecDAO = new CabecDAO();
            cabecDAO.delCabec(cabecBean);

        }

    }

    public void delForm(CabecBean cabecBean) {

        RespostaDAO respostaDAO = new RespostaDAO();
        respostaDAO.delItem(cabecBean.getIdCabec());

        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        brigadistaDAO.delBrigadista(cabecBean.getIdCabec());

        EquipDAO equipDAO = new EquipDAO();
        equipDAO.delEquip(cabecBean.getIdCabec());

        FotoDAO fotoDAO = new FotoDAO();
        fotoDAO.delFotoCabec(cabecBean.getIdCabec());

        TalhaoDAO talhaoDAO = new TalhaoDAO();
        talhaoDAO.delTalhao(cabecBean.getIdCabec());

        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.delCabec(cabecBean);

    }

    /////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS POR SERVIDOR /////////////////////

    public void atualDadosBrigad(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("BrigadistaBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    public void atualDadosColab(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("ColabBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    public void atualDadosTercComb(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("TercCombBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    public void atualDadosTipoApont(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("TipoApontBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    public void atualDadosTalhao(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("TalhaoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    public void atualDadosEquip(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("EquipBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    public void atualDadosOrigemFogo(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("OrigemFogoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    public void atualDadosSecao(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("SecaoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////// MANIPULAÇÃO FOTO ///////////////////////////////////////////////

    public FotoItemBean salvarFoto(Bitmap bitmap, Long tipoFoto, int tipoTela){
        FotoDAO fotoDAO = new FotoDAO();
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = cabecDAO.getCabecIniciado();
        }
        else{
            cabecBean = cabecDAO.getCabecFechado();
        }
        return fotoDAO.salvarFoto(cabecBean.getIdCabec(), bitmap, tipoFoto);
    }

    public List getListFotoCabecIniciado(Long tipoFoto, int tipoTela){
        FotoDAO fotoAbordDAO = new FotoDAO();
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = cabecDAO.getCabecIniciado();
        }
        else{
            cabecBean = cabecDAO.getCabecFechado();
        }
        return fotoAbordDAO.getListFotoCabec(cabecBean.getIdCabec(), tipoFoto);
    }

    public Bitmap getStringBitmap(String foto) {
        FotoDAO fotoDAO = new FotoDAO();
        return fotoDAO.getStringBitmap(foto);
    }

    public List<FotoItemBean> getListFotoCabecFechado(Long tipoFoto){
        FotoDAO fotoAbordDAO = new FotoDAO();
        return fotoAbordDAO.getListFotoCabec(getCabecFechado().getIdCabec(), tipoFoto);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
