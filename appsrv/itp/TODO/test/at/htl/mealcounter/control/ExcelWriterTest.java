package at.htl.mealcounter.control;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@QuarkusTest
class ExcelWriterTest {
    //private ExcelWriter excelWriter;
    private static String FILE_NAME = "test.xlsx";
    private String fileLocation;

    @Inject
    ExcelWriter excelWriter;

    @Test
    public void generateExcelFile() throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        fileLocation = path.substring(0, path.length() - 1) + FILE_NAME;

       //excelWriter = new ExcelWriter();
        excelWriter.writeExcel();
    }




}
