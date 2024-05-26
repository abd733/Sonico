package com.example.sonicochat.database;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sonicochat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chatwindo extends AppCompatActivity implements OnUserSelectedListener{
    String receiverImg, receiverUid, receiverName, senderUid;
    CircleImageView profile;
    TextView receiverNameTextView;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    public static String senderImg;
    public static String receiverImgUrl;
    CardView sendButton;
    EditText messageEditText;

    String senderRoom, receiverRoom;
    RecyclerView messageRecyclerView;
    ArrayList<MsgModelclass> messageList;
    MessagesAdpter messageAdapter;
    ImageView cameraButton;
    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_GALLERY = 2;

    private void showPhotoPickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Photo");
        builder.setItems(new CharSequence[]{"Camera", "Gallery"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // Camera
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_CAMERA);
                        }
                        break;
                    case 1: // Gallery
                        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhotoIntent, REQUEST_GALLERY);
                        break;
                }
            }
        });
        builder.show();
    }

    private boolean hasPermissions() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA && data != null) {
                // Handle the camera photo
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                // Use the imageBitmap as needed
            } else if (requestCode == REQUEST_GALLERY && data != null) {
                // Handle the gallery photo
                Uri selectedImage = data.getData();
                // Use the selectedImage URI as needed
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            boolean cameraPermissionGranted = false;
            boolean storagePermissionGranted = false;

            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals(Manifest.permission.CAMERA)) {
                    cameraPermissionGranted = grantResults[i] == PackageManager.PERMISSION_GRANTED;
                }
                if (permissions[i].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    storagePermissionGranted = grantResults[i] == PackageManager.PERMISSION_GRANTED;
                }
            }

            if (cameraPermissionGranted && storagePermissionGranted) {
                showPhotoPickerDialog();
            } else {
                Toast.makeText(this, "Permissions are required to use this feature", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatwindo);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initializeFirebase();
        initializeUI();
        loadDataFromIntent();
        setupRecyclerView();
        loadUserData();
        loadMessages();
        setupListeners();
    }

    private void initializeFirebase() {
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        senderUid = firebaseAuth.getUid();
    }

    private void initializeUI() {
        sendButton = findViewById(R.id.sendbtnn);
        messageEditText = findViewById(R.id.textmsg);
        receiverNameTextView = findViewById(R.id.recivername);
        profile = findViewById(R.id.profileimgg);
        messageRecyclerView = findViewById(R.id.msgadpter);
        cameraButton = findViewById(R.id.camera_message);
    }

    private void loadDataFromIntent() {
        receiverName = getIntent().getStringExtra("name");
        receiverImg = getIntent().getStringExtra("receiverImg");
        receiverUid = getIntent().getStringExtra("uid");
        senderRoom = senderUid + receiverUid;
        receiverRoom = receiverUid + senderUid;
        receiverNameTextView.setText(receiverName);
        Picasso.get().load(receiverImg).into(profile);
    }

    private void setupRecyclerView() {
        messageList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        messageRecyclerView.setLayoutManager(linearLayoutManager);
        messageAdapter = new MessagesAdpter(this, messageList);
        messageRecyclerView.setAdapter(messageAdapter);
    }

    private void loadUserData() {
        DatabaseReference reference = database.getReference().child("Users").child(senderUid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                senderImg = snapshot.child("profilepic").getValue(String.class);
                receiverImgUrl = receiverImg;
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

    private void loadMessages() {
        DatabaseReference chatReference = database.getReference().child("chats").child(senderRoom).child("messages");
        DatabaseReference chatReceiver = database.getReference().child("chats").child(receiverRoom).child("messages");
        chatReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MsgModelclass messages = dataSnapshot.getValue(MsgModelclass.class);
                    messageList.add(messages);
                }
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
        chatReceiver.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MsgModelclass mesage = dataSnapshot.getValue(MsgModelclass.class);
                    messageList.add(mesage);
                }
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupListeners() {
        sendButton.setOnClickListener(view -> sendMessage());
        cameraButton.setOnClickListener(view -> {
            if (hasPermissions()) {
                showPhotoPickerDialog();
            } else {
                requestPermissions();
            }
        });
    }

    private void sendMessage() {
        String message = messageEditText.getText().toString();
        if (message.isEmpty()) {
            Toast.makeText(this, "Enter The Message First", Toast.LENGTH_SHORT).show();
            return;
        }

        messageEditText.setText("");
        MsgModelclass messages = new MsgModelclass(message, senderUid, new Date().getTime());
        DatabaseReference chatRef = database.getReference().child("chats");
        chatRef.child(senderRoom).child("messages").push().setValue(messages);


        // Envoyer le message à la salle de chat du récepteur uniquement
        chatRef.child(receiverRoom).child("messages").push().setValue(messages)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Chatwindo.this, "Message Sent", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Chatwindo.this, "Failed to send message", Toast.LENGTH_SHORT).show();
                        Log.e("SendMessage", "Error: " + task.getException());
                    }
                });
    }


    @Override
    public void onUserSelected(String userId, String userName, String userImageUrl) {
        Intent intent = new Intent(this, Chatwindo.class);
        intent.putExtra("uid", userId);
        intent.putExtra("nameeee", userName);
        intent.putExtra("receiverImg", userImageUrl);
        startActivity(intent);
    }
}
