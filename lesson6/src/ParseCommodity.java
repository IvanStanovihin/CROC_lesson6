import java.util.List;

    public class ParseCommodity {
        public List<Commodity> commoditys;

    }

    class Commodity{
        public int id;
        public String name;
        private Integer maxAmount = 0;
        private Seller sellerWithMaxAmountCommodity = null;


        public String toString(){
           return "id: " + id + " name: " + name + " max amount: " + maxAmount +
                   " seller with max amount: " + sellerWithMaxAmountCommodity;
        }
    }