package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Entite.Club;
import com.example.myapplication.Entite.Enseignant;

import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder> {

    private List<Club> clubs;
    private int selectedItem = RecyclerView.NO_POSITION;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public ClubAdapter(List<Club> clubs, Context context) {
        this.clubs = clubs;
        this.context=context;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club, parent, false);
        return new ClubViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {


        Club club = clubs.get(position);
        holder.nomTextView.setText(club.getNom());
        holder.presidentTextView.setText(club.getPresident());
        holder.vicepTextView.setText(club.getVicep());
        holder.descriptionTextView.setText(club.getDescription());

        // Highlight the selected item
        holder.itemView.setActivated(position == selectedItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the clicked enseignant
                Club club = clubs.get(position);

                // Launch EnseignantDetail activity
                Intent intent = new Intent(context, clubDetails.class);
                intent.putExtra("CLUB_ID", club.getId());
                context.startActivity(intent);
                // Update selected item and notify the adapter
             //   selectedItem = holder.getAdapterPosition();
               // notifyDataSetChanged();

                // Notify the activity/fragment about the item click
                //if (onItemClickListener != null) {
                  //  onItemClickListener.onItemClick(selectedItem);
               // }
            }
        });

    }

    @Override
    public int getItemCount() {
        return clubs != null ? clubs.size() : 0;
    }

    static class ClubViewHolder extends RecyclerView.ViewHolder {

        TextView nomTextView;
        TextView presidentTextView;
        TextView vicepTextView;
        TextView descriptionTextView;


        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);

            nomTextView = itemView.findViewById(R.id.nomTextView);
            presidentTextView = itemView.findViewById(R.id.presidentTextView);
            vicepTextView = itemView.findViewById(R.id.vicepTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
