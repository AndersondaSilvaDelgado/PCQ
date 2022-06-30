package br.com.usinasantafe.pcq.retrofit;

import java.util.List;

import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FormCompletoDao {

    @POST("inserirformulario.php")
    Call<List<CabecBean>> envioFormCompleto(@Body List<CabecBean> cabecBeanList);

}
