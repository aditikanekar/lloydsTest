package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class FileReader {
    private static final String filename = "src/test/resources/dataSheet/ZopaData.xlsx";

    public static Cell getDataFromFile(String scenarioName,String columnName) {
        Cell value = null;
        try {
            FileInputStream excelFile = new FileInputStream(new File(filename));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            Map<String, Integer> map = new HashMap<>();
            Row nextRow = iterator.next();
            System.out.println("Current row is>>>>>>>>>>>>>>"+nextRow.cellIterator());
            Iterator<Cell> cellIterator = nextRow.iterator();
            value = null;
            int totalRows = sheet.getPhysicalNumberOfRows();
            int rowNumber=0;
            int columnNumber=0;
//get row number w.r.t scenario name
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            if (cell.getRichStringCellValue().getString().trim().equals(scenarioName)) {
                                System.out.println(">>>>>>row no is>>>>>>"+ row.getRowNum());
                                rowNumber=row.getRowNum();

                            }
                        }
                    }
                }
           Row row = sheet.getRow(0);

            //get column value w.r.t row number
            for (int i = 0; i < row.getLastCellNum(); i++) {
                 System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim()
                        .equals(columnName)) {
                    columnNumber = i;
                    System.out.println(">>>>>>column no is>>>>>>" + columnNumber);
                }
            }
            row = sheet.getRow(rowNumber);
//            if (row == null)
//                return "";
            Cell cell = row.getCell(columnNumber);
            System.out.println(">>>>>>final value is>>>>>>" + cell.getStringCellValue());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
    public static Cell getDataFromFile(String columnName) {
        Cell value = null;
        try {
            FileInputStream excelFile = new FileInputStream(new File(filename));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            Map<String, Integer> map = new HashMap<>();
            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            value = null;
            int totalRows = sheet.getPhysicalNumberOfRows();
            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();

                if (currentCell.getStringCellValue().equalsIgnoreCase(columnName)) {
                    map.put(currentCell.getStringCellValue(), currentCell.getColumnIndex());//add the cell contents (name of column) and cell index to the map
                    Row dataRow = sheet.getRow(totalRows - 1);
                    value = dataRow.getCell(map.get(columnName));
                    System.out.println(">>>>>>>>>"+value);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
    public static void main(String[] args) {
        getDataFromFile("scenario2","email");
       /* getDataFromFile("postcode");
        getDataFromFile("firstName");
        getDataFromFile("lastName");*/

    }
}




