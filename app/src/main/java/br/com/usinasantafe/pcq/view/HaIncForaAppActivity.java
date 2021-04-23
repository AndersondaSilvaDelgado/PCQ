package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;

public class HaIncForaAppActivity extends ActivityGeneric {

    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ha_inc_fora_app);

        pcqContext = (PCQContext) getApplication();

        Button buttonOkPadrao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancPadrao = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkPadrao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    String haIncendioString = editTextPadrao.getText().toString();
                    Double haIncendioDouble = Double.valueOf(haIncendioString.replace(",", "."));

                    if (haIncendioDouble > 0) {

                        pcqContext.getFormularioCTR().setHaIncForaAppCabec(haIncendioDouble);

                        Intent it = new Intent(HaIncForaAppActivity.this, MsgCameraActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncForaAppActivity.this);
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

                    pcqContext.getFormularioCTR().setHaIncForaAppCabec(0D);

                    Intent it = new Intent(HaIncForaAppActivity.this, HaIncForaAppActivity.class);
                    startActivity(it);
                    finish();

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
        Intent it = new Intent(HaIncForaAppActivity.this, HaIncAppActivity.class);
        startActivity(it);
        finish();
    }

}