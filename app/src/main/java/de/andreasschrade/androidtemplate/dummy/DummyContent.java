package de.andreasschrade.androidtemplate.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.andreasschrade.androidtemplate.R;

/**
 * Just dummy content. Nothing special.
 *
 * Created by Andreas Schrade on 14.12.2015.
 */
public class DummyContent {

    /**
     * An array of sample items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample items. Key: sample ID; Value: Item.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<>(5);

    static {
        addItem(new DummyItem("1", R.drawable.p1, "Challenge #1", "Feed 'Em", "Focusing is about saying No.","$25","Completed"));
        addItem(new DummyItem("2", R.drawable.p2, "Challenge #2", "Eat 'Em","A quitter never wins and a winner never quits.","$100","Pending"));
        addItem(new DummyItem("3", R.drawable.p3, "Challenge #3", "Read 'Em", "Action is the foundational key to all success.","One free hug","Completed"));
        addItem(new DummyItem("4", R.drawable.p4, "Challenge #4", "Napoleon Hill","Our only limitations are those we set up in our own minds.",":*","Pending"));
        addItem(new DummyItem("5", R.drawable.p5, "Challenge #5", "Steve Jobs","Deciding what not do do is as important as deciding what to do.","25% off on meal","Pending"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class DummyItem {
        public final String id;
        public final int photoId;
        public final String title;
        public final String author;
        public final String content;
        public final String completed_status;
        public final String reward_value;

        public DummyItem(String id, int photoId, String title, String author, String content, String reward_value, String completed_status) {
            this.id = id;
            this.photoId = photoId;
            this.title = title;
            this.author = author;
            this.content = content;
            this.reward_value = reward_value;
            this.completed_status = completed_status;
        }
    }
}
