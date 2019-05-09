package sg.edu.rp.c346.studenttestpractical;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnRetreive;
    EditText etName, etGPA;
    TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnRetreive = findViewById(R.id.btnRetrieve);
        tvResults = findViewById(R.id.Result);
        etGPA = findViewById(R.id.etGPA);
        etName = findViewById(R.id.etName);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                String studentName = etName.getText().toString();
                Integer studentGPA = Integer.parseInt(etGPA.getText().toString());
                db.insertStudent(studentName, studentGPA);
                db.close();
            }
        });
        btnRetreive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                ArrayList<String> data = db.getStudentContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

            }
        });
    }

}
