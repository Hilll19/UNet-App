package hilworking.unetapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;
//import hilworking.unetapplication.messages.MesaageList;

public class Friends_Chat extends AppCompatActivity {

//    private final List<MesaageList> mesaageLists = new ArrayList<>();
    private String email;
    private String name;

    private RecyclerView messageRecyclerView;
//    private final String parentDBname = "users";

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://unet-base-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_chat);


        final CircleImageView userProfilePic = findViewById(R.id.profilePic);


        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");



        //get profile pic from firebase database
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                final String profilePicUrl = snapshot.child("users").child("name").child("profile_pic").getValue(String.class);
//
//                if (!profilePicUrl.isEmpty()){
//                    //set profile pic to circel image view
//                    Picasso.get().load(profilePicUrl).into(userProfilePic);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.child("users").getChildren()){

                    final String getName = dataSnapshot.getKey();
                    if (!getName.equals(name)){
                        final String getEmail = dataSnapshot.child("email").getValue(String.class);
                        final String getProfilePic = dataSnapshot.child("profile_pic").getValue(String.class);

//                        MesaageList mesaageList = new MesaageList(getEmail, getName, "", getProfilePic, 0 );
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}