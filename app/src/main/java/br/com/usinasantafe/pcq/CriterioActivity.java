package br.com.usinasantafe.pcq;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.bean.estaticas.QuestaoBean;
import br.com.usinasantafe.pcq.bean.estaticas.RespBean;

public class CriterioActivity extends ActivityGeneric {

    private List respostaList;
    private List questaoList;
    private PCQContext pcqContext;
    private QuestaoBean questaoBean;
    private RadioGroup radioGroupItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criterio);

        pcqContext = (PCQContext) getApplication();

        TextView textViewCriterio = (TextView) findViewById(R.id.textViewCriterio);
        TextView textViewTituloCriterio = (TextView) findViewById(R.id.textViewTituloCriterio);
        radioGroupItem = (RadioGroup) findViewById(R.id.radioGroupItem);

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled}, //disabled
                        new int[]{android.R.attr.state_enabled} //enabled
                },
                new int[] {
                        Color.BLACK //disabled
                        ,Color.BLUE //enabled
                }
        );

        textViewTituloCriterio.setText("CRITÉRIO " + pcqContext.getFormularioCTR().getPosCriterio());

        questaoBean = new QuestaoBean();
        questaoList = questaoBean.orderBy("seqQuestao",true);
        questaoBean = (QuestaoBean) questaoList.get(pcqContext.getFormularioCTR().getPosCriterio() - 1);

        textViewCriterio.setText(questaoBean.getDescrQuestao());

        RespBean respBean = new RespBean();
        respostaList = respBean.get("idQuestao", questaoBean.getIdQuestao());

        ArrayList<ViewHolderChoice> itens = new ArrayList<ViewHolderChoice>();

        for (int i = 0; i < respostaList.size(); i++) {
            respBean = (RespBean) respostaList.get(i);
            RadioButton radioButtonItem = new RadioButton(getApplicationContext());
            radioButtonItem.setText(respBean.getDescrResp());
            radioButtonItem.setTextColor(Color.BLACK);
            radioButtonItem.setTextSize(20F);
            radioButtonItem.setButtonTintList(colorStateList);
            radioGroupItem.addView(radioButtonItem);
        }

        radioGroupItem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                itemSelected(radioGroup.indexOfChild(radioButton));
            }
        });

    }


    public void itemSelected(int pos){
        RespBean respBean = (RespBean) respostaList.get(pos);
        pcqContext.getFormularioCTR().setItemBean(questaoBean.getIdQuestao(), respBean.getIdResp());
        List subRespList = respBean.get("idQuestao", respBean.getIdSubResp());
        if(subRespList.size() == 0){
            pcqContext.getFormularioCTR().salvarItem(0L);
            if(questaoList.size() > pcqContext.getFormularioCTR().getPosCriterio()) {
                pcqContext.getFormularioCTR().setPosCriterio(pcqContext.getFormularioCTR().getPosCriterio() + 1);
                Intent it = new Intent(CriterioActivity.this, CriterioActivity.class);
                startActivity(it);
                finish();
            }
            else{
                pcqContext.getFormularioCTR().fecharCabec();
                Intent it = new Intent(CriterioActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }
        }
        else{
            Intent it = new Intent(CriterioActivity.this, StatusCriterioActivity.class);
            startActivity(it);
            finish();
        }
        questaoList.clear();
        subRespList.clear();
    }

}
