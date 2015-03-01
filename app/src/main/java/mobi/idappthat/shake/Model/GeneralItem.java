package mobi.idappthat.shake.Model;

/**
 * Created by Cameron on 3/1/15.
 */
public class GeneralItem {

    private String name;
    private int rating;
    private int price;
    private double lat, lng;

    public GeneralItem() {

    }

    public GeneralItem(String name, int rating, int price, double lat, double lng) {
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.lat = lat;
        this.lng = lng;
    }

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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
