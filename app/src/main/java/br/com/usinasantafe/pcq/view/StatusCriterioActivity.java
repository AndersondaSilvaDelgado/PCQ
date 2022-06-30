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

import java.util.List;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.bean.estaticas.QuestaoBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.RespBean;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class StatusCriterioActivity extends ActivityGeneric {

    private List<RespBean> subRespList;
    private PCQContext pcqContext;
    private RadioGroup radioGroupStatus;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_criterio);

        pcqContext = (PCQContext) getApplication();

        TextView textViewStatusCriterio = findViewById(R.id.textViewStatusCriterio);
        TextView textViewTituloStatusCriterio = findViewById(R.id.textViewTituloStatusCriterio);
        Button buttonRetStatusCriterio = findViewById(R.id.buttonRetStatusCriterio);
        Button buttonAvancaStatusCriterio = findViewById(R.id.buttonAvancaStatusCriterio);
        radioGroupStatus = findViewById(R.id.radioGroupStatus);

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_enabled}
                },
                new int[] {
                        Color.BLACK
                        ,Color.BLUE
                }
        );

        textViewTituloStatusCriterio.setText("STATUS");

        LogProcessoDAO.getInstance().insertLogProcesso("posicao = -1;\n" +
                "        RespBean respBean = pcqContext.getFormularioCTR().getResp(pcqContext.getFormularioCTR().getIdResp());\n" +
                "        textViewStatusCriterio.setText(respBean.getDescrResp());\n" +
                "        subRespList = pcqContext.getFormularioCTR().respIdQuestaoList(respBean.getIdSubResp());\n" +
                "        for (RespBean subRespBean : subRespList) {\n" +
                "            RadioButton radioButtonStatus = new RadioButton(getApplicationContext());\n" +
                "            radioButtonStatus.setText(subRespBean.getDescrResp());\n" +
                "            radioButtonStatus.setTextColor(Color.BLACK);\n" +
                "            radioButtonStatus.setTextSize(22F);\n" +
                "            radioButtonStatus.setButtonTintList(colorStateList);\n" +
                "            radioGroupStatus.addView(radioButtonStatus);\n" +
                "        }", getLocalClassName());
        posicao = -1;

        RespBean respBean = pcqContext.getFormularioCTR().getResp(pcqContext.getFormularioCTR().getIdResp());
        textViewStatusCriterio.setText(respBean.getDescrResp());
        subRespList = pcqContext.getFormularioCTR().respIdQuestaoList(respBean.getIdSubResp());

        for (RespBean subRespBean : subRespList) {
            RadioButton radioButtonStatus = new RadioButton(getApplicationContext());
            radioButtonStatus.setText(subRespBean.getDescrResp());
            radioButtonStatus.setTextColor(Color.BLACK);
            radioButtonStatus.setTextSize(22F);
            radioButtonStatus.setButtonTintList(colorStateList);
            radioGroupStatus.addView(radioButtonStatus);
        }

        radioGroupStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                LogProcessoDAO.getInstance().insertLogProcesso("radioGroupStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {\n" +
                        "            @Override\n" +
                        "            public void onCheckedChanged(RadioGroup radioGroup, int i) {\n" +
                        "                View radioButton = radioGroup.findViewById(i);\n" +
                        "                posicao = radioGroup.indexOfChild(radioButton);", getLocalClassName());
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonRetStatusCriterio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetStatusCriterio.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(StatusCriterioActivity.this, CriterioActivity.class);", getLocalClassName());
                Intent it = new Intent(StatusCriterioActivity.this, CriterioActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAvancaStatusCriterio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancaStatusCriterio.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(posicao > -1){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(posicao > -1){\n" +
                            "                    RespBean respBean = subRespList.get(posicao);\n" +
                            "                    pcqContext.getFormularioCTR().salvarAtualizarItem(respBean.getIdResp());\n" +
                            "                    List<QuestaoBean> questaoList = pcqContext.getFormularioCTR().questaoList();", getLocalClassName());
                    RespBean respBean = subRespList.get(posicao);
                    pcqContext.getFormularioCTR().salvarAtualizarItem(respBean.getIdResp());
                    List<QuestaoBean> questaoList = pcqContext.getFormularioCTR().questaoList();
                    if(pcqContext.getFormularioCTR().verCabecFinalizado()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecFinalizado()) {", getLocalClassName());
                        if (questaoList.size() > pcqContext.getFormularioCTR().getPosCriterio()) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (questaoList.size() > pcqContext.getFormularioCTR().getPosCriterio()) {\n" +
                                    "                            pcqContext.getFormularioCTR().setPosCriterio(pcqContext.getFormularioCTR().getPosCriterio() + 1);\n" +
                                    "                            Intent it = new Intent(StatusCriterioActivity.this, CriterioActivity.class);", getLocalClassName());
                            pcqContext.getFormularioCTR().setPosCriterio(pcqContext.getFormularioCTR().getPosCriterio() + 1);
                            Intent it = new Intent(StatusCriterioActivity.this, CriterioActivity.class);
                            startActivity(it);
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            pcqContext.getFormularioCTR().finalizarCabecItem();\n" +
                                    "                            Intent it = new Intent(StatusCriterioActivity.this, RelacaoCriterioActivity.class);", getLocalClassName());
                            pcqContext.getFormularioCTR().finalizarCabecItem();
                            Intent it = new Intent(StatusCriterioActivity.this, RelacaoCriterioActivity.class);
                            startActivity(it);
                        }
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent(StatusCriterioActivity.this, RelacaoCriterioActivity.class);", getLocalClassName());
                        Intent it = new Intent(StatusCriterioActivity.this, RelacaoCriterioActivity.class);
                        startActivity(it);
                    }
                    LogProcessoDAO.getInstance().insertLogProcesso("questaoList.clear();", getLocalClassName());
                    questaoList.clear();
                    finish();
                }
            }
        });

    }

    public void onBackPressed() {
    }

}
