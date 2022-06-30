package br.com.usinasantafe.pcq.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.control.FormularioCTR;
import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;

public class AdapterListFormRecebido extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;

    public AdapterListFormRecebido(Context context, List itens) {
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

        view = layoutInflater.inflate(R.layout.activity_item_form_recebido, null);
        TextView textViewIdExtCabec = (TextView) view.findViewById(R.id.textViewIdExtCabec);
        TextView textViewFuncionario = (TextView) view.findViewById(R.id.textViewFuncionario);
        TextView textViewSecao = (TextView) view.findViewById(R.id.textViewSecao);

        FormularioCTR formularioCTR = new FormularioCTR();
        CabecBean cabecBean = (CabecBean) itens.get(position);

        textViewIdExtCabec.setText("FORMULÁRIO N. " + cabecBean.getIdExtCabec());
        textViewFuncionario.setText("FUNCIONÁRIO: " + formularioCTR.getIdFuncColab(cabecBean.getIdFuncCabec()).getMatricColab() + " - " + formularioCTR.getIdFuncColab(cabecBean.getIdFuncCabec()).getNomeColab());
        textViewSecao.setText("SEÇÃO: " + formularioCTR.getIdSecao(cabecBean.getSecaoCabec()).getCodSecao() + " - " + formularioCTR.getIdSecao(cabecBean.getSecaoCabec()).getDescrSecao());

        return view;

    }
}
