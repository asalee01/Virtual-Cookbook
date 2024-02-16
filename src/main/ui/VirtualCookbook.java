package ui;

import model.Recipe;
import model.RecipeList;

import java.util.*;

public class VirtualCookbook {

    private Recipe recipe;
    private Scanner input;

    private List<String> listIng;
    private List<String> listInstruct;
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
        if (command.equals("add")) {
            doAdd();
        } else if (command.equals("remove")) {
            doRemove();
        } else if (command.equals("modify")) {
            doModify();
        } else if (command.equals("search")) {
            doSearch();
        } else if (command.equals("view all")) {
            doView();
        } else if (command.equals("close cookbook")) {
            doClose();
        } else {
            System.out.println("Selection is invalid. Please type the option you want to select.");
        }
    }

    private void instantiate() {
        // rec = new Recipe();
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
    private void doAdd() {
        System.out.println("Enter name of recipe");
        String name = input.next(); //rec.setName(input.next());
        System.out.println("Enter ingredients of recipe");
        input.next(); //rec.setIngredients(input.next());
        System.out.println("Do you to add more ingredients? Type y for yes and n for no.");
        List<String> ingredients = checkInput(); // check if y, if it is y then loop the ingredients
        System.out.println("Enter instructions of recipe");
        input.next(); //rec.setCookingInstructions(input.next());
        System.out.println("Do you to add more instructions? Type y for yes and n for no.");
        List<String> instructions = checkCookingInput(); // check if y, if it is y then loop the instructions
        System.out.println("Enter prep time (minutes) of recipe");
        int prepTime = input.nextInt(); //rec.setPrepTime(input.nextInt());
        System.out.println("Enter cooking time (minutes) of recipe :");
        int cookingTime = input.nextInt(); //rec.setCookingTime(input.nextInt());
        System.out.println("Enter calories of recipe:");
        int calories = input.nextInt(); //rec.setCalories(input.nextInt());
        System.out.println("Enter description of recipe:");
        String description = input.next();
        System.out.println("Recipe has been added!");
        recipe = new Recipe(name, ingredients, instructions, prepTime, cookingTime, calories, description);
        listRec.addRecipes(recipe);
    }

    private void doRemove() {
        System.out.println("Select which recipe you want to remove.");
        doView();
        String name = input.next();
        listRec.removeRecipeByName(name);
        System.out.println("Recipe has been Removed");
    }

    private void doModify() {
        System.out.println("Select which recipe you would like to change.");
        doView();
        System.out.println("Enter the name of the recipe you want to change");
        String name = input.next();
        //listRec.modifyRecipe(name, );
    } //TODO: code this function such that it works.


    private List<Recipe> doSearch() {
        List<Recipe> selectedRecipes = selectRecipe();
        if (selectedRecipes.isEmpty()) {
            System.out.println("No Recipes found!! Sorry!!");
        }
        return selectedRecipes;
    }

    private void doView() {
        System.out.println("Here are all your recipes.");
        for (Recipe r : listRec.allRecipes()) {
            System.out.println(r.getName());
        }
    }

    private void doClose() {
        System.out.println("Goodbye!");
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private List<Recipe> selectRecipe() {
        String selection = "";
        while (!(selection.equals("i") || selection.equals("t"))) {
            System.out.println("i for ingredients");
            System.out.println("t for cooking time");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("i")) {
            System.out.println("Enter ingredients: ");
            List<String> ingredients = checkInput();
            List<Recipe> filteredRecipes = listRec.searchByIngredients(ingredients);
            return filteredRecipes;
        } else {
            System.out.println("Enter cooking time :");
            int time = input.nextInt();
            List<Recipe> filteredRecipes = listRec.searchByCookingTime(time);
            return filteredRecipes;
        }
        //TODO: fix this error. Doesn't return the recipe but function works.
    }


    private List<String> checkInput2() {
        boolean keepGoing = true;
        listIng = new ArrayList<>();
        while (keepGoing) {
            System.out.println("Add ingredients:");
            String ingredient = input.next();
            listIng.add(ingredient);
            System.out.println("Do you to add more ingredients? Type y for yes and n for no.");
            String result = input.next();
            if (result.equals("n")) {
                keepGoing = false;
            }
        }
        return listIng;
    }

    private List<String> checkCookingInput() {
        String result = input.next();
        boolean keepGoing = true;
        String instruc = result;
        listInstruct = new ArrayList<>();
        while (instruc.equals("y")) {
            System.out.println("Add instructions:");
            instruc = input.next();
            instruc = instruc.toLowerCase();
            listInstruct.add(instruc);
            System.out.println("Do you to add more instructions? Type y for yes and n for no.");
            checkCookingInput();
        }
        return listInstruct;
    }

    private List<String> checkInput() {
        String result = input.next();
        boolean keepGoing = true;
        String ingred = result;
        listInstruct = new ArrayList<>();
        while (ingred.equals("y")) {
            System.out.println("Add ingredients:");
            ingred = input.next();
            ingred = ingred.toLowerCase();
            listInstruct.add(ingred);
            System.out.println("Do you to add more ingredients? Type y for yes and n for no.");
            checkInput();
        }
        return listInstruct;
    }
}

