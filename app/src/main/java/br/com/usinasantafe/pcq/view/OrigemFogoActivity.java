package br.com.usinasantafe.pcq.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.bean.estaticas.OrigemFogoBean;

public class OrigemFogoActivity extends ActivityGeneric {

    private List<OrigemFogoBean> origemFogoList;
    private PCQContext pcqContext;
    private RadioGroup radioGroupItemOrigemFogo;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origem_fogo);

        pcqContext = (PCQContext) getApplication();

        Button buttonRetOrigemFogo = (Button) findViewById(R.id.buttonRetOrigemFogo);
        Button buttonAvancaOrigemFogo = (Button) findViewById(R.id.buttonAvancaOrigemFogo);
        radioGroupItemOrigemFogo = (RadioGroup) findViewById(R.id.radioGroupItemOrigemFogo);

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

        origemFogoList = pcqContext.getFormularioCTR().origemFogoList();

        for (OrigemFogoBean origemFogoBean : origemFogoList) {
            RadioButton radioButtonItem = new RadioButton(getApplicationContext());
            radioButtonItem.setText(origemFogoBean.getDescrOrigemFogo());
            radioButtonItem.setTextColor(Color.BLACK);
            radioButtonItem.setTextSize(22F);
            radioButtonItem.setButtonTintList(colorStateList);
            radioGroupItemOrigemFogo.addView(radioButtonItem);
        }

        radioGroupItemOrigemFogo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonRetOrigemFogo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(OrigemFogoActivity.this, ColabActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAvancaOrigemFogo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(posicao > -1) {

                    OrigemFogoBean origemFogoBean = origemFogoList.get(posicao);
                    pcqContext.getFormularioCTR().setOrigemFogoCabec(origemFogoBean.getIdOrigemFogo());

                    Intent it = new Intent(OrigemFogoActivity.this, SecaoActivity.class);
                    startActivity(it);
                    finish();

                }
            }
        });

    }

    public void onBackPressed() {
    }

}