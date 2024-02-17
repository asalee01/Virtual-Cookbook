package ui;

import model.Recipe;
import model.RecipeList;

import java.util.*;

public class VirtualCookbook {

    private Recipe recipe;
    private Scanner input;
    private RecipeList listRec;

    public VirtualCookbook() {
        runCookbook();
    }

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

    //MODIFIES: this
    //EFFECTS: takes user to specified request
    private void processCommand(String command) {
        switch (command) {
            case "add":
                addRecipe();
                break;
            case "remove":
                removeRecipe();
                break;
            case "modify":
                modifyRecipe();
                break;
            case "search":
                searchRecipe();
                break;
            case "view all":
                doView();
                break;
            case "close cookbook":
                closeCookbook();
                break;
            default:
                System.out.println("Selection is invalid. Please type the option you want to select.");
                break;
        }
    }

    private void instantiate() {
        listRec = new RecipeList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("Select from:");
        System.out.println("add");
        System.out.println("remove");
        System.out.println("modify");
        System.out.println("search");
        System.out.println("view all");
        System.out.println("close cookbook");
    }

    //MODIFIES: this
    //EFFECTS: processes the input of adding a recipe.
    private void addRecipe() {
        listRec.addRecipes(makeRecipe());
    }

    private void removeRecipe() {
        System.out.println("Select which recipe you want to remove.");
        doView();
        String name = input.next();
        listRec.removeRecipeByName(name);
        System.out.println("Recipe has been Removed");
    }

    private void modifyRecipe() {
        System.out.println("Select which recipe you would like to change.");
        doView();
        System.out.println("\nEnter the name of the recipe you want to change");
        String name = input.next();
        System.out.println("\nTo modify please enter a recipe that you would to substitute.");
        listRec.modifyRecipe(name, makeRecipe());
    }


    private List<Recipe> searchRecipe() {
        List<Recipe> selectedRecipes = selectRecipe();
        if (selectedRecipes.isEmpty()) {
            System.out.println("No Recipes found!! Sorry!!");
        }
        return selectedRecipes;
    }

    private void doView() {
        System.out.println("Here are all your recipes.");
        for (Recipe r : listRec.getAllRecipes()) {
            System.out.println(r.getName());
        }
    }

    private void closeCookbook() {
        System.out.println("Goodbye!");
    }


    private List<Recipe> selectRecipe() {
        List<Recipe> selectedRecs = new ArrayList<>();
        String selection = "";
        while (!(selection.equals("i") || selection.equals("t"))) {
            System.out.println("i for ingredients");
            System.out.println("t for cooking time");
            selection = input.next();
            selection = selection.toLowerCase();
            selectedRecs = selectRecipeHelper(selection);
        }
        return selectedRecs;
    } //if (selectedRecs.isEmpty())

    private List<Recipe> selectRecipeHelper(String selection) {
        if (selection.equals("i")) {
            System.out.println("Enter ingredients: ");
            List<String> ingredients = checkMakeListIng();
            return listRec.searchByIngredients(ingredients);
        } else {
            System.out.println("Enter cooking time :");
            int time = input.nextInt();
            return listRec.searchByCookingTime(time);
            //TODO: fix this error. Doesn't return the recipe but function works.
        }
    }


    private List<String> checkMakeListIng() {
        List<String> listIngred = new ArrayList<>();
        String ing = input.next().toLowerCase();
        boolean keepGoing = true;
        String ingredient = ing;
        listIngred.add(ingredient);
        while (keepGoing) {
            System.out.println("Do you want to add more ingredients? Type y for yes.");
            String result = input.next();
            if (result.equals("y")) {
                System.out.println("Add ingredients:");
                ingredient = input.next();
                ingredient = ingredient.toLowerCase();
                System.out.println("Do you to add more ingredients? Type y for yes, anything else entered is considered"
                        + "as a no.");
            } else {
                keepGoing = false;
            }
        }
        return listIngred;
    }


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
                //checkInput();
            } else {
                keepGoing = false;
            }
        }
        return listIngredients;
    }

    public Recipe makeRecipe() {
        System.out.println("Enter name of recipe");
        String name = input.next(); //rec.setName(input.next());
        System.out.println("Enter ingredients of recipe");
        String firstIng = input.next(); //rec.setIngredients(input.next());
        System.out.println("Do you to add more ingredients? Type y for yes and n for no.");
        List<String> ingredients = checkInput(firstIng); // check if y, if it is y then loop the ingredients
        System.out.println("Enter instructions of recipe");
        String firstInstruc = input.next(); //rec.setCookingInstructions(input.next());
        System.out.println("Do you to add more instructions? Type y for yes and n for no.");
        List<String> instructions = checkCookingInput(firstInstruc);// check if y, if it is y then loop the instructions
        System.out.println("Enter prep time (minutes) of recipe");
        int prepTime = input.nextInt(); //rec.setPrepTime(input.nextInt());
        System.out.println("Enter cooking time (minutes) of recipe :");
        int cookingTime = input.nextInt(); //rec.setCookingTime(input.nextInt());
        System.out.println("Enter calories of recipe:");
        int calories = input.nextInt(); //rec.setCalories(input.nextInt());
        System.out.println("Enter description of recipe:");
        String description = input.next();
        System.out.println("Recipe has been added!");
        return recipe = new Recipe(name, ingredients, instructions, prepTime, cookingTime, calories, description);
    }
}

