import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ParseSales {

    List<Sale> sales;
}

    class Sale{
    public int saleID;
    public int sellerID;
    public int commodityID;
    public int amountCommodity;
    public String dateOfSale;

        public LocalDate getDate(){
            LocalDate dateResult = null;
            String dateInfo[] = dateOfSale.split("/");
            int []dateNumber = Arrays.asList(dateInfo).stream().mapToInt(Integer::parseInt).toArray();
                dateResult = LocalDate.of(dateNumber[2], dateNumber[1], dateNumber[0]);
            return dateResult;
        }
    }