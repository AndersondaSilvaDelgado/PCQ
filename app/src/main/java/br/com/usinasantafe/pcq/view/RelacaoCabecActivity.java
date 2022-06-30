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
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class RelacaoCabecActivity extends ActivityGeneric {

    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacao_cabec);

        pcqContext = (PCQContext) getApplication();
        Button buttonAvancarCabec = findViewById(R.id.buttonAvancarCabec);
        Button buttonExcluirCabec = findViewById(R.id.buttonExcluirCabec);

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> itens;\n" +
                "        if(pcqContext.getFormularioCTR().getCabecFinalizado().getTipoCabec() == 3L){\n" +
                "            itens = exibirCabecRecebido();\n" +
                "        } else {\n" +
                "            itens = exibirCabecSimplicadoCompleto();\n" +
                "        }", getLocalClassName());
        ArrayList<String> itens;
        if(pcqContext.getFormularioCTR().getCabecFinalizado().getTipoCabec() == 3L){
            itens = exibirCabecRecebido();
        } else {
            itens = exibirCabecSimplicadoCompleto();
        }

        LogProcessoDAO.getInstance().insertLogProcesso("AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        ListView inforListView = findViewById(R.id.listaViewInforCabec);\n" +
                "        inforListView.setAdapter(adapterList);", getLocalClassName());
        AdapterList adapterList = new AdapterList(this, itens);
        ListView inforListView = findViewById(R.id.listaViewInforCabec);
        inforListView.setAdapter(adapterList);
        inforListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                LogProcessoDAO.getInstance().insertLogProcesso("inforListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {", getLocalClassName());
                if(pcqContext.getFormularioCTR().getCabecFinalizado().getTipoCabec() < 3L){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().getCabecFinalizado().getTipoCabec() < 3L){", getLocalClassName());
                    Intent it;
                    switch (position) {
                        case 1:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 1:\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, ColabActivity.class);", getLocalClassName());
                            it = new Intent(RelacaoCabecActivity.this, ColabActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 2:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 2:\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, TipoApontTrabActivity.class);", getLocalClassName());
                            it = new Intent(RelacaoCabecActivity.this, TipoApontTrabActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 3:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 3:\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, OrigemFogoActivity.class);", getLocalClassName());
                            it = new Intent(RelacaoCabecActivity.this, OrigemFogoActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 4:
                        case 5:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 4:\n" +
                                    "                        case 5:\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, SecaoActivity.class);", getLocalClassName());
                            it = new Intent(RelacaoCabecActivity.this, SecaoActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 6:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 6:\n" +
                                    "                            pcqContext.setPosCameraTela(1);\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, CameraActivity.class);", getLocalClassName());
                            pcqContext.setPosCameraTela(1);
                            it = new Intent(RelacaoCabecActivity.this, CameraActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 7:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 7:\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, HaIncAppActivity.class);", getLocalClassName());
                            it = new Intent(RelacaoCabecActivity.this, HaIncAppActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 8:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 8:\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, HaIncForaAppActivity.class);", getLocalClassName());
                            it = new Intent(RelacaoCabecActivity.this, HaIncForaAppActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 9:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 9:\n" +
                                    "                            pcqContext.setPosCameraTela(2);\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, CameraActivity.class);", getLocalClassName());
                            pcqContext.setPosCameraTela(2);
                            it = new Intent(RelacaoCabecActivity.this, CameraActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 10:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 10:\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, TanqueActivity.class);", getLocalClassName());
                            it = new Intent(RelacaoCabecActivity.this, TanqueActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 11:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 11:\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, SaveiroActivity.class);", getLocalClassName());
                            it = new Intent(RelacaoCabecActivity.this, SaveiroActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 12:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 12:\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, BrigadistaActivity.class);", getLocalClassName());
                            it = new Intent(RelacaoCabecActivity.this, BrigadistaActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 13:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 13:\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, TercCombActivity.class);", getLocalClassName());
                            it = new Intent(RelacaoCabecActivity.this, TercCombActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 14:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 14:\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, AceiroCanavialActivity.class);", getLocalClassName());
                            it = new Intent(RelacaoCabecActivity.this, AceiroCanavialActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 15:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 15:\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, AceiroVegetNativaActivity.class);", getLocalClassName());
                            it = new Intent(RelacaoCabecActivity.this, AceiroVegetNativaActivity.class);
                            startActivity(it);
                            finish();
                            break;
                        case 16:
                            LogProcessoDAO.getInstance().insertLogProcesso("case 16:\n" +
                                    "                            it = new Intent(RelacaoCabecActivity.this, ComentarioActivity.class);", getLocalClassName());
                            it = new Intent(RelacaoCabecActivity.this, ComentarioActivity.class);
                            startActivity(it);
                            finish();
                            break;
                    }
                }

            }

        });

        buttonAvancarCabec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancarCabec.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcqContext.getFormularioCTR().getCabecFinalizado().getTipoCabec() == 1L) {
                    pcqContext.getFormularioCTR().finalizarCabecParaEnvio(getLocalClassName());
                    Intent it = new Intent(RelacaoCabecActivity.this, MenuInicialActivity.class);
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().getCabecFinalizado().getTipoCabec() == 1L) {\n" +
                            "                    pcqContext.getFormularioCTR().finalizarCabecParaEnvio();\n" +
                            "                    Intent it = new Intent(RelacaoCabecActivity.this, MenuInicialActivity.class);", getLocalClassName());
                    startActivity(it);
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    pcqContext.getFormularioCTR().setPosCriterio(1);\n" +
                            "                    Intent it = new Intent(RelacaoCabecActivity.this, CriterioActivity.class);", getLocalClassName());
                    pcqContext.getFormularioCTR().setPosCriterio(1);
                    Intent it = new Intent(RelacaoCabecActivity.this, CriterioActivity.class);
                    startActivity(it);
                }
                finish();
            }

        });

        buttonExcluirCabec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonExcluirCabec.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(RelacaoCabecActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE EXCLUIR TODOS OS DADOS DO FORMULÁRIO?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(RelacaoCabecActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE EXCLUIR TODOS OS DADOS DO FORMULÁRIO?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pcqContext.getFormularioCTR().excluirItemCabec();
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {\n" +
                                "                        pcqContext.getFormularioCTR().excluirItemCabec();", getLocalClassName());
                        if(pcqContext.getFormularioCTR().getCabecFinalizado().getTipoCabec() == 3L){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().getCabecFinalizado().getTipoCabec() == 3L){\n" +
                                    "                            pcqContext.getFormularioCTR().setCabecFinalizadoParaCabecRecebido();", getLocalClassName());
                            pcqContext.getFormularioCTR().setCabecFinalizadoParaCabecRecebido();
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            pcqContext.getFormularioCTR().excluirCabecAberto();", getLocalClassName());
                            pcqContext.getFormularioCTR().excluirCabecAberto();
                        }
                        LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(RelacaoCabecActivity.this, MenuInicialActivity.class);", getLocalClassName());
                        Intent it = new Intent(RelacaoCabecActivity.this, MenuInicialActivity.class);
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

    public ArrayList<String> exibirCabecSimplicadoCompleto(){

        LogProcessoDAO.getInstance().insertLogProcesso("CabecBean cabecBean = pcqContext.getFormularioCTR().getCabecFinalizado();\n" +
                "        ArrayList<String> itens = new ArrayList<>();\n" +
                "        itens.add(\"DATA/HORA:\\n\" + cabecBean.getDthrCabec());\n" +
                "        itens.add(\"COLABORADOR:\\n\" + pcqContext.getFormularioCTR().getIdFuncColab(cabecBean.getIdFuncCabec()).getMatricColab() + \" - \"\n" +
                "                + pcqContext.getFormularioCTR().getIdFuncColab(cabecBean.getIdFuncCabec()).getNomeColab());\n" +
                "        itens.add(\"TIPO APONTAMENTO DE TRABALHO:\\n\" + pcqContext.getFormularioCTR().getTipoApont(cabecBean.getTipoApontTrabCabec()).getDescrTipoApont());\n" +
                "        itens.add(\"ORIGEM DO FOGO:\\n\" + pcqContext.getFormularioCTR().getOrigemFogo(cabecBean.getOrigemFogoCabec()).getDescrOrigemFogo());\n" +
                "        itens.add(\"SEÇÃO:\\n\" + pcqContext.getFormularioCTR().getIdSecao(cabecBean.getSecaoCabec()).getCodSecao() + \" - \"\n" +
                "                + pcqContext.getFormularioCTR().getIdSecao(cabecBean.getSecaoCabec()).getDescrSecao());", getLocalClassName());
        CabecBean cabecBean = pcqContext.getFormularioCTR().getCabecFinalizado();
        ArrayList<String> itens = new ArrayList<>();

        itens.add("DATA/HORA:\n" + cabecBean.getDthrCabec());
        itens.add("COLABORADOR:\n" + pcqContext.getFormularioCTR().getIdFuncColab(cabecBean.getIdFuncCabec()).getMatricColab() + " - "
                + pcqContext.getFormularioCTR().getIdFuncColab(cabecBean.getIdFuncCabec()).getNomeColab());
        itens.add("TIPO APONTAMENTO DE TRABALHO:\n" + pcqContext.getFormularioCTR().getTipoApont(cabecBean.getTipoApontTrabCabec()).getDescrTipoApont());
        itens.add("ORIGEM DO FOGO:\n" + pcqContext.getFormularioCTR().getOrigemFogo(cabecBean.getOrigemFogoCabec()).getDescrOrigemFogo());
        itens.add("SEÇÃO:\n" + pcqContext.getFormularioCTR().getIdSecao(cabecBean.getSecaoCabec()).getCodSecao() + " - "
                + pcqContext.getFormularioCTR().getIdSecao(cabecBean.getSecaoCabec()).getDescrSecao());

        LogProcessoDAO.getInstance().insertLogProcesso("String talhoes = \"\";\n" +
                "        int i = 0;\n" +
                "        List<TalhaoItemBean> talhaoItemList = pcqContext.getFormularioCTR().talhaoItemCabecAbertoList();\n" +
                "        for (TalhaoItemBean talhaoItemBean : talhaoItemList){\n" +
                "            if(i == 0){\n" +
                "                talhoes = talhoes + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao();\n" +
                "            }\n" +
                "            else{\n" +
                "                talhoes = talhoes + \" - \" + pcqContext.getFormularioCTR().getTalhao(talhaoItemBean.getIdTalhao()).getCodTalhao();\n" +
                "            }\n" +
                "            i++;\n" +
                "        }\n" +
                "        talhaoItemList.clear();", getLocalClassName());
        String talhoes = "";
        int i = 0;
        List<TalhaoItemBean> talhaoItemList = pcqContext.getFormularioCTR().talhaoList(cabecBean.getIdCabec());
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

        LogProcessoDAO.getInstance().insertLogProcesso("itens.add(\"TALHÃO(ÕES) DE CANAVIAL E/OU PALHADA:\\n\" + talhoes);\n" +
                "        itens.add(\"QTDE DE FOTOS CANAVIAL: \" + pcqContext.getFormularioCTR().getListFotoCabecFechado(1L).size());\n" +
                "        itens.add(\"HA APP: \" + cabecBean.getHaIncAppCabec());\n" +
                "        itens.add(\"HA FORA APP: \" + cabecBean.getHaIncForaAppCabec());\n" +
                "        itens.add(\"QTDE DE FOTOS DE INCÊNDIO APP OU FORA APP: \" + pcqContext.getFormularioCTR().getListFotoCabecFechado(2L).size());", getLocalClassName());
        itens.add("TALHÃO(ÕES) DE CANAVIAL E/OU PALHADA:\n" + talhoes);
        itens.add("QTDE DE FOTOS CANAVIAL: " + pcqContext.getFormularioCTR().getListFotoCabecFinalizado(1L).size());
        itens.add("HA APP: " + cabecBean.getHaIncAppCabec());
        itens.add("HA FORA APP: " + cabecBean.getHaIncForaAppCabec());
        itens.add("QTDE DE FOTOS DE INCÊNDIO APP OU FORA APP: " + pcqContext.getFormularioCTR().getListFotoCabecFinalizado(2L).size());

        LogProcessoDAO.getInstance().insertLogProcesso("String tanques = \"\";\n" +
                "        i = 0;\n" +
                "        List<EquipItemBean> tanqueItemList = pcqContext.getFormularioCTR().tanqueItemList(cabecBean.getIdCabec());\n" +
                "        for (EquipItemBean equipItemBean : tanqueItemList){\n" +
                "            if(i == 0){\n" +
                "                tanques = tanques + pcqContext.getFormularioCTR().getEquip(equipItemBean.getIdEquip()).getNroEquip();\n" +
                "            }\n" +
                "            else{\n" +
                "                tanques = tanques + \" - \" + pcqContext.getFormularioCTR().getEquip(equipItemBean.getIdEquip()).getNroEquip();\n" +
                "            }\n" +
                "            i++;\n" +
                "        }\n" +
                "        tanqueItemList.clear();\n" +
                "        itens.add(\"TANQUE(S):\\n\" + tanques);", getLocalClassName());
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

        LogProcessoDAO.getInstance().insertLogProcesso("String saveiros = \"\";\n" +
                "        i = 0;\n" +
                "        List<EquipItemBean> saveiroItemList = pcqContext.getFormularioCTR().saveiroItemList(cabecBean.getIdCabec());\n" +
                "        for (EquipItemBean equipItemBean : saveiroItemList){\n" +
                "            if(i == 0){\n" +
                "                saveiros = saveiros + \"\" + pcqContext.getFormularioCTR().getEquip(equipItemBean.getIdEquip()).getNroEquip();\n" +
                "            }\n" +
                "            else{\n" +
                "                saveiros = saveiros + \" - \" + pcqContext.getFormularioCTR().getEquip(equipItemBean.getIdEquip()).getNroEquip();\n" +
                "            }\n" +
                "            i++;\n" +
                "        }\n" +
                "        saveiroItemList.clear();\n" +
                "        itens.add(\"SAVEIRO(S):\\n\" + saveiros);", getLocalClassName());
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

        LogProcessoDAO.getInstance().insertLogProcesso("String brigadistas = \"\";\n" +
                "        i = 0;\n" +
                "        List<BrigadistaItemBean> brigadistaItemList = pcqContext.getFormularioCTR().brigadistaItemList(cabecBean.getIdCabec());\n" +
                "        for (BrigadistaItemBean brigadistaItemBean : brigadistaItemList){\n" +
                "            if(i == 0){\n" +
                "                brigadistas = brigadistas + \"\" + pcqContext.getFormularioCTR().getBrigadista(brigadistaItemBean.getIdFunc()).getMatricBrigadista();\n" +
                "            }\n" +
                "            else{\n" +
                "                brigadistas = brigadistas + \" - \" + pcqContext.getFormularioCTR().getBrigadista(brigadistaItemBean.getIdFunc()).getMatricBrigadista();\n" +
                "            }\n" +
                "            i++;\n" +
                "        }\n" +
                "        brigadistaItemList.clear();\n" +
                "        itens.add(\"BRIGADISTAS:\\n\" + brigadistas);", getLocalClassName());
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
            LogProcessoDAO.getInstance().insertLogProcesso("if(cabecBean.getTercCombCabec() > 0){\n" +
                    "            itens.add(\"TERCEIRO COMBATE:\\n\" + pcqContext.getFormularioCTR().getTercComb(cabecBean.getTercCombCabec()).getDescrTercComb());", getLocalClassName());
            itens.add("TERCEIRO COMBATE:\n" + pcqContext.getFormularioCTR().getTercComb(cabecBean.getTercCombCabec()).getDescrTercComb());
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            itens.add(\"TERCEIRO COMBATE:\");", getLocalClassName());
            itens.add("TERCEIRO COMBATE:");
        }

        if(cabecBean.getAceiroCanavialCabec() == 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(cabecBean.getAceiroCanavialCabec() == 1){\n" +
                    "            itens.add(\"CONDIÇÕES DO ACEIRO - CANAVIAL (CARREADOR): MAIOR QUE 3 m\");", getLocalClassName());
            itens.add("CONDIÇÕES DO ACEIRO - CANAVIAL (CARREADOR): MAIOR QUE 3 m");
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            itens.add(\"CONDIÇÕES DO ACEIRO - CANAVIAL (CARREADOR): MENOR QUE 3 m\");", getLocalClassName());
            itens.add("CONDIÇÕES DO ACEIRO - CANAVIAL (CARREADOR): MENOR QUE 3 m");
        }

        if(cabecBean.getAceiroVegetNativalCabec() == 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(cabecBean.getAceiroVegetNativalCabec() == 1){\n" +
                    "            itens.add(\"CONDIÇÕES DO ACEIRO - VEGETAÇÃO NATIVA: MAIOR QUE 6 m\");", getLocalClassName());
            itens.add("CONDIÇÕES DO ACEIRO - VEGETAÇÃO NATIVA: MAIOR QUE 6 m");
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            itens.add(\"CONDIÇÕES DO ACEIRO - VEGETAÇÃO NATIVA: MENOR QUE 6 m\");", getLocalClassName());
            itens.add("CONDIÇÕES DO ACEIRO - VEGETAÇÃO NATIVA: MENOR QUE 6 m");
        }

        LogProcessoDAO.getInstance().insertLogProcesso("itens.add(\"COMENTÁRIO:\\n\" + cabecBean.getComentCabec());", getLocalClassName());
        itens.add("COMENTÁRIO:\n" + cabecBean.getComentCabec());
        return itens;
    }

    public ArrayList<String> exibirCabecRecebido(){

        LogProcessoDAO.getInstance().insertLogProcesso("CabecBean cabecBean = pcqContext.getFormularioCTR().getCabecFinalizado();\n" +
                "        ArrayList<String> itens = new ArrayList<>();\n" +
                "        itens.add(\"FORMULÁRIO \" + cabecBean.getIdExtCabec());\n" +
                "        itens.add(\"DATA/HORA \" + cabecBean.getDthrCabec());\n" +
                "        itens.add(\"COLABORADOR:\\n\" + pcqContext.getFormularioCTR().getIdFuncColab(cabecBean.getIdFuncCabec()).getMatricColab() + \" - \"\n" +
                "                + pcqContext.getFormularioCTR().getIdFuncColab(cabecBean.getIdFuncCabec()).getNomeColab());\n" +
                "        itens.add(\"TIPO APONTAMENTO DE TRABALHO:\\n\" + pcqContext.getFormularioCTR().getTipoApont(cabecBean.getTipoApontTrabCabec()).getDescrTipoApont());\n" +
                "        itens.add(\"ORIGEM DO FOGO:\\n\" + pcqContext.getFormularioCTR().getOrigemFogo(cabecBean.getOrigemFogoCabec()).getDescrOrigemFogo());\n" +
                "        itens.add(\"SEÇÃO:\\n\" + pcqContext.getFormularioCTR().getIdSecao(cabecBean.getSecaoCabec()).getCodSecao() + \" - \"\n" +
                "                + pcqContext.getFormularioCTR().getIdSecao(cabecBean.getSecaoCabec()).getDescrSecao());", getLocalClassName());
        CabecBean cabecBean = pcqContext.getFormularioCTR().getCabecFinalizado();
        ArrayList<String> itens = new ArrayList<>();
        itens.add("FORMULÁRIO " + cabecBean.getIdExtCabec());
        itens.add("DATA/HORA " + cabecBean.getDthrCabec());
        itens.add("COLABORADOR:\n" + pcqContext.getFormularioCTR().getIdFuncColab(cabecBean.getIdFuncCabec()).getMatricColab() + " - "
                + pcqContext.getFormularioCTR().getIdFuncColab(cabecBean.getIdFuncCabec()).getNomeColab());
        itens.add("TIPO APONTAMENTO DE TRABALHO:\n" + pcqContext.getFormularioCTR().getTipoApont(cabecBean.getTipoApontTrabCabec()).getDescrTipoApont());
        itens.add("ORIGEM DO FOGO:\n" + pcqContext.getFormularioCTR().getOrigemFogo(cabecBean.getOrigemFogoCabec()).getDescrOrigemFogo());
        itens.add("SEÇÃO:\n" + pcqContext.getFormularioCTR().getIdSecao(cabecBean.getSecaoCabec()).getCodSecao() + " - "
                + pcqContext.getFormularioCTR().getIdSecao(cabecBean.getSecaoCabec()).getDescrSecao());
        return itens;
    }

    public void onBackPressed() {
    }

}