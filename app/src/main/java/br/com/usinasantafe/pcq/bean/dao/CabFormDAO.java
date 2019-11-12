package br.com.usinasantafe.pcq.bean.dao;

import java.util.List;

import br.com.usinasantafe.pcq.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class CabFormDAO {

    public CabFormDAO() {
    }

    public void salvarCabecAbert(CabecBean cabecBean){
        cabecBean.setStatusCabec(1L);
        cabecBean.setDthrCabec(Tempo.getInstance().dataComHora());
        cabecBean.insert();
    }

    public List cabecFechList() {
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get("statusCabAbord", 2L);
    }

    public List cabecAbertList() {
        CabecBean cabecBean = new CabecBean();
        return cabecBean.get("statusCabAbord", 1L);
    }

    public CabecBean getCabecAbert(){
        CabecBean cabecBean = (CabecBean) cabecAbertList().get(0);
        return cabecBean;
    }

    public void salvarCabecFech(CabecBean cabecBean){
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

}
