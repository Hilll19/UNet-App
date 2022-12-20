package hilworking.unetapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Public_Discussion extends AppCompatActivity {

    RecycleViewAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Message_Design> list;
    TextInputLayout message;

    FloatingActionButton send;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://unet-base-default-rtdb.firebaseio.com/");

    FirebaseAuth fAuth;
    FirebaseUser fusers;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_discussion);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        fusers = fAuth.getCurrentUser();
        String userID = fusers.getUid();
        String uEmail = fusers.getEmail();
        String timeStamp = new SimpleDateFormat("dd-MM-yy HH.mma").format(Calendar.getInstance().getTime());

        send = findViewById(R.id.fab_send);
        message = findViewById(R.id.message);
        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = message.getEditText().getText().toString();
                databaseReference.child("users").push().setValue(new Message_Design(uEmail,msg, timeStamp )).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        message.getEditText().setText("");

                    }
                });

            }
        });

        adapter = new RecycleViewAdapter(this, list);
        LinearLayoutManager llm = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        receiveMessage();
    }

    private void receiveMessage(){
        databaseReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snap:snapshot.getChildren()){
                    Message_Design message_design = snap.getValue(Message_Design.class);
                    list.add(message_design);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}