package com.izv.dam.newquip.vistas.notas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.izv.dam.newquip.R;
import com.izv.dam.newquip.contrato.ContratoNota;

import com.izv.dam.newquip.pojo.Nota;
import com.izv.dam.newquip.vistas.VistaMapa;
import com.izv.dam.newquip.vistas.VistaMapaVisualizar;

public class VistaNota extends AppCompatActivity implements ContratoNota.InterfaceVista {

    private EditText editTextTitulo, editTextNota;
    private Nota nota = new Nota();
    private PresentadorNota presentador;
    private TextView tvLocalizacion;

    private Button btLocalizador;
    private  Button btVisualizador;
    private Context c = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        presentador = new PresentadorNota(this);

        editTextTitulo = (EditText) findViewById(R.id.etTitulo);
        editTextNota = (EditText) findViewById(R.id.etNota);
        btLocalizador = (Button) findViewById(R.id.btObetenrUbicacion);
        btVisualizador = (Button) findViewById(R.id.btVisualizarLocalizacion);
        tvLocalizacion =(TextView) findViewById(R.id.tvLocalizador);

        if (savedInstanceState != null) {
            nota = savedInstanceState.getParcelable("nota");
        } else {
            Bundle b = getIntent().getExtras();
            if(b != null ) {
                nota = b.getParcelable("nota");
            }
        }
        mostrarNota(nota);

        btLocalizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,nota.getLocalizacion(),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(c, VistaMapa.class);
                startActivityForResult(i,111);
            }
        });

        btVisualizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, VistaMapaVisualizar.class);
                Bundle b = new Bundle();
                b.putString("localizacion" , nota.getLocalizacion());
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case 111:
                    String localizacion = data.getExtras().getString("localizacion");
                    nota.setLocalizacion(localizacion);
                    tvLocalizacion.setText(nota.getLocalizacion());
                    break;
            }
        }
    }

    @Override
    protected void onPause() {
        saveNota();
        presentador.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        presentador.onResume();
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("nota", nota);
    }

    @Override
    public void mostrarNota(Nota n) {
        editTextTitulo.setText(nota.getTitulo());
        editTextNota.setText(nota.getNota());
        tvLocalizacion.setText(nota.getLocalizacion());
    }

    private void saveNota() {
        nota.setTitulo(editTextTitulo.getText().toString());
        nota.setNota(editTextNota.getText().toString());
        long r = presentador.onSaveNota(nota);
        if(r > 0 & nota.getId() == 0){
            nota.setId(r);
        }
    }
}
/*
* TODO Hacer base de datos OrmLite
*/