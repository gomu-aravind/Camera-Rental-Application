package phase01;

import java.util.*;

public class CameraOperations {
	
	
	static List<Camera> cameraList=new ArrayList<Camera>();
	
	//Adding the camera object to the list so that it can be used mainly for viewing and other operations
	public void addCamera(Camera c) {
		cameraList.add(c);
	}
	
	
	//Populating some data to the application to show that it contains some list
	public static void createData() {
		CameraOperations co=new CameraOperations();
		String brands[]= {"Samsung","Sony","Nokia","Gopro","Kodak","Nikon","Sigma","Casio","Ricoch","Samsung"};
		String models[]= {"SA123","SO87","NO24","GO60","KA234","NI10","SI02","CI913","RI36","SA87"};
		double price[]= {4500,5000,6000,6500,20000,5000,10000,15000,23000,7000};
		int id[]= {10,7,9,2,8,5,6,3,1,4};
		Camera c[]=new Camera[brands.length];

		for(int i=0;i<brands.length;i++) {
			c[i]=new Camera(id[i],brands[i],models[i],price[i]);
			co.addCamera(c[i]);
			if(i==1 || i==3) {
				c[i].setStatus("Rented");
			}
		}
	}
	

//=================================================================================================================
//SHOWING TASKS
	
	//To show all cameras
	public void showCamera() {
		Collections.sort(cameraList, new SortByCameraId());
		System.out.println("===================================================================");
		System.out.println("\n   CAMERA-ID    BRAND          MODEL    PRICE(PER DAY)      STATUS");
		System.out.println("===================================================================");
		for(Camera i:cameraList) {
			System.out.print("\t"+i.getCameraId()+"\t"+i.getBrand()+"\t\t"+i.getModel()+"\t"+i.getRentPerDay()+"\t\t"+i.getStatus()+"\n");
		}
		System.out.println("===================================================================");
	}
	
	
	//To show user camera
	public void showUserCamera(UserDetails u) {
		Collections.sort(u.getUserId());
		int length=u.getUserId().size();
		System.out.println("===================================================================");
		System.out.println("\n   CAMERA-ID    BRAND          MODEL    PRICE(PER DAY)      STATUS");
		System.out.println("===================================================================");
		for(int i=0;i<length;i++) {
			for(Camera cam:cameraList) {
				if(cam.getCameraId()==u.getUserId().get(i)) {
					System.out.print("\t"+cam.getCameraId()+"\t"+cam.getBrand()+"\t\t"+cam.getModel()+"\t"+cam.getRentPerDay()+"\t\t"+cam.getStatus()+"\n");
					}
				}
			}
		System.out.println("===================================================================");
		}
	
	//To show available cameras
			public void showAvailableCamera(UserDetails user) {
				Collections.sort(cameraList,new SortByCameraId());
				Iterator<Camera> it=cameraList.iterator();
				Camera c;
				System.out.println("===================================================================");
				System.out.println("\n   CAMERA-ID    BRAND          MODEL    PRICE(PER DAY)      STATUS");
				System.out.println("===================================================================");
				while(it.hasNext()) {
					c=(Camera) it.next();
					if(c.getStatus().equals("Available")) {
						System.out.print("\t"+c.getCameraId()+"\t"+c.getBrand()+"\t\t"+c.getModel()+"\t"+c.getRentPerDay()+"\t\t"+c.getStatus()+"\n");
					}
				}
				System.out.println("===================================================================");
			}
	
//=================================================================================================================
//REMOVING TASKS	
	
	//To remove camera from user
	public boolean removeUserCamera(int key,UserDetails user) {
		int length=user.getUserId().size();
		for(int i=0;i<length;i++) {
			if(user.getUserId().get(i)==key) {
				if(checkStatus(key)) {
					user.getUserId().remove(i);
					return true;
				}
			}
				
			
		}
		return false;
		
	}
	
	
	
	
	
//=================================================================================================================
//CHECKING TASKS
	
	//To check camera status
	public boolean checkStatus(int l) {
		Iterator<Camera> it=cameraList.iterator();
		Camera c;
		while(it.hasNext()) {
			c=(Camera)it.next();
			if(c.getStatus().equals("Rented") && c.getCameraId()==l) {
				return false;
			}
		}
		return true;
	}


	//To check camera id
	public boolean checkCameraId(int id) {
		for(Camera c:cameraList) {
			if(c.getCameraId()==id) {
				return true;
			}
		}
		return false;
	}
	
	
	//To check whether the list of camera is available
	public boolean listEmpty() {
		int count=0;{
			for(Camera c:cameraList) {
				if(c.getStatus().equals("Available")) {
					count++;
				}
			}
			if(count==0)return true;
			return false;
		}
	}
	
	
	//To check for the rent and wallet
		public void rentCamera(int id,UserDetails user) {
			Iterator<Camera> it=cameraList.iterator();
			Camera c;
			//Check if the id matches rented or not
			boolean valid=true;
			
			
				//get the wallet details and check if it is possible for transaction
				while(it.hasNext()) {
					
					if(checkStatus(id)==false || checkCameraId(id)==false) {
						System.out.println("THE ID IS NOT IN THE SHOWN LIST");
						valid=false;
						break;
					}
					else {
						c=(Camera)it.next();
						if(c.getCameraId()==id && checkStatus(id)==true) {
							if(c.getRentPerDay()<=user.getWalletAmount()) {
								user.setWalletAmount(user.getWalletAmount()-c.getRentPerDay());
								c.setStatus("Rented");
								user.registry(id);
								System.out.println("YOUR TRANSACTION FOR CAMERA - "+c.getBrand()+" "+c.getModel()+" with rent INR: "+c.getRentPerDay()+" HAS SUCCESSFULLY COMPLETED");
								valid=false;
								break;
								}
							}
						}
					}
				if(valid)System.out.println("YOUR WALLET AMOUNT IS INSUFFICIENT TO RENT THE CAMERA.PLEASE DEPOSIT THE AMOUNT TO YOUR WALLET");
			}
		
//=================================================================================================================
		
}

