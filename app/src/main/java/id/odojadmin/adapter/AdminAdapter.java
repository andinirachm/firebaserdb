package id.odojadmin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.odojadmin.R;
import id.odojadmin.model.Admin;

/**
 * Created by Andini Rachmah on 21/12/18.
 */
public class AdminAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Admin> adminList = new ArrayList<>();

    public AdminAdapter(Context context, List<Admin> adminList) {
        this.context = context;
        this.adminList = adminList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if(holder instanceof ItemHolder){
           ItemHolder h = (ItemHolder) holder;
           h.setData(adminList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return adminList.size();
    }


    static class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_name)
        TextView textViewName;
        @BindView(R.id.text_view_email)
        TextView textViewEmail;
        @BindView(R.id.text_view_origin)
        TextView textViewOrigin;

        ItemHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(Admin admin){
            textViewEmail.setText(admin.getEmail());
            textViewName.setText(admin.getName());
            textViewOrigin.setText(admin.getOrigin());
        }
    }
}
