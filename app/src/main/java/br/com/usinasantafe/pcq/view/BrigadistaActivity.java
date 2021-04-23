package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
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

public class BrigadistaActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView brigadistaListView;
    private List<BrigadistaBean> brigadistaList;
    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brigadista);

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodosBrigadista);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodosBrigadista);
        Button buttonRetBrigadista = (Button) findViewById(R.id.buttonRetBrigadista);
        Button buttonSalvarBrigadista = (Button) findViewById(R.id.buttonSalvarBrigadista);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

        brigadistaList = pcqContext.getFormularioCTR().brigadistaList();

        for (BrigadistaBean brigadistaBean : brigadistaList) {
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            viewHolderChoice.setSelected(false);
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

        buttonRetBrigadista.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(BrigadistaActivity.this, MsgCameraActivity.class);
                startActivity(it);
                finish();
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

                    pcqContext.getFormularioCTR().setBrigadistaCabec(brigadistaSelectedList);
                    brigadistaSelectedList.clear();

                    Intent it = new Intent(BrigadistaActivity.this, TercCombActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(BrigadistaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA REALMENTE AVANÇAR SEM ADICIONAR BRIGADISTA?");
                    alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent it = new Intent(BrigadistaActivity.this, OrgaoAmbActivity.class);
                            startActivity(it);
                            finish();

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
    }

}
