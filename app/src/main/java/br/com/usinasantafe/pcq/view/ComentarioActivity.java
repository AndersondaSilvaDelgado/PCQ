package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class ComentarioActivity extends ActivityGeneric {

    private PCQContext pcqContext;
    private EditText editTextComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        pcqContext = (PCQContext) getApplication();

        Button buttonRetComentario = findViewById(R.id.buttonRetComentario);
        Button buttonAvancaComentario = findViewById(R.id.buttonAvancaComentario);
        editTextComentario = findViewById(R.id.editTextComentario);

        buttonRetComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetComentario.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcqContext.getFormularioCTR().verCabecAberto()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                            "                    Intent it = new Intent(ComentarioActivity.this, AceiroVegetNativaActivity.class);", getLocalClassName());
                    Intent it = new Intent(ComentarioActivity.this, AceiroVegetNativaActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(ComentarioActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                    Intent it = new Intent(ComentarioActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonAvancaComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancaComentario.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (!editTextComentario.getText().toString().equals("")) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextComentario.getText().toString().equals(\"\")) {\n" +
                            "                    pcqContext.getFormularioCTR().setComentCabec(editTextComentario.getText().toString());\n" +
                            "                    pcqContext.getFormularioCTR().finalizarCabec();\n" +
                            "                    Intent it = new Intent(ComentarioActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                    pcqContext.getFormularioCTR().setComentCabec(editTextComentario.getText().toString());
                    pcqContext.getFormularioCTR().finalizarCabec();
                    Intent it = new Intent(ComentarioActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();

                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder( ComentarioActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"POR FAVOR! DIGITE AS AÇÕES QUE FORAM TOMADAS PARA COMBATER O INCÊNDIO.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder( ComentarioActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! DIGITE AS AÇÕES QUE FORAM TOMADAS PARA COMBATER O INCÊNDIO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
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
