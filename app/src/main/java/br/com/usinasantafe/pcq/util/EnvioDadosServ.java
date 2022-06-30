package br.com.usinasantafe.pcq.util;

import br.com.usinasantafe.pcq.control.FormularioCTR;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pcq.retrofit.PostFormCompleto;
import br.com.usinasantafe.pcq.util.connHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    public static int status; //1 - Existe Dados para Enviar; 2 - Enviado; 3 - Todos os Dados Foram Enviados;

    public EnvioDadosServ() {
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void envioFormCompleto(String activity) {
        FormularioCTR formularioCTR = new FormularioCTR();
        PostFormCompleto postFormCompleto = new PostFormCompleto();
        LogProcessoDAO.getInstance().insertLogProcesso("postFormCompleto.envioFormCompleto(formularioCTR.dadosCabecFinalizadoCompleto());", activity);
        postFormCompleto.envioFormCompleto(formularioCTR.dadosCabecFinalizadoCompleto(), activity);
    }

    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public Boolean verEnvioFormCompleto() {
        FormularioCTR formularioCTR = new FormularioCTR();
        return formularioCTR.verCabecEnvio();
    }

    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

    public void envioDados(String activity) {
        LogProcessoDAO.getInstance().insertLogProcesso("public void envioDados(String activity) {\n" +
                "        status = 1;", activity);
        status = 1;
        if(verEnvioFormCompleto()){
            LogProcessoDAO.getInstance().insertLogProcesso("if(verEnvioFormCompleto()){\n" +
                    "            status = 2;\n" +
                    "            envioFormCompleto(activity);", activity);
            status = 2;
            envioFormCompleto(activity);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            status = 3;", activity);
            status = 3;
        }
    }

    public boolean verifDadosEnvio() {
        if (!verEnvioFormCompleto()){
            return false;
        } else {
            return true;
        }
    }

}