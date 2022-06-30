package br.com.usinasantafe.pcq.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

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
import br.com.usinasantafe.pcq.model.bean.variaveis.RespItemBean;
import br.com.usinasantafe.pcq.model.dao.TercCombDAO;
import br.com.usinasantafe.pcq.model.dao.TipoApontDAO;
import br.com.usinasantafe.pcq.util.AtualDadosServ;
import br.com.usinasantafe.pcq.util.EnvioDadosServ;

public class FormularioCTR {

    private int posTalhao;
    private int posCriterio;
    private int posCabecReaj;
    private RespItemBean respItemBean;

    public FormularioCTR() {
        if(respItemBean == null)
            respItemBean = new RespItemBean();
    }

    //////////////////////////////Cabecalho ///////////////////////////////////////////////////////

    public void salvarCabecAberto(Long tipo){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = new CabecBean();
        ConfigCTR configCTR = new ConfigCTR();
        cabecBean.setTipoCabec(tipo);
        cabecBean.setNroAparelhoCabec(configCTR.getConfig().getNroAparelhoConfig());
        cabecDAO.salvarCabecAberto(cabecBean);
    }

    public void excluirCabecAberto(){
        CabecDAO cabecDAO = new CabecDAO();
        excluirCabec(cabecDAO.getCabecAberto());
    }

    public void setCabecFinalizadoParaCabecRecebido(){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setCabecFinalizadoParaCabecRecebido();
    }

    public void setCabecRecebidoParaCabecFinalizado(int pos){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setCabecRecebidoParaCabecFinalizado(pos);
    }

    public void finalizarCabec(){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.finalizarCabec();
    }

    public void finalizarCabecItem(){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.finalizarCabecItem();
    }

    public void finalizarCabecParaEnvio(String activity){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.finalizarCabecParaEnvio();
        EnvioDadosServ.getInstance().envioDados(activity);
    }

    public boolean verCabecAberto(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.verCabecAberto();
    }

    public Boolean verCabecEnvio(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.verCabecEnvio();
    }

    public boolean verCabecFinalizado(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.verCabecFinalizado();
    }

    public boolean verCabecItemFinalizado(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.verCabecItemFinalizado();
    }

    public List<CabecBean> cabecRecebidoList() {
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.cabecRecebidoList();
    }

    public void receberCabec(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.receberCabec(telaAtual, telaProx, progressDialog);
    }

    public void receberCabecEnviado(List<CabecBean> cabecBeanList, String activity){

        Log.i("PCQ", "RECEBENDO DADOS 2");
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.receberCabecEnviado(cabecBeanList);

        EnvioDadosServ.getInstance().envioDados(activity);

    }

    public void deleteCabecAberto(){
        CabecDAO cabecDAO = new CabecDAO();
        List<CabecBean> cabecBeanArrayList = cabecDAO.cabecAbertoList();
        for (CabecBean cabecBean : cabecBeanArrayList) {
            excluirCabec(cabecBean);
        }
    }

    public void deleteCabecEnviado(){
        CabecDAO cabecDAO = new CabecDAO();
        ArrayList<CabecBean> cabecBeanArrayList = cabecDAO.cabecExcluirArrayList();
        for (CabecBean cabecBean : cabecBeanArrayList) {
            excluirCabec(cabecBean);
        }
    }

    public void excluirCabec(CabecBean cabecBean) {

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
        cabecDAO.deleteCabec(cabecBean.getIdCabec());

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////Item Cabecalho////////////////////////////////////////////////

    public void excluirItemCabec(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(verCabecFinalizado()){
            cabecBean = cabecDAO.getCabecFinalizado();
        } else {
            cabecBean = cabecDAO.getCabecItemFinalizado();
        }
        RespostaDAO respostaDAO = new RespostaDAO();
        respostaDAO.delItem(cabecBean.getIdCabec());
    }

    public void setItemBean(Long idQuestao, Long idResp, Long seqQuestao) {
        respItemBean = new RespItemBean();
        respItemBean.setIdQuestao(idQuestao);
        respItemBean.setIdResp(idResp);
        respItemBean.setSeqQuestao(seqQuestao);
    }

    public void salvarAtualizarItem(Long idSubResp){
        respItemBean.setIdSubResp(idSubResp);
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(verCabecFinalizado()){
            cabecBean = cabecDAO.getCabecFinalizado();
        } else {
            cabecBean = cabecDAO.getCabecItemFinalizado();
        }
        RespostaDAO respostaDAO = new RespostaDAO();
        respostaDAO.salvarAtualizarItem(respItemBean, cabecBean.getIdCabec());
    }

    public Long getIdResp(){
        return respItemBean.getIdResp();
    }

    public List<RespItemBean> respItemList(){
        RespostaDAO respostaDAO = new RespostaDAO();
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecItemFinalizado();
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

    public void setBrigadistaCabec(ArrayList<Long> brigadistaCabec){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = cabecDAO.getCabecAberto();
        }
        else{
            cabecBean = cabecDAO.getCabecItemFinalizado();
        }
        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        brigadistaDAO.setBrigadistaCabec(brigadistaCabec, cabecBean.getIdCabec());
    }

    public void delBrigadista(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = cabecDAO.getCabecAberto();
        }
        else{
            cabecBean = cabecDAO.getCabecItemFinalizado();
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
        return brigadistaDAO.brigadistaItemList(getCabecAberto().getIdCabec());
    }

    public List<BrigadistaItemBean> brigadistaItemCabecAbertoList(){
        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        return brigadistaDAO.brigadistaItemList(getCabecItemFinalizado().getIdCabec());
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

    public void setTalhaoCabec(ArrayList<Long> talhaoCabec){
        CabecDAO cabecDAO = new CabecDAO();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = cabecDAO.getCabecAberto();
        }
        else{
            cabecBean = cabecDAO.getCabecItemFinalizado();
        }
        talhaoDAO.setTalhaoCabec(talhaoCabec, cabecBean.getIdCabec());
    }

    public List<TalhaoBean> talhaoList(){
        CabecDAO cabecDAO = new CabecDAO();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = cabecDAO.getCabecAberto();
        }
        else{
            cabecBean = cabecDAO.getCabecItemFinalizado();
        }
        return talhaoDAO.talhaoList(cabecBean.getSecaoCabec());
    }

    public List<TalhaoItemBean> talhaoItemList(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = cabecDAO.getCabecAberto();
        }
        else{
            cabecBean = cabecDAO.getCabecItemFinalizado();
        }
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.talhaoItemList(cabecBean.getIdCabec());
    }

    public List<TalhaoItemBean> talhaoItemCabecIniciadoList(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean = cabecDAO.getCabecAberto();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.talhaoItemList(cabecBean.getIdCabec());
    }

    public List<TalhaoItemBean> talhaoList(Long idCabec){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.talhaoItemList(idCabec);
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

    public void setTanqueCabec(ArrayList<Long> tanqueCabec){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = cabecDAO.getCabecAberto();
        }
        else{
            cabecBean = cabecDAO.getCabecItemFinalizado();
        }
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.setTanqueCabec(tanqueCabec, cabecBean.getIdCabec());
    }

    public void delTanqueCabec(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = cabecDAO.getCabecAberto();
        }
        else{
            cabecBean = cabecDAO.getCabecItemFinalizado();
        }
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.delTanque(cabecBean.getIdCabec());
    }

    public List<EquipItemBean> tanqueCabecAbertoList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.tanqueItemList(getCabecAberto().getIdCabec());
    }

    public List<EquipItemBean> tanqueCabecFinalizadoList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.tanqueItemList(getCabecFinalizado().getIdCabec());
    }

    public List<EquipItemBean> tanqueItemList(Long idCabec){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.tanqueItemList(idCabec);
    }

    public List<EquipBean> saveiroList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.saveiroList();
    }

    public void setSaveiroCabec(ArrayList<Long> saveiroCabec){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = cabecDAO.getCabecAberto();
        }
        else{
            cabecBean = cabecDAO.getCabecItemFinalizado();
        }
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.setSaveiroCabec(saveiroCabec, cabecBean.getIdCabec());
    }

    public void delSaveiroCabec(){
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = cabecDAO.getCabecAberto();
        }
        else{
            cabecBean = cabecDAO.getCabecItemFinalizado();
        }
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.delSaveiro(cabecBean.getIdCabec());
    }

    public List<EquipItemBean> saveiroItemCabecIniciadoList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.saveiroItemList(getCabecAberto().getIdCabec());
    }

    public List<EquipItemBean> saveiroItemCabecAbertoList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.saveiroItemList(getCabecItemFinalizado().getIdCabec());
    }

    public List<EquipItemBean> saveiroItemList(Long idCabec){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.saveiroItemList(idCabec);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////set Cabecalho////////////////////////////////////////////////

    public void setDataInsCabec(String dataInsCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setDataInsCabec(dataInsCabec);
    }

    public void setIdFuncCabec(Long matricColabFunc){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setIdFuncCabec(matricColabFunc);
    }

    public void setTipoApontTrabCabec(Long tipoApontTrabCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setTipoApontTrabCabec(tipoApontTrabCabec);
    }

    public void setSecaoCabec(Long secaoCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setSecaoCabec(secaoCabec);
    }

    public void setHaIncAppCabec(Double haIncAppCabec){
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setHaIncAppCabec(haIncAppCabec);
    }

    public void setHaIncForaAppCabec(Double haIncForaAppCabec) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setHaIncForaAppCabec(haIncForaAppCabec);
    }

    public void setTercCombCabec(Long empresaTercCabec) {
        CabecDAO cabecDAO = new CabecDAO();
        cabecDAO.setTercCombCabec(empresaTercCabec);
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

    public CabecBean getCabecFinalizado(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.getCabecFinalizado();
    }

    public CabecBean getCabecItemFinalizado(){
        CabecDAO cabecDAO = new CabecDAO();
        return cabecDAO.getCabecItemFinalizado();
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

    ////////////////////////////Dados para Envio///////////////////////////////////////////////////


    public List<CabecBean> dadosCabecFinalizadoCompleto() {
        CabecDAO cabecDAO = new CabecDAO();
        List<CabecBean> cabecList = cabecDAO.cabecEnvioList();
        for (int i = 0; i < cabecList.size(); i++) {
            RespostaDAO respostaDAO = new RespostaDAO();
            List<RespItemBean> respItemBeanList = respostaDAO.respItemList(cabecList.get(i).getIdCabec());
            cabecList.get(i).setRespItemBeanList(respItemBeanList);
            BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
            List<BrigadistaItemBean> brigadistaItemBeanList = brigadistaDAO.brigadistaItemList(cabecList.get(i).getIdCabec());
            cabecList.get(i).setBrigadistaItemBeanList(brigadistaItemBeanList);
            EquipDAO equipDAO = new EquipDAO();
            List<EquipItemBean> equipItemBeanList = equipDAO.equipItemList(cabecList.get(i).getIdCabec());
            cabecList.get(i).setEquipItemBeanList(equipItemBeanList);
            FotoDAO fotoDAO = new FotoDAO();
            List<FotoItemBean> fotoItemBeanList = fotoDAO.fotoItemList(cabecList.get(i).getIdCabec());
            cabecList.get(i).setFotoItemBeanList(fotoItemBeanList);
            TalhaoDAO talhaoDAO = new TalhaoDAO();
            List<TalhaoItemBean> talhaoItemBeanList = talhaoDAO.talhaoItemList(cabecList.get(i).getIdCabec());
            cabecList.get(i).setTalhaoItemBeanList(talhaoItemBeanList);
        }
        return cabecList;
    }

//    public DadosEnvioBean dadosCabecFinalizadoCompleto() {
//
//        DadosEnvioBean dadosEnvioBean = new DadosEnvioBean();
//
//        CabecDAO cabecDAO = new CabecDAO();
//        List<CabecBean> cabecList = cabecDAO.cabecFinalizadoList();
//        JsonArray cabecJsonArray = new JsonArray();
//        JsonArray itemJsonArray = new JsonArray();
//        JsonArray brigadistaJsonArray = new JsonArray();
//        JsonArray equipJsonArray = new JsonArray();
//        JsonArray fotoJsonArray = new JsonArray();
//        JsonArray talhaoJsonArray = new JsonArray();
//
//        for (CabecBean cabecBean : cabecList) {
//
//            Gson gsonCabec = new Gson();
//            cabecJsonArray.add(gsonCabec.toJsonTree(cabecBean, cabecBean.getClass()));
//
//            RespostaDAO respostaDAO = new RespostaDAO();
//            itemJsonArray = respostaDAO.dadosEnvioItem(cabecBean.getIdCabec());
//
//            BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
//            brigadistaJsonArray = brigadistaDAO.dadosEnvioBrigadista(cabecBean.getIdCabec());
//
//            EquipDAO equipDAO = new EquipDAO();
//            equipJsonArray = equipDAO.dadosEnvioEquip(cabecBean.getIdCabec());
//
//            FotoDAO fotoDAO = new FotoDAO();
//            fotoJsonArray = fotoDAO.dadosEnvioFoto(cabecBean.getIdCabec());
//
//            TalhaoDAO talhaoDAO = new TalhaoDAO();
//            talhaoJsonArray = talhaoDAO.dadosEnvioTalhao(cabecBean.getIdCabec());
//
//        }
//
//        JsonObject cabecJsonObj = new JsonObject();
//        cabecJsonObj.add("cabec", cabecJsonArray);
//        dadosEnvioBean.setCabec(cabecJsonObj.toString());
//
//        JsonObject itemJsonObj = new JsonObject();
//        itemJsonObj.add("item", itemJsonArray);
//        dadosEnvioBean.setItem(itemJsonObj.toString());
//
//        JsonObject brigadistaJsonObj = new JsonObject();
//        brigadistaJsonObj.add("brigadista", brigadistaJsonArray);
//        dadosEnvioBean.setBrigadista(brigadistaJsonObj.toString());
//
//        JsonObject equipJsonObj = new JsonObject();
//        equipJsonObj.add("equip", equipJsonArray);
//        dadosEnvioBean.setEquip(equipJsonObj.toString());
//
//        JsonObject fotoJsonObj = new JsonObject();
//        fotoJsonObj.add("foto", fotoJsonArray);
//        dadosEnvioBean.setFoto(fotoJsonObj.toString());
//
//        JsonObject talhaoJsonObj = new JsonObject();
//        talhaoJsonObj.add("talhao", talhaoJsonArray);
//        dadosEnvioBean.setTalhao(talhaoJsonObj.toString());
//
//        return dadosEnvioBean;
//    }
//
//    public DadosEnvioBean dadosCabecFinalizadoSimples() {
//
//        DadosEnvioBean dadosEnvioBean = new DadosEnvioBean();
//
//        CabecDAO cabecDAO = new CabecDAO();
//        List<CabecBean> cabecList = cabecDAO.cabecFinalRecebidoList();
//        JsonArray cabecJsonArray = new JsonArray();
//        JsonArray itemJsonArray = new JsonArray();
//
//        for (CabecBean cabecBean : cabecList) {
//
//            Gson gsonCabec = new Gson();
//            cabecJsonArray.add(gsonCabec.toJsonTree(cabecBean, cabecBean.getClass()));
//
//            RespostaDAO respostaDAO = new RespostaDAO();
//            itemJsonArray = respostaDAO.dadosEnvioItem(cabecBean.getIdCabec());
//
//        }
//
//        JsonObject cabecJsonObj = new JsonObject();
//        cabecJsonObj.add("cabec", cabecJsonArray);
//        dadosEnvioBean.setCabec(cabecJsonObj.toString());
//
//        JsonObject itemJsonObj = new JsonObject();
//        itemJsonObj.add("item", itemJsonArray);
//        dadosEnvioBean.setItem(itemJsonObj.toString());
//
//        return dadosEnvioBean;
//
//    }
//
//    public void delFormFechado() {
//        CabecDAO cabecDAO = new CabecDAO();
//        delForm(cabecDAO.cabecItemFinalizadoList());
//    }
//
//    public void delFormFinalizado() {
//        CabecDAO cabecDAO = new CabecDAO();
//        delForm(cabecDAO.cabecEnvioList());
//    }
//
//    public void delItemFechadoRecebido() {
//        RespostaDAO respostaDAO = new RespostaDAO();
//        CabecDAO cabecDAO = new CabecDAO();
//        respostaDAO.delItem(cabecDAO.getCabecEnviado().getIdCabec());
//        cabecDAO.reabrirRecebidoCabec(cabecDAO.getCabecEnviado());
//    }
//
//    public void delFormFinalRecebido() {
//        CabecDAO cabecDAO = new CabecDAO();
//        delForm(cabecDAO.cabecFinalRecebidoList());
//    }
//
//    public void delForm(List<CabecBean> cabecList) {
//
//        for (CabecBean cabecBean : cabecList) {
//
//            RespostaDAO respostaDAO = new RespostaDAO();
//            respostaDAO.delItem(cabecBean.getIdCabec());
//
//            BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
//            brigadistaDAO.delBrigadista(cabecBean.getIdCabec());
//
//            EquipDAO equipDAO = new EquipDAO();
//            equipDAO.delEquip(cabecBean.getIdCabec());
//
//            FotoDAO fotoDAO = new FotoDAO();
//            fotoDAO.delFotoCabec(cabecBean.getIdCabec());
//
//            TalhaoDAO talhaoDAO = new TalhaoDAO();
//            talhaoDAO.delTalhao(cabecBean.getIdCabec());
//
//            CabecDAO cabecDAO = new CabecDAO();
//            cabecDAO.delCabec(cabecBean);
//
//        }
//
//    }
//
//    public void delForm(CabecBean cabecBean) {
//
//        RespostaDAO respostaDAO = new RespostaDAO();
//        respostaDAO.delItem(cabecBean.getIdCabec());
//
//        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
//        brigadistaDAO.delBrigadista(cabecBean.getIdCabec());
//
//        EquipDAO equipDAO = new EquipDAO();
//        equipDAO.delEquip(cabecBean.getIdCabec());
//
//        FotoDAO fotoDAO = new FotoDAO();
//        fotoDAO.delFotoCabec(cabecBean.getIdCabec());
//
//        TalhaoDAO talhaoDAO = new TalhaoDAO();
//        talhaoDAO.delTalhao(cabecBean.getIdCabec());
//
//        CabecDAO cabecDAO = new CabecDAO();
//        cabecDAO.delCabec(cabecBean);
//
//    }

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

    public FotoItemBean salvarFoto(Bitmap bitmap, Long tipoFoto){
        FotoDAO fotoDAO = new FotoDAO();
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = cabecDAO.getCabecAberto();
        }
        else{
            cabecBean = cabecDAO.getCabecItemFinalizado();
        }
        return fotoDAO.salvarFoto(cabecBean.getIdCabec(), bitmap, tipoFoto);
    }

    public List getListFotoCabecIniciado(Long tipoFoto){
        FotoDAO fotoAbordDAO = new FotoDAO();
        CabecDAO cabecDAO = new CabecDAO();
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = cabecDAO.getCabecAberto();
        }
        else{
            cabecBean = cabecDAO.getCabecItemFinalizado();
        }
        return fotoAbordDAO.getListFotoCabec(cabecBean.getIdCabec(), tipoFoto);
    }

    public Bitmap getStringBitmap(String foto) {
        FotoDAO fotoDAO = new FotoDAO();
        return fotoDAO.getStringBitmap(foto);
    }

    public List<FotoItemBean> getListFotoCabecFinalizado(Long tipoFoto){
        FotoDAO fotoAbordDAO = new FotoDAO();
        return fotoAbordDAO.getListFotoCabec(getCabecFinalizado().getIdCabec(), tipoFoto);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
