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
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class ListaFormRecebidoActivity extends ActivityGeneric {

    private PCQContext pcqContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_form_recebido);

        pcqContext = (PCQContext) getApplication();
        Button buttonAtualizarFormRecebido = findViewById(R.id.buttonAtualizarFormRecebido);
        Button buttonRetornarFormRecebido = findViewById(R.id.buttonRetornarFormRecebido);

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaViewFormRecebido = findViewById(R.id.listaViewFormRecebido);\n" +
                "        AdapterListFormRecebido adapterListFormFormRecebido = new AdapterListFormRecebido(this, pcqContext.getFormularioCTR().cabecRecebidoList());\n" +
                "        listaViewFormRecebido.setAdapter(adapterListFormFormRecebido);", getLocalClassName());
        ListView listaViewFormRecebido = findViewById(R.id.listaViewFormRecebido);
        AdapterListFormRecebido adapterListFormFormRecebido = new AdapterListFormRecebido(this, pcqContext.getFormularioCTR().cabecRecebidoList());
        listaViewFormRecebido.setAdapter(adapterListFormFormRecebido);
        listaViewFormRecebido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("listaViewFormRecebido.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                pcqContext.getFormularioCTR().setCabecRecebidoParaCabecFinalizado(position);\n" +
                        "                Intent it = new Intent(ListaFormRecebidoActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                pcqContext.getFormularioCTR().setCabecRecebidoParaCabecFinalizado(position);
                Intent it = new Intent(ListaFormRecebidoActivity.this, RelacaoCabecActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonAtualizarFormRecebido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualizarFormRecebido.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(ListaFormRecebidoActivity.this);\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                    progressBar.setProgress(0);\n" +
                            "                    progressBar.setMax(100);\n" +
                            "                    progressBar.show();\n" +
                            "                    pcqContext.getFormularioCTR().receberCabec(ListaFormRecebidoActivity.this, ListaFormRecebidoActivity.class, progressBar);", getLocalClassName());
                    progressBar = new ProgressDialog(ListaFormRecebidoActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pcqContext.getFormularioCTR().receberCabec(ListaFormRecebidoActivity.this, ListaFormRecebidoActivity.class, progressBar);

                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaFormRecebidoActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaFormRecebidoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                        }
                    });

                    alerta.show();

                }

            }
        });

        buttonRetornarFormRecebido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetornarFormRecebido.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                Intent it = new Intent(ListaFormRecebidoActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            }

        });


    }
}