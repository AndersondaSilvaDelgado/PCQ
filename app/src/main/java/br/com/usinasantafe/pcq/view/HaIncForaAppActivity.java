package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class HaIncForaAppActivity extends ActivityGeneric {

    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ha_inc_fora_app);

        pcqContext = (PCQContext) getApplication();

        Button buttonOkPadrao = findViewById(R.id.buttonOkPadrao);
        Button buttonCancPadrao = findViewById(R.id.buttonCancPadrao);

        buttonOkPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkPadrao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (!editTextPadrao.getText().toString().equals("")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                            "                    String haIncendioString = editTextPadrao.getText().toString();\n" +
                            "                    Double haIncendioDouble = Double.valueOf(haIncendioString.replace(\",\", \".\"));", getLocalClassName());
                    String haIncendioString = editTextPadrao.getText().toString();
                    Double haIncendioDouble = Double.valueOf(haIncendioString.replace(",", "."));
                    if (haIncendioDouble > 0) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (haIncendioDouble > 0) {\n" +
                                "                        pcqContext.getFormularioCTR().setHaIncForaAppCabec(haIncendioDouble);", getLocalClassName());
                        pcqContext.getFormularioCTR().setHaIncForaAppCabec(haIncendioDouble);
                        if(pcqContext.getFormularioCTR().verCabecAberto()){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                                    "                            Intent it = new Intent(HaIncForaAppActivity.this, MsgCameraActivity.class);", getLocalClassName());
                            Intent it = new Intent(HaIncForaAppActivity.this, MsgCameraActivity.class);
                            startActivity(it);
                            finish();
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            Intent it = new Intent(HaIncForaAppActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                            Intent it = new Intent(HaIncForaAppActivity.this, RelacaoCabecActivity.class);
                            startActivity(it);
                            finish();
                        }
                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncForaAppActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"POR FAVOR, DIGITE A QUANTIDADE DE AREA QUEIMADA DE CANA EM HECTARE!\");", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncForaAppActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("POR FAVOR, DIGITE A QUANTIDADE DE AREA QUEIMADA DE CANA EM HECTARE!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                            @Override\n" +
                                        "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                            }
                        });

                        alerta.show();
                    }
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    pcqContext.getFormularioCTR().setHaIncForaAppCabec(0D);", getLocalClassName());
                    pcqContext.getFormularioCTR().setHaIncForaAppCabec(0D);
                    if(pcqContext.getFormularioCTR().verCabecAberto()){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                                "                        Intent it = new Intent(HaIncForaAppActivity.this, TanqueActivity.class);", getLocalClassName());
                        Intent it = new Intent(HaIncForaAppActivity.this, TanqueActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent(HaIncForaAppActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                        Intent it = new Intent(HaIncForaAppActivity.this, RelacaoCabecActivity.class);
                        startActivity(it);
                        finish();
                    }
                }

            }
        });

        buttonCancPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancPadrao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (editTextPadrao.getText().toString().length() > 0) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (editTextPadrao.getText().toString().length() > 0) {\n" +
                            "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {", getLocalClassName());
        if(pcqContext.getFormularioCTR().verCabecAberto()){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                    "            Intent it = new Intent(HaIncForaAppActivity.this, HaIncAppActivity.class);", getLocalClassName());
            Intent it = new Intent(HaIncForaAppActivity.this, HaIncAppActivity.class);
            startActivity(it);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            Intent it = new Intent(HaIncForaAppActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
            Intent it = new Intent(HaIncForaAppActivity.this, RelacaoCabecActivity.class);
            startActivity(it);
        }
        finish();
    }

}