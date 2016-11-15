package pble.enpit2016.zerocontact.parts;

/**
 * Iconクラス
 * Created by kyokn on 2016/11/04.
 */

public class Icon {

    private final String name;
    private final String hobby;
    private final int image;
    private int[] location;

    public Icon(String name, String hobby, int resource, int[] location) {
        this.name = name;
        this.hobby = hobby;
        this.image = resource;
        this.location = location;
    }

    public Icon(String name, String hobby, int resource) {
        this.name = name;
        this.hobby = hobby;
        this.image = resource;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getHobby() {
        return hobby;
    }

    public int getImage() {
        return image;
    }

    public int[] getLocation() {
        return location;
    }
}
