import java.util.*;

public class FoodRatings {
    // Helper entry so we can store and remove exact objects from TreeSet
    private static class Entry implements Comparable<Entry> {
        String food;
        int rating;

        Entry(String food, int rating) {
            this.food = food;
            this.rating = rating;
        }

        // Sort by rating descending, then food name ascending
        @Override
        public int compareTo(Entry other) {
            if (this.rating != other.rating) {
                return Integer.compare(other.rating, this.rating); // higher rating first
            }
            return this.food.compareTo(other.food); // lexicographically smaller first
        }

        // equals/hashCode based on food name so we can map easily if needed
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry)) return false;
            Entry e = (Entry) o;
            return Objects.equals(this.food, e.food);
        }

        @Override
        public int hashCode() {
            return Objects.hash(food);
        }
    }

    private final Map<String, String> foodToCuisine;
    private final Map<String, Integer> foodToRating;
    private final Map<String, TreeSet<Entry>> cuisineToSet;
    // Keep track of current Entry object per food so we can remove it in O(log n)
    private final Map<String, Entry> foodToEntry;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        cuisineToSet = new HashMap<>();
        foodToEntry = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String f = foods[i];
            String c = cuisines[i];
            int r = ratings[i];

            foodToCuisine.put(f, c);
            foodToRating.put(f, r);

            cuisineToSet.putIfAbsent(c, new TreeSet<>());
            Entry e = new Entry(f, r);
            cuisineToSet.get(c).add(e);
            foodToEntry.put(f, e);
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        int oldRating = foodToRating.get(food);

        // remove old entry
        Entry oldEntry = foodToEntry.get(food);
        if (oldEntry != null) {
            // remove from cuisine set
            TreeSet<Entry> set = cuisineToSet.get(cuisine);
            set.remove(oldEntry);
        }

        // create and insert new entry
        Entry newEntry = new Entry(food, newRating);
        cuisineToSet.get(cuisine).add(newEntry);

        // update maps
        foodToRating.put(food, newRating);
        foodToEntry.put(food, newEntry);
    }

    public String highestRated(String cuisine) {
        TreeSet<Entry> set = cuisineToSet.get(cuisine);
        if (set == null || set.isEmpty()) return "";
        return set.first().food; // first has highest rating due to comparator
    }

    // Simple demo / quick test
    public static void main(String[] args) {
        String[] foods = {"kimchi","miso","sushi","ramen","burger","kebab"};
        String[] cuisines = {"korean","japanese","japanese","japanese","american","middleeast"};
        int[] ratings = {9,12,12,10,8,11};

        FoodRatings fr = new FoodRatings(foods, cuisines, ratings);

        System.out.println(fr.highestRated("japanese")); // miso or sushi -> lexicographically "miso" < "sushi" so miso
        fr.changeRating("sushi", 13);
        System.out.println(fr.highestRated("japanese")); // sushi
        fr.changeRating("miso", 14);
        System.out.println(fr.highestRated("japanese")); // miso
    }
}

