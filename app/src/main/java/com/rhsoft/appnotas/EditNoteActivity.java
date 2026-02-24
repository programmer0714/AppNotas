package com.rhsoft.appnotas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {

    EditText edtTitulo, edtContenido;
    Button btnGuardarCambios, btnBack, btnEliminarNota;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        edtTitulo = findViewById(R.id.edtTitulo);
        edtContenido = findViewById(R.id.edtContenido);

        btnGuardarCambios = findViewById(R.id.btnGuardarCambios);
        btnBack = findViewById(R.id.btnBack);
        btnEliminarNota = findViewById(R.id.btnEliminarNota);

        position = getIntent().getIntExtra("pos", -1);
        String titulo = getIntent().getStringExtra("titulo");
        String contenido = getIntent().getStringExtra("contenido");

        edtTitulo.setText(titulo);
        edtContenido.setText(contenido);

        btnBack.setOnClickListener(v -> finish());

        btnGuardarCambios.setOnClickListener(v -> {
            Intent result = new Intent();
            result.putExtra("pos", position);
            result.putExtra("titulo", edtTitulo.getText().toString());
            result.putExtra("contenido", edtContenido.getText().toString());
            setResult(RESULT_OK, result);
            finish();
        });

        btnEliminarNota.setOnClickListener(v -> {
            Intent result = new Intent();
            result.putExtra("eliminar", true);
            result.putExtra("pos", position);
            setResult(RESULT_OK, result);
            finish();
        });
    }
}