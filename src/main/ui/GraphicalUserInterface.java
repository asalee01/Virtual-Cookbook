package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private final JSonWriter jsonWriter;
    private final JSonReader jsonReader;
    private RecipeList listRec;
    private JButton addButton;
    private JButton removeButton;
    private JTextField nameFieldModify;
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
    private JTextField nameFieldModifies;
    private JTextField ingredientsFieldModify;
    private JTextField instructionsFieldModify;
    private JTextField prepTimeFieldModify;
    private JTextField cookingTimeFieldModify;
    private JTextField caloriesFieldModify;
    private JTextField descriptionFieldModify;
    private JPanel modifyPanel;

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
    private void createFrame() {
        cookbookFrame = new JFrame();
        cookbookFrame.add(cookbookPanel, BorderLayout.LINE_START);
        cookbookFrame.add(outputPanel, BorderLayout.CENTER);
        cookbookFrame.setSize(750, 750);
        cookbookFrame.setLocation(300, 100);
        cookbookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cookbookFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close(e);
            }
        });
        cookbookFrame.setTitle("Virtual Cookbook");
        cookbookFrame.setVisible(true);
        cookbookFrame.setResizable(true);
    }

    //EFFECTS: creates a new JPanel that is the buttons panel, with a logo and all the buttons.
    private void createButtonPanel() {
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
    private void addRecipe() {
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
    private void createTextFields() {
        nameField = new JTextField();
        ingredientsField = new JTextField();
        instructionsField = new JTextField();
        prepTimeField = new JTextField();
        cookingTimeField = new JTextField();
        caloriesField = new JTextField();
        descriptionField = new JTextField();
    }

    //MODIFIES: cookbookFrame
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
        cookbookFrame.dispose();
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
        JOptionPane.showMessageDialog(cookbookFrame, name + " has been added!",
                "Update", JOptionPane.PLAIN_MESSAGE);
    }

    //MODIFIES: cookbookFrame;
    //EFFECTS: Upon submitting recipe, this is responsible for making the addRecipe panel invisible and the output panel
    //         visible.
    private void resetFrameForAdd() {
        cookbookFrame.removeAll();
        cookbookFrame.dispose();
        createFrame();
        cookbookFrame.repaint();
        panel.setVisible(false);
        cookbookFrame.remove(panel);
        cookbookFrame.add(outputPanel, BorderLayout.CENTER);
    }

    //EFFECTS: Upon pressing this button, the user can modify an existing recipe by entering its name and then adding
    //         a new modified recipe.
    private void modifyRecipeAction() {
        modifyButton.addActionListener(e -> {
            createModifyRecipePanel();
            JButton submitButton = new JButton("Submit");
            modifyRecipePanel.add(submitButton, BorderLayout.SOUTH);
            submitButton.addActionListener(e2 -> modifyRecipe());
        });
    }

    //EFFECTS: creates a panel that prompts the user to enter the name of the recipe they want to modify, then transfers
    //         them to a new panel that allows them to make their changes.
    private void modifyRecipe() {
        createTextFieldsModify();
        createAddModifyRecipePanel();
        modifyPanel.validate();
        modifyPanel.revalidate();
        modifyPanel.repaint();

        JButton modify = new JButton("Submit changes");
        modifyPanel.add(modify, BorderLayout.SOUTH);
        modify.addActionListener(e -> {
            listRec.modifyRecipe(nameFieldModify.getText(), parseRecipeModify());
            resetFrameForModify();
            JOptionPane.showMessageDialog(cookbookFrame, "Recipe has been modified!",
                    "Update", JOptionPane.PLAIN_MESSAGE);
        });
    }

    //MODIFIES: cookbookFrame
    //EFFECTS: creates a panel that shows all the recipes in the list and prompts user to enter one of the names.
    private void createModifyRecipePanel() {
        nameFieldModify = new JTextField(20);
        modifyRecipePanel = new JPanel();
        modifyRecipePanel.setLayout(new BoxLayout(modifyRecipePanel, BoxLayout.PAGE_AXIS));
        doViewModify();
        modifyRecipePanel.add(new JLabel("Enter name of recipe:"));
        modifyRecipePanel.add(nameFieldModify);

        cookbookFrame.removeAll();
        cookbookFrame.dispose();
        createFrame();
        cookbookFrame.repaint();
        cookbookFrame.remove(outputPanel);
        cookbookFrame.add(modifyRecipePanel, BorderLayout.CENTER);
        modifyRecipePanel.setVisible(true);
    }

    //EFFECTS: creates the necessary text fields for the user to modify their recipe.
    private void createTextFieldsModify() {
        nameFieldModifies = new JTextField();
        ingredientsFieldModify = new JTextField();
        instructionsFieldModify = new JTextField();
        prepTimeFieldModify = new JTextField();
        cookingTimeFieldModify = new JTextField();
        caloriesFieldModify = new JTextField();
        descriptionFieldModify = new JTextField();
    }

    //EFFECTS: creates the new panel for the user to modify their recipe, it removes the previous panel and makes itself
    //         visible.
    private void createAddModifyRecipePanel() {
        modifyPanel = new JPanel();
        modifyPanel.setLayout(new BoxLayout(modifyPanel, BoxLayout.PAGE_AXIS));
        modifyPanel.add(new JLabel("Enter name of recipe:"));
        modifyPanel.add(nameFieldModifies);
        modifyPanel.add(new JLabel("Enter ingredients of recipe:"));
        modifyPanel.add(ingredientsFieldModify);
        modifyPanel.add(new JLabel("Enter instructions of recipe:"));
        modifyPanel.add(instructionsFieldModify);
        modifyPanel.add(new JLabel("Enter prep time (minutes) (only numbers)of recipe:"));
        modifyPanel.add(prepTimeFieldModify);
        modifyPanel.add(new JLabel("Enter cooking time (minutes) (only numbers) of recipe:"));
        modifyPanel.add(cookingTimeFieldModify);
        modifyPanel.add(new JLabel("Enter calories (only numbers) of recipe:"));
        modifyPanel.add(caloriesFieldModify);
        modifyPanel.add(new JLabel("Enter description of recipe:"));
        modifyPanel.add(descriptionFieldModify);

        cookbookFrame.removeAll();
        cookbookFrame.dispose();
        createFrame();
        cookbookFrame.remove(outputPanel);
        cookbookFrame.add(modifyPanel, BorderLayout.CENTER);
        modifyPanel.setVisible(true);
    }

    //EFFECTS: this converts all the user's input into a Recipe that can be stored in the JSON File.
    private Recipe parseRecipeModify() {
        String name = nameFieldModifies.getText();
        String[] ingredients = ingredientsFieldModify.getText().split(",");
        String[] instructions = instructionsFieldModify.getText().split(",");
        int prepTime = 0;
        if (!prepTimeFieldModify.getText().equals("")) {
            prepTime = Integer.parseInt(prepTimeFieldModify.getText());
        }
        int cookingTime = 0;
        if (!cookingTimeFieldModify.getText().equals("")) {
            cookingTime = Integer.parseInt(cookingTimeFieldModify.getText());
        }
        int calories = 0;
        if (!caloriesFieldModify.getText().equals("")) {
            calories = Integer.parseInt(caloriesFieldModify.getText());
        }
        String description = descriptionFieldModify.getText();
        return new Recipe(name, List.of(ingredients), List.of(instructions),
                prepTime, cookingTime, calories, description);
    }

    //MODIFIES: cookbookFrame
    //EFFECTS: Upon submitting recipe, this is responsible for making the modifyRecipe panel invisible and the output
    // panel visible.
    private void resetFrameForModify() {
        cookbookFrame.removeAll();
        cookbookFrame.dispose();
        createFrame();
        cookbookFrame.repaint();
        modifyPanel.setVisible(false);
        cookbookFrame.remove(modifyPanel);
        cookbookFrame.add(outputPanel, BorderLayout.CENTER);
    }


    //EFFECTS: This should output all the recipe names in the list on top while the user is modifying a recipe.
    private void doViewModify() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        for (Recipe r : listRec.getAllRecipes()) {
            JLabel recipesNames = new JLabel(r.getName());
            this.modifyRecipePanel.add(recipesNames, gbc);
        }
        modifyRecipePanel.validate();
        modifyRecipePanel.revalidate();
        modifyRecipePanel.repaint();
        this.modifyRecipePanel.setVisible(true);
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
        removeRecipePanel.setVisible(true);
    }

    //MODIFIES: cookbookFrame
    //EFFECTS: Upon pressing the submit button, this method removes the recipe and the removeRecipe panel and creates
    //         a new output panel.
    private void removeRecipe() {
        String name = nameFieldRemove.getText();
        listRec.removeRecipeByName(name);

        removeRecipePanel.setVisible(false);
        cookbookFrame.remove(removeRecipePanel);
        cookbookFrame.add(outputPanel, BorderLayout.CENTER);
        outputPanel.setVisible(true);
        JOptionPane.showMessageDialog(cookbookFrame, name + " has been removed.",
                "Update", JOptionPane.PLAIN_MESSAGE);
    }

    //MODIFIES: cookbookFrame
    //EFFECTS: creates a remove Recipe panel, which allows the user to enter a recipe name and remove it.
    private void createRemoveRecipePanel() {
        nameFieldRemove = new JTextField(20);
        removeRecipePanel = new JPanel();
        removeRecipePanel.setLayout(new BoxLayout(removeRecipePanel, BoxLayout.PAGE_AXIS));
        doViewRemove();
        removeRecipePanel.add(new JLabel("Enter name of recipe:"));
        removeRecipePanel.add(nameFieldRemove);

        cookbookFrame.removeAll();
        cookbookFrame.dispose();
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

    //MODIFIES: cookbookFrame
    //EFFECTS: outputs all the recipe names present in the recipe list currently.
    private void doView() {
        cookbookFrame.removeAll();
        cookbookFrame.dispose();
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
        if (listRec.getAllRecipes().isEmpty()) {
            JOptionPane.showMessageDialog(cookbookFrame, "No recipes in current cookbook!",
                    "Note", JOptionPane.WARNING_MESSAGE);
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
        cookbookFrame.dispose();
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
        cookbookFrame.dispose();
        createFrame();
        cookbookFrame.repaint();
        cookbookFrame.remove(outputPanel);
        cookbookFrame.add(searchRecipePanel, BorderLayout.CENTER);
        searchRecipePanel.setVisible(true);
    }

    //EFFECTS: handles output cases for the searchRecipe() method
    private void searchRecipes() {
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
    private List<Recipe> selectRecipe() {
        List<Recipe> selectedRecs = new ArrayList<>();
        if (ingredientsField.getText().isEmpty() && cookingTimeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(cookbookFrame, "Please enter ingredients or cooking time.");
            return selectedRecs;
        } else if (!ingredientsField.getText().isEmpty() && !cookingTimeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(cookbookFrame,
                    "Please enter either ingredients or cooking time, not both.");
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
    private void saveRecipes() {
        cookbookFrame.removeAll();
        cookbookFrame.dispose();
        createFrame();
        cookbookFrame.repaint();
        try {
            jsonWriter.open();
            jsonWriter.write(listRec);
            jsonWriter.close();
            JOptionPane.showMessageDialog(cookbookFrame, "Saved to " + JSON_STORE,
                    "Update", JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(cookbookFrame, "Unable to write to file: " + JSON_STORE,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: Upon pressing the load button, it reads the recipe list stored in the JSon file.
    private void loadButtonAction() {
        loadButton.addActionListener(e -> loadRecipes());
    }

    //EFFECTS: this method is responsible for reading Json file.
    private void loadRecipes() {
        try {
            listRec = jsonReader.read();
            if (listRec.getSize() == 0) {
                JOptionPane.showMessageDialog(cookbookFrame, "No recipes present in saved file!!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            {
                JOptionPane.showMessageDialog(cookbookFrame, "Unable to read from file: " + JSON_STORE,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //EFFECTS: prints out all the log events that have occurred when the cookbook is run.
    public void close(WindowEvent e) {
        for (model.Event e1 : model.EventLog.getInstance()) {
            System.out.println(e1);
        }
    }

}


// References: I had many, but I tried to track back to everything that I used.
// https://stackoverflow.com/questions/21375255/
// jpanel-positions-and-sizes-changes-according-to-screensize/21376596#21376596
// https://www.tutorialspoint.com/swingexamples/show_error_message_dialog.htm
// IMAGE ORIGIN: picsart studios built-in stickers.
// https://stackoverflow.com/questions/2939617/how-to-merge-joptionpane-and-frame-into-one
//https://stackoverflow.com/questions/13840048/swing-gui-output-does-not-show
//https://docs.oracle.com/javase/tutorial/uiswing/events/windowlistener.html