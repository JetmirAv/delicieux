package edu.fiek.delicieux.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.fiek.delicieux.DetailsActivity;
import edu.fiek.delicieux.R;
import edu.fiek.delicieux.model.CookBook;


public class CookBookAdapter extends RecyclerView.Adapter<CookBookAdapter.CookBookViewHolder> {

    Context context;
    List<CookBook> cookBookList;


    public CookBookAdapter(Context context, List<CookBook> cookBookList) {
        this.context = context;
        this.cookBookList = cookBookList;
    }

    @NonNull
    @Override
    public CookBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cook_book_row_item, parent, false);
        return new CookBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CookBookViewHolder holder, int position) {

        holder.foodImage.setImageResource(cookBookList.get(position).getImageUrl());
        holder.name.setText(cookBookList.get(position).getName());
        holder.price.setText(cookBookList.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailsActivity.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cookBookList.size();
    }

    public static final class CookBookViewHolder extends RecyclerView.ViewHolder{

        ImageView foodImage;
        TextView price, name;

        public CookBookViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);

        }
    }
}
