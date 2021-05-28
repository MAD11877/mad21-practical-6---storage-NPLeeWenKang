package sg.edu.np.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int position;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Debug","OnCreate");

        Intent receivingEnd = getIntent();
        Bundle bundle = receivingEnd.getExtras();;
        position = receivingEnd.getIntExtra("position",0);
        TextView profileTitle = (TextView) findViewById(R.id.profileTitle);
        TextView profileDescription = (TextView) findViewById(R.id.description);
        Button followBtn = (Button) findViewById(R.id.btnFollow);
        DBHandler userDB = new DBHandler(this, null,null,1);

        if (bundle != null){
            user = bundle.getParcelable("user");
            profileTitle.setText(user.getName());
            profileDescription.setText(user.getDescription());
            followBtn.setText(user.isFollowed()? "Unfollow" : "Follow");
        } else {
            user = new User("Null","Null",0,false);
        }
        Log.d("mydatabase", ""+user.getName());
        Log.d("mydatabase", ""+user.isFollowed());

        followBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    user.setFollowed(!user.isFollowed());
                    userDB.updateUser(user);
                    if (user.isFollowed()){
                        followBtn.setText("Unfollow");
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(MainActivity.this, "Followed", duration);
                        toast.show();
                    } else{
                        followBtn.setText("Follow");
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(MainActivity.this, "Unfollowed", duration);
                        toast.show();
                    }
            }
        });
    }
    @Override
    protected void onStart( ) {
        super.onStart();
    }
    @Override
    protected void onResume( ) {
        super.onResume();
        Log.i("Debug","onResume");
    }
    @Override
    protected void onPause( ) {
        super.onPause();
        Log.i("Debug","onPaused");
        if (ListActivity.userList.get(position).isFollowed() != user.isFollowed()){
            SharedPreferences preference = getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor editor =  preference.edit();
            editor.putInt("position",position);
            editor.apply();
        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Debug","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Debug","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Debug","onRestart");
    }
}