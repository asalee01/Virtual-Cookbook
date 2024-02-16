package model;

import java.util.*;

public class Recipe {
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
        for (String i: ingredients) {
            i.toLowerCase();
        }
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

    public void setIngredients(String i) {
        ingredients.add(i);
    }

    public int getCalories() {
        return calories;
    }

    public String getDescription() {
        return description;
    }


    //setters
    public void setCookingInstructions(String ins) {
        cookingInstructions.add(ins);
    }

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
}
