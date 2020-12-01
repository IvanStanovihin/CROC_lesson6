import java.util.List;

    public class ParseSellers {
        public List<Seller> sellers;

        public Seller findSeller(int id){
            Seller foundSeller = null;
            for (Seller seller : sellers){
                if (id == seller.id){
                    foundSeller = seller;
                    break;
                }
            }
            return foundSeller;
        }
    }

    class Seller{
        public int id;
        public String lastName;
        public String firstName;

        public int getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String toString(){
            return "id: " + id + " firstName: " + firstName + " lastName: " + lastName;
        }
    }

