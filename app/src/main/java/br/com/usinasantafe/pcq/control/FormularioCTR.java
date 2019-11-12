package br.com.usinasantafe.pcq.control;

import br.com.usinasantafe.pcq.bean.variaveis.CabecBean;

public class FormularioCTR {

    private CabecBean cabecBean;

    public FormularioCTR() {
        if (cabecBean == null)
            cabecBean = new CabecBean();
    }

    public CabecBean getCabecBean() {
        return cabecBean;
    }

    public void setCabecBean(CabecBean cabecBean) {
        this.cabecBean = cabecBean;
    }
}
