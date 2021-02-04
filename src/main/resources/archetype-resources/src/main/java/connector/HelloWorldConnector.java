package ${package}.connector;

import com.cosmotech.connector.commons.Connector;
import com.cosmotech.connector.commons.pojo.CsvData;
import com.github.doyaaaaaken.kotlincsv.client.CsvFileReader;
import com.github.doyaaaaaken.kotlincsv.dsl.context.CsvReaderContext;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

/**
 * Hello world Connector:
 * The aim of this class is to show you how to build a simple connector to the Simulator
 * This class :
 *  - define a client which will read a data source (here a CSV file)
 *  - process data
 *  - export several CSV files with data expected by the Simulator
 */
public class HelloWorldConnector implements Connector<CsvFileReader,List<CsvData>,List<String>>
{

    private static final Config configuration  = ConfigProvider.getConfig();

    @Override
    public CsvFileReader createClient() {
        // A dummy example here read a CSV file with some vegetable information
        try {
            return new CsvFileReader(
                new CsvReaderContext(),
                new BufferedReader(
                    new FileReader(
                        Objects.requireNonNull(this.getClass().getClassLoader()
                            .getResource(
                                configuration
                                    .getValue("raw.data.csv.path",
                                        String.class
                                    )
                            )
                        ).getFile()
                    )
                )
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @NotNull
    @Override
    public List<CsvData> prepare(CsvFileReader csvFileReader) {
        // A dummy example here:
        // - all source CSV lines are read
        // - the data are grouped by name (a column in the source CSV file)
        // - the list of data to send to the simulator is construct
        final List<CsvData> dataToExport = new ArrayList<>();
        try {
            // Specify the export directory for demonstration and test purpose
            final String exportDemoFolder = Files.createTempDirectory("exportDemoFolder").toString();
            final List<List<String>> dummyData = csvFileReader.readAll();

            if ( !dummyData.isEmpty() ) {
                // Extract headers from source files
                final List<String> headers = dummyData.get(0);
                final Map<String, String> headerNameAndType = headers.stream()
                    .collect(Collectors.toMap(Function.identity(), (header) -> "type" ));

                // Construct data to Export
                dummyData
                    .subList(1, dummyData.size())
                    .stream()
                    .collect(Collectors.groupingBy(strings -> strings.get(0)))
                    .forEach((fileName,rows) -> dataToExport.add(new CsvData(fileName,headerNameAndType,rows,exportDemoFolder)));

            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return dataToExport;
    }


    @Override
    public List<String> process() {
        //  Basically here :
        //  - The client which retrieve data is created
        //  - data are processed
        //  - data are sent to the simulator
        System.out.println("Start Process");
        System.out.println("Building Client ... ");
        final CsvFileReader client = this.createClient();
        List<String> result = new ArrayList<>();
        System.out.println("Constructing simulator data... ");
        final List<CsvData> processedData = this.prepare(client);
        processedData.forEach(
            (csvData) -> {
                System.out.println("Exporting simulator data into " + csvData.getExportDirectory() + csvData.getFileName() + ".csv");
                result.add(csvData.writeFile());
            }
        );
        System.out.println("End Process");
        return result;
    }
}
