package mobi.idappthat.shake.Model;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Cameron on 3/1/15.
 */
public class GeneralItem {

    private String name, imageUrl;
    private int rating;
    private int price;

    public GeneralItem() {

    }

    public GeneralItem(String name, int rating, int price, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.price = price;
    }

    public String getImageUrl() { return imageUrl; }

    public String getName() {
        return name;
    }

    public String getPriceText() {
        switch (price) {
            case 1:
                return "$,(Cheap)";
            case 2:
                return "$$,(Affordable)";
            case 3:
                return "$$$,(Moderate)";
            case 4:
                return "$$$$,(Expensive)";
            default:
                return ",";
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
