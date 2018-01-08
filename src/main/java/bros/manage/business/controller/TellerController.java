package bros.manage.business.controller;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import bros.manage.base.controller.ManageNoSessionController;
import bros.manage.business.service.ITellerService;
import bros.manage.entity.ResponseData;
import ch.qos.logback.classic.Logger;

@RestController
public class TellerController extends ManageNoSessionController{

	private static final Log logger = LogFactory.getLog(TellerController.class);
	@Autowired
	private ITellerService tellerService;
	
	@GetMapping(value="/getTeller/{id}")
	public ResponseData getTeller(@PathVariable String id){
		try{
			Map<String, Object> result = tellerService.getTellerById(id);

			return success(result);
		}catch(Exception e){
			return error(e);
		}
	}
}
