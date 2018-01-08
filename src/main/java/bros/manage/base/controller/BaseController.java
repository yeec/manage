package bros.manage.base.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import bros.manage.constants.BaseErrorCodeConstants;
import bros.manage.entity.ResponseData;
import bros.manage.exception.ServiceException;

@ControllerAdvice
public class BaseController {
	
	public static final Log logger = LogFactory.getLog(BaseController.class);

	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public ResponseData exceptionHandler(Exception ex, HttpServletRequest request){
		ResponseData result = new ResponseData();
		String errMsg = "";
		String errCode = "";
		if(ex instanceof ServiceException){
			errMsg = ((ServiceException)ex).getErrorMsg();
			errCode = ((ServiceException)ex).getErrorCode();
		}else if(ex instanceof MaxUploadSizeExceededException){
			long fileSize = ((MaxUploadSizeExceededException)ex).getMaxUploadSize();
			long sizeMb = fileSize/1024/1024;//处理成MB及
			errMsg = "您上传的文件大小不能超过"+sizeMb+"MB";
			errCode = BaseErrorCodeConstants.EBNT0002;
		}else if(ex instanceof HttpMediaTypeNotSupportedException){
			HttpMediaTypeNotSupportedException e = (HttpMediaTypeNotSupportedException) ex;
			errMsg = "不支持的MediaType:"+e.getContentType();
			errCode=BaseErrorCodeConstants.EBNT0003;
		}else if(ex instanceof HttpRequestMethodNotSupportedException){
			HttpRequestMethodNotSupportedException e = (HttpRequestMethodNotSupportedException)ex;
			errMsg = "不支持的请求方法："+e.getMethod();
			errCode = BaseErrorCodeConstants.EBNT0004;
		}else{
			errMsg = "系统异常";
			errCode = BaseErrorCodeConstants.EBNT0001;
		}
		logger.error(errCode+":"+errMsg);
		result.setReturnCode(errCode);
		result.setReturnMsg(errMsg);
		return result;
	}
	/**
	 * 返回成功
	 * @return
	 */
	public ResponseData success(){
		ResponseData result = new ResponseData();
		result.setReturnCode(BaseErrorCodeConstants.EBNT0000);
		result.setReturnMsg("交易成功");
		return result;
	}
	/**
	 * 返回成功
	 * @param map 返回数据
	 * @return
	 */
	public ResponseData success(Map<String, Object> map){
		ResponseData result = new ResponseData();
		result.setReturnCode(BaseErrorCodeConstants.EBNT0000);
		result.setReturnMsg("交易成功");
		result.setReturnData(map);
		return result;
	}
	/**
	 * 返回失败 默认
	 * @return
	 */
	public ResponseData error(){
		ResponseData result = new ResponseData();
		result.setReturnCode(BaseErrorCodeConstants.EBNT0001);
		result.setReturnMsg("系统异常");
		return result;
	}
	/**
	 * 返回失败
	 * @param errCode 错误码
	 * @param errMsg  错误信息
	 * @return
	 */
	public ResponseData error(String errCode,String errMsg){
		ResponseData result = new ResponseData();
		result.setReturnCode(errCode);
		result.setReturnMsg(errMsg);
		return result;
	}
	/**
	 * 返回失败
	 * @param e
	 * @return
	 */
	public ResponseData error(Exception ex){
		ResponseData result = new ResponseData();
		String errMsg = "";
		String errCode = "";
		if(ex instanceof ServiceException){
			errMsg = ((ServiceException)ex).getErrorMsg();
			errCode = ((ServiceException)ex).getErrorCode();
		}else{
			errMsg = "系统异常";
			errCode = BaseErrorCodeConstants.EBNT0001;
		}
		logger.error(errCode+":"+errMsg);
		result.setReturnCode(errCode);
		result.setReturnMsg(errMsg);
		return result;
	}
}
