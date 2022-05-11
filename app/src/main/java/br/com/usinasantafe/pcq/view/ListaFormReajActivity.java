package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.util.ConexaoWeb;

public class ListaFormReajActivity extends ActivityGeneric {

    private PCQContext pcqContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_form_reaj);

        pcqContext = (PCQContext) getApplication();
        Button buttonAtualizarFormReaj = (Button) findViewById(R.id.buttonAtualizarFormReaj);
        Button buttonRetornarFormReaj = (Button) findViewById(R.id.buttonRetornarFormReaj);

        ListView listaViewFormReaj = (ListView) findViewById(R.id.listaViewFormReaj);
        AdapterListFormReaj adapterListFormReaj = new AdapterListFormReaj(this, pcqContext.getFormularioCTR().cabecRecebidoList());
        listaViewFormReaj.setAdapter(adapterListFormReaj);

        listaViewFormReaj.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pcqContext.getFormularioCTR().setPosCabecReaj(position);
                Intent it = new Intent(ListaFormReajActivity.this, RelacaoCabecReajActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonAtualizarFormReaj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaFormReajActivity.this)) {

                    progressBar = new ProgressDialog(ListaFormReajActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pcqContext.getFormularioCTR().receberCabecReaj(ListaFormReajActivity.this, ListaFormReajActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaFormReajActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alerta.show();

                }

            }
        });

        buttonRetornarFormReaj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaFormReajActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            }

        });


    }
}