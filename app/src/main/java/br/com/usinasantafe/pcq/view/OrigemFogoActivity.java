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
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

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

        Button buttonAtualizarBD = findViewById(R.id.buttonAtualizarBD);
        Button buttonRetOrigemFogo = findViewById(R.id.buttonRetOrigemFogo);
        Button buttonAvancaOrigemFogo = findViewById(R.id.buttonAvancaOrigemFogo);
        radioGroupItemOrigemFogo = findViewById(R.id.radioGroupItemOrigemFogo);

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


        LogProcessoDAO.getInstance().insertLogProcesso("posicao = -1;", getLocalClassName());
        posicao = -1;

        LogProcessoDAO.getInstance().insertLogProcesso("origemFogoList = pcqContext.getFormularioCTR().origemFogoList();\n" +
                "        for (OrigemFogoBean origemFogoBean : origemFogoList) {\n" +
                "            RadioButton radioButtonItem = new RadioButton(getApplicationContext());\n" +
                "            radioButtonItem.setText(origemFogoBean.getDescrOrigemFogo());\n" +
                "            radioButtonItem.setTextColor(Color.BLACK);\n" +
                "            radioButtonItem.setTextSize(22F);\n" +
                "            radioButtonItem.setButtonTintList(colorStateList);\n" +
                "            radioGroupItemOrigemFogo.addView(radioButtonItem);\n" +
                "        }", getLocalClassName());
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
                LogProcessoDAO.getInstance().insertLogProcesso("radioGroupItemOrigemFogo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {\n" +
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
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(OrigemFogoActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(OrigemFogoActivity.this);
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
                                    "                            progressBar = new ProgressDialog(OrigemFogoActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();\n" +
                                    "                            pcqContext.getFormularioCTR().atualDadosOrigemFogo(OrigemFogoActivity.this, OrigemFogoActivity.class, progressBar);", getLocalClassName());
                            progressBar = new ProgressDialog(OrigemFogoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pcqContext.getFormularioCTR().atualDadosOrigemFogo(OrigemFogoActivity.this, OrigemFogoActivity.class, progressBar);

                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            AlertDialog.Builder alerta = new AlertDialog.Builder(OrigemFogoActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(OrigemFogoActivity.this);
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

        buttonRetOrigemFogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetOrigemFogo.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcqContext.getFormularioCTR().verCabecAberto()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                            "                    Intent it = new Intent(OrigemFogoActivity.this, TipoApontTrabActivity.class);", getLocalClassName());
                    Intent it = new Intent(OrigemFogoActivity.this, TipoApontTrabActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(OrigemFogoActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                    Intent it = new Intent(OrigemFogoActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonAvancaOrigemFogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancaOrigemFogo.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(posicao > -1) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if(posicao > -1) {\n" +
                            "                    OrigemFogoBean origemFogoBean = origemFogoList.get(posicao);\n" +
                            "                    pcqContext.getFormularioCTR().setOrigemFogoCabec(origemFogoBean.getIdOrigemFogo());", getLocalClassName());
                    OrigemFogoBean origemFogoBean = origemFogoList.get(posicao);
                    pcqContext.getFormularioCTR().setOrigemFogoCabec(origemFogoBean.getIdOrigemFogo());
                    if(pcqContext.getFormularioCTR().verCabecAberto()){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                                "                        Intent it = new Intent(OrigemFogoActivity.this, SecaoActivity.class);", getLocalClassName());
                        Intent it = new Intent(OrigemFogoActivity.this, SecaoActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent(OrigemFogoActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
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