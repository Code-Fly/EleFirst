package com.elefirst.base.utils;

import com.elefirst.base.entity.Page;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/12/1.
 */
public class ExportUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExportUtil.class);

    public static List doImport(HSSFWorkbook wb, String sheetName, Class cls) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        List list = new ArrayList<>();
        HSSFSheet sheet = wb.getSheet(sheetName);
        // 循环行Row
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            HSSFRow hssfRow = sheet.getRow(rowNum);
            if (hssfRow != null) {
//                JSONObject obj = new JSONObject();

                Object obj = cls.newInstance();

                Field[] f = getBeanFields(cls, new Field[]{});

                for (int i = 0; i < f.length; i++) {
                    String key = f[i].getName();
                    Object value = format(f[i], getValue(hssfRow.getCell(i)));

                    f[i].setAccessible(true);
                    f[i].set(obj, value);
//                    obj.put(key, value);
                }

                list.add(obj);
            }
        }

        return list;
    }

    public static HSSFWorkbook doExport(String sheetName, List list, Class cls) throws IllegalAccessException {
        HSSFWorkbook wb = new HSSFWorkbook();
        // 声明一个单子并命名
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 给单子名称一个长度
        sheet.setDefaultColumnWidth(15);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        // 创建第一行（也可以称为表头）
        HSSFRow row = sheet.createRow(0);
        // 样式字体居中
        style.setAlignment(HorizontalAlignment.CENTER);
        // 给表头第一行一次创建单元格

        Field[] f = getBeanFields(cls, new Field[]{});

        for (int i = 0; i < f.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellType(getCellType(f[i]));
            cell.setCellValue(f[i].getName());
            cell.setCellStyle(style);
        }


        // 向单元格里填充数据
        for (int i = 0; i < list.size(); i++) {
            JSONObject obj = JSONObject.fromObject(new Gson().toJson(list.get(i)));

            row = sheet.createRow(i + 1);
            for (int j = 0; j < f.length; j++) {
                String key = f[j].getName();
                if (obj.containsKey(key)) {
                    String value = obj.getString(key);
                    row.createCell(j).setCellValue(value);
                }
            }
        }
        return wb;
    }

    public static XSSFWorkbook doExport(File tplFile, Map<String, String> blankFieldMap, int dataBeginIndex,
                                        List<List<String>> rowList) throws IOException {
        XSSFWorkbook wb = null;
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(tplFile));
        wb = new XSSFWorkbook(inputStream);
        int sheetNumbers = wb.getNumberOfSheets();
        Set<Map.Entry<String, String>> set = blankFieldMap.entrySet();
        for (int sheetIndex = 0; sheetIndex < sheetNumbers; sheetIndex++) {
            // System.out.println("sheetIndex = " + sheetIndex);
            XSSFSheet st = wb.getSheetAt(sheetIndex);
            // int sheetMergeCount = st.getNumMergedRegions();
            // System.out.println("sheetMergeCount = " + sheetMergeCount);
            int rowIndex = 0;
            int lastCellNum = 0;
            for (; rowIndex <= st.getLastRowNum(); rowIndex++) {
                logger.info("rowIndex = " + rowIndex);
                XSSFRow row = st.getRow(rowIndex);
//                lastCellNum = row.getLastCellNum();
//                logger.info("lastCellNum = " + lastCellNum);
                if (rowIndex < dataBeginIndex - 1) {
                    for (Cell c : row) {
                        String value = getValue(c);
                        // System.out.println("cell value = " + value);
                        for (Map.Entry<String, String> oneEntry : set) {
                            String key = oneEntry.getKey();
                            if (value.trim().equals(key)) {
                                // 替换单元格的值
                                c.setCellType(XSSFCell.CELL_TYPE_STRING);
                                c.setCellValue(oneEntry.getValue());
                            }
                        }
                    }
                }
            }
            // 创建行数据
            int dataIndex = dataBeginIndex - 1;
            for (List<String> oneRowRecord : rowList) {
                XSSFRow row = st.createRow(dataIndex);
                for (int j = 0; j < oneRowRecord.size(); j++) {
                    // Cell c = row.createCell(j);
                    // String value = getCellValue(c);
                    // System.out.println("cell value = " + value);
                    // 替换单元格的值
                    // c.setCellType(XSSFCell.CELL_TYPE_STRING);
                    // c.setCellValue(oneRowRecord.get(j));
                    createCell(wb, row, (short) j, CellStyle.ALIGN_RIGHT, CellStyle.VERTICAL_BOTTOM,
                            oneRowRecord.get(j), false, false, false, false);
                }
                dataIndex++;
            }
        }

        return wb;
    }

    public static Object format(Field field, String data) {
        if (null == data) {
            return null;
        } else if (field.getType() == Integer.class) {
            return Integer.valueOf(data);
        } else if (field.getType() == Double.class) {
            return Double.valueOf(data);
        } else if (field.getType() == Long.class) {
            return Long.valueOf(data);
        } else if (field.getType() == Float.class) {
            return Float.valueOf(data);
        } else if (field.getType() == Short.class) {
            return Short.valueOf(data);
        } else if (field.getType() == Boolean.class) {
            return Boolean.valueOf(data);
        } else {
            return String.valueOf(data);
        }
    }

    public static CellType getCellType(Field field) {
        if (field.getType() == Integer.class) {
            return CellType.NUMERIC;
        } else if (field.getType() == Double.class) {
            return CellType.NUMERIC;
        } else if (field.getType() == Long.class) {
            return CellType.NUMERIC;
        } else if (field.getType() == Float.class) {
            return CellType.NUMERIC;
        } else if (field.getType() == Short.class) {
            return CellType.NUMERIC;
        } else if (field.getType() == Boolean.class) {
            return CellType.BOOLEAN;
        } else {
            return CellType.STRING;
        }
    }

    public static String getValue(Cell cell) {
        if (null != cell) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: // 数字
//                    return String.valueOf(cell.getNumericCellValue());
                    if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                        return com.elefirst.base.utils.DateUtil.getDate(cell.getDateCellValue());
                    } else {
                        DecimalFormat df = new DecimalFormat("#,##0.00");
                        // DecimalFormat df = new DecimalFormat("#");
                        return df.format(cell.getNumericCellValue());
                    }
                case Cell.CELL_TYPE_STRING: // 字符串
                    return String.valueOf(cell.getStringCellValue());
                case Cell.CELL_TYPE_BOOLEAN: // Boolean
                    return String.valueOf(cell.getBooleanCellValue());
                case Cell.CELL_TYPE_FORMULA: // 公式
                    return String.valueOf(cell.getCellFormula());
                case Cell.CELL_TYPE_BLANK: // 空值
                    return String.valueOf(" ");
                case Cell.CELL_TYPE_ERROR: // 故障
                    return String.valueOf(" ");
                default:
                    return String.valueOf("未知类型   ");
            }
        } else {
            return null;
        }

    }

    public static Field[] getBeanFields(Class cls, Field[] fs) {
        fs = (Field[]) ArrayUtils.addAll(fs, cls.getDeclaredFields());
        if (cls.getSuperclass() != null && cls.getSuperclass() != Page.class) {
            Class clsSup = cls.getSuperclass();
            fs = getBeanFields(clsSup, fs);
        }
        return fs;
    }

    /**
     * Creates a cell and aligns it a certain way.
     *
     * @param wb     the workbook
     * @param row    the row to create the cell in
     * @param column the column number to create the cell in
     * @param halign the horizontal alignment for the cell.
     */
    public static void createCell(Workbook wb, Row row, short column, short halign, short valign, String textValue,
                                  boolean isHeader, boolean isBold, boolean errorFlag, boolean wrapFlag) {
        // Create a new font and alter it.
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("宋体");
        font.setBold(isBold);

        Cell cell = row.createCell(column);
        cell.setCellValue(textValue);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cellStyle.setFont(font);
        cellStyle.setWrapText(wrapFlag);
        if (isHeader) {
            cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }
        if (errorFlag) {
            cellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLUE.getIndex());
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(cellStyle);
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // readExcel("/Users/ptero/Documents/test.xlsx");
        Map<String, String> blankFieldMap = new HashMap<>();
        blankFieldMap.put("data1", "84.68");
        blankFieldMap.put("data2", "2017/7/24 11:30");
        blankFieldMap.put("data3", "13.02");
        blankFieldMap.put("data4", "2017/7/24 7:15");
        blankFieldMap.put("data5", "0");
        blankFieldMap.put("data6", "2017/7/24");
        blankFieldMap.put("data7", "老楼总表电源监测点日负荷监测表");
        // 5行数据
        List<List<String>> rowList = new ArrayList<>(5);
        // 开始组织每行数据,
        List<String> oneRow = null;
        for (int i = 0; i < 5; i++) {
            oneRow = new ArrayList<>();
            rowList.add(oneRow);
            oneRow.add(0, "0:00");
            oneRow.add(1, "23.55");
            oneRow.add(2, "6.58");
            oneRow.add(3, "12.72");
            oneRow.add(4, "4.23");
            oneRow.add(5, "6.94");
            oneRow.add(6, "");
            oneRow.add(7, "28.38");
            oneRow.add(8, "54.48");
            oneRow.add(9, "18");
            oneRow.add(10, "33.62");
            oneRow.add(11, "");
        }

        File tplFile = new File("/Users/barrie/Documents/Workspaces/MyEclipse/POI/test.xlsx");
        File outFile = new File("/Users/barrie/Documents/Workspaces/MyEclipse/POI/test2.xlsx");

        // 从第9行开始为正式数据,之前为表头
        Workbook wb = doExport(tplFile, blankFieldMap, 9, rowList);

        if (outFile.exists()) {
            outFile.delete();
        }
        // Write the output to a file
        BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(outFile));
        wb.write(fileOut);
        fileOut.close();
        wb.close();
    }
}
