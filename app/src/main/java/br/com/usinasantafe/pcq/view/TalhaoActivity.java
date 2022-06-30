package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class TalhaoActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView talhaoListView;
    private List<TalhaoBean> talhaoList;
    private PCQContext pcqContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talhao);

        pcqContext = (PCQContext) getApplication();

        Button buttonDesmarcarTodos = findViewById(R.id.buttonDesmarcarTodosTalhao);
        Button buttonMarcarTodos = findViewById(R.id.buttonMarcarTodosTalhao);
        Button buttonSalvarTalhao = findViewById(R.id.buttonSalvarTalhao);
        Button buttonAtualizarBD = findViewById(R.id.buttonAtualizarBD);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

        LogProcessoDAO.getInstance().insertLogProcesso("talhaoList = pcqContext.getFormularioCTR().talhaoList();\n" +
                "        for (TalhaoBean talhaoBean : talhaoList) {\n" +
                "            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();\n" +
                "            viewHolderChoice.setSelected(false);\n" +
                "            viewHolderChoice.setDescrCheckBox(String.valueOf(talhaoBean.getCodTalhao()));\n" +
                "            itens.add(viewHolderChoice);\n" +
                "        }\n" +
                "        adapterListChoice = new AdapterListChoice(this, itens);\n" +
                "        talhaoListView = (ListView) findViewById(R.id.listTalhao);\n" +
                "        talhaoListView.setAdapter(adapterListChoice);", getLocalClassName());
        talhaoList = pcqContext.getFormularioCTR().talhaoList();

        for (TalhaoBean talhaoBean : talhaoList) {
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            viewHolderChoice.setSelected(false);
            viewHolderChoice.setDescrCheckBox(String.valueOf(talhaoBean.getCodTalhao()));
            itens.add(viewHolderChoice);
        }

        adapterListChoice = new AdapterListChoice(this, itens);
        talhaoListView = (ListView) findViewById(R.id.listTalhao);
        talhaoListView.setAdapter(adapterListChoice);
        buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                itens.clear();\n" +
                        "                for (TalhaoBean talhaoBean : talhaoList) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();\n" +
                        "                    viewHolderChoice.setSelected(false);\n" +
                        "                    viewHolderChoice.setDescrCheckBox(String.valueOf(talhaoBean.getCodTalhao()));\n" +
                        "                    itens.add(viewHolderChoice);\n" +
                        "                }\n" +
                        "                adapterListChoice = new AdapterListChoice( TalhaoActivity.this, itens);\n" +
                        "                talhaoListView = (ListView) findViewById(R.id.listTalhao);\n" +
                        "                talhaoListView.setAdapter(adapterListChoice);", getLocalClassName());
                itens.clear();
                for (TalhaoBean talhaoBean : talhaoList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(false);
                    viewHolderChoice.setDescrCheckBox(String.valueOf(talhaoBean.getCodTalhao()));
                    itens.add(viewHolderChoice);
                }
                adapterListChoice = new AdapterListChoice( TalhaoActivity.this, itens);
                talhaoListView = (ListView) findViewById(R.id.listTalhao);
                talhaoListView.setAdapter(adapterListChoice);
            }
        });

        buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                itens.clear();\n" +
                        "                for (TalhaoBean talhaoBean : talhaoList) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();\n" +
                        "                    viewHolderChoice.setSelected(true);\n" +
                        "                    viewHolderChoice.setDescrCheckBox(String.valueOf(talhaoBean.getCodTalhao()));\n" +
                        "                    itens.add(viewHolderChoice);\n" +
                        "                }\n" +
                        "                adapterListChoice = new AdapterListChoice( TalhaoActivity.this, itens);\n" +
                        "                talhaoListView = (ListView) findViewById(R.id.listTalhao);\n" +
                        "                talhaoListView.setAdapter(adapterListChoice);", getLocalClassName());
                itens.clear();
                for (TalhaoBean talhaoBean : talhaoList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(true);
                    viewHolderChoice.setDescrCheckBox(String.valueOf(talhaoBean.getCodTalhao()));
                    itens.add(viewHolderChoice);
                }
                adapterListChoice = new AdapterListChoice( TalhaoActivity.this, itens);
                talhaoListView = (ListView) findViewById(R.id.listTalhao);
                talhaoListView.setAdapter(adapterListChoice);
            }
        });

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(TalhaoActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(TalhaoActivity.this);
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
                                    "                            progressBar = new ProgressDialog(TalhaoActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();\n" +
                                    "                            pcqContext.getFormularioCTR().atualDadosTalhao(TalhaoActivity.this, TalhaoActivity.class, progressBar);", getLocalClassName());
                            progressBar = new ProgressDialog(TalhaoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();
                            pcqContext.getFormularioCTR().atualDadosTalhao(TalhaoActivity.this, TalhaoActivity.class, progressBar);
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            AlertDialog.Builder alerta = new AlertDialog.Builder(TalhaoActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(TalhaoActivity.this);
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

        buttonSalvarTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonSalvarTalhao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                ArrayList<Long> talhaoSelectedList = new ArrayList<Long>();\n" +
                        "                for (int i = 0; i < itens.size(); i++) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = itens.get(i);\n" +
                        "                    if(viewHolderChoice.isSelected()){\n" +
                        "                        TalhaoBean talhaoBean = talhaoList.get(i);\n" +
                        "                        talhaoSelectedList.add(talhaoBean.getIdTalhao());\n" +
                        "                    }\n" +
                        "                }", getLocalClassName());
                ArrayList<Long> talhaoSelectedList = new ArrayList<Long>();
                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);
                    if(viewHolderChoice.isSelected()){
                        TalhaoBean talhaoBean = talhaoList.get(i);
                        talhaoSelectedList.add(talhaoBean.getIdTalhao());
                    }
                }
                if(talhaoSelectedList.size() > 0){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(talhaoSelectedList.size() > 0){\n" +
                            "                    pcqContext.getFormularioCTR().setTalhaoCabec(talhaoSelectedList);\n" +
                            "                    pcqContext.getFormularioCTR().setPosTalhao(1);\n" +
                            "                    Intent it = new Intent(TalhaoActivity.this, TipoTalhaoActivity.class);", getLocalClassName());
                    pcqContext.getFormularioCTR().setTalhaoCabec(talhaoSelectedList);
                    pcqContext.getFormularioCTR().setPosTalhao(1);
                    Intent it = new Intent(TalhaoActivity.this, TipoTalhaoActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder( TalhaoActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"POR FAVOR! SELECIONE O(S) TALHAO(OES) DO INCENDIO.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder( TalhaoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! SELECIONE O(S) TALHAO(OES) DO INCENDIO.");
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
                LogProcessoDAO.getInstance().insertLogProcesso("talhaoSelectedList.clear();", getLocalClassName());
                talhaoSelectedList.clear();
            }

        });

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {\n" +
                "        Intent it = new Intent(TalhaoActivity.this, SecaoActivity.class);", getLocalClassName());
        Intent it = new Intent(TalhaoActivity.this, SecaoActivity.class);
        startActivity(it);
        finish();
    }

}
