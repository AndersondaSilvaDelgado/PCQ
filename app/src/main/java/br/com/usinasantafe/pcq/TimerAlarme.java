package br.com.usinasantafe.pcq;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.usinasantafe.pcq.model.pst.DatabaseHelper;
import br.com.usinasantafe.pcq.util.EnvioDadosServ;
import br.com.usinasantafe.pcq.util.Tempo;

public class TimerAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		Log.i("PST", "DATA HORA = " + Tempo.getInstance().dataComHora());
		if(EnvioDadosServ.getInstance().verifDadosEnvio()){
			Log.i("PST", "ENVIANDO");
			EnvioDadosServ.getInstance().envioDados(context);
		}
	}
}