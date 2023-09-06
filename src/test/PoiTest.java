package test;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class PoiTest {

    @Test
    public void test() {

        // 文件路径可以根据自己需求来 我的是放在本地根路径下了
        File file = new File("C:\\Users\\issuser\\Desktop\\Test.xlsx");
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(file);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = wb.getSheetAt(0);
        List<POIXMLDocumentPart> list = sheet.getRelations();
        for (POIXMLDocumentPart part : list) {
            if (part instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) part;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture picture = (XSSFPicture) shape;
                    PictureData pic = picture.getPictureData();
                    XSSFClientAnchor anchor = picture.getPreferredSize();
                    CTMarker marker = anchor.getFrom();
                    // 获取图片格式
                    String ext = pic.suggestFileExtension();
                    System.out.println("行号[{}],单元格[{}],图片格式[{}]" + marker.getRow() + "---" + marker.getCol() + "---" + ext);
                }
            }
        }
    }

    @Test
    public void test1() {
        File file = new File("C:\\Users\\issuser\\Desktop\\Test.xlsx");
        XSSFWorkbook book = null;
        try {
            book = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        System.out.println(book.getNumberOfSheets());
        System.out.println(book.getSheetAt(0).getLastRowNum());
        System.out.println(book.getSheetAt(0).getRow(0).getLastCellNum());
        System.out.println(book.getSheetAt(0).getRow(0).getCell(13));
    }

    @Test
    public void test2() {
        File file = new File("C:\\Users\\issuser\\Desktop\\Test.xlsx");
        XSSFWorkbook book = null;
        try {
            book = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        List<XSSFPictureData> pict = book.getAllPictures();
        FileOutputStream fos = null;
        Iterator it = pict.iterator();
        while (it.hasNext()) {
            XSSFPictureData data = (XSSFPictureData) it.next();
            // try {
            // fos = new FileOutputStream();
            // } catch (FileNotFoundException e) {
            //     throw new RuntimeException(e);
            // }

            // System.out.println(packagePart);
        }
    }

    @Test
    public void test03() {
        // 文件路径可以根据自己需求来 我的是放在本地根路径下了
        File file = new File("C:\\Users\\issuser\\Desktop\\Test.xlsx");
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(file);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        DataFormatter formatter = new DataFormatter();
        Sheet sheet1 = wb.getSheetAt(0);
        for (Row row : sheet1) {
            for (Cell cell : row) {
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                System.out.print(cellRef.formatAsString());
                System.out.print(" - ");
                // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
                String text = formatter.formatCellValue(cell);
                System.out.println(text);
                // Alternatively, get the value and format it yourself
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.println(cell.getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            System.out.println(cell.getDateCellValue());
                        } else {
                            System.out.println(cell.getNumericCellValue());
                        }
                        break;
                    case BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        System.out.println(cell.getCellFormula());
                        break;
                    case BLANK:
                        System.out.println();
                        break;
                    default:
                        System.out.println();
                }
            }
        }
    }

    @Test
    public void test05() {
        String str = "abc,123,2131,24,";
        String[] split = str.split(",", 100);
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
        System.out.println(split.length);
    }

    @Test
    public void test43() {
        System.out.println(numWays(70));
    }
    public int numWays(int n) {
        int sum = 0;
        Map<Integer, Integer> tempMap = new HashMap<>();
        for (int i = 0; i <= n/2; i++) {
            tempMap.put(i, n - 2 * i);
        };
        for (Map.Entry<Integer, Integer> map : tempMap.entrySet()) {
            if (map.getKey() == 0 || map.getValue() == 0) {
                sum += 1;
            } else {
                double temp_top = 1;
                double temp_bottom = 1;
                for(int i = map.getKey(); i >= 1; i--) {
                    temp_top *= (map.getKey() + map.getValue() - map.getKey() + i);
                    temp_bottom *= i;
                }
                sum += temp_top / temp_bottom;
            }
        }
        return sum;
    }
}
