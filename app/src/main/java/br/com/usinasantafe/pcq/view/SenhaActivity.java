package br.com.usinasantafe.pcq.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;

public class SenhaActivity extends ActivityGeneric {

    private EditText editTextSenha;
    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);


        editTextSenha = findViewById(R.id.editTextSenha);
        Button btOkSenha = findViewById(R.id.buttonOkSenha);
        Button btCancSenha = findViewById(R.id.buttonCancSenha);

        pcqContext = (PCQContext) getApplication();

        btOkSenha.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("btOkSenha.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @SuppressWarnings(\"unchecked\")\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (editTextSenha.getText().toString().equals("fgbny946")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (editTextSenha.getText().toString().equals(\"fgbny946\")) {\n" +
                            "Intent it = new Intent(SenhaActivity.this, LogProcessoActivity.class);", getLocalClassName());
                    Intent it = new Intent(SenhaActivity.this, LogProcessoActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        btCancSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("btCancSenha.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);", getLocalClassName());
                Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

    }
}