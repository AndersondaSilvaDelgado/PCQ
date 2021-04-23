package br.com.usinasantafe.pcq.model.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.variaveis.FotoItemBean;
import br.com.usinasantafe.pcq.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pcq.util.Tempo;

public class FotoDAO {

    public FotoDAO() {
    }

    public FotoItemBean salvarFoto(Long idCabAbord, Bitmap bitmap, Long tipoFoto){
        FotoItemBean fotoItemBean = new FotoItemBean();
        fotoItemBean.setIdCabec(idCabAbord);
        fotoItemBean.setFoto(getBitmapString(bitmap));
        fotoItemBean.setDthrFoto(Tempo.getInstance().dataComHora());
        fotoItemBean.setTipoFoto(tipoFoto);
        fotoItemBean.insert();
        return fotoItemBean;
    }

    public List getListFotoCabec(Long idCabec, Long tipoFoto){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        pesqArrayList.add(getPesqTipo(tipoFoto));
        FotoItemBean fotoItemBean = new FotoItemBean();
        return fotoItemBean.get(pesqArrayList);
    }

    private String getBitmapString(Bitmap foto){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        foto.compress(Bitmap.CompressFormat.JPEG, 95, stream);

        byte[] byteArray = stream.toByteArray();

        return(Base64.encodeToString(byteArray, Base64.DEFAULT));

    }

    public Bitmap getStringBitmap(String foto){

        try{
            byte [] encodeByte= Base64.decode(foto ,Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        }catch(Exception e){
            Log.i("PST", "Erro = " + e.toString());
            return null;
        }

    }

    public void delFotoCabec(Long idCabec){
        FotoItemBean fotoItemBean = new FotoItemBean();
        List<FotoItemBean> fotoList = fotoItemBean.get("idCabFoto", idCabec);
        for (FotoItemBean fotoItemBeanBD : fotoList) {
            fotoItemBeanBD.delete();
        }
    }

    public JsonArray dadosEnvioFoto(Long idCabec){
        FotoItemBean fotoItemBean = new FotoItemBean();
        List<FotoItemBean> fotoList = fotoItemBean.get("idCabec", idCabec);
        JsonArray fotoJsonArray = new JsonArray();
        for (int i = 0; i < fotoList.size(); i++) {
            fotoItemBean = fotoList.get(i);
            Gson cabecGson = new Gson();
            fotoJsonArray.add(cabecGson.toJsonTree(fotoItemBean, fotoItemBean.getClass()));
        }
        fotoList.clear();
        return fotoJsonArray;
    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabecFoto){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("idCabec");
        especificaPesquisa.setValor(idCabecFoto);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqTipo(Long tipoFoto){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("tipoFoto");
        especificaPesquisa.setValor(tipoFoto);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

}
