package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Entite.Enseignant;
import java.util.List;

public class EnseignantAdapter extends RecyclerView.Adapter<EnseignantAdapter.EnseignantViewHolder> {

    private List<Enseignant> enseignants;

    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EnseignantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enseignant, parent, false);
        return new EnseignantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EnseignantViewHolder holder, int position) {
        Enseignant enseignant = enseignants.get(position);
        holder.nomTextView.setText("Nom: " + enseignant.getNom());
        holder.prenomTextView.setText("Prenom: " + enseignant.getPrenom());
        holder.emailTextView.setText("Email: " + enseignant.getEmail());
        holder.matiereTextView.setText("Matière: " + enseignant.getMatiere());
    }

    @Override
    public int getItemCount() {
        return enseignants != null ? enseignants.size() : 0;
    }

    static class EnseignantViewHolder extends RecyclerView.ViewHolder {
        TextView nomTextView;
        TextView prenomTextView;
        TextView emailTextView;
        TextView matiereTextView;

        public EnseignantViewHolder(@NonNull View itemView) {
            super(itemView);
            nomTextView = itemView.findViewById(R.id.nomTextView);
            prenomTextView = itemView.findViewById(R.id.prenomTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            matiereTextView = itemView.findViewById(R.id.matiereTextView);
        }
    }
}
