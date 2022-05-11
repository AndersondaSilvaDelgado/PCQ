package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        TextView textViewFoto = (TextView) findViewById(R.id.textViewFoto);
        Button buttonCapturaFoto = (Button) findViewById(R.id.buttonCapturaFoto);
        Button buttonAvancaFoto = (Button) findViewById(R.id.buttonAvancaFoto);
        Button buttonRetFoto = (Button) findViewById(R.id.buttonRetFoto);
        Button buttonAbrirGaleria = (Button) findViewById(R.id.buttonAbrirGaleria);

        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(CameraActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        tipoFoto = Long.valueOf(pcqContext.getPosCameraTela());
        fotoList = pcqContext.getFormularioCTR().getListFotoCabecIniciado(tipoFoto, pcqContext.getTipoTela());

        if(pcqContext.getPosCameraTela() == 1){
            textViewFoto.setText("FOTO CANAVIAL");
        }
        else{
            textViewFoto.setText("FOTO FORA DO CANAVIAL");
        }

        AdapterListFoto adapterListFoto = new AdapterListFoto(CameraActivity.this, fotoList);
        mRecyclerView.setAdapter(adapterListFoto);

        buttonAbrirGaleria.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                pcqContext.setTipoFoto(2);
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);

            }
        });

        buttonCapturaFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(fotoList.size() < 3){
                    pcqContext.setTipoFoto(1);
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
        });

        buttonAvancaFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pcqContext.getTipoTela() == 1){
                    if(pcqContext.getPosCameraTela() == 1) {

                        if(fotoList.size() > 0){
                            Intent it = new Intent(CameraActivity.this, HaIncAppActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            AlertDialog.Builder alerta = new AlertDialog.Builder(CameraActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("ADICIONE PELO MENOS UMA FOTO NO FORMULÁRIO.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alerta.show();
                        }

                    }
                    else{
                        Intent it = new Intent(CameraActivity.this, TanqueActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
                else{
                    Intent it = new Intent(CameraActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonRetFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(pcqContext.getTipoTela() == 1){
                    if(pcqContext.getPosCameraTela() == 1) {
                        List<TalhaoItemBean> talhaoItemList = pcqContext.getFormularioCTR().talhaoItemCabecIniciadoList();
                        TalhaoItemBean talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);
                        if(talhaoItemBean.getTipoTalhao() == 1L) {
                            Intent it = new Intent(CameraActivity.this, AltCanavialActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            Intent it = new Intent(CameraActivity.this, HaIncPalhadaActivity.class);
                            startActivity(it);
                            finish();
                        }
                    }
                    else{
                        Intent it = new Intent(CameraActivity.this, MsgCameraActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
                else{
                    Intent it = new Intent(CameraActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK){

            Bitmap bitmap;
            if(pcqContext.getTipoFoto() == 1){

                bitmap = (Bitmap) data.getExtras().get("data");

            }
            else{

                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, MediaStore.Images.Media.DATA);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();

                bitmap = (BitmapFactory.decodeFile(picturePath));

            }

            fotoList.add(pcqContext.getFormularioCTR().salvarFoto(bitmap, tipoFoto, pcqContext.getTipoTela()));

            Intent it = new Intent(CameraActivity.this, CameraActivity.class);
            startActivity(it);
            finish();

        }

    }

    public void onBackPressed() {
    }

}