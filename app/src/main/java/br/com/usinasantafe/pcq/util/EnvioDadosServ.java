package br.com.usinasantafe.pcq.util;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pcq.control.ConfigCTR;
import br.com.usinasantafe.pcq.model.bean.variaveis.DadosEnvioBean;
import br.com.usinasantafe.pcq.control.FormularioCTR;
import br.com.usinasantafe.pcq.util.connHttp.MultipartGenerico;
import br.com.usinasantafe.pcq.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.pcq.util.connHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

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

    public void envioDados() {

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] dados = new String[8];

        FormularioCTR formularioCTR = new FormularioCTR();

        DadosEnvioBean dadosEnvioBean = formularioCTR.dadosCabecFechEnvio();

        Log.i("PST", "CABEC = " + dadosEnvioBean.getCabec());
        Log.i("PST", "ITEM = " + dadosEnvioBean.getItem());
        Log.i("PST", "BRIGADISTA = " + dadosEnvioBean.getBrigadista());
        Log.i("PST", "EQUIP = " + dadosEnvioBean.getEquip());
        Log.i("PST", "FOTO = " + dadosEnvioBean.getFoto());
        Log.i("PST", "ORGAOAMB = " + dadosEnvioBean.getOrgaoAmb());
        Log.i("PST", "TALHAO = " + dadosEnvioBean.getTalhao());

        dados[0] = urlsConexaoHttp.getsInserirDados();
        dados[1] = dadosEnvioBean.getCabec();
        dados[2] = dadosEnvioBean.getItem();
        dados[3] = dadosEnvioBean.getBrigadista();
        dados[4] = dadosEnvioBean.getEquip();
        dados[5] = dadosEnvioBean.getFoto();
        dados[6] = dadosEnvioBean.getOrgaoAmb();
        dados[7] = dadosEnvioBean.getTalhao();

        MultipartGenerico multipartGenerico = new MultipartGenerico();
        multipartGenerico.execute(dados);

    }

    public void envioLogErro() {

        ConfigCTR configCTR = new ConfigCTR();
        String dados = configCTR.dadosEnvioLogErro();

        Log.i("PMM", "LOG ERRO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertLogErro()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(url);

    }

    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public Boolean verifLogErro() {
        ConfigCTR configCTR = new ConfigCTR();
        return configCTR.verEnvioLogErro();
    }

    public Boolean verifEnvioDados() {
        FormularioCTR formularioCTR = new FormularioCTR();
        return formularioCTR.verEnvioDados();
    }

    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

    public void envioDados(Context context) {
        enviando = true;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            envioDadosPrinc();
        }
        else{
            enviando = false;
        }

    }

    public void envioDadosPrinc(){

        if(verifLogErro()){
            envioLogErro();
        }
        else{
            if(verifEnvioDados()){
                envioDados();
            }
        }

    }

    public boolean verifDadosEnvio() {
        if (!verifEnvioDados()
            && !verifLogErro()){
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

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }

}