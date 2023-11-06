package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.Product;
import entities.enums.OrderStatus;
import services.OrderItem;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter client data: ");
		System.out.print("Name: ");
		String clientName = sc.nextLine();
		System.out.print("E-mail: ");
		String clientEmail = sc.nextLine();
		System.out.println("Birth date (DD/MM/YYYY): ");
		Date birthDate = formatter.parse(sc.next());
		
		Client client = new Client(clientName, clientEmail, birthDate);
		
		System.out.println("Enter order data: ");
		System.out.print("Status: ");
		OrderStatus status = OrderStatus.valueOf(sc.next());
		System.out.print("How many items to this order? ");
		int numberOfOrders = sc.nextInt();
		
		Order order = new Order(LocalDate.now(), status, client);
		
		for(int i = 0; i < numberOfOrders; i++) {
			System.out.println("Enter #" + (i + 1) + " item data: ");
			System.out.print("Product name: ");
			String productName = sc.next();
			System.out.print("Product price: ");
			Double productPrice = sc.nextDouble();
			
			Product product = new Product(productName, productPrice);
			
			System.out.print("Quantity: ");
			int quantity = sc.nextInt();
			
			OrderItem item = new OrderItem(product, quantity);
			
			order.addItem(item);
		}
		
		System.out.println("ORDER SUMMARY: ");
		System.out.println("Order moment: " + order.getMoment());
		System.out.println("Order status: " + order.getStatus());
		System.out.println("Client: " + client.getName() + "(" + client.getBirthDate() + ") - " + client.getEmail());
		System.out.println("Order items: ");
		for(OrderItem item : order.getItems()) {
			System.out.println(item.getProduct().getName() + ", Quantity: " + item.getQuantity() + ", Subtotal: $" + item.subTotal());
		}
		System.out.println("Total price: $" + order.total());
	}

}
