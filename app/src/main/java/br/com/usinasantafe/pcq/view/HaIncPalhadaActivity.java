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

public class HaIncPalhadaActivity extends ActivityGeneric {

    private List<TalhaoItemBean> talhaoItemList;
    private PCQContext pcqContext;
    private TalhaoItemBean talhaoItemBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ha_inc_palhada);

        pcqContext = (PCQContext) getApplication();

        TextView textViewPadrao = findViewById(R.id.textViewPadrao);
        Button buttonOkPadrao = findViewById(R.id.buttonOkPadrao);
        Button buttonCancPadrao = findViewById(R.id.buttonCancPadrao);

        LogProcessoDAO.getInstance().insertLogProcesso("talhaoItemList = pcqContext.getFormularioCTR().talhaoItemList();\n" +
                "        talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);\n" +
                "        textViewPadrao.setText(\"TALHÃO \" + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao() + \"\\n\" +\n" +
                "                \"ÁREA QUEIMADA DE PALHADA(HA)\");", getLocalClassName());
        talhaoItemList = pcqContext.getFormularioCTR().talhaoItemList();
        talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);

        textViewPadrao.setText("TALHÃO " + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao() + "\n" +
                "ÁREA QUEIMADA DE PALHADA(HA)");

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
                                "                        pcqContext.getFormularioCTR().setHaIncPalhadaTalhao(haIncendioDouble, talhaoItemBean);", getLocalClassName());
                        pcqContext.getFormularioCTR().setHaIncPalhadaTalhao(haIncendioDouble, talhaoItemBean);
                        if(pcqContext.getFormularioCTR().getPosTalhao() == talhaoItemList.size()){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().getPosTalhao() == talhaoItemList.size()){", getLocalClassName());
                            if(pcqContext.getFormularioCTR().verCabecAberto()){
                                LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                                        "                                pcqContext.setPosCameraTela(1);\n" +
                                        "                                Intent it = new Intent(HaIncPalhadaActivity.this, CameraActivity.class);", getLocalClassName());
                                pcqContext.setPosCameraTela(1);
                                Intent it = new Intent(HaIncPalhadaActivity.this, CameraActivity.class);
                                startActivity(it);
                            } else {
                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                Intent it = new Intent(HaIncPalhadaActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                                Intent it = new Intent(HaIncPalhadaActivity.this, RelacaoCabecActivity.class);
                                startActivity(it);
                            }
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            pcqContext.getFormularioCTR().setPosTalhao(pcqContext.getFormularioCTR().getPosTalhao() + 1);\n" +
                                    "                            Intent it = new Intent(HaIncPalhadaActivity.this, TipoTalhaoActivity.class);", getLocalClassName());
                            pcqContext.getFormularioCTR().setPosTalhao(pcqContext.getFormularioCTR().getPosTalhao() + 1);
                            Intent it = new Intent(HaIncPalhadaActivity.this, TipoTalhaoActivity.class);
                            startActivity(it);
                        }
                        talhaoItemList.clear();
                        finish();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncPalhadaActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"POR FAVOR, DIGITE A QUANTIDADE DE AREA QUEIMADA DE CANA EM HECTARE!\");", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncPalhadaActivity.this);
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
                    AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncPalhadaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR, DIGITE A QUANTIDADE DE AREA QUEIMADA DE CANA EM HECTARE!");
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(HaIncPalhadaActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"POR FAVOR, DIGITE A QUANTIDADE DE AREA QUEIMADA DE CANA EM HECTARE!\");", getLocalClassName());
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
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {", getLocalClassName());
        if(talhaoItemBean.getTipoTalhao() == 2){
            LogProcessoDAO.getInstance().insertLogProcesso("if(talhaoItemBean.getTipoTalhao() == 2){\n" +
                    "            Intent it = new Intent(HaIncPalhadaActivity.this, TipoTalhaoActivity.class);", getLocalClassName());
            Intent it = new Intent(HaIncPalhadaActivity.this, TipoTalhaoActivity.class);
            startActivity(it);
            finish();
        } else if(talhaoItemBean.getTipoTalhao() == 3){
            Intent it = new Intent( HaIncPalhadaActivity.this, AltCanavialActivity.class);
            LogProcessoDAO.getInstance().insertLogProcesso("if(talhaoItemBean.getTipoTalhao() == 2){\n" +
                    "            Intent it = new Intent(HaIncPalhadaActivity.this, TipoTalhaoActivity.class);", getLocalClassName());
            startActivity(it);
            finish();
        }

    }
}