package br.com.usinasantafe.pcq;

import android.app.Application;

import br.com.usinasantafe.pcq.control.ConfigCTR;
import br.com.usinasantafe.pcq.control.FormularioCTR;

public class PCQContext extends Application {

    public static String versaoAplic = "1.00";
    private FormularioCTR formularioCTR;
    private ConfigCTR configCTR;
    private int posCameraTela;
    // 1 - Camera Queima de Cana
    // 2 - Camera Queima de Vegetação Nativa
    private int tipoTela;
    // 1 - Fluxo Normal
    // 2 - Relação
    // 3 - Formulário para Reajuste
    // 4 - Relação de Formulário para Reajuste

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

    public int getPosCameraTela() {
        return posCameraTela;
    }

    public void setPosCameraTela(int posCameraTela) {
        this.posCameraTela = posCameraTela;
    }

    public int getTipoTela() {
        return tipoTela;
    }

    public void setTipoTela(int tipoTela) {
        this.tipoTela = tipoTela;
    }
}
