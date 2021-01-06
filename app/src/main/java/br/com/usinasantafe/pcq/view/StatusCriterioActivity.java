package br.com.usinasantafe.pcq.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.bean.estaticas.QuestaoBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.RespBean;

public class StatusCriterioActivity extends ActivityGeneric {

    private List subRespList;
    private PCQContext pcqContext;
    private RadioGroup radioGroupStatus;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_criterio);

        pcqContext = (PCQContext) getApplication();

        TextView textViewStatusCriterio = (TextView) findViewById(R.id.textViewStatusCriterio);
        TextView textViewTituloStatusCriterio = (TextView) findViewById(R.id.textViewTituloStatusCriterio);
        Button buttonRetStatusCriterio = (Button) findViewById(R.id.buttonRetStatusCriterio);
        Button buttonAvancaStatusCriterio = (Button) findViewById(R.id.buttonAvancaStatusCriterio);
        radioGroupStatus = (RadioGroup) findViewById(R.id.radioGroupStatus);

        posicao = -1;

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

        textViewTituloStatusCriterio.setText("STATUS");

        RespBean respBean = new RespBean();
        List respostaList = respBean.get("idResp", pcqContext.getFormularioCTR().getIdResp());
        respBean = (RespBean) respostaList.get(0);

        textViewStatusCriterio.setText(respBean.getDescrResp());

        RespBean subRespBean = new RespBean();
        subRespList = subRespBean.get("idQuestao", respBean.getIdSubResp());

        ArrayList<ViewHolderChoice> itens = new ArrayList<ViewHolderChoice>();

        for (int i = 0; i < subRespList.size(); i++) {
            respBean = (RespBean) subRespList.get(i);
            RadioButton radioButtonStatus = new RadioButton(getApplicationContext());
            radioButtonStatus.setText(respBean.getDescrResp());
            radioButtonStatus.setTextColor(Color.BLACK);
            radioButtonStatus.setTextSize(22F);
            radioButtonStatus.setButtonTintList(colorStateList);
            radioGroupStatus.addView(radioButtonStatus);
        }

        radioGroupStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonRetStatusCriterio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(StatusCriterioActivity.this, CriterioActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAvancaStatusCriterio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(posicao > -1){
                    RespBean respBean = (RespBean) subRespList.get(posicao);
                    pcqContext.getFormularioCTR().salvarItem(respBean.getIdResp());
                    QuestaoBean questaoBean = new QuestaoBean();
                    List questaoList = questaoBean.orderBy("seqQuestao",true);
                    if(questaoList.size() > pcqContext.getFormularioCTR().getPosCriterio()) {
                        pcqContext.getFormularioCTR().setPosCriterio(pcqContext.getFormularioCTR().getPosCriterio() + 1);
                        Intent it = new Intent(StatusCriterioActivity.this, CriterioActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        pcqContext.getFormularioCTR().fecharCabec();
                        Intent it = new Intent(StatusCriterioActivity.this, MenuInicialActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }
        });

    }


}
