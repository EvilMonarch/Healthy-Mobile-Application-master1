package com.pleng.healthy.healthy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WeightConfigItem extends ArrayAdapter<WeightStore>{

    List<WeightStore> weightStore = new ArrayList<WeightStore>();
    Context context;

    public WeightConfigItem(@NonNull Context context, int resource,List<WeightStore> list) {
        super(context, resource, list);
        this.context = context;
        this.weightStore = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View weightItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_weight_items,
                parent,
                false
        );
        TextView weight = (TextView)weightItem.findViewById(R.id.weight_fragment_weight);
        TextView date = (TextView)weightItem.findViewById(R.id.weight_fragment_date);
        TextView status = (TextView)weightItem.findViewById(R.id.weight_fragment_status);

        weight.setText(weightStore.get(position).getWeight()+"");
        date.setText(weightStore.get(position).getDate());
        status.setText(weightStore.get(position).getStatus());

        return weightItem;
    }
}
