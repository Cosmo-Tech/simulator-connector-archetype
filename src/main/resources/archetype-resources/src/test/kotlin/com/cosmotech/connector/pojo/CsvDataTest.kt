package com.cosmotech.connector.pojo

import com.cosmotech.connector.CsmUnitTest
import org.apache.commons.io.FileUtils
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Test class for CsvData methods
 */
class CsvDataTest: CsmUnitTest()  {

    // Working test directory
    private val testTempFolder = Files.createTempDirectory("testTempFolder");

    @Test
    fun test_exportData() {
        val dataTestToPrint = createSampleDataForTest()
        val exportCsvFile = testTempFolder.resolve(dataTestToPrint.fileName.plus(".csv"))
        assertFalse (Files.exists(exportCsvFile),"Check if $exportCsvFile doesn't already exist")
        dataTestToPrint.exportData(testTempFolder.toString())
        assertTrue (Files.exists(exportCsvFile), "Check that $exportCsvFile has been correctly created")
        val expectedFileExported = getResourceFile("pojo/Test1.csv")
        assertTrue(
            FileUtils.contentEqualsIgnoreEOL(
                exportCsvFile.toFile(),
                expectedFileExported.toFile(),
                Charsets.UTF_8.name()
            ),"Check that the content exported is the expected one"
        )
    }

    @Test
    fun test_exportDataWithExistingTargetFile() {
        val dataTestToPrint = createSampleDataForTest()
        val exportCsvFile = testTempFolder.resolve(dataTestToPrint.fileName.plus(".csv"))
        Files.createFile(exportCsvFile)
        assertTrue(Files.exists(exportCsvFile),"Check if $exportCsvFile exists")
        assertEquals(Files.size(exportCsvFile),0,"Check if $exportCsvFile is empty")
        dataTestToPrint.exportData(testTempFolder.toString())
        val expectedFileExported = getResourceFile("pojo/Test1.csv").toFile()
        assertTrue(
            FileUtils.contentEqualsIgnoreEOL(
                exportCsvFile.toFile(),
                expectedFileExported,
                Charsets.UTF_8.name()
            ),"Check if $exportCsvFile has been overwritten"
        )
    }

    private fun createSampleDataForTest(): CsvData {
        return CsvData(
            "Test1",
            mutableListOf("column1", "column2", "column3"),
            mutableListOf(
                mutableListOf("data11", "data12", "data13"),
                mutableListOf("data21", "data22", "data23"),
                mutableListOf("data31", "data32", "data33")
            )
        )
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun getResourceFile(filePath: String): Path {
        return Paths.get(getTestResource(filePath)?.file)
    }
}