package br.com.usinasantafe.pcq;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pcq.bean.estaticas.ColabBean;

public class BrigadistaActivity extends ActivityGeneric {

    private PCQContext pcqContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brigadista);

        pcqContext = (PCQContext) getApplication();

        Button buttonOkBrigadista = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancBrigadista = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkBrigadista.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    pcqContext.getFormularioCTR().getCabecBean().setQtdeBrigadistaCabec(Long.parseLong(editTextPadrao.getText().toString()));

                    Intent it = new Intent(BrigadistaActivity.this, MsgActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(BrigadistaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR, DIGITE A QUANTIDADE DE BRIGADISTAS QUE AUXILIARAM NO COMBATE AO INCÊNDIO!");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alerta.show();

                }

            }

        });

        buttonCancBrigadista.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(BrigadistaActivity.this, MsgActivity.class);
        startActivity(it);
        finish();
    }

}
