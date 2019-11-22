package br.com.usinasantafe.pcq;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ComentarioActivity extends ActivityGeneric {

    private PCQContext pcqContext;
    private EditText editTextComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        pcqContext = (PCQContext) getApplication();

        Button buttonRetComentario = (Button) findViewById(R.id.buttonRetComentario);
        Button buttonAvancaComentario = (Button) findViewById(R.id.buttonAvancaComentario);
        editTextComentario = (EditText) findViewById(R.id.editTextComentario);

        buttonRetComentario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(ComentarioActivity.this, MsgActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAvancaComentario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextComentario.getText().toString().equals("")) {

                    pcqContext.getFormularioCTR().setComentCabec(editTextComentario.getText().toString());
                    pcqContext.getFormularioCTR().setPosCriterio(1);

                    Intent it = new Intent(ComentarioActivity.this, CriterioActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder( ComentarioActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! DIGITE AS AÇÕES QUE FORAM TOMADAS PARA COMBATER O INCÊNDIO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                        }
                    });
                    alerta.show();
                }

            }

        });

    }

    public void onBackPressed() {
    }


}
