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
import br.com.usinasantafe.pcq.model.bean.estaticas.TalhaoBean;

public class TalhaoActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView talhaoListView;
    private List<TalhaoBean> talhaoList;
    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talhao);

        pcqContext = (PCQContext) getApplication();

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodosTalhao);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodosTalhao);
        Button buttonRetTalhao = (Button) findViewById(R.id.buttonRetTalhao);
        Button buttonSalvarTalhao = (Button) findViewById(R.id.buttonSalvarTalhao);

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

        buttonRetTalhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(TalhaoActivity.this, SecaoActivity.class);
                startActivity(it);
                finish();
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

                    pcqContext.getFormularioCTR().setTalhaoCabec(talhaoSelectedList);
                    pcqContext.getFormularioCTR().setPosMsg(1);

                    Intent it = new Intent(TalhaoActivity.this, QuestoesCabecActivity.class);
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
    }

}
