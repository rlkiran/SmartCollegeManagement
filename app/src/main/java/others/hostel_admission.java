package others;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.my_project003.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class hostel_admission extends AppCompatActivity {

    EditText name,phn,mail,addr,rollno;
    RadioGroup year,branch;
    RadioButton rb1,rb2;
    String sname,sphn,smail,saddr,srollno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_admission);
        name= findViewById(R.id.reg_usrname);
        phn= findViewById(R.id.reg_phone);
        mail= findViewById(R.id.reg_email);
        addr= findViewById(R.id.reg_addr);
        rollno= findViewById(R.id.reg_roll);
        year = findViewById(R.id.reg_group);
        branch = findViewById(R.id.reg_groupBr);
    }

    public void submitApplication(View view) {
        sname = name.getText().toString();
        sphn = phn.getText().toString();
        smail = mail.getText().toString();
        saddr = addr.getText().toString();
        srollno = rollno.getText().toString();

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final int selectedId=year.getCheckedRadioButtonId();
        rb1 =findViewById(selectedId);
        int selectedId2=branch.getCheckedRadioButtonId();
        rb2 =findViewById(selectedId2);


        if(!sname.isEmpty() && !sphn.isEmpty() && !smail.isEmpty()
                && !saddr.isEmpty()  && !srollno.isEmpty()
                && !rb1.getText().toString().isEmpty() && !rb2.getText().toString().isEmpty()) {
            Map<String, Object> user = new HashMap<>();
            user.put("name", sname);
            user.put("phone",sphn);
            user.put("email",smail);
            user.put("address",saddr);
            user.put("rollno",srollno);
            user.put("year",rb1.getText().toString());
            db.collection("HostelApplications").document(srollno)
                    .set(user)
                    .addOnSuccessListener(aVoid -> Toast.makeText(getApplicationContext(), "Form Submitted To admin", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(hostel_admission.this, "Failed to submit the form", Toast.LENGTH_SHORT).show());
        }

    }

}
