package br.com.usinasantafe.pcq.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pcq.util.Tempo;

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

    public void delCabecInic(){
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.delete();
    }


    public void fecharCabec(CabecBean cabecBean){
        cabecBean.setStatusCabec(2L);
        cabecBean.update();
    }

    public boolean verCabecIniciado(){
        List<CabecBean> cabecList = getCabecIniciadoList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public boolean verCabecAberto(){
        List<CabecBean> cabecList = getCabecAbertoList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public CabecBean getCabecIniciado(){
        List<CabecBean> cabecList = getCabecIniciadoList();
        CabecBean cabecBean = cabecList.get(0);
        cabecList.clear();
        return cabecBean;
    }

    public CabecBean getCabecAberto(){
        List<CabecBean> cabecList = getCabecAbertoList();
        CabecBean cabecBean = cabecList.get(0);
        cabecList.clear();
        return cabecBean;
    }

    private List<CabecBean> getCabecIniciadoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusIniciado());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    private List<CabecBean> getCabecAbertoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusAberto());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    public List<CabecBean> cabecFechList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusFechado());
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get(pesqArrayList);
    }

    public void setIdFuncCabec(Long matricColabCabec){
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setIdFuncCabec(matricColabCabec);
        cabecBean.update();
    }

    public void setTipoApontTrabCabec(Long tipoApontTrabCabec){
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setTipoApontTrabCabec(tipoApontTrabCabec);
        cabecBean.update();
    }

    public void setSecaoCabec(Long secaoCabec){
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setSecaoCabec(secaoCabec);
        cabecBean.update();
    }

    public void setHaIncAppCabec(Double haIncAppCabec){
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setHaIncAppCabec(haIncAppCabec);
        cabecBean.update();
    }

    public void setHaIncForaAppCabec(Double haIncForaAppCabec) {
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setHaIncForaAppCabec(haIncForaAppCabec);
        cabecBean.update();
    }

    public void setTercCombCabec(Long empresaTercCabec) {
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setTercCombCabec(empresaTercCabec);
        cabecBean.update();
    }

    public void setOrigemFogoCabec(Long origemFogoCabec) {
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setOrigemFogoCabec(origemFogoCabec);
        cabecBean.update();
    }

    public void setAceiroCanavialCabec(Long aceiroCanavialCabec) {
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setAceiroCanavialCabec(aceiroCanavialCabec);
        cabecBean.update();
    }

    public void setAceiroVegetNativalCabec(Long aceiroVegetNativalCabec) {
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setAceiroVegetNativalCabec(aceiroVegetNativalCabec);
        cabecBean.update();
    }

    public void setComentCabec(String comentCabec){
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setComentCabec(comentCabec);
        cabecBean.setDthrCabec(Tempo.getInstance().dataComHora());
        cabecBean.setStatusCabec(1L);
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

    private EspecificaPesquisa getPesqTipoCompleto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("tipoCabec");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
