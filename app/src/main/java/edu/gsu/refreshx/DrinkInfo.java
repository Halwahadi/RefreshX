package edu.gsu.refreshx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DrinkInfo extends Fragment {
    private TextView name;
    private  TextView id;
    private String dname, recipe, warnings, website, image, info;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drink_info, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.drink_name);
        id = view.findViewById(R.id.drink_id);

        final int drinkId = getArguments().getInt("drinkID");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://76.20.217.228/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ApiResult> result = jsonPlaceHolderApi.getdrink(drinkId, dname, recipe, warnings, website, image);
        System.out.println(result.toString());
        result.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid drink id", Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    ApiResult.drinkinfo drinkinfo = response.body().getDrink().get(0);
                    name.setText("Name: "+ drinkinfo.getName());
                    id.setText("ID: "+ drinkId);
                }
            }
            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Cannot connect to server", Toast.LENGTH_LONG).show();
            }
        });
        
        /*String[] data = lookupDrink(drinkId, dname,recipe, warnings, website, image);
        if (data != null) {
            name.setText("Name: "+ data[0]);
            id.setText("ID: "+data[1]);
        }
        else {
            name.setText("Something Went Wrong");
            id.setText("Something Went Wrong");
        }*/
        //Set up return onclick
        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DrinkInfo.this)
                        .navigate(R.id.Return);
            }
        });
    }

    /**
     * LookupDrink is a function to look up the data needed for a drink to be displayed
     * Provides mocked up data. If out of range returns null array
     * @param id of drink
     * @return String array filled with values of drink or null if invalid id
     */
    private String[] lookupDrink(int id, String name, String recipe, String warnings, String website, String image){


       /* switch (id){
            case 0:
                temp[0] = "water";
                temp[1] = "" + id;
                return temp;
            case 1:
                temp[0] = "Coke";
                temp[1] = "" + id;
                return temp;
            case 2:
                temp[0] = "Sprite";
                temp[1] = "" + id;
                return temp;
            case 3:
                temp[0] = "Fanta";
                temp[1] = "" + id;
                return temp;
            default:
                return null;
        }
*/
       return null;
    }
}
