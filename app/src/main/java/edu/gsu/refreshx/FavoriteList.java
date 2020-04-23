package edu.gsu.refreshx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoriteList extends AppCompatActivity {
    private ApiResult.User user;
    ArrayList<String> listDrinks = new ArrayList<String>();
    ArrayList<String> drinkIds = new ArrayList<String>();
    
    ListView listView;
    public String drink_id;
    private String dname, recipe, warnings, website, image, info;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent;
        if(item.getItemId() == R.id.signout_menu){
            intent = new Intent(this, LoginActivity.class);
        }
        if(item.getItemId() == R.id.goback_menu){
            intent = new Intent(this, DrinkRandomizer.class);
        }
        if(item.getItemId() == R.id.favorites_menu){
            return true;
        }
        else{
            intent = new Intent(this, FavoriteList.class);
        }
        startActivity(intent);
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_bar, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.favoritesList);
        user = new Gson().fromJson(getIntent().getStringExtra("authenticated_user"), ApiResult.User.class);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://76.20.217.228/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        //get drink_id from user_id
        final Call<ApiResult> result = jsonPlaceHolderApi.getList(user.getId(),drink_id);
        System.out.println(result.toString());
        //request from db
        result.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Invalid drink id", Toast.LENGTH_LONG).show();
                    return;
                }
                //when request successfully
                else {
//                    List<String> temp = new ArrayList<String>();
                    final List<String> drink_name = new ArrayList<String>();
                    ApiResult.drinkList drinkList;
                    // add all drink_id of that user_id to temp array list
                    for (int i = 0; i < response.body().getDrinklist().size(); i++) {
                        drinkList = response.body().getDrinklist().get(i);
                        drinkIds.add(drinkList.getDrink_id());
                    }
                    System.out.println("Inside the queue....................................");
                    Retrofit retrofit1 = new Retrofit.Builder()
                            .baseUrl("http://76.20.217.228/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    final JsonPlaceHolderApi jsonPlaceHolderApi1 = retrofit1.create(JsonPlaceHolderApi.class);

                    final String[] drinkNames = {};
                    //Get the name for each drink
                    for (int j = 0; j < drinkIds.size(); j++) {
                        //get drink name from those drink id

                        Call<ApiResult> result = jsonPlaceHolderApi1.getdrink(Integer.valueOf(drinkIds.get(j)), dname, recipe, warnings, website, image);
                        result.enqueue(new Callback<ApiResult>() {
                            @Override
                            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                                if (!response.isSuccessful()) {
                                    System.out.println("fail");
                                    return;
                                } else {
                                    System.out.println("success");
                                    ApiResult.drinkinfo drinkinfo = response.body().getDrink().get(0);
                                    System.out.println(drinkinfo.getName());
                                    String name = drinkinfo.getName();
                                    listDrinks.add(name);
                                    System.out.println("added successfully, now list size become: " + listDrinks.size());
                                    updateDisplay();
                                }
                            }

                            @Override
                            public void onFailure(Call<ApiResult> call, Throwable t) {
                                System.out.println("no connect");
                            }
                        });
                    }
                }
            }
            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Cannot connect to server", Toast.LENGTH_LONG).show();
            }
        });
       // final String[] drinkNames= {"Milk", "Chocolate", "Water"};
    }

    private void updateDisplay() {
        super.onStart();

        System.out.println("listdrink outside for loop and the size =" + listDrinks.size() + "............");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(FavoriteList.this, android.R.layout.simple_list_item_1, listDrinks);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mydrinks = listDrinks.get(position);

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
