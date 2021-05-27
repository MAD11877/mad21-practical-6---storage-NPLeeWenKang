package sg.edu.np.madpractical;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView txt;
    public TextView txt2;
    public View view;
    public UserViewHolder(View itemView) {
        super(itemView);
        txt = itemView.findViewById(R.id.text1);
        txt2 = itemView.findViewById(R.id.text2);
        //view = itemView;
        // Set the listener for the whole box
        view = itemView.findViewById(R.id.img_profile);
    }
}

