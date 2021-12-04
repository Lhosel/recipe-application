package ca.gbc.recipeproject.services.springdatajpa;

import ca.gbc.recipeproject.model.Ingredient;
import ca.gbc.recipeproject.services.ExcelFileService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

@Service
public class ExcelFileServiceImpl implements ExcelFileService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public ByteArrayInputStream export(Set<Ingredient> shoppingList) {
        try(Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Shopping List");
            Row row = sheet.createRow(0);

            //Define header cell style
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Creating header cells
            Cell cell = row.createCell(0);
            cell.setCellValue("Amount");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Ingredient Name");
            cell.setCellStyle(headerCellStyle);

            // Creating data rows for each contact

            Integer count = 0;
            for (Ingredient ingredient : shoppingList) {
                count++;
                Row dataRow = sheet.createRow(count);
                dataRow.createCell(0).setCellValue(ingredient.getAmount());
                dataRow.createCell(1).setCellValue(ingredient.getIngredientName());
            }

            // Making size of column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }catch (IOException ex) {
            logger.error("Error during export Excel file", ex);
            return null;
        }
    }
}