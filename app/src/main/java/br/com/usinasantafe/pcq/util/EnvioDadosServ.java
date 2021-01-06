package br.com.usinasantafe.pcq.util;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.pcq.model.bean.variaveis.DadosEnvioBean;
import br.com.usinasantafe.pcq.control.FormularioCTR;
import br.com.usinasantafe.pcq.util.connHttp.MultipartGenerico;
import br.com.usinasantafe.pcq.util.connHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void envioDados() {

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] dados = new String[6];

        FormularioCTR formularioCTR = new FormularioCTR();

        DadosEnvioBean dadosEnvioBean = formularioCTR.dadosCabecFechEnvio();

        Log.i("PST", "CABEC = " + dadosEnvioBean.getCabec());
        Log.i("PST", "ITEM = " + dadosEnvioBean.getItem());
        Log.i("PST", "EQUIP = " + dadosEnvioBean.getEquip());
        Log.i("PST", "ORGAOAMB = " + dadosEnvioBean.getOrgaoAmb());
        Log.i("PST", "TALHAO = " + dadosEnvioBean.getTalhao());

        dados[0] = urlsConexaoHttp.getsInserirDados();
        dados[1] = dadosEnvioBean.getCabec();
        dados[2] = dadosEnvioBean.getItem();
        dados[3] = dadosEnvioBean.getEquip();
        dados[4] = dadosEnvioBean.getOrgaoAmb();
        dados[5] = dadosEnvioBean.getTalhao();

        MultipartGenerico multipartGenerico = new MultipartGenerico();
        multipartGenerico.execute(dados);

    }

    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public Boolean verifEnvioDados() {
        FormularioCTR formularioCTR = new FormularioCTR();
        return formularioCTR.verEnvioDados();
    }

    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

    public void envioDados(Context context) {
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context) && verifEnvioDados()) {
            enviando = true;
            envioDados();
        }
        else{
            enviando = false;
        }

    }

    public boolean verifDadosEnvio() {
        if (!verifEnvioDados()){
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    public boolean isEnviando() {
        return enviando;
    }

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }
}