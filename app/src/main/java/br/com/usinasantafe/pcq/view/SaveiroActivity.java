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
import br.com.usinasantafe.pcq.util.ConexaoWeb;

public class SaveiroActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView saveiroListView;
    private List<EquipBean> saveiroList;
    private List<EquipItemBean> saveiroItemList;
    private PCQContext pcqContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saveiro);

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodosSaveiro);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodosSaveiro);
        Button buttonSalvarTanque = (Button) findViewById(R.id.buttonSalvarSaveiro);
        Button buttonAtualizarBD = (Button) findViewById(R.id.buttonAtualizarBD);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

        saveiroList = pcqContext.getFormularioCTR().saveiroList();
        if(pcqContext.getTipoTela() == 1) {
            saveiroItemList = pcqContext.getFormularioCTR().saveiroItemCabecIniciadoList();
        }
        else {
            saveiroItemList = pcqContext.getFormularioCTR().saveiroItemCabecAbertoList();
        }

        for (EquipBean equipBean : saveiroList) {
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            boolean ver = false;
            for(EquipItemBean equipItemBean : saveiroItemList){
                if(equipBean.getIdEquip().equals(equipItemBean.getIdEquip())){
                    ver = true;
                }
            }
            viewHolderChoice.setSelected(ver);
            viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
            itens.add(viewHolderChoice);
        }

        saveiroItemList.clear();

        adapterListChoice = new AdapterListChoice(this, itens);
        saveiroListView = (ListView) findViewById(R.id.listSaveiro);
        saveiroListView.setAdapter(adapterListChoice);

        buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                itens.clear();
                for (EquipBean equipBean : saveiroList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(false);
                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice( SaveiroActivity.this, itens);
                saveiroListView = (ListView) findViewById(R.id.listSaveiro);
                saveiroListView.setAdapter(adapterListChoice);

            }
        });

        buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                itens.clear();
                for (EquipBean equipBean : saveiroList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(true);
                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice( SaveiroActivity.this, itens);
                saveiroListView = (ListView) findViewById(R.id.listSaveiro);
                saveiroListView.setAdapter(adapterListChoice);

            }
        });

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                AlertDialog.Builder alerta = new AlertDialog.Builder(SaveiroActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(SaveiroActivity.this)) {

                            progressBar = new ProgressDialog(SaveiroActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pcqContext.getFormularioCTR().atualDadosEquip(SaveiroActivity.this, SaveiroActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(SaveiroActivity.this);
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

        buttonSalvarTanque.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ArrayList<Long> saveiroSelectedList = new ArrayList<Long>();

                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);
                    if(viewHolderChoice.isSelected()){
                        EquipBean equipBean = (EquipBean) saveiroList.get(i);
                        saveiroSelectedList.add(equipBean.getIdEquip());
                    }
                }

                if(saveiroSelectedList.size() > 0){

                    pcqContext.getFormularioCTR().setSaveiroCabec(saveiroSelectedList, pcqContext.getTipoTela());
                    saveiroSelectedList.clear();

                    if(pcqContext.getTipoTela() == 1) {
                        Intent it = new Intent(SaveiroActivity.this, BrigadistaActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Intent it = new Intent(SaveiroActivity.this, RelacaoCabecActivity.class);
                        startActivity(it);
                        finish();
                    }

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(SaveiroActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA REALMENTE AVANÇAR SEM ADICIONAR SAVEIRO?");
                    alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            pcqContext.getFormularioCTR().delSaveiroCabec(pcqContext.getTipoTela());

                            if(pcqContext.getTipoTela() == 1) {
                                Intent it = new Intent(SaveiroActivity.this, BrigadistaActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                Intent it = new Intent(SaveiroActivity.this, RelacaoCabecActivity.class);
                                startActivity(it);
                                finish();
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

                saveiroSelectedList.clear();

            }

        });

    }

    public void onBackPressed() {
        if(pcqContext.getTipoTela() == 1) {
            Intent it = new Intent(SaveiroActivity.this, TanqueActivity.class);
            startActivity(it);
            finish();
        }
        else{
            Intent it = new Intent(SaveiroActivity.this, RelacaoCabecActivity.class);
            startActivity(it);
            finish();
        }
    }

}
