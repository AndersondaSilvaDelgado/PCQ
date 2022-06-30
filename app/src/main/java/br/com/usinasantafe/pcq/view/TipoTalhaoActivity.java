package br.com.usinasantafe.pcq.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.bean.variaveis.TalhaoItemBean;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class TipoTalhaoActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView statusCanavialListView;
    private List<TalhaoItemBean> talhaoItemList;
    private PCQContext pcqContext;
    private TalhaoItemBean talhaoItemBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_talhao);

        TextView textViewTituloStatusCanavial = findViewById(R.id.textViewTituloStatusCanavial);
        Button buttonRetStatusCanavial = findViewById(R.id.buttonRetStatusCanavial);
        Button buttonSalvarStatusCanavial = findViewById(R.id.buttonSalvarStatusCanavial);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<>();

        LogProcessoDAO.getInstance().insertLogProcesso("talhaoItemList = pcqContext.getFormularioCTR().talhaoItemList();\n" +
                "        talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);\n" +
                "        talhaoItemList.clear();", getLocalClassName());
        talhaoItemList = pcqContext.getFormularioCTR().talhaoItemList();
        talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);
        talhaoItemList.clear();

        textViewTituloStatusCanavial.setText("STATUS CANAVIAL\n" +
                                                "TALHÃO " + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao());

        LogProcessoDAO.getInstance().insertLogProcesso("ViewHolderChoice viewHolderChoice1 = new ViewHolderChoice();\n" +
                "        viewHolderChoice1.setSelected(false);\n" +
                "        viewHolderChoice1.setDescrCheckBox(\"CANA\");\n" +
                "        itens.add(viewHolderChoice1);\n" +
                "        ViewHolderChoice viewHolderChoice2 = new ViewHolderChoice();\n" +
                "        viewHolderChoice2.setSelected(false);\n" +
                "        viewHolderChoice2.setDescrCheckBox(\"PALHADA\");\n" +
                "        itens.add(viewHolderChoice2);\n" +
                "        adapterListChoice = new AdapterListChoice(this, itens);\n" +
                "        statusCanavialListView = (ListView) findViewById(R.id.listStatusCanavial);\n" +
                "        statusCanavialListView.setAdapter(adapterListChoice);", getLocalClassName());
        ViewHolderChoice viewHolderChoice1 = new ViewHolderChoice();
        viewHolderChoice1.setSelected(false);
        viewHolderChoice1.setDescrCheckBox("CANA");
        itens.add(viewHolderChoice1);
        ViewHolderChoice viewHolderChoice2 = new ViewHolderChoice();
        viewHolderChoice2.setSelected(false);
        viewHolderChoice2.setDescrCheckBox("PALHADA");
        itens.add(viewHolderChoice2);
        adapterListChoice = new AdapterListChoice(this, itens);
        statusCanavialListView = (ListView) findViewById(R.id.listStatusCanavial);
        statusCanavialListView.setAdapter(adapterListChoice);
        buttonRetStatusCanavial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetStatusCanavial.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcqContext.getFormularioCTR().getPosTalhao() == 1){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().getPosTalhao() == 1){\n" +
                            "                    Intent it = new Intent( TipoTalhaoActivity.this, TalhaoActivity.class);", getLocalClassName());
                    Intent it = new Intent( TipoTalhaoActivity.this, TalhaoActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonSalvarStatusCanavial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonSalvarStatusCanavial.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                int tipoCana = 0;\n" +
                        "                int tipoPalhada = 0;\n" +
                        "                for (int i = 0; i < itens.size(); i++) {\n" +
                        "                    ViewHolderChoice viewHolderChoice = itens.get(i);\n" +
                        "                    if(viewHolderChoice.isSelected()){\n" +
                        "                        if(i == 0){\n" +
                        "                            tipoCana = 1;\n" +
                        "                        }\n" +
                        "                        else if(i == 1){\n" +
                        "                            tipoPalhada = 1;\n" +
                        "                        }\n" +
                        "                    }\n" +
                        "                }", getLocalClassName());
                int tipoCana = 0;
                int tipoPalhada = 0;
                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);
                    if(viewHolderChoice.isSelected()){
                        if(i == 0){
                            tipoCana = 1;
                        }
                        else if(i == 1){
                            tipoPalhada = 1;
                        }
                    }
                }
                LogProcessoDAO.getInstance().insertLogProcesso("Long tipoTalhao = 0L;\n" +
                        "                if((tipoCana == 1) && (tipoPalhada == 0)){\n" +
                        "                    tipoTalhao = 1L;\n" +
                        "                }\n" +
                        "                else if((tipoCana == 0) && (tipoPalhada == 1)){\n" +
                        "                    tipoTalhao = 2L;\n" +
                        "                }\n" +
                        "                else if((tipoCana == 1) && (tipoPalhada == 1)){\n" +
                        "                    tipoTalhao = 3L;\n" +
                        "                }\n" +
                        "                pcqContext.getFormularioCTR().setTipoTalhao(tipoTalhao, talhaoItemBean);", getLocalClassName());
                Long tipoTalhao = 0L;
                if((tipoCana == 1) && (tipoPalhada == 0)){
                    tipoTalhao = 1L;
                }
                else if((tipoCana == 0) && (tipoPalhada == 1)){
                    tipoTalhao = 2L;
                }
                else if((tipoCana == 1) && (tipoPalhada == 1)){
                    tipoTalhao = 3L;
                }
                pcqContext.getFormularioCTR().setTipoTalhao(tipoTalhao, talhaoItemBean);
                if(tipoTalhao == 1L){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(tipoTalhao == 1L){\n" +
                            "                    Intent it = new Intent( TipoTalhaoActivity.this, HaIncCanaActivity.class);", getLocalClassName());
                    Intent it = new Intent( TipoTalhaoActivity.this, HaIncCanaActivity.class);
                    startActivity(it);
                    finish();
                } else if(tipoTalhao == 2L){
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if(tipoTalhao == 2L){\n" +
                            "                    Intent it = new Intent( TipoTalhaoActivity.this, HaIncPalhadaActivity.class);", getLocalClassName());
                    Intent it = new Intent( TipoTalhaoActivity.this, HaIncPalhadaActivity.class);
                    startActivity(it);
                    finish();
                } else if(tipoTalhao == 3L){
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if(tipoTalhao == 3L){\n" +
                            "                    Intent it = new Intent( TipoTalhaoActivity.this, HaIncCanaActivity.class);", getLocalClassName());
                    Intent it = new Intent( TipoTalhaoActivity.this, HaIncCanaActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

    public void onBackPressed() {
    }

}