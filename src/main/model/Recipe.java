package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.*;


//Recipe is a class that contains methods such that you are able to create a recipe with the specified parameters in the
//constructor.
public class Recipe implements Writable {
    private String recipeName;
    private int cookingTime;
    private List<String> ingredients;
    private int prepTime;
    private List<String> cookingInstructions;
    private int calories;
    private String description;


    //EFFECTS: creates a recipe with a name, ingredients, instruction, preparation time, cooking time, calories
    //         and a short description.
    public Recipe(String name, List<String> ingredients, List<String> instructions, int prepTime,
                  int cookTime, int cals, String description) {
        this.recipeName = name;
        this.ingredients = ingredients;
        this.cookingInstructions = instructions;
        this.prepTime = prepTime;
        this.cookingTime = cookTime;
        this.calories = cals;
        this.description = description;
    }

    //getters
    public List<String> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return recipeName;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public List<String> getCookingInstructions() {
        return cookingInstructions;
    }

    public int getCalories() {
        return calories;
    }

    public String getDescription() {
        return description;
    }


    //REQUIRES: An instruction
    //MODIFIES: this
    //EFFECTS: adds specific instructions to the list of existing instructions.
    public void addCookingInstructions(String ins) {
        cookingInstructions.add(ins);
    }

    //REQUIRES: An ingredient
    //MODIFIES: this
    //EFFECTS: adds specific ingredient to the list of existing ingredients.
    public void addIngredients(String i) {
        ingredients.add(i);
    }


    //setters
    public void setName(String name) {
        recipeName = name;
    }

    public void setPrepTime(int time) {
        prepTime = time;
    }

    public void setCookingTime(int time) {
        cookingTime = time;
    }

    public void setCalories(int cals) {
        calories = cals;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", recipeName);
        json.put("ingredients", ingredients);
        json.put("ingredients", ingredients);
        json.put("instructions", cookingInstructions);
        json.put("preparation time", prepTime);
        json.put("cooking time", cookingTime);
        json.put("calories", calories);
        json.put("description", description);
        return json;
    }
}
