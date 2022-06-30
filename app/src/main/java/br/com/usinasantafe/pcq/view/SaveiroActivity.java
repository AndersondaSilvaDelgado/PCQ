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
import br.com.usinasantafe.pcq.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.EquipItemBean;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class SaveiroActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView saveiroListView;
    private List<EquipBean> saveiroList;
    private PCQContext pcqContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saveiro);

        Button buttonDesmarcarTodos = findViewById(R.id.buttonDesmarcarTodosSaveiro);
        Button buttonMarcarTodos = findViewById(R.id.buttonMarcarTodosSaveiro);
        Button buttonSalvarTanque = findViewById(R.id.buttonSalvarSaveiro);
        Button buttonAtualizarBD = findViewById(R.id.buttonAtualizarBD);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<>();

        LogProcessoDAO.getInstance().insertLogProcesso("List<EquipItemBean> saveiroItemList;\n" +
                "        saveiroList = pcqContext.getFormularioCTR().saveiroList();", getLocalClassName());
        List<EquipItemBean> saveiroItemList;
        saveiroList = pcqContext.getFormularioCTR().saveiroList();
        if(pcqContext.getFormularioCTR().verCabecAberto()){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                    "            saveiroItemList = pcqContext.getFormularioCTR().saveiroItemCabecIniciadoList();", getLocalClassName());
            saveiroItemList = pcqContext.getFormularioCTR().saveiroItemCabecIniciadoList();
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            saveiroItemList = pcqContext.getFormularioCTR().saveiroItemCabecAbertoList();", getLocalClassName());
            saveiroItemList = pcqContext.getFormularioCTR().saveiroItemCabecAbertoList();
        }

        LogProcessoDAO.getInstance().insertLogProcesso("for (EquipBean equipBean : saveiroList) {\n" +
                "            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();\n" +
                "            boolean ver = false;\n" +
                "            for (EquipItemBean equipItemBean : saveiroItemList) {\n" +
                "                if (equipBean.getIdEquip().equals(equipItemBean.getIdEquip())) {\n" +
                "                    ver = true;\n" +
                "                }\n" +
                "            }\n" +
                "            viewHolderChoice.setSelected(ver);\n" +
                "            viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));\n" +
                "            itens.add(viewHolderChoice);\n" +
                "        }\n" +
                "        saveiroItemList.clear();", getLocalClassName());
        for (EquipBean equipBean : saveiroList) {
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            boolean ver = false;
            for (EquipItemBean equipItemBean : saveiroItemList) {
                if (equipBean.getIdEquip().equals(equipItemBean.getIdEquip())) {
                    ver = true;
                }
            }
            viewHolderChoice.setSelected(ver);
            viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
            itens.add(viewHolderChoice);
        }
        saveiroItemList.clear();

        LogProcessoDAO.getInstance().insertLogProcesso("adapterListChoice = new AdapterListChoice(this, itens);\n" +
                "        saveiroListView = findViewById(R.id.listSaveiro);\n" +
                "        saveiroListView.setAdapter(adapterListChoice);", getLocalClassName());
        adapterListChoice = new AdapterListChoice(this, itens);
        saveiroListView = findViewById(R.id.listSaveiro);
        saveiroListView.setAdapter(adapterListChoice);

        buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                itens.clear();\n" +
                        "                for (EquipBean equipBean : saveiroList) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();\n" +
                        "                    viewHolderChoice.setSelected(false);\n" +
                        "                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));\n" +
                        "                    itens.add(viewHolderChoice);\n" +
                        "                }\n" +
                        "                adapterListChoice = new AdapterListChoice( SaveiroActivity.this, itens);\n" +
                        "                saveiroListView = findViewById(R.id.listSaveiro);\n" +
                        "                saveiroListView.setAdapter(adapterListChoice);", getLocalClassName());
                itens.clear();
                for (EquipBean equipBean : saveiroList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(false);
                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
                    itens.add(viewHolderChoice);
                }
                adapterListChoice = new AdapterListChoice( SaveiroActivity.this, itens);
                saveiroListView = findViewById(R.id.listSaveiro);
                saveiroListView.setAdapter(adapterListChoice);
            }
        });

        buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                itens.clear();\n" +
                        "                for (EquipBean equipBean : saveiroList) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();\n" +
                        "                    viewHolderChoice.setSelected(true);\n" +
                        "                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));\n" +
                        "                    itens.add(viewHolderChoice);\n" +
                        "                }\n" +
                        "                adapterListChoice = new AdapterListChoice( SaveiroActivity.this, itens);\n" +
                        "                saveiroListView = findViewById(R.id.listSaveiro);\n" +
                        "                saveiroListView.setAdapter(adapterListChoice);", getLocalClassName());
                itens.clear();
                for (EquipBean equipBean : saveiroList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(true);
                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
                    itens.add(viewHolderChoice);
                }
                adapterListChoice = new AdapterListChoice( SaveiroActivity.this, itens);
                saveiroListView = findViewById(R.id.listSaveiro);
                saveiroListView.setAdapter(adapterListChoice);
            }
        });

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(SaveiroActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(SaveiroActivity.this);
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
                                    "                            progressBar = new ProgressDialog(SaveiroActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();\n" +
                                    "                            pcqContext.getFormularioCTR().atualDadosEquip(SaveiroActivity.this, SaveiroActivity.class, progressBar);", getLocalClassName());
                            progressBar = new ProgressDialog(SaveiroActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pcqContext.getFormularioCTR().atualDadosEquip(SaveiroActivity.this, SaveiroActivity.class, progressBar);

                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            AlertDialog.Builder alerta = new AlertDialog.Builder(SaveiroActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(SaveiroActivity.this);
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

        buttonSalvarTanque.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<Long> saveiroSelectedList = new ArrayList<Long>();\n" +
                        "                for (int i = 0; i < itens.size(); i++) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = itens.get(i);\n" +
                        "                    if(viewHolderChoice.isSelected()){\n" +
                        "                        EquipBean equipBean = saveiroList.get(i);\n" +
                        "                        saveiroSelectedList.add(equipBean.getIdEquip());\n" +
                        "                    }\n" +
                        "                }", getLocalClassName());
                ArrayList<Long> saveiroSelectedList = new ArrayList<Long>();

                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);
                    if(viewHolderChoice.isSelected()){
                        EquipBean equipBean = saveiroList.get(i);
                        saveiroSelectedList.add(equipBean.getIdEquip());
                    }
                }

                if(saveiroSelectedList.size() > 0){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(saveiroSelectedList.size() > 0){\n" +
                            "                    pcqContext.getFormularioCTR().setSaveiroCabec(saveiroSelectedList);\n" +
                            "                    saveiroSelectedList.clear();", getLocalClassName());
                    pcqContext.getFormularioCTR().setSaveiroCabec(saveiroSelectedList);
                    saveiroSelectedList.clear();
                    if(pcqContext.getFormularioCTR().verCabecAberto()){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                                "                        Intent it = new Intent(SaveiroActivity.this, BrigadistaActivity.class);", getLocalClassName());
                        Intent it = new Intent(SaveiroActivity.this, BrigadistaActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent(SaveiroActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                        Intent it = new Intent(SaveiroActivity.this, RelacaoCabecActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(SaveiroActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"DESEJA REALMENTE AVANÇAR SEM ADICIONAR SAVEIRO?\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(SaveiroActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA REALMENTE AVANÇAR SEM ADICIONAR SAVEIRO?");
                    alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                            pcqContext.getFormularioCTR().delSaveiroCabec();", getLocalClassName());
                            pcqContext.getFormularioCTR().delSaveiroCabec();
                            if(pcqContext.getFormularioCTR().verCabecAberto()){
                                LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                                        "                                Intent it = new Intent(SaveiroActivity.this, BrigadistaActivity.class);", getLocalClassName());
                                Intent it = new Intent(SaveiroActivity.this, BrigadistaActivity.class);
                                startActivity(it);
                            } else {
                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                Intent it = new Intent(SaveiroActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                                Intent it = new Intent(SaveiroActivity.this, RelacaoCabecActivity.class);
                                startActivity(it);
                            }
                            finish();

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

                saveiroSelectedList.clear();

            }

        });

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {", getLocalClassName());
        if(pcqContext.getFormularioCTR().verCabecAberto()){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                    "            Intent it = new Intent(SaveiroActivity.this, TanqueActivity.class);", getLocalClassName());
            Intent it = new Intent(SaveiroActivity.this, TanqueActivity.class);
            startActivity(it);
            finish();
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            Intent it = new Intent(SaveiroActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
            Intent it = new Intent(SaveiroActivity.this, RelacaoCabecActivity.class);
            startActivity(it);
            finish();
        }
    }

}
