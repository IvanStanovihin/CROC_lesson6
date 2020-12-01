import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String commoditysFilePath = "src\\inputJSONFiles\\commodities.json";

    private static final String sellersFilePath = "src\\inputJSONFiles\\sellers.json";

    private static final String salesFilePath = "src\\inputJSONFiles\\sales.json";

    private static final String commodityAvailabilityFilePath = "src\\inputJSONFiles\\commodityAvailability.json";


    public static void main(String[]args) {
        Gson g = new Gson();
        try (FileReader commoditysPath = new FileReader(commoditysFilePath);
             FileReader sellersPath = new FileReader(sellersFilePath);
             FileReader salesPath = new FileReader(salesFilePath);
             FileReader commoditysAvailabilityPath = new FileReader(commodityAvailabilityFilePath)) {

            ParseCommodity parseCommodities = g.fromJson(commoditysPath, ParseCommodity.class);
            ParseSellers parseSellers = g.fromJson(sellersPath, ParseSellers.class);
            ParseSales parseSales = g.fromJson(salesPath, ParseSales.class);
            ParseCommodityAvailability parseCommodityAvailability = g.fromJson(commoditysAvailabilityPath,
                    ParseCommodityAvailability.class);

            Map<Integer, Optional<AvailabilityCommodity>> maxCommoditiesCount
                    = getMaxCommodityAvailability(parseCommodityAvailability);
            XMLWriter.createXMLDocumentForFirstTask(maxCommoditiesCount, parseSellers);

            System.out.println("\nЗадание№2");
            Map<LocalDate, Long> distributionSale = getDistributionSaleByDate(parseSales);
            XMLWriter.createXMLDocumentForSecondTask(distributionSale);

        }catch(FileNotFoundException ex1){
            ex1.printStackTrace();
        }catch(IOException ex2){
            ex2.printStackTrace();
        }
    }

    /**
     * Метод находит для каждого товара его максимальное количество среди всех продавцов.
     * @param infoCommoditiesAvailability - информация о наличии товаров у продавцов, прочитанная из файла.
     * @return Слоарь, где ключ - id товара, значение - объект с информацией о найденном товаре.
     */
    public static Map<Integer, Optional<AvailabilityCommodity>> getMaxCommodityAvailability
            (ParseCommodityAvailability infoCommoditiesAvailability){

        Map<Integer, Optional<AvailabilityCommodity>> maxCommodityCount = null;
        maxCommodityCount = infoCommoditiesAvailability.commoditiesAvailability.stream()
                .collect(Collectors.groupingBy(AvailabilityCommodity::getCommodityID,
                        Collectors.maxBy(Comparator.comparing(AvailabilityCommodity::getCommodityAmount))));
        return maxCommodityCount;
    }


    /**
     * Метод находит распределение продаж по датам(определяет сколько продаж было сделанно в каждый из дней).
     * @param parseSales информация о всех продажах.
     * @return словарь, где ключ - дата продажи, значение - колличество продаж в этот день.
     */
    public static Map<LocalDate, Long> getDistributionSaleByDate(ParseSales parseSales){
        Map<LocalDate, Long> distributionSale;
        distributionSale = parseSales.sales.stream().collect(
                Collectors.groupingBy(Sale::getDate, Collectors.counting()));
        return distributionSale;
    }


}
