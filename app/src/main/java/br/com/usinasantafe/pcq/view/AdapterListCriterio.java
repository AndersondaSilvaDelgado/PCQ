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
//
//        ApontIndBean apontIndBean = (ApontIndBean) itens.get(position);
//
//        textViewHistHrInicial.setText("HORÁRIO INICIAL: " + Tempo.getInstance().manipDHComTZ(apontIndBean.getDthrInicialApont()));
//        if(!apontIndBean.getDthrFinalApont().equals("")){
//            textViewHistHrFinal.setText("HORÁRIO FINAL: " + Tempo.getInstance().manipDHComTZ(apontIndBean.getDthrFinalApont()));
//        }
//        else{
//            textViewHistHrFinal.setText("HORÁRIO FINAL: ");
//        }
//
//        if(apontIndBean.getParadaApont() == 0) {
//            textViewHistApont.setText("TRABALHANDO: OS " + apontIndBean.getOsApont() + " - ITEM " + apontIndBean.getItemOSApont());
//            textViewHistApont.setTextColor(Color.BLUE);
//        }
//        else{
//
//            MecanicoCTR mecanicoCTR = new MecanicoCTR();
//            ParadaBean paradaBean = mecanicoCTR.getParadaId(apontIndBean.getParadaApont());
//            textViewHistApont.setText("PARADA: " + paradaBean.getCodParada() + " - " + paradaBean.getDescrParada());
//            textViewHistApont.setTextColor(Color.RED);
//        }

        return view;
    }

}
