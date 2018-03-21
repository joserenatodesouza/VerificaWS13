package com.example.malucheinf.verificaws13;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.example.malucheinf.verificaws13.Banco.BancoController;
import com.example.malucheinf.verificaws13.Banco.SQLite;

import java.net.URL;

public class Cadastro extends AppCompatActivity {

    private EditText NomeWS;
    private EditText URL;
    private Button Salvar;
    private Switch Ativo;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Salvar = (Button) findViewById(R.id.salvar);

        Salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BancoController crud = new BancoController(getBaseContext());
                NomeWS = (EditText) findViewById(R.id.nome);
                URL = (EditText) findViewById(R.id.url);
                Ativo = (Switch) findViewById(R.id.ativo);
                String nomeString = NomeWS.getText().toString();
                String urlWS = URL.getText().toString();
                String ativo = String.valueOf(Ativo.isChecked());
                String resultado;

                resultado = crud.insereDado(nomeString,urlWS,ativo);

                Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
            }
        });

        //Consulta Dados
        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {SQLite.nome, SQLite.Ativo};
        int[] idViews = new int[] {R.id.nome, R.id.ativo};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_cadastro,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);
    }

}
