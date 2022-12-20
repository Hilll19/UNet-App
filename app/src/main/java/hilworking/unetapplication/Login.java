package hilworking.unetapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button buttonLog;
    TextView gotoRegister;

    EditText FullNameLog, EmailLog, PasswordLog;

    String[] Items = {"Universitas Indonesia", "Universitas Gadjah Mada", "Universitas Diponegoro", "President University"};

    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

//    private final String parentDBname = "users";

    FirebaseAuth fAuth;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://unet-base-default-rtdb.firebaseio.com/");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText FullNameLog = findViewById(R.id.FullNameLog);
        final EditText EmailLog = findViewById(R.id.EmailLog);
        final EditText PasswordLog = findViewById(R.id.PasswordLog);

        buttonLog = findViewById(R.id.buttonLog);
        gotoRegister = findViewById(R.id.RegisterInLog);


        fAuth = FirebaseAuth.getInstance();

//        if(fAuth.getCurrentUser() != null) {
//            startActivity(new Intent(Login.this, MainActivity.class));
//        }


        autoCompleteTxt = findViewById(R.id.auto_complete_text);

        adapterItems = new ArrayAdapter<>(this,R.layout.list_item_university, Items);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item =parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: "+item, Toast.LENGTH_SHORT).show();
            }
        });
//        check users already logged
//        if (!MemoryData.getData(this).isEmpty()){
//            Intent intent = new Intent(Login.this, Friends_Chat.class);
//            intent.putExtra("email", MemoryData.getData(this));
//            intent.putExtra("name", MemoryData.getName(this));
//            startActivity(intent);
//            finish();
//        }
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nameTXT = FullNameLog.getText().toString();
                final String emailTXT = EmailLog.getText().toString();

                if (nameTXT.isEmpty() || emailTXT.isEmpty()){
                    Toast.makeText(Login.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child("users").hasChild(nameTXT)){
                                Toast.makeText(Login.this, "Name already exists", Toast.LENGTH_SHORT).show();
                            }else {
                                databaseReference.child("users").child(nameTXT).child("name").setValue(emailTXT);

                                //Save Email
                                MemoryData.saveData(emailTXT, Login.this);
                                //Save FullName
                                MemoryData.saveData(nameTXT, Login.this);

                                Toast.makeText(Login.this, "Succes", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.putExtra("name", nameTXT);
                                intent.putExtra("email", emailTXT);
                                startActivity(intent);
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                }

                fAuth.signInWithEmailAndPassword(EmailLog.getText().toString(), PasswordLog.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(Login.this, MainActivity.class);
//                            st=FullNameLog.getText().toString();
                            intent.putExtra("name",nameTXT);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }

                });

            }
        });
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });;
    }
}
