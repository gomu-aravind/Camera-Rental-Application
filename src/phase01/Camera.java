package phase01;

public class Camera {
	private static int cameraCount=0;
	private int cameraId;
	private String brand;
	private String model;
	private double rentPerDay;
	private String status;
	
	public Camera(int cameraId,String brand, String model, double rentPerDay) {
		this.cameraId=cameraId;
		this.brand = brand;
		this.model = model;
		this.rentPerDay = rentPerDay;
		this.status="Available";
		++cameraCount;
	}
	
	public Camera(String brand, String model, double rentPerDay) {
		this.cameraId=++cameraCount;
		this.brand = brand;
		this.model = model;
		this.rentPerDay = rentPerDay;
		this.status="Available";
	}

	public static void dec() {
		cameraCount--;
	}
	public int getCameraId() {
		return cameraId;
	}
	public void setCameraId(int cameraId) {
		this.cameraId = cameraId;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	
	public double getRentPerDay() {
		return rentPerDay;
	}
	public void setRentPerDay(double rentPerDay) {
		this.rentPerDay = rentPerDay;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
