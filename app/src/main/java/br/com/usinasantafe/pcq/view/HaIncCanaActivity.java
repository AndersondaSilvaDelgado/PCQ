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
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class HaIncCanaActivity extends ActivityGeneric {

    private List<TalhaoItemBean> talhaoItemList;
    private PCQContext pcqContext;
    private TalhaoItemBean talhaoItemBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ha_inc_cana);

        pcqContext = (PCQContext) getApplication();

        TextView textViewPadrao = findViewById(R.id.textViewPadrao);
        Button buttonOkPadrao = findViewById(R.id.buttonOkPadrao);
        Button buttonCancPadrao = findViewById(R.id.buttonCancPadrao);

        LogProcessoDAO.getInstance().insertLogProcesso("talhaoItemList = pcqContext.getFormularioCTR().talhaoItemList();\n" +
                "        talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);\n" +
                "        talhaoItemList.clear();\n" +
                "        textViewPadrao.setText(\"TALHÃO \" + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao() + \"\\n\" +\n" +
                "                                \"ÁREA QUEIMADA DE CANA(HA)\");", getLocalClassName());
        talhaoItemList = pcqContext.getFormularioCTR().talhaoItemList();
        talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);
        talhaoItemList.clear();

        textViewPadrao.setText("TALHÃO " + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao() + "\n" +
                                "ÁREA QUEIMADA DE CANA(HA)");

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
                                "                        pcqContext.getFormularioCTR().setHaIncCanaTalhao(haIncendioDouble, talhaoItemBean);\n" +
                                "                        Intent it = new Intent(HaIncCanaActivity.this, AltCanavialActivity.class);", getLocalClassName());
                        pcqContext.getFormularioCTR().setHaIncCanaTalhao(haIncendioDouble, talhaoItemBean);
                        Intent it = new Intent(HaIncCanaActivity.this, AltCanavialActivity.class);
                        startActivity(it);
                        finish();

                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncCanaActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"POR FAVOR, DIGITE A QUANTIDADE DE AREA QUEIMADA DE CANA EM HECTARE!\");", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncCanaActivity.this);
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
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncCanaActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"POR FAVOR, DIGITE A QUANTIDADE DE AREA QUEIMADA DE CANA EM HECTARE!\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncCanaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR, DIGITE A QUANTIDADE DE AREA QUEIMADA DE CANA EM HECTARE!");
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
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {\n" +
                "        Intent it = new Intent(HaIncCanaActivity.this, TipoTalhaoActivity.class);", getLocalClassName());
        Intent it = new Intent(HaIncCanaActivity.this, TipoTalhaoActivity.class);
        startActivity(it);
        finish();
    }

}