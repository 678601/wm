package com.example.demo.utils;

import java.awt.Color;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;

/**
 * @author wanyajun 导出公共类
 *
 */
public class ExportExcelUtil {

	private final static Logger log = LoggerFactory.getLogger(ExportExcelUtil.class);

	/**
	 * 单个导出
	 * 
	 * @param response
	 * @param fileName
	 * @param data
	 * @throws Exception
	 */
	public static void exportExcel(HttpServletResponse response, String fileName, ExcelData data) throws Exception {
		// 告诉浏览器用什么软件可以打开此文件
//		response.setHeader("content-Type", "application/vnd.ms-excel");
		response.setHeader("content-Type", "application/octet-stream");
		// 下载文件的默认名称
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		response.setCharacterEncoding("utf-8");
		exportExcel(data, response.getOutputStream());
		log.info("导出结束");
	}

	public static void exportExcel(ExcelData data, OutputStream out) throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook();
		try {
			String sheetName = data.getSheetName();
			if (null == sheetName) {
				sheetName = "Sheet1";
			}
			XSSFSheet sheet = wb.createSheet(sheetName);
			writeExcel(wb, sheet, data);
			wb.write(out);
		} finally {
			wb.close();
		}
	}

	private static void writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) throws Exception {
		writeTitlesToExcel(wb, sheet, data.getTitles());
		writeRowsToExcel(wb, sheet, data.getKeys(), data.getList());
		autoSizeColumns(sheet, data.getTitles().length + 1);

	}

	public static void writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, String[] titles) {
		int colIndex = 0;
		Font titleFont = wb.createFont();
		titleFont.setFontName("simsun");
		titleFont.setBold(true);
		// titleFont.setFontHeightInPoints((short) 14);
		titleFont.setColor(IndexedColors.BLACK.index);

		XSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
		titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		titleStyle.setFont(titleFont);
		setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));

		Row titleRow = sheet.createRow(0);
		colIndex = 0;
		for (String field : titles) {
			Cell cell = titleRow.createCell(colIndex);
			cell.setCellValue(field);
			cell.setCellStyle(titleStyle);
			colIndex++;
		}
	}

	private static void writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, String[] keys, List<Map<String, String>> list)
			throws Exception {
		if (list == null || list.size() == 0) {
			return;
		}
		Font dataFont = wb.createFont();
		dataFont.setFontName("simsun");
		// dataFont.setFontHeightInPoints((short) 14);
		dataFont.setColor(IndexedColors.BLACK.index);

		XSSFCellStyle dataStyle = wb.createCellStyle();
		dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		dataStyle.setFont(dataFont);
		setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
		int rowIndex = 1;
		int colIndex = 0;
		for (Map<String, String> map : list) {
			Row dataRow = sheet.createRow(rowIndex);
			Cell cellindex = dataRow.createCell(0);
			cellindex.setCellStyle(dataStyle);
			colIndex = 0;
			for (String key : keys) {
				Cell cell = dataRow.createCell(colIndex);
				String value = map.get(key);
				cell.setCellValue(value);
				cell.setCellStyle(dataStyle);
				colIndex++;
			}
			rowIndex++;
		}
	}

	public static void autoSizeColumns(Sheet sheet, int columnNumber) {

		for (int i = 0; i < columnNumber; i++) {
			int orgWidth = sheet.getColumnWidth(i);
			sheet.autoSizeColumn(i, true);
			int newWidth = (int) (sheet.getColumnWidth(i) + 400);
			if (newWidth > orgWidth) {
				// 修复列宽255的问题
				sheet.setColumnWidth(i, newWidth > 65280 ? 65280 : newWidth);
			} else {
				sheet.setColumnWidth(i, orgWidth);
			}
		}
	}

	private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
		style.setBorderTop(border);
		style.setBorderLeft(border);
		style.setBorderRight(border);
		style.setBorderBottom(border);
		style.setBorderColor(BorderSide.TOP, color);
		style.setBorderColor(BorderSide.LEFT, color);
		style.setBorderColor(BorderSide.RIGHT, color);
		style.setBorderColor(BorderSide.BOTTOM, color);
	}

	public static boolean getRegionsCol(Cell cell, Sheet sheet, String name) {
		int sheetmergerCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetmergerCount; i++) {
			// 得出具体的合并单元格
			CellRangeAddress ca = sheet.getMergedRegion(i);
			// 得到合并单元格的起始行, 结束行, 起始列, 结束列
			int firstC = ca.getFirstColumn();
			int lastC = ca.getLastColumn();
			int firstR = ca.getFirstRow();
//			int lastR = ca.getLastRow();
			if (sheet.getRow(firstR).getCell(firstC).getStringCellValue().equals(name)) {
				// 判断该单元格是否在合并单元格范围之内, 如果是, 则返回 true
				if (cell.getColumnIndex() <= lastC && cell.getColumnIndex() >= firstC) {
					return true;
				}
			}
		}
		return false;
	}
}
