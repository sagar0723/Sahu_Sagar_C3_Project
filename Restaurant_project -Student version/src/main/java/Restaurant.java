import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<Item> selectedMenu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
    	//weather the restaurant is opened/closed
    	LocalTime currentTime=getCurrentTime();
    	int paraOne=currentTime.compareTo(openingTime);//paraOne Should big greater 
    	int paraTwo=currentTime.compareTo(closingTime);//paraOne Should big smaller
    	if(paraOne>=0&&paraTwo<=0)
    		return true;
    	else
    		return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
    	//return the menu list of items
        return this.menu;
    }

    private Item findItemByName(String itemName) throws itemNotFoundException{
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        throw new itemNotFoundException(itemName);
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }
 
    //C3:Part3 newly added functions::::::::::::::::
    
	public List<Item> getSelectedMenu() {
		// TODO Auto-generated method stub
		return this.selectedMenu;
	}

	public void addToSelectedMenu(String itemName) throws itemNotFoundException {
		// TODO Auto-generated method stub
		Item item= findItemByName(itemName);
		selectedMenu.add(item);
		
	}

	public int yourOrderCost() {
		int totalprice=0,price;
		for(Item item:selectedMenu) {
			price=item.getPrice();
			totalprice+=price;
		}
		return totalprice;
	}

}
