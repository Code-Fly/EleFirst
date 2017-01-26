package com.elefirst.base.utils;

import com.elefirst.base.entity.Page;
import com.google.gson.Gson;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
public class ExportUtils {
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

    public static String getValue(HSSFCell cell) {
        if (null != cell) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    return String.valueOf(cell.getNumericCellValue());
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    return String.valueOf(cell.getStringCellValue());
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    return String.valueOf(cell.getBooleanCellValue());
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    return String.valueOf(cell.getCellFormula());
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    return String.valueOf(" ");
                case HSSFCell.CELL_TYPE_ERROR: // 故障
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
}
