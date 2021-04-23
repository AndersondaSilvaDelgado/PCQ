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
import br.com.usinasantafe.pcq.model.bean.estaticas.TercCombBean;

public class TercCombActivity extends ActivityGeneric {

    private List<TercCombBean> tercCombList;
    private PCQContext pcqContext;
    private RadioGroup radioGroupItemTercComb;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terc_comb);

        pcqContext = (PCQContext) getApplication();

        Button buttonRetTercComb = (Button) findViewById(R.id.buttonRetTercComb);
        Button buttonAvancaTercComb = (Button) findViewById(R.id.buttonAvancaTercComb);
        radioGroupItemTercComb = (RadioGroup) findViewById(R.id.radioGroupItemTercComb);

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

        tercCombList = pcqContext.getFormularioCTR().tercCombList();

        for (TercCombBean tercCombBean : tercCombList) {
            RadioButton radioButtonItem = new RadioButton(getApplicationContext());
            radioButtonItem.setText(tercCombBean.getDescrTercComb());
            radioButtonItem.setTextColor(Color.BLACK);
            radioButtonItem.setTextSize(22F);
            radioButtonItem.setButtonTintList(colorStateList);
            radioGroupItemTercComb.addView(radioButtonItem);
        }

        radioGroupItemTercComb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonRetTercComb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(TercCombActivity.this, BrigadistaActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAvancaTercComb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TercCombBean tercCombBean = new TercCombBean();

                if(posicao > -1) {
                    tercCombBean.setIdTercComb(0L);
                }
                else {
                    tercCombBean = tercCombList.get(posicao);
                }

                pcqContext.getFormularioCTR().setTercCombCabec(tercCombBean.getIdTercComb());

                Intent it = new Intent(TercCombActivity.this, AceiroCanavialActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed() {
    }

}
