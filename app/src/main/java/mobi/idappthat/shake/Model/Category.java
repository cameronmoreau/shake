package mobi.idappthat.shake.Model;

/**
 * Created by Cameron on 3/1/15.
 */
public class Category {

    public static final int HOBBIES = 1;
    public static final int TRAVEL = 2;
    public static final int DINING = 3;
    public static final int FUN = 4;
    public static final int OUTDOORS = 5;
    public static final int SPORTS = 6;

    public static final int HOBBIES_TECH = 1;
    public static final int HOBBIES_ARTS = 2;

    public static final int DINING_BREAKFAST = 1;
    public static final int DINING_LUNCH = 2;
    public static final int DINING_DINNER = 3;
    public static final int DINING_COFFEE = 4;

    public static final int FUN_MOVIES = 1;
    public static final int FUN_MUSIC = 2;
    public static final int FUN_NIGHTLIFE = 3;

    public static final int OUTDOORS_HIKING = 1;
    public static final int OUTDOORS_SWIMMING = 2;
    public static final int OUTDOORS_PARK = 3;

    public static final int SPORTS_FOOTBALL = 1;
    public static final int SPORTS_BASEBALL = 2;
    public static final int SPORTS_BASKETBALL = 3;
    public static final int SPORTS_SOCCER = 4;

    public static final String CATEGORY_TYPE = "category";

    public static String getCategoryName(int categoryType) {
        switch(categoryType) {
            case 1:
                return "Hobbies";
            case 2:
                return "Travel";
            case 3:
                return "Dining";
            case 4:
                return "Fun";
            case 5:
                return "Outdoors";
            case 6:
                return "Sports";
            default:
                return "";
        }
    }

    public static String getCategoryType(int categoryType, int subCategoryType) {
        if(categoryType == 1) {
            switch(subCategoryType) {
                case 1:
                    return "";
                case 2:
                    return "";
                default:
                    return "";
            }
        } else if(categoryType == 2) {

        } else if(categoryType == 3) {

        } else if(categoryType == 4) {

        } else if(categoryType == 5) {

        } else if(categoryType == 6) {

        }
        return "fuck";
    }
}
