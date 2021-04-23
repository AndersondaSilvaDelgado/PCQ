package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.bean.variaveis.FotoItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.TalhaoItemBean;

public class CameraActivity extends ActivityGeneric {

    private RecyclerView mRecyclerView;
    private List<FotoItemBean> fotoList;
    private PCQContext pcqContext;
    private Long tipoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        pcqContext = (PCQContext) getApplication();

        Button buttonCapturaFoto = (Button) findViewById(R.id.buttonCapturaFoto);
        Button buttonAvancaFoto = (Button) findViewById(R.id.buttonAvancaFoto);
        Button buttonRetFoto = (Button) findViewById(R.id.buttonRetFoto);

        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(CameraActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        tipoFoto = 0L;
        if(pcqContext.getPosTela() == 1){
            tipoFoto = 1L;
        }
        else if(pcqContext.getPosTela() == 2){
            tipoFoto = 2L;
        }

        fotoList = pcqContext.getFormularioCTR().getListFotoCabecIniciado(tipoFoto);

        AdapterListFoto adapterListFoto = new AdapterListFoto(CameraActivity.this, fotoList);
        mRecyclerView.setAdapter(adapterListFoto);

        buttonCapturaFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });

        buttonAvancaFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pcqContext.getPosTela() == 1) {
                    Intent it = new Intent(CameraActivity.this, HaIncAppActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pcqContext.getPosTela() == 2){
                    Intent it = new Intent(CameraActivity.this, HaIncAppActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonRetFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(pcqContext.getPosTela() == 1) {
                    List<TalhaoItemBean> talhaoItemList = pcqContext.getFormularioCTR().talhaoItemCabecIniciadoList();
                    TalhaoItemBean talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);
                    if(talhaoItemBean.getStatusCanavialTalhao() == 1L) {
                        Intent it = new Intent(CameraActivity.this, TipoCanaActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Intent it = new Intent(CameraActivity.this, HaIncPalhadaActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
                else if(pcqContext.getPosTela() == 2){
                    Intent it = new Intent(CameraActivity.this, MsgCameraActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

    public void tirarFoto(){
        if(fotoList.size() < 3){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);
        }
        else{
            AlertDialog.Builder alerta = new AlertDialog.Builder(CameraActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("CADA ABORDAGEM PODEM TER APENAS 3 FOTOS. POR FAVOR, EXCLUA UMA FOTO PARA PODE TIRA UMA NOVA FOTO.");
            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alerta.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK){

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            fotoList.add(pcqContext.getFormularioCTR().salvarFoto(bitmap, tipoFoto));

            Intent it = new Intent(CameraActivity.this, CameraActivity.class);
            startActivity(it);
            finish();

        }

    }

    public void onBackPressed() {
    }

}