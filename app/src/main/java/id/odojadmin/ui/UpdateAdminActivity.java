package id.odojadmin.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.odojadmin.R;
import id.odojadmin.model.Admin;

public class UpdateAdminActivity extends BaseActivity {

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
    @BindView(R.id.edit_text_email_login)
    EditText editTextEmailLogin;
    @BindView(R.id.edit_text_password_login)
    EditText editTextPasswordLogin;
    @BindView(R.id.linear_layout_login)
    LinearLayout linearLayoutLogin;

    private Admin adminLoggedIn;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin);
        ButterKnife.bind(this);
        setTitle("Update Admin");
    }


    private void doUpdate() {
        id = editTextEmail.getText().toString().replace("@", "");
        id = id.replace(".", "");
        mFirebaseDatabase = mFirebaseInstance.getReference("admin").child(id);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("noAdmin", editTextNo.getText().toString().trim());
        hashMap.put("email", editTextEmail.getText().toString().trim());
        hashMap.put("id", id);
        hashMap.put("name", editTextName.getText().toString().trim());
        hashMap.put("origin", editTextOrigin.getText().toString().trim());
        hashMap.put("phone", editTextPhone.getText().toString().trim());
        hashMap.put("status", editTextStatus.getText().toString().trim());
        hashMap.put("totalGroup", editTextTotalGroup.getText().toString().trim());
        mFirebaseDatabase.updateChildren(hashMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                Toast.makeText(UpdateAdminActivity.this, "Berhasil Update", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginAdmin(final String email, final String password) {
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Admin admin = noteDataSnapshot.getValue(Admin.class);
                    if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                        adminLoggedIn = admin;
                        break;
                    }
                }

                if (adminLoggedIn != null) {
                    Toast.makeText(UpdateAdminActivity.this, "Berhasil Login " + adminLoggedIn.getName(), Toast.LENGTH_SHORT).show();
                    linearLayoutLogin.setVisibility(View.GONE);
                    readData();
                } else
                    Toast.makeText(UpdateAdminActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UpdateAdminActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void readData() {
        editTextNo.setText(adminLoggedIn.getNoAdmin());
        editTextName.setText(adminLoggedIn.getName());
        editTextEmail.setText(adminLoggedIn.getEmail());
        editTextOrigin.setText(adminLoggedIn.getOrigin());
        editTextPhone.setText(adminLoggedIn.getPhone());
        editTextTotalGroup.setText(adminLoggedIn.getTotalGroup());
        editTextStatus.setText(adminLoggedIn.getStatus());
        editTextPassword.setText(adminLoggedIn.getPassword());
        System.out.println("HALO : " + mFirebaseDatabase.getKey() + " - " + mFirebaseDatabase.getRoot());
    }


    @OnClick({R.id.btn_login, R.id.btn_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginAdmin(editTextEmailLogin.getText().toString().trim(), editTextPasswordLogin.getText().toString().trim());
                break;
            case R.id.btn_update:
                doUpdate();
                break;
        }
    }
}
