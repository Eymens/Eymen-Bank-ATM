package com.eymen;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class OptionMenu {
	Scanner menuInput = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("###,##0.00 'TL'");
	HashMap<String, Customer> data = new HashMap<String, Customer>();

	public void getLogin() throws IOException {
		boolean end = false;

		String username;
		String password;
		int i = 0;

		while (i<3) {
			try {
				System.out.print("\nEnter your username: ");
				username = menuInput.next();
				//System.out.println(username);
				System.out.print("\nEnter your password: ");
				password = menuInput.next();
				//System.out.println(password);

				Iterator it = data.entrySet().iterator();

				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					Customer acc = (Customer) pair.getValue();
					if (data.containsKey(username) && password.equals(acc.getPassword())) {
						getAccountType(acc);
						end = true;
						break;
					}

					//System.out.println("username + password "+username + password);
					//System.out.println("Incoming data "+acc.getUsername() + acc.getPassword()+ " result: "+ data.containsKey(username));
				}

				if (!end) {
					System.out.println("\nWrong username or password");
					i++;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Character(s).");
			}
		}
	}

	public void getAccountType(Customer acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSelect the account you want to access: ");
				System.out.println(" Type 1 - Checkings Account");
				System.out.println(" Type 2 - Savings Account");
				System.out.println(" Type 3 - Exit");
				System.out.print("\nChoice: ");

				int selection = menuInput.nextInt();

				switch (selection) {
					case 1:
						getChecking(acc);
						break;
					case 2:
						getSaving(acc);
						break;
					case 3:
						end = true;
						break;
					default:
						System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}

	public void getChecking(Customer acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCheckings Account: ");
				System.out.println(" Type 1 - View Balance");
				System.out.println(" Type 2 - Withdraw Funds");
				System.out.println(" Type 3 - Deposit Funds");
				System.out.println(" Type 4 - Transfer Funds");
				System.out.println(" Type 5 - Exit");
				System.out.print("\nChoice: ");

				int selection = menuInput.nextInt();

				switch (selection) {
					case 1:
						System.out.println("\nCheckings Account Balance: " + moneyFormat.format(acc.getCheckingBalance()));
						break;
					case 2:
						acc.getCheckingWithdrawInput();
						break;
					case 3:
						acc.getCheckingDepositInput();
						break;

					case 4:
						acc.getTransferInput("Checkings");
						break;
					case 5:
						end = true;
						break;
					default:
						System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}

	public void getSaving(Customer acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSavings Account: ");
				System.out.println(" Type 1 - View Balance");
				System.out.println(" Type 2 - Withdraw Funds");
				System.out.println(" Type 3 - Deposit Funds");
				System.out.println(" Type 4 - Transfer Funds");
				System.out.println(" Type 5 - Exit");
				System.out.print("Choice: ");
				int selection = menuInput.nextInt();
				switch (selection) {
					case 1:
						System.out.println("\nSavings Account Balance: " + moneyFormat.format(acc.getSavingBalance()));
						break;
					case 2:
						acc.getsavingWithdrawInput();
						break;
					case 3:
						acc.getSavingDepositInput();
						break;
					case 4:
						acc.getTransferInput("Savings");
						break;
					case 5:
						end = true;
						break;
					default:
						System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}

	public void createAccount() throws IOException {
		boolean end = false;
		String id = null;
		while (!end) {
			try {
				System.out.println("\nEnter your username ");
				id = menuInput.next();
				Iterator it = data.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					if (!data.containsKey(id)) {
						end = true;
					}
				}
				if (!end) {
					System.out.println("\nThis username is already registered");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
		System.out.println("\nEnter password to be registered");
		String pass = menuInput.next();

		data.put(id, new Customer(id, pass));

		System.out.println("\nYour new account has been successfully registered!");
		//System.out.println("id pass " + id + pass);
		System.out.println("\nRedirecting to login.............");
		getLogin();
	}

	public void mainMenu() throws IOException {

		data.put("admin@gmail.com", new Customer("admin@gmail.com", "root", 1000, 0));
		data.put("eymen@gmail.com", new Customer("eymen@gmail.com", "eymen", 100000000, 7777777));
		data.put("eymen", new Customer("eymen", "eymen", 1000000, 1000000));


		boolean end = false;
		while (!end) {
			try {
				System.out.println("\n Type 1 - Login");
				System.out.println(" Type 2 - Create New Account");
				System.out.print("\nChoice: ");
				int choice = menuInput.nextInt();
				switch (choice) {
					case 1:
						getLogin();
						end = true;
						break;
					case 2:
						createAccount();
						end = true;
						break;
					default:
						System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
		System.out.println("\nThank You for using this ATM.\n");
		menuInput.close();
		System.exit(0);
	}
}
