import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

import models.RentalAgreement;
import models.ToolCode;
import util.Validator;

/**
 * The main class that runs the tool checkout system.
 * It takes user input and generates a rental agreement 
 * for each tool the user check out.
 */
public class Main {

	public static void main(String[] args) {
		RentalAgreement.Builder rentalAgreementBuilder = new RentalAgreement.Builder();
		Scanner userInput = new Scanner(System.in);
		System.out.println("Welcome to the Tool Checkout System!");
		String inputOption = "";
		boolean continueCheckout = true;
		while (continueCheckout) {
			
			// Entering tool code
			System.out.print("Enter the tool code: ");
			inputOption = userInput.nextLine();
			Optional<ToolCode> toolCode = Validator.validateToolCode(inputOption);
			while (toolCode.isEmpty()) {
				System.out.print("Invalid tool code, please try again: ");
				inputOption = userInput.nextLine();
				toolCode = Validator.validateToolCode(inputOption);
			}
			rentalAgreementBuilder.setToolCode(toolCode.get());
			
			// Entering checkout out date
			System.out.print("Enter the check out date in the format MM/DD/YY: ");
			inputOption = userInput.nextLine();
			Optional<LocalDate> checkOutDate = Validator.validateCheckOutDate(inputOption);
			while (checkOutDate.isEmpty()) {
				System.out.print("Invalid check out date, please try again: ");
				inputOption = userInput.nextLine();
				checkOutDate = Validator.validateCheckOutDate(inputOption);
			}
			rentalAgreementBuilder.setCheckOutDate(checkOutDate.get());
			
			// Entering rental days amount
			System.out.print("Enter the number of rental days: ");
			inputOption = userInput.nextLine();
			Optional<Integer> rentalDays = Validator.validateRentalDays(inputOption);
			while (rentalDays.isEmpty()) {
				System.out.print("Invalid number of rental days, please enter a number greater than 1: ");
				inputOption = userInput.nextLine();
				rentalDays = Validator.validateRentalDays(inputOption);
			}
			rentalAgreementBuilder.setRentalDays(rentalDays.get());
			
			// Entering discount amount
			System.out.print("Enter discount: ");
			inputOption = userInput.nextLine();
			Optional<Integer> discount = Validator.validateDiscount(inputOption);
			while (discount.isEmpty()) {
				System.out.print("Invalid discount amount, please enter a whole number between 0 and 100: ");
				inputOption = userInput.nextLine();
				discount = Validator.validateDiscount(inputOption);
			}
			rentalAgreementBuilder.setDiscountPercent(discount.get());
			
			// Asking whether the user would like to print the rental agreement
			System.out.print("Would you like to print the rental agreement? (Y/n): ");
			inputOption = userInput.nextLine();
			Optional<Boolean> shouldPrint = Validator.validateYesNo(inputOption);
			while (shouldPrint.isEmpty()) {
				System.out.print("Invalid input, please try again: ");
				inputOption = userInput.nextLine();
			}
			RentalAgreement rentalAgreement = rentalAgreementBuilder.build();
			if (shouldPrint.get().booleanValue()) {
				System.out.print(rentalAgreement.toString());
			}
			
			// Asking the user if they would like to checkout out another tool, 
			// which will repeat the process
			System.out.print("Would you like to check out another tool? (Y/n): ");
			inputOption = userInput.nextLine();
			Optional<Boolean> shouldContinue = Validator.validateYesNo(inputOption);
			while (shouldContinue.isEmpty()) {
				System.out.print("Invalid input, please try again: ");
				inputOption = userInput.nextLine();
			}
			continueCheckout = shouldContinue.get();
		}
		
		System.out.println("Thanks for using the tool checkout system!");
		userInput.close();
	}

}
