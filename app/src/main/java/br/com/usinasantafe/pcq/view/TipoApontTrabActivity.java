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
import br.com.usinasantafe.pcq.model.bean.estaticas.TipoApontBean;
import br.com.usinasantafe.pcq.util.ConexaoWeb;

public class TipoApontTrabActivity extends ActivityGeneric {

    private List<TipoApontBean> tipoApontList;
    private PCQContext pcqContext;
    private RadioGroup radioGroupItemTipoApont;
    private int posicao;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_apont_trab);

        pcqContext = (PCQContext) getApplication();

        Button buttonAtualizarBD = (Button) findViewById(R.id.buttonAtualizarBD);
        Button buttonRetTipoApont = (Button) findViewById(R.id.buttonRetTipoApont);
        Button buttonAvancaTipoApont = (Button) findViewById(R.id.buttonAvancaTipoApont);
        radioGroupItemTipoApont = (RadioGroup) findViewById(R.id.radioGroupItemTipoApont);

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

        tipoApontList = pcqContext.getFormularioCTR().tipoApontList();

        for (TipoApontBean tipoApontBean : tipoApontList) {
            RadioButton radioButtonItem = new RadioButton(getApplicationContext());
            radioButtonItem.setText(tipoApontBean.getDescrTipoApont());
            radioButtonItem.setTextColor(Color.BLACK);
            radioButtonItem.setTextSize(22F);
            radioButtonItem.setButtonTintList(colorStateList);
            radioGroupItemTipoApont.addView(radioButtonItem);
        }

        radioGroupItemTipoApont.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(TipoApontTrabActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(TipoApontTrabActivity.this)) {

                            progressBar = new ProgressDialog(TipoApontTrabActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pcqContext.getFormularioCTR().atualDadosTipoApont(TipoApontTrabActivity.this, TipoApontTrabActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(TipoApontTrabActivity.this);
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

        buttonRetTipoApont.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pcqContext.getTipoTela() == 1){
                    Intent it = new Intent(TipoApontTrabActivity.this, ColabActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(TipoApontTrabActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonAvancaTipoApont.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(posicao > -1) {

                    TipoApontBean tipoApontBean = tipoApontList.get(posicao);
                    pcqContext.getFormularioCTR().setTipoApontTrabCabec(tipoApontBean.getIdTipoApont(), pcqContext.getTipoTela());

                    if(pcqContext.getTipoTela() == 1) {
                        Intent it = new Intent(TipoApontTrabActivity.this, OrigemFogoActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Intent it = new Intent(TipoApontTrabActivity.this, RelacaoCabecActivity.class);
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