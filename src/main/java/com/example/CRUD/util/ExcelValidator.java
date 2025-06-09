package com.example.CRUD.util;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ExcelValidator {
    public  boolean validateSheetName(Workbook workbook, List<String> sheetNames){
        if(workbook.getNumberOfSheets() < sheetNames.size()){
            return false;
        }
        for(int i = 0; i < sheetNames.size(); i++){
            String actualSheetName = workbook.getSheetName(i);
            if(!actualSheetName.equalsIgnoreCase(sheetNames.get(i))){
                return false;
            }
        }
        return true;
    }

    public boolean validateColumnNames(Sheet sheet, List<String> columnNames){
        Row headerRow = sheet.getRow(0);
        if(headerRow == null || headerRow.getPhysicalNumberOfCells() < columnNames.size()){
            return false;
        }
        for(int i = 0; i < columnNames.size(); i++){
            Cell cell = headerRow.getCell(i+1);
            if(cell == null ){
                return false;
            }
            String actualColumnName = cell.getStringCellValue();
            if(!actualColumnName.equalsIgnoreCase(columnNames.get(i))){
                return false;
            }
        }
        return true;
    }
}
