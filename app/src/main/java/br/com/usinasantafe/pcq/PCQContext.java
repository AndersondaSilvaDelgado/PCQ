package br.com.usinasantafe.pcq;

import android.app.Application;

import br.com.usinasantafe.pcq.control.FormularioCTR;

public class PCQContext extends Application {

    public static String versaoAplic = "1.00";
    private FormularioCTR formularioCTR;
    private int posMsg;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public FormularioCTR getFormularioCTR() {
        if (formularioCTR == null)
            formularioCTR = new FormularioCTR();
        return formularioCTR;
    }

    public int getPosMsg() {
        return posMsg;
    }

    public void setPosMsg(int posMsg) {
        this.posMsg = posMsg;
    }
}
