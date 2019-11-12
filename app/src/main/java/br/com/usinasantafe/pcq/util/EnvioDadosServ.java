package br.com.usinasantafe.pcq.util;

import android.content.Context;

import br.com.usinasantafe.pcq.control.FormularioCTR;

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

    public void dadosEnvio() {

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();
        FormularioCTR formularioCTR = new FormularioCTR();

        String[] dados = new String[7];

//        String cabec = formularioCTR.dadosCabecFechEnvio();
//        String item = formularioCTR.dadosItemFechEnvio();

//        Log.i("PST", "CABECALHO = " + cabec);
//        Log.i("PST", "ITEM = " + item);
//
//        dados[0] = urlsConexaoHttp.getsInserirDados();
//        dados[1] = cabec;
//        dados[2] = item;
//        dados[3] = formularioCTR.dadosFotoFechEnvio(1);
//        dados[4] = formularioCTR.dadosFotoFechEnvio(2);
//        dados[5] = formularioCTR.dadosFotoFechEnvio(3);
//        dados[6] = formularioCTR.dadosFotoFechEnvio(4);
//
//        ConHttpMultipartGenerico conHttpMultipartGenerico = new ConHttpMultipartGenerico();
//        conHttpMultipartGenerico.execute(dados);

    }

    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

//    public Boolean verifEnvioDados() {
//        FormularioCTR formularioCTR = new FormularioCTR();
//        return formularioCTR.verEnvioDados();
//    }

    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

    public void envioDados(Context context) {
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            enviando = true;
            envioDados();
        }
        else{
            enviando = false;
        }

    }

    public void envioDados() {
//        if(verifEnvioDados()){
//            enviando = true;
//            dadosEnvio();
//        }
//        else{
//            enviando = false;
//        }
    }

    public boolean verifDadosEnvio() {
//        if (!verifEnvioDados()){
//            enviando = false;
//            return false;
//        } else {
            return true;
//        }
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