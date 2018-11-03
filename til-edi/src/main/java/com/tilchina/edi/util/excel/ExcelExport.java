package com.tilchina.edi.util.excel;


import com.tilchina.edi.util.*;
import com.tilchina.edi.util.collect.ListUtils;
import com.tilchina.edi.util.collect.SetUtils;
import com.tilchina.edi.util.excel.annotaion.ExcelAnnotationUtils;
import com.tilchina.edi.util.excel.annotaion.ExcelField;
import com.tilchina.edi.util.excel.annotaion.ExcelFields;
import com.tilchina.edi.util.lang.ObjectUtils;
import com.tilchina.edi.util.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
/**
 * 导出Excel文件 （导出“XLSX”格式，支持大数据量导出）
 *
 * @version 2018-09-03
 */
public class ExcelExport implements Closeable {
    private static Logger logger = LoggerUtil.getLogger(ExcelExport.class);

    /**
     * 工作簿对象
     */
    private Workbook wb;
    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;

    /**
     * 当前行号
     */
    private int rownum;

    /**
     * 注解列表（Object[]{ ExcelField, Field/Method }）
     */
    private List<Object[]> annotationList;

    /**
     * 用户清理缓存
     */
    private Set<Class<?>> fieldTypes = SetUtils.newHashSet();

    /**
     * 构造函数（导出数据）
     *
     * @param title 表格标题，传“空值”，表示无标题
     * @param cls   实体对象，通过annotation.ExportField获取标题
     */
    public ExcelExport(String title, Class<?> cls) {
        this(title, cls, ExcelField.Type.EXPORT);
    }

    /**
     * 构造函数
     *
     * @param title 表格标题，传“空值”，表示无标题
     * @param cls   实体对象，通过annotation.ExportField获取标题
     * @param type  导出类型（1:导出数据；2：导出模板）
     */
    public ExcelExport(String title, Class<?> cls, ExcelField.Type type) {
        this(title, cls, type, null);
    }

    /**
     * 构造函数
     *
     * @param title  表格标题，传“空值”，表示无标题
     * @param cls    实体对象，通过annotation.ExportField获取标题
     * @param type   导出类型（1:导出数据；2：导出模板）
     * @param groups 导入分组
     */
    public ExcelExport(String title, Class<?> cls, ExcelField.Type type, String... groups) {
        this(null, title, cls, type, groups);
    }

    /**
     * 构造函数
     *
     * @param sheetName 指定Sheet名称
     * @param title     表格标题，传“空值”，表示无标题
     * @param cls       实体对象，通过annotation.ExportField获取标题
     * @param type      导出类型（1:导出数据；2：导出模板）
     * @param groups    导入分组
     */
    public ExcelExport(String sheetName, String title, Class<?> cls, ExcelField.Type type, String... groups) {
        this(null, sheetName, title, cls, type, groups);
    }

    /**
     * 构造函数
     *
     * @param wb        指定现有工作簿对象
     * @param sheetName 指定Sheet名称
     * @param title     表格标题，传“空值”，表示无标题
     * @param cls       实体对象，通过annotation.ExportField获取标题
     * @param type      导出类型（1:导出数据；2：导出模板）
     * @param groups    导入分组
     */
    public ExcelExport(Workbook wb, String sheetName, String title, Class<?> cls, ExcelField.Type type, String... groups) {
        if (wb != null) {
            this.wb = wb;
        } else {
            this.wb = createWorkbook();
        }
        this.createSheet(sheetName, title, cls, type, groups);
    }

    /**
     * 构造函数
     *
     * @param title           表格标题，传“空值”，表示无标题
     * @param headerList      表头列表
     * @param headerWidthList 表头宽度列表
     */
    public ExcelExport(String title, List<String> headerList, List<Integer> headerWidthList) {
        this(null, title, headerList, headerWidthList);
    }

    /**
     * 构造函数
     *
     * @param sheetName       指定Sheet名称
     * @param title           表格标题，传“空值”，表示无标题
     * @param headerList      表头列表
     * @param headerWidthList 表头宽度列表
     */
    public ExcelExport(String sheetName, String title, List<String> headerList, List<Integer> headerWidthList) {
        this(null, sheetName, title, headerList, headerWidthList);
    }

    /**
     * 构造函数
     *
     * @param wb              指定现有工作簿对象
     * @param sheetName       指定Sheet名称
     * @param title           表格标题，传“空值”，表示无标题
     * @param headerList      表头列表
     * @param headerWidthList 表头宽度列表
     */
    public ExcelExport(Workbook wb, String sheetName, String title, List<String> headerList, List<Integer> headerWidthList) {
        if (wb != null) {
            this.wb = wb;
        } else {
            this.wb = createWorkbook();
        }
        this.createSheet(sheetName, title, headerList, headerWidthList);
    }

    /**
     * 创建一个工作簿
     *
     * @return
     */
    public Workbook createWorkbook() {
        return new SXSSFWorkbook(500);
    }

    /**
     * 获取当前工作薄
     *
     * @return
     */
    public Workbook getWorkbook() {
        return wb;
    }

    /**
     * 创建工作表
     *
     * @param sheetName 指定Sheet名称
     * @param title     表格标题，传“空值”，表示无标题
     * @param cls       实体对象，通过annotation.ExportField获取标题
     * @param type      导出类型（1:导出数据；2：导出模板）
     * @param groups    导入分组
     */
    public void createSheet(String sheetName, String title, Class<?> cls, ExcelField.Type type, String... groups) {
        this.annotationList = ListUtils.newArrayList();
        // Get annotation field
        Field[] fs = cls.getDeclaredFields();
        for (Field f : fs) {
            ExcelFields efs = f.getAnnotation(ExcelFields.class);
            if (efs != null && efs.value() != null) {
                for (ExcelField ef : efs.value()) {
                    ExcelAnnotationUtils.addAnnotation(annotationList, ef, f, type, groups);
                }
            }
            ExcelField ef = f.getAnnotation(ExcelField.class);
            ExcelAnnotationUtils.addAnnotation(annotationList, ef, f, type, groups);
        }
        // Get annotation method
        Method[] ms = cls.getDeclaredMethods();
        for (Method m : ms) {
            ExcelFields efs = m.getAnnotation(ExcelFields.class);
            if (efs != null && efs.value() != null) {
                for (ExcelField ef : efs.value()) {
                    ExcelAnnotationUtils.addAnnotation(annotationList, ef, m, type, groups);
                }
            }
            ExcelField ef = m.getAnnotation(ExcelField.class);
            ExcelAnnotationUtils.addAnnotation(annotationList, ef, m, type, groups);
        }
        // Field sorting
        Collections.sort(annotationList, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                return new Integer(((ExcelField) o1[0]).sort()).compareTo(
                        new Integer(((ExcelField) o2[0]).sort()));
            }

            ;
        });
        // Initialize
        List<String> headerList = ListUtils.newArrayList();
        List<Integer> headerWidthList = ListUtils.newArrayList();
        for (Object[] os : annotationList) {
            ExcelField ef = (ExcelField) os[0];
            String headerTitle = ef.title();
            // 如果是导出，则去掉注释
            if (type == ExcelField.Type.EXPORT) {
                String[] ss = StringUtils.split(headerTitle, "**", 2);
                if (ss.length == 2) {
                    headerTitle = ss[0];
                }
            }
            headerList.add(headerTitle);
            headerWidthList.add(ef.width());
        }
        // 创建工作表
        this.createSheet(sheetName, title, headerList, headerWidthList);
    }


    /**
     * 创建工作表
     *
     * @param sheetName       指定Sheet名称
     * @param title           表格标题，传“空值”，表示无标题
     * @param headerList      表头字段设置
     * @param headerWidthList 表头字段宽度设置
     */
    public void createSheet(String sheetName, String title, List<String> headerList, List<Integer> headerWidthList) {
        this.sheet = wb.createSheet(StringUtils.defaultString(sheetName, StringUtils.defaultString(title, "Sheet1")));
        this.styles = createStyles(wb);
        this.rownum = 0;
        //create title
        if (StringUtils.isNotBlank(title)) {
            Row titleRow = sheet.createRow(rownum++);
            titleRow.setHeightInPoints(30);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle(styles.get("title"));
            titleCell.setCellValue(title);
            sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
                    titleRow.getRowNum(), titleRow.getRowNum(), headerList.size() - 1));
        }
        //create header
        if (headerList == null) {
            throw new ExcelException("headerList not null!");
        }
        Row headerRow = sheet.createRow(rownum++);
        headerRow.setHeightInPoints(16);
        for (int i = 0; i < headerList.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(styles.get("header"));
            String[] ss = StringUtils.split(headerList.get(i), "**", 2);
            if (ss.length == 2) {
                cell.setCellValue(ss[0]);
                Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
                        new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
                comment.setRow(cell.getRowIndex());
                comment.setColumn(cell.getColumnIndex());
                comment.setString(new XSSFRichTextString(ss[1]));
                cell.setCellComment(comment);
            } else {
                cell.setCellValue(headerList.get(i));
            }
        }
        boolean isDefWidth = (headerWidthList != null && headerWidthList.size() == headerList.size());
        for (int i = 0; i < headerList.size(); i++) {
            int colWidth = -1;
            if (isDefWidth) {
                colWidth = headerWidthList.get(i);
            }
            if (colWidth == -1) {
                colWidth = sheet.getColumnWidth(i) * 2;
                colWidth = colWidth < 3000 ? 3000 : colWidth;
            }
            if (colWidth == 0) {
                sheet.setColumnHidden(i, true);
            } else {
                sheet.setColumnWidth(i, colWidth);
            }
        }
        logger.debug("Create sheet {} success."+sheetName);
    }

    /**
     * 创建表格样式
     *
     * @param wb 工作薄对象
     * @return 样式列表
     */
    private Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

        CellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(titleFont);
        styles.put("title", style);

        style = wb.createCellStyle();
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(CellStyle.ALIGN_LEFT);
        styles.put("data1", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(CellStyle.ALIGN_CENTER);
        styles.put("data2", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        styles.put("data3", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);

        return styles;
    }

    /**
     * 添加一行
     *
     * @return 行对象
     */
    public Row addRow() {
        return sheet.createRow(rownum++);
    }

    /**
     * 添加一个单元格
     *
     * @param row    添加的行
     * @param column 添加列号
     * @param val    添加值
     * @return 单元格对象
     */
    public Cell addCell(Row row, int column, Object val) {
        return this.addCell(row, column, val, ExcelField.Align.AUTO, Class.class, null);
    }

    /**
     * 添加一个单元格
     *
     * @param row        添加的行
     * @param column     添加列号
     * @param val        添加值
     * @param align      对齐方式（1：靠左；2：居中；3：靠右）
     * @param fieldType
     * @param dataFormat 数值格式（例如：0.00，yyyy-MM-dd）
     * @return
     */
    public Cell addCell(Row row, int column, Object val, ExcelField.Align align, Class<?> fieldType, String dataFormat) {
        Cell cell = row.createCell(column);
        String defaultDataFormat = "@";
        try {
            if (val == null) {
                cell.setCellValue("");
            } else if (fieldType != Class.class) {
                fieldTypes.add(fieldType); // 先存起来，方便完成后清理缓存
                cell.setCellValue((String) fieldType.getMethod("setValue", Object.class).invoke(null, val));
                try {
                    defaultDataFormat = (String) fieldType.getMethod("getDataFormat").invoke(null);
                } catch (Exception ex) {
                    defaultDataFormat = "@";
                }
            } else {
                if (val instanceof String) {
                    cell.setCellValue((String) val);
                } else if (val instanceof Integer) {
                    cell.setCellValue((Integer) val);
                    defaultDataFormat = "0";
                } else if (val instanceof Long) {
                    cell.setCellValue((Long) val);
                    defaultDataFormat = "0";
                } else if (val instanceof Double) {
                    cell.setCellValue((Double) val);
                    defaultDataFormat = "0.00";
                } else if (val instanceof Float) {
                    cell.setCellValue((Float) val);
                    defaultDataFormat = "0.00";
                } else if (val instanceof Date) {
                    cell.setCellValue((Date) val);
                    defaultDataFormat = "yyyy-MM-dd HH:mm";
                } else {
                    // 如果没有指定 fieldType，切自行根据类型查找相应的转换类（com.jeesite.common.utils.excel.fieldtype.值的类名+Type）
                    Class<?> fieldType2 = Class.forName(this.getClass().getName().replaceAll(this.getClass().getSimpleName(),
                            "fieldtype." + val.getClass().getSimpleName() + "Type"));
                    fieldTypes.add(fieldType2); // 先存起来，方便完成后清理缓存
                    cell.setCellValue((String) fieldType2.getMethod("setValue", Object.class).invoke(null, val));
                }
            }
            CellStyle style = styles.get("data_column_" + column);
            if (style == null) {
                style = wb.createCellStyle();
                style.cloneStyleFrom(styles.get("data" + (align.value() >= 1 && align.value() <= 3 ? align.value() : "")));
                if (dataFormat != null) {
                    defaultDataFormat = dataFormat;
                }
                style.setDataFormat(wb.createDataFormat().getFormat(defaultDataFormat));
                styles.put("data_column_" + column, style);
            }
            cell.setCellStyle(style);
        } catch (Exception ex) {
            logger.info("Set cell value [" + row.getRowNum() + "," + column + "] error: " + ex.toString());
            cell.setCellValue(ObjectUtils.toString(val));
        }
        return cell;
    }

    /**
     * 添加数据
     *
     * @param list
     * @param <E>
     * @return
     */
    public <E> ExcelExport setDataList(List<E> list) {
        for (E e : list) {
            int colunm = 0;
            Row row = this.addRow();
            StringBuilder sb = new StringBuilder();
            for (Object[] os : annotationList) {
                ExcelField ef = (ExcelField) os[0];
                Object val = null;
                // Get entity value
                try {
                    if (StringUtils.isNotBlank(ef.attrName())) {
                        val = ReflectUtils.invokeGetter(e, ef.attrName());
                    } else {
                        if (os[1] instanceof Field) {
                            val = ReflectUtils.invokeGetter(e, ((Field) os[1]).getName());
                        } else if (os[1] instanceof Method) {
                            val = ReflectUtils.invokeMethod(e, ((Method) os[1]).getName(), new Class[]{}, new Object[]{});
                        }
                    }
                    // If is dict, get dict label
                    //if (StringUtils.isNotBlank(ef.dictType())) {}
                } catch (Exception ex) {
                    // Failure to ignore
                    logger.info(ex.toString());
                    val = "";
                }
                String dataFormat = ef.dataFormat();
                /*try {
                    // 获取Json格式化注解的格式化参数
                    JsonFormat jf = e.getClass().getMethod("get" + StringUtils.capitalize(ef.attrName())).getAnnotation(JsonFormat.class);
                    if (jf != null && jf.pattern() != null) {
                        dataFormat = jf.pattern();
                    }
                } catch (Exception e1) {
                    // 如果获取失败，则使用默认。
                }*/
                this.addCell(row, colunm++, val, ef.align(), ef.fieldType(), dataFormat);
                sb.append(val + ", ");
            }
            logger.debug("Write success: [" + row.getRowNum() + "] " + sb.toString());
        }
        return this;
    }

    /**
     * 输出数据流
     *
     * @param os 输出数据流
     */
    public ExcelExport write(OutputStream os) {
        try {
            wb.write(os);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return this;
    }


    /**
     * 输出到文件
     *
     * @param name 输出文件名
     */
    public ExcelExport writeFile(String name) throws FileNotFoundException, IOException {
        FileOutputStream os = new FileOutputStream(name);
        this.write(os);
        return this;
    }

    /**
     * 清理临时文件
     *
     * @deprecated see close()
     */
    public ExcelExport dispose() {
        try {
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void close() {
        if (wb instanceof SXSSFWorkbook) {
            ((SXSSFWorkbook) wb).dispose();
        }
        Iterator<Class<?>> it = fieldTypes.iterator();
        while (it.hasNext()) {
            Class<?> clazz = it.next();
            try {
                clazz.getMethod("clearCache").invoke(null);
            } catch (Exception e) {
                // 报错忽略，有可能没实现此方法
            }
        }
    }
}
