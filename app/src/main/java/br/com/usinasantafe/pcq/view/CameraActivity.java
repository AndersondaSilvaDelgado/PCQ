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
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

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

        TextView textViewFoto = findViewById(R.id.textViewFoto);
        Button buttonCapturaFoto = findViewById(R.id.buttonCapturaFoto);
        Button buttonAvancaFoto = findViewById(R.id.buttonAvancaFoto);
        Button buttonRetFoto = findViewById(R.id.buttonRetFoto);
        Button buttonAbrirGaleria = findViewById(R.id.buttonAbrirGaleria);
        mRecyclerView = findViewById(R.id.recyclerview);

        LogProcessoDAO.getInstance().insertLogProcesso("GridLayoutManager mGridLayoutManager = new GridLayoutManager(CameraActivity.this, 2);\n" +
                "        mRecyclerView.setLayoutManager(mGridLayoutManager);\n" +
                "        tipoFoto = Long.valueOf(pcqContext.getPosCameraTela());\n" +
                "        fotoList = pcqContext.getFormularioCTR().getListFotoCabecIniciado(tipoFoto);\n" +
                "        if(pcqContext.getPosCameraTela() == 1){\n" +
                "            textViewFoto.setText(\"FOTO CANAVIAL\");\n" +
                "        }\n" +
                "        else{\n" +
                "            textViewFoto.setText(\"FOTO FORA DO CANAVIAL\");\n" +
                "        }\n" +
                "        AdapterListFoto adapterListFoto = new AdapterListFoto(CameraActivity.this, fotoList);\n" +
                "        mRecyclerView.setAdapter(adapterListFoto);", getLocalClassName());
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(CameraActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        tipoFoto = Long.valueOf(pcqContext.getPosCameraTela());
        fotoList = pcqContext.getFormularioCTR().getListFotoCabecIniciado(tipoFoto);

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
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAbrirGaleria.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                pcqContext.setTipoFoto(2);\n" +
                        "                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);\n" +
                        "                startActivityForResult(intent, 1);", getLocalClassName());
                pcqContext.setTipoFoto(2);
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        buttonCapturaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCapturaFoto.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(fotoList.size() < 3){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(fotoList.size() < 3){\n" +
                            "                    pcqContext.setTipoFoto(1);\n" +
                            "                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);\n" +
                            "                    startActivityForResult(intent, 1);", getLocalClassName());
                    pcqContext.setTipoFoto(1);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(CameraActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"CADA ABORDAGEM PODEM TER APENAS 3 FOTOS. POR FAVOR, EXCLUA UMA FOTO PARA PODE TIRA UMA NOVA FOTO.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(CameraActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("CADA ABORDAGEM PODEM TER APENAS 3 FOTOS. POR FAVOR, EXCLUA UMA FOTO PARA PODE TIRA UMA NOVA FOTO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                    AlertDialog.Builder alerta = new AlertDialog.Builder(CameraActivity.this);\n" +
                                    "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                    alerta.setMessage(\"CADA ABORDAGEM PODEM TER APENAS 3 FOTOS. POR FAVOR, EXCLUA UMA FOTO PARA PODE TIRA UMA NOVA FOTO.\");", getLocalClassName());
                        }
                    });
                    alerta.show();
                }

            }
        });

        buttonAvancaFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancaFoto.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcqContext.getFormularioCTR().verCabecAberto()){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){", getLocalClassName());
                    if(pcqContext.getPosCameraTela() == 1) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getPosCameraTela() == 1) {", getLocalClassName());
                        if(fotoList.size() > 0){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(fotoList.size() > 0){\n" +
                                    "                            Intent it = new Intent(CameraActivity.this, HaIncAppActivity.class);", getLocalClassName());
                            Intent it = new Intent(CameraActivity.this, HaIncAppActivity.class);
                            startActivity(it);
                            finish();
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            AlertDialog.Builder alerta = new AlertDialog.Builder(CameraActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"ADICIONE PELO MENOS UMA FOTO NO FORMULÁRIO.\");", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(CameraActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("ADICIONE PELO MENOS UMA FOTO NO FORMULÁRIO.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                            "                                @Override\n" +
                                            "                                public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                                }
                            });
                            alerta.show();
                        }

                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent(CameraActivity.this, TanqueActivity.class);", getLocalClassName());
                        Intent it = new Intent(CameraActivity.this, TanqueActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(CameraActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                    Intent it = new Intent(CameraActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonRetFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetFoto.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcqContext.getFormularioCTR().verCabecAberto()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){", getLocalClassName());
                    if(pcqContext.getPosCameraTela() == 1) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getPosCameraTela() == 1) {\n" +
                                "                        List<TalhaoItemBean> talhaoItemList = pcqContext.getFormularioCTR().talhaoItemCabecIniciadoList();\n" +
                                "                        TalhaoItemBean talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);\n" +
                                "                        Intent it;", getLocalClassName());
                        List<TalhaoItemBean> talhaoItemList = pcqContext.getFormularioCTR().talhaoItemCabecIniciadoList();
                        TalhaoItemBean talhaoItemBean = talhaoItemList.get(pcqContext.getFormularioCTR().getPosTalhao() - 1);
                        Intent it;
                        if(talhaoItemBean.getTipoTalhao() == 1L) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if(talhaoItemBean.getTipoTalhao() == 1L) {\n" +
                                    "                            it = new Intent(CameraActivity.this, AltCanavialActivity.class);", getLocalClassName());
                            it = new Intent(CameraActivity.this, AltCanavialActivity.class);
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            it = new Intent(CameraActivity.this, HaIncPalhadaActivity.class);", getLocalClassName());
                            it = new Intent(CameraActivity.this, HaIncPalhadaActivity.class);
                        }
                        startActivity(it);
                        finish();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent(CameraActivity.this, MsgCameraActivity.class);", getLocalClassName());
                        Intent it = new Intent(CameraActivity.this, MsgCameraActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(CameraActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
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
        LogProcessoDAO.getInstance().insertLogProcesso("@RequiresApi(api = Build.VERSION_CODES.O)\n" +
                "    @Override\n" +
                "    protected void onActivityResult(int requestCode, int resultCode, Intent data) {", getLocalClassName());
        if(requestCode == 1 && resultCode == RESULT_OK){

            LogProcessoDAO.getInstance().insertLogProcesso("if(requestCode == 1 && resultCode == RESULT_OK){\n" +
                    "            Bitmap bitmap;", getLocalClassName());
            Bitmap bitmap;
            if(pcqContext.getTipoFoto() == 1){
                LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getTipoFoto() == 1){\n" +
                        "                bitmap = (Bitmap) data.getExtras().get(\"data\");", getLocalClassName());
                bitmap = (Bitmap) data.getExtras().get("data");
            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                Uri selectedImage = data.getData();\n" +
                        "                String[] filePath = {MediaStore.Images.Media.DATA};\n" +
                        "                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, MediaStore.Images.Media.DATA);\n" +
                        "                c.moveToFirst();\n" +
                        "                int columnIndex = c.getColumnIndex(filePath[0]);\n" +
                        "                String picturePath = c.getString(columnIndex);\n" +
                        "                c.close();\n" +
                        "                bitmap = (BitmapFactory.decodeFile(picturePath));", getLocalClassName());
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, MediaStore.Images.Media.DATA);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();

                bitmap = (BitmapFactory.decodeFile(picturePath));

            }

            LogProcessoDAO.getInstance().insertLogProcesso("fotoList.add(pcqContext.getFormularioCTR().salvarFoto(bitmap, tipoFoto));\n" +
                    "            Intent it = new Intent(CameraActivity.this, CameraActivity.class);", getLocalClassName());
            fotoList.add(pcqContext.getFormularioCTR().salvarFoto(bitmap, tipoFoto));

            Intent it = new Intent(CameraActivity.this, CameraActivity.class);
            startActivity(it);
            finish();

        }

    }

    public void onBackPressed() {
    }

}