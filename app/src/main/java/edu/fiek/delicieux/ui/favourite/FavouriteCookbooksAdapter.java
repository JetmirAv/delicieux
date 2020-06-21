package edu.fiek.delicieux.ui.favourite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.model.CookBook;

public class FavouriteCookbooksAdapter extends RecyclerView.Adapter<FavouriteCookbooksAdapter.ViewHolder> {

    private ArrayList<CookBook> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    FavouriteCookbooksAdapter(Context context, ArrayList<CookBook> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cook_book_row_item, parent, false);

        ConstraintLayout layout = view.findViewById(R.id.con_layout_cookbook_detail_view);
        layout.setMaxWidth(450);
        layout.setMaxHeight(675);
        TextView textView = view.findViewById(R.id.name);
        textView.setTextSize(12);


        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.myTextView.setText(mData[position]);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }

    }

    // convenience method for getting data at click position
    CookBook getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}