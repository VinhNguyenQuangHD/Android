package com.example.myandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Library_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Library_frag extends Fragment {

    private ArrayList<Librarypage_personal_lib> personal_libs;

    private ArrayList<Book_overal> recommends_list;

    private RecyclerView recycleview,recycleview2;


    public Library_frag() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Library_frag newInstance(String param1, String param2) {
        Library_frag fragment = new Library_frag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library_frag, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycleview = view.findViewById(R.id.recycle_lib_personal);
        recycleview2 = view.findViewById(R.id.recommend_lib_slot);

        personal_libs = new ArrayList<>();
        recommends_list= new ArrayList<>();

        setAdapter();
        setAdapter2();
    }

    private void setAdapter2() {
        Librarypage_lib_recommend_adapter adapter2 = new Librarypage_lib_recommend_adapter(recommends_list);
        RecyclerView.LayoutManager manager2 = new LinearLayoutManager(getActivity().getApplicationContext());
        recycleview2.setLayoutManager(manager2);
        recycleview2.setItemAnimator(new DefaultItemAnimator());
        recycleview2.setAdapter(adapter2);
    }

    private void setAdapter() {
        Librarypage_personal_lib_adapter adapter = new Librarypage_personal_lib_adapter(personal_libs);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycleview.setLayoutManager(manager);
        recycleview.setItemAnimator(new DefaultItemAnimator());
        recycleview.setAdapter(adapter);
    }

}