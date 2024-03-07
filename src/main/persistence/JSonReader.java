package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.*;

import model.Recipe;
import model.RecipeList;
import org.json.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// It is a reader that reads the workroom data stored in file.
public class JSonReader {
    private String source;

    // EFFECTS: creates a reader that reads the source of the file.
    public JSonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the workroom file and outputs the file's information.
    // throws IOException.
    public RecipeList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipeList(jsonObject);
    }

    // EFFECTS:takes in the source as a string and reads that file.
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom and returns the workroom.
    private RecipeList parseRecipeList(JSONObject jsonObject) {
        RecipeList rl = new RecipeList();
        addRecipes(rl, jsonObject);
        return rl;
    }

    // MODIFIES: (RecipeList) rl
    // EFFECTS: parses recipe list from Json and adds them to workroom
    private void addRecipes(RecipeList rl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("recipes");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addRecipe(rl, nextRecipe);
        }
    }

    // MODIFIES: (Recipe List) rl
    // EFFECTS: parses recipe from Json and adds them to workroom.
    private void addRecipe(RecipeList rl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");
        List<String> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientsArray.length(); i++) {
            ingredients.add(ingredientsArray.getString(i));
        }
        JSONArray instructionsArray = jsonObject.getJSONArray("instructions");
        List<String> instructions = new ArrayList<>();
        for (int i = 0; i < instructionsArray.length(); i++) {
            instructions.add(instructionsArray.getString(i));
        }
        int prepTime = jsonObject.getInt("preparation time");
        int cookTime = jsonObject.getInt("cooking time");
        int calories = jsonObject.getInt("calories");
        String description = jsonObject.getString("description");
        Recipe recipe = new Recipe(name, ingredients, instructions, prepTime, cookTime, calories, description);
        rl.addRecipes(recipe);
    }
}