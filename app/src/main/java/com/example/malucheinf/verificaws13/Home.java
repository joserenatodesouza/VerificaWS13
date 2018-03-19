package com.example.malucheinf.verificaws13;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.malucheinf.verificaws13.WebService.WebServicesJSON;

import org.json.JSONObject;

public class Home extends AppCompatActivity {

    private Toolbar toolbar;
    private String WebServiceURL = "http://matrixnet.labvw.com.br:8080/matrixnet/wsrvProtocoloMatrix_v3.HACCR.svc?wsdl";
    //Layout
    private TextView resultado;
    private Button Atualiza;

    //Mensagens
    public String tituloErro;
    private String msgErro;

    //Layouts
    private RelativeLayout activity_home;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        resultado = (TextView) findViewById(R.id.resultado);
        Atualiza = (Button) findViewById(R.id.Atualiza);

        //WebService consulta
        Atualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lerJSONWS lu = new lerJSONWS(Home.this);
                lu.execute("");
            }
        });


    }
//Menu cria e option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.Cadastrar:
                Cadastros();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void Cadastros(){
        Intent intent = new Intent(Home.this, Cadastro.class);
        startActivity(intent);

    }
    public class lerJSONWS extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog;
        private JSONObject jsonObject;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(contexto);
            dialog.setMessage(getString(R.string.carregando));
            dialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
        }

        public lerJSONWS() {
            super();
        }

        public lerJSONWS(Context contexto) {
            super();
            setContexto(contexto);
        }

        private Context contexto;
        public Context getContexto() {
            return contexto;
        }
        public void setContexto(Context contexto) {
            this.contexto = contexto;
        }

        @Override
        protected String doInBackground(String... arg0) {

            try {
                WebServicesJSON c = new WebServicesJSON();
                String url = WebServiceURL;

                String retorno;
                Log.i("HOME", url);

                retorno = c.consultaDadosWEB(url);
                Log.i("HOME", retorno);
                resultado.setText(retorno);
                // jsonObject = c.processarRETORNOJSON(c.consultaDadosWEB(url));
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: handle exception
            }

            return null;
        }

    }
}
