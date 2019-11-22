package br.com.usinasantafe.pcq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MsgActivity extends ActivityGeneric {

    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        pcqContext = (PCQContext) getApplication();

        TextView textViewMSG = (TextView) findViewById(R.id.textViewMSG);
        Button buttonSimMSG = (Button) findViewById(R.id.buttonSimMSG);
        Button buttonNaoMSG = (Button) findViewById(R.id.buttonNaoMSG);
        Button buttonRetMSG = (Button) findViewById(R.id.buttonRetMSG);

        switch (pcqContext.getFormularioCTR().getPosMsg()){
            case 1:
                textViewMSG.setText("HOUVE INCÊNDIO EM CANA?");
                break;
            case 2:
                textViewMSG.setText("HOUVE INCÊNDIO EM PALHADA?");
                break;
            case 3:
                textViewMSG.setText("HOUVE INCÊNDIO NA VEGETAÇÃO NATIVA - RESERVA LEGAL?");
                break;
            case 4:
                textViewMSG.setText("HOUVE INCÊNDIO NA VEGETAÇÃO NATIVA - APP?");
                break;
            case 5:
                textViewMSG.setText("HOUVE INCÊNDIO NA VEGETAÇÃO NATIVA - ÁREA COMUM?");
                break;
            case 6:
                textViewMSG.setText("FORAM UTILIZADOS CAMINHÕES TANQUE NO COMBATE AO INCÊNDIO?");
                break;
            case 7:
                textViewMSG.setText("FORAM UTILIZADOS SAVEIRO(S) NO COMBATE AO INCÊNDIO?");
                break;
            case 8:
                textViewMSG.setText("HOUVE AUXILIO DE BRIGADISTAS NO COMBATE AO INCÊNCIO?");
                break;
            case 9:
                textViewMSG.setText("HOUVE FISCALIZAÇÃO DE TERCEIROS?");
                break;
            case 10:
                textViewMSG.setText("HOUVE FISCALIZAÇÃO DE ALGUM ORGÃO AMBIENTAL?");
                break;
        }

        buttonSimMSG.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(pcqContext.getFormularioCTR().getPosMsg() < 6){
                    Intent it = new Intent(MsgActivity.this, HaIncendioActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pcqContext.getFormularioCTR().getPosMsg() == 6){
                    Intent it = new Intent(MsgActivity.this, TanqueActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pcqContext.getFormularioCTR().getPosMsg() == 7){
                    Intent it = new Intent(MsgActivity.this, SaveiroActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pcqContext.getFormularioCTR().getPosMsg() == 8){
                    Intent it = new Intent(MsgActivity.this, BrigadistaActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pcqContext.getFormularioCTR().getPosMsg() == 9){
                    Intent it = new Intent(MsgActivity.this, EmpresaTercActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pcqContext.getFormularioCTR().getPosMsg() == 10){
                    Intent it = new Intent(MsgActivity.this, OrgaoAmbActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonNaoMSG.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(pcqContext.getFormularioCTR().getPosMsg() == 10){
                    Intent it = new Intent(MsgActivity.this, ComentarioActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    pcqContext.getFormularioCTR().setPosMsg(pcqContext.getFormularioCTR().getPosMsg() + 1);
                    Intent it = new Intent(MsgActivity.this, MsgActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonRetMSG.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(pcqContext.getFormularioCTR().getPosMsg() == 1){
                    Intent it = new Intent(MsgActivity.this, TalhaoActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }

    public void onBackPressed() {
    }

}
