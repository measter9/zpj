package pl.pollub.zpj;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class XMLHandler {
    public static void writeKampersToXml(List<Kamper> kampers, String filename) {
        try {
            JAXBContext context = JAXBContext.newInstance(KamperListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            KamperListWrapper wrapper = new KamperListWrapper();
            wrapper.setKampers(kampers);
            marshaller.marshal(wrapper, new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static List<Kamper> readKampersFromXml(String filename) throws InvalidDataException {
        try {
            JAXBContext context = JAXBContext.newInstance(KamperListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            KamperListWrapper wrapper = (KamperListWrapper) unmarshaller.unmarshal(new File(filename));
            return wrapper.getKampers();
        } catch (JAXBException e) {
            throw new InvalidDataException("Could not read XML file: " + e.getMessage());
        }
    }

    private static void validateOrder(Order order) throws InvalidDataException {
        if (order.getOrderId() <= 0) {
            throw new InvalidDataException("Invalid orderID: " + order.getOrderId());
        }
        if (order.getKamper() == null) {
            throw new InvalidDataException("Kamper cannot be null for orderID: " + order.getOrderId());
        }
        if (order.getDuration() <= 0) {
            throw new InvalidDataException("Invalid duration");
        }
    }
    public static void writeOrderToXml(List<Order> orders, String filename) {
        OrderListWrapper wrapper = new OrderListWrapper();
        List<Order> validOrders = new ArrayList<>();
        for (Order order : orders) {
            try {
                validateOrder(order);
                validOrders.add(order);
            } catch (InvalidDataException e) {
                System.err.println("Could not create order: " + e.getMessage());
            }
        }
        if (validOrders.isEmpty()) {
            System.err.println("No valid orders to write to XML.");
            return;
        }
        try {
            JAXBContext context = JAXBContext.newInstance(OrderListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            wrapper.setOrders(validOrders);
            marshaller.marshal(wrapper, new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static List<Order> readOrdersFromXml(String filename) throws InvalidDataException {
        try {
            JAXBContext context = JAXBContext.newInstance(OrderListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            OrderListWrapper wrapper = (OrderListWrapper) unmarshaller.unmarshal(new File(filename));
            return wrapper.getOrders();
        } catch (JAXBException e) {
            throw new InvalidDataException("Could not read XML file: " + e.getMessage());
        }
    }
}