package persistance;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.*;

import model.Recipe;
import model.RecipeList;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JSonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JSonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RecipeList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipeList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private RecipeList parseRecipeList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        RecipeList rl = new RecipeList();
        addRecipes(rl, jsonObject);
        return rl;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addRecipes(RecipeList rl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("thingies");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addRecipe(rl, nextRecipe);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addRecipe(RecipeList rl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        List<String> ingredients = (List<String>) jsonObject.get("ingredients");
        List<String> instructions = (List<String>) jsonObject.get("instructions");
        int prepTime = jsonObject.getInt("prep. time");
        int cookTime = jsonObject.getInt("cook time");
        int calories = jsonObject.getInt("calories");
        String description = jsonObject.getString("description");
        Recipe recipe = new Recipe(name, ingredients, instructions, prepTime, cookTime, calories, description);
        rl.addRecipes(recipe);
    }
}