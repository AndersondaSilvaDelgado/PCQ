package br.com.usinasantafe.pcq.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;

public class MsgCameraActivity extends ActivityGeneric {

    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_camera);

        pcqContext = (PCQContext) getApplication();

        TextView textViewMSG = (TextView) findViewById(R.id.textViewMSG);
        Button buttonSimMSG = (Button) findViewById(R.id.buttonSimMSG);
        Button buttonNaoMSG = (Button) findViewById(R.id.buttonNaoMSG);
        Button buttonRetMSG = (Button) findViewById(R.id.buttonRetMSG);

        textViewMSG.setText("SE HOUVE INCÊNDIO EM VAGETAÇÃO NATIVA, DESEJA TIRA FOTO?");

        buttonSimMSG.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pcqContext.setPosCameraTela(2);
                Intent it = new Intent(MsgCameraActivity.this, CameraActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonNaoMSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MsgCameraActivity.this, TanqueActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonRetMSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MsgCameraActivity.this, HaIncForaAppActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}
