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

    private JFrame addRecipeFrame;


    public GraphicalUserInterface() {
        listRec = new RecipeList();
        createButtons();
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
        cookbookFrame.setLocation(300, 300);
        cookbookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cookbookFrame.setTitle("Virtual Cookbook");
        cookbookFrame.setVisible(true);
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
        allActionButtons();
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
        JButton submitButton = new JButton("Submit");
        panel.add(submitButton, BorderLayout.SOUTH);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Recipe recipe = parseRecipe();
                if (recipe != null) {
                    listRec.addRecipes(recipe);
                }
            }
        });
    }

    public void createTextFields() {
        nameField = new JTextField(20);
        ingredientsField = new JTextField();
        instructionsField = new JTextField();
        prepTimeField = new JTextField(10);
        cookingTimeField = new JTextField(10);
        caloriesField = new JTextField(10);
        descriptionField = new JTextField(20);
    }

    private void createAddRecipePanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(new JLabel("Enter name of recipe:"));
        panel.add(nameField);
        panel.add(new JLabel("Enter ingredients of recipe:"));
        panel.add(ingredientsField);
        panel.add(new JLabel("Enter instructions of recipe:"));
        panel.add(instructionsField);
        panel.add(new JLabel("Enter prep time (minutes) of recipe:"));
        panel.add(prepTimeField);
        panel.add(new JLabel("Enter cooking time (minutes) of recipe:"));
        panel.add(cookingTimeField);
        panel.add(new JLabel("Enter calories of recipe:"));
        panel.add(caloriesField);
        panel.add(new JLabel("Enter description of recipe:"));
        panel.add(descriptionField);
        panel.setVisible(true);

        cookbookFrame.add(panel, BorderLayout.WEST);
    }

    private Recipe parseRecipe() {
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
        return new Recipe(name, List.of(ingredients), List.of(instructions),
                prepTime, cookingTime, calories, description);
    }

    private void modifyRecipeAction() {
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //modifyRecipe();
            }
        });
    }

    private void removeButtonAction() {
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //removeRecipe();
            }
        });
    }

    private void viewAllAction() {
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doView();
            }
        });
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
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}




