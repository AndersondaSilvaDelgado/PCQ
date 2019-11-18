package br.com.usinasantafe.pcq;

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

public class TanqueActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView tanqueListView;
    private List tanqueList;
    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanque);

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodosTanque);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodosTanque);
        Button buttonRetTanque = (Button) findViewById(R.id.buttonRetTanque);
        Button buttonSalvarTanque = (Button) findViewById(R.id.buttonSalvarTanque);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

        EquipBean equipBean = new EquipBean();
        tanqueList = equipBean.get("tipoEquip", 1L);

        for (int i = 0; i < tanqueList.size(); i++) {
            equipBean = (EquipBean) tanqueList.get(i);
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            viewHolderChoice.setSelected(false);
            viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
            itens.add(viewHolderChoice);
        }

        adapterListChoice = new AdapterListChoice(this, itens);
        tanqueListView = (ListView) findViewById(R.id.listTanque);
        tanqueListView.setAdapter(adapterListChoice);

        buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                itens.clear();
                for (int i = 0; i < tanqueList.size(); i++) {
                    EquipBean equipBean = (EquipBean) tanqueList.get(i);
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(false);
                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice( TanqueActivity.this, itens);
                tanqueListView = (ListView) findViewById(R.id.listTalhao);
                tanqueListView.setAdapter(adapterListChoice);

            }
        });

        buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                itens.clear();
                for (int i = 0; i < tanqueList.size(); i++) {
                    EquipBean equipBean = (EquipBean) tanqueList.get(i);
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(true);
                    viewHolderChoice.setDescrCheckBox(String.valueOf(equipBean.getNroEquip()));
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice( TanqueActivity.this, itens);
                tanqueListView = (ListView) findViewById(R.id.listTalhao);
                tanqueListView.setAdapter(adapterListChoice);

            }
        });

        buttonRetTanque.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(TanqueActivity.this, MsgActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonSalvarTanque.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ArrayList<Long> tanqueSelectedList = new ArrayList<Long>();

                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);
                    if(viewHolderChoice.isSelected()){
                        EquipBean equipBean = (EquipBean) tanqueList.get(i);
                        tanqueSelectedList.add(equipBean.getNroEquip());
                    }
                }

                if(tanqueSelectedList.size() > 0){

                    pcqContext.getFormularioCTR().getCabecBean().setTanqueCabec(tanqueSelectedList);
                    pcqContext.setPosMsg(pcqContext.getPosMsg() + 1);

                    Intent it = new Intent(TanqueActivity.this, MsgActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder( TanqueActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! SELECIONE O(S) CAMINHAO(ÕES) TANQUE USADO PARA O COMBATE AO INCÊNDIO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                        }
                    });
                    alerta.show();
                }

                tanqueSelectedList.clear();

            }

        });

    }

    public void onBackPressed() {
    }

}
