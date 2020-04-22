package edu.gsu.refreshx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoriteList extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);

        listView = (ListView) findViewById(R.id.favoritesList);

        final String[] drinkNames= {"Milk", "Chocolate", "Water"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drinkNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mydrinks = drinkNames[position];

                Toast.makeText(FavoriteList.this, "You've clicked on " + mydrinks, Toast.LENGTH_LONG).show();
            }
        });
    }

    /*ArrayList<DrinkInfo> drinks;
    boolean isDeleting = false;
    FavoriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
    }

    public void onResume () {
        super.onResume();

        FavoriteDataSource ds = new FavoriteDataSource(this);
        try {
            ds.open();
            drinks = ds.getFavoriteDrinks();
            ds.close();
            adapter = new FavoriteAdapter(this, drinks);
            setListAdapter(adapter);
        }
        catch (Exception e) {
            Toast.makeText(this, "Error retrieving drinks", Toast.LENGTH_LONG).show();
        }

        if (drinks.size() > 0) {
            ListView listView = getListView();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                    ApiResult.drinkinfo selectedDrink = response().body().get(position);
                    if (isDeleting) {
                        adapter.showDelete(position, itemClicked, FavoriteList.this, selectedDrink);
                    } else {
                        Intent intent = new Intent(FavoriteList.this, DrinkInfo.class);
                        intent.putExtra("drinkName", selectedDrink.getName());
                        startActivity(intent);
                    }
                }
            });
        }
        else {
            Intent intent = new Intent(FavoriteList.this, DrinkInfo.class);
            startActivity(intent);
        }
    }

    public void onClick(View view) {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
        int position = viewHolder.getAdapterPosition();
        int drinkId = drinks.get(position).getId();
        Intent i = new Intent(FavoriteList.this, DrinkRandomizer.class);
        i.putExtra("drinkId", drinkId);
        startActivity(i);
    }
     */

}
