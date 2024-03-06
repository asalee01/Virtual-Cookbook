//package persistence;
//
//import model.*;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class JsonReaderTest extends JsonTest {
//
//    @Test
//    void testReaderNonExistentFile() {
//        JSonReader reader = new JSonReader("./data/noSuchFile.json");
//        try {
//            RecipeList rl = reader.read();
//            fail("IOException expected");
//        } catch (IOException e) {
//            // pass
//        }
//    }
//
//    @Test
//    void testReaderEmptyWorkRoom() {
//        JSonReader reader = new JSonReader("./data/testReaderEmptyWorkRoom.json");
//        try {
//            RecipeList rl = reader.read();
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
//
//    @Test
//    void testReaderGeneralWorkRoom() {
//        JSonReader reader = new JSonReader("./data/testReaderGeneralWorkRoom.json");
//        try {
//            RecipeList rl = reader.read();
//            List<Recipe> recipes = rl.getAllRecipes();
//            assertEquals(2, recipes.size());
//            checkRecipe("recipe1", "rec1",  );
//            checkRecipe("recipe2","rec2", );
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
//}