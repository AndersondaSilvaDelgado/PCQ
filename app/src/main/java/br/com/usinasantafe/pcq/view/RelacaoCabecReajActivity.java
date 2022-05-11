package br.com.usinasantafe.pcq.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class RelacaoCabecReajActivity extends ActivityGeneric {

    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacao_cabec_reaj);

        pcqContext = (PCQContext) getApplication();
        Button buttonTermCabecReaj = (Button) findViewById(R.id.buttonTermCabecReaj);
        Button buttonRetCabecReaj = (Button) findViewById(R.id.buttonRetCabecReaj);

        CabecBean cabecBean = pcqContext.getFormularioCTR().cabecRecebidoList().get(pcqContext.getFormularioCTR().getPosCabecReaj());

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("FORMULÁRIO " + cabecBean.getIdExtCabec());
        itens.add("DATA/HORA " + Tempo.getInstance().dataHoraCTZ(cabecBean.getDthrCabec()));
        itens.add("COLABORADOR:\n" + pcqContext.getFormularioCTR().getIdFuncColab(cabecBean.getIdFuncCabec()).getMatricColab() + " - "
                + pcqContext.getFormularioCTR().getIdFuncColab(cabecBean.getIdFuncCabec()).getNomeColab());
        itens.add("TIPO APONTAMENTO DE TRABALHO:\n" + pcqContext.getFormularioCTR().getTipoApont(cabecBean.getTipoApontTrabCabec()).getDescrTipoApont());
        itens.add("ORIGEM DO FOGO:\n" + pcqContext.getFormularioCTR().getOrigemFogo(cabecBean.getOrigemFogoCabec()).getDescrOrigemFogo());
        itens.add("SEÇÃO:\n" + pcqContext.getFormularioCTR().getIdSecao(cabecBean.getSecaoCabec()).getCodSecao() + " - "
                + pcqContext.getFormularioCTR().getIdSecao(cabecBean.getSecaoCabec()).getDescrSecao());

        AdapterList adapterList = new AdapterList(this, itens);
        ListView listaViewCabecReaj = (ListView) findViewById(R.id.listaViewCabecReaj);
        listaViewCabecReaj.setAdapter(adapterList);

        buttonTermCabecReaj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                pcqContext.getFormularioCTR().setPosCriterio(1);
                pcqContext.setTipoTela(3);
                Intent it = new Intent(RelacaoCabecReajActivity.this, CriterioActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetCabecReaj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(RelacaoCabecReajActivity.this, ListaFormReajActivity.class);
                startActivity(it);
                finish();

            }

        });

    }
}