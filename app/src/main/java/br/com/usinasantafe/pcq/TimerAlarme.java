package br.com.usinasantafe.pcq;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.j256.ormlite.field.DatabaseField;

import java.util.List;

import br.com.usinasantafe.pcq.model.bean.variaveis.BrigadistaItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.EquipItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.FotoItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.RespItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.TalhaoItemBean;
import br.com.usinasantafe.pcq.model.pst.DatabaseHelper;
import br.com.usinasantafe.pcq.util.EnvioDadosServ;
import br.com.usinasantafe.pcq.util.Tempo;

public class TimerAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		teste();
		Log.i("PST", "DATA HORA = " + Tempo.getInstance().dataComHora());
		if(EnvioDadosServ.getInstance().verifDadosEnvio()){
			Log.i("PST", "ENVIANDO");
			EnvioDadosServ.getInstance().envioFormComum(context);
		}
	}

	public void teste(){

		BrigadistaItemBean brigadistaItemBean = new BrigadistaItemBean();
		List<BrigadistaItemBean> brigadistaItemList = brigadistaItemBean.all();

		for(BrigadistaItemBean brigadistaItemBeanBD : brigadistaItemList){
			Log.i("PCQ", "BRIGADISTA");
			Log.i("PCQ", "IdBrigadistaItem = " + brigadistaItemBeanBD.getIdBrigadistaItem());
			Log.i("PCQ", "IdCabec = " + brigadistaItemBeanBD.getIdCabec());
			Log.i("PCQ", "IdFunc = " + brigadistaItemBeanBD.getIdFunc());
			Log.i("PCQ", "DthrBrigadista = " + brigadistaItemBeanBD.getDthrBrigadista());
		}

		CabecBean cabecBean = new CabecBean();
		List<CabecBean> cabecList = cabecBean.all();

		for(CabecBean cabecBeanBD : cabecList){
			Log.i("PCQ", "CABEC");
			Log.i("PCQ", "IdCabec = " + cabecBeanBD.getIdCabec());
			Log.i("PCQ", "IdExtCabec = " + cabecBeanBD.getIdExtCabec());
			Log.i("PCQ", "NroAparelhoCabec = " + cabecBeanBD.getNroAparelhoCabec());
			Log.i("PCQ", "IdFuncCabec = " + cabecBeanBD.getIdFuncCabec());
			Log.i("PCQ", "TipoApontTrabCabec = " + cabecBeanBD.getTipoApontTrabCabec());
			Log.i("PCQ", "SecaoCabec = " + cabecBeanBD.getSecaoCabec());
			Log.i("PCQ", "HaIncAppCabec = " + cabecBeanBD.getHaIncAppCabec());
			Log.i("PCQ", "HaIncForaAppCabec = " + cabecBeanBD.getHaIncForaAppCabec());
			Log.i("PCQ", "TercCombCabec = " + cabecBeanBD.getTercCombCabec());
			Log.i("PCQ", "OrigemFogoCabec = " + cabecBeanBD.getOrigemFogoCabec());
			Log.i("PCQ", "AceiroCanavialCabec = " + cabecBeanBD.getAceiroCanavialCabec());
			Log.i("PCQ", "AceiroVegetNativalCabec = " + cabecBeanBD.getAceiroVegetNativalCabec());
			Log.i("PCQ", "ComentCabec = " + cabecBeanBD.getComentCabec());
			Log.i("PCQ", "DthrCabec = " + cabecBeanBD.getDthrCabec());
			Log.i("PCQ", "TipoCabec = " + cabecBeanBD.getTipoCabec());
			Log.i("PCQ", "StatusCabec = " + cabecBeanBD.getStatusCabec());

		}

		EquipItemBean equipItemBean = new EquipItemBean();
		List<EquipItemBean> equipItemList = equipItemBean.all();

		for(EquipItemBean equipItemBeanBD : equipItemList){
			Log.i("PCQ", "EQUIP ITEM");
			Log.i("PCQ", "IdItemEquip = " + equipItemBeanBD.getIdItemEquip());
			Log.i("PCQ", "IdCabec = " + equipItemBeanBD.getIdCabec());
			Log.i("PCQ", "IdEquip = " + equipItemBeanBD.getIdEquip());
			Log.i("PCQ", "TipoEquip = " + equipItemBeanBD.getTipoEquip());
			Log.i("PCQ", "DthrEquip = " + equipItemBeanBD.getDthrEquip());
		}

		FotoItemBean fotoItemBean = new FotoItemBean();
		List<FotoItemBean> fotoItemList = fotoItemBean.all();

		for(FotoItemBean fotoItemBeanBD : fotoItemList){
			Log.i("PCQ", "FOTO ITEM");
			Log.i("PCQ", "IdFotoItem = " + fotoItemBeanBD.getIdFotoItem());
			Log.i("PCQ", "IdCabec = " + fotoItemBeanBD.getIdCabec());
			Log.i("PCQ", "Foto = " + fotoItemBeanBD.getFoto());
			Log.i("PCQ", "TipoFoto = " + fotoItemBeanBD.getTipoFoto());
			Log.i("PCQ", "DthrFoto = " + fotoItemBeanBD.getDthrFoto());
		}

		RespItemBean respItemBean = new RespItemBean();
		List<RespItemBean> respItemList = respItemBean.all();

		for(RespItemBean respItemBeanBD : respItemList){
			Log.i("PCQ", "RESP ITEM");
			Log.i("PCQ", "IdItem = " + respItemBeanBD.getIdItem());
			Log.i("PCQ", "IdCabec = " + respItemBeanBD.getIdCabec());
			Log.i("PCQ", "IdQuestao = " + respItemBeanBD.getIdQuestao());
			Log.i("PCQ", "IdResp = " + respItemBeanBD.getIdResp());
			Log.i("PCQ", "IdSubResp = " + respItemBeanBD.getIdSubResp());
			Log.i("PCQ", "DthrItem = " + respItemBeanBD.getDthrItem());
		}

		TalhaoItemBean talhaoItemBean = new TalhaoItemBean();
		List<TalhaoItemBean> talhaoItemList = talhaoItemBean.all();

		for(TalhaoItemBean talhaoItemBeanBD : talhaoItemList){
			Log.i("PCQ", "TALHAO ITEM");
			Log.i("PCQ", "IdTalhaoItem = " + talhaoItemBeanBD.getIdTalhaoItem());
			Log.i("PCQ", "IdCabec = " + talhaoItemBeanBD.getIdCabec());
			Log.i("PCQ", "IdTalhao = " + talhaoItemBeanBD.getIdTalhao());
			Log.i("PCQ", "DthrTalhao = " + talhaoItemBeanBD.getDthrTalhao());
			Log.i("PCQ", "HaIncCanaTalhao = " + talhaoItemBeanBD.getHaIncCanaTalhao());
			Log.i("PCQ", "AltCanaTalhao = " + talhaoItemBeanBD.getAltCanaTalhao());
			Log.i("PCQ", "HaIncPalhadaTalhao = " + talhaoItemBeanBD.getHaIncPalhadaTalhao());
			Log.i("PCQ", "TipoTalhao = " + talhaoItemBeanBD.getTipoTalhao());
		}

	}

}