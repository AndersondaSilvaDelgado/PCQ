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

public class TanqueActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView tanqueListView;
    private List<EquipBean> tanqueList;
    private List<EquipItemBean> tanqueItemList;
    private PCQContext pcqContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanque);

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodosTanque);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodosTanque);
        Button buttonSalvarTanque = (Button) findViewById(R.id.buttonSalvarTanque);
        Button buttonAtualizarBD = (Button) findViewById(R.id.buttonAtualizarBD);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

        tanqueList = pcqContext.getFormularioCTR().tanqueList();
        if(pcqContext.getTipoTela() == 1) {
            tanqueItemList = pcqContext.getFormularioCTR().tanqueItemCabecIniciadoList();
        }
        else {
            tanqueItemList = pcqContext.getFormularioCTR().tanqueItemCabecAbertoList();
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
        tanqueListView = (ListView) findViewById(R.id.listTanque);
        tanqueListView.setAdapter(adapterListChoice);

        buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                itens.clear();
                for (EquipBean equipBean : tanqueList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(false);
                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice( TanqueActivity.this, itens);
                tanqueListView = (ListView) findViewById(R.id.listTanque);
                tanqueListView.setAdapter(adapterListChoice);

            }
        });

        buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                itens.clear();
                for (EquipBean equipBean : tanqueList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(true);
                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice( TanqueActivity.this, itens);
                tanqueListView = (ListView) findViewById(R.id.listTanque);
                tanqueListView.setAdapter(adapterListChoice);

            }
        });

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(TanqueActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(TanqueActivity.this)) {

                            progressBar = new ProgressDialog(TanqueActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pcqContext.getFormularioCTR().atualDadosEquip(TanqueActivity.this, TanqueActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(TanqueActivity.this);
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

                ArrayList<Long> tanqueSelectedList = new ArrayList<Long>();

                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);
                    if(viewHolderChoice.isSelected()){
                        EquipBean equipBean = (EquipBean) tanqueList.get(i);
                        tanqueSelectedList.add(equipBean.getIdEquip());
                    }
                }

                if(tanqueSelectedList.size() > 0){

                    pcqContext.getFormularioCTR().setTanqueCabec(tanqueSelectedList, pcqContext.getTipoTela());
                    tanqueSelectedList.clear();

                    if(pcqContext.getTipoTela() == 1) {
                        Intent it = new Intent(TanqueActivity.this, SaveiroActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Intent it = new Intent(TanqueActivity.this, RelacaoCabecActivity.class);
                        startActivity(it);
                        finish();
                    }

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(TanqueActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA REALMENTE AVANÇAR SEM ADICIONAR TANQUE DA ÁQUA?");
                    alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            pcqContext.getFormularioCTR().delTanqueCabec(pcqContext.getTipoTela());
                            if(pcqContext.getTipoTela() == 1) {
                                Intent it = new Intent(TanqueActivity.this, SaveiroActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                Intent it = new Intent(TanqueActivity.this, RelacaoCabecActivity.class);
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

            }

        });

    }

    public void onBackPressed() {
        if(pcqContext.getTipoTela() == 1) {
            Intent it = new Intent(TanqueActivity.this, CameraActivity.class);
            startActivity(it);
            finish();
        }
        else{
            Intent it = new Intent(TanqueActivity.this, RelacaoCabecActivity.class);
            startActivity(it);
            finish();
        }
    }

}
