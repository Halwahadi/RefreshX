package edu.gsu.refreshx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FavoriteAdapter extends ArrayAdapter<DrinkInfo> {

    private ArrayList<DrinkInfo> items;
    private Context adapterContext;

    public FavoriteAdapter (Context context, ArrayList<DrinkInfo> items) {
        super(context, R.layout.favorite_list_item, items);
        adapterContext = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        try {
            DrinkInfo drink = items.get(position);

            if(v == null) {
                LayoutInflater vi = (LayoutInflater) adapterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.favorite_list_item, null);
            }

            TextView drinkNum = (TextView) v.findViewById(R.id.textDrinkNumber);
            TextView drinkName = (TextView) v.findViewById(R.id.textDrinkName);
            Button deleteButton = (Button) v.findViewById(R.id.buttonDeleteFavorite);

            // Set the drink number and drink name from ApiResults HERE
            drinkNum.setText(drink.getId());
            drinkName.setText(drink.toString()); //change this
            deleteButton.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return v;
    }

    /* public void showDelete(final int position, final View convertView,final Context context, final DrinkInfo drink) {
        View v = convertView;
        final Button b = (Button) v.findViewById(R.id.buttonDeleteFavorite);
        //2
        if (b.getVisibility()==View.INVISIBLE) {
            b.setVisibility(View.VISIBLE);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideDelete(position, convertView, context);
                    items.remove(drink);
                    deleteOption(drink.getId(), context);
                }
            });
        }
        else {
            hideDelete(position, convertView, context);
        }
    }

    private void deleteOption(int drinkToDelete, Context context) {
        FavoriteDataSource db = new FavoriteDataSource(context);
        try {
            db.open();
            db.deleteFavortieDrink(drinkToDelete);
            db.close();
        }
        catch (Exception e) {
            Toast.makeText(adapterContext, "Delete Favorite Drink Failed", Toast.LENGTH_LONG).show();
        }
        this.notifyDataSetChanged();
    }

    */

    public void hideDelete(int position, View convertView, Context context) {
        View v = convertView;
        final Button b = (Button) v.findViewById(R.id.buttonDeleteFavorite);
        b.setVisibility(View.INVISIBLE);
        b.setOnClickListener(null);
    }

    public class FavortiesAdapter extends RecyclerView.ViewHolder {

        private ArrayList<DrinkInfo> drinkData; //Holds drink objects
        private View.OnClickListener onItemClickListener;

        public FavortiesAdapter(@NonNull View itemView) {
            super(itemView);
        }

        public class FavoriteViewHolder extends RecyclerView.ViewHolder {

            //variables or the FavoriteViewHolder class to reference new TextView and Button widgets
            public TextView textViewDrink;
            public TextView textViewNumber;
            public Button deleteButton;
            public FavoriteViewHolder(@NonNull View itemView) {
                super(itemView);
                //assign above variables to itemView variables in layout
                textViewDrink = itemView.findViewById(R.id.textDrinkName);
                textViewNumber = itemView.findViewById(R.id.textDrinkNumber);
                deleteButton = itemView.findViewById(R.id.buttonDeleteFavorite);
                itemView.setTag(this);
                itemView.setOnClickListener(onItemClickListener);
            }

            //methods to return widgets added
            public TextView getTextViewDrink() {
                return textViewDrink;
            }

            public TextView getTextViewNumber() {
                return textViewNumber;
            }

            public Button getDeleteButton() {
                return deleteButton;
            }

            /*
            * Drink name and number are displayed in ViewHolder
            *   - the position is used to retrieve drink from ArrayList
            *   - drink object methods are used to get the required values
            * */
            public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
                FavoriteViewHolder fvh = (FavoriteViewHolder) holder;
                fvh.getTextViewDrink().setText(drinkData.get(position).toString()); //change this to get particular part of ApiResult
                fvh.getTextViewNumber().setText(drinkData.get(position).getId());
            }
        }
    }


}
