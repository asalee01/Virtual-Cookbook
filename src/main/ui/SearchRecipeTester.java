package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.*;

public class SearchRecipeTester extends JFrame {

    private JTextField ingredientsField;
    private JTextField cookingTimeField;
    private JButton searchButton;
    private JTextArea recipeTextArea;
    RecipeList listRec;

    public SearchRecipeTester() {
        listRec = new RecipeList();
        setTitle("Recipe Search");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JPanel ingredientsPanel = new JPanel();
        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsField = new JTextField(20);
        ingredientsPanel.add(ingredientsLabel);
        ingredientsPanel.add(ingredientsField);
        JPanel cookingTimePanel = new JPanel();
        JLabel cookingTimeLabel = new JLabel("Cooking Time:");
        cookingTimeField = new JTextField(10);
        cookingTimePanel.add(cookingTimeLabel);
        cookingTimePanel.add(cookingTimeField);
        searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchRecipes());

        recipeTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(recipeTextArea);

        add(ingredientsPanel);
        add(cookingTimePanel);
        add(searchButton);
        add(scrollPane);
    }

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

    public List<Recipe> selectRecipe() {
        List<Recipe> selectedRecs = new ArrayList<>();
        String selection = "";

        // Determine selection based on user input
        if (ingredientsField.getText().isEmpty() && cookingTimeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter ingredients or cooking time.");
            return selectedRecs; // Return empty list if no input provided
        } else if (!ingredientsField.getText().isEmpty() && !cookingTimeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter either ingredients or cooking time, not both.");
            return selectedRecs; // Return empty list if both fields are filled
        } else if (!ingredientsField.getText().isEmpty()) {
            selectedRecs = selectRecipeHelper("i");
        } else if (!cookingTimeField.getText().isEmpty()) {
            selectedRecs = selectRecipeHelper("t");
        }

        return selectedRecs;
    }

    private List<Recipe> selectRecipeHelper(String selection) {
        if (selection.equals("i")) {
            List<String> ingredients = Arrays.asList(ingredientsField.getText().split(","));
            return listRec.searchByIngredients(ingredients);
        } else if (selection.equals("t")) {
            try {
                int time = Integer.parseInt(cookingTimeField.getText());
                return listRec.searchByCookingTime(time);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for cooking time.");
                return new ArrayList<>(); // Return empty list if cooking time is not a number
            }
        } else {
            return new ArrayList<>(); // Return empty list for any other invalid selection
        }
    }


    // You need to implement the Recipe and selectRecipe method according to your requirements

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SearchRecipeTester();
            }
        });
    }
}
