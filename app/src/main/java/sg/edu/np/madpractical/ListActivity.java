package sg.edu.np.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    public static ArrayList<User> userList = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DBHandler userDB = new DBHandler(this, null,null,1);

        if (!userDB.tableExists()){
            for (int i = 0; i< 20; i++){
                int userInt = new Random().nextInt(1000000);
                int descriptionInt = new Random().nextInt(1000000);
                boolean followed = new Random().nextBoolean();
                User u = new User();
                u.setName("Name"+userInt);
                u.setDescription(""+descriptionInt);
                u.setFollowed(followed);
                userDB.addUser(u);
            }
        }

        userList = userDB.getUsers();

        RecyclerView recyclerView = findViewById(R.id.rv);
        UsersAdapter mAdapter =
                new UsersAdapter(this,userList);
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
            userList.get(position).setFollowed(!userList.get(position).isFollowed());

            SharedPreferences.Editor editor = preferences.edit();

            editor.remove("position");
            editor.apply();
        }
    }
}
