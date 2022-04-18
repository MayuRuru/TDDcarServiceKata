package kataTDD.kataTDD.service;

import kataTDD.kataTDD.exceptions.RentingServiceException;
import kataTDD.kataTDD.model.Car;
import kataTDD.kataTDD.model.RentItem;
import kataTDD.kataTDD.model.User;
import kataTDD.kataTDD.repositories.CarRepo;
import kataTDD.kataTDD.repositories.RentItemRepo;
import kataTDD.kataTDD.repositories.UserRepo;

public class RentingService {

    private UserRepo userRepo;
    private CarRepo carRepo;
    private RentItemRepo rentItemRepo;

    public RentingService(UserRepo userRepo, CarRepo carRepo, RentItemRepo rentItemRepo) {
        this.userRepo = userRepo;
        this.carRepo = carRepo;
        this.rentItemRepo = rentItemRepo;
    }

    public RentItem rentCar(Long userId, Long cardId) {
        //find a user
        var userOptional = userRepo.findById(userId);
        if (userOptional.isEmpty()){
            throw new RentingServiceException("User not Found", "R-101");
        }

        //find a car
        var carOptional = carRepo.findById(cardId);
        if (carOptional.isEmpty()){
            throw new RentingServiceException("Car not Found", "C-101");
        }

        //check if car is rented already
        var rentItemOptional = rentItemRepo.findRentItemByCarId(carOptional.get());
        if (rentItemOptional.isPresent()){
            throw new RentingServiceException("Car is already rented", "C-200");
        }

        //create rent item
        RentItem rentItem = new RentItem(new User(), new Car());
        rentItem.setRentingUser(userOptional.get());
        rentItem.setRentedCar(carOptional.get());
        //rentItem.setDate(new Date());

        //save rent item and return
        return rentItemRepo.save(rentItem);
    }
}
