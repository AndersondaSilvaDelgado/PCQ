package br.com.usinasantafe.pcq.view;

import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import br.com.usinasantafe.pcq.R;

public class ViewHolderImage extends RecyclerView.ViewHolder {

    ImageView imagem;
    CardView cardView;

    ViewHolderImage(View itemView) {
        super(itemView);

        imagem = itemView.findViewById(R.id.ivImage);
        cardView = itemView.findViewById(R.id.cardview);
    }

}
