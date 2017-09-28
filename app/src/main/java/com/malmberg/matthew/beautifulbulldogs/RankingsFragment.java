package com.malmberg.matthew.beautifulbulldogs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankingsFragment extends Fragment {

    private ListView bulldogList;

    public RankingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen


        View view = inflater.inflate(R.layout.fragment_rankings, container, false);

        bulldogList = (ListView) view.findViewById(R.id.bulldog_list);
        MainActivity mainActivity = (MainActivity) this.getActivity();

        RankingsAdapter adapter = new BulldogArrayAdapter(this.getActivity(), this.getRankings());
        bulldogList.setAdapter(adapter);

        return view;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.bulldog_cell, parent, false);

        TextView name = rowView.findViewById(R.id.name_label);
        TextView age = rowView.findViewById(R.id.age_label);
        name.setText(getItem(position).getName());
        age.setText(String.valueOf(getItem(position).getVotes().average("rating")));

        return rowView;
    }
    @Override
    public void onResume() {
        super.onResume();

        RankingsAdapter adapter = new RankingsAdapter(this.getActivity(), this.getRankings());
        bulldogList.setAdapter(adapter);
    }

    public ArrayList<Bulldog> getRankings() {
        ArrayList<Bulldog> bulldogs = new ArrayList(mainActivity.realm.where(Bulldog.class).findAll());

        Collections.sort(bulldogs, new Comparator<Bulldog>() {
            @Override
            public int compare(Bulldog bulldog, Bulldog bulldog2) {
                return ((Double) bulldog2.getVotes().average("rating")).compareTo((Double) bulldog.getVotes().average("rating"));
            }
        });


        return bulldogs;
    }
}
