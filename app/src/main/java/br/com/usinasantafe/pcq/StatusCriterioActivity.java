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

public class StatusCriterioActivity extends ActivityGeneric {

    private ListView subRespListView;
    private List subRespList;
    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_criterio);

        pcqContext = (PCQContext) getApplication();

        TextView textViewStatusCriterio = (TextView) findViewById(R.id.textViewStatusCriterio);
        TextView textViewTituloStatusCriterio = (TextView) findViewById(R.id.textViewTituloStatusCriterio);

        textViewTituloStatusCriterio.setText("STATUS");

        RespBean respBean = new RespBean();
        List respostaList = respBean.get("idResp", pcqContext.getFormularioCTR().getIdResp());
        respBean = (RespBean) respostaList.get(0);

        textViewStatusCriterio.setText(respBean.getDescrResp());

        RespBean subRespBean = new RespBean();
        subRespList = subRespBean.get("idQuestao", respBean.getIdSubResp());

        ArrayList<ViewHolderChoice> itens = new ArrayList<ViewHolderChoice>();

        for (int i = 0; i < subRespList.size(); i++) {
            respBean = (RespBean) subRespList.get(i);
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            viewHolderChoice.setSelected(false);
            viewHolderChoice.setDescrCheckBox(respBean.getDescrResp());
            itens.add(viewHolderChoice);
        }

        AdapterListChoiceStatus adapterListChoiceStatus = new AdapterListChoiceStatus(this, itens);
        subRespListView = (ListView) findViewById(R.id.listStatusCriterio);
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) subRespListView.getLayoutParams();
        lp.height = subRespList.size() * 80;
        subRespListView.setLayoutParams(lp);
        subRespListView.setAdapter(adapterListChoiceStatus);

    }

    public void itemSelected(int pos){
        RespBean respBean = (RespBean) subRespList.get(pos);
        pcqContext.getFormularioCTR().salvarItem(respBean.getIdResp());
        QuestaoBean questaoBean = new QuestaoBean();
        List questaoList = questaoBean.get("seqQuestao",true);
        if(questaoList.size() > pcqContext.getFormularioCTR().getPosCriterio()) {
            pcqContext.getFormularioCTR().setPosCriterio(pcqContext.getFormularioCTR().getPosCriterio() + 1);
            Intent it = new Intent(StatusCriterioActivity.this, CriterioActivity.class);
            startActivity(it);
            finish();
        }
        else{
            pcqContext.getFormularioCTR().fecharCabec();
            Intent it = new Intent(StatusCriterioActivity.this, MenuInicialActivity.class);
            startActivity(it);
            finish();
        }
    }

}
