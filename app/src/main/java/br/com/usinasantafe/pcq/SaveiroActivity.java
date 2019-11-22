package br.com.usinasantafe.pcq;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.bean.estaticas.EquipBean;

public class SaveiroActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView saveiroListView;
    private List saveiroList;
    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saveiro);

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodosTanque);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodosTanque);
        Button buttonRetTanque = (Button) findViewById(R.id.buttonRetTanque);
        Button buttonSalvarTanque = (Button) findViewById(R.id.buttonSalvarTanque);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

        EquipBean equipBean = new EquipBean();
        saveiroList = equipBean.get("tipoEquip", 2L);

        for (int i = 0; i < saveiroList.size(); i++) {
            equipBean = (EquipBean) saveiroList.get(i);
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
                // TODO Auto-generated method stub

                itens.clear();
                for (int i = 0; i < saveiroList.size(); i++) {
                    EquipBean equipBean = (EquipBean) saveiroList.get(i);
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
                // TODO Auto-generated method stub

                itens.clear();
                for (int i = 0; i < saveiroList.size(); i++) {
                    EquipBean equipBean = (EquipBean) saveiroList.get(i);
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
                // TODO Auto-generated method stub
                Intent it = new Intent(SaveiroActivity.this, MsgActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonSalvarTanque.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

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
                    pcqContext.getFormularioCTR().setPosMsg(pcqContext.getFormularioCTR().getPosMsg() + 1);

                    Intent it = new Intent(SaveiroActivity.this, MsgActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder( SaveiroActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! SELECIONE A(S) SAVEIRO(S) USADO PARA O COMBATE AO INCÊNDIO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

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
