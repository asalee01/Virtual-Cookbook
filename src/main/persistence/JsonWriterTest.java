//package persistence;
//
//import model.*;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class JsonWriterTest extends JsonTest {
//    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
//    //write data to a file and then use the reader to read it back in and check that we
//    //read in a copy of what was written out.
//
//    @Test
//    void testWriterInvalidFile() {
//        try {
//            RecipeList rl = new RecipeList();
//            JSonWriter writer = new JSonWriter("./data/my\0illegal:fileName.json");
//            writer.open();
//            fail("IOException was expected");
//        } catch (IOException e) {
//            // pass
//        }
//    }
//
//    @Test
//    void testWriterEmptyWorkroom() {
//        try {
//            RecipeList rl = new RecipeList("My work room");
//            JSonWriter writer = new JSonWriter("./data/testWriterEmptyWorkroom.json");
//            writer.open();
//            writer.write(rl);
//            writer.close();
//
//            JSonReader reader = new JSonReader("./data/testWriterEmptyWorkroom.json");
//            rl = reader.read();
//            assertEquals(0, wr.numThingies());
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }
//
//    @Test
//    void testWriterGeneralWorkroom() {
//        try {
//            RecipeList rl = new RecipeList();
//            rl.addRecipes(new Recipe("rec1", ));
//            rl.addRecipes(new Recipe("rec2", ));
//            JSonWriter writer = new JSonWriter("./data/testWriterGeneralWorkroom.json");
//            writer.open();
//            writer.write(rl);
//            writer.close();
//
//            JSonReader reader = new JSonReader("./data/testWriterGeneralWorkroom.json");
//            rl = reader.read();
//            List<Recipe> thingies = rl.getAllRecipes();
//            assertEquals(2, thingies.size());
//            checkRecipe("rec1",);
//            checkRecipe("rec2",);
//
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }
//}