public class SportEquipment {
    private String title;
    private int price;
    public SportEquipment(String t, int p){
        title = t;
        price = p;
    }
    public String toString(){
        return this.title + " " + Integer.toString(this.price);
    }
    public String getTitle(){
        return title;
    }
}
