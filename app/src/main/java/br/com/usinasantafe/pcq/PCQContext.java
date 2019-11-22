package br.com.usinasantafe.pcq;

import android.app.Application;

import br.com.usinasantafe.pcq.control.FormularioCTR;

public class PCQContext extends Application {

    public static String versaoAplic = "1.00";
    private FormularioCTR formularioCTR;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public FormularioCTR getFormularioCTR() {
        if (formularioCTR == null)
            formularioCTR = new FormularioCTR();
        return formularioCTR;
    }

}
