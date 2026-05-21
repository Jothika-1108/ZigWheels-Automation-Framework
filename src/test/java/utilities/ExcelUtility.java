package utilities;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    static XSSFWorkbook workbook = new XSSFWorkbook();

    static Sheet bikeSheet = workbook.createSheet("Honda Bikes");
    static Sheet carSheet = workbook.createSheet("Used Cars Chennai");

    static int bikeRow = 0;
    static int carRow = 0;

    // ================== BIKE ==================

    public static void createBikeHeader() {

        Row row = bikeSheet.createRow(bikeRow++);

        row.createCell(0).setCellValue("Bike Name");
        row.createCell(1).setCellValue("Price");
        row.createCell(2).setCellValue("Launch Date");
    }

    public static void writeBikeData(String name, String price, String launch) {

        Row row = bikeSheet.createRow(bikeRow++);

        row.createCell(0).setCellValue(name);
        row.createCell(1).setCellValue(price);
        row.createCell(2).setCellValue(launch);
    }

    // ================== CARS ==================

    public static void createCarHeader() {

        Row row = carSheet.createRow(carRow++);

        row.createCell(0).setCellValue("Popular Car Models (Chennai)");
    }

    public static void writeCarData(String model) {

        Row row = carSheet.createRow(carRow++);

        row.createCell(0).setCellValue(model);
    }

    // ================== SAVE ==================

    public static void saveExcel() {

        try {

            FileOutputStream file = new FileOutputStream(
                    System.getProperty("user.dir") + "/testData/FinalOutput.xlsx");

            workbook.write(file);
            file.close();   // ✅ correct

            System.out.println("✅ Excel file created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}