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
import br.com.usinasantafe.pcq.model.bean.estaticas.BrigadistaBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.BrigadistaItemBean;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class BrigadistaActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView brigadistaListView;
    private List<BrigadistaBean> brigadistaList;
    private PCQContext pcqContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brigadista);

        Button buttonDesmarcarTodos = findViewById(R.id.buttonDesmarcarTodosBrigadista);
        Button buttonMarcarTodos = findViewById(R.id.buttonMarcarTodosBrigadista);
        Button buttonSalvarBrigadista = findViewById(R.id.buttonSalvarBrigadista);
        Button buttonAtualizarBD = findViewById(R.id.buttonAtualizarBD);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<>();

        LogProcessoDAO.getInstance().insertLogProcesso("brigadistaList = pcqContext.getFormularioCTR().brigadistaList();\n" +
                "        List<BrigadistaItemBean> brigadistaItemList;\n" +
                "        if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                "            brigadistaItemList = pcqContext.getFormularioCTR().brigadistaItemCabecIniciadoList();\n" +
                "        }\n" +
                "        else {\n" +
                "            brigadistaItemList = pcqContext.getFormularioCTR().brigadistaItemCabecAbertoList();\n" +
                "        }", getLocalClassName());
        brigadistaList = pcqContext.getFormularioCTR().brigadistaList();

        List<BrigadistaItemBean> brigadistaItemList;
        if(pcqContext.getFormularioCTR().verCabecAberto()){
            brigadistaItemList = pcqContext.getFormularioCTR().brigadistaItemCabecIniciadoList();
        }
        else {
            brigadistaItemList = pcqContext.getFormularioCTR().brigadistaItemCabecAbertoList();
        }

        LogProcessoDAO.getInstance().insertLogProcesso("for (BrigadistaBean brigadistaBean : brigadistaList) {\n" +
                "            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();\n" +
                "            boolean ver = false;\n" +
                "            for(BrigadistaItemBean brigadistaItemBean : brigadistaItemList){\n" +
                "                if(brigadistaBean.getIdFuncBrigadista().equals(brigadistaItemBean.getIdFunc())){\n" +
                "                    ver = true;\n" +
                "                }\n" +
                "            }\n" +
                "            viewHolderChoice.setSelected(ver);\n" +
                "            viewHolderChoice.setDescrCheckBox(brigadistaBean.getMatricBrigadista() + \" - \" + brigadistaBean.getNomeBrigadista());\n" +
                "            itens.add(viewHolderChoice);\n" +
                "        }", getLocalClassName());
        for (BrigadistaBean brigadistaBean : brigadistaList) {
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            boolean ver = false;
            for(BrigadistaItemBean brigadistaItemBean : brigadistaItemList){
                if(brigadistaBean.getIdFuncBrigadista().equals(brigadistaItemBean.getIdFunc())){
                    ver = true;
                }
            }
            viewHolderChoice.setSelected(ver);
            viewHolderChoice.setDescrCheckBox(brigadistaBean.getMatricBrigadista() + " - " + brigadistaBean.getNomeBrigadista());
            itens.add(viewHolderChoice);
        }

        LogProcessoDAO.getInstance().insertLogProcesso("adapterListChoice = new AdapterListChoice(this, itens);\n" +
                "        brigadistaListView = findViewById(R.id.listBrigadista);\n" +
                "        brigadistaListView.setAdapter(adapterListChoice);", getLocalClassName());
        adapterListChoice = new AdapterListChoice(this, itens);
        brigadistaListView = findViewById(R.id.listBrigadista);
        brigadistaListView.setAdapter(adapterListChoice);
        buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                itens.clear();", getLocalClassName());
                itens.clear();

                LogProcessoDAO.getInstance().insertLogProcesso("for (BrigadistaBean brigadistaBean : brigadistaList) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();\n" +
                        "                    viewHolderChoice.setSelected(false);\n" +
                        "                    viewHolderChoice.setDescrCheckBox(brigadistaBean.getMatricBrigadista() + \" - \" + brigadistaBean.getNomeBrigadista());\n" +
                        "                    itens.add(viewHolderChoice);\n" +
                        "                }", getLocalClassName());
                for (BrigadistaBean brigadistaBean : brigadistaList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(false);
                    viewHolderChoice.setDescrCheckBox(brigadistaBean.getMatricBrigadista() + " - " + brigadistaBean.getNomeBrigadista());
                    itens.add(viewHolderChoice);
                }

                LogProcessoDAO.getInstance().insertLogProcesso("adapterListChoice = new AdapterListChoice( BrigadistaActivity.this, itens);\n" +
                        "                brigadistaListView = findViewById(R.id.listBrigadista);\n" +
                        "                brigadistaListView.setAdapter(adapterListChoice);", getLocalClassName());
                adapterListChoice = new AdapterListChoice( BrigadistaActivity.this, itens);
                brigadistaListView = findViewById(R.id.listBrigadista);
                brigadistaListView.setAdapter(adapterListChoice);

            }
        });

        buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("        buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                itens.clear();", getLocalClassName());
                itens.clear();

                LogProcessoDAO.getInstance().insertLogProcesso("for (BrigadistaBean brigadistaBean : brigadistaList) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();\n" +
                        "                    viewHolderChoice.setSelected(true);\n" +
                        "                    viewHolderChoice.setDescrCheckBox(brigadistaBean.getMatricBrigadista() + \" - \" + brigadistaBean.getNomeBrigadista());\n" +
                        "                    itens.add(viewHolderChoice);\n" +
                        "                }", getLocalClassName());
                for (BrigadistaBean brigadistaBean : brigadistaList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(true);
                    viewHolderChoice.setDescrCheckBox(brigadistaBean.getMatricBrigadista() + " - " + brigadistaBean.getNomeBrigadista());
                    itens.add(viewHolderChoice);
                }

                LogProcessoDAO.getInstance().insertLogProcesso("adapterListChoice = new AdapterListChoice( BrigadistaActivity.this, itens);\n" +
                        "                brigadistaListView = findViewById(R.id.listBrigadista);\n" +
                        "                brigadistaListView.setAdapter(adapterListChoice);", getLocalClassName());
                adapterListChoice = new AdapterListChoice( BrigadistaActivity.this, itens);
                brigadistaListView = findViewById(R.id.listBrigadista);
                brigadistaListView.setAdapter(adapterListChoice);

            }
        });

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                \n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(BrigadistaActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(BrigadistaActivity.this);
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
                                    "                            progressBar = new ProgressDialog(BrigadistaActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();\n" +
                                    "                            pcqContext.getFormularioCTR().atualDadosBrigad(BrigadistaActivity.this, BrigadistaActivity.class, progressBar);", getLocalClassName());
                            progressBar = new ProgressDialog(BrigadistaActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pcqContext.getFormularioCTR().atualDadosBrigad(BrigadistaActivity.this, BrigadistaActivity.class, progressBar);

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            AlertDialog.Builder alerta = new AlertDialog.Builder(BrigadistaActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(BrigadistaActivity.this);
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

        buttonSalvarBrigadista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonSalvarBrigadista.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                ArrayList<Long> brigadistaSelectedList = new ArrayList<Long>();\n" +
                        "                for (int i = 0; i < itens.size(); i++) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = itens.get(i);\n" +
                        "                    if(viewHolderChoice.isSelected()){\n" +
                        "                        BrigadistaBean brigadistaBean = brigadistaList.get(i);\n" +
                        "                        brigadistaSelectedList.add(brigadistaBean.getIdFuncBrigadista());\n" +
                        "                    }\n" +
                        "                }", getLocalClassName());
                ArrayList<Long> brigadistaSelectedList = new ArrayList<Long>();

                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);
                    if(viewHolderChoice.isSelected()){
                        BrigadistaBean brigadistaBean = brigadistaList.get(i);
                        brigadistaSelectedList.add(brigadistaBean.getIdFuncBrigadista());
                    }
                }

                if(brigadistaSelectedList.size() > 0){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(brigadistaSelectedList.size() > 0){\n" +
                            "                    pcqContext.getFormularioCTR().setBrigadistaCabec(brigadistaSelectedList);\n" +
                            "                    brigadistaSelectedList.clear();", getLocalClassName());
                    pcqContext.getFormularioCTR().setBrigadistaCabec(brigadistaSelectedList);
                    brigadistaSelectedList.clear();

                    if(pcqContext.getFormularioCTR().verCabecAberto()){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                                "                        Intent it = new Intent(BrigadistaActivity.this, TercCombActivity.class);", getLocalClassName());
                        Intent it = new Intent(BrigadistaActivity.this, TercCombActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent(BrigadistaActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                        Intent it = new Intent(BrigadistaActivity.this, RelacaoCabecActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(BrigadistaActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"DESEJA REALMENTE AVANÇAR SEM ADICIONAR BRIGADISTA?\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(BrigadistaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA REALMENTE AVANÇAR SEM ADICIONAR BRIGADISTA?");
                    alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                            pcqContext.getFormularioCTR().delBrigadista();", getLocalClassName());
                            pcqContext.getFormularioCTR().delBrigadista();
                            if(pcqContext.getFormularioCTR().verCabecFinalizado()){
                                LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecFinalizado()){\n" +
                                        "                                Intent it = new Intent(BrigadistaActivity.this, TercCombActivity.class);", getLocalClassName());
                                Intent it = new Intent(BrigadistaActivity.this, TercCombActivity.class);
                                startActivity(it);
                                finish();
                            } else {
                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                Intent it = new Intent(BrigadistaActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                                Intent it = new Intent(BrigadistaActivity.this, RelacaoCabecActivity.class);
                                startActivity(it);
                                finish();
                            }

                        }
                    });

                    alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                        }
                    });

                    alerta.show();

                }

                brigadistaSelectedList.clear();

            }

        });

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {", getLocalClassName());
        if(pcqContext.getFormularioCTR().verCabecAberto()){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                    "            Intent it = new Intent(BrigadistaActivity.this, SaveiroActivity.class);", getLocalClassName());
            Intent it = new Intent(BrigadistaActivity.this, SaveiroActivity.class);
            startActivity(it);
            finish();
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            Intent it = new Intent(BrigadistaActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
            Intent it = new Intent(BrigadistaActivity.this, RelacaoCabecActivity.class);
            startActivity(it);
            finish();
        }
    }

}
