package com.rhsoft.appnotas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.ViewHolder> {

    private ArrayList<Nota> listaNotas;
    private OnNotaClickListener listener;

    public interface OnNotaClickListener {
        void onNotaClick(int position);
    }

    public NotasAdapter(ArrayList<Nota> listaNotas, OnNotaClickListener listener) {
        this.listaNotas = listaNotas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nota, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Nota nota = listaNotas.get(position);

        holder.txtTitulo.setText(nota.getTitulo());
        holder.txtFecha.setText(nota.getFecha());
    }

    @Override
    public int getItemCount() {
        return listaNotas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitulo, txtFecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitulo = itemView.findViewById(R.id.txtTituloNota);
            txtFecha  = itemView.findViewById(R.id.txtFecha);

            // Cuando el usuario toca una nota
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onNotaClick(getAdapterPosition());
                }
            });
        }
    }
}