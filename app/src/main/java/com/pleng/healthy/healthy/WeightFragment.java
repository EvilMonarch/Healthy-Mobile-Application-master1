package com.pleng.healthy.healthy;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class WeightFragment extends Fragment {

    ArrayList<WeightStore> weightStore = new ArrayList<>();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        weightStore.add(new WeightStore(50,"10-jul-50","UP"));
        weightStore.add(new WeightStore(60,"10-jul-50","UP"));
        weightStore.add(new WeightStore(70,"10-jul-50","UP"));
        ListView weightList = (ListView) getView().findViewById(R.id.weight_list);
        WeightConfigItem weightConfigItem =  new WeightConfigItem(
                getActivity(),
                R.layout.fragment_weight_items,
                weightStore
        );

        weightList.setAdapter(weightConfigItem);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight,container,false);
    }
}
