package hilworking.unetapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView ShowName;
    String st;
    ImageView imglogout;
    LinearLayout mtrlImg1, mtrlImg2, mtrlImg3, mtrlImg4;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imglogout = findViewById(R.id.imgLogout);
        mtrlImg1 = findViewById(R.id.mtrlImg1);
        mtrlImg2 = findViewById(R.id.mtrlImg2);
        mtrlImg3 = findViewById(R.id.mtrlImg3);
        mtrlImg4 = findViewById(R.id.mtrlImg4);
        ShowName = findViewById(R.id.ShowName);

        fAuth = FirebaseAuth.getInstance();

        imglogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

        mtrlImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Public_Discussion.class);
                startActivity(intent);
            }
        });
        mtrlImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Friends_Chat.class);
                startActivity(intent);
            }
        });
        mtrlImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Jurnal_Umum.class);
                startActivity(intent);
            }
        });
        mtrlImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Info_Beasiswa.class);
                startActivity(intent);
            }
        });

        st=fAuth.getCurrentUser().getEmail();
        ShowName.setText(st);
    }
}