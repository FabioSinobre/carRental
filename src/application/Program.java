package application;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.service.BrazilTaxService;
import model.service.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("Insira os dados do aluguel");
		System.out.println("Modelo do carro: ");
		String carModel = sc.nextLine();
		System.out.println("Digite a data da retirada do veículo (dd/MM/aaaa HH:mm): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.println("Digite a data de devolução do veículo (dd/MM/aaaa HH:mm): ");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Entre com o valor por hora:");
		double pricePerHour = sc.nextDouble();
		System.out.print("Entre com o valor por ano:");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		rentalService.processInvoice(cr);
		
		System.out.println();
		System.out.println("Fatura:");
		System.out.println("Pagamento básico: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Imposto: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Pagamento total: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		
		sc.close();

	}

}
