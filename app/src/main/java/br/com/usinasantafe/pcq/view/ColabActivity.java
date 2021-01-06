package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pcq.util.ConexaoWeb;

public class ColabActivity extends ActivityGeneric {

    private PCQContext pcqContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colab);

        pcqContext = (PCQContext) getApplication();

        Button buttonOkOperador = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOperador = (Button) findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder( ColabActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ColabActivity.this)) {

                            progressBar = new ProgressDialog(ColabActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

//                            pstContext.getAbordagemCTR().atualDadosColab(ObservadorDigActivity.this, ObservadorDigActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ColabActivity.this);
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

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }

        });

        buttonOkOperador.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    ColabBean colabBean = new ColabBean();
                    List colabList = colabBean.get("matricColab", Long.parseLong(editTextPadrao.getText().toString()));

                    if (colabList.size() > 0) {

                        colabBean = (ColabBean) colabList.get(0);
                        pcqContext.getFormularioCTR().salvarCabecIniciado(colabBean.getMatricColab());
                        colabList.clear();

                        Intent it = new Intent(ColabActivity.this, SecaoActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ColabActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NUMERAÇÃO DO FUNCIONARIO INEXISTENTE! FAVOR VERIFICA A MESMA.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    }
                }

            }

        });

        buttonCancOperador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });


    }

    public void onBackPressed() {
        Intent it = new Intent(ColabActivity.this, MenuInicialActivity.class);
        startActivity(it);
        finish();
    }

}
