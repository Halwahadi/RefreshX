package edu.gsu.refreshx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class Randomizer extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_randomizer, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up onclick of button and goes to next fragment bases on nav_graph.xml
        view.findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int drinkId =  (int) (Math.random()*12+1);
                Bundle args = new Bundle();
                args.putInt("drinkID", drinkId);
                NavHostFragment.findNavController(Randomizer.this).navigate(R.id.SelectDrink,args);
            }
        });
    }
}
