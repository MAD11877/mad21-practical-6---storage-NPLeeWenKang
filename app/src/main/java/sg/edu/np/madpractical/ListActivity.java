package sg.edu.np.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    public static ArrayList<User> uList = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//        ImageView roundIcon = (ImageView) findViewById(R.id.roundIcon);

//        roundIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog alert = alertBuilder().create();
//                alert.show();
//            }
//        });
        UserDBHandler userDB = new UserDBHandler(this, null,null,1);
        uList = userDB.getUsers();
        Log.d("mydatabase", ""+uList.get(0).isFollowed());



        RecyclerView recyclerView = findViewById(R.id.rv);
        UsersAdapter mAdapter =
                new UsersAdapter(this,uList);
        Log.d("mydatabase", ""+uList.get(0).isFollowed());
        LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }


    public AlertDialog.Builder alertBuilder(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Profile");
        builder.setMessage("MADness");
        builder.setCancelable(true);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Intent changePage = new Intent(ListActivity.this, MainActivity.class);

                int randomInt = new Random().nextInt(1000000);
                changePage.putExtra("number", String.valueOf(randomInt));

                startActivity(changePage);
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
            }
        });
        return builder;
    }
    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        if (preferences.contains("position")){
            Log.d("mydatabase", "pref");
            int position = preferences.getInt("position",0);
            uList.get(position).setFollowed(!uList.get(position).isFollowed());

            SharedPreferences.Editor editor = preferences.edit();

            editor.remove("position");
            editor.apply();
        }
    }
}
