package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.bean.estaticas.BrigadistaBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.BrigadistaItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.EquipItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.TalhaoItemBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class RelacaoCabecalhoActivity extends ActivityGeneric {

    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacao_cabecalho);

        pcqContext = (PCQContext) getApplication();
        Button buttonAvancarCabec = (Button) findViewById(R.id.buttonAvancarCabec);
        Button buttonRetornarCabec = (Button) findViewById(R.id.buttonRetornarCabec);

        CabecBean cabecBean = new CabecBean();
        cabecBean = pcqContext.getFormularioCTR().getCabecAbert();

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("DATA/HORA:\n" + Tempo.getInstance().dataHoraCTZ(cabecBean.getDthrCabec()));
        itens.add("COLABORADOR:\n" + pcqContext.getFormularioCTR().getIdFuncColab(cabecBean.getIdFuncCabec()).getMatricColab() + " - "
                        + pcqContext.getFormularioCTR().getIdFuncColab(cabecBean.getIdFuncCabec()).getNomeColab());
        itens.add("TIPO APONTAMENTO DE TRABALHO:\n" + pcqContext.getFormularioCTR().getTipoApont(cabecBean.getTipoApontTrabCabec()).getDescrTipoApont());
        itens.add("ORIGEM DO FOGO:\n" + pcqContext.getFormularioCTR().getOrigemFogo(cabecBean.getOrigemFogoCabec()).getDescrOrigemFogo());
        itens.add("SEÇÃO:\n" + pcqContext.getFormularioCTR().getIdSecao(cabecBean.getSecaoCabec()).getCodSecao() + " - "
                        + pcqContext.getFormularioCTR().getIdSecao(cabecBean.getSecaoCabec()).getDescrSecao());

        String talhoes = "";
        int i = 0;
        List<TalhaoItemBean> talhaoItemList = pcqContext.getFormularioCTR().talhaoItemCabecAbertoList();
        for (TalhaoItemBean talhaoItemBean : talhaoItemList){
            if(i == 0){
                talhoes = "" + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao();
            }
            else{
                talhoes = " - " + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao();
            }
            i++;
        }
        talhaoItemList.clear();

        itens.add("TALHÃO(ÕES) DE CANAVIAL E/OU PALHADA:\n" + talhoes);
        itens.add("QTDE DE FOTOS CANAVIAL: " + pcqContext.getFormularioCTR().getListFotoCabecAbert(1L).size());
        itens.add("HA APP: " + cabecBean.getHaIncAppCabec());
        itens.add("HA FORA APP: " + cabecBean.getHaIncForaAppCabec());
        itens.add("QTDE DE FOTOS APP OU FORA APP: " + pcqContext.getFormularioCTR().getListFotoCabecAbert(2L).size());

        String tanques = "";
        i = 0;
        List<EquipItemBean> tanqueItemList = pcqContext.getFormularioCTR().tanqueItemList(cabecBean.getIdCabec());
        for (EquipItemBean equipItemBean : tanqueItemList){
            if(i == 0){
                tanques = "" + pcqContext.getFormularioCTR().getEquip(equipItemBean.getIdEquip()).getNroEquip();
            }
            else{
                tanques = " - " + pcqContext.getFormularioCTR().getEquip(equipItemBean.getIdEquip()).getNroEquip();
            }
            i++;
        }
        tanqueItemList.clear();

        itens.add("TANQUE(S):\n" + tanques);

        String saveiros = "";
        i = 0;
        List<EquipItemBean> saveiroItemList = pcqContext.getFormularioCTR().saveiroItemList(cabecBean.getIdCabec());
        for (EquipItemBean equipItemBean : saveiroItemList){
            if(i == 0){
                saveiros = "" + pcqContext.getFormularioCTR().getEquip(equipItemBean.getIdEquip()).getNroEquip();
            }
            else{
                saveiros = " - " + pcqContext.getFormularioCTR().getEquip(equipItemBean.getIdEquip()).getNroEquip();
            }
            i++;
        }
        saveiroItemList.clear();

        itens.add("SAVEIRO(S):\n" + saveiros);

        String brigadistas = "";
        i = 0;
        List<BrigadistaItemBean> brigadistaItemList = pcqContext.getFormularioCTR().brigadistaItemList(cabecBean.getIdCabec());
        for (BrigadistaItemBean brigadistaItemBean : brigadistaItemList){
            if(i == 0){
                brigadistas = "" + pcqContext.getFormularioCTR().getBrigadista(brigadistaItemBean.getIdFunc()).getMatricBrigadista();
            }
            else{
                brigadistas = " - " + pcqContext.getFormularioCTR().getBrigadista(brigadistaItemBean.getIdFunc()).getMatricBrigadista();
            }
            i++;
        }
        brigadistaItemList.clear();

        itens.add("BRIGADISTAS:\n" + brigadistas);
        itens.add("TERCEIRO COMBATE:\n" + pcqContext.getFormularioCTR().getTercComb(cabecBean.getTercCombCabec()).getDescrTercComb());
        if(cabecBean.getAceiroCanavialCabec() == 1){
            itens.add("CONDIÇÕES DO ACEIRO - CANAVIAL (CARREADOR): MAIOR QUE 3 m");
        }
        else{
            itens.add("CONDIÇÕES DO ACEIRO - CANAVIAL (CARREADOR): MENOR QUE 3 m");
        }

        if(cabecBean.getAceiroVegetNativalCabec() == 1){
            itens.add("CONDIÇÕES DO ACEIRO - CANAVIAL (CARREADOR): MAIOR QUE 3 m");
        }
        else{
            itens.add("CONDIÇÕES DO ACEIRO - CANAVIAL (CARREADOR): MENOR QUE 3 m");
        }

        itens.add("COMENTÁRIO:\n" + cabecBean.getComentCabec());

        AdapterList adapterList = new AdapterList(this, itens);
        ListView inforListView = (ListView) findViewById(R.id.listaViewInforCabec);
        inforListView.setAdapter(adapterList);

        buttonAvancarCabec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(RelacaoCabecalhoActivity.this, RelacaoCriterioActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonRetornarCabec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(RelacaoCabecalhoActivity.this, ComentarioActivity.class);
                startActivity(it);
                finish();
            }

        });

    }
}