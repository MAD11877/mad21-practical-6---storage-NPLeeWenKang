package sg.edu.np.madpractical;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class User implements Parcelable {
    public User(String str, String str2,int num,boolean bool){
        name = str;
        description = str2;
        id = num;
        followed = bool;
    }

    public User(boolean followed) {
        this.followed = followed;
    }

    private String name;
    private String description;
    private int id;
    private boolean followed;

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

    // Gets parcel and converts them from string to attributes in a model
    public User(Parcel in){
        String[] data = new String[4];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        // Converts from string
        this.name = data[0];
        this.description = data[1];
        this.id = Integer.parseInt(data[2]);
        this.followed = Boolean.parseBoolean(data[3]);
        Log.d("mydatabase", "data[3]"+data[3]);
        Log.d("mydatabase", "this.followed"+this.followed);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name, this.description, ""+this.id, ""+this.followed});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
