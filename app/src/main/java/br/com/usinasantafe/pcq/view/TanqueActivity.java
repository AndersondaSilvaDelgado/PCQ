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

public class TanqueActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView tanqueListView;
    private List<EquipBean> tanqueList;
    private PCQContext pcqContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanque);

        Button buttonDesmarcarTodos = findViewById(R.id.buttonDesmarcarTodosTanque);
        Button buttonMarcarTodos = findViewById(R.id.buttonMarcarTodosTanque);
        Button buttonSalvarTanque = findViewById(R.id.buttonSalvarTanque);
        Button buttonAtualizarBD = findViewById(R.id.buttonAtualizarBD);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<>();

        LogProcessoDAO.getInstance().insertLogProcesso("tanqueList = pcqContext.getFormularioCTR().tanqueList();\n" +
                "        List<EquipItemBean> tanqueItemList;\n" +
                "        if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                "            tanqueItemList = pcqContext.getFormularioCTR().tanqueCabecAbertoList();\n" +
                "        }\n" +
                "        else {\n" +
                "            tanqueItemList = pcqContext.getFormularioCTR().tanqueCabecFinalizadoList();\n" +
                "        }\n" +
                "        for (EquipBean equipBean : tanqueList) {\n" +
                "            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();\n" +
                "            boolean ver = false;\n" +
                "            for(EquipItemBean equipItemBean : tanqueItemList){\n" +
                "                if(equipBean.getIdEquip().equals(equipItemBean.getIdEquip())){\n" +
                "                    ver = true;\n" +
                "                }\n" +
                "            }\n" +
                "            viewHolderChoice.setSelected(ver);\n" +
                "            viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));\n" +
                "            itens.add(viewHolderChoice);\n" +
                "        }\n" +
                "        tanqueItemList.clear();\n" +
                "        adapterListChoice = new AdapterListChoice(this, itens);\n" +
                "        tanqueListView = findViewById(R.id.listTanque);\n" +
                "        tanqueListView.setAdapter(adapterListChoice);", getLocalClassName());
        tanqueList = pcqContext.getFormularioCTR().tanqueList();
        List<EquipItemBean> tanqueItemList;
        if(pcqContext.getFormularioCTR().verCabecAberto()){
            tanqueItemList = pcqContext.getFormularioCTR().tanqueCabecAbertoList();
        }
        else {
            tanqueItemList = pcqContext.getFormularioCTR().tanqueCabecFinalizadoList();
        }
        for (EquipBean equipBean : tanqueList) {
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            boolean ver = false;
            for(EquipItemBean equipItemBean : tanqueItemList){
                if(equipBean.getIdEquip().equals(equipItemBean.getIdEquip())){
                    ver = true;
                }
            }
            viewHolderChoice.setSelected(ver);
            viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
            itens.add(viewHolderChoice);
        }
        tanqueItemList.clear();
        adapterListChoice = new AdapterListChoice(this, itens);
        tanqueListView = findViewById(R.id.listTanque);
        tanqueListView.setAdapter(adapterListChoice);
        buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                itens.clear();\n" +
                        "                for (EquipBean equipBean : tanqueList) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();\n" +
                        "                    viewHolderChoice.setSelected(false);\n" +
                        "                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));\n" +
                        "                    itens.add(viewHolderChoice);\n" +
                        "                }\n" +
                        "                adapterListChoice = new AdapterListChoice( TanqueActivity.this, itens);\n" +
                        "                tanqueListView = findViewById(R.id.listTanque);\n" +
                        "                tanqueListView.setAdapter(adapterListChoice);", getLocalClassName());
                itens.clear();
                for (EquipBean equipBean : tanqueList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(false);
                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
                    itens.add(viewHolderChoice);
                }
                adapterListChoice = new AdapterListChoice( TanqueActivity.this, itens);
                tanqueListView = findViewById(R.id.listTanque);
                tanqueListView.setAdapter(adapterListChoice);
            }
        });

        buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                itens.clear();\n" +
                        "                for (EquipBean equipBean : tanqueList) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();\n" +
                        "                    viewHolderChoice.setSelected(true);\n" +
                        "                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));\n" +
                        "                    itens.add(viewHolderChoice);\n" +
                        "                }\n" +
                        "                adapterListChoice = new AdapterListChoice( TanqueActivity.this, itens);\n" +
                        "                tanqueListView = findViewById(R.id.listTanque);\n" +
                        "                tanqueListView.setAdapter(adapterListChoice);", getLocalClassName());
                itens.clear();
                for (EquipBean equipBean : tanqueList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(true);
                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
                    itens.add(viewHolderChoice);
                }
                adapterListChoice = new AdapterListChoice( TanqueActivity.this, itens);
                tanqueListView = findViewById(R.id.listTanque);
                tanqueListView.setAdapter(adapterListChoice);
            }
        });

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(TanqueActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(TanqueActivity.this);
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
                                    "                            progressBar = new ProgressDialog(TanqueActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();\n" +
                                    "                            pcqContext.getFormularioCTR().atualDadosEquip(TanqueActivity.this, TanqueActivity.class, progressBar);", getLocalClassName());
                            progressBar = new ProgressDialog(TanqueActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();
                            pcqContext.getFormularioCTR().atualDadosEquip(TanqueActivity.this, TanqueActivity.class, progressBar);
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            AlertDialog.Builder alerta = new AlertDialog.Builder(TanqueActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(TanqueActivity.this);
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
                LogProcessoDAO.getInstance().insertLogProcesso("buttonSalvarTanque.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                ArrayList<Long> tanqueSelectedList = new ArrayList<Long>();\n" +
                        "                for (int i = 0; i < itens.size(); i++) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = itens.get(i);\n" +
                        "                    if(viewHolderChoice.isSelected()){\n" +
                        "                        EquipBean equipBean = tanqueList.get(i);\n" +
                        "                        tanqueSelectedList.add(equipBean.getIdEquip());\n" +
                        "                    }\n" +
                        "                }", getLocalClassName());
                ArrayList<Long> tanqueSelectedList = new ArrayList<Long>();
                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);
                    if(viewHolderChoice.isSelected()){
                        EquipBean equipBean = tanqueList.get(i);
                        tanqueSelectedList.add(equipBean.getIdEquip());
                    }
                }
                if(tanqueSelectedList.size() > 0){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(tanqueSelectedList.size() > 0){\n" +
                            "                    pcqContext.getFormularioCTR().setTanqueCabec(tanqueSelectedList);\n" +
                            "                    tanqueSelectedList.clear();", getLocalClassName());
                    pcqContext.getFormularioCTR().setTanqueCabec(tanqueSelectedList);
                    tanqueSelectedList.clear();
                    if(pcqContext.getFormularioCTR().verCabecAberto()){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                                "                        Intent it = new Intent(TanqueActivity.this, SaveiroActivity.class);", getLocalClassName());
                        Intent it = new Intent(TanqueActivity.this, SaveiroActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent(TanqueActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                        Intent it = new Intent(TanqueActivity.this, RelacaoCabecActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(TanqueActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"DESEJA REALMENTE AVANÇAR SEM ADICIONAR TANQUE DA ÁQUA?\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(TanqueActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA REALMENTE AVANÇAR SEM ADICIONAR TANQUE DA ÁQUA?");
                    alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                            pcqContext.getFormularioCTR().delTanqueCabec();", getLocalClassName());
                            pcqContext.getFormularioCTR().delTanqueCabec();
                            if(pcqContext.getFormularioCTR().verCabecAberto()){
                                LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                                        "                                Intent it = new Intent(TanqueActivity.this, SaveiroActivity.class);", getLocalClassName());
                                Intent it = new Intent(TanqueActivity.this, SaveiroActivity.class);
                                startActivity(it);
                                finish();
                            } else {
                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                Intent it = new Intent(TanqueActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                                Intent it = new Intent(TanqueActivity.this, RelacaoCabecActivity.class);
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

            }

        });

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {", getLocalClassName());
        if(pcqContext.getFormularioCTR().verCabecAberto()){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                    "            Intent it = new Intent(TanqueActivity.this, MsgCameraActivity.class);", getLocalClassName());
            Intent it = new Intent(TanqueActivity.this, MsgCameraActivity.class);
            startActivity(it);
            finish();
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            Intent it = new Intent(TanqueActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
            Intent it = new Intent(TanqueActivity.this, RelacaoCabecActivity.class);
            startActivity(it);
            finish();
        }
    }

}
