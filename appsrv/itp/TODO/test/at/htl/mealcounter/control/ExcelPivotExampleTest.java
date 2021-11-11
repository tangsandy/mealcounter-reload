package at.htl.mealcounter.control;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ExcelPivotExampleTest {


    @Inject
    ExcelPivotExample excelPivotExample;
    @Test
    void writeExcel() {

        try {
            excelPivotExample.writeExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}