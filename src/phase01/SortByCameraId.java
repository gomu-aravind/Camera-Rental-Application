package phase01;

import java.util.Comparator;

public class SortByCameraId implements Comparator<Camera>{

	@Override
	public int compare(Camera o1, Camera o2) {
		return o1.getCameraId()-o2.getCameraId();
	}
	
	
}
