package com.example.echo.jogodavelha_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int request_code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickbEnviar(View view) {

        //EditText etTxt1 = (EditText) findViewById(R.id.etTexto1);
        String strNivel = "";

        Intent i = new Intent(this, JogodaVelha.class);

        // chama atividade usando intent i; não espera retorno

        // exemplo se precisar selecionar um botão entre vários chamando o mesmo método onClick
        switch (view.getId()){
            case R.id.bFacil:

                strNivel = "Facil";
                i.putExtra("txt1", strNivel);
                startActivity(i);

                break;
            case R.id.bMedio:

                strNivel = "Facil";
                i.putExtra("txt1", strNivel);
                startActivity(i);

                break;
            case R.id.bDificil:

                strNivel = "Facil";
                i.putExtra("txt1", strNivel);
                startActivity(i);

                break;
        }
    }

    public void onClickbPerguntar (View view){
        EditText etTxt1 = (EditText) findViewById(R.id.etTexto1);
        String strTxt1 = etTxt1.getText().toString();

        Intent i = new Intent(this, AtividadeTres.class);
        i.putExtra("txt1", strTxt1);
        startActivityForResult(i, request_code);
        // chama atividade usando intent i;  espera retorno
        // request_code é um int usado para verificar o retorno da activity
    }

    // metodo obrigatorio, nome e assinatura, para pegar o retorno da activity
    protected void onActivityResult (int requestCode, int resultCode, Intent dados) {
        if ((requestCode ==  request_code) && (resultCode == RESULT_OK)){
            TextView tvResposta= (TextView) findViewById(R.id.tvResposta);
            String strResposta = dados.getExtras().getString("txt2");
            tvResposta.setText(strResposta);
        }
    }




}
