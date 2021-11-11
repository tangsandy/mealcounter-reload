//package at.htl.mealcounter.control;
//
//import at.htl.mealcounter.entity.Person;
//import org.apache.poi.xssf.usermodel.*;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.*;
//import org.apache.poi.ss.SpreadsheetVersion;
//
//import org.apache.poi.xssf.streaming.*;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.time.YearMonth;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//import java.util.List;
//
//@ApplicationScoped
//class ExcelPivotExample {
//
//
//    @Inject
//    PersonRepository personRepository;
//
//    @Inject
//    ConsumationRepository consumationRepository;
//
//    private  void streamCellData(Sheet sheet, int rowsCount) {
//        Calendar calOne = Calendar.getInstance();
//        int year = calOne.get(Calendar.YEAR);
//        int rowsMonthCount = 0;
//       List<Person> personFromEntryYear = personRepository.findByEntryYear(2016);
//        int consumed;
// /*       for (int r = 1; r <= 12; r++) {
//
//            for (int i = 1; i <= YearMonth.of(year, r).lengthOfMonth(); i++) {
//                Row row = sheet.createRow(rowsCount+i+1);
//                Cell cell = row.createCell(0);
//                cell.setCellValue( Month.of(r).toString());
//                for (int j = 1; j < personFromEntryYear.size(); j++) {
//                    Person person = personFromEntryYear.get(j-1);
//                    if(consumationRepository.findByDateAndPerson(LocalDate.parse("2020-01-01"), person).isHasConsumed()){
//                        consumed = 1;
//                    }else {
//                        consumed = 0;
//                    }
//                    cell = row.createCell(j);
//                    cell.setCellValue(consumed);
//                }
//
//                rowsMonthCount += YearMonth.of(year, r).lengthOfMonth();
//            }
//
//
//
//        }*/
//
//
//        for (int i = 1; i <= 12 ; i++) {
//            for (int j = 1; j <= 31; j++) {
//                Row row = sheet.createRow(j);
//                Cell cell = row.createCell(0);
//                cell.setCellValue("monat");
//            }
//        }
//
//    }
//
//    public void writeExcel() throws IOException {
//
//        int rowsCount = 31;
//
////first create XSSFWorkbook
//        XSSFWorkbook wb = new XSSFWorkbook();
//
////create XSSFSheet with at least the headings
//        XSSFSheet sheet = wb.createSheet("Sheet1");
//        Row row = sheet.createRow(0);
//        Cell cell = row.createCell(0);
//        cell.setCellValue("Eintrittsjahr/Monat");
//
//        for (int i = 1; i < personRepository.findByEntryYear(2016).size(); i++) {
//             cell = row.createCell(i);
//            cell.setCellValue(personRepository.findById((long)i).getFirstName() + personRepository.findById((long)i).getLastName());
//        }
//       /* row = sheet.createRow(1);
//        for (int i = 1; i <= 12; i++) {
//            cell = row.createCell(0);
//        }*/
//
//
//
////create XSSFSheet for pivot table
//        XSSFSheet pivotSheet = wb.createSheet("Pivot sheet");
//
////create pivot table
//        XSSFPivotTable pivotTable = pivotSheet.createPivotTable(
//                new AreaReference(new CellReference("Sheet1!A1"),
//                        new CellReference("Sheet1!R" + (rowsCount +1)), //make the reference big enough for later data
//                        SpreadsheetVersion.EXCEL2007),
//                new CellReference("A5"));
////Configure the pivot table
////Use first column as row label
//        pivotTable.addRowLabel(0);
////Sum up the second column
//        for (int i = 1; i <  personRepository.findByEntryYear(2016).size(); i++) {
//            pivotTable.addColumnLabel(DataConsolidateFunction.SUM, i);
//
//        }
////Avarage the third column
//       // pivotTable.addColumnLabel(DataConsolidateFunction.AVERAGE, 2);
////Add filter on forth column
//       // pivotTable.addReportFilter(3);
//
////now create SXSSFWorkbook from XSSFWorkbook
//        SXSSFWorkbook swb = new SXSSFWorkbook(wb);
//        SXSSFSheet ssheet = swb.getSheet("Sheet1");
//
////now stream the big amount of data to build the pivot table on into Sheet1
//        streamCellData(ssheet, rowsCount);
//
//        swb.write(new FileOutputStream("nfc-reader.xlsx"));
//        swb.close();
//        swb.dispose();
//
//    }
//}
