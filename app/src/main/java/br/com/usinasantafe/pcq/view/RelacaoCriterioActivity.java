package br.com.usinasantafe.pcq.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;

public class RelacaoCriterioActivity extends ActivityGeneric {

    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacao_criterio);

        pcqContext = (PCQContext) getApplication();
        Button buttonAvancarCriteiro = (Button) findViewById(R.id.buttonAvancarCriteiro);
        Button buttonRetornarCriteiro = (Button) findViewById(R.id.buttonRetornarCriteiro);

        pcqContext = (PCQContext) getApplication();

        ListView listaViewInforCriteiro = (ListView) findViewById(R.id.listaViewInforCriteiro);
        AdapterListCriterio adapterListCriterio = new AdapterListCriterio(this, pcqContext.getFormularioCTR().respItemList());
        listaViewInforCriteiro.setAdapter(adapterListCriterio);

        buttonAvancarCriteiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        buttonRetornarCriteiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(RelacaoCriterioActivity.this, RelacaoCabecalhoActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}