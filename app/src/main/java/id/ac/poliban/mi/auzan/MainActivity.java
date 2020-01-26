package id.ac.poliban.mi.auzan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//private Button btnStore, btnGetall;
//private EditText etname;
//private DatabaseHelper databaseHelper;
//private TextView tvnames;
//private ArrayList<String> arrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        TextView tvnames = findViewById(R.id.tvnames);
        Button btnStore = findViewById(R.id.btnStore);
        Button btnGetall = findViewById(R.id.btnget);
        EditText etname = findViewById(R.id.etname);

        btnStore.setOnClickListener(v -> {
            databaseHelper.addStudentDetail(etname.getText().toString());
            etname.setText("");
            Toast.makeText(MainActivity.this,
                    "Stored Successfully!", Toast.LENGTH_SHORT).show();
        });

        btnGetall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> arrayList = databaseHelper.getAllStudentsList();
                tvnames. setText("");
                for (int i=0; i < arrayList.size(); i++){
                    tvnames.setText(tvnames.getText().toString()+", "+arrayList.get(i));
                }
            }
        });

    }
}
