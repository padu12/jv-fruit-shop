package core.basesyntax;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.CsvFileReaderService;
import core.basesyntax.service.CsvFileWriterService;
import core.basesyntax.service.ReportCreatorService;
import core.basesyntax.service.impl.CsvFileReaderServiceImpl;
import core.basesyntax.service.impl.CsvFileWriterServiceImpl;
import core.basesyntax.service.impl.ReportCreatorServiceImpl;
import core.basesyntax.service.impl.StringToFruitTransactionListService;

public class Main {
    public static void main(String[] args) {
        CsvFileReaderService csvFileReaderService = new CsvFileReaderServiceImpl();
        StringToFruitTransactionListService transactionListService =
                new StringToFruitTransactionListService();
        CsvFileWriterService csvFileWriterService = new CsvFileWriterServiceImpl();
        ReportCreatorService<FruitTransaction> reportCreatorService =
                new ReportCreatorServiceImpl();
        String transactions = csvFileReaderService.read("src/main/resources/data.csv");
        Storage.setFruitTransactions(transactionListService.convert(transactions));
        String completeReport = reportCreatorService.create(Storage.getFruitTransactions());
        csvFileWriterService.write(completeReport);
    }
}
