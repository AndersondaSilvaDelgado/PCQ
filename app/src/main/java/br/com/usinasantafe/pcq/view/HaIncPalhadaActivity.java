package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.bean.variaveis.TalhaoItemBean;

public class HaIncPalhadaActivity extends ActivityGeneric {

    private List<TalhaoItemBean> talhaoItemList;
    private PCQContext pcqContext;
    private TalhaoItemBean talhaoItemBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ha_inc_palhada);

        pcqContext = (PCQContext) getApplication();

        TextView textViewPadrao = (TextView) findViewById(R.id.textViewPadrao);
        Button buttonOkPadrao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancPadrao = (Button) findViewById(R.id.buttonCancPadrao);

        talhaoItemList = pcqContext.getFormularioCTR().talhaoItemList(pcqContext.getTipoTela());
        talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);

        textViewPadrao.setText("TALHÃO " + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao() + "\n" +
                "ÁREA QUEIMADA DE PALHADA(HA)");

        buttonOkPadrao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    String haIncendioString = editTextPadrao.getText().toString();
                    Double haIncendioDouble = Double.valueOf(haIncendioString.replace(",", "."));

                    if (haIncendioDouble > 0) {

                        pcqContext.getFormularioCTR().setHaIncPalhadaTalhao(haIncendioDouble, talhaoItemBean);

                        if(pcqContext.getFormularioCTR().getPosTalhao() == talhaoItemList.size()){
                            if(pcqContext.getTipoTela() == 1){
                                pcqContext.setPosCameraTela(1);
                                Intent it = new Intent(HaIncPalhadaActivity.this, CameraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                Intent it = new Intent(HaIncPalhadaActivity.this, RelacaoCabecActivity.class);
                                startActivity(it);
                                finish();
                            }
                        }
                        else{
                            pcqContext.getFormularioCTR().setPosTalhao(pcqContext.getFormularioCTR().getPosTalhao() + 1);
                            Intent it = new Intent(HaIncPalhadaActivity.this, TipoTalhaoActivity.class);
                            startActivity(it);
                            finish();
                        }
                        talhaoItemList.clear();

                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncPalhadaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("POR FAVOR, DIGITE A QUANTIDADE DE AREA QUEIMADA DE CANA EM HECTARE!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();
                    }
                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncPalhadaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR, DIGITE A QUANTIDADE DE AREA QUEIMADA DE CANA EM HECTARE!");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }

                    });

                    alerta.show();
                }

            }
        });

        buttonCancPadrao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {

        if(talhaoItemBean.getTipoTalhao() == 2){
            Intent it = new Intent(HaIncPalhadaActivity.this, TipoTalhaoActivity.class);
            startActivity(it);
            finish();
        }
        else if(talhaoItemBean.getTipoTalhao() == 3){
            Intent it = new Intent( HaIncPalhadaActivity.this, AltCanavialActivity.class);
            startActivity(it);
            finish();
        }

    }
}