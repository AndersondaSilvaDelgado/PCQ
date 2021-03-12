package br.com.usinasantafe.pcq.model.dao;

import java.util.List;

import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class CabecDAO {

    public CabecDAO() {
    }

    public void salvarCabecIniciado(CabecBean cabecBean){
        cabecBean.setMatricColabCabec(0L);
        cabecBean.setHaIncCanaCabec(0D);
        cabecBean.setHaIncAreaComumCabec(0D);
        cabecBean.setHaIncAppCabec(0D);
        cabecBean.setHaIncResLegalCabec(0D);
        cabecBean.setHaIncPalhadaCabec(0D);
        cabecBean.setQtdeBrigadistaCabec(0L);
        cabecBean.setEmpresaTercCabec("null");
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
        List<CabecBean> cabecList = getCabecAbertoList();
        CabecBean cabecBean = cabecList.get(0);
        cabecList.clear();
        return cabecBean;
    }

    public CabecBean getCabecAbert(){
        List<CabecBean> cabecList = getCabecAbertoList();
        CabecBean cabecBean = cabecList.get(0);
        cabecList.clear();
        return cabecBean;
    }

    private List<CabecBean> getCabecIniciadoList() {
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get("statusCabec", 0L);
    }

    private List<CabecBean> getCabecAbertoList() {
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get("statusCabec", 1L);
    }

    public List<CabecBean> cabecFechList() {
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get("statusCabec", 2L);
    }

    public void setMatricColabCabec(Long matricColabCabec){
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setMatricColabCabec(matricColabCabec);
        cabecBean.update();
    }

    public void setSecaoCabec(Long secaoCabec){
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setSecaoCabec(secaoCabec);
        cabecBean.update();
    }

    public void setHaIncCanaCabec(Double haIncCanaCabec){
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setHaIncCanaCabec(haIncCanaCabec);
        cabecBean.update();
    }

    public void setHaIncPalhadaCabec(Double haIncPalhadaCabec) {
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setHaIncPalhadaCabec(haIncPalhadaCabec);
        cabecBean.update();
    }

    public void setHaIncResLegalCabec(Double haIncResLegalCabec) {
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setHaIncResLegalCabec(haIncResLegalCabec);
        cabecBean.update();
    }

    public void setHaIncAppCabec(Double haIncAppCabec){
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setHaIncAppCabec(haIncAppCabec);
        cabecBean.update();
    }

    public void setHaIncAreaComumCabec(Double haIncAreaComumCabec) {
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setHaIncAreaComumCabec(haIncAreaComumCabec);
        cabecBean.update();
    }

    public void setQtdeBrigadistaCabec(Long qtdeBrigadistaCabec) {
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setQtdeBrigadistaCabec(qtdeBrigadistaCabec);
        cabecBean.update();
    }

    public void setEmpresaTercCabec(String empresaTercCabec) {
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setEmpresaTercCabec(empresaTercCabec);
        cabecBean.update();
    }

    public void setOrigemFogoCabec(Long origemFogoCabec) {
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setOrigemFogoCabec(origemFogoCabec);;
        cabecBean.update();
    }

    public void setComentCabec(String comentCabec){
        CabecBean cabecBean = getCabecIniciado();
        cabecBean.setComentCabec(comentCabec);
        cabecBean.setDthrCabec(Tempo.getInstance().dataComHora());
        cabecBean.setStatusCabec(1L);
        cabecBean.update();
    }

}
