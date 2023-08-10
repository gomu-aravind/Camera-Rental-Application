package phase01;

import java.util.*;

public class CameraMain {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		//Creating data for app which contains some data 
		CameraOperations.createData();
		
		
		//Setting up of the user object to begin login process
		UserDetails user=new UserDetails();
		user.setName("admin");
		user.setPassword("admin123");
		user.setWalletAmount(13000);
		user.userData();
		
		
		
		String name,passwd;
		boolean validate=true;
		while(validate) {
			System.out.println("-------------------------------------------");
			System.out.println("\tWELCOME TO CAMERA RENTAL APP");
			System.out.println("-------------------------------------------");
			System.out.println("PLEASE LOGIN TO CONTINUE");
			System.out.println("USERNAME");
			name=sc.next();
			System.out.println("PASSWORD");
			passwd=sc.next();
			
			if(name.equals(user.getName()) && passwd.equals(user.getPassword())) { //SUCCESS
				validate=false;
				options(user);
				break;
			}else {                                                                //FAILURE
				System.out.println("Incorrect username/password.Please try again\n");
			}
		}
		sc.close();

	}

	private static void options(UserDetails user) {
		//LISTING OF OPTIONS
		CameraOperations co=new CameraOperations();
		System.out.println("\n\n1. MY CAMERA");
		System.out.println("2. RENT A CAMERA");
		System.out.println("3. VIEW ALL CAMERAS");
		System.out.println("4. MY WALLET ");
		System.out.println("5. EXIT");
		Scanner sc=new Scanner(System.in);
		int choice;
		choice=sc.nextInt();
		
		
		//Functionality begins
		switch(choice) {
			//1 MY CAMERA
			case 1:
			{
			subchoice(user,co);
			break;}
			
			//2) RENT A CAMERA
			case 2:{
				if(co.listEmpty()==true) {
					System.out.println("NO DATA PRESENT AT THIS MOMEMT");
					options(user);
					break;
					
				}else {
					co.showAvailableCamera(user);
					int id;
					System.out.println("ENTER THE CAMERA ID YOU WANT TO RENT");
					id=sc.nextInt();
					co.rentCamera(id,user);
					options(user);
					break;
				}
			}
			// 3) VIEW ALL CAMERAS
			case 3:{
				co.showCamera();
				options(user);
				break;
			}
			// 4) MY WALLET
			case 4:{
				System.out.println("YOUR CURRENT BALANCE IS "+user.getWalletAmount());
				boolean ch=true;
				while(ch) {
					String pick;
					double amt;
				System.out.println("DO YOU WANT TO DEPOSIT MORE AMOUNT TO YOUR WALLET?(YES/NO)");
				pick=sc.next();
				if(pick.equalsIgnoreCase("yes")) {
					System.out.println("ENTER THE AMOUNT");
					amt=sc.nextDouble();
					System.out.println("YOUR WALLET BALANCE UPDATED SUCCESSFULLY.CURRENT WALLET BALANCE - INR: "+user.update(amt));
					ch=false;
				}else if(pick.equalsIgnoreCase("no")) {
					ch=false;
				}else {
					System.out.println("CHOOSE THE GIVEN OPTION");
				}
				
				}
				options(user);
				break;
			}
			// 5) EXIT
			case 5:{
				exitOfApp();
				break;
			}
			default:{
				System.out.println("Invalid option.Redirecting to main menu");
				options(user);
				break;
			}
		}
		sc.close();
	}
	
	
	public static void subchoice(UserDetails user,CameraOperations co) {
		Scanner sc=new Scanner(System.in);
		int ch;
		System.out.println("\n1. ADD");
		System.out.println("2. REMOVE");
		System.out.println("3. VIEW MY CAMERAS");
		System.out.println("4. GO TO PREVIOUS MENU");
		ch=sc.nextInt();
		switch(ch) {
		//i) ADD
		case 1:{
			String brand,model;
			double price;
			System.out.println("ENTER THE CAMERA NAME");
			brand=sc.next();
			System.out.println("ENTER THE MODEL");
			model=sc.next();
			System.out.println("ENTER THE PRICE PER DAY");
			price=sc.nextDouble();
			Camera c=new Camera(brand,model,price);
			co.addCamera(c);
			user.registry(c.getCameraId());
			System.out.println("YOUR CAMERA HAS BEEN ADDED TO THE LIST");
			subchoice(user,co);
			break;
		}
		//ii) REMOVE
		case 2:{
			int remove;
			co.showUserCamera(user);
			System.out.println("ENTER THE CAMERA ID TO REMOVE");
			remove=sc.nextInt();
			if(user.getUserId().contains(remove)) {
				if(co.removeUserCamera(remove, user)) {
					System.out.println("CAMERA SUCCESSFULLY REMOVED FROM THE LIST");
				}else {
					System.out.println("CAMERA IS RENTED SO IT CAN'T BE REMOVED");
				}
			}else {
				System.out.println("PLEASE ENTER THE ID IN YOUR ACCOUNT");
			}
			subchoice(user,co);
			break;
			
		}
		//iii) VIEW MY CAMERAS
		case 3:{
			co.showUserCamera(user);
			subchoice(user,co);
			break;
		}//iv) GO TO PREVIOUS MENU
		case 4:{
			options(user);
			break;
			}
		default:{
			System.out.println("Invalid option.Redirecting to menu");
			subchoice(user,co);
			break;
			}
		}
	}
	
	private static void exitOfApp() {
		System.out.println("CLOSING THE APPLICATION");
		System.out.println("THANK YOU! VISIT AGAIN ");
	}
	

}
