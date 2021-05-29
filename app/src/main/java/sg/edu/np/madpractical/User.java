package sg.edu.np.madpractical;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class User{
    public User(String str, String str2,int num,boolean bool){
        name = str;
        description = str2;
        id = num;
        followed = bool;
    }

    public User(boolean followed) {
        this.followed = followed;
    }

    public String name;
    public String description;
    public int id;
    public boolean followed;

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", followed=" + followed +
                '}';
    }
}
