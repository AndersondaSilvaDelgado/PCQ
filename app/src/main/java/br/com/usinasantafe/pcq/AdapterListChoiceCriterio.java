package br.com.usinasantafe.pcq;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;

public class AdapterListChoiceCriterio extends BaseAdapter {

    private ArrayList<ViewHolderChoice> itens;
    private CriterioActivity criterioActivity;

    public AdapterListChoiceCriterio(CriterioActivity criterioActivity, ArrayList<ViewHolderChoice> itens) {
        // TODO Auto-generated constructor stub
        this.itens = itens;
        this.criterioActivity = criterioActivity;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) criterioActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_item_lista_choice, null, true);

            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBoxItemList);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.checkBox.setText(itens.get(position).getDescrCheckBox());
        viewHolder.checkBox.setChecked(itens.get(position).isSelected());
        viewHolder.checkBox.setTag(position);

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = (Integer)  viewHolder.checkBox.getTag();
                if(itens.get(pos).isSelected()){
                    itens.get(pos).setSelected(false);
                }else {
                    itens.get(pos).setSelected(true);
                    criterioActivity.itemSelected(pos);
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {
        protected CheckBox checkBox;
    }

}