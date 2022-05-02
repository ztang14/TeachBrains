package com.onlineShop.view;

import java.util.Scanner;

import com.onlineShop.controller.LoginController;
import com.onlineShop.entities.Profile;

public class MenuView {

//  Profile userProfile = null;
  LoginController loginController = new LoginController();
  Scanner scanner = new Scanner(System.in);
  
  public void showMenu() {
	  
	System.out.println("********Menu Page **********");
	System.out.println("Enter 1 to Login, 2 to Register, 3 to Exit");
	  
	int option = scanner.nextInt();
	
	
	if(option == 1) {
		loginView();
	}
	else if(option == 2) {
       registerView();
       showMenu();
	}
	else {
		System.out.println("Invalid option is choosen, Please try again...");
		showMenu();
	}
  }
  public void loginView() {
	  System.out.println("-------------Login Page --------");
	  System.out.println("Enter the Username");
      String username = scanner.next();
		
	  System.out.println("Enter the Password");
	  String password = scanner.next();
		
	  if(loginController.login(username, password)) {
		System.out.println("Success Login..");
	  }
	  else {
		System.out.println("Login Failed..");
	  }  
  }
  public void registerView(){
		System.out.println("----------Register Page ---------");
		System.out.println("Enter the Name");
		String name = scanner.next();
		
		System.out.println("Enter the Age");
		int age = scanner.nextInt();
		
		System.out.println("Enter the Mobile");
		String mobile = scanner.next();
		
		System.out.println("Enter the Address");
		String address = scanner.next();
		
		System.out.println("Enter the Email");
		String email = scanner.next();
		
		System.out.println("Enter the Username");
		String username = scanner.next();
		
		System.out.println("Enter the Password");
		String password = scanner.next();
		
		System.out.println("Confirm the Password");
		String cfPassword = scanner.next();
		
		try{
			if(loginController.register(name, age, mobile, address, email, username, password, cfPassword)) {
			  System.out.println("Successful Regsiter..");
		    }
		    else {
			  System.out.println("Regsiter Failed..");
		    }
		}
		catch(Exception ex) 
		{
		  System.out.println(ex);	
		}
  }
}
