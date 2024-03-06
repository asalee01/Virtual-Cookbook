package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            RecipeList rl = new RecipeList();
            JSonWriter writer = new JSonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RecipeList rl = new RecipeList();
            JSonWriter writer = new JSonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JSonReader reader = new JSonReader("./data/testWriterEmptyWorkroom.json");
            rl = reader.read();
//            assertEquals(0, .numThingies());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            RecipeList rl = new RecipeList();
            rl.addRecipes(newRecipe1());
            rl.addRecipes(newRecipe2());
            JSonWriter writer = new JSonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JSonReader reader = new JSonReader("./data/testWriterGeneralWorkroom.json");
            rl = reader.read();
            List<Recipe> recipes = rl.getAllRecipes();
            assertEquals(2, recipes.size());
            Recipe r = recipes.get(0);
            Recipe r1 = recipes.get(1);
            checkRecipe(r, "Rec1", ingForR1(), instructForR1(), 100, 200, 300,
                    "Desc");
            checkRecipe(r1, "Rec2", ingForR2(), instructForR2(), 100, 20, 900,
                    "Yummy");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    public Recipe newRecipe1() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("ing");
        List<String> instruct = new ArrayList<>();
        instruct.add("INS");
        Recipe recipe1 = new Recipe("Rec1", ingredients, instruct, 100, 200, 300,
                "Desc");
        return recipe1;
    }

    public Recipe newRecipe2() {
        List<String> ingredients1 = new ArrayList<>();
        ingredients1.add("ing1");
        ingredients1.add("ing2");
        List<String> instruct1 = new ArrayList<>();
        instruct1.add("Ins1");
        Recipe recipe2 = new Recipe("Rec2", ingredients1, instruct1, 100, 20, 900,
                "Yummy");
        return recipe2;
    }

    public List<String> ingForR1() {
        List<String> ingredients3 = new ArrayList<>();
        ingredients3.add("ing");
        return ingredients3;
    }

    public List<String> instructForR1() {
        List<String> instruct3 = new ArrayList<>();
        instruct3.add("INS");
        return instruct3;
    }

    public List<String> ingForR2() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("ing1");
        ingredients.add("ing2");
        return ingredients;
    }

    public List<String> instructForR2() {
        List<String> instruct3 = new ArrayList<>();
        instruct3.add("Ins1");
        return instruct3;
    }
}