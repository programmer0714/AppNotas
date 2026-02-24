package com.rhsoft.appnotas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    EditText editTitulo, editContenido;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTitulo = findViewById(R.id.editTitulo);
        editContenido = findViewById(R.id.editContenido);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> guardarNota());
    }

    private void guardarNota() {
        String titulo = editTitulo.getText().toString();
        String contenido = editContenido.getText().toString();

        if (titulo.isEmpty()) {
            editTitulo.setError("Escribe un título");
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("titulo", titulo);
        intent.putExtra("contenido", contenido);

        setResult(RESULT_OK, intent);
        finish();
    }
}