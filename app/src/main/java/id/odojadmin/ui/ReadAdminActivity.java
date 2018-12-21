package id.odojadmin.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.odojadmin.R;
import id.odojadmin.adapter.AdminAdapter;
import id.odojadmin.model.Admin;

public class ReadAdminActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private AdminAdapter adapter;

    private List<Admin> adminList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_admin);
        ButterKnife.bind(this);
        setRecyclerView();
        readAdmin();
    }

    private void setRecyclerView() {
        adapter = new AdminAdapter(this, adminList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void readAdmin() {
        adminList.clear();
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Admin admin = noteDataSnapshot.getValue(Admin.class);
                    adminList.add(admin);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ReadAdminActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
