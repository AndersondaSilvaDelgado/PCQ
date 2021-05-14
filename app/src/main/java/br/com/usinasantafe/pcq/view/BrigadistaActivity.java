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
import br.com.usinasantafe.pcq.util.ConexaoWeb;

public class BrigadistaActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView brigadistaListView;
    private List<BrigadistaBean> brigadistaList;
    private List<BrigadistaItemBean> brigadistaItemList;
    private PCQContext pcqContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brigadista);

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodosBrigadista);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodosBrigadista);
        Button buttonSalvarBrigadista = (Button) findViewById(R.id.buttonSalvarBrigadista);
        Button buttonAtualizarBD = (Button) findViewById(R.id.buttonAtualizarBD);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

        brigadistaList = pcqContext.getFormularioCTR().brigadistaList();

        if(pcqContext.getTipoTela() == 1) {
            brigadistaItemList = pcqContext.getFormularioCTR().brigadistaItemCabecIniciadoList();
        }
        else {
            brigadistaItemList = pcqContext.getFormularioCTR().brigadistaItemCabecAbertoList();
        }

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

        adapterListChoice = new AdapterListChoice(this, itens);
        brigadistaListView = (ListView) findViewById(R.id.listBrigadista);
        brigadistaListView.setAdapter(adapterListChoice);

        buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                itens.clear();
                for (BrigadistaBean brigadistaBean : brigadistaList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(false);
                    viewHolderChoice.setDescrCheckBox(brigadistaBean.getMatricBrigadista() + " - " + brigadistaBean.getNomeBrigadista());
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice( BrigadistaActivity.this, itens);
                brigadistaListView = (ListView) findViewById(R.id.listBrigadista);
                brigadistaListView.setAdapter(adapterListChoice);

            }
        });

        buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                itens.clear();
                for (BrigadistaBean brigadistaBean : brigadistaList) {
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(true);
                    viewHolderChoice.setDescrCheckBox(brigadistaBean.getMatricBrigadista() + " - " + brigadistaBean.getNomeBrigadista());
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice( BrigadistaActivity.this, itens);
                brigadistaListView = (ListView) findViewById(R.id.listBrigadista);
                brigadistaListView.setAdapter(adapterListChoice);

            }
        });

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                AlertDialog.Builder alerta = new AlertDialog.Builder(BrigadistaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(BrigadistaActivity.this)) {

                            progressBar = new ProgressDialog(BrigadistaActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pcqContext.getFormularioCTR().atualDadosBrigad(BrigadistaActivity.this, BrigadistaActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(BrigadistaActivity.this);
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

        buttonSalvarBrigadista.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ArrayList<Long> brigadistaSelectedList = new ArrayList<Long>();

                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);
                    if(viewHolderChoice.isSelected()){
                        BrigadistaBean brigadistaBean = (BrigadistaBean) brigadistaList.get(i);
                        brigadistaSelectedList.add(brigadistaBean.getIdFuncBrigadista());
                    }
                }

                if(brigadistaSelectedList.size() > 0){

                    pcqContext.getFormularioCTR().setBrigadistaCabec(brigadistaSelectedList, pcqContext.getTipoTela());
                    brigadistaSelectedList.clear();

                    if(pcqContext.getTipoTela()  == 1) {
                        Intent it = new Intent(BrigadistaActivity.this, TercCombActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Intent it = new Intent(BrigadistaActivity.this, RelacaoCabecActivity.class);
                        startActivity(it);
                        finish();
                    }

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(BrigadistaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA REALMENTE AVANÇAR SEM ADICIONAR BRIGADISTA?");
                    alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            pcqContext.getFormularioCTR().delBrigadista(pcqContext.getTipoTela());

                            if(pcqContext.getTipoTela() == 1) {
                                Intent it = new Intent(BrigadistaActivity.this, TercCombActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                Intent it = new Intent(BrigadistaActivity.this, RelacaoCabecActivity.class);
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

                brigadistaSelectedList.clear();

            }

        });

    }

    public void onBackPressed() {
        if(pcqContext.getTipoTela() == 1) {
            Intent it = new Intent(BrigadistaActivity.this, SaveiroActivity.class);
            startActivity(it);
            finish();
        }
        else{
            Intent it = new Intent(BrigadistaActivity.this, RelacaoCabecActivity.class);
            startActivity(it);
            finish();
        }
    }

}
