package br.com.usinasantafe.pcq.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pcq.control.ConfigCTR;
import br.com.usinasantafe.pcq.model.dao.CabecDAO;
import br.com.usinasantafe.pcq.model.dao.ConfigDAO;
import br.com.usinasantafe.pcq.util.connHttp.PostVerGenerico;
import br.com.usinasantafe.pcq.util.connHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pcq.view.MenuInicialActivity;
import br.com.usinasantafe.pcq.model.bean.AtualAplicBean;
import br.com.usinasantafe.pcq.view.TelaInicialActivity;

/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private ProgressDialog progressDialog;
    private Context telaAtual;
    private Class telaProx;
    private String tipo;
    private TelaInicialActivity telaInicialActivity;
    private PostVerGenerico postVerGenerico;
    public static int status;

    public VerifDadosServ() {
    }

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result) {

        if (!result.equals("")) {
            if (this.tipo.equals("Atualiza")) {
                status = 3;
                ConfigDAO configDAO = new ConfigDAO();
                AtualAplicBean atualAplicBean = configDAO.recAtual(result.trim());
                if (atualAplicBean.getFlagAtualApp().equals(1L)) {
                    AtualizarAplicativo atualizarAplicativo = new AtualizarAplicativo();
                    atualizarAplicativo.setContext(this.telaInicialActivity);
                    atualizarAplicativo.execute();
                } else {
                    this.telaInicialActivity.goMenuInicial();
                }
            }
            else if(this.tipo.equals("Cabec")){
                CabecDAO cabecDAO = new CabecDAO();
                cabecDAO.receberDadosCabec(result);
            }
        }

    }

    public void verAtualAplic(String versaoAplic, TelaInicialActivity telaInicialActivity, String activity) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.tipo = "Atualiza";
        this.telaInicialActivity = telaInicialActivity;

        AtualAplicBean atualAplicBean = new AtualAplicBean();
        ConfigCTR configCTR = new ConfigCTR();
        atualAplicBean.setNroAparelhoAtual(configCTR.getConfig().getNroAparelhoConfig());
        atualAplicBean.setVersaoAtual(versaoAplic);

        JsonArray jsonArray = new JsonArray();

        Gson gson = new Gson();
        jsonArray.add(gson.toJsonTree(atualAplicBean, atualAplicBean.getClass()));

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        Log.i("PMM", "LISTA = " + json.toString());

        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", json.toString());

        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

    public void verDados(String tipo, Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.tipo = tipo;

        envioDados();

    }

    public void envioDados() {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", "");

        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

    public void cancel() {
        status = 3;
        if (postVerGenerico.getStatus() == AsyncTask.Status.RUNNING) {
            postVerGenerico.cancel(true);
        }
    }

    public void pulaTelaSemTerm(){
        this.progressDialog.dismiss();
        Intent it = new Intent(telaAtual, telaProx);
        telaAtual.startActivity(it);
    }

    public void msgSemTerm(String texto){
        this.progressDialog.dismiss();
        AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
        alerta.setTitle("ATENÇÃO");
        alerta.setMessage(texto);
        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alerta.show();
    }

}
