package com.inducesmile.favorites.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    public static class DummyItem {
        public String id;
        public String drink_name;
        public String website_url;

        public DummyItem(String id, String drink_name,
                         String website_url)
        {
            this.id = id;
            this.drink_name = drink_name;
            this.website_url = website_url;
        }

        @Override
        public String toString() {
            return drink_name;
        }
    }
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */


    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "The Activator Blueberry Strawberry",
                "https://www.smoothieking.com/menu/smoothies/fitness-blends/the-activator-blueberry-strawberry"));
        addItem(new DummyItem("2", "Mocha Cookie Crumble Frappuccino",
                "https://www.starbucks.com/menu/product/956/iced?parent=%2Fdrinks%2Ffrappuccino-blended-beverages%2Fcoffee-frappuccino"));
        addItem(new DummyItem("3", "Life on Mars",
                "https://foxtrotbar.com/menu/"));
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }



}
