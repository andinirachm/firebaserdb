package id.odojadmin.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import id.odojadmin.R;
import id.odojadmin.ui.CreateAdminActivity;
import id.odojadmin.ui.UpdateAdminActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCreateClick(View view) {
        startActivity(new Intent(this, CreateAdminActivity.class));
    }


    public void onUpdateClick(View view) {
        startActivity(new Intent(this, UpdateAdminActivity.class));
    }

    public void onDeleteClick(View view) {

    }

    public void onReadClick(View view) {
        startActivity(new Intent(this, ReadAdminActivity.class));
    }
}
