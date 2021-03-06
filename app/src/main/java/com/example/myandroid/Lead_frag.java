package com.example.myandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Lead_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Lead_frag extends Fragment {

    private RecyclerView recyclerView, recyclerview2;
    private Leaderboardpage_top_3_best_adapter adapter;
    private ArrayList<Book_overal> list,list2;

    private Leaderboardpage_leaderboard_detail_adapter adapter2;

    public Lead_frag() {
        // Required empty public constructor
    }

    public static Lead_frag newInstance(String param1, String param2) {
        Lead_frag fragment = new Lead_frag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lead_frag, container, false);

        recyclerview2 = (RecyclerView)view.findViewById(R.id.leaderboard_detail_view) ;
        recyclerView = (RecyclerView) view.findViewById(R.id.top_3_best_view);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        recyclerview2.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference("sach");

            databaseReference.orderByChild("book_watch")
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            Book_overal book = snapshot.getValue(Book_overal.class);
                            if (book!=null){
                                list.add(0,book);
                                adapter.notifyDataSetChanged();

                            }
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        adapter2 = new Leaderboardpage_leaderboard_detail_adapter(list);
        recyclerview2.setAdapter(adapter2);
        adapter = new Leaderboardpage_top_3_best_adapter(list);
        recyclerView.setAdapter(adapter);

        return view;
    }
}