package phase01;

import java.util.ArrayList;

public class UserDetails {
	private String name;
	private String password;
	private double walletAmount;
	private  ArrayList<Integer> userId=new ArrayList<Integer>();
	
	
	
	
	public ArrayList<Integer> getUserId() {
		return userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public double getWalletAmount() {
		return walletAmount;
	}


	public void setWalletAmount(double walletAmount) {
		this.walletAmount = walletAmount;
	}


	public  void registry(int n) {
		userId.add(n);
	}
	
	
	public double update(double amount) {
		double updateWallet=this.getWalletAmount()+amount;
		this.setWalletAmount(updateWallet);
		return updateWallet;
	}
	
	
	//Populating some data to show that the user has some list
	public void userData() {
		CameraOperations co=new CameraOperations();
		String brands[]= {"Fujitsu","Pentax","Nikon","GE"};
		String models[]= {"FU240","PE27","NIFX01","GE792"};
		double price[]= {7500,12000,19000,9000};
		int id[]= {12,11,14,13};
		Camera c[]=new Camera[brands.length];
		for(int i=0;i<brands.length;i++) {
			c[i]=new Camera(id[i],brands[i],models[i],price[i]);
			co.addCamera(c[i]);
			this.registry(c[i].getCameraId());
			if(i==1) {
				c[i].setStatus("Rented");
			}
		}
		
	}

}
