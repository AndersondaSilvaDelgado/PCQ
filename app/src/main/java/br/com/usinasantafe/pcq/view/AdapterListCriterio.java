package br.com.usinasantafe.pcq.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.control.FormularioCTR;
import br.com.usinasantafe.pcq.model.bean.variaveis.RespItemBean;

public class AdapterListCriterio extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;

    public AdapterListCriterio(Context context, List itens) {
        this.itens = itens;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = layoutInflater.inflate(R.layout.activity_item_criteiro, null);
        TextView textViewCriteiro = (TextView) view.findViewById(R.id.textViewCriteiro);
        TextView textViewQuestao = (TextView) view.findViewById(R.id.textViewQuestao);
        TextView textViewResp = (TextView) view.findViewById(R.id.textViewResp);
        TextView textViewSubResp = (TextView) view.findViewById(R.id.textViewSubResp);

        FormularioCTR formularioCTR = new FormularioCTR();

        RespItemBean respItemBean = (RespItemBean) itens.get(position);

        textViewCriteiro.setText("CRITÉRIO " + (position + 1));
        textViewQuestao.setText("QUESTÃO:\n" + formularioCTR.getQuestao(respItemBean.getIdQuestao()).getDescrQuestao());
        textViewResp.setText("RESPOSTA:\n" + formularioCTR.getResp(respItemBean.getIdResp()).getDescrResp());
        if(respItemBean.getIdSubResp() > 0){
            textViewSubResp.setText("SUB RESPOSTA:\n" + formularioCTR.getResp(respItemBean.getIdSubResp()).getDescrResp());
        }
        else{
            textViewSubResp.setText("");
        }

        return view;
    }

}
