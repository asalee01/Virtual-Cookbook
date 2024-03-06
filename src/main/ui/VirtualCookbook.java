package ui;

import model.Recipe;
import model.RecipeList;
import persistence.JSonReader;
import persistence.JSonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
//Virtual Cookbook UI, has all the main functionalities implemented (you can add, remove, modify recipes and search for
//recipes using specific ingredients or a specified cooking time.)

//EFFECTS: creates an instance of a virtual cookbook.
public class VirtualCookbook {

    private static final String JSON_STORE = "./data/workroom.json";
    private Scanner input;
    private RecipeList listRec;
    private JSonWriter jsonWriter;
    private JSonReader jsonReader;

    //EFFECTS: runs the cookbook instance.
    public VirtualCookbook() {
        jsonWriter = new JSonWriter(JSON_STORE);
        jsonReader = new JSonReader(JSON_STORE);
        runCookbook();
    }

    //EFFECTS: runs the virtual cookbook.
    private void runCookbook() {
        boolean keepGoing = true;
        String text = null;
        instantiate();

        while (keepGoing) {
            displayMenu();
            text = input.next();
            text = text.toLowerCase();
            if (text.equals("close cookbook")) {
                keepGoing = false;
            } else {
                processCommand(text);
            }
        }
        System.out.println("\nHave a good day! Goodbye!");
    }


    //REQUIRES: it requires the user to enter a specific string to run a command.
    //EFFECTS: takes user to specified request
    private void processCommand(String command) {
        if (command.equals("add")) {
            addRecipe();
        } else if (command.equals("remove")) {
            removeRecipe();
        } else if (command.equals("modify")) {
            modifyRecipe();
        } else if (command.equals("search")) {
            searchRecipe();
        } else if (command.equals("view all")) {
            doView();
        } else if (command.equals("load")) {
            loadRecipes();
        } else if (command.equals("save")) {
            saveRecipes();
        } else if (command.equals("close cookbook")) {
            closeCookbook();
        } else {
            System.out.println("Selection is invalid. Please type the option you want to select.");
        }
    }


    //EFFECTS: It creates an instance of a recipe list to store all the recipes entered by the user. Also, we
    //         instantiate a new scanner that expects the user's input.
    private void instantiate() {
        listRec = new RecipeList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    //EFFECTS: displays all the possible options the user can see and choose.
    private void displayMenu() {
        System.out.println("\n\nSelect from:");
        System.out.println("add");
        System.out.println("remove");
        System.out.println("modify");
        System.out.println("search");
        System.out.println("view all");
        System.out.println("load");
        System.out.println("save");
        System.out.println("close cookbook");
    }

    //MODIFIES: this
    //EFFECTS: processes the input of adding a recipe by making the user enter a new recipe.
    private void addRecipe() {
        listRec.addRecipes(makeRecipe());
        System.out.println("Recipe has been added!");
    }


    //MODIFIES: this
    //EFFECTS: It allows the user to remove the recipe of his choice, firstly all the recipes in the cookbook will be
    //         shown and then the user can enter one of the recipe names and remove it.
    private void removeRecipe() {
        System.out.println("Select which recipe you want to remove.");
        doView();
        String name = input.next();
        listRec.removeRecipeByName(name);
        System.out.println("Changes have been made");
    }


    //MODIFIES: this
    //EFFECTS: Gives user all the recipe names in the cookbook, then asks user to pick a recipe from that. Then,
    //         prompts the user to create a new recipe to modify the recipe. (They could copy and paste the whole recipe
    //         and make all the necessary modifications.)
    private void modifyRecipe() {
        System.out.println("Select which recipe you would like to change.");
        doView();
        System.out.println("\nEnter the name of the recipe you want to change");
        String name = input.next();
        System.out.println("\nTo modify please enter the name of the new recipe.");
        listRec.modifyRecipe(name, makeRecipe());
        System.out.println("Modifications have been made to the list of recipes!");
    }


    //EFFECTS: this feature allows user to search for a recipe by a certain requirement, it outputs all the recipes that
    //         fulfill the requirement.
    private void searchRecipe() {
        List<Recipe> selectedRecipes = selectRecipe();
        if (selectedRecipes.isEmpty()) {
            System.out.println("No Recipes found!! Sorry!!");
        }
        for (Recipe r : selectedRecipes) {
            System.out.println(r.getName());
        }
    }


    //EFFECTS: outputs all the recipes' names in the cookbook.
    private void doView() {
        System.out.println("Here are all your recipes.");
        for (Recipe r : listRec.getAllRecipes()) {
            System.out.println(r.getName());
        }
    }


    //EFFECTS: it closes the cookbook and outputs a nice message.
    private void closeCookbook() {
        System.out.println("Goodbye!");
    }


    //MODIFIES: selectedRecs (list of recipes)
    //EFFECTS: It checks whether the user's input is one of the two, then calls for a helper.
    private List<Recipe> selectRecipe() {
        List<Recipe> selectedRecs = new ArrayList<>();
        String selection = "";
        while (!(selection.equals("i") || selection.equals("t"))) {
            System.out.println("\ni for ingredients");
            System.out.println("t for cooking time");
            selection = input.next();
            selection = selection.toLowerCase();
            if (selection.equals("i") || selection.equals("t")) {
                selectedRecs = selectRecipeHelper(selection);
            } else {
                System.out.println("Invalid Selection!! Please try again");
            }
        }
        return selectedRecs;
    }


    //REQUIRES: It requires a string (the user's input)
    //EFFECTS: it runs a specific method for each of the options. If it is "i" it will call another helper that takes in
    //         a list of ingredients, then it wil return the list of recipes that contain these ingredients. If it is
    //         "t" it will filter all the recipes using the searchByCookingTime method in RecipeList, then it returns a
    //         list of recipes.
    private List<Recipe> selectRecipeHelper(String selection) {

        if (selection.equals("i")) {
            System.out.println("Enter ingredients: ");
            List<String> ingredients = checkMakeListIng();
            return listRec.searchByIngredients(ingredients);
        } else {
            System.out.println("Enter cooking time :");
            int time = input.nextInt();
            return listRec.searchByCookingTime(time);
        }
    }


    //MODIFIES: listIngredients.
    //EFFECTS: It prompts user for the specific ingredients they want to filter by, then outputs the list of
    // ingredients.
    private List<String> checkMakeListIng() {
        List<String> listIngredients = new ArrayList<>();
        String ing = input.next().toLowerCase();
        boolean keepGoing = true;
        String ingredient = ing;
        listIngredients.add(ingredient);
        while (keepGoing) {
            System.out.println("Do you want to add more ingredients? Type y for yes any other input is considered"
                    + " as a no.");
            String result = input.next();
            if (result.equals("y")) {
                System.out.println("Add ingredients:");
                ingredient = input.next().toLowerCase();
            } else {
                keepGoing = false;
            }
        }
        return listIngredients;
    }

    //REQUIRES: an instruction from the user.
    //MODIFIES: listInstruct
    //EFFECTS: It prompts the user to add an arbitrary number of instructions then outputs it.
    private List<String> checkCookingInput(String instructions) {
        String result = input.next();
        boolean keepGoing = true;
        String instruction = result;
        List<String> listInstruct = new ArrayList<>();
        listInstruct.add(instructions);
        while (keepGoing) {
            if (instruction.equals("y")) {
                System.out.println("Add instructions:");
                instruction = input.next();
                instruction = instruction.toLowerCase();
                listInstruct.add(instruction);
                System.out.println("Do you to add more instructions? Type y for yes anything else entered is considered"
                        + " as a no.");
                instruction = input.next();
            } else {
                keepGoing = false;
            }
        }
        return listInstruct;
    }

    //REQUIRES: an ingredient from the user.
    //MODIFIES: listIngredients
    //EFFECTS: It prompts the user to add an arbitrary number of ingredients then outputs it.
    private List<String> checkInput(String ing) {
        String result = input.next();
        boolean keepGoing = true;
        String ingredient = result;
        List<String> listIngredients = new ArrayList<>();
        listIngredients.add(ing.toLowerCase());
        while (keepGoing) {
            if (ingredient.equals("y")) {
                System.out.println("Add ingredients:");
                ingredient = input.next();
                ingredient = ingredient.toLowerCase();
                listIngredients.add(ingredient);
                System.out.println("Do you to add more ingredients? Type y for yes, anything else entered is considered"
                        + " as a no.");
                ingredient = input.next();
            } else {
                keepGoing = false;
            }
        }
        return listIngredients;
    }

    //MODIFIES: recipe
    //EFFECTS: It creates a new recipe by prompting the reader to enter its name, ingredients, instructions, prep time,
    //         cooking time, calories, and a brief description.
    public Recipe makeRecipe() {
        System.out.println("Enter name of recipe");
        String name = input.next();


        System.out.println("Enter ingredients of recipe");
        String firstIng = input.next();
        System.out.println("Do you want to add more ingredients? Type y for yes any other input is considered"
                + " as a no.");
        List<String> ingredients = checkInput(firstIng);


        System.out.println("Enter instructions of recipe");
        String firstInstruct = input.next();
        System.out.println("Do you want to add more instructions? Type y for yes any other input is considered"
                + " as a no.");
        List<String> instructions = checkCookingInput(firstInstruct);


        System.out.println("Enter prep time (minutes) of recipe");
        int prepTime = input.nextInt();


        System.out.println("Enter cooking time (minutes) of recipe :");
        int cookingTime = input.nextInt();


        System.out.println("Enter calories of recipe:");
        int calories = input.nextInt();


        System.out.println("Enter description of recipe:");
        String description = input.next();

        Recipe recipe;
        return recipe = new Recipe(name, ingredients, instructions, prepTime, cookingTime, calories, description);
    }

    // EFFECTS: saves the workroom to file
    private void saveRecipes() {
        try {
            jsonWriter.open();
            jsonWriter.write(listRec);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadRecipes() {
        try {
            listRec = jsonReader.read();
            System.out.println("Loaded saved recipes");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
