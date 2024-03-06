package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JSonReader reader = new JSonReader("./data/noSuchFile.json");
        try {
            RecipeList rl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JSonReader reader = new JSonReader("./data/testEmptyReader.json");
        try {
            RecipeList rl = reader.read();
            assertTrue(rl.getAllRecipes().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JSonReader reader = new JSonReader("./data/testReader.json");
        try {
            RecipeList rl = reader.read();
            List<Recipe> recipes = rl.getAllRecipes();
            assertEquals(2, recipes.size());
            Recipe r = recipes.get(0);
            Recipe r1 = recipes.get(1);
            List<String> ingredients = new ArrayList<>();
            ingredients.add("ing");
            List<String> instruct = new ArrayList<>();
            instruct.add("INS");
            checkRecipe(r, "Rec1", ingredients, instruct, 100, 200, 300,
                    "Desc");
            List<String> ingredients1 = new ArrayList<>();
            ingredients1.add("ing1");
            ingredients1.add("ing2");
            List<String> instruct1 = new ArrayList<>();
            instruct1.add("Ins1");
            checkRecipe(r1, "Rec2", ingredients1, instruct1, 100, 20, 900,
                    "Yummy");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}