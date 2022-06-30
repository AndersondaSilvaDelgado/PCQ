package br.com.usinasantafe.pcq.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class LogErroActivity extends ActivityGeneric {

    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_erro);

        pcqContext = (PCQContext) getApplication();

        Button buttonRetLogErro = findViewById(R.id.buttonRetLogErro);

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaHistorico = findViewById(R.id.listaHistorico);\n" +
                "        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pcqContext.getConfigCTR().logErroList());\n" +
                "        listaHistorico.setAdapter(adapterListHistorico);", getLocalClassName());
        ListView listViewLogErro = findViewById(R.id.listViewLogErro);
        AdapterListErro adapterListErro = new AdapterListErro(this, pcqContext.getConfigCTR().logErroList());
        listViewLogErro.setAdapter(adapterListErro);

        buttonRetLogErro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetLogProcesso.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "Intent it = new Intent(LogErroActivity.this, LogBaseDadoActivity.class);", getLocalClassName());
                Intent it = new Intent(LogErroActivity.this, LogBaseDadoActivity.class);
                startActivity(it);
                finish();
            }

        });


    }

    public void onBackPressed() {
    }

}