package com.example.sonicochat.admin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sonicochat.R;
import com.example.sonicochat.admin.adapter.SearchUserResyclerAdapter;
import com.example.sonicochat.database.Users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchUserActivity extends AppCompatActivity {

    EditText searchInput;
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Users, SearchUserResyclerAdapter.UserModelViewHolder> adapter;
    DatabaseReference usersRef;
    ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user_activiry);

        searchInput = findViewById(R.id.search_username_input);
        recyclerView = findViewById(R.id.search_user_recycler_view);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setupRecyclerView();

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void setupRecyclerView() {
        Query query = usersRef.orderByChild("userName");

        FirebaseRecyclerOptions<Users> options = new FirebaseRecyclerOptions.Builder<Users>()
                .setQuery(query, Users.class)
                .build();

        adapter = new SearchUserResyclerAdapter(options, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void filter(String text) {
        Query query;
        if (text.isEmpty()) {
            query = usersRef.orderByChild("userName");
        } else {
            query = usersRef.orderByChild("userName")
                    .startAt(text.toLowerCase())
                    .endAt(text.toLowerCase() + "\uf8ff");
        }

        FirebaseRecyclerOptions<Users> options = new FirebaseRecyclerOptions.Builder<Users>()
                .setQuery(query, Users.class)
                .build();

        // Mettre à jour les options de l'adaptateur avec la nouvelle requête
        adapter.updateOptions(options);
        adapter.notifyDataSetChanged(); // Notifier l'adaptateur des changements
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
