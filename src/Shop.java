import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Shop {
    private Map<SportEquipment, Integer> goods;
    private Map<SportEquipment, Integer> rentedGoods;
    public Shop(){
        goods = new HashMap<>();
        rentedGoods = new HashMap<>();
    }
    void readGoods (String filename) throws FileNotFoundException, IOException, EmptyException{
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        String s;
        in.readLine();//the first line is expected to be the csv table header and is skipped
        while ((s = in.readLine()) != null) {
            String[] data = s.split(";");
            try {
                if (data.length != 3) {
                    System.out.println("Incorrectly formatted line");
                }
                if (Integer.parseInt(data[1]) <= 0 || Integer.parseInt(data[2]) <=0 ){
                    System.out.println("Incorrectly formatted line. Price or quantity can't be negative or zero");
                }
                goods.put(new SportEquipment(data[0], Integer.parseInt(data[1])), Integer.parseInt(data[2]));
            } catch (NumberFormatException e) {
                System.out.println("Incorrectly formatted number");
            }
        } //display an error for every incorrectly formatted line but continue reading and adding to the map every correct one
            if (goods.isEmpty()) { throw new EmptyException(); }
            in.close();
    }
    void printAvailableGoods(){
        if (goods.isEmpty()) {System.out.println("There are no goods in the shop");}
        else{
            System.out.println("Available:");
            for (HashMap.Entry<SportEquipment, Integer> entry : goods.entrySet()){
                System.out.println(entry.getKey().toString() + " " + entry.getValue().toString());
            }
        }
    }
    void printRentedGoods(){
        if (rentedGoods.isEmpty()) {System.out.println("There are no goods currently rented");}
        else{
            System.out.println("Rented:");
            for (HashMap.Entry<SportEquipment, Integer> entry : rentedGoods.entrySet()){
                System.out.println(entry.getKey().toString() + " " + entry.getValue().toString());
            }
        }
    }
    void rentGoodsToCustomer(RentUnit customer, String name){
        boolean isFound = false;
        for (HashMap.Entry<SportEquipment, Integer> entry : goods.entrySet()){
            if (entry.getKey().getTitle().equalsIgnoreCase(name)) {
                isFound = true;
                if (customer.rentGoods(entry.getKey())) {
                    if (entry.getValue() == 1) {
                        goods.remove(entry.getKey());
                    } else {
                        goods.put(entry.getKey(), (entry.getValue()) - 1);
                    }
                    boolean isFoundRented = false;
                    for (HashMap.Entry<SportEquipment, Integer> rentedEntry : rentedGoods.entrySet()) {
                        if (rentedEntry.getKey().getTitle().equalsIgnoreCase(name)) {
                            rentedGoods.put(rentedEntry.getKey(), (rentedEntry.getValue() + 1));
                            isFoundRented = true;
                        }
                    }
                    if (!isFoundRented){
                        rentedGoods.put(entry.getKey(), 1);
                    }
                }
                break;
            }
        }
        if (!isFound){
            System.out.println("There are no such goods in store");
        }
    }
    void getBackGoods (RentUnit customer, int number){
        SportEquipment returnedGoods = customer.returnGoods(number-1);
        if (returnedGoods != null){
            Integer qty = goods.get(returnedGoods);
            if (qty == null) {
                qty = 0;
            }
            goods.put(returnedGoods, qty+1);
            qty = rentedGoods.get(returnedGoods);
            if (qty == 1) {
                rentedGoods.remove(returnedGoods);
            }
            else {
                rentedGoods.put(returnedGoods, qty-1);
            }
        }
    }
}
