package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BreedAdapter extends RecyclerView.Adapter<BreedAdapter.BreedViewHolder> {

    private List<String> breedList;

    //Onclick opt
    private OnItemClickListener onItemClickListener;

    public BreedAdapter(List<String> breedList) {
        this.breedList = breedList;
    }

    // Interface and event listner for itemview clicks
    public interface OnItemClickListener {
        void onItemClick(String selectedBreed);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    @NonNull
    @Override
    public BreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_breed, parent, false);
        return new BreedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedViewHolder holder, int position) {
        String breed = breedList.get(position);
        holder.bind(breed);

        //Intilizing setonclick Listner to the itemview

        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(breed);
            }
        });
    }

    @Override
    public int getItemCount() {
        return breedList.size();
    }

    public static class BreedViewHolder extends RecyclerView.ViewHolder {
        private TextView breedTextView;

        public BreedViewHolder(@NonNull View itemView) {
            super(itemView);
            breedTextView = itemView.findViewById(R.id.breed_text_view);
        }

        public void bind(String breed) {
            breedTextView.setText(breed);
        }
    }
}

