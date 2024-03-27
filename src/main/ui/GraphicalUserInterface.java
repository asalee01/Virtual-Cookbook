package ui;

import model.Recipe;
import model.RecipeList;
import persistence.JSonReader;
import persistence.JSonWriter;
import ui.VirtualCookbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GraphicalUserInterface {
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

    private JOptionPane outputs;


    public GraphicalUserInterface() {
        outputs = new JOptionPane();
        listRec = new RecipeList();
        createButtons();
        allActionButtons();
        createButtonPanel();
        createOutputPanel();
        createFrame();
        jsonWriter = new JSonWriter(JSON_STORE);
        jsonReader = new JSonReader(JSON_STORE);
    }

    private void createButtons() {
        addButton = new JButton("Add recipe");
        removeButton = new JButton("Remove recipe");
        showAllButton = new JButton("Show All");
        modifyButton = new JButton("Modify recipe");
        searchButton = new JButton("Search recipes");
        loadButton = new JButton("Load recipes");
        saveButton = new JButton("Save recipes");
    }

    private JPanel createOutputPanel() {
        outputPanel = new JPanel(new GridBagLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Results"));
        return outputPanel;
    }

    public JFrame createFrame() {
        cookbookFrame = new JFrame();
        cookbookFrame.add(cookbookPanel, BorderLayout.LINE_START);
        cookbookFrame.add(outputPanel, BorderLayout.CENTER);
        cookbookFrame.setSize(750, 750);
        cookbookFrame.setLocation(300, 100);
        cookbookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cookbookFrame.setTitle("Virtual Cookbook");
        cookbookFrame.setVisible(true);
        cookbookFrame.setResizable(true);
        return cookbookFrame;
    }

    public JPanel createButtonPanel() {
        cookbookPanel = new JPanel();
        cookbookPanel.setBorder(BorderFactory.createTitledBorder("Choices"));
        Box buttons = Box.createVerticalBox();
        buttons.add(addButton);
        buttons.add(removeButton);
        buttons.add(modifyButton);
        buttons.add(searchButton);
        buttons.add(showAllButton);
        buttons.add(loadButton);
        buttons.add(saveButton);
        cookbookPanel.add(buttons);
        return cookbookPanel;
    }

    private void allActionButtons() {
        addButtonAction();
        removeButtonAction();
        modifyRecipeAction();
        viewAllAction();
        searchButtonAction();
        loadButtonAction();
        saveButtonAction();
    }

    private void addButtonAction() {
        addButton.addActionListener(e -> addRecipe());
    }

    public void addRecipe() {
        createTextFields();
        createAddRecipePanel();
        panel.validate();
        panel.revalidate();
        panel.repaint();


        JButton submitButton = new JButton("Submit");
        panel.add(submitButton, BorderLayout.SOUTH);
        submitButton.addActionListener(e -> parseRecipe());
        System.out.println("Adding recipe");
    }

    public void createTextFields() {
        nameField = new JTextField();
        ingredientsField = new JTextField();
        instructionsField = new JTextField();
        prepTimeField = new JTextField();
        cookingTimeField = new JTextField();
        caloriesField = new JTextField();
        descriptionField = new JTextField();
    }

    private void createAddRecipePanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new JLabel("Enter name of recipe:"));
        nameField.setPreferredSize(new Dimension(1,1));
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

        cookbookFrame.add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
    }


    private void parseRecipe() {
        System.out.println("Test");
        String name = nameField.getText();
        String[] ingredients = ingredientsField.getText().split(",");
        String[] instructions = instructionsField.getText().split(",");
        int prepTime = 0;
        if (!prepTimeField.getText().equals("")) {
            prepTime = Integer.valueOf(prepTimeField.getText());
        }
        int cookingTime = 0;
        if (!cookingTimeField.getText().equals("")) {
            cookingTime = Integer.valueOf(cookingTimeField.getText());
        }
        int calories = 0;
        if (!caloriesField.getText().equals("")) {
            calories = Integer.valueOf(caloriesField.getText());
        }
        String description = descriptionField.getText();
        Recipe test = new Recipe(name, List.of(ingredients), List.of(instructions),
                prepTime, cookingTime, calories, description);
        listRec.addRecipes(test);

        cookbookFrame.remove(panel);
        outputs.createDialog("Recipe has been added!");
        cookbookFrame.getContentPane().add(outputs);
    }

    private void modifyRecipeAction() {
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //modifyRecipe();
            }
        });
    }

//    private void modifyRecipe() {
//        System.out.println("Select which recipe you would like to change.");
//        doView();
//        System.out.println("\nEnter the name of the recipe you want to change");
//        String name = input.next();
//        System.out.println("\nTo modify please enter the name of the new recipe.");
//        listRec.modifyRecipe(name, makeRecipe());
//        System.out.println("Modifications have been made to the list of recipes!");
//    }


    private void removeButtonAction() {
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doView();
                createRemoveRecipePanel();
                removeRecipePanel.validate();
                removeRecipePanel.revalidate();
                removeRecipePanel.repaint();
                JButton submitButton = new JButton("Submit");

                removeRecipe();
                removeRecipePanel.add(submitButton, BorderLayout.SOUTH);
                submitButton.addActionListener(e1 -> removeRecipe());
            }
        });
    }


    public void removeRecipe() {
        String name = nameFieldRemove.getText();
        listRec.removeRecipeByName(name);
    }

    private void createRemoveRecipePanel() {
        nameFieldRemove = new JTextField(20);
        removeRecipePanel = new JPanel();
        removeRecipePanel.setLayout(new BoxLayout(removeRecipePanel, BoxLayout.PAGE_AXIS));
        removeRecipePanel.add(new JLabel("Enter name of recipe:"));
        removeRecipePanel.add(nameFieldRemove);

        cookbookFrame.add(removeRecipePanel, BorderLayout.CENTER);
        cookbookFrame.setVisible(true);
    }

    private void viewAllAction() {
        showAllButton.addActionListener(e -> doView());
    }

    private void doView() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        for (Recipe r : listRec.getAllRecipes()) {
            JLabel recipesNames = new JLabel(r.getName() + "\n");
            this.outputPanel.add(recipesNames, gbc);
        }
        this.outputPanel.revalidate();
        this.outputPanel.validate();
    }

    private void searchButtonAction() {
       // searchButton.addActionListener(e3 -> searchRecipes());
    }

    private void saveButtonAction() {
        saveButton.addActionListener(e2 -> saveRecipes());
    }

    public void saveRecipes() {
        try {
            jsonWriter.open();
            jsonWriter.write(listRec);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadButtonAction() {
        loadButton.addActionListener(e -> loadRecipes());
    }

    public void loadRecipes() {
        try {
            listRec = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}




