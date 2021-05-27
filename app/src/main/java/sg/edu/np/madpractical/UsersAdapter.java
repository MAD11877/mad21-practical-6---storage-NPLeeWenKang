package sg.edu.np.madpractical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder>{
    Context context;
    ArrayList<User> data;
    public UsersAdapter(Context c, ArrayList<User> d){
        context = c;
        data = d;
        Log.d("mydatabase", "binderadapt"+data.get(0).isFollowed());
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item;
        if (viewType == 0){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        } else {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_large, parent, false);
        }

        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User u = data.get(position);
        Log.d("mydatabase", "binderholder"+data.get(0).isFollowed());
        holder.txt.setText(u.getName());
        holder.txt2.setText(""+u.getDescription());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Profile")
                        .setCancelable(true)
                        .setMessage(u.getName());

                alert.setPositiveButton("View", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Intent changePage = new Intent(context, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("user", (Parcelable) u);
                        changePage.putExtras(bundle);
                        changePage.putExtra("position", position);
                        context.startActivity(changePage);
                    }
                });
                alert.setNegativeButton("Close", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                    }
                });
                alert.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        String name = data.get(position).getName();
        if(name.substring(name.length()-1).equals("7")){
            return 1;
        } else {
            return 0;
        }

    }
}
