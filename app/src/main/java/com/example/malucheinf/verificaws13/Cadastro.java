package com.example.malucheinf.verificaws13;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.example.malucheinf.verificaws13.Banco.BancoController;
import com.example.malucheinf.verificaws13.Banco.Model;
import com.example.malucheinf.verificaws13.Banco.SQLite;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class Cadastro extends AppCompatActivity {
    private List<Model> listaws;
    private EditText NomeWS;
    private EditText URL;
    private Button Salvar;
    private Switch Ativo;
    private ListView lista;
    private Context context;

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
                String id = "1";
                String nomeString = NomeWS.getText().toString();
                String urlWS = URL.getText().toString();
                String ativo = String.valueOf(Ativo.isChecked());
                String resultado;

                resultado = crud.insereDado(id, nomeString,urlWS,ativo);

                Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
            }
        });
        context = Cadastro.this;
        //Consulta Dados
        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {SQLite.nome, SQLite.Ativo};
//        //String[] nomeCampos = new String[] {"id","nome","url","ativo"};
        int[] idViews = new int[] {R.id.nome, R.id.ativo};

        Log.i("Cadastro","Antes do cursor adapter");

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.listview, cursor,nomeCampos,idViews, 0);

        Log.i("Cadastro","depois do cursor adapter");
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);
    }

}
