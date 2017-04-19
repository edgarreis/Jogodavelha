package com.example.echo.jogodavelha_2;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class JogodaVelha extends AppCompatActivity {

    int[][] velha;
    int contJogadas;
    boolean quem;
    boolean jogo;
    TableLayout tlMatrizJogo;
    TextView tvMensagem;
    Button bClicado;
    Button bJogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tlMatrizJogo = (TableLayout) findViewById(R.id.tlMatrizJogo) ;
        tvMensagem = (TextView) findViewById(R.id.tvMensagem);
        bJogar = (Button) findViewById(R.id.bJogar);
        velha = new int[3][3];
        quem = true;
        jogo = true;
        contJogadas = 0;


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        String txt1 = extras.getString("txt1");

        TextView tvTxt2 = (TextView) findViewById(R.id.tvMensagem);
        tvTxt2.setText(txt1);

    }

    void onClickJogar (View view) {
        int i = 0, j = 0;
        int resul;


        bClicado = (Button) findViewById(view.getId());
        switch (view.getId()) {
            case R.id.b00: i=0; j=0; break;
            case R.id.b01: i=0; j=1; break;
            case R.id.b02: i=0; j=2; break;
            case R.id.b10: i=1; j=0; break;
            case R.id.b11: i=1; j=1; break;
            case R.id.b12: i=1; j=2; break;
            case R.id.b20: i=2; j=0; break;
            case R.id.b21: i=2; j=1; break;
            case R.id.b22: i=2; j=2; break;
        }
        if ((velha[i][j]==0) && (jogo)) {
            contJogadas++;
            if (quem) {
                velha[i][j] = 3;
                bClicado.setTextColor(Color.BLUE);
                bClicado.setText("X");
            } else {
                velha[i][j] = 5;
                bClicado.setTextColor(Color.RED);
                bClicado.setText("O");
            }
            quem = !quem;
            resul = resultado(velha[i][j]);
            switch (resul) {
                case 1:
                    tvMensagem.setText("Vitoria na vertical");
                    encerra();
                    break;
                case 2:
                    tvMensagem.setText("Vitoria na horizontal");
                    encerra();
                    break;
                case 3:
                    tvMensagem.setText("Vitoria na diagonal");
                    encerra();
                    break;
            }
            if ((contJogadas == 9) && (resul == 4)) {
                tvMensagem.setText("Jogo empatou");
                encerra();
            }
        }
        else {
            AlertDialog.Builder adAviso = new AlertDialog.Builder(this);
            adAviso.setMessage("Casa ocupada!");
            adAviso.setTitle("Movimento invalido");
            adAviso.setCancelable(true);
            adAviso.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface d, int arg) {
                    // algo pode ser feito aqui
                }
            });
            adAviso.create().show();

        }
    }

    int resultado(int jogador) {
        // alguem ganhou na vertical /
        if ((velha[0][0]+velha[1][0]+velha[2][0] == 3*jogador) ||
                (velha[0][1]+velha[1][1]+velha[2][1]  == 3*jogador) ||
                (velha[0][2]+velha[1][2]+velha[2][2]  == 3*jogador))
            return 1;
        // alguem ganhou na horizontal
        if ((velha[0][0]+velha[0][1]+velha[0][2] == 3*jogador) ||
                (velha[1][0]+velha[1][1]+velha[1][2]  == 3*jogador) ||
                (velha[2][0]+velha[2][1]+velha[2][2]  == 3*jogador))
            return 2;
        // alguem ganhou na diagonal
        if ((velha[0][0]+velha[1][1]+velha[2][2] == 3*jogador) ||
                (velha[0][2]+velha[1][1]+velha[2][0]  == 3*jogador))
            return 3;
        return 4; // sem ganhador
    }

    void encerra() {
        bJogar.setVisibility(View.VISIBLE);
        jogo = false;
    }

    void onClickbJogar (View view){
        // esconder botao e mensagem
        bJogar.setVisibility(View.INVISIBLE);
        tvMensagem.setText("");

        // zerar tabuleiro do jogo da velha
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                velha[i][j] = 0; // poderia unificar
            }
        }

        // zerar os botoes
        // tlMatrizJogo.getChildCount()
        for (int i=0; i<3; i++){
            TableRow linha = (TableRow) tlMatrizJogo.getChildAt(i);
            // linha.getChildCount()
            for (int j=0; j<3; j++){
                // pode testar com if (A instaceof B)
                Button bLimpar = (Button) linha.getChildAt(j);
                bLimpar.setText("");
            }
        }

        // zerar as variaveis de controle
        quem = true;
        contJogadas = 0;
        jogo = true;
    }
}
