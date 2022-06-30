package br.com.usinasantafe.pcq.retrofit;

import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pcq.control.FormularioCTR;
import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.model.dao.LogErroDAO;
import br.com.usinasantafe.pcq.util.EnvioDadosServ;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFormCompleto {

    public PostFormCompleto() {
    }

    public void envioFormCompleto(List<CabecBean> cabecList, String activity){

        try {
            Log.i("PCQ", "ENVIANDO");
            FormCompletoDao formCompletoDao = ConnRetrofit.getInstance().conn().create(FormCompletoDao.class);
            Call<List<CabecBean>> call = formCompletoDao.envioFormCompleto(cabecList);
            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<List<CabecBean>> call, Response<List<CabecBean>> response) {
                    Log.i("PCQ", "RECEBENDO DADOS 1");
                    FormularioCTR formularioCTR = new FormularioCTR();
                    formularioCTR.receberCabecEnviado(response.body(), activity);
                }

                @Override
                public void onFailure(Call<List<CabecBean>> call, Throwable t) {
                    Log.i("PCQ", "ERRO1 = " + t);
                    LogErroDAO.getInstance().insertLogErro(t);
                    EnvioDadosServ.status = 1;
                }
            });

        } catch (Exception e) {
            Log.i("PCQ", "ERRO2 = " + e);
            LogErroDAO.getInstance().insertLogErro(e);
            EnvioDadosServ.status = 1;
        }

    }

}
