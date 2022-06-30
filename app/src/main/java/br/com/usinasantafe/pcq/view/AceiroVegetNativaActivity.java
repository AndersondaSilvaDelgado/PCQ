package br.com.usinasantafe.pcq.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class AceiroVegetNativaActivity extends ActivityGeneric {

    private PCQContext pcqContext;
    private RadioGroup radioGroupItemAceiroVegetNativa;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceiro_veget_nativa);

        pcqContext = (PCQContext) getApplication();

        Button buttonRetAceiroVegetNativa = findViewById(R.id.buttonRetAceiroVegetNativa);
        Button buttonAvancaAceiroVegetNativa = findViewById(R.id.buttonAvancaAceiroVegetNativa);
        radioGroupItemAceiroVegetNativa = findViewById(R.id.radioGroupItemAceiroVegetNativa);

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

        LogProcessoDAO.getInstance().insertLogProcesso("posicao = -1;", getLocalClassName());
        posicao = -1;

        LogProcessoDAO.getInstance().insertLogProcesso("RadioButton radioButtonItem = new RadioButton(getApplicationContext());\n" +
                "        radioButtonItem.setText(\"MAIOR QUE 6 m\");\n" +
                "        radioButtonItem.setTextColor(Color.BLACK);\n" +
                "        radioButtonItem.setTextSize(22F);\n" +
                "        radioButtonItem.setButtonTintList(colorStateList);\n" +
                "        radioGroupItemAceiroVegetNativa.addView(radioButtonItem);", getLocalClassName());
        RadioButton radioButtonItem = new RadioButton(getApplicationContext());
        radioButtonItem.setText("MAIOR QUE 6 m");
        radioButtonItem.setTextColor(Color.BLACK);
        radioButtonItem.setTextSize(22F);
        radioButtonItem.setButtonTintList(colorStateList);
        radioGroupItemAceiroVegetNativa.addView(radioButtonItem);

        LogProcessoDAO.getInstance().insertLogProcesso("RadioButton radioButtonItem2 = new RadioButton(getApplicationContext());\n" +
                "        radioButtonItem2.setText(\"MENOR QUE 6 m\");\n" +
                "        radioButtonItem2.setTextColor(Color.BLACK);\n" +
                "        radioButtonItem2.setTextSize(22F);\n" +
                "        radioButtonItem2.setButtonTintList(colorStateList);\n" +
                "        radioGroupItemAceiroVegetNativa.addView(radioButtonItem2);", getLocalClassName());
        RadioButton radioButtonItem2 = new RadioButton(getApplicationContext());
        radioButtonItem2.setText("MENOR QUE 6 m");
        radioButtonItem2.setTextColor(Color.BLACK);
        radioButtonItem2.setTextSize(22F);
        radioButtonItem2.setButtonTintList(colorStateList);
        radioGroupItemAceiroVegetNativa.addView(radioButtonItem2);

        LogProcessoDAO.getInstance().insertLogProcesso("RadioButton radioButtonItem3 = new RadioButton(getApplicationContext());\n" +
                "        radioButtonItem3.setText(\"NÃO SE APLICA\");\n" +
                "        radioButtonItem3.setTextColor(Color.BLACK);\n" +
                "        radioButtonItem3.setTextSize(22F);\n" +
                "        radioButtonItem3.setButtonTintList(colorStateList);\n" +
                "        radioGroupItemAceiroVegetNativa.addView(radioButtonItem3);", getLocalClassName());
        RadioButton radioButtonItem3 = new RadioButton(getApplicationContext());
        radioButtonItem3.setText("NÃO SE APLICA");
        radioButtonItem3.setTextColor(Color.BLACK);
        radioButtonItem3.setTextSize(22F);
        radioButtonItem3.setButtonTintList(colorStateList);
        radioGroupItemAceiroVegetNativa.addView(radioButtonItem3);

        radioGroupItemAceiroVegetNativa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                LogProcessoDAO.getInstance().insertLogProcesso("radioGroupItemAceiroVegetNativa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {\n" +
                        "            @Override\n" +
                        "            public void onCheckedChanged(RadioGroup radioGroup, int i) {\n" +
                        "                View radioButton = radioGroup.findViewById(i);\n" +
                        "                posicao = radioGroup.indexOfChild(radioButton);", getLocalClassName());
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonRetAceiroVegetNativa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetAceiroVegetNativa.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcqContext.getFormularioCTR().verCabecAberto()){
                    Intent it = new Intent(AceiroVegetNativaActivity.this, AceiroCanavialActivity.class);
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                            "                    Intent it = new Intent(AceiroVegetNativaActivity.this, AceiroCanavialActivity.class);", getLocalClassName());
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent( AceiroVegetNativaActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                    Intent it = new Intent( AceiroVegetNativaActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonAvancaAceiroVegetNativa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancaAceiroVegetNativa.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(posicao > -1){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(posicao > -1){\n" +
                            "                    Long pos = posicao + 1L;\n" +
                            "                    pcqContext.getFormularioCTR().setAceiroVegetNativalCabec(pos);", getLocalClassName());
                    Long pos = posicao + 1L;
                    pcqContext.getFormularioCTR().setAceiroVegetNativalCabec(pos);
                    if(pcqContext.getFormularioCTR().verCabecAberto()){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                                "                        Intent it = new Intent( AceiroVegetNativaActivity.this, ComentarioActivity.class);", getLocalClassName());
                        Intent it = new Intent( AceiroVegetNativaActivity.this, ComentarioActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent( AceiroVegetNativaActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                        Intent it = new Intent( AceiroVegetNativaActivity.this, RelacaoCabecActivity.class);
                        startActivity(it);
                        finish();
                    }

                }

            }
        });

    }
}