package com.maryam.ecommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class DBHandler extends SQLiteOpenHelper {
    //constants
    private static final String DB_NAME = "ecommercedb";

    private static final int DB_VERSION = 2;

    private static final String TABLE_USERACCOUNTS = "user_accounts";
    private static final String USER_ID_COL = "id";
    private static final String FIRSTNAME_COL = "firstname";
    private static final String LASTNAME_COL = "lastname";
    private static final String USERNAME_COL = "username";
    private static final String PASSWORD_COL = "password";

    private static final String TABLE_PRODUCTS = "products";
    private static final String PRODUCT_ID_COL = "productID";
    private static final String PRODUCT_NAME_COL = "product_name";
    private static final String PRODUCT_FOODGROUP_COL = "food_group";
    private static final String PRODUCT_DIETPREF_COL = "dietary_preferences";
    private static final String PRODUCT_CUISINE_COL = "cuisine";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUserAccountsTable = "CREATE TABLE " + TABLE_USERACCOUNTS + " ("
                + USER_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRSTNAME_COL + " TEXT,"
                + LASTNAME_COL + " TEXT,"
                + USERNAME_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";

        db.execSQL(createUserAccountsTable);

        String createProductsTable = "CREATE TABLE " + TABLE_PRODUCTS + " ("
                + PRODUCT_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PRODUCT_NAME_COL + " TEXT,"
                + PRODUCT_FOODGROUP_COL + " TEXT,"
                + PRODUCT_DIETPREF_COL + " TEXT,"
                + PRODUCT_CUISINE_COL + " TEXT)";

        db.execSQL(createProductsTable);

        //UNCOMMENT LATER
        //insertProducts(db)
    }


    public boolean addNewShopper(String firstname, String lastname, String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        if (existingUser(username,password)){
            return false;
        }

        ContentValues values = new ContentValues();

        values.put(FIRSTNAME_COL, firstname); //key-value pairs
        values.put(LASTNAME_COL, lastname);
        values.put(USERNAME_COL, username);
        values.put(PASSWORD_COL, password);

        //also create a new shopper object
        Shopper shopper = new Shopper(firstname, lastname, username, password);

        db.insert(TABLE_USERACCOUNTS, null, values);

        db.close();
        return true;
    }

    public boolean existingUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        //check if the username already exists
        String query = "SELECT * FROM " + TABLE_USERACCOUNTS + " WHERE " + USERNAME_COL +  " = ? AND " + PASSWORD_COL + " = ?"; //? is a placeholder for a parameter
        Cursor cursor = db.rawQuery(query, new String[]{username,password});

        if (cursor.getCount() > 0){
            cursor.close();
            db.close();
            return true;
        }

        return false;
    }

    private void insertProducts(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            Gson gson = new Gson();

            //products array
            Product[] products = {
                    new Product("Apple","Fruits", new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Pear","Fruits", new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Banana","Fruits",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Orange","Fruits",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Grapes","Fruits",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Broccoli","Vegetables",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Carrot","Vegetables",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Cucumber","Vegetables",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Potato","Vegetables",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Lettuce","Vegetables",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Tomato","Vegetables",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),"Italian"),
                    new Product("Milk","Dairy",new ArrayList<>(Arrays.asList("Vegetarian", "Halal", "Gluten-Free")),null),
                    new Product("Vegan milk","Dairy",new ArrayList<>(Arrays.asList("Vegan","Vegetarian", "Halal", "Gluten-Free")),null),
                    new Product("Butter","Dairy",new ArrayList<>(Arrays.asList("Vegetarian", "Halal", "Gluten-Free")),null),
                    new Product("Eggs","Dairy",new ArrayList<>(Arrays.asList("Vegetarian", "Halal", "Gluten-Free")),null),
                    new Product("Yoghurt","Dairy",new ArrayList<>(Arrays.asList("Vegetarian", "Halal", "Gluten-Free")),null),
                    new Product("Vegan yoghurt","Dairy",new ArrayList<>(Arrays.asList("Vegan","Vegetarian", "Halal", "Gluten-Free")),null),
                    new Product("Mozzarella cheese","Dairy",new ArrayList<>(Arrays.asList("Vegetarian", "Halal", "Gluten-Free")),"Italian"),
                    new Product("Vegan cheese","Dairy",new ArrayList<>(Arrays.asList("Vegan","Vegetarian", "Halal", "Gluten-Free")),null),
                    new Product("Pizza","Frozen food",new ArrayList<>(Arrays.asList("Vegetarian", "Halal")),"Italian"),
                    new Product("Ice cream","Frozen food",new ArrayList<>(Arrays.asList("Vegetarian", "Halal")),null),
                    new Product("Vegan ice cream","Frozen food",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal")),null),
                    new Product("Fish cod","Frozen food",new ArrayList<>(Arrays.asList("Halal")),null),
                    new Product("Baby chicken (halal)","Frozen food",new ArrayList<>(Arrays.asList("Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Chicken nuggets","Frozen food",new ArrayList<>(Arrays.asList("Gluten-Free", "Dairy-free")),null),
                    new Product("Lamb cubes (halal)","Frozen food",new ArrayList<>(Arrays.asList("Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Pork salami","Frozen food",new ArrayList<>(Arrays.asList("Gluten-Free", "Dairy-Free")),null),
                    new Product("Sausages","Frozen food",new ArrayList<>(Arrays.asList("Gluten-Free", "Dairy-Free")),null),
                    new Product("Ketchup","Condiments",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Mayo","Condiments",new ArrayList<>(Arrays.asList("Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Vegan mayo","Condiments",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Honey","Condiments",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Baguette","Breads",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Dairy-Free")),"French"),
                    new Product("White bread","Breads",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Dairy-Free")),null),
                    new Product("Gluten-free white bread","Breads",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free","Dairy-Free")),null),
                    new Product("Brioche","Breads",new ArrayList<>(Arrays.asList("Vegetarian", "Halal", "Dairy-Free")),null),
                    new Product("Ketchup","Breads",new ArrayList<>(Arrays.asList("Vegan","Vegetarian", "Halal", "Gluten-Free","Dairy-Free")),null),
                    new Product("Milk chocolate bar","Snacks",new ArrayList<>(Arrays.asList("Vegetarian", "Halal")),null),
                    new Product("Vegan cookies","Snacks",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Dairy-Free")),null),
                    new Product("Gluten-free cookies","Snacks",new ArrayList<>(Arrays.asList("Vegetarian", "Halal", "Gluten-Free")),null),
                    new Product("Sea-salt crisps","Snacks",new ArrayList<>(Arrays.asList("Vegetarian", "Halal", "Dairy-Free")),null),
                    new Product("Dairy-free cupcakes","Snacks",new ArrayList<>(Arrays.asList("Vegetarian", "Halal", "Dairy-Free")),null),
                    new Product("Wine","Beverages",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Orange juice","Beverages",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free","Dairy-Free")),null),
                    new Product("Coffee packets","Beverages",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free","Dairy-Free")),null),
                    new Product("Tea packets","Beverages",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free","Dairy-Free")),null),
                    new Product("Spaghetti","Pasta",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Dairy-Free")),"Italian"),
                    new Product("Penne pasta","Pasta",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Dairy-Free")),"Italian"),
                    new Product("Ravioli","Pasta",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Dairy-Free")),"Italian"),
                    new Product("Hummus","Lebanese selection (exclusive)",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),"Lebanese"),
                    new Product("Pita bread","Lebanese selection (exclusive)",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Dairy-Free")),"Lebanese"),
                    new Product("Tabbouleh salad","Lebanese selection (exclusive)",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),"Lebanese"),
                    new Product("Kibbeh","Lebanese selection (exclusive)",new ArrayList<>(Arrays.asList("Halal", "Gluten-Free", "Dairy-Free")),"Lebanese"),
                    new Product("Pain Au Chocolat","French selection (exclusive)",new ArrayList<>(Arrays.asList("Vegetarian", "Halal")),"French"),
                    new Product("Croissant","French selection (exclusive)",new ArrayList<>(Arrays.asList("Vegetarian", "Halal")),"French"),
                    new Product("Chocolate eclairs","French selection (exclusive)",new ArrayList<>(Arrays.asList("Vegetarian", "Halal")),"French"),
                    new Product("Vegetable sushi","Japanese selection (exclusive)",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),"Japanese"),
                    new Product("Ramen noodles","Japanese selection (exclusive)",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Dairy-Free")),"Japanese"),
                    new Product("Sticky rice","Japanese selection (exclusive)",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),"Japanese"),
                    new Product("Raw salmon","Japanese selection (exclusive)",new ArrayList<>(Arrays.asList("Halal", "Gluten-Free", "Dairy-Free")),"Japanese"),
                    new Product("Sugar","Cooking ingredients",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Cocoa powder","Cooking ingredients",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Baking powder","Cooking ingredients",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Gluten-Free", "Dairy-Free")),null),
                    new Product("Flour","Cooking ingredients",new ArrayList<>(Arrays.asList("Vegan", "Vegetarian", "Halal", "Dairy-Free")),null)
            };

            for (Product product : products) {
                values.put(PRODUCT_NAME_COL, product.getProductName());
                values.put(PRODUCT_FOODGROUP_COL, product.getFoodGroup());
                String dietPrefJson = gson.toJson(product.getDietPref());
                values.put(PRODUCT_DIETPREF_COL, dietPrefJson);
                values.put(PRODUCT_CUISINE_COL, product.getCuisine());
                db.insert(TABLE_PRODUCTS, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //checks if the table exists already and rewrites it
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        onCreate(db);
    }
}