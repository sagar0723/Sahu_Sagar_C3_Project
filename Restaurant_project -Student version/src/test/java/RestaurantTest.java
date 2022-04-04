import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    @BeforeEach//refactored Code
    public void beforeEachTest() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //TEST CASE BY SAGAR
    	
        Restaurant spyRestaurent= Mockito.spy(restaurant);
        //time between opening and closing
        Mockito.when(spyRestaurent.getCurrentTime()).thenReturn(LocalTime.parse("13:00:00"));
        boolean isOpen=spyRestaurent.isRestaurantOpen();
            
    	assertEquals(true,isOpen);
    }


	@Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
		//TEST CASE BY SAGAR
		
        Restaurant spyRestaurent= Mockito.spy(restaurant);
        //time before opening(08:00) and after closing(23:00)
        Mockito.when(spyRestaurent.getCurrentTime()).thenReturn(LocalTime.parse("08:00:00")).thenReturn(LocalTime.parse("23:00:00"));
    
        boolean isOpenBefore=spyRestaurent.isRestaurantOpen();
        boolean isOpenAfter=spyRestaurent.isRestaurantOpen();
            
    	assertEquals(false,isOpenBefore);
    	assertEquals(false,isOpenAfter);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
       
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
}