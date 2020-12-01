import java.util.List;

    public class ParseCommodityAvailability {
        public List<AvailabilityCommodity>commoditiesAvailability;
    }

    class AvailabilityCommodity{
        public int sellerId;
        public int commodityID;
        public int commodityPrice;
        public int commodityAmount;

        public int getSellerId() {
            return sellerId;
        }

        public int getCommodityAmount() {
            return commodityAmount;
        }

        public int getCommodityID() {
            return commodityID;
        }

    }