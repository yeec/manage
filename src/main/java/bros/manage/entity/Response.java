package bros.manage.entity;

/**
 * 返回结果实体
 * @author wyc
 *
 */
public class Response<T>{

	private String returnCode;
	
	private String returnMsg;
	
	private T returnData;
	
//	@Override
//    public String toString() {
//        return "Response [{returnCode=" + returnCode + ", returnMsg=" + returnMsg + ", returnData=" + returnData!=null?returnData.toString():"" + "}]";
//    }

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public T getReturnData() {
		return returnData;
	}

	public void setReturnData(T returnData) {
		this.returnData = returnData;
	}
	
}
