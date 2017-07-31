package me.holostan.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by ghu on 7/24/2017.
 */
public class PoiUtil {

    private static final String SUFFIX_2003 = ".xls";
    private static final String SUFFIX_2007 = ".xlsx";

    public String filePath;
    private Sheet sheet;
    LinkedList<String>[] result;

    public static Map<String,String> locales = new HashMap<>();

    static {
        /**
         * en_US
         * da_KD
         * de_DE
         * en_GB
         * es_ES
         * es_MX
         * fi_FI
         * fr_FR
         * fr_CA
         * hu_HU
         * it_IT
         * ja_JA
         * ko_KO
         * nl_NL
         * no_NO
         * pl_PL
         * pt_BR
         * ru_RU
         * sv_SV
         * th_TH
         * tr_TR
         * zh_TW
         */
        locales.put("ENG_US","en_US");
        locales.put("ENG_GB","en_GB");
        locales.put("DAN_DK","da_DK");
        locales.put("GER_DE","de_DE");
        locales.put("SPA_ES","es_ES");
        locales.put("SPA_MX","es_MX");
        locales.put("FIN_FI","fi_FI");
        locales.put("FRE_FR","fr_FR");
        locales.put("FRE_CA","fr_CA");
        locales.put("ITA_IT","it_IT");
        locales.put("JPN_JP","ja_JP");
        locales.put("KOR_KR","ko_KR");
        locales.put("DUT_NL","nl_NL");
        locales.put("NOR_NO","no_NO");
        locales.put("POL_PL","pl_PL");
        locales.put("POR_BR","pt_BR");
        locales.put("RUS_RU","ru_RU");
        locales.put("SWE_SE","sv_SE");
        locales.put("THA_TH","th_TH");
        locales.put("CHT_CN","zh_CN");
        locales.put("CZE_CZ","cs_CZ");
        locales.put("TUR_TR","tr_TR");
        locales.put("POR_PT","pt_PT");
        locales.put("HUN_HU","hu_HU");
        locales.put("GRE_GR","el_GR");
        locales.put("ENG_US","en_US");
    }


    enum StringId {
        CheckoutDisclaimer_US("ro.msg.legal_msg.US"),
        CheckoutDisclaimer_EU("ro.msg.legal_msg.EU"),
        CheckoutDisclaimer_JP("ro.msg.legal_msg.JP"),
        Gifting_CheckoutDisclaimer_US("ro.msg.gift_legal.US"),
        Gifting_CheckoutDisclaimer_EU("ro.msg.gift_legal.EU"),
        Gifting_CheckoutDisclaimer_JP("ro.msg.gift_legal.JP"),
        Subs_CheckoutDisclaimer_US("ro.msg.subs_legal.US"),
        Subs_CheckoutDisclaimer_EU("ro.msg.subs_legal.EU"),
        Subs_CheckoutDisclaimer_JP("ro.msg.subs_legal.JP");

        private String id;

        StringId(String id){
            this.id = id;
        }

        protected static StringId byId(String id) {
            switch (id){
                case("ro.msg.legal_msg.US"):
                    return CheckoutDisclaimer_US;
                case("ro.msg.legal_msg.EU"):
                    return CheckoutDisclaimer_EU;
                case("ro.msg.legal_msg.JP"):
                    return CheckoutDisclaimer_JP;
                case("ro.msg.gift_legal.US"):
                    return Gifting_CheckoutDisclaimer_US;
                case("ro.msg.gift_legal.EU"):
                    return Gifting_CheckoutDisclaimer_EU;
                case("ro.msg.gift_legal.JP"):
                    return Gifting_CheckoutDisclaimer_JP;
                case("ro.msg.subs_legal.US"):
                    return Subs_CheckoutDisclaimer_US;
                case("ro.msg.subs_legal.EU"):
                    return Subs_CheckoutDisclaimer_EU;
                case("ro.msg.subs_legal.JP"):
                    return Subs_CheckoutDisclaimer_JP;
                default:
                    return null;
            }
        }

    }

    public static void main(String[] args) throws Exception{

        File path = new File("D:\\out\\code");
        File[] files = path.listFiles();

        Map<String,StringBuffer> resultMap = new LinkedHashMap<>();

        for(File file : files) {
            PoiUtil poiUtil = new PoiUtil();
            poiUtil.filePath = file.getAbsolutePath();
            Workbook workbook = poiUtil.initWorkBook();
            poiUtil.loadExcel(workbook);
            poiUtil.init();

            for(int i = 2; i < poiUtil.result.length; i++){
                String id = poiUtil.result[i].get(0);
                String locale = poiUtil.result[i].get(2);
                String legalText = poiUtil.result[i].get(3);

                StringBuffer buffer = resultMap.get(locale);
                if(buffer == null){
                    buffer = new StringBuffer();
                }

                String name = StringId.byId(id) == null ? id : StringId.byId(id).name();

                buffer.append(name).append('=').append(legalText).append('\n');

                resultMap.put(locale,buffer);

            }
        }

        StringBuffer resultString = new StringBuffer();

        for(Map.Entry<String,StringBuffer> entry : resultMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue().toString();

            String l = locales.get(key);

            if(l == null){
                l = key;
            }

            resultString.append(l).append('\n').append(value).append("\n\n");
        }

        PrintWriter out = new PrintWriter("D:\\out\\out.txt");
        try {
            out.print( resultString.toString() );
            out.flush();
        } finally {
            out.close();
        }

    }

    void loadExcel(Workbook workbook) {
        sheet = workbook.getSheetAt(0);
    }

    private String getCellValue(Cell cell) {
        String cellValue = "";
        DataFormatter formatter = new DataFormatter();
        if (cell != null) {
            //判断单元格数据的类型，不同类型调用不同的方法
            switch (cell.getCellType()) {
                //数值类型
                case Cell.CELL_TYPE_NUMERIC:
                    //进一步判断 ，单元格格式是日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = formatter.formatCellValue(cell);
                    } else {
                        //数值
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                //判断单元格是公式格式，需要做一种特殊处理来得到相应的值
                case Cell.CELL_TYPE_FORMULA:{
                    try{
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }catch(IllegalStateException e){
                        cellValue = String.valueOf(cell.getRichStringCellValue());
                    }

                }
                break;
                case Cell.CELL_TYPE_BLANK:
                    cellValue = "";
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "";
                    break;
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }
        return cellValue.trim();
    }

    //初始化表格中的每一行，并得到每一个单元格的值
    public  void init(){
        int rowNum = sheet.getLastRowNum() + 1;
        result = new LinkedList[rowNum];
        for(int i=0;i<rowNum;i++){
            Row row = sheet.getRow(i);
            //每有新的一行，创建一个新的LinkedList对象
            result[i] = new LinkedList();
            for(int j=0;j<row.getLastCellNum();j++){
                Cell cell = row.getCell(j);
                //获取单元格的值
                String str = getCellValue(cell);
                //将得到的值放入链表中
                result[i].add(str);
            }
        }
    }

    Workbook initWorkBook() throws IOException {
        File file = new File(filePath);
        InputStream is = new FileInputStream(file);

        Workbook workbook = null;
        //根据后缀，得到不同的Workbook子类，即HSSFWorkbook或XSSFWorkbook
        if (filePath.endsWith(SUFFIX_2003)) {
            workbook = new HSSFWorkbook(is);
        } else if (filePath.endsWith(SUFFIX_2007)) {
//                workbook = new XSSFWorkbook(is);
            //todo:
        }

        return workbook;
    }

}
