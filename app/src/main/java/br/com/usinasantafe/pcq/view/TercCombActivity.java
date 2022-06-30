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
import br.com.usinasantafe.pcq.model.bean.estaticas.TercCombBean;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class TercCombActivity extends ActivityGeneric {

    private List<TercCombBean> tercCombList;
    private PCQContext pcqContext;
    private RadioGroup radioGroupItemTercComb;
    private int posicao;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terc_comb);

        pcqContext = (PCQContext) getApplication();

        Button buttonRetTercComb = findViewById(R.id.buttonRetTercComb);
        Button buttonAvancaTercComb = findViewById(R.id.buttonAvancaTercComb);
        Button buttonAtualizarBD = findViewById(R.id.buttonAtualizarBD);
        radioGroupItemTercComb = findViewById(R.id.radioGroupItemTercComb);

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

        LogProcessoDAO.getInstance().insertLogProcesso("posicao = -1;\n" +
                "        tercCombList = pcqContext.getFormularioCTR().tercCombList();\n" +
                "        for (TercCombBean tercCombBean : tercCombList) {\n" +
                "            RadioButton radioButtonItem = new RadioButton(getApplicationContext());\n" +
                "            radioButtonItem.setText(tercCombBean.getDescrTercComb());\n" +
                "            radioButtonItem.setTextColor(Color.BLACK);\n" +
                "            radioButtonItem.setTextSize(22F);\n" +
                "            radioButtonItem.setButtonTintList(colorStateList);\n" +
                "            radioGroupItemTercComb.addView(radioButtonItem);\n" +
                "        }", getLocalClassName());
        posicao = -1;
        tercCombList = pcqContext.getFormularioCTR().tercCombList();
        for (TercCombBean tercCombBean : tercCombList) {
            RadioButton radioButtonItem = new RadioButton(getApplicationContext());
            radioButtonItem.setText(tercCombBean.getDescrTercComb());
            radioButtonItem.setTextColor(Color.BLACK);
            radioButtonItem.setTextSize(22F);
            radioButtonItem.setButtonTintList(colorStateList);
            radioGroupItemTercComb.addView(radioButtonItem);
        }

        radioGroupItemTercComb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                LogProcessoDAO.getInstance().insertLogProcesso("radioGroupItemTercComb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {\n" +
                        "            @Override\n" +
                        "            public void onCheckedChanged(RadioGroup radioGroup, int i) {\n" +
                        "                View radioButton = radioGroup.findViewById(i);\n" +
                        "                posicao = radioGroup.indexOfChild(radioButton);", getLocalClassName());
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(TercCombActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(TercCombActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                        if (connectNetwork) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                    "                            progressBar = new ProgressDialog(TercCombActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();\n" +
                                    "                            pcqContext.getFormularioCTR().atualDadosTercComb(TercCombActivity.this, TercCombActivity.class, progressBar);", getLocalClassName());
                            progressBar = new ProgressDialog(TercCombActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();
                            pcqContext.getFormularioCTR().atualDadosTercComb(TercCombActivity.this, TercCombActivity.class, progressBar);
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            AlertDialog.Builder alerta = new AlertDialog.Builder(TercCombActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(TercCombActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                            "                                @Override\n" +
                                            "                                public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                                }
                            });

                            alerta.show();

                        }


                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }
                });

                alerta.show();

            }
        });

        buttonRetTercComb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetTercComb.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcqContext.getFormularioCTR().verCabecAberto()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                            "                    Intent it = new Intent(TercCombActivity.this, BrigadistaActivity.class);", getLocalClassName());
                    Intent it = new Intent(TercCombActivity.this, BrigadistaActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(TercCombActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                    Intent it = new Intent(TercCombActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonAvancaTercComb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancaTercComb.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                TercCombBean tercCombBean = new TercCombBean();\n" +
                        "                if(posicao > -1) {\n" +
                        "                    tercCombBean = tercCombList.get(posicao);\n" +
                        "                }\n" +
                        "                else {\n" +
                        "                    tercCombBean.setIdTercComb(0L);\n" +
                        "                }\n" +
                        "                pcqContext.getFormularioCTR().setTercCombCabec(tercCombBean.getIdTercComb());", getLocalClassName());
                TercCombBean tercCombBean = new TercCombBean();
                if(posicao > -1) {
                    tercCombBean = tercCombList.get(posicao);
                }
                else {
                    tercCombBean.setIdTercComb(0L);
                }
                pcqContext.getFormularioCTR().setTercCombCabec(tercCombBean.getIdTercComb());
                if(pcqContext.getFormularioCTR().verCabecAberto()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                            "                    Intent it = new Intent(TercCombActivity.this, AceiroCanavialActivity.class);", getLocalClassName());
                    Intent it = new Intent(TercCombActivity.this, AceiroCanavialActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(TercCombActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                    Intent it = new Intent(TercCombActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

    }

    public void onBackPressed() {
    }

}
