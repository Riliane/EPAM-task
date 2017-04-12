import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        RentUnit customer = new RentUnit();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV file name: ");
        boolean isRead = false;
        while (!isRead) {
            try {
                String filename = sc.nextLine();
                shop.readGoods(filename);
                isRead = true;
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Enter a different file name");
            } catch (IOException e) {
                System.out.println("There was an error reading the file. Enter a different file name");
            } catch (EmptyException e) {
                System.out.println("There are no goods in the file. Enter a different file name");
            }
        }
        int i = 1;
        while (i != 0){
            System.out.println("Enter 1 to rent goods, 2 to return rented goods, 3 to print available and returned goods, 0 to exit");
            try{
                i = Integer.parseInt(sc.nextLine());
                switch (i){
                    case 1:
                        System.out.println("Enter the name of the goods you'd like to rent");
                        shop.rentGoodsToCustomer(customer, sc.nextLine());
                        break;
                    case 2:
                        if(customer.printRentedGoods()) {
                            System.out.println("Enter the number of the goods you'd like to return");
                            shop.getBackGoods(customer, Integer.parseInt(sc.nextLine()));
                        }
                        break;
                    case 3:
                        shop.printAvailableGoods();
                        shop.printRentedGoods();
                        break;
                    default:
                        System.out.println("Invalid value entered");
                }
            }
            catch (NumberFormatException e){
                System.out.println("Invalid value entered");
            }


        }
    }
}
