package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class DataActivity extends ActivityGeneric {

    private DatePickerDialog datePickerDialog;
    private Button editTextData;
    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        pcqContext = (PCQContext) getApplication();

        initDatePicker();
        editTextData = findViewById(R.id.editTextData);
        Button buttonSalvarData = findViewById(R.id.buttonSalvarData);
        Button buttonCancData = findViewById(R.id.buttonCancData);

        editTextData.setText(getTodaysDate());

        buttonSalvarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonSalvarData.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                pcqContext.getFormularioCTR().setDataInsCabec(editTextData.getText().toString());", getLocalClassName());
                pcqContext.getFormularioCTR().setDataInsCabec(editTextData.getText().toString());
                if(pcqContext.getFormularioCTR().verCabecAberto()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                            "                    Intent it = new Intent(DataActivity.this, ColabActivity.class);", getLocalClassName());
                    Intent it = new Intent(DataActivity.this, ColabActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(DataActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                    Intent it = new Intent(DataActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancData.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcqContext.getFormularioCTR().verCabecAberto()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcqContext.getFormularioCTR().verCabecAberto()){\n" +
                            "                    Intent it = new Intent(DataActivity.this, MenuInicialActivity.class);", getLocalClassName());
                    Intent it = new Intent(DataActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(DataActivity.this, RelacaoCabecActivity.class);", getLocalClassName());
                    Intent it = new Intent(DataActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

    }

    public void onBackPressed() {
    }

    private String getTodaysDate() {
        LogProcessoDAO.getInstance().insertLogProcesso("private String getTodaysDate() {\n" +
                "        Calendar cal = Calendar.getInstance();\n" +
                "        int year = cal.get(Calendar.YEAR);\n" +
                "        int month = cal.get(Calendar.MONTH);\n" +
                "        month = month + 1;\n" +
                "        int day = cal.get(Calendar.DAY_OF_MONTH);\n" +
                "        return makeDateString(day, month, year);", getLocalClassName());
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        LogProcessoDAO.getInstance().insertLogProcesso("private void initDatePicker() {", getLocalClassName());
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                LogProcessoDAO.getInstance().insertLogProcesso("DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {\n" +
                        "            @Override\n" +
                        "            public void onDateSet(DatePicker datePicker, int year, int month, int day) {\n" +
                        "                month = month + 1;\n" +
                        "                String date = makeDateString(day, month, year);\n" +
                        "                editTextData.setText(date);", getLocalClassName());
                month = month + 1;
                String date = makeDateString(day, month, year);
                editTextData.setText(date);
            }
        };

        LogProcessoDAO.getInstance().insertLogProcesso("Calendar cal = Calendar.getInstance();\n" +
                "        int year = cal.get(Calendar.YEAR);\n" +
                "        int month = cal.get(Calendar.MONTH);\n" +
                "        int day = cal.get(Calendar.DAY_OF_MONTH);\n" +
                "        int style = AlertDialog.THEME_HOLO_LIGHT;\n" +
                "        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);", getLocalClassName());
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year) {
        LogProcessoDAO.getInstance().insertLogProcesso("private String makeDateString(int day, int month, int year) {\n" +
                "        return String.format(\"%02d\", day) + \"/\" +  String.format(\"%02d\", month) + \"/\" + year;", getLocalClassName());
        return String.format("%02d", day) + "/" +  String.format("%02d", month) + "/" + year;
    }

    public void openDatePicker(View view) {
        LogProcessoDAO.getInstance().insertLogProcesso("public void openDatePicker(View view) {\n" +
                "        datePickerDialog.show();", getLocalClassName());
        datePickerDialog.show();
    }


}