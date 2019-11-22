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

public class OrgaoAmbActivity extends Activity {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView orgaoAmbListView;
    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgao_amb);

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodosOrgAmbiental);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodosOrgAmbiental);
        Button buttonRetOrgAmbiental = (Button) findViewById(R.id.buttonRetOrgAmbiental);
        Button buttonSalvarOrgAmbiental = (Button) findViewById(R.id.buttonSalvarOrgAmbiental);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

        ViewHolderChoice viewHolderChoiceCetesb = new ViewHolderChoice();
        viewHolderChoiceCetesb.setSelected(false);
        viewHolderChoiceCetesb.setDescrCheckBox("CETESB");
        itens.add(viewHolderChoiceCetesb);

        ViewHolderChoice viewHolderChoicePolicia = new ViewHolderChoice();
        viewHolderChoicePolicia.setSelected(false);
        viewHolderChoicePolicia.setDescrCheckBox("POLÍCIA AMBIENTAL");
        itens.add(viewHolderChoicePolicia);

        adapterListChoice = new AdapterListChoice(this, itens);
        orgaoAmbListView = (ListView) findViewById(R.id.listOrgAmbiental);
        orgaoAmbListView.setAdapter(adapterListChoice);

        buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                itens.clear();
                ViewHolderChoice viewHolderChoiceCetesb = new ViewHolderChoice();
                viewHolderChoiceCetesb.setSelected(false);
                viewHolderChoiceCetesb.setDescrCheckBox("CETESB");
                itens.add(viewHolderChoiceCetesb);

                ViewHolderChoice viewHolderChoicePolicia = new ViewHolderChoice();
                viewHolderChoicePolicia.setSelected(false);
                viewHolderChoicePolicia.setDescrCheckBox("POLÍCIA AMBIENTAL");
                itens.add(viewHolderChoicePolicia);

                adapterListChoice = new AdapterListChoice( OrgaoAmbActivity.this, itens);
                orgaoAmbListView = (ListView) findViewById(R.id.listTalhao);
                orgaoAmbListView.setAdapter(adapterListChoice);

            }
        });

        buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                itens.clear();
                ViewHolderChoice viewHolderChoiceCetesb = new ViewHolderChoice();
                viewHolderChoiceCetesb.setSelected(true);
                viewHolderChoiceCetesb.setDescrCheckBox("CETESB");
                itens.add(viewHolderChoiceCetesb);

                ViewHolderChoice viewHolderChoicePolicia = new ViewHolderChoice();
                viewHolderChoicePolicia.setSelected(true);
                viewHolderChoicePolicia.setDescrCheckBox("POLÍCIA AMBIENTAL");
                itens.add(viewHolderChoicePolicia);

                adapterListChoice = new AdapterListChoice( OrgaoAmbActivity.this, itens);
                orgaoAmbListView = (ListView) findViewById(R.id.listTalhao);
                orgaoAmbListView.setAdapter(adapterListChoice);

            }
        });

        buttonRetOrgAmbiental.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(OrgaoAmbActivity.this, MsgActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonSalvarOrgAmbiental.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ArrayList<Long> orgaoAmbSelectedList = new ArrayList<Long>();

                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);
                    if(viewHolderChoice.isSelected()){
                        orgaoAmbSelectedList.add(Long.valueOf(i + 1));
                    }
                }

                if(orgaoAmbSelectedList.size() > 0){

                    pcqContext.getFormularioCTR().setOrgAmbCabec(orgaoAmbSelectedList);

                    Intent it = new Intent(OrgaoAmbActivity.this, ComentarioActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder( OrgaoAmbActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! SELECIONE O(S) ORGÃO(ÕES) AMBIENTAL QUE FISCALIZARAM O COMBATE AO INCÊNDIO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                        }
                    });
                    alerta.show();
                }

            }

        });

    }
}