import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

import models.ToolCode;
import util.Validator;

public class Main {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Welcome to the Tool Checkout System!");
		String inputOption = "";
		while (!inputOption.equals("n")) {
			
			System.out.print("Enter the tool code: ");
			inputOption = userInput.nextLine();
			Optional<ToolCode> toolCode = Validator.validateToolCode(inputOption);
			while (toolCode.isEmpty()) {
				System.out.print("Invalid tool code, please try again: ");
				inputOption = userInput.nextLine();
				toolCode = Validator.validateToolCode(inputOption);
			}
			
			System.out.print("Enter the check out date in the format MM/DD/YYYY: ");
			inputOption = userInput.nextLine();
			Optional<LocalDate> checkOutDate = Validator.validateCheckOutDate(inputOption);
			while (checkOutDate.isEmpty()) {
				System.out.print("Invalid check out date, please try again: ");
				inputOption = userInput.nextLine();
				checkOutDate = Validator.validateCheckOutDate(inputOption);
			}
			
			System.out.print("Enter the number of rental days: ");
			inputOption = userInput.nextLine();
			Optional<Integer> rentalDays = Validator.validateRentalDays(inputOption);
			while (rentalDays.isEmpty()) {
				System.out.print("Invalid number of rental days, please enter a number greater than 1: ");
				inputOption = userInput.nextLine();
				rentalDays = Validator.validateRentalDays(inputOption);
			}
			
			System.out.print("Enter discount: ");
			inputOption = userInput.nextLine();
			Optional<Integer> discount = Validator.validateDiscount(inputOption);
			while (discount.isEmpty()) {
				System.out.print("Invalid discount amount, please enter a whole number between 0 and 100: ");
				inputOption = userInput.nextLine();
				discount = Validator.validateDiscount(inputOption);
			}
			
			System.out.print("Would you like to print the rental agreement? (Y/n): ");
			inputOption = userInput.nextLine();
			while (!Validator.validateYesNo(inputOption)) {
				System.out.print("Invalid input, please try again: ");
				inputOption = userInput.nextLine();
			}
			
			System.out.print("Would you like to check out another tool? (Y/n): ");
			inputOption = userInput.nextLine();
			while (!Validator.validateYesNo(inputOption)) {
				System.out.print("Invalid input, please try again: ");
				inputOption = userInput.nextLine();
			}
			
			System.out.println("Thanks for using the tool checkout system!");
		}
		userInput.close();
	}

}
