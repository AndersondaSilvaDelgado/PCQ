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

public class AceiroVegetNativaActivity extends ActivityGeneric {

    private PCQContext pcqContext;
    private RadioGroup radioGroupItemAceiroVegetNativa;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceiro_veget_nativa);

        pcqContext = (PCQContext) getApplication();

        Button buttonRetAceiroVegetNativa = (Button) findViewById(R.id.buttonRetAceiroVegetNativa);
        Button buttonAvancaAceiroVegetNativa = (Button) findViewById(R.id.buttonAvancaAceiroVegetNativa);
        radioGroupItemAceiroVegetNativa = (RadioGroup) findViewById(R.id.radioGroupItemAceiroVegetNativa);

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

        RadioButton radioButtonItem = new RadioButton(getApplicationContext());
        radioButtonItem.setText("MAIOR QUE 6 m");
        radioButtonItem.setTextColor(Color.BLACK);
        radioButtonItem.setTextSize(22F);
        radioButtonItem.setButtonTintList(colorStateList);
        radioGroupItemAceiroVegetNativa.addView(radioButtonItem);

        RadioButton radioButtonItem2 = new RadioButton(getApplicationContext());
        radioButtonItem2.setText("MENOR QUE 6 m");
        radioButtonItem2.setTextColor(Color.BLACK);
        radioButtonItem2.setTextSize(22F);
        radioButtonItem2.setButtonTintList(colorStateList);
        radioGroupItemAceiroVegetNativa.addView(radioButtonItem2);

        radioGroupItemAceiroVegetNativa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonRetAceiroVegetNativa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pcqContext.getTipoTela() == 1){
                    Intent it = new Intent(AceiroVegetNativaActivity.this, AceiroCanavialActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent( AceiroVegetNativaActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonAvancaAceiroVegetNativa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(posicao > -1){

                    Long pos = posicao + 1L;
                    pcqContext.getFormularioCTR().setAceiroVegetNativalCabec(pos, pcqContext.getTipoTela());

                    if(pcqContext.getTipoTela() == 1){
                        Intent it = new Intent( AceiroVegetNativaActivity.this, ComentarioActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Intent it = new Intent( AceiroVegetNativaActivity.this, RelacaoCabecActivity.class);
                        startActivity(it);
                        finish();
                    }

                }

            }
        });

    }
}