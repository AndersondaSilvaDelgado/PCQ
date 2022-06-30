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

public class CriterioActivity extends ActivityGeneric {

    private List<RespBean> respostaList;
    private List<QuestaoBean> questaoList;
    private PCQContext pcqContext;
    private QuestaoBean questaoBean;
    private RadioGroup radioGroupItem;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criterio);

        pcqContext = (PCQContext) getApplication();

        TextView textViewCriterio = findViewById(R.id.textViewCriterio);
        TextView textViewTituloCriterio = findViewById(R.id.textViewTituloCriterio);
        Button buttonRetCriterio = findViewById(R.id.buttonRetCriterio);
        Button buttonAvancaCriterio = findViewById(R.id.buttonAvancaCriterio);
        radioGroupItem = findViewById(R.id.radioGroupItem);

        textViewTituloCriterio.setText("CRITÉRIO " + pcqContext.getFormularioCTR().getPosCriterio());

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

        LogProcessoDAO.getInstance().insertLogProcesso("posicao = -1;", getLocalClassName());
        posicao = -1;

        LogProcessoDAO.getInstance().insertLogProcesso("questaoBean = new QuestaoBean();\n" +
                "        questaoList = pcqContext.getFormularioCTR().questaoList();\n" +
                "        questaoBean = questaoList.get(pcqContext.getFormularioCTR().getPosCriterio() - 1);\n" +
                "        textViewCriterio.setText(questaoBean.getDescrQuestao());", getLocalClassName());
        questaoBean = new QuestaoBean();
        questaoList = pcqContext.getFormularioCTR().questaoList();
        questaoBean = questaoList.get(pcqContext.getFormularioCTR().getPosCriterio() - 1);

        textViewCriterio.setText(questaoBean.getDescrQuestao());

        LogProcessoDAO.getInstance().insertLogProcesso("respostaList = pcqContext.getFormularioCTR().respIdQuestaoList(questaoBean.getIdQuestao());\n" +
                "        for (RespBean respBean : respostaList) {\n" +
                "            RadioButton radioButtonItem = new RadioButton(getApplicationContext());\n" +
                "            radioButtonItem.setText(respBean.getDescrResp());\n" +
                "            radioButtonItem.setTextColor(Color.BLACK);\n" +
                "            radioButtonItem.setTextSize(22F);\n" +
                "            radioButtonItem.setButtonTintList(colorStateList);\n" +
                "            radioGroupItem.addView(radioButtonItem);\n" +
                "        }", getLocalClassName());
        respostaList = pcqContext.getFormularioCTR().respIdQuestaoList(questaoBean.getIdQuestao());
        for (RespBean respBean : respostaList) {
            RadioButton radioButtonItem = new RadioButton(getApplicationContext());
            radioButtonItem.setText(respBean.getDescrResp());
            radioButtonItem.setTextColor(Color.BLACK);
            radioButtonItem.setTextSize(22F);
            radioButtonItem.setButtonTintList(colorStateList);
            radioGroupItem.addView(radioButtonItem);
        }

        radioGroupItem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                LogProcessoDAO.getInstance().insertLogProcesso("radioGroupItem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {\n" +
                        "            @Override\n" +
                        "            public void onCheckedChanged(RadioGroup radioGroup, int i) {\n" +
                        "                View radioButton = radioGroup.findViewById(i);\n" +
                        "                posicao = radioGroup.indexOfChild(radioButton);", getLocalClassName());
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonRetCriterio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetCriterio.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcqContext.getFormularioCTR().verCabecFinalizado()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecFinalizado()){", getLocalClassName());
                    if(pcqContext.getFormularioCTR().getPosCriterio() > 1){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().getPosCriterio() > 1){\n" +
                                "                        pcqContext.getFormularioCTR().setPosCriterio(pcqContext.getFormularioCTR().getPosCriterio() - 1);\n" +
                                "                        Intent it = new Intent(CriterioActivity.this, CriterioActivity.class);", getLocalClassName());
                        pcqContext.getFormularioCTR().setPosCriterio(pcqContext.getFormularioCTR().getPosCriterio() - 1);
                        Intent it = new Intent(CriterioActivity.this, CriterioActivity.class);
                        startActivity(it);
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent(CriterioActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                        Intent it = new Intent(CriterioActivity.this, RelacaoCabecActivity.class);
                        startActivity(it);
                    }
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(CriterioActivity.this, RelacaoCriterioActivity.class);", getLocalClassName());
                    Intent it = new Intent(CriterioActivity.this, RelacaoCriterioActivity.class);
                    startActivity(it);
                }
                finish();

            }
        });

        buttonAvancaCriterio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancaCriterio.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(posicao > -1){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(posicao > -1){\n" +
                            "                    RespBean respBean = respostaList.get(posicao);\n" +
                            "                    pcqContext.getFormularioCTR().setItemBean(questaoBean.getIdQuestao(), respBean.getIdResp(), questaoBean.getSeqQuestao());\n" +
                            "                    List<RespBean> subRespList = pcqContext.getFormularioCTR().respIdQuestaoList(respBean.getIdSubResp());", getLocalClassName());
                    RespBean respBean = respostaList.get(posicao);
                    pcqContext.getFormularioCTR().setItemBean(questaoBean.getIdQuestao(), respBean.getIdResp(), questaoBean.getSeqQuestao());
                    List<RespBean> subRespList = pcqContext.getFormularioCTR().respIdQuestaoList(respBean.getIdSubResp());
                    if(subRespList.size() == 0){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(subRespList.size() == 0){\n" +
                                "                        pcqContext.getFormularioCTR().salvarAtualizarItem(0L);", getLocalClassName());
                        pcqContext.getFormularioCTR().salvarAtualizarItem(0L);
                        if(pcqContext.getFormularioCTR().verCabecFinalizado()){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecFinalizado()){", getLocalClassName());
                            if(questaoList.size() > pcqContext.getFormularioCTR().getPosCriterio()) {
                                LogProcessoDAO.getInstance().insertLogProcesso("if(questaoList.size() > pcqContext.getFormularioCTR().getPosCriterio()) {\n" +
                                        "                                pcqContext.getFormularioCTR().setPosCriterio(pcqContext.getFormularioCTR().getPosCriterio() + 1);\n" +
                                        "                                Intent it = new Intent(CriterioActivity.this, CriterioActivity.class);", getLocalClassName());
                                pcqContext.getFormularioCTR().setPosCriterio(pcqContext.getFormularioCTR().getPosCriterio() + 1);
                                Intent it = new Intent(CriterioActivity.this, CriterioActivity.class);
                                startActivity(it);
                            } else {
                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                pcqContext.getFormularioCTR().finalizarCabecItem();\n" +
                                        "                                Intent it = new Intent(CriterioActivity.this, RelacaoCriterioActivity.class);", getLocalClassName());
                                pcqContext.getFormularioCTR().finalizarCabecItem();
                                Intent it = new Intent(CriterioActivity.this, RelacaoCriterioActivity.class);
                                startActivity(it);
                            }
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            Intent it = new Intent(CriterioActivity.this, RelacaoCriterioActivity.class);", getLocalClassName());
                            Intent it = new Intent(CriterioActivity.this, RelacaoCriterioActivity.class);
                            startActivity(it);
                        }
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent(CriterioActivity.this, StatusCriterioActivity.class);", getLocalClassName());
                        Intent it = new Intent(CriterioActivity.this, StatusCriterioActivity.class);
                        startActivity(it);
                    }
                    questaoList.clear();
                    subRespList.clear();
                    finish();
                }
            }
        });

    }

    public void onBackPressed() {
    }

}
