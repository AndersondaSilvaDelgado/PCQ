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

public class AceiroCanavialActivity extends ActivityGeneric {

    private PCQContext pcqContext;
    private RadioGroup radioGroupItemAceiroCanavial;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceiro_canavial);

        pcqContext = (PCQContext) getApplication();

        Button buttonRetAceiroCanavial = (Button) findViewById(R.id.buttonRetAceiroCanavial);
        Button buttonAvancaAceiroCanavial = (Button) findViewById(R.id.buttonAvancaAceiroCanavial);
        radioGroupItemAceiroCanavial = (RadioGroup) findViewById(R.id.radioGroupItemAceiroCanavial);

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
        radioButtonItem.setText("MAIOR QUE 3 m");
        radioButtonItem.setTextColor(Color.BLACK);
        radioButtonItem.setTextSize(22F);
        radioButtonItem.setButtonTintList(colorStateList);
        radioGroupItemAceiroCanavial.addView(radioButtonItem);

        RadioButton radioButtonItem2 = new RadioButton(getApplicationContext());
        radioButtonItem2.setText("MENOR QUE 3 m");
        radioButtonItem2.setTextColor(Color.BLACK);
        radioButtonItem2.setTextSize(22F);
        radioButtonItem2.setButtonTintList(colorStateList);
        radioGroupItemAceiroCanavial.addView(radioButtonItem2);

        radioGroupItemAceiroCanavial.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonRetAceiroCanavial.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent( AceiroCanavialActivity.this, MsgCameraActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAvancaAceiroCanavial.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(posicao > -1){

                    Long pos = posicao + 1L;
                    pcqContext.getFormularioCTR().setAceiroCanavialCabec(pos);

                    Intent it = new Intent( AceiroCanavialActivity.this, AceiroVegetNativaActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

    }

}