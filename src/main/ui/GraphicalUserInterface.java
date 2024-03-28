package ui;

import model.Recipe;
import model.RecipeList;
import persistence.JSonReader;
import persistence.JSonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//GraphicalUserInterface UI , has all the main functionalities implemented (you can add, remove, modify recipes and
// search for recipes using specific ingredients or a specified cooking time.) with the addition of graphics.

public class GraphicalUserInterface {
    //All the fields in the class.
    private static final String JSON_STORE = "./data/cookbook.json";
    private RecipeList listRec;
    private JSonWriter jsonWriter;
    private JSonReader jsonReader;
    private JButton addButton;
    private JButton removeButton;
    private JButton showAllButton;
    private JButton modifyButton;
    private JButton searchButton;
    private JButton loadButton;
    private JButton saveButton;
    private JFrame cookbookFrame;
    private JPanel outputPanel;
    private JPanel cookbookPanel;
    private JTextField nameField;
    private JTextField ingredientsField;
    private JTextField instructionsField;
    private JTextField prepTimeField;
    private JTextField cookingTimeField;
    private JTextField caloriesField;
    private JTextField descriptionField;
    private JPanel panel;
    private JPanel removeRecipePanel;
    private JTextField nameFieldRemove;
    private JTextArea recipeTextArea;
    private JPanel modifyRecipePanel;
    private JPanel searchRecipePanel;

    //EFFECTS: creates a recipe list, all buttons and a cookbook and button panel and a cookbook frame. Also
    // instantiates the JSon Writer and JSon Reader.
    public GraphicalUserInterface() {
        listRec = new RecipeList();
        createButtons();
        allActionButtons();
        createButtonPanel();
        createOutputPanel();
        createFrame();
        jsonWriter = new JSonWriter(JSON_STORE);
        jsonReader = new JSonReader(JSON_STORE);
    }

    //EFFECTS: creates all the buttons for the user to interact with.
    private void createButtons() {
        addButton = new JButton("Add recipe");
        removeButton = new JButton("Remove recipe");
        showAllButton = new JButton("Show All");
        modifyButton = new JButton("Modify recipe");
        searchButton = new JButton("Search recipes");
        loadButton = new JButton("Load recipes");
        saveButton = new JButton("Save recipes");
    }

    //EFFECTS: creates the output panel named "Results" that shows all the output to the user.
    private void createOutputPanel() {
        outputPanel = new JPanel(new GridBagLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Results"));
    }

    //EFFECTS: creates the GUI frame that the user will be shown upon running named "Virtual Cookbook", has the cookbook
    //         and output panel added to it.
    public void createFrame() {
        cookbookFrame = new JFrame();
        cookbookFrame.add(cookbookPanel, BorderLayout.LINE_START);
        cookbookFrame.add(outputPanel, BorderLayout.CENTER);
        cookbookFrame.setSize(750, 750);
        cookbookFrame.setLocation(300, 100);
        cookbookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cookbookFrame.setTitle("Virtual Cookbook");
        cookbookFrame.setVisible(true);
        cookbookFrame.setResizable(true);
    }

    //EFFECTS: creates a new JPanel that is the buttons panel, with a logo and all the buttons.
    public void createButtonPanel() {
        cookbookPanel = new JPanel();
        ImageIcon originalIcon = new ImageIcon(
                "C:\\Users\\athif\\IdeaProjects\\CPSC210_Project\\src\\main\\Images\\Untitled Project.jpg");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imgLabel = new JLabel(resizedIcon);
        cookbookPanel.add(imgLabel, BorderLayout.CENTER);

        cookbookPanel.setBorder(BorderFactory.createTitledBorder("Choices"));
        Box buttons = Box.createVerticalBox();
        buttons.add(imgLabel);
        buttons.add(addButton);
        buttons.add(removeButton);
        buttons.add(modifyButton);
        buttons.add(searchButton);
        buttons.add(showAllButton);
        buttons.add(loadButton);
        buttons.add(saveButton);
        cookbookPanel.add(buttons);
    }

    //EFFECTS: this calls all the action buttons present for each button in the buttons panel.
    private void allActionButtons() {
        addButtonAction();
        removeButtonAction();
        modifyRecipeAction();
        viewAllAction();
        searchButtonAction();
        loadButtonAction();
        saveButtonAction();
    }

    //EFFECTS: Upon pressing, adds a recipe to the user's recipe list.
    private void addButtonAction() {
        addButton.addActionListener(e -> addRecipe());
    }

    //EFFECTS: creates a new panel that allows the user to enter their new recipe and submit it, once submitted they are
    //         returned to the startup screen.
    public void addRecipe() {
        createTextFields();
        createAddRecipePanel();
        panel.validate();
        panel.revalidate();
        panel.repaint();

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton, BorderLayout.SOUTH);
        submitButton.addActionListener(e -> parseRecipe());
    }

    //EFFECTS: creates the necessary text fields for the user to add their recipe.
    public void createTextFields() {
        nameField = new JTextField();
        ingredientsField = new JTextField();
        instructionsField = new JTextField();
        prepTimeField = new JTextField();
        cookingTimeField = new JTextField();
        caloriesField = new JTextField();
        descriptionField = new JTextField();
    }

    //EFFECTS: creates the new panel for the user to add their recipe, it removes the previous panel and makes itself
    //         visible.
    private void createAddRecipePanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new JLabel("Enter name of recipe:"));
        panel.add(nameField);
        panel.add(new JLabel("Enter ingredients of recipe:"));
        panel.add(ingredientsField);
        panel.add(new JLabel("Enter instructions of recipe:"));
        panel.add(instructionsField);
        panel.add(new JLabel("Enter prep time (minutes) (only numbers)of recipe:"));
        panel.add(prepTimeField);
        panel.add(new JLabel("Enter cooking time (minutes) (only numbers) of recipe:"));
        panel.add(cookingTimeField);
        panel.add(new JLabel("Enter calories (only numbers) of recipe:"));
        panel.add(caloriesField);
        panel.add(new JLabel("Enter description of recipe:"));
        panel.add(descriptionField);

        cookbookFrame.removeAll();
        createFrame();
        cookbookFrame.remove(outputPanel);
        cookbookFrame.add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
    }

    //EFFECTS: this converts all the user's input into a Recipe that can be stored in the JSON File.
    private void parseRecipe() {
        String name = nameField.getText();
        String[] ingredients = ingredientsField.getText().split(",");
        String[] instructions = instructionsField.getText().split(",");
        int prepTime = 0;
        if (!prepTimeField.getText().equals("")) {
            prepTime = Integer.parseInt(prepTimeField.getText());
        }
        int cookingTime = 0;
        if (!cookingTimeField.getText().equals("")) {
            cookingTime = Integer.parseInt(cookingTimeField.getText());
        }
        int calories = 0;
        if (!caloriesField.getText().equals("")) {
            calories = Integer.parseInt(caloriesField.getText());
        }
        String description = descriptionField.getText();
        Recipe test = new Recipe(name, List.of(ingredients), List.of(instructions),
                prepTime, cookingTime, calories, description);
        listRec.addRecipes(test);
        resetFrameForAdd();
        // want to output (name + " has been added!");
    }

    //EFFECTS: Upon submitting recipe, this is responsible for making the addRecipe panel invisible and the output panel
    //         visible.
    private void resetFrameForAdd() {
        cookbookFrame.removeAll();
        createFrame();
        cookbookFrame.repaint();
        panel.setVisible(false);
        cookbookFrame.remove(panel);
        cookbookFrame.add(outputPanel, BorderLayout.CENTER);
    }

    //EFFECTS: This should output all the recipe names in the list on top while the user is removing a recipe.
    private void doViewModify() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        for (Recipe r : listRec.getAllRecipes()) {
            JLabel recipesNames = new JLabel(r.getName());
            this.removeRecipePanel.add(recipesNames, gbc);
        }
        removeRecipePanel.validate();
        removeRecipePanel.revalidate();
        removeRecipePanel.repaint();
        this.removeRecipePanel.setVisible(true);
    }

    private void modifyRecipe() {
        JLabel text = new JLabel("Select which recipe you would like to change.");
        doViewModify();
        JLabel text2 = new JLabel("\nEnter the name of the recipe you want to change");
        JLabel text3 = new JLabel("\nTo modify please enter the name of the new recipe.");

    }

    private void createModifyRecipePanel() {
        nameFieldRemove = new JTextField(20);
        modifyRecipePanel = new JPanel();
        removeRecipePanel.setLayout(new BoxLayout(removeRecipePanel, BoxLayout.PAGE_AXIS));
        doViewRemove();
        removeRecipePanel.add(new JLabel("Enter name of recipe:"));
        removeRecipePanel.add(nameFieldRemove);

        cookbookFrame.removeAll();
        createFrame();
        cookbookFrame.repaint();
        cookbookFrame.remove(outputPanel);
        cookbookFrame.add(removeRecipePanel, BorderLayout.CENTER);
        removeRecipePanel.setVisible(true);
    }


    //EFFECTS: Upon pressing this button, the user can modify an existing recipe by entering its name and then adding
    //         a new modified recipe.
    private void modifyRecipeAction() {
        modifyButton.addActionListener(e -> {
            //modifyRecipe();
        });
    }

    //EFFECTS: Upon pressing this button, the user will be taken to a new panel that allows them to enter a name of a
    //         recipe and remove it.
    private void removeButtonAction() {
        removeButton.addActionListener(e -> {
            createRemoveRecipePanel();
            JButton submitButton = new JButton("Submit");
            removeRecipePanel.add(submitButton, BorderLayout.SOUTH);
            submitButton.addActionListener(e1 -> removeRecipe());


        });
    }

    //EFFECTS: This should output all the recipe names in the list on top while the user is removing a recipe.
    private void doViewRemove() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        for (Recipe r : listRec.getAllRecipes()) {
            JLabel recipesNames = new JLabel(r.getName());
            this.removeRecipePanel.add(recipesNames, gbc);
        }
        removeRecipePanel.validate();
        removeRecipePanel.revalidate();
        removeRecipePanel.repaint();
        this.removeRecipePanel.setVisible(true);
    }

    //EFFECTS: Upon pressing the submit button, this method removes the recipe and the removeRecipe panel and creates
    //         a new output panel.
    public void removeRecipe() {
        String name = nameFieldRemove.getText();
        listRec.removeRecipeByName(name);

        removeRecipePanel.setVisible(false);
        cookbookFrame.remove(removeRecipePanel);
        cookbookFrame.add(outputPanel, BorderLayout.CENTER);
        outputPanel.setVisible(true);
        //want to output (name + " recipe has been removed")
    }

    //EFFECTS: creates a remove Recipe panel, which allows the user to enter a recipe name and remove it.
    private void createRemoveRecipePanel() {
        nameFieldRemove = new JTextField(20);
        removeRecipePanel = new JPanel();
        removeRecipePanel.setLayout(new BoxLayout(removeRecipePanel, BoxLayout.PAGE_AXIS));
        doViewRemove();
        removeRecipePanel.add(new JLabel("Enter name of recipe:"));
        removeRecipePanel.add(nameFieldRemove);

        cookbookFrame.removeAll();
        createFrame();
        cookbookFrame.repaint();
        cookbookFrame.remove(outputPanel);
        cookbookFrame.add(removeRecipePanel, BorderLayout.CENTER);
        removeRecipePanel.setVisible(true);
    }

    //EFFECTS: this method is run when the Show All button is run, this calls the doView method which is responsible
    // for the functionality.
    private void viewAllAction() {
        showAllButton.addActionListener(e -> doView());
    }

    //EFFECTS: outputs all the recipe names present in the recipe list currently.
    private void doView() {
        cookbookFrame.removeAll();
        createFrame();
        cookbookFrame.setVisible(true);
        outputPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        for (Recipe r : listRec.getAllRecipes()) {
            JLabel recipesNames = new JLabel(r.getName());
            this.outputPanel.add(recipesNames, gbc);
        }
        this.outputPanel.validate();
        outputPanel.revalidate();
        outputPanel.repaint();
        this.outputPanel.setVisible(true);
    }

    //EFFECTS: this method is run upon the user pressing the search button, which allows the user to search for
    //         something depending on the ingredients and time.
    private void searchButtonAction() {
        searchButton.addActionListener(e3 -> searchRecipe());
    }

    //EFFECTS: creates a new panel, lets the user input their choice and outputs either the list of recipes or the
    //         error messages.
    private void searchRecipe() {
        createSearchRecipePanel();
        searchRecipePanel.validate();
        searchRecipePanel.revalidate();
        searchRecipePanel.repaint();

        JButton searchButton = new JButton("Search");
        searchRecipePanel.add(searchButton, BorderLayout.SOUTH);
        searchButton.addActionListener(e -> searchRecipes());

        JButton exitButton = new JButton("Exit to main menu");
        searchRecipePanel.add(exitButton, BorderLayout.SOUTH);
        exitButton.addActionListener(e -> exit());
    }

    //MODIFIES: cookbookFrame
    //EFFECTS: exits back to main menu of the cookbook
    private void exit() {
        searchRecipePanel.setVisible(false);
        cookbookFrame.removeAll();
        createFrame();
    }

    //MODIFIES: cookbookFrame
    //EFFECTS: creates a searchRecipePanel with two text fields and output window.
    private void createSearchRecipePanel() {
        searchRecipePanel = new JPanel();
        searchRecipePanel.setLayout(new BoxLayout(searchRecipePanel, BoxLayout.Y_AXIS));
        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsField = new JTextField(20);
        searchRecipePanel.add(ingredientsLabel);
        searchRecipePanel.add(ingredientsField);
        JLabel cookingTimeLabel = new JLabel("Cooking Time:");
        cookingTimeField = new JTextField(10);
        searchRecipePanel.add(cookingTimeLabel);
        searchRecipePanel.add(cookingTimeField);

        recipeTextArea = new JTextArea(10, 30);
        recipeTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recipeTextArea);
        searchRecipePanel.add(scrollPane);
        cookbookFrame.removeAll();
        createFrame();
        cookbookFrame.repaint();
        cookbookFrame.remove(outputPanel);
        cookbookFrame.add(searchRecipePanel, BorderLayout.CENTER);
        searchRecipePanel.setVisible(true);
    }

    //EFFECTS: handles output cases for the searchRecipe() method
    public void searchRecipes() {
        List<Recipe> selectedRecipes = selectRecipe();
        if (selectedRecipes.isEmpty()) {
            recipeTextArea.setText("No Recipes found!! Sorry!!");
        } else {
            StringBuilder recipesText = new StringBuilder();
            for (Recipe r : selectedRecipes) {
                recipesText.append(r.getName()).append("\n");
            }
            recipeTextArea.setText(recipesText.toString());
        }
    }

    //EFFECTS: returns a list of recipes, it checks the user's input and determines which selection of the helper to be
    //         used.
    public List<Recipe> selectRecipe() {
        List<Recipe> selectedRecs = new ArrayList<>();
        if (ingredientsField.getText().isEmpty() && cookingTimeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(cookbookFrame, "Please enter ingredients or cooking time.");
            return selectedRecs;
        } else if (!ingredientsField.getText().isEmpty() && !cookingTimeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(cookbookFrame, "Please enter either ingredients or cooking time, not both.");
            return selectedRecs;
        } else if (!ingredientsField.getText().isEmpty()) {
            selectedRecs = selectRecipeHelper("i");
        } else if (!cookingTimeField.getText().isEmpty()) {
            selectedRecs = selectRecipeHelper("t");
        }
        return selectedRecs;
    }

    // MODIFIES: searchRecipePanel
    //EFFECTS: searches the recipe depending on the selection by either time or ingredients.
    private List<Recipe> selectRecipeHelper(String selection) {
        if (selection.equals("i")) {
            List<String> ingredients = Arrays.asList(ingredientsField.getText().split(","));
            return listRec.searchByIngredients(ingredients);
        } else if (selection.equals("t")) {
            try {
                int time = Integer.parseInt(cookingTimeField.getText());
                return listRec.searchByCookingTime(time);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(cookbookFrame, "Please enter a valid number for cooking time.");
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }


    //EFFECTS: Upon pressing the Save button this method is run, it writes the current recipe list to a json file.
    private void saveButtonAction() {
        saveButton.addActionListener(e2 -> saveRecipes());
    }

    //EFFECTS: this method is responsible for writing the json file for the user.
    public void saveRecipes() {
        cookbookFrame.removeAll();
        createFrame();
        cookbookFrame.repaint();
        try {
            jsonWriter.open();
            jsonWriter.write(listRec);
            jsonWriter.close();
            //create a pop-up window.
            JTextField saveMessage = new JTextField();
            //saveMessage = ("Saved to " + JSON_STORE);
            //outputPanel.add(saveMessage);
            //re

            //want to output ("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            //want to output something in console
        }
    }

    //EFFECTS: Upon pressing the load button, it reads the recipe list stored in the JSon file.
    private void loadButtonAction() {
        loadButton.addActionListener(e -> loadRecipes());
    }

    //EFFECTS: this method is responsible for reading Json file.
    public void loadRecipes() {
        cookbookFrame.removeAll();
        createFrame();
        cookbookFrame.repaint();
        try {
            listRec = jsonReader.read();
            if (listRec.getSize() == 0) {
                //want to output ("No recipes saved");
            }
        } catch (IOException e) {
            //want to output something in console
        }
    }
}




