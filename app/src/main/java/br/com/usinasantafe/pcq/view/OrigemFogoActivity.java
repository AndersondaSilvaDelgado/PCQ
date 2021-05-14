package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.bean.estaticas.OrigemFogoBean;
import br.com.usinasantafe.pcq.util.ConexaoWeb;

public class OrigemFogoActivity extends ActivityGeneric {

    private List<OrigemFogoBean> origemFogoList;
    private PCQContext pcqContext;
    private RadioGroup radioGroupItemOrigemFogo;
    private int posicao;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origem_fogo);

        pcqContext = (PCQContext) getApplication();

        Button buttonAtualizarBD = (Button) findViewById(R.id.buttonAtualizarBD);
        Button buttonRetOrigemFogo = (Button) findViewById(R.id.buttonRetOrigemFogo);
        Button buttonAvancaOrigemFogo = (Button) findViewById(R.id.buttonAvancaOrigemFogo);
        radioGroupItemOrigemFogo = (RadioGroup) findViewById(R.id.radioGroupItemOrigemFogo);

        posicao = -1;

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_enabled}
                },
                new int[] {
                        Color.BLACK
                        ,Color.BLUE
                }
        );

        origemFogoList = pcqContext.getFormularioCTR().origemFogoList();

        for (OrigemFogoBean origemFogoBean : origemFogoList) {
            RadioButton radioButtonItem = new RadioButton(getApplicationContext());
            radioButtonItem.setText(origemFogoBean.getDescrOrigemFogo());
            radioButtonItem.setTextColor(Color.BLACK);
            radioButtonItem.setTextSize(22F);
            radioButtonItem.setButtonTintList(colorStateList);
            radioGroupItemOrigemFogo.addView(radioButtonItem);
        }

        radioGroupItemOrigemFogo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(OrigemFogoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(OrigemFogoActivity.this)) {

                            progressBar = new ProgressDialog(OrigemFogoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pcqContext.getFormularioCTR().atualDadosOrigemFogo(OrigemFogoActivity.this, OrigemFogoActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(OrigemFogoActivity.this);
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

        buttonRetOrigemFogo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pcqContext.getTipoTela() == 1) {
                    Intent it = new Intent(OrigemFogoActivity.this, TipoApontTrabActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(OrigemFogoActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonAvancaOrigemFogo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(posicao > -1) {

                    OrigemFogoBean origemFogoBean = origemFogoList.get(posicao);
                    pcqContext.getFormularioCTR().setOrigemFogoCabec(origemFogoBean.getIdOrigemFogo(), pcqContext.getTipoTela());

                    if(pcqContext.getTipoTela() == 1) {
                        Intent it = new Intent(OrigemFogoActivity.this, SecaoActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Intent it = new Intent(OrigemFogoActivity.this, RelacaoCabecActivity.class);
                        startActivity(it);
                        finish();
                    }

                }
            }
        });

    }

    public void onBackPressed() {
    }

}