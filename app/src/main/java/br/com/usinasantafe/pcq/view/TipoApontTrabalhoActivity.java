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
import br.com.usinasantafe.pcq.model.bean.estaticas.TipoApontBean;

public class TipoApontTrabalhoActivity extends ActivityGeneric {

    private List<TipoApontBean> tipoApontList;
    private PCQContext pcqContext;
    private RadioGroup radioGroupItemTipoApont;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_apont_trabalho);

        pcqContext = (PCQContext) getApplication();

        Button buttonRetTipoApont = (Button) findViewById(R.id.buttonRetTipoApont);
        Button buttonAvancaTipoApont = (Button) findViewById(R.id.buttonAvancaTipoApont);
        radioGroupItemTipoApont = (RadioGroup) findViewById(R.id.radioGroupItemTipoApont);

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

        tipoApontList = pcqContext.getFormularioCTR().tipoApontList();

        for (TipoApontBean tipoApontBean : tipoApontList) {
            RadioButton radioButtonItem = new RadioButton(getApplicationContext());
            radioButtonItem.setText(tipoApontBean.getDescrTipoApont());
            radioButtonItem.setTextColor(Color.BLACK);
            radioButtonItem.setTextSize(22F);
            radioButtonItem.setButtonTintList(colorStateList);
            radioGroupItemTipoApont.addView(radioButtonItem);
        }

        radioGroupItemTipoApont.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonRetTipoApont.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(TipoApontTrabalhoActivity.this, ColabActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAvancaTipoApont.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(posicao > -1) {

                    TipoApontBean tipoApontBean = tipoApontList.get(posicao);
                    pcqContext.getFormularioCTR().setTipoApontTrabCabec(tipoApontBean.getIdTipoApont());
                    
                    Intent it = new Intent(TipoApontTrabalhoActivity.this, OrigemFogoActivity.class);
                    startActivity(it);
                    finish();

                }
            }
        });

    }
}