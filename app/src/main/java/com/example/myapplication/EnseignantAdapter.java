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
    private int selectedItem = RecyclerView.NO_POSITION;
    private OnItemClickListener onItemClickListener;

    public EnseignantAdapter(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
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

        holder.nomTextView.setText(enseignant.getNom());
        holder.prenomTextView.setText(enseignant.getPrenom());
        holder.emailTextView.setText(enseignant.getEmail());
        holder.matiereTextView.setText(enseignant.getMatiere());

        // Highlight the selected item
        holder.itemView.setActivated(position == selectedItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update selected item and notify the adapter
                selectedItem = holder.getAdapterPosition();
                notifyDataSetChanged();

                // Notify the activity/fragment about the item click
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(selectedItem);
                }
            }
        });

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
