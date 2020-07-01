package edu.fiek.delicieux.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.models.Ingredients;
import edu.fiek.delicieux.repositories.IngredientRepository;
import edu.fiek.delicieux.ui.list.ListFragment;
import edu.fiek.delicieux.ui.recipes.RecipesDetailFragment;

public class ListItemsAdapter extends RecyclerView.Adapter<ListItemsAdapter.ItemViewHolder> {

    private View.OnClickListener listener;
    private List<Ingredients> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    int btnName;


    // data is passed into the constructor
    public ListItemsAdapter(Context context, List<Ingredients> data, int btnName) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.btnName = btnName;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_row, parent, false);


        return new ItemViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        holder.item_name.setText(mData.get(position).getName());
        holder.item_qty.setText(mData.get(position).getQuantity());
        holder.button.setText(holder.button.getContext().getString(btnName));

        if (btnName == R.string.remove) {
            listener = new ListFragment.CustomOnClickListener();
            ((ListFragment.CustomOnClickListener) listener).setIngredient(mData.get(position));
        }

        if (btnName == R.string.add_to_cart) {
            listener = new RecipesDetailFragment.CustomOnClickListener();
            ((RecipesDetailFragment.CustomOnClickListener) listener).setIngredient(mData.get(position));
        }

        holder.button.setOnClickListener(listener);
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
    Ingredients getItem(int id) {
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

    public static final class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView item_name, item_qty;
        Button button;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            item_qty = itemView.findViewById(R.id.item_qty);
            button = itemView.findViewById(R.id.item_status);
        }
    }
}