package id.odojadmin.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.odojadmin.R;
import id.odojadmin.model.Admin;

public class CreateAdminActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_no)
    EditText editTextNo;
    @BindView(R.id.edit_text_name)
    EditText editTextName;
    @BindView(R.id.edit_text_email)
    EditText editTextEmail;
    @BindView(R.id.edit_text_origin)
    EditText editTextOrigin;
    @BindView(R.id.edit_text_phone)
    EditText editTextPhone;
    @BindView(R.id.edit_text_total_group)
    EditText editTextTotalGroup;
    @BindView(R.id.edit_text_status)
    EditText editTextStatus;
    @BindView(R.id.edit_text_password)
    EditText editTextPassword;
    private String adminId;


    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_admin);
        ButterKnife.bind(this);
        setTitle("Create Admin");
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("admin");
    }

    @OnClick({R.id.edit_text_password, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_text_password:
                break;
            case R.id.btn_register:
                doRegister();
                break;
        }
    }

    private void registerAdmin(String id, String email, String noAdmin, String name, String totalGroup, String origin, String phone, String status, String password) {
        Admin admin = new Admin(id, email, noAdmin, name, totalGroup, origin, phone, status, password);
        adminId = email.replace("@", "");
        adminId = adminId.replace(".", "");
        mFirebaseDatabase.child(adminId).setValue(admin).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CreateAdminActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void doRegister() {
        String id = editTextNo.getText().toString().trim() + "-" + editTextName.getText().toString().trim() + "-" + editTextOrigin.getText().toString().trim();
        registerAdmin(id, editTextEmail.getText().toString().trim(),
                editTextNo.getText().toString(),
                editTextName.getText().toString(),
                editTextTotalGroup.getText().toString(),
                editTextOrigin.getText().toString(),
                editTextPhone.getText().toString(),
                editTextStatus.getText().toString(),
                editTextPassword.getText().toString());


    }

}
