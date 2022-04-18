package kataTDD.kataTDD;

import kataTDD.kataTDD.exceptions.RentingServiceException;
import kataTDD.kataTDD.model.Car;
import kataTDD.kataTDD.model.RentItem;
import kataTDD.kataTDD.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import kataTDD.kataTDD.repositories.CarRepo;
import kataTDD.kataTDD.repositories.RentItemRepo;
import kataTDD.kataTDD.repositories.UserRepo;
import kataTDD.kataTDD.service.RentingService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class RentingServiceTest {

    private User user;
    private Car car;
    private RentItem rentItem;

    @Mock
    private UserRepo userRepo;
    @Mock
    private CarRepo carRepo;
    @Mock
    private RentItemRepo rentItemRepo;

    @BeforeEach
    void setUp(){
        User user = new User();
        Car car = new Car();
    }

    @Test
    void canRentCar(){
        //Given:
        User user = new User();
        Car car = new Car();
        RentItem rentItem = new RentItem(user, car);
        var rentingService = new RentingService(userRepo, carRepo, rentItemRepo);

        Mockito.when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(carRepo.findById(car.getId())).thenReturn(Optional.of(car));
        Mockito.when(rentItemRepo.save(any(RentItem.class))).thenReturn(rentItem);

        assertEquals(user, userRepo.findById(user.getId()).get());
        assertEquals(car, carRepo.findById(user.getId()).get());
        assertEquals(rentItem, rentItemRepo.save(new RentItem(new User(), new Car())));

        var sut = rentingService.rentCar(user.getId(), car.getId());

    }

    @Test
    void shouldThrowExceptionIfUserDoesntExist(){
        User user = new User();
        Car car = new Car();
        RentItem rentItem = new RentItem(user, car);
        var rentingService = new RentingService(userRepo, carRepo, rentItemRepo);

        Mockito.when(userRepo.findById(user.getId())).thenReturn(Optional.empty());
        Mockito.when(carRepo.findById(car.getId())).thenReturn(Optional.of(car));
        Mockito.when(rentItemRepo.save(any(RentItem.class))).thenReturn(rentItem);
        //Mockito.when(rentItemRepo.findRentItemByCarId(car)).thenReturn(Optional.of(rentItem));

        RentingServiceException thrown = assertThrows(RentingServiceException.class, ()-> rentingService.rentCar(user.getId(), car.getId()));
        //var sut = rentingService.rentCar(user.getId(), car.getId());

        assertEquals("User not Found", thrown.getMessage());
        assertEquals("R-101", thrown.getCode());
    }

    @Test
    void shouldThrowExceptionIfCarDoesntExist(){
        User user = new User();
        Car car = new Car();
        RentItem rentItem = new RentItem(user, car);
        var rentingService = new RentingService(userRepo, carRepo, rentItemRepo);

        Mockito.when(carRepo.findById(car.getId())).thenReturn(Optional.empty());
        Mockito.when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(rentItemRepo.save(rentItem)).thenReturn(rentItem);
        Mockito.when(rentItemRepo.findRentItemByCarId(car)).thenReturn(Optional.empty());

        RentingServiceException thrown = assertThrows(RentingServiceException.class, ()-> rentingService.rentCar(user.getId(), car.getId()));
        //var sut = rentingService.rentCar(user.getId(), car.getId());

        assertEquals("Car not Found", thrown.getMessage());
        assertEquals("C-101", thrown.getCode());
    }

    @Test
    void shouldThrowExceptionIfCarIsAlreadyRented(){
        User user = new User();
        Car car = new Car();
        RentItem rentItem = new RentItem(user, car);
        var rentingService = new RentingService(userRepo, carRepo, rentItemRepo);

        Mockito.when(carRepo.findById(car.getId())).thenReturn(Optional.of(car));
        Mockito.when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(rentItemRepo.save(rentItem)).thenReturn(rentItem);
        Mockito.when(rentItemRepo.findRentItemByCarId(car)).thenReturn(Optional.of(rentItem));

        RentingServiceException thrown = assertThrows(RentingServiceException.class, ()-> rentingService.rentCar(user.getId(), car.getId()));
        //var sut = rentingService.rentCar(user.getId(), car.getId());

        assertEquals("Car is already rented", thrown.getMessage());
        assertEquals("C-200", thrown.getCode());
    }


}