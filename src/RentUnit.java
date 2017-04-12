public class RentUnit {
    private SportEquipment[] units;
    public RentUnit(){
        units = new SportEquipment[]{null, null, null};
    }
    public boolean rentGoods(SportEquipment equipment){
        int i = 0;
        while (i<3 && units[i] != null){ //goods are put in the first available space
            i++;
        }
        if (i == 3){
            System.out.println("Cannot rent new equipment. Rented equipment needs to be returned first");
            return false;
        }
         units[i] = equipment;
         return true;
    }
    public SportEquipment returnGoods(int i){
        if (i < 0 || i > 2 || units[i] == null){
            System.out.println("Invalid number of equipment to be returned");
            return null;
        }
        else {
            SportEquipment equipment = units[i];
            while (i < 2){
                units[i] = units[i+1]; //move all the goods in spaces next to the freed one to the previous spaces
                i++;
            }
            units[i] = null;
            return equipment;
        }
    }
    public boolean printRentedGoods(){
        if (units[0] == null){
            System.out.println("This customer has no rented goods");
            return false;
        }
        else {
            int i = 0;
            while (i<3 && units[i] != null){
                System.out.println((i+1) + ". " + units[i].toString());
                i++;
            }
            return true;
        }
    }
}
