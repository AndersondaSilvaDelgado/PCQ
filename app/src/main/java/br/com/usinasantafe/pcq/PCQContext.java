package br.com.usinasantafe.pcq;

import android.app.Application;

import br.com.usinasantafe.pcq.control.ConfigCTR;
import br.com.usinasantafe.pcq.control.FormularioCTR;

public class PCQContext extends Application {

    public static String versaoAplic = "1.00";
    private FormularioCTR formularioCTR;
    private ConfigCTR configCTR;
    public int tipoForm; //1 - Completo; 2 - Simples
    public int posTela;
    // 1 - Camera Queima de Cana
    // 1 - Camera Queima de Vegetação Nativa

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public FormularioCTR getFormularioCTR() {
        if (formularioCTR == null)
            formularioCTR = new FormularioCTR();
        return formularioCTR;
    }

    public ConfigCTR getConfigCTR() {
        if (configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

    public int getTipoForm() {
        return tipoForm;
    }

    public void setTipoForm(int tipoForm) {
        this.tipoForm = tipoForm;
    }

    public int getPosTela() {
        return posTela;
    }

    public void setPosTela(int posTela) {
        this.posTela = posTela;
    }
}
