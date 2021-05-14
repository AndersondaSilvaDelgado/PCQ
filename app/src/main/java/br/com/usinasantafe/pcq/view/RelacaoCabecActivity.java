package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.bean.variaveis.BrigadistaItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.EquipItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.TalhaoItemBean;
import br.com.usinasantafe.pcq.util.Tempo;

public class RelacaoCabecActivity extends ActivityGeneric {

    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacao_cabec);

        pcqContext = (PCQContext) getApplication();
        Button buttonAvancarCabec = (Button) findViewById(R.id.buttonAvancarCabec);
        Button buttonExcluirCabec = (Button) findViewById(R.id.buttonExcluirCabec);

        CabecBean cabecBean = pcqContext.getFormularioCTR().getCabecFechado();

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
                talhoes = talhoes + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao();
            }
            else{
                talhoes = talhoes + " - " + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao();
            }
            i++;
        }
        talhaoItemList.clear();

        itens.add("TALHÃO(ÕES) DE CANAVIAL E/OU PALHADA:\n" + talhoes);
        itens.add("QTDE DE FOTOS CANAVIAL: " + pcqContext.getFormularioCTR().getListFotoCabecFechado(1L).size());
        itens.add("HA APP: " + cabecBean.getHaIncAppCabec());
        itens.add("HA FORA APP: " + cabecBean.getHaIncForaAppCabec());
        itens.add("QTDE DE FOTOS DE INCÊNDIO APP OU FORA APP: " + pcqContext.getFormularioCTR().getListFotoCabecFechado(2L).size());

        String tanques = "";
        i = 0;
        List<EquipItemBean> tanqueItemList = pcqContext.getFormularioCTR().tanqueItemList(cabecBean.getIdCabec());
        for (EquipItemBean equipItemBean : tanqueItemList){
            if(i == 0){
                tanques = tanques + pcqContext.getFormularioCTR().getEquip(equipItemBean.getIdEquip()).getNroEquip();
            }
            else{
                tanques = tanques + " - " + pcqContext.getFormularioCTR().getEquip(equipItemBean.getIdEquip()).getNroEquip();
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
                saveiros = saveiros + "" + pcqContext.getFormularioCTR().getEquip(equipItemBean.getIdEquip()).getNroEquip();
            }
            else{
                saveiros = saveiros + " - " + pcqContext.getFormularioCTR().getEquip(equipItemBean.getIdEquip()).getNroEquip();
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
                brigadistas = brigadistas + "" + pcqContext.getFormularioCTR().getBrigadista(brigadistaItemBean.getIdFunc()).getMatricBrigadista();
            }
            else{
                brigadistas = brigadistas + " - " + pcqContext.getFormularioCTR().getBrigadista(brigadistaItemBean.getIdFunc()).getMatricBrigadista();
            }
            i++;
        }
        brigadistaItemList.clear();

        itens.add("BRIGADISTAS:\n" + brigadistas);
        if(cabecBean.getTercCombCabec() > 0){
            itens.add("TERCEIRO COMBATE:\n" + pcqContext.getFormularioCTR().getTercComb(cabecBean.getTercCombCabec()).getDescrTercComb());
        }
        else{
            itens.add("TERCEIRO COMBATE:");
        }
        if(cabecBean.getAceiroCanavialCabec() == 1){
            itens.add("CONDIÇÕES DO ACEIRO - CANAVIAL (CARREADOR): MAIOR QUE 3 m");
        }
        else{
            itens.add("CONDIÇÕES DO ACEIRO - CANAVIAL (CARREADOR): MENOR QUE 3 m");
        }

        if(cabecBean.getAceiroVegetNativalCabec() == 1){
            itens.add("CONDIÇÕES DO ACEIRO - VEGETAÇÃO NATIVA: MAIOR QUE 6 m");
        }
        else{
            itens.add("CONDIÇÕES DO ACEIRO - VEGETAÇÃO NATIVA: MENOR QUE 6 m");
        }

        itens.add("COMENTÁRIO:\n" + cabecBean.getComentCabec());

        AdapterList adapterList = new AdapterList(this, itens);
        ListView inforListView = (ListView) findViewById(R.id.listaViewInforCabec);
        inforListView.setAdapter(adapterList);

        inforListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pcqContext.setTipoTela(2);
                Intent it;
                switch (position) {
                    case 1:
                        it = new Intent(RelacaoCabecActivity.this, ColabActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 2:
                        it = new Intent(RelacaoCabecActivity.this, TipoApontTrabActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 3:
                        it = new Intent(RelacaoCabecActivity.this, OrigemFogoActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 4:
                        it = new Intent(RelacaoCabecActivity.this, SecaoActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 5:
                        it = new Intent(RelacaoCabecActivity.this, SecaoActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 6:
                        pcqContext.setPosCameraTela(1);
                        it = new Intent(RelacaoCabecActivity.this, CameraActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 7:
                        it = new Intent(RelacaoCabecActivity.this, HaIncAppActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 8:
                        it = new Intent(RelacaoCabecActivity.this, HaIncForaAppActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 9:
                        pcqContext.setPosCameraTela(2);
                        it = new Intent(RelacaoCabecActivity.this, CameraActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 10:
                        it = new Intent(RelacaoCabecActivity.this, TanqueActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 11:
                        it = new Intent(RelacaoCabecActivity.this, SaveiroActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 12:
                        it = new Intent(RelacaoCabecActivity.this, BrigadistaActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 13:
                        it = new Intent(RelacaoCabecActivity.this, TercCombActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 14:
                        it = new Intent(RelacaoCabecActivity.this, AceiroCanavialActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 15:
                        it = new Intent(RelacaoCabecActivity.this, AceiroVegetNativaActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 16:
                        it = new Intent(RelacaoCabecActivity.this, ComentarioActivity.class);
                        startActivity(it);
                        finish();
                        break;
                }

            }

        });

        buttonAvancarCabec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(pcqContext.getFormularioCTR().getCabecFechado().getTipoCabec() == 1L){

                    Intent it = new Intent(RelacaoCabecActivity.this, RelacaoCriterioActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    pcqContext.getFormularioCTR().finalizarCabec();
                    Intent it = new Intent(RelacaoCabecActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        });

        buttonExcluirCabec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(RelacaoCabecActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE EXCLUIR TODOS OS DADOS DO FORMULÁRIO?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pcqContext.getFormularioCTR().delFormFechado();
                        Intent it = new Intent(RelacaoCabecActivity.this, MenuInicialActivity.class);
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

    }

    public void onBackPressed() {
    }

}