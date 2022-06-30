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
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

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

        talhaoItemList = pcqContext.getFormularioCTR().talhaoItemList();
        talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);

        textViewTituloTipoCana.setText("TALHÃO " + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao() + "\n" +
                                            "TIPO CANA");

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
                "        radioButtonItem.setText(\"ALTURA MENOR 1,5 m\");\n" +
                "        radioButtonItem.setTextColor(Color.BLACK);\n" +
                "        radioButtonItem.setTextSize(22F);\n" +
                "        radioButtonItem.setButtonTintList(colorStateList);\n" +
                "        radioGroupItemTipoApont.addView(radioButtonItem);", getLocalClassName());
        RadioButton radioButtonItem = new RadioButton(getApplicationContext());
        radioButtonItem.setText("ALTURA MENOR 1,5 m");
        radioButtonItem.setTextColor(Color.BLACK);
        radioButtonItem.setTextSize(22F);
        radioButtonItem.setButtonTintList(colorStateList);
        radioGroupItemTipoApont.addView(radioButtonItem);

        LogProcessoDAO.getInstance().insertLogProcesso("RadioButton radioButtonItem2 = new RadioButton(getApplicationContext());\n" +
                "        radioButtonItem2.setText(\"ALTURA MAIOR QUE 1,5 m\");\n" +
                "        radioButtonItem2.setTextColor(Color.BLACK);\n" +
                "        radioButtonItem2.setTextSize(22F);\n" +
                "        radioButtonItem2.setButtonTintList(colorStateList);\n" +
                "        radioGroupItemTipoApont.addView(radioButtonItem2);", getLocalClassName());
        RadioButton radioButtonItem2 = new RadioButton(getApplicationContext());
        radioButtonItem2.setText("ALTURA MAIOR QUE 1,5 m");
        radioButtonItem2.setTextColor(Color.BLACK);
        radioButtonItem2.setTextSize(22F);
        radioButtonItem2.setButtonTintList(colorStateList);
        radioGroupItemTipoApont.addView(radioButtonItem2);

        radioGroupItemTipoApont.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                LogProcessoDAO.getInstance().insertLogProcesso("radioGroupItemTipoApont.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {\n" +
                        "            @Override\n" +
                        "            public void onCheckedChanged(RadioGroup radioGroup, int i) {\n" +
                        "                View radioButton = radioGroup.findViewById(i);\n" +
                        "                posicao = radioGroup.indexOfChild(radioButton);", getLocalClassName());
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonRetTipoCana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetTipoCana.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(AltCanavialActivity.this, HaIncCanaActivity.class);", getLocalClassName());
                Intent it = new Intent(AltCanavialActivity.this, HaIncCanaActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAvancaTipoApont.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancaTipoApont.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(posicao > -1){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(posicao > -1){\n" +
                            "                    Long pos = posicao + 1L;\n" +
                            "                    pcqContext.getFormularioCTR().setAltCanaTalhao(pos, talhaoItemBean);", getLocalClassName());
                    Long pos = posicao + 1L;
                    pcqContext.getFormularioCTR().setAltCanaTalhao(pos, talhaoItemBean);
                    if(talhaoItemBean.getTipoTalhao() == 1L){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(talhaoItemBean.getTipoTalhao() == 1L){", getLocalClassName());
                        if(pcqContext.getFormularioCTR().getPosTalhao() == talhaoItemList.size()){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().getPosTalhao() == talhaoItemList.size()){", getLocalClassName());
                            if(pcqContext.getFormularioCTR().verCabecAberto()){
                                LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                                        "                                pcqContext.setPosCameraTela(1);\n" +
                                        "                                Intent it = new Intent( AltCanavialActivity.this, CameraActivity.class);", getLocalClassName());
                                pcqContext.setPosCameraTela(1);
                                Intent it = new Intent( AltCanavialActivity.this, CameraActivity.class);
                                startActivity(it);
                            } else {
                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                Intent it = new Intent( AltCanavialActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                                Intent it = new Intent( AltCanavialActivity.this, RelacaoCabecActivity.class);
                                startActivity(it);
                            }
                        } else {
                            pcqContext.getFormularioCTR().setPosTalhao(pcqContext.getFormularioCTR().getPosTalhao() + 1);
                            Intent it = new Intent( AltCanavialActivity.this, TipoTalhaoActivity.class);
                            startActivity(it);
                        }
                        talhaoItemList.clear();
                        finish();
                    } else if(talhaoItemBean.getTipoTalhao() == 3L){
                        LogProcessoDAO.getInstance().insertLogProcesso("} else if(talhaoItemBean.getTipoTalhao() == 3L){\n" +
                                "                        Intent it = new Intent( AltCanavialActivity.this, HaIncPalhadaActivity.class);", getLocalClassName());
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