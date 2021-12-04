package ca.gbc.recipeproject.services;

import ca.gbc.recipeproject.model.Ingredient;

import java.io.ByteArrayInputStream;
import java.util.Set;

public interface ExcelFileService {

    ByteArrayInputStream export(Set<Ingredient> shoppingList);
}
