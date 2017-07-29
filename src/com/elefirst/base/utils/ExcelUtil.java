package com.elefirst.base.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.elefirst.base.utils.ExportUtil.getValue;

/**
 * Created by barrie on 2017/7/30.
 */
public class ExcelUtil {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private File tpl;
    private XSSFWorkbook wb;

    public File getTpl() {
        return tpl;
    }

    public void setTpl(File tpl) {
        this.tpl = tpl;
    }

    public XSSFWorkbook getWb() {
        return wb;
    }

    public void setWb(XSSFWorkbook wb) {
        this.wb = wb;
    }

    public ExcelUtil(File tpl) throws IOException {
        this.tpl = tpl;

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(tpl));
        this.wb = new XSSFWorkbook(inputStream);
    }

    public XSSFWorkbook buildHeader(int dataBeginIndex, int mergeCellLength, String[] days) throws IOException, ParseException {
        int sheetNumbers = this.wb.getNumberOfSheets();
        for (int sheetIndex = 0; sheetIndex < sheetNumbers; sheetIndex++) {
            // System.out.println("sheetIndex = " + sheetIndex);
            XSSFSheet st = this.wb.getSheetAt(sheetIndex);
            // int sheetMergeCount = st.getNumMergedRegions();
            // System.out.println("sheetMergeCount = " + sheetMergeCount);
            int rowIndex = 0;
            int lastCellNum = 0;
            for (; rowIndex <= st.getLastRowNum(); rowIndex++) {
                logger.info("rowIndex = " + rowIndex);
                XSSFRow row = st.getRow(rowIndex);
                lastCellNum = row.getLastCellNum();
                logger.info("lastCellNum = " + lastCellNum);
                if (rowIndex < dataBeginIndex - 1) {
                    // 第3、4行特别处理
                    if (rowIndex == 2) {
                        // 第1、2个cell不处理
                        int initIndex = 2;
                        for (int i = 0; i < days.length + 1; i++) {
                            String title = "";
                            if (i == 0) {
                                title = "总电量";
                            } else {
                                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                                SimpleDateFormat oformat = new SimpleDateFormat("MM-dd");
                                title = oformat.format(format.parse(days[i - 1]));
                            }

                            // 从第3列开始，创建5个单元格，并且合并赋值
                            for (int j = 0; j < mergeCellLength; j++) {
                                createCell(wb, row, (short) (initIndex + j + (mergeCellLength * i)),
                                        CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_BOTTOM, title, false, false, false,
                                        false);
                            }
                            st.addMergedRegion(new CellRangeAddress(2, 2, (initIndex + (mergeCellLength * i)),
                                    (initIndex - 1 + (mergeCellLength * (i + 1)))));

                        }
                    }
                    if (rowIndex == 3) {
                        // 第1、2个cell不处理
                        int initIndex = 2;
                        for (int i = 0; i < days.length + 1; i++) {
                            // 从第3列开始，创建5个单元格，总量，峰平谷尖
                            for (int j = 0; j < mergeCellLength; j++) {
                                switch (j) {
                                    case 0:
                                        createCell(wb, row, (short) (initIndex + j + (mergeCellLength * i)),
                                                CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_BOTTOM, "总", false, false, false,
                                                false);
                                        break;
                                    case 1:
                                        createCell(wb, row, (short) (initIndex + j + (mergeCellLength * i)),
                                                CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_BOTTOM, "峰", false, false, false,
                                                false);
                                        break;
                                    case 2:
                                        createCell(wb, row, (short) (initIndex + j + (mergeCellLength * i)),
                                                CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_BOTTOM, "平", false, false, false,
                                                false);
                                        break;
                                    case 3:
                                        createCell(wb, row, (short) (initIndex + j + (mergeCellLength * i)),
                                                CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_BOTTOM, "谷", false, false, false,
                                                false);
                                        break;
                                    case 4:
                                        createCell(wb, row, (short) (initIndex + j + (mergeCellLength * i)),
                                                CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_BOTTOM, "尖", false, false, false,
                                                false);
                                        break;
                                }
                            }
                        }
                    }

                }
            }

        }
        return wb;

    }

    public XSSFWorkbook buildData(Map<String, String> blankFieldMap, int dataBeginIndex,
                                  List<List<String>> rowList) throws IOException {
        int sheetNumbers = this.wb.getNumberOfSheets();
        Set<Map.Entry<String, String>> set = blankFieldMap.entrySet();
        for (int sheetIndex = 0; sheetIndex < sheetNumbers; sheetIndex++) {
            // System.out.println("sheetIndex = " + sheetIndex);
            XSSFSheet st = this.wb.getSheetAt(sheetIndex);
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

    /**
     * Creates a cell and aligns it a certain way.
     *
     * @param wb     the workbook
     * @param row    the row to create the cell in
     * @param column the column number to create the cell in
     * @param halign the horizontal alignment for the cell.
     */
    private void createCell(Workbook wb, Row row, short column, short halign, short valign, String textValue,
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

        }

        File tpl = new File("/Users/barrie/Downloads/分时电量日报表.xlsx");
        File outFile = new File("/Users/barrie/Documents/Workspaces/MyEclipse/POI/test2.xlsx");

        // 从第9行开始为正式数据,之前为表头
        ExcelUtil util = new ExcelUtil(tpl);
//        util.buildHeader(5, 5, 2);
        util.buildData(blankFieldMap, 5, rowList);

        if (outFile.exists()) {
            outFile.delete();
        }
        // Write the output to a file
        BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(outFile));
        util.getWb().write(fileOut);
        fileOut.close();
        util.getWb().close();
    }
}
