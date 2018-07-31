package javabeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ApiStatus {
	
	String status;
	
	public ApiStatus() {
	}

	public ApiStatus(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
