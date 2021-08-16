package br.com.usinasantafe.pcq.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.control.FormularioCTR;
import br.com.usinasantafe.pcq.model.bean.variaveis.FotoItemBean;

public class AdapterListFoto extends RecyclerView.Adapter<ViewHolderImage> {

    private Context context;
    private List imagemList;
    private int pos;
    private FotoItemBean fotoItemBean;

    public AdapterListFoto(Context context, List imagemList) {
        this.context = context;
        this.imagemList = imagemList;
    }

    @NonNull
    @Override
    public ViewHolderImage onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_lista_foto, viewGroup, false);
        return new ViewHolderImage(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderImage holder, @SuppressLint("RecyclerView") int i) {

        pos = i;

        fotoItemBean = (FotoItemBean) imagemList.get(pos);
        FormularioCTR formularioCTR = new FormularioCTR();
        holder.imagem.setImageBitmap(ThumbnailUtils.extractThumbnail(formularioCTR.getStringBitmap(fotoItemBean.getFoto()),
                192, 192));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA APAGAR A FOTO DO FORMULÁRIO?");
                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        imagemList.remove(pos);
                        fotoItemBean.delete();

                        Intent it = new Intent(context, CameraActivity.class);
                        context.startActivity(it);

                    }
                });

                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return imagemList.size();
    }
}