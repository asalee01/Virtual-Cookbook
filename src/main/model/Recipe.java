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


    public Recipe() {
        ingredients = new ArrayList<>();
        cookingInstructions = new ArrayList<>();
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(String i) {
        ingredients.add(i);
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

    public int getCalories() {
        return calories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
