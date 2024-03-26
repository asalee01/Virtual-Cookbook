package ui;

import javax.swing.*;

import model.*;

import java.util.List;

public class CHAT {

    public static void main(String[] args) {
        CHAT gui = new CHAT();
        Recipe recipe = gui.makeRecipe();
        if (recipe != null) {
            System.out.println("Recipe created:");
            System.out.println(recipe);
        } else {
            System.out.println("Recipe creation canceled or closed.");
        }
    }

    public Recipe makeRecipe() {
        JTextField nameField = new JTextField(20);
        JTextField ingredientsField = new JTextField(20);
        JTextField instructionsField = new JTextField(20);
        JTextField prepTimeField = new JTextField(20);
        JTextField cookingTimeField = new JTextField(20);
        JTextField caloriesField = new JTextField(20);
        JTextField descriptionField = new JTextField(20);

        JPanel panel = new JPanel();
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

        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Recipe Details",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String combinedIngredients = ingredientsField.getText();
            String[] ingredients = combinedIngredients.split(",");
            String combinedInstructions = ingredientsField.getText();
            String[] instructions = combinedInstructions.split(",");
            int prepTime = parseInteger(prepTimeField.getText());
            int cookingTime = parseInteger(cookingTimeField.getText());
            int calories = parseInteger(caloriesField.getText());
            String description = descriptionField.getText();

            return new Recipe(name, List.of(ingredients), List.of(instructions), prepTime,
                    cookingTime, calories, description);
        }

        // If canceled or closed, return null
        return null;
    }

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid input for time. Please enter a valid integer value.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return -1; // Return a default value indicating an error
        }
    }
}
