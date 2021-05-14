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
import br.com.usinasantafe.pcq.model.bean.variaveis.TalhaoItemBean;

public class AltCanavialActivity extends ActivityGeneric {

    private List<TalhaoItemBean> talhaoItemList;
    private PCQContext pcqContext;
    private TalhaoItemBean talhaoItemBean;
    private RadioGroup radioGroupItemTipoApont;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt_canavial);

        pcqContext = (PCQContext) getApplication();

        TextView textViewTituloTipoCana = (TextView) findViewById(R.id.textViewTituloTipoCana);
        Button buttonRetTipoCana = (Button) findViewById(R.id.buttonRetTipoCana);
        Button buttonAvancaTipoApont = (Button) findViewById(R.id.buttonAvancaTipoCana);
        radioGroupItemTipoApont = (RadioGroup) findViewById(R.id.radioGroupItemTipoCana);

        talhaoItemList = pcqContext.getFormularioCTR().talhaoItemList(pcqContext.getTipoTela());
        talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);

        textViewTituloTipoCana.setText("TALHÃO " + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao() + "\n" +
                                            "TIPO CANA");

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
        radioButtonItem.setText("ALTURA MENOR 1,5 m");
        radioButtonItem.setTextColor(Color.BLACK);
        radioButtonItem.setTextSize(22F);
        radioButtonItem.setButtonTintList(colorStateList);
        radioGroupItemTipoApont.addView(radioButtonItem);

        RadioButton radioButtonItem2 = new RadioButton(getApplicationContext());
        radioButtonItem2.setText("ALTURA MAIOR QUE 1,5 m");
        radioButtonItem2.setTextColor(Color.BLACK);
        radioButtonItem2.setTextSize(22F);
        radioButtonItem2.setButtonTintList(colorStateList);
        radioGroupItemTipoApont.addView(radioButtonItem2);

        radioGroupItemTipoApont.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonRetTipoCana.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(AltCanavialActivity.this, HaIncCanaActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAvancaTipoApont.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(posicao > -1){

                    Long pos = posicao + 1L;
                    pcqContext.getFormularioCTR().setAltCanaTalhao(pos, talhaoItemBean);

                    if(talhaoItemBean.getTipoTalhao() == 1L){

                        if(pcqContext.getFormularioCTR().getPosTalhao() == talhaoItemList.size()){
                            if(pcqContext.getTipoTela() == 1){
                                pcqContext.setPosCameraTela(1);
                                Intent it = new Intent( AltCanavialActivity.this, CameraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                Intent it = new Intent( AltCanavialActivity.this, RelacaoCabecActivity.class);
                                startActivity(it);
                                finish();
                            }
                        }
                        else{
                            pcqContext.getFormularioCTR().setPosTalhao(pcqContext.getFormularioCTR().getPosTalhao() + 1);
                            Intent it = new Intent( AltCanavialActivity.this, TipoTalhaoActivity.class);
                            startActivity(it);
                            finish();
                        }
                        talhaoItemList.clear();

                    }
                    else if(talhaoItemBean.getTipoTalhao() == 3L){
                        Intent it = new Intent( AltCanavialActivity.this, HaIncPalhadaActivity.class);
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