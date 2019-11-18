package br.com.usinasantafe.pcq;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HaIncendioActivity extends ActivityGeneric {

    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ha_incendio);

        pcqContext = (PCQContext) getApplication();

        TextView textViewPadrao = (TextView) findViewById(R.id.textViewPadrao);
        Button buttonOkPadrao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancPadrao = (Button) findViewById(R.id.buttonCancPadrao);

        switch (pcqContext.getPosMsg()){
            case 1:
                textViewPadrao.setText("ÁREA QUEIMADA DE CANA(HA):");
                break;
            case 2:
                textViewPadrao.setText("ÁREA QUEIMADA DE PALHADA(HA):");
                break;
            case 3:
                textViewPadrao.setText("ÁREA QUEIMADA DE RESERVA LEGAL(HA):");
                break;
            case 4:
                textViewPadrao.setText("ÁREA QUEIMADA DE APP(HA):");
                break;
            case 5:
                textViewPadrao.setText("ÁREA QUEIMADA DE ÁREA COMUM(HA):");
                break;
        }

        buttonOkPadrao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {
                    String haIncendioString = editTextPadrao.getText().toString();
                    Double haIncendioDouble = Double.valueOf(haIncendioString.replace(",", "."));

                    if (haIncendioDouble > 0) {

                        switch (pcqContext.getPosMsg()){
                            case 1:
                                pcqContext.getFormularioCTR().getCabecBean().setHaIncCanaCabec(haIncendioDouble);
                                break;
                            case 2:
                                pcqContext.getFormularioCTR().getCabecBean().setHaIncPalhadaCabec(haIncendioDouble);
                                break;
                            case 3:
                                pcqContext.getFormularioCTR().getCabecBean().setHaIncResLegalCabec(haIncendioDouble);
                                break;
                            case 4:
                                pcqContext.getFormularioCTR().getCabecBean().setHaIncAppCabec(haIncendioDouble);
                                break;
                            case 5:
                                pcqContext.getFormularioCTR().getCabecBean().setHaIncAreaComumCabec(haIncendioDouble);
                                break;
                        }


                        pcqContext.setPosMsg(pcqContext.getPosMsg() + 1);

                        Intent it = new Intent(HaIncendioActivity.this, MsgActivity.class);
                        startActivity(it);
                        finish();

                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncendioActivity.this);
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
                    AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncendioActivity.this);
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
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(HaIncendioActivity.this, MsgActivity.class);
        startActivity(it);
        finish();
    }

}
