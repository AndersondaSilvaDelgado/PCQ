package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class RelacaoCriterioActivity extends ActivityGeneric {

    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacao_criterio);

        pcqContext = (PCQContext) getApplication();
        Button buttonAvancarCriteiro = findViewById(R.id.buttonAvancarCriteiro);
        Button buttonExcluirCriteiro = findViewById(R.id.buttonExcluirCriteiro);

        pcqContext = (PCQContext) getApplication();

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaViewInforCriteiro = findViewById(R.id.listaViewInforCriteiro);\n" +
                "        AdapterListCriterio adapterListCriterio = new AdapterListCriterio(this, pcqContext.getFormularioCTR().respItemList());\n" +
                "        listaViewInforCriteiro.setAdapter(adapterListCriterio);", getLocalClassName());
        ListView listaViewInforCriteiro = findViewById(R.id.listaViewInforCriteiro);
        AdapterListCriterio adapterListCriterio = new AdapterListCriterio(this, pcqContext.getFormularioCTR().respItemList());
        listaViewInforCriteiro.setAdapter(adapterListCriterio);
        listaViewInforCriteiro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                LogProcessoDAO.getInstance().insertLogProcesso("listaViewInforCriteiro.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                pcqContext.getFormularioCTR().setPosCriterio(position + 1);\n" +
                        "                Intent it = new Intent(RelacaoCriterioActivity.this, CriterioActivity.class);", getLocalClassName());
                pcqContext.getFormularioCTR().setPosCriterio(position + 1);
                Intent it = new Intent(RelacaoCriterioActivity.this, CriterioActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonAvancarCriteiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancarCriteiro.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                pcqContext.getFormularioCTR().finalizarCabecParaEnvio();\n" +
                        "                Intent it = new Intent(RelacaoCriterioActivity.this, MenuInicialActivity.class);", getLocalClassName());
                pcqContext.getFormularioCTR().finalizarCabecParaEnvio(getLocalClassName());
                Intent it = new Intent(RelacaoCriterioActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonExcluirCriteiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonExcluirCriteiro.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(RelacaoCriterioActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE EXCLUIR TODOS OS DADOS DO FORMULÁRIO?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(RelacaoCriterioActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE EXCLUIR TODOS OS DADOS DO FORMULÁRIO?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {\n" +
                                "                        pcqContext.getFormularioCTR().excluirItemCabec();\n" +
                                "                        Intent it = new Intent(RelacaoCriterioActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                        pcqContext.getFormularioCTR().excluirItemCabec();
                        Intent it = new Intent(RelacaoCriterioActivity.this, RelacaoCabecActivity.class);
                        startActivity(it);
                        finish();
                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }
                });

                alerta.show();

            }

        });

    }

    public void onBackPressed() {
    }

}