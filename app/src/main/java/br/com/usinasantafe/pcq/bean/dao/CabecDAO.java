package br.com.usinasantafe.pcq.bean.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class CabecDAO {

    public CabecDAO() {
    }

    //////////////////////////////Cabecalho Iniciado///////////////////////////////////////////////

    public void salvarCabecIniciado(CabecBean cabecBean){
        cabecBean.setHaIncCanaCabec(0D);
        cabecBean.setHaIncAreaComumCabec(0D);
        cabecBean.setHaIncAppCabec(0D);
        cabecBean.setHaIncResLegalCabec(0D);
        cabecBean.setHaIncPalhadaCabec(0D);
        cabecBean.setQtdeBrigadistaCabec(0L);
        cabecBean.setEmpresaTercCabec("null");
        cabecBean.setComentCabec("null");
        cabecBean.setStatusCabec(0L);
        cabecBean.insert();
    }

    public CabecBean getCabecInic(){
        CabecBean cabecBean = new CabecBean();
        List cabecList = cabecBean.get("statusCabec", 0L);
        cabecBean = (CabecBean) cabecList.get(0);
        cabecList.clear();
        return cabecBean;
    }

    public void delCabecInic(){
        CabecBean cabecBean = getCabecInic();
        cabecBean.delete();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////set Cabecalho////////////////////////////////////////////////

    public void setSecaoCabec(Long secaoCabec){
        CabecBean cabecBean = getCabecInic();
        cabecBean.setSecaoCabec(secaoCabec);
        cabecBean.update();
    }

    public void setHaIncCanaCabec(Double haIncCanaCabec){
        CabecBean cabecBean = getCabecInic();
        cabecBean.setHaIncCanaCabec(haIncCanaCabec);
        cabecBean.update();
    }

    public void setHaIncPalhadaCabec(Double haIncPalhadaCabec) {
        CabecBean cabecBean = getCabecInic();
        cabecBean.setHaIncPalhadaCabec(haIncPalhadaCabec);
        cabecBean.update();
    }

    public void setHaIncResLegalCabec(Double haIncResLegalCabec) {
        CabecBean cabecBean = getCabecInic();
        cabecBean.setHaIncResLegalCabec(haIncResLegalCabec);
        cabecBean.update();
    }

    public void setHaIncAppCabec(Double haIncAppCabec){
        CabecBean cabecBean = getCabecInic();
        cabecBean.setHaIncAppCabec(haIncAppCabec);
        cabecBean.update();
    }

    public void setHaIncAreaComumCabec(Double haIncAreaComumCabec) {
        CabecBean cabecBean = getCabecInic();
        cabecBean.setHaIncAreaComumCabec(haIncAreaComumCabec);
        cabecBean.update();
    }

    public void setQtdeBrigadistaCabec(Long qtdeBrigadistaCabec) {
        CabecBean cabecBean = getCabecInic();
        cabecBean.setQtdeBrigadistaCabec(qtdeBrigadistaCabec);
        cabecBean.update();
    }

    public void setEmpresaTercCabec(String empresaTercCabec) {
        CabecBean cabecBean = getCabecInic();
        cabecBean.setEmpresaTercCabec(empresaTercCabec);
        cabecBean.update();
    }

    public void setComentCabec(String comentCabec){
        CabecBean cabecBean = getCabecInic();
        cabecBean.setComentCabec(comentCabec);
        cabecBean.setDthrCabec(Tempo.getInstance().dataComHora());
        cabecBean.setStatusCabec(1L);
        cabecBean.update();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public CabecBean getCabecAbert(){
        CabecBean cabecBean = new CabecBean();
        List cabecList = cabecBean.get("statusCabec", 1L);
        cabecBean = (CabecBean) cabecList.get(0);
        cabecList.clear();
        return cabecBean;
    }

    public void fecharCabec(CabecBean cabecBean){
        cabecBean.setStatusCabec(2L);
        cabecBean.update();
    }

    public void delCabec(Long idCabAbord) {
        CabecBean cabecBean = new CabecBean();
        List cabecList = cabecBean.get("idCabAbord", idCabAbord);
        cabecBean = (CabecBean) cabecList.get(0);
        cabecBean.delete();
        cabecList.clear();
    }


    public List cabecFechList() {
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get("statusCabec", 2L);
    }


}
