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

    public void salvarCabecIniciado(CabecBean cabecBean){
        cabecBean.setIdFuncCabec(0L);
        cabecBean.setHaIncForaAppCabec(0D);
        cabecBean.setHaIncAppCabec(0D);
        cabecBean.setTercCombCabec(0L);
        cabecBean.setComentCabec("null");
        cabecBean.setStatusCabec(0L);
        cabecBean.setOrigemFogoCabec(1L);
        cabecBean.insert();
    }

    public void delCabec(CabecBean cabecBean){
        cabecBean.delete();
    }

    public void abrirCabec(){
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setStatusCabec(1L);
        cabecBean.update();
    }

    public void fecharCabec(Long tipoCabec){
        CabecBean cabecBean;
        if(tipoCabec == 1){
            cabecBean = getCabecAberto();
        }
        else{
            cabecBean = getCabecIniciado();
        }
        cabecBean.setStatusCabec(2L);
        cabecBean.update();
    }

    public void finalizarCabec(CabecBean cabecBean){
        cabecBean.setStatusCabec(3L);
        cabecBean.update();
    }

    public void fecharRecebidoCabec(int pos){
        CabecBean cabecBean = getCabecRecebido(pos);
        cabecBean.setStatusCabec(5L);
        cabecBean.update();
    }

    public void finalRecebidoCabec(CabecBean cabecBean){
        cabecBean.setStatusCabec(6L);
        cabecBean.update();
    }

    public void reabrirRecebidoCabec(CabecBean cabecBean){
        cabecBean.setStatusCabec(4L);
        cabecBean.update();
    }

    public void finalRecebidoCabec(){
        CabecBean cabecBean = getCabecFechRecebido();
        cabecBean.setStatusCabec(6L);
        cabecBean.update();
    }

    public boolean verCabecIniciado(){
        List<CabecBean> cabecList = cabecIniciadoList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public boolean verCabecAberto(){
        List<CabecBean> cabecList = cabecAbertoList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public boolean verCabecFechado(){
        List<CabecBean> cabecList = cabecFechadoList();
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

    public boolean verCabecFechRecebido(){
        List<CabecBean> cabecList = cabecFechadoRecebidoList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public boolean verCabecFinalRecebido(){
        List<CabecBean> cabecList = cabecFinalRecebidoList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public CabecBean getCabecIniciado(){
        List<CabecBean> cabecList = cabecIniciadoList();
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

    public CabecBean getCabecFechado(){
        List<CabecBean> cabecList = cabecFechadoList();
        CabecBean cabecBean = cabecList.get(0);
        cabecList.clear();
        return cabecBean;
    }

    public CabecBean getCabecRecebido(int pos){
        List<CabecBean> cabecList = cabecRecebidoList();
        CabecBean cabecBean = cabecList.get(pos);
        cabecList.clear();
        return cabecBean;
    }

    public CabecBean getCabecFechRecebido(){
        List<CabecBean> cabecList = cabecFechadoRecebidoList();
        CabecBean cabecBean = cabecList.get(0);
        cabecList.clear();
        return cabecBean;
    }

    private List<CabecBean> cabecIniciadoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusIniciado());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    private List<CabecBean> cabecAbertoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusAberto());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    public List<CabecBean> cabecFechadoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusFechado());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    public List<CabecBean> cabecFinalizadoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusFinalizado());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    public List<CabecBean> cabecRecebidoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusRecebido());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    public List<CabecBean> cabecFechadoRecebidoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusFechRecebido());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    public List<CabecBean> cabecFinalRecebidoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusFinalRecebido());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }


    public void setDataInsCabec(String dataInsCabec, int tipoTela){
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = getCabecIniciado();
        }
        else{
            cabecBean = getCabecFechado();
        }
        cabecBean.setDataInsCabec(dataInsCabec);
        cabecBean.update();
    }

    public void setIdFuncCabec(Long matricColabCabec, int tipoTela){
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = getCabecIniciado();
        }
        else{
            cabecBean = getCabecFechado();
        }
        cabecBean.setIdFuncCabec(matricColabCabec);
        cabecBean.update();
    }

    public void setTipoApontTrabCabec(Long tipoApontTrabCabec, int tipoTela){
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = getCabecIniciado();
        }
        else{
            cabecBean = getCabecFechado();
        }
        cabecBean.setTipoApontTrabCabec(tipoApontTrabCabec);
        cabecBean.update();
    }

    public void setSecaoCabec(Long secaoCabec, int tipoTela){
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = getCabecIniciado();
        }
        else{
            cabecBean = getCabecFechado();
        }
        cabecBean.setSecaoCabec(secaoCabec);
        cabecBean.update();
    }

    public void setHaIncAppCabec(Double haIncAppCabec, int tipoTela){
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = getCabecIniciado();
        }
        else{
            cabecBean = getCabecFechado();
        }
        cabecBean.setHaIncAppCabec(haIncAppCabec);
        cabecBean.update();
    }

    public void setHaIncForaAppCabec(Double haIncForaAppCabec, int tipoTela) {
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = getCabecIniciado();
        }
        else{
            cabecBean = getCabecFechado();
        }
        cabecBean.setHaIncForaAppCabec(haIncForaAppCabec);
        cabecBean.update();
    }

    public void setTercCombCabec(Long empresaTercCabec, int tipoTela) {
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = getCabecIniciado();
        }
        else{
            cabecBean = getCabecFechado();
        }
        cabecBean.setTercCombCabec(empresaTercCabec);
        cabecBean.update();
    }

    public void setOrigemFogoCabec(Long origemFogoCabec, int tipoTela) {
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = getCabecIniciado();
        }
        else{
            cabecBean = getCabecFechado();
        }
        cabecBean.setOrigemFogoCabec(origemFogoCabec);
        cabecBean.update();
    }

    public void setAceiroCanavialCabec(Long aceiroCanavialCabec, int tipoTela) {
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = getCabecIniciado();
        }
        else{
            cabecBean = getCabecFechado();
        }
        cabecBean.setAceiroCanavialCabec(aceiroCanavialCabec);
        cabecBean.update();
    }

    public void setAceiroVegetNativalCabec(Long aceiroVegetNativalCabec, int tipoTela) {
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = getCabecIniciado();
        }
        else{
            cabecBean = getCabecFechado();
        }
        cabecBean.setAceiroVegetNativalCabec(aceiroVegetNativalCabec);
        cabecBean.update();
    }

    public void setComentCabec(String comentCabec, int tipoTela){
        CabecBean cabecBean;
        if(tipoTela == 1){
            cabecBean = getCabecIniciado();
        }
        else{
            cabecBean = getCabecFechado();
        }
        cabecBean.setComentCabec(comentCabec);
        cabecBean.setDthrCabec(Tempo.getInstance().dataComHora());
        cabecBean.update();
    }

    private EspecificaPesquisa getPesqStatusIniciado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(0L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusFechado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusFinalizado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(3L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusRecebido(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(4L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusFechRecebido(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(5L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusFinalRecebido(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(6L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqTipoCompleto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("tipoCabec");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    public void receberCabecReaj(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().setVerTerm(true);
        VerifDadosServ.getInstance().verDados("Cabec", telaAtual, telaProx, progressDialog);

    }

    public void recDadosCabecReaj(String result) {

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                List<CabecBean> cabecList = cabecRecebidoList();
                for(CabecBean cabecBean : cabecList){
                    delCabec(cabecBean);
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

}
