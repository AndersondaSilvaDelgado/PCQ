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

public class RelacaoCriterioActivity extends ActivityGeneric {

    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacao_criterio);

        pcqContext = (PCQContext) getApplication();
        Button buttonAvancarCriteiro = (Button) findViewById(R.id.buttonAvancarCriteiro);
        Button buttonRetornarCriteiro = (Button) findViewById(R.id.buttonRetornarCriteiro);
        Button buttonExcluirCriteiro = (Button) findViewById(R.id.buttonExcluirCriteiro);

        pcqContext = (PCQContext) getApplication();

        if(pcqContext.getFormularioCTR().verCabecFechado()){
            pcqContext.setTipoTela(2);
        }
        else{
            pcqContext.setTipoTela(4);
        }

        ListView listaViewInforCriteiro = (ListView) findViewById(R.id.listaViewInforCriteiro);
        AdapterListCriterio adapterListCriterio = new AdapterListCriterio(this, pcqContext.getFormularioCTR().respItemList(pcqContext.getTipoTela()));
        listaViewInforCriteiro.setAdapter(adapterListCriterio);

        listaViewInforCriteiro.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pcqContext.getFormularioCTR().setPosCriterio(position + 1);
                Intent it = new Intent(RelacaoCriterioActivity.this, CriterioActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonAvancarCriteiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pcqContext.getFormularioCTR().verCabecFechado()) {
                    pcqContext.getFormularioCTR().finalizarCabec();
                }
                else{
                    pcqContext.getFormularioCTR().finalRecebidoCabec();
                }
                Intent it = new Intent(RelacaoCriterioActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonExcluirCriteiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String texto;
                if(pcqContext.getFormularioCTR().verCabecFechado()) {
                    texto = "DESEJA REALMENTE EXCLUIR TODOS OS DADOS DO FORMULÁRIO?";
                }
                else{
                    texto = "DESEJA REALMENTE EXCLUIR TODOS AS RESPOSTAS DO FORMULÁRIO RECEBIDO?";
                }

                AlertDialog.Builder alerta = new AlertDialog.Builder(RelacaoCriterioActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage(texto);
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(pcqContext.getFormularioCTR().verCabecFechado()) {
                            pcqContext.getFormularioCTR().delFormFechado();
                        }
                        else{
                            pcqContext.getFormularioCTR().delItemFechadoRecebido();
                        }

                        Intent it = new Intent(RelacaoCriterioActivity.this, MenuInicialActivity.class);
                        startActivity(it);
                        finish();

                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }

        });

        buttonRetornarCriteiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pcqContext.getTipoTela() < 3){
                    Intent it = new Intent(RelacaoCriterioActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }

    public void onBackPressed() {
    }

}