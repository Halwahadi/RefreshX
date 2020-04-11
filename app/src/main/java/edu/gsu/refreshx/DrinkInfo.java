package edu.gsu.refreshx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class DrinkInfo extends Fragment {

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

        int drinkId = getArguments().getInt("drinkID");
        TextView name = view.findViewById(R.id.drink_name);
        TextView id = view.findViewById(R.id.drink_id);

        String[] data = lookupDrink(drinkId);

        if (data != null) {
            name.setText("Name: "+ data[0]);
            id.setText("ID: "+data[1]);
        }
        else {
            name.setText("Something Went Wrong");
            id.setText("Something Went Wrong");
        }
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
    private String[] lookupDrink(int id){
        String[] temp = new String[2];
        switch (id){
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

    }
}
