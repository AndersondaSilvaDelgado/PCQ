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
import br.com.usinasantafe.pcq.util.ConexaoWeb;

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

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodosTalhao);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodosTalhao);
        Button buttonSalvarTalhao = (Button) findViewById(R.id.buttonSalvarTalhao);
        Button buttonAtualizarBD = (Button) findViewById(R.id.buttonAtualizarBD);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

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

                AlertDialog.Builder alerta = new AlertDialog.Builder(TalhaoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(TalhaoActivity.this)) {

                            progressBar = new ProgressDialog(TalhaoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pcqContext.getFormularioCTR().atualDadosTalhao(TalhaoActivity.this, TalhaoActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(TalhaoActivity.this);
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

        buttonSalvarTalhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ArrayList<Long> talhaoSelectedList = new ArrayList<Long>();

                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);
                    if(viewHolderChoice.isSelected()){
                        TalhaoBean talhaoBean = (TalhaoBean) talhaoList.get(i);
                        talhaoSelectedList.add(talhaoBean.getIdTalhao());
                    }
                }

                if(talhaoSelectedList.size() > 0){

                    pcqContext.getFormularioCTR().setSecaoCabec(pcqContext.getFormularioCTR().getCodSecao(pcqContext.getFormularioCTR().getSecao()).getIdSecao(), pcqContext.getTipoTela());
                    pcqContext.getFormularioCTR().setTalhaoCabec(talhaoSelectedList, pcqContext.getTipoTela());
                    pcqContext.getFormularioCTR().setPosTalhao(1);

                    Intent it = new Intent(TalhaoActivity.this, TipoTalhaoActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder( TalhaoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! SELECIONE O(S) TALHAO(OES) DO INCENDIO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerta.show();
                }

                talhaoSelectedList.clear();

            }

        });

    }

    public void onBackPressed() {
        Intent it = new Intent(TalhaoActivity.this, SecaoActivity.class);
        startActivity(it);
        finish();
    }

}
