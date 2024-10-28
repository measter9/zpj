package pl.pollub.zpj;

import pl.pollub.zpj.models.Kamper;
import pl.pollub.zpj.models.Order;
import pl.pollub.zpj.models.Role;
import pl.pollub.zpj.models.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@SpringBootApplication
public class ZpjApplication {

    public static void main(String[] args) {
//		SpringApplication.run(ZpjApplication.class, args);
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User(0, "jan"));
        users.add(new User(1, "user"));
        users.add(new User(2, "jan"));
        users.add(new User(3, "123"));
        users.add(new User(4, "456"));
        users.add(users.get(0));
        users.add(users.get(0));
        users.add(users.get(0));

        users.get(1).setRole(Role.ADMIN);
        users.get(0).setActive(false);
        users.get(3).setActive(false);

        ArrayList<User> users2 = new ArrayList<>();
        users2.add(new User(0,"Adam"));
        users2.add(new User(1, "Jan"));
        users2.add(new User(2,"Username"));
        users2.add(new User(3,"Adam"));
        users2.add(new User(4,"Jan2"));


///1
///3.1
        List<Kamper> kamperList = new ArrayList<>();
        kamperList.add(new Kamper(1, "Kamper 1", 50.0));
        kamperList.add(new Kamper(2, "Kamper 2", 100.0));
        kamperList.add(new Kamper(3, "Kamper 3", 200.0));
        //write to XML
        XMLHandler.writeKampersToXml(kamperList, "kampers.xml");
        //read kampers from XML
        try {
            List<Kamper> readKampers = XMLHandler.readKampersFromXml("kampers.xml");
            readKampers.forEach(System.out::println);
        } catch (InvalidDataException e) {
            System.err.println(e.getMessage());
        }
        //orders
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1, kamperList.get(0),8));
        orderList.add(new Order(2, kamperList.get(1),10));
        orderList.add(new Order(3, kamperList.get(2),2));
        orderList.add(new Order(4, null,3));
        XMLHandler.writeOrderToXml(orderList, "orders.xml");

        try {
            List<Order> readOrders = XMLHandler.readOrdersFromXml("orders.xml");
            readOrders.forEach(System.out::println);
        } catch (InvalidDataException e) {
            System.err.println(e.getMessage());
        }
///2.1
        System.out.println("Aktywni Użytkownicy: \n");
        Stream<User> stream = users.stream().filter(user -> user.isActive());
        stream.forEach(System.out::println);

//		long distinctCount = users.stream().distinct().count();
//		System.out.println(distinctCount);
///2
        System.out.println("\nPowtarzający się użytkownicy \n");
        Map<User, List<User>> grouped = users.stream().collect(Collectors.groupingBy(Function.identity()));
        grouped.forEach((group, list) -> System.out.println(group + " :" + list.size()));
///3
        System.out.println("\nPowtarzający się użytkownicy \n");
        grouped = users.stream().collect(Collectors.groupingBy(Function.identity()));
        grouped.forEach((group, list) -> System.out.println(group + " :" + list.size()));
        System.out.println("\nDeaktywowanie wszystkich użytkowników w drugiej liscie \n");
        List<User> mappedUsers = users2.stream().map(user -> {
            user.setActive(false);
            return user;
        }).toList();

        mappedUsers.stream().distinct().forEach(System.out::println);

///4
        System.out.println("\nZapisanie użytkowników do pliku i odczyt \n");
		zapis(users.stream(),"users.txt");
        odczyt("users.txt").sorted((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).forEach(System.out::println);
        System.out.println();
        odczyt("users.txt").filter(user -> Objects.equals(user.getName(), "jan")).forEach(System.out::println);

        System.out.println("Zapis odczyt NIO");

        zapisNIO(users2.stream(),"users2.txt");
        odczytNIO("users2.txt").forEach(System.out::println);
    }

    public static void zapis(Stream<User> userStream, String filename) {
        try {
            FileWriter fileWriter = new FileWriter(new File(filename));
			userStream.distinct().forEach(user -> {
                try {
                    fileWriter.append(user.toString()+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
			fileWriter.close();
        } catch (IOException e) {
            System.out.println("nie znaleziono pliku");
        }
    }
    public static Stream<User> odczyt(String filename) {
        ArrayList<User> users = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename)));
            String line = null;
            while((line = bufferedReader.readLine()) != null ){
                users.add(new User(line));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return users.stream();
    }


    public static void zapisNIO(Stream<User> userStream,String filename){
        Path path = Paths.get(filename);
        var stringStream = userStream.map(user -> user.toString()).toList();
        try {
            Files.write(path, stringStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stream<User> odczytNIO(String filename){
        Path path = Paths.get(filename);
        try {
             return Files.readAllLines(path).stream().map(s ->  new User(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
