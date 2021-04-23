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
import br.com.usinasantafe.pcq.model.bean.estaticas.EquipBean;

public class SaveiroActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView saveiroListView;
    private List<EquipBean> saveiroList;
    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saveiro);

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodosSaveiro);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodosSaveiro);
        Button buttonRetTanque = (Button) findViewById(R.id.buttonRetSaveiro);
        Button buttonSalvarTanque = (Button) findViewById(R.id.buttonSalvarSaveiro);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

        saveiroList = pcqContext.getFormularioCTR().saveiroList();

        for (EquipBean equipBean : saveiroList) {
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            viewHolderChoice.setSelected(false);
            viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
            itens.add(viewHolderChoice);
        }

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
                saveiroListView = (ListView) findViewById(R.id.listTalhao);
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
                saveiroListView = (ListView) findViewById(R.id.listTalhao);
                saveiroListView.setAdapter(adapterListChoice);

            }
        });

        buttonRetTanque.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(SaveiroActivity.this, MsgCameraActivity.class);
                startActivity(it);
                finish();
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
                        saveiroSelectedList.add(equipBean.getNroEquip());
                    }
                }

                if(saveiroSelectedList.size() > 0){

                    pcqContext.getFormularioCTR().setSaveiroCabec(saveiroSelectedList);
                    saveiroSelectedList.clear();

                    Intent it = new Intent(SaveiroActivity.this, BrigadistaActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(SaveiroActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA REALMENTE AVANÇAR SEM ADICIONAR SAVEIRO?");
                    alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent it = new Intent(SaveiroActivity.this, BrigadistaActivity.class);
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

                saveiroSelectedList.clear();

            }

        });

    }

    public void onBackPressed() {
    }

}
