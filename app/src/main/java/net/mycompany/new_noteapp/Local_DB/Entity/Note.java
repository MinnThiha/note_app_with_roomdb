package net.mycompany.new_noteapp.Local_DB.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "tbl_note")
public class Note implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    int id;

    private String title;
    private String desc;
    private String date;
    @ColumnInfo(name = "cat_id")
    private int cat_id;

    public Note(String title, String desc, String date, int cat_id) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.cat_id = cat_id;
    }

    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        desc = in.readString();
        date = in.readString();
        cat_id = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(date);
        dest.writeInt(cat_id);
    }
}
