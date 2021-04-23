package br.com.usinasantafe.pcq.util.connHttp;


import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

import br.com.usinasantafe.pcq.control.FormularioCTR;
import br.com.usinasantafe.pcq.util.EnvioDadosServ;

public class MultipartGenerico extends AsyncTask<String, Void, String>   {

    private static MultipartGenerico instance = null;

    public MultipartGenerico() {
    }

    public static MultipartGenerico getInstance() {
        if (instance == null)
            instance = new MultipartGenerico();
        return instance;
    }

	@Override
	protected String doInBackground(String... params) {

        String answer = "";

        String url = params[0];
        String cabec = params[1];
        String item = params[2];
        String brigadista = params[3];
		String equip = params[4];
		String foto = params[5];
		String orgaoamb = params[6];
		String talhao = params[7];

		try{

			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
            valores.add(new BasicNameValuePair("cabec", cabec));
			valores.add(new BasicNameValuePair("item", item));
			valores.add(new BasicNameValuePair("brigadista", brigadista));
            valores.add(new BasicNameValuePair("equip", equip));
			valores.add(new BasicNameValuePair("foto", foto));
			valores.add(new BasicNameValuePair("orgaoamb", orgaoamb));
			valores.add(new BasicNameValuePair("talhao", talhao));

            httpPost.setEntity(new UrlEncodedFormEntity(valores));
            HttpResponse resposta = httpClient.execute(httpPost);
            answer = EntityUtils.toString(resposta.getEntity());

		} catch (Exception e) {
			EnvioDadosServ.getInstance().setEnviando(false);
		}
		
		return answer;
	}

	protected void onPostExecute(String result) {

		try {

			Log.i("ECM", "VALOR RECEBIDO --> " + result);
			if(result.trim().equals("GRAVOU")){
				FormularioCTR formularioCTR = new FormularioCTR();
				formularioCTR.delForm();
			}
			else{
				EnvioDadosServ.getInstance().setEnviando(false);
			}

		} catch (Exception e) {
			EnvioDadosServ.getInstance().setEnviando(false);
			Log.i("PMM", "Erro = " + e);
		}

	}
	
}
