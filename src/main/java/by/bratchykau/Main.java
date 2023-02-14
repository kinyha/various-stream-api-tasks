package by.bratchykau;

import by.bratchykau.model.Animal;
import by.bratchykau.model.Car;
import by.bratchykau.model.Flower;
import by.bratchykau.model.House;
import by.bratchykau.model.Person;
import by.bratchykau.util.Constance;
import by.bratchykau.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream().filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparing(Animal::getAge))
                .skip(Constance.NUMBER_OF_ANIMALS_IN_ZOO * 2)
                .limit(Constance.NUMBER_OF_ANIMALS_IN_ZOO)
                .forEach(System.out::println);
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> Constance.JAPANESE.equals(animal.getOrigin()))
                .map(animal -> {
                    animal.setBread(animal.getBread().toUpperCase());
                    return animal;
                })
                .filter(animal -> Constance.FEMALE.equals(animal.getGender()))
                .map(Animal::getBread)
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() >= 30)
                .map(animal -> animal.getOrigin())
                .distinct()
                .filter(origin -> origin.startsWith("A"))
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long animalsCount = animals.stream()
                .filter(animal -> "Female".equals(animal.getGender()))
                .count();
        System.out.println("Animals count: " + animalsCount);
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isHungarian = animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal -> "Hungarian".equals(animal.getOrigin()));
        System.out.println("Is Hungarian: " + isHungarian);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isFemaleAndMale = animals.stream()
                .allMatch(animal -> animal.getGender().equals("Female") && animal.getGender().equals("Male"));
        System.out.println("Is Female and Male: " + isFemaleAndMale);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isAnimalsFromOceaniaExist = animals.stream()
                .noneMatch(animal -> animal.getOrigin().equals("Oceania"));
        System.out.println("Is animals from Oceania exist: " + isAnimalsFromOceaniaExist);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        Optional<Animal> maxAge = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max(Comparator.comparing(Animal::getAge));
        System.out.println("Max age: " + maxAge.get().getAge());
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        Optional<Integer> minBreadLength = animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .map(chars -> chars.length)
                .min(Comparator.comparing(Integer::intValue));
        System.out.println("Min bread length: " + minBreadLength.get());
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();

        Optional<Integer> sumOfAges = animals.stream()
                .map(animal -> animal.getAge())
                .reduce(Integer::sum);
        System.out.println("Sum of ages: " + sumOfAges.get());
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        double avgAge = animals.stream()
                .filter(animal -> "Indonesian".equals(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .orElse(0.0);
        System.out.println("Average age of Indonesian animals: " + avgAge);
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
                .filter(person -> "Male".equals(person.getGender()))
                .filter(person -> person.getDateOfBirth().isAfter(LocalDate.of(1996, 2, 13)) &&
                        person.getDateOfBirth().isBefore(LocalDate.of(2005, 2, 14)))
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        Stream.concat(
                        houses.stream()
                                .filter(house -> "Hospital".equals(house.getBuildingType()))
                                .flatMap(house -> house.getPersonList().stream()),
                        houses.stream()
                                .flatMap(house -> house.getPersonList().stream())
                                .filter(person -> (("Male".equals(person.getGender()) && person.getDateOfBirth().isBefore(LocalDate.of(1960, 2, 13)) ||
                                        "Female".equals(person.getGender()) && person.getDateOfBirth().isBefore(LocalDate.of(1965, 2, 13))))
                                        || person.getDateOfBirth().isAfter(LocalDate.of(2005, 2, 14)))
                )
                .limit(500)
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        Map<String, List<Car>> carsByCountry = cars.stream()
                .collect(Collectors.groupingBy(car -> {
                    if ("Jaguar".equals(car.getCarMake()) || "White".equals(car.getColor())) {
                        return "Turkmenistan";
                    } else if (car.getMass() <= 1500 && ("Lexus".equals(car.getCarMake()) || "BMW".equals(car.getCarMake())
                            || "Chrysler".equals(car.getCarMake()) || "Toyota".equals(car.getCarMake()))) {
                        return "Uzbekistan";
                    } else if (("Black".equals(car.getColor()) && car.getMass() >= 4000) || "GMC".equals(car.getCarMake())
                            || "Dodge".equals(car.getCarMake())) {
                        return "Kazakhstan";
                    } else if (car.getReleaseYear() <= 1982 || "Civic".equals(car.getCarModel()) || "Cherokee".equals(car.getCarModel())) {
                        return "Kyrgyzstan";
                    } else if ((!"Yellow".equals(car.getColor()) && !"Red".equals(car.getColor()) &&
                            !"Green".equals(car.getColor()) && !"Blue".equals(car.getColor())) || car.getPrice() >= 40000) {
                        return "Russia";
                    } else if (car.getVin().contains("59")) {
                        return "Mongolia";
                    } else {
                        return "Other";
                    }
                }));

        double costPerKg = 0.00714;

        double turkmenistanLogisticCost = carsByCountry.get("Turkmenistan").stream()
                .mapToInt(Car::getMass)
                .sum() * costPerKg;
        System.out.println("Turkmenistan logistic cost: " + turkmenistanLogisticCost + "$");

        double uzbekistanLogisticCost = carsByCountry.get("Uzbekistan").stream()
                .mapToInt(Car::getMass)
                .sum() * costPerKg;
        System.out.println("Uzbekistan logistic cost: " + uzbekistanLogisticCost + "$");

        double kazakhstanLogisticCost = carsByCountry.get("Kazakhstan").stream()
                .mapToInt(Car::getMass)
                .sum() * costPerKg;
        System.out.println("Kazakhstan logistic cost: " + kazakhstanLogisticCost + "$");

        double kyrgyzstanLogisticCost = carsByCountry.get("Kyrgyzstan").stream()
                .mapToInt(Car::getMass)
                .sum() * costPerKg;
        System.out.println("Kyrgyzstan logistic cost: " + kyrgyzstanLogisticCost + "$");

        double russiaLogisticCost = carsByCountry.get("Russia").stream()
                .mapToInt(Car::getMass)
                .sum() * costPerKg;
        System.out.println("Russia logistic cost: " + russiaLogisticCost + "$");

        double mongoliaLogisticCost = carsByCountry.get("Mongolia").stream()
                .mapToInt(Car::getMass)
                .sum() * costPerKg;
        System.out.println("Mongolia logistic cost: " + mongoliaLogisticCost + "$");

        double sumLogisticCost = turkmenistanLogisticCost + uzbekistanLogisticCost + kazakhstanLogisticCost
                + kyrgyzstanLogisticCost + russiaLogisticCost + mongoliaLogisticCost;
        System.out.println("Total logistic cost: " + sumLogisticCost + "$");
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();

        List<Flower> sortedFlowers = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Flower::getWaterConsumptionPerDay).reversed())
                .filter(f -> f.getCommonName().matches("[C-S].*"))
                .filter(flower -> flower.isShadePreferred() && (flower.getFlowerVaseMaterial().contains("Glass") || flower.getFlowerVaseMaterial().contains("Aluminum") || flower.getFlowerVaseMaterial().contains("Steel")))
                .toList();

        double totalCost = sortedFlowers.stream()
                .mapToDouble(flower -> flower.getPrice() + (flower.getWaterConsumptionPerDay() * 5  * 365 * 1.39 / 1000))
                .sum();

        System.out.println("The total cost of maintaining all the plants over 5 years is: $" + totalCost);
    }



    private static void task16() throws IOException {
        List<Flower> flowers = Util.getFlowers();

        List<Flower> selectedFlowers = flowers.stream()
                .sorted(Comparator.comparingInt(Flower::getPrice))
                .limit(100)
                .sorted(Comparator.comparingDouble(Flower::getWaterConsumptionPerDay))
                .limit(50)
                .filter(f -> f.getFlowerVaseMaterial().contains("Plastic"))
                .collect(Collectors.toList());

        int totalCost = selectedFlowers.stream()
                .mapToInt(Flower::getPrice)
                .sum();

        System.out.println("Total cost of selected flowers: $" + totalCost);
    }
}