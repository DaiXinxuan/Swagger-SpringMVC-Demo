package pins.philips.swagger.json;

public class ReturnJson {
	private boolean status = false;
	private String errMsg;
	private Object jsonString;
	
	public ReturnJson(boolean status){
		this.status = status;
	}
	
	public ReturnJson(){
		
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public Object getJsonString() {
		return jsonString;
	}
	public void setJsonString(Object jsonString) {
		this.jsonString = jsonString;
	}
}
