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

public class AceiroCanavialActivity extends ActivityGeneric {

    private PCQContext pcqContext;
    private RadioGroup radioGroupItemAceiroCanavial;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceiro_canavial);

        pcqContext = (PCQContext) getApplication();

        Button buttonRetAceiroCanavial = findViewById(R.id.buttonRetAceiroCanavial);
        Button buttonAvancaAceiroCanavial = findViewById(R.id.buttonAvancaAceiroCanavial);
        radioGroupItemAceiroCanavial = findViewById(R.id.radioGroupItemAceiroCanavial);

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
                "        radioButtonItem.setText(\"MAIOR QUE 3 m\");\n" +
                "        radioButtonItem.setTextColor(Color.BLACK);\n" +
                "        radioButtonItem.setTextSize(22F);\n" +
                "        radioButtonItem.setButtonTintList(colorStateList);\n" +
                "        radioGroupItemAceiroCanavial.addView(radioButtonItem);", getLocalClassName());
        RadioButton radioButtonItem = new RadioButton(getApplicationContext());
        radioButtonItem.setText("MAIOR QUE 3 m");
        radioButtonItem.setTextColor(Color.BLACK);
        radioButtonItem.setTextSize(22F);
        radioButtonItem.setButtonTintList(colorStateList);
        radioGroupItemAceiroCanavial.addView(radioButtonItem);

        LogProcessoDAO.getInstance().insertLogProcesso("RadioButton radioButtonItem2 = new RadioButton(getApplicationContext());\n" +
                "        radioButtonItem2.setText(\"MENOR QUE 3 m\");\n" +
                "        radioButtonItem2.setTextColor(Color.BLACK);\n" +
                "        radioButtonItem2.setTextSize(22F);\n" +
                "        radioButtonItem2.setButtonTintList(colorStateList);\n" +
                "        radioGroupItemAceiroCanavial.addView(radioButtonItem2);", getLocalClassName());
        RadioButton radioButtonItem2 = new RadioButton(getApplicationContext());
        radioButtonItem2.setText("MENOR QUE 3 m");
        radioButtonItem2.setTextColor(Color.BLACK);
        radioButtonItem2.setTextSize(22F);
        radioButtonItem2.setButtonTintList(colorStateList);
        radioGroupItemAceiroCanavial.addView(radioButtonItem2);

        radioGroupItemAceiroCanavial.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                LogProcessoDAO.getInstance().insertLogProcesso("radioGroupItemAceiroCanavial.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {\n" +
                        "            @Override\n" +
                        "            public void onCheckedChanged(RadioGroup radioGroup, int i) {\n" +
                        "                View radioButton = radioGroup.findViewById(i);\n" +
                        "                posicao = radioGroup.indexOfChild(radioButton);", getLocalClassName());
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonRetAceiroCanavial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetAceiroCanavial.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcqContext.getFormularioCTR().verCabecAberto()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                            "                    Intent it = new Intent( AceiroCanavialActivity.this, TercCombActivity.class);", getLocalClassName());
                    Intent it = new Intent( AceiroCanavialActivity.this, TercCombActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent( AceiroCanavialActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                    Intent it = new Intent( AceiroCanavialActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonAvancaAceiroCanavial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancaAceiroCanavial.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(posicao > -1){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(posicao > -1){\n" +
                            "                    Long pos = posicao + 1L;\n" +
                            "                    pcqContext.getFormularioCTR().setAceiroCanavialCabec(pos);", getLocalClassName());
                    Long pos = posicao + 1L;
                    pcqContext.getFormularioCTR().setAceiroCanavialCabec(pos);
                    if(pcqContext.getFormularioCTR().verCabecAberto()){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                                "                        Intent it = new Intent( AceiroCanavialActivity.this, AceiroVegetNativaActivity.class);", getLocalClassName());
                        Intent it = new Intent( AceiroCanavialActivity.this, AceiroVegetNativaActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent( AceiroCanavialActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                        Intent it = new Intent( AceiroCanavialActivity.this, RelacaoCabecActivity.class);
                        startActivity(it);
                        finish();
                    }

                }

            }
        });

    }

    public void onBackPressed() {
    }

}