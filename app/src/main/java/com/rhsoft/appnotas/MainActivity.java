package com.rhsoft.appnotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerNotas;
    NotasAdapter notasAdapter;
    ArrayList<Nota> listaNotas;

    private static final int REQUEST_ADD_NOTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerNotas = findViewById(R.id.recyclerNotas);

        cargarNotas();

        recyclerNotas.setLayoutManager(new LinearLayoutManager(this));

        notasAdapter = new NotasAdapter(listaNotas, position -> abrirNota(position));
        recyclerNotas.setAdapter(notasAdapter);

        // Swipe para eliminar
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
        ) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                listaNotas.remove(position);
                notasAdapter.notifyItemRemoved(position);
                guardarNotas();
            }
        };

        new ItemTouchHelper(callback).attachToRecyclerView(recyclerNotas);

        findViewById(R.id.btnAgregar).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivityForResult(intent, REQUEST_ADD_NOTE);
        });
    }

    private void abrirNota(int position) {
        Intent i = new Intent(MainActivity.this, EditNoteActivity.class);
        i.putExtra("pos", position);
        i.putExtra("titulo", listaNotas.get(position).getTitulo());
        i.putExtra("contenido", listaNotas.get(position).getContenido());
        startActivityForResult(i, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Nueva nota
        if (requestCode == REQUEST_ADD_NOTE && resultCode == RESULT_OK) {
            String titulo = data.getStringExtra("titulo");
            String contenido = data.getStringExtra("contenido");

            String fecha = fechaActual();

            listaNotas.add(new Nota(titulo, contenido, fecha));
            notasAdapter.notifyItemInserted(listaNotas.size() - 1);
            guardarNotas();
        }

        // Eliminar desde EditNoteActivity
        if (requestCode == 2 && resultCode == RESULT_OK) {

            boolean eliminar = data.getBooleanExtra("eliminar", false);
            int pos = data.getIntExtra("pos", -1);

            if (eliminar && pos != -1) {
                listaNotas.remove(pos);
                notasAdapter.notifyItemRemoved(pos);
                guardarNotas();
                return;
            }

            // Editar nota
            String nuevoTitulo = data.getStringExtra("titulo");
            String nuevoContenido = data.getStringExtra("contenido");

            if (pos != -1) {
                listaNotas.get(pos).setTitulo(nuevoTitulo);
                listaNotas.get(pos).setContenido(nuevoContenido);
                listaNotas.get(pos).setFecha(fechaActual());

                notasAdapter.notifyItemChanged(pos);
                guardarNotas();
            }
        }
    }

    private String fechaActual() {
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }

    private void guardarNotas() {
        try {
            JSONArray jsonArray = new JSONArray();

            for (Nota nota : listaNotas) {
                JSONObject obj = new JSONObject();
                obj.put("titulo", nota.getTitulo());
                obj.put("contenido", nota.getContenido());
                obj.put("fecha", nota.getFecha());

                jsonArray.put(obj);
            }

            SharedPreferences prefs = getSharedPreferences("mis_notas", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("lista", jsonArray.toString());
            editor.apply();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarNotas() {
        listaNotas = new ArrayList<>();

        SharedPreferences prefs = getSharedPreferences("mis_notas", MODE_PRIVATE);
        String datos = prefs.getString("lista", "");

        if (!datos.equals("")) {
            try {
                JSONArray jsonArray = new JSONArray(datos);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);

                    String titulo = obj.getString("titulo");
                    String contenido = obj.getString("contenido");
                    String fecha = obj.getString("fecha");

                    listaNotas.add(new Nota(titulo, contenido, fecha));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}