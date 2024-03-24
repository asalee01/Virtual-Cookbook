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
import java.io.IOException;
import java.util.Arrays;
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
    private JFrame cookbookFrame = new JFrame();
    private JPanel cookbookPanel = new JPanel();
    private JTextField recipeTextField;
    private JPanel outputPanel;

    private JTextArea outputTextArea;

    public GraphicalUserInterface() {
        listRec = new RecipeList();
        recipeTextField = new JTextField(20);
        addButton = new JButton("Add recipe");
        removeButton = new JButton("Remove recipe");
        showAllButton = new JButton("Show All");
        modifyButton = new JButton("Modify recipe");
        searchButton = new JButton("Search recipes");
        loadButton = new JButton("Load recipes");
        saveButton = new JButton("Save recipes");
        createOutputPanel();
        createFrame();
        displayMenu();
        jsonWriter = new JSonWriter(JSON_STORE);
        jsonReader = new JSonReader(JSON_STORE);
        outputTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        cookbookFrame.add(cookbookPanel, BorderLayout.NORTH);
        cookbookFrame.add(scrollPane, BorderLayout.CENTER);
        cookbookFrame.pack();
        cookbookFrame.setVisible(true);
    }

    private void createOutputPanel() {
        outputPanel = new JPanel(new GridLayout(15, 6));
    }

    // Method to display output in the text area
    private void displayOutput(String output) {
        outputTextArea.append(output + "\n");
    }

    // Method to clear the output text area
    private void clearOutput() {
        outputTextArea.setText("");
    }


    public static void main(String[] args) {
        new GraphicalUserInterface();
    }


    public void createFrame() {
        cookbookFrame.add(cookbookPanel, BorderLayout.CENTER);
        cookbookFrame.add(outputPanel, BorderLayout.LINE_START);
        cookbookFrame.setSize(500, 500);
        cookbookFrame.setLocation(300, 300);
        cookbookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cookbookFrame.setTitle("Virtual Cookbook");
        //cookbookFrame.pack(); automatically makes it as small as possible.
        cookbookFrame.setVisible(true);
        cookbookFrame.setLayout(new BorderLayout());
        cookbookFrame.add(cookbookPanel, BorderLayout.CENTER);
    }

    public void displayMenu() {
        cookbookPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        cookbookPanel.setLayout(new FlowLayout());
        cookbookPanel.add(addButton);
        //JTextField for inputs of users.
        cookbookPanel.add(removeButton);
        cookbookPanel.add(showAllButton);
        cookbookPanel.add(modifyButton);
        cookbookPanel.add(loadButton);
        cookbookPanel.add(saveButton);
        cookbookPanel.add(searchButton);
        allActionButtons();
    }

    private void allActionButtons() {
        //addButtonAction();
        removeButtonAction();
        //modifyRecipeAction();
        viewAllAction();
        searchButtonAction();
        loadButtonAction();
        saveButtonAction();
    }

//    private void addButtonAction() {
//        addButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Open a dialog for the user to input recipe details
//                JTextField nameField = new JTextField(20);
//                JTextField ingredientsField = new JTextField(20);
//                JTextField instructionsField = new JTextField(20);
//                JTextField prepTimeField = new JTextField(20);
//                JTextField cookingTimeField = new JTextField(20);
//                JTextField caloriesField = new JTextField(20);
//                JTextField descriptionField = new JTextField(20);
//
//                JPanel addRecipePanel = new JPanel();
//                addRecipePanel.setLayout(new GridLayout(0, 1));
//                addRecipePanel.add(new JLabel("Name:"));
//                addRecipePanel.add(nameField);
//                addRecipePanel.add(new JLabel("Ingredients:"));
//                addRecipePanel.add(ingredientsField);
//                addRecipePanel.add(new JLabel("Instructions:"));
//                addRecipePanel.add(instructionsField);
//                addRecipePanel.add(new JLabel("Prep Time (minutes):"));
//                addRecipePanel.add(prepTimeField);
//                addRecipePanel.add(new JLabel("Cooking Time (minutes):"));
//                addRecipePanel.add(cookingTimeField);
//                addRecipePanel.add(new JLabel("Calories:"));
//                addRecipePanel.add(caloriesField);
//                addRecipePanel.add(new JLabel("Description:"));
//                addRecipePanel.add(descriptionField);
//
//                int result = JOptionPane.showConfirmDialog(null, addRecipePanel,
//                        "Please Enter Recipe Details", JOptionPane.OK_CANCEL_OPTION);
//
//                // If the user clicks OK, extract data and add the recipe
//                if (result == JOptionPane.OK_OPTION) {
//                    String name = nameField.getText();
//                    String ingredientsString = ingredientsField.getText();
//                    java.util.List<String> ingredients = Arrays.asList(ingredientsString.split("\\s*,\\s*"));
//                    String instructionsString = instructionsField.getText();
//                    List<String> instructions = Arrays.asList(instructionsString.split("\\s*,\\s*"));
//                    int prepTime = Integer.parseInt(prepTimeField.getText());
//                    int cookingTime = Integer.parseInt(cookingTimeField.getText());
//                    int calories = Integer.parseInt(caloriesField.getText());
//                    String description = descriptionField.getText();
//
//                    // Add the recipe using the provided data
////                    listRec.addRecipes(newRecipe);
//                    displayOutput("Recipe added successfully.");
//                }
//            }
//        });
//    }

    private void removeButtonAction() {
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //removeRecipe();
            }
        });
    }
//    public void addRecipe() {
//        listRec.addRecipes(makeRecipe());
//        System.out.println("Recipe has been added!");
//    }

//    public Recipe makeRecipe() {
//        System.out.println("Enter name of recipe");
//        String name = input.next();
//        System.out.println("Enter ingredients of recipe");
//        String firstIng = input.next();
//        System.out.println("Do you want to add more ingredients? Type y for yes any other input is considered"
//                + " as a no.");
//        List<String> ingredients = checkInput(firstIng);
//        System.out.println("Enter instructions of recipe");
//        String firstInstruct = input.next();
//        System.out.println("Do you want to add more instructions? Type y for yes any other input is considered"
//                + " as a no.");
//        List<String> instructions = checkCookingInput(firstInstruct);
//        System.out.println("Enter prep time (minutes) of recipe");
//        int prepTime = input.nextInt();
//        System.out.println("Enter cooking time (minutes) of recipe :");
//        int cookingTime = input.nextInt();
//        System.out.println("Enter calories of recipe:");
//        int calories = input.nextInt();
//        System.out.println("Enter description of recipe:");
//        String description = input.next();
//        Recipe recipe;
//        return recipe = new Recipe(name, ingredients, instructions, prepTime, cookingTime, calories, description);
//    }


//    private void removeRecipe() {
//        System.out.println("Select which recipe you want to remove.");
//        //doView();
//        //String name = input.next();
//        listRec.removeRecipeByName(name);
//        System.out.println("Changes have been made");
//
//       // JTextField
//    }


//    private void modifyRecipeAction() {
//        modifyButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                modifyRecipe();
//            }
//        });
//    }


    private void viewAllAction() {
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doView();
            }
        });
    }

    private void doView() {
        System.out.println("Here are all your recipes.");
        for (Recipe r : listRec.getAllRecipes()) {
            JLabel recipesNames = new JLabel(r.getName());
            this.outputPanel.add(recipesNames);
        }
        this.outputPanel.revalidate();
        this.outputPanel.validate();
    }

    private void searchButtonAction() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //searchRecipe();
            }
        });
    }

    private void saveButtonAction() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //saveRecipes();
            }
        });
    }

    private void loadButtonAction() {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRecipes();
            }
        });
    }

    public void loadRecipes() {
        try {
            listRec = jsonReader.read();
            System.out.println("Loaded saved recipes");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}




