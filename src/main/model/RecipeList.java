package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;


//Recipe List is basically a list of recipes and has methods that can fulfill all the user stories mentioned.
public class RecipeList implements Writable {

    private final List<Recipe> recipes;

    public RecipeList() {
        recipes = new ArrayList<>();
    }


    //REQUIRES: A recipe
    //MODIFIES: this
    //EFFECTS: adds the recipe to the recipe list. If there are duplicate recipes, it will not be added.
    public void addRecipes(Recipe r) {
        if (!recipes.contains(r)) {
            recipes.add(r);
        }
        EventLog.getInstance().logEvent(new Event("Recipe has been added!"));
    }


    //REQUIRES: A recipe name.
    //MODIFIES: this
    //EFFECTS: searches through the list of recipes for the recipe name given, then removes that recipe.
    public void removeRecipeByName(String r) {
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getName().equals(r)) {
                recipes.remove(i);
                break;
            }
        }
        EventLog.getInstance().logEvent(new Event("A recipe has been removed!"));
    }


    //REQUIRES: A recipe name and the modified recipe.
    //MODIFIES: this
    //EFFECTS: Finds the given name in the list and replaces it with the new Recipe. If not found, add it to the list.
    public void modifyRecipe(String name, Recipe newRecipe) {
        int index = -1;
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getName().equals(name)) {
                index = i;
            }
        }
        if (index == -1) {
            addRecipes(newRecipe);
        } else {
            recipes.set(index, newRecipe);
        }
        EventLog.getInstance().logEvent(new Event("A recipe has been modified."));
    }

    //REQUIRES: time >= 0.
    //MODIFIES:
    //EFFECTS: returns a list of recipes that have cooking time less than or equal to the time entered.
    public List<Recipe> searchByCookingTime(int time) {
        List<Recipe> filteredTimeRecipes = new ArrayList<>();
        for (Recipe r : recipes) {
            if (time >= r.getCookingTime()) {
                filteredTimeRecipes.add(r);
            }
        }
        EventLog.getInstance().logEvent(new Event("Searched for recipes by cooking time"));
        return filteredTimeRecipes;
    }


    //REQUIRES: List of ingredients
    //MODIFIES:
    //EFFECTS: returns a list of recipes that contain the list of ingredients entered in their ingredients list.
    public List<Recipe> searchByIngredients(List<String> i) {
        List<Recipe> filteredIngredientsRecipes = new ArrayList<>();
        for (String ing : i) {
            for (Recipe r : recipes) {
                if (r.getIngredients().contains(ing) && !filteredIngredientsRecipes.contains(r)) {
                    filteredIngredientsRecipes.add(r);
                }
            }
        }
        EventLog.getInstance().logEvent(new Event("Searched for recipes by ingredients."));
        return filteredIngredientsRecipes;
    }

    //getters
    public int getSize() {
        return recipes.size();
    }

    public Recipe getRecipe(int r) {
        return recipes.get(r);
    }

    public List<Recipe> getAllRecipes() {
        EventLog.getInstance().logEvent(new Event("Extracted all recipes in cookbook."));
        return recipes;
    }

    //MAKE IT SUCH THAT YOU CAN ADD THE RECIPES INDIVIDUALLY
    // CALL THE toJson method in recipe class.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("recipes", recipesToJson());
        return json;
    }

    public JSONArray recipesToJson() {
        JSONArray allRecipe = new JSONArray();
        for (Recipe r: recipes) {
            allRecipe.put(r.toJson());
        }
        return allRecipe;
    }
}
