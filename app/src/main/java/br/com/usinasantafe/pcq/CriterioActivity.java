package br.com.usinasantafe.pcq;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.bean.estaticas.QuestaoBean;
import br.com.usinasantafe.pcq.bean.estaticas.RespBean;

public class CriterioActivity extends ActivityGeneric {

    private ListView respostaListView;
    private List respostaList;
    private List questaoList;
    private PCQContext pcqContext;
    private QuestaoBean questaoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criterio);

        pcqContext = (PCQContext) getApplication();

        TextView textViewCriterio = (TextView) findViewById(R.id.textViewCriterio);
        TextView textViewTituloCriterio = (TextView) findViewById(R.id.textViewTituloCriterio);

        textViewTituloCriterio.setText("CRITÉRIO " + pcqContext.getFormularioCTR().getPosCriterio());

        questaoBean = new QuestaoBean();
        List questaoList = questaoBean.get("seqQuestao",true);
        questaoBean = (QuestaoBean) questaoList.get(pcqContext.getFormularioCTR().getPosCriterio() - 1);

        textViewCriterio.setText(questaoBean.getDescrQuestao());

        RespBean respBean = new RespBean();
        respostaList = respBean.get("idQuestao", questaoBean.getIdQuestao());

        ArrayList<ViewHolderChoice> itens = new ArrayList<ViewHolderChoice>();

        for (int i = 0; i < respostaList.size(); i++) {
            respBean = (RespBean) respostaList.get(i);
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            viewHolderChoice.setSelected(false);
            viewHolderChoice.setDescrCheckBox(respBean.getDescrResp());
            itens.add(viewHolderChoice);
        }

        AdapterListChoiceCriterio adapterListChoiceCriterio = new AdapterListChoiceCriterio(this, itens);
        respostaListView = (ListView) findViewById(R.id.listCriterio);
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) respostaListView.getLayoutParams();
        lp.height = respostaList.size() * 80;
        respostaListView.setLayoutParams(lp);
        respostaListView.setAdapter(adapterListChoiceCriterio);
    }


    public void itemSelected(int pos){
        RespBean respBean = (RespBean) respostaList.get(pos);
        pcqContext.getFormularioCTR().setItemBean(questaoBean.getIdQuestao(), respBean.getIdResp());
        List subRespList = respBean.get("idSubResp", respBean.getIdSubResp());
        if(subRespList.size() == 0){
            pcqContext.getFormularioCTR().salvarItem(0L);
            if(questaoList.size() > pcqContext.getFormularioCTR().getPosCriterio()) {
                pcqContext.getFormularioCTR().setPosCriterio(pcqContext.getFormularioCTR().getPosCriterio() + 1);
                Intent it = new Intent(CriterioActivity.this, CriterioActivity.class);
                startActivity(it);
                finish();
            }
            else{
                pcqContext.getFormularioCTR().fecharCabec();
                Intent it = new Intent(CriterioActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }
        }
        else{
            Intent it = new Intent(CriterioActivity.this, StatusCriterioActivity.class);
            startActivity(it);
            finish();
        }

    }

}
