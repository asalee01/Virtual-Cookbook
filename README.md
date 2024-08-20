
# **Virtual Cookbook**

A simple cookbook with your recipes filtered for your specific needs. Allows user to add, remove and filter recipe for
requirements.

## Features:

- Allows users to: add recipes, remove recipes, add prep time, add cooking time.
- Includes a feature where users can enter a condition and provides all recipes that fulfill the condition
- Allows user to view **ALL** recipes in the cookbook.
- (Possible Idea): Rate your recipes to help you remember their taste or the cooking process.
- Update recipes: makes changes to an already existing recipe.


## Why this project?
Cooking is one of the most important daily tasks that a lot of people skip due to the great amount of time required to 
cook. As a university student who orders food way too much, I learned that if I make a cookbook with features to filter
recipes depending on the ingredients I currently have or filters recipes depending on the amount of time I can allocate 
to cook.

## Target Audience
People who have a passion for cooking and students who are abroad and require help in cooking. 


## User Stories:
- As a user, I would want to add a recipe.
- As a user, I would want to remove a recipe.
- As a user, I would want to modify the recipe.
- As a user, I would want to search recipes with a certain cooking time.
- As a user, I would want to search recipes that I can cook with the entered ingredients.
- As a user, I would want to view all my recipes.
- As a user, I want to save all the recipes I added and modified.
- As a user, I want to be able to add recipes alongside the ones I already have saved.


## GUI using guidelines:
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking on the 
  Add Recipe button on the choices panel, this will then prompt you enter specific types of text to each of the input
  boxes. Once you feel like your recipe has been successfully added, you can click submit, and it will have been added 
  to a Y (RecipeList).
- You can generate the second required action related to the user story "removing multiple Xs from a Y" by clicking on 
  the Remove Recipe button on the choices panel, this will ask you enter the name of the recipe that needs to be removed
  once you enter that it will be removed.
- You can locate my visual component by looking at the top left of the initial panel, and you would see a logo of my
  cookbook.
- You can save my state by just clicking on the save button in the choices panel.
- You can load my state by just clicking on the load button in the choices panel.
- You can view all the recipes in my cookbook currently by clicking the Show All button.
- You can search for recipes by ingredients and time by clicking the search button. You can ONLY type in one of the 
  JTextFields, and then it will show you all the recipes in the cookbook that meet your requirement.
- You can modify recipes by clicking the modify button and typing the recipe you want to change. Then you can modify 
  that recipe to your choose and update the cookbook.

## Phase 4 Task 2:
- Wed Apr 03 12:59:45 PDT 2024
- Recipe has been added!
- Wed Apr 03 12:59:45 PDT 2024
- Recipe has been added!
- Wed Apr 03 12:59:45 PDT 2024
- Extracted all recipes in cookbook.
- Wed Apr 03 12:59:45 PDT 2024
- Extracted all recipes in cookbook.
- Wed Apr 03 13:00:04 PDT 2024
- Recipe has been added!
- Wed Apr 03 13:00:12 PDT 2024
- Extracted all recipes in cookbook.
- Wed Apr 03 13:00:28 PDT 2024
- A recipe has been modified.
- Wed Apr 03 13:00:44 PDT 2024
- Searched for recipes by ingredients.
- Wed Apr 03 13:00:51 PDT 2024
- Searched for recipes by cooking time.

## Phase 4 Task 3:
- In my GraphicalUserInterface class in UI, I noticed that I have a lot of duplicated code that could cause high
  coupling between my methods in the GUI. If I had more time, I would have implemented one general method with areas 
  with "gaps" that can be modified for every instance being used. Also, I noticed that my methods in both the GUI and
  console-based UI has very large method bodies that could make it very hard for the reader to understand and possibly
  debug my code, I would decrease the size of the methods to ensure each method has only one function and add more 
  detailed comments about the use of every method. Also, I believe that my GUI class can be separated into many 
  subclasses, i.e. a class for creating the frame and adding specific panels with their methods, this would help me 
  implement the single-responsibility principle we learned in previous classes. Also, I could maybe use less global
  fields, although my current implementation requires these fields to be global, I believe there is a way to access
  these fields by improving my implementation, i.e. making it more concise to fit everything into 1 method. 
- In short, to improve this project I should create more methods with more readable code and ensure that the design
  patterns we learned are somewhat implemented.