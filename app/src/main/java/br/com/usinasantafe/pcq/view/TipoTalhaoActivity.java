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

        TextView textViewTituloStatusCanavial = (TextView) findViewById(R.id.textViewTituloStatusCanavial);
        Button buttonRetStatusCanavial = (Button) findViewById(R.id.buttonRetStatusCanavial);
        Button buttonSalvarStatusCanavial = (Button) findViewById(R.id.buttonSalvarStatusCanavial);

        pcqContext = (PCQContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

        talhaoItemList = pcqContext.getFormularioCTR().talhaoItemList(pcqContext.getTipoTela());
        talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);
        talhaoItemList.clear();

        textViewTituloStatusCanavial.setText("STATUS CANAVIAL\n" +
                                                "TALHÃO " + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao());

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
                if(pcqContext.getFormularioCTR().getPosTalhao() == 1){
                    Intent it = new Intent( TipoTalhaoActivity.this, TalhaoActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonSalvarStatusCanavial.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

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
                    Intent it = new Intent( TipoTalhaoActivity.this, HaIncCanaActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(tipoTalhao == 2L){
                    Intent it = new Intent( TipoTalhaoActivity.this, HaIncPalhadaActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(tipoTalhao == 3L){
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