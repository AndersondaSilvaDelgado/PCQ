package br.com.usinasantafe.pcq.view;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.TimerAlarme;
import br.com.usinasantafe.pcq.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pcq.util.AtualDadosServ;
import br.com.usinasantafe.pcq.util.ConexaoWeb;
import br.com.usinasantafe.pcq.util.EnvioDadosServ;
import br.com.usinasantafe.pcq.util.VerifDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuListView;
    private PCQContext pcqContext;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pcqContext = (PCQContext) getApplication();
        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);

        progressBar = new ProgressDialog(this);

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        customHandler.postDelayed(updateTimerThread, 0);

        ColabBean colabBean = new ColabBean();

        if (!colabBean.hasElements()) {

            ConexaoWeb conexaoWeb = new ConexaoWeb();

            if(conexaoWeb.verificaConexao(MenuInicialActivity.this)){

                progressBar = new ProgressDialog(MenuInicialActivity.this);
                progressBar.setCancelable(true);
                progressBar.setMessage("ATUALIZANDO ...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();

                AtualDadosServ.getInstance().atualizarBD(progressBar);
                AtualDadosServ.getInstance().setContext(MenuInicialActivity.this);

            }
            else{

                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alerta.show();

            }

        }

        if(pcqContext.getFormularioCTR().verCabecAbert()){

            pcqContext.getFormularioCTR().delItemAberto();
            pcqContext.getFormularioCTR().setPosCriterio(1);

            Intent it = new Intent(MenuInicialActivity.this, CriterioActivity.class);
            startActivity(it);
            finish();

        }
        else {
            atualizarAplic();
        }

        listarMenuInicial();

    }

    private void listarMenuInicial() {

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("FORMULÁRIO");
        itens.add("ATUALIZAR DADOS");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuListView = (ListView) findViewById(R.id.listaMenuInicial);
        menuListView.setAdapter(adapterList);

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("FORMULÁRIO")) {

                    ColabBean colabBean = new ColabBean();

                    if (colabBean.hasElements()) {

                        clearBD();

                        Intent it = new Intent(MenuInicialActivity.this, ColabActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("BASE DE DADOS DESATUALIZADA! POR FAVOR, SELECIONE A OPÇÃO 'ATUALIZAR DADOS' PARA ATUALIZAR A BASE DE DADOS ANTES DE CRIAR UM NOVO FORMULÁRIO.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();

                    }

                } else if (text.equals("ATUALIZAR DADOS")) {

                    ConexaoWeb conexaoWeb = new ConexaoWeb();

                    if(conexaoWeb.verificaConexao(MenuInicialActivity.this)){

                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("ATUALIZANDO BASE DE DADOS...");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setProgress(0);
                        progressBar.setMax(100);
                        progressBar.show();

                        AtualDadosServ.getInstance().atualizarBD(progressBar);
                        AtualDadosServ.getInstance().setContext(MenuInicialActivity.this);

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();

                    }

                } else if (text.equals("SAIR")) {

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }

            }

        });

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            int status = EnvioDadosServ.getInstance().getStatusEnvio();
            if (status == 1) {
                textViewProcesso.setTextColor(Color.YELLOW);
                textViewProcesso.setText("Enviando Dados...");
            }
            else if (status == 2) {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem Dados para serem Enviados");
            }
            else if (status == 3) {
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram Enviados");
            }
            customHandler.postDelayed(this, 10000);
        }
    };

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void onBackPressed() {
    }

    public void atualizarAplic(){
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(this)) {
            progressBar.setCancelable(true);
            progressBar.setMessage("BUSCANDO ATUALIZAÇÃO...");
            progressBar.show();
            VerifDadosServ.getInstance().verAtualAplic(pcqContext.versaoAplic, this, progressBar);
        } else {
            startTimer();
        }
    }

    public void startTimer() {

        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

        if (progressBar.isShowing()) {
            progressBar.dismiss();
        }

        if (alarmeAtivo) {

            Log.i("PST", "NOVO TIMER");

            Intent intent = new Intent(this, TimerAlarme.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            if (pendingIntent != null && alarmManager != null) {
                alarmManager.cancel(pendingIntent);
            }

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, pendingIntent);

        } else {
            Log.i("PMM", "TIMER já ativo");
        }
    }

    public void clearBD(){
        pcqContext.getFormularioCTR().delCabecIniciado();
    }

}
