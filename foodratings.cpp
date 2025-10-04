#include <bits/stdc++.h>
using namespace std;

class FoodRatings {
    unordered_map<string, string> foodToCuisine;
    unordered_map<string, int> foodToRating;
    // For each cuisine, keep a set ordered by (-rating, food) so begin() is highest-rated
    unordered_map<string, set<pair<int,string>>> cuisineToSet;

public:
    FoodRatings(vector<string>& foods, vector<string>& cuisines, vector<int>& ratings) {
        for (size_t i = 0; i < foods.size(); ++i) {
            string f = foods[i];
            string c = cuisines[i];
            int r = ratings[i];

            foodToCuisine[f] = c;
   e         foodToRating[f] = r;
            cuisineToSet[c].insert({-r, f}); // negative rating to order highest first
        }
    }

    void changeRating(const string& food, int newRating) {
        auto itCuisine = foodToCuisine.find(food);
        if (itCuisine == foodToCuisine.end()) return; // unknown food

        string cuisine = itCuisine->second;
        int oldRating = foodToRating[food];

        // erase old pair and insert new one
        auto &s = cuisineToSet[cuisine];
        s.erase({-oldRating, food});
        s.insert({-newRating, food});
        foodToRating[food] = newRating;
    }

    string highestRated(const string& cuisine) {
        auto it = cuisineToSet.find(cuisine);
        if (it == cuisineToSet.end() || it->second.empty()) return "";
        return it->second.begin()->second; // first element has highest rating (because rating is negative)
    }
};

// Quick demonstration
int main() {
    vector<string> foods = {"kimchi","miso","sushi","ramen","burger","kebab"};
    vector<string> cuisines = {"korean","japanese","japanese","japanese","american","middleeast"};
    vector<int> ratings = {9,12,12,10,8,11};

    FoodRatings fr(foods, cuisines, ratings);

    cout << fr.highestRated("japanese") << "\n"; // miso (lexicographically smaller than sushi for tie)
    fr.changeRating("sushi", 13);
    cout << fr.highestRated("japanese") << "\n"; // sushi
    fr.changeRating("miso", 14);
    cout << fr.highestRated("japanese") << "\n"; // miso

    return 0;
}
