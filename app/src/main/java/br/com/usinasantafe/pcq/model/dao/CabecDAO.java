package br.com.usinasantafe.pcq.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pcq.util.Tempo;
import br.com.usinasantafe.pcq.util.VerifDadosServ;

public class CabecDAO {

    public CabecDAO() {
    }

    ///////////////////////////// SALVAR E MUDAR STATUS //////////////////////////////////////////

    public void salvarCabecAberto(CabecBean cabecBean){
        cabecBean.setIdFuncCabec(0L);
        cabecBean.setHaIncForaAppCabec(0D);
        cabecBean.setHaIncAppCabec(0D);
        cabecBean.setTercCombCabec(0L);
        cabecBean.setComentCabec("null");
        cabecBean.setStatusCabec(1L);
        cabecBean.setOrigemFogoCabec(1L);

        cabecBean.insert();
    }

    public void setCabecRecebidoParaCabecFinalizado(int pos){
        List<CabecBean> cabecBeanList = cabecRecebidoList();
        CabecBean cabecBean = cabecBeanList.get(pos);
        cabecBeanList.clear();
        cabecBean.setStatusCabec(2L);
        cabecBean.update();
    }

    public void setCabecFinalizadoParaCabecRecebido() {
        CabecBean cabecBean = getCabecFinalizado();
        cabecBean.setStatusCabec(4L);
        cabecBean.update();
    }

    public void finalizarCabec(){
        CabecBean cabecBean = getCabecAberto();
        cabecBean.setStatusCabec(2L);
        cabecBean.update();
    }

    public void finalizarCabecItem(){
        CabecBean cabecBean = getCabecFinalizado();
        cabecBean.setStatusCabec(3L);
        cabecBean.update();
    }

    public void finalizarCabecParaEnvio(){
        CabecBean cabecBean;
        if(verCabecFinalizado()){
            cabecBean = getCabecFinalizado();
        } else {
            cabecBean = getCabecItemFinalizado();
        }
        cabecBean.setStatusCabec(4L);
        cabecBean.update();
    }

    public void receberCabecEnviado(List<CabecBean> cabecBeanList){
        for(CabecBean cabecBean : cabecBeanList){
            CabecBean cabecBeanBD = getCabecIdCabec(cabecBean.getIdCabec());
            cabecBeanBD.setStatusCabec(6L);
            cabecBeanBD.update();
        }
    }

    public void deleteCabec(Long idCabec){
        CabecBean cabecBeanBD = getCabecIdCabec(idCabec);
        cabecBeanBD.delete();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// VERIFICAR POR STATUS ///////////////////////////////////////

    public boolean verCabecAberto(){
        List<CabecBean> cabecList = cabecAbertoList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public boolean verCabecFinalizado(){
        List<CabecBean> cabecList = cabecFinalizadoList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public boolean verCabecItemFinalizado(){
        List<CabecBean> cabecList = cabecItemFinalizadoList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public boolean verCabecEnvio(){
        List<CabecBean> cabecList = cabecEnvioList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public boolean verCabecEnviado(){
        List<CabecBean> cabecList = cabecEnviadoList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// GET CABEC POR STATUS ///////////////////////////////////////

    private CabecBean getCabecIdCabec(Long idCabec){
        List<CabecBean> cabecList = cabecList(idCabec);
        CabecBean cabecBean = cabecList.get(0);
        cabecList.clear();
        return cabecBean;
    }

    public CabecBean getCabecAberto(){
        List<CabecBean> cabecList = cabecAbertoList();
        CabecBean cabecBean = cabecList.get(0);
        cabecList.clear();
        return cabecBean;
    }

    public CabecBean getCabecFinalizado(){
        List<CabecBean> cabecList = cabecFinalizadoList();
        CabecBean cabecBean = cabecList.get(0);
        cabecList.clear();
        return cabecBean;
    }

    public CabecBean getCabecItemFinalizado(){
        List<CabecBean> cabecList = cabecItemFinalizadoList();
        CabecBean cabecBean = cabecList.get(0);
        cabecList.clear();
        return cabecBean;
    }

    public CabecBean getCabecEnviado(){
        List<CabecBean> cabecList = cabecEnviadoList();
        CabecBean cabecBean = cabecList.get(0);
        cabecList.clear();
        return cabecBean;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// RETORNO LIST CABEC /////////////////////////////////////////

    private List<CabecBean> cabecList(Long idCabec) {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    public List<CabecBean> cabecAbertoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusCabecAberto());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    private List<CabecBean> cabecFinalizadoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusCabecFinalizado());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    public List<CabecBean> cabecItemFinalizadoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusCabecItemFinalizado());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    public List<CabecBean> cabecEnvioList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusEnvio());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    public List<CabecBean> cabecRecebidoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusRecebido());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    public List<CabecBean> cabecEnviadoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusEnviado());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    public ArrayList<CabecBean> cabecExcluirArrayList(){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusEnviado());

        CabecBean cabecBean = new CabecBean();
        List<CabecBean> cabecBeanList =  cabecBean.get(pesqArrayList);

        ArrayList<CabecBean> cabecBeanArrayList = new ArrayList<>();
        for (CabecBean cabecBeanBD : cabecBeanList) {
            if(cabecBeanBD.getDthrCabecLong() < Tempo.getInstance().subDiaLong(3)) {
                cabecBeanArrayList.add(cabecBeanBD);
            }
        }
        cabecBeanList.clear();
        return cabecBeanArrayList;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// PESQUISA CABEC ////////////////////////////////////////////

    private EspecificaPesquisa getPesqIdCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabec");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusCabecAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusCabecFinalizado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusCabecItemFinalizado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(3L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusEnvio(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(4L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusRecebido(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(5L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusEnviado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(6L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// SETAR VALORES CAMPOS ////////////////////////////////////////

    public void setDataInsCabec(String dataInsCabec){
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = getCabecAberto();
        }
        else{
            cabecBean = getCabecItemFinalizado();
        }
        cabecBean.setDataInsCabec(dataInsCabec);
        cabecBean.update();
    }

    public void setIdFuncCabec(Long matricColabCabec){
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = getCabecAberto();
        }
        else{
            cabecBean = getCabecItemFinalizado();
        }
        cabecBean.setIdFuncCabec(matricColabCabec);
        cabecBean.update();
    }

    public void setTipoApontTrabCabec(Long tipoApontTrabCabec){
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = getCabecAberto();
        }
        else{
            cabecBean = getCabecItemFinalizado();
        }
        cabecBean.setTipoApontTrabCabec(tipoApontTrabCabec);
        cabecBean.update();
    }

    public void setSecaoCabec(Long secaoCabec){
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = getCabecAberto();
        }
        else{
            cabecBean = getCabecItemFinalizado();
        }
        cabecBean.setSecaoCabec(secaoCabec);
        cabecBean.update();
    }

    public void setHaIncAppCabec(Double haIncAppCabec){
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = getCabecAberto();
        }
        else{
            cabecBean = getCabecItemFinalizado();
        }
        cabecBean.setHaIncAppCabec(haIncAppCabec);
        cabecBean.update();
    }

    public void setHaIncForaAppCabec(Double haIncForaAppCabec) {
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = getCabecAberto();
        }
        else{
            cabecBean = getCabecItemFinalizado();
        }
        cabecBean.setHaIncForaAppCabec(haIncForaAppCabec);
        cabecBean.update();
    }

    public void setTercCombCabec(Long empresaTercCabec) {
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = getCabecAberto();
        }
        else{
            cabecBean = getCabecItemFinalizado();
        }
        cabecBean.setTercCombCabec(empresaTercCabec);
        cabecBean.update();
    }

    public void setOrigemFogoCabec(Long origemFogoCabec) {
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = getCabecAberto();
        }
        else{
            cabecBean = getCabecItemFinalizado();
        }
        cabecBean.setOrigemFogoCabec(origemFogoCabec);
        cabecBean.update();
    }

    public void setAceiroCanavialCabec(Long aceiroCanavialCabec) {
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = getCabecAberto();
        }
        else{
            cabecBean = getCabecItemFinalizado();
        }
        cabecBean.setAceiroCanavialCabec(aceiroCanavialCabec);
        cabecBean.update();
    }

    public void setAceiroVegetNativalCabec(Long aceiroVegetNativalCabec) {
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = getCabecAberto();
        }
        else{
            cabecBean = getCabecItemFinalizado();
        }
        cabecBean.setAceiroVegetNativalCabec(aceiroVegetNativalCabec);
        cabecBean.update();
    }

    public void setComentCabec(String comentCabec){
        CabecBean cabecBean;
        if(verCabecAberto()){
            cabecBean = getCabecAberto();
        }
        else{
            cabecBean = getCabecItemFinalizado();
        }
        cabecBean.setComentCabec(comentCabec);
        Long dthrLong = Tempo.getInstance().dthrAtualLong();
        cabecBean.setDthrCabec(Tempo.getInstance().dthrLongToString(dthrLong));
        cabecBean.setDthrCabecLong(dthrLong);
        cabecBean.update();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// RECEBER CABEC ////////////////////////////////////////////

    public void receberCabec(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().verDados("Cabec", telaAtual, telaProx, progressDialog);
    }

    public void receberDadosCabec(String result) {

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                List<CabecBean> cabecList = cabecRecebidoList();
                for(CabecBean cabecBean : cabecList){
                    cabecBean.delete();
                }

                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject objeto = jsonArray.getJSONObject(j);
                    Gson gson = new Gson();
                    CabecBean cabecBean = gson.fromJson(objeto.toString(), CabecBean.class);
                    cabecBean.insert();
                }

                VerifDadosServ.getInstance().pulaTelaSemTerm();

            } else {
                VerifDadosServ.getInstance().msgSemTerm("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }

        } catch (Exception e) {
            VerifDadosServ.getInstance().msgSemTerm("FALHA DE PESQUISA DE FORMULÁRIOS! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<String> cabecAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("CABECALHO");
        CabecBean cabecBean = new CabecBean();
        List<CabecBean> cabecList = cabecBean.orderBy("idCabec", true);
        for (CabecBean cabecBeanBD : cabecList) {
            dadosArrayList.add(dadosCabec(cabecBeanBD));
        }
        cabecList.clear();
        return dadosArrayList;
    }

    private String dadosCabec(CabecBean cabecBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(cabecBean, cabecBean.getClass()).toString();
    }

}
