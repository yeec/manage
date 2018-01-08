package bros.manage;

import java.util.HashMap;
import java.util.Map;

import bros.manage.entity.ResponseData;

public class test {

	public static void main(String[] args) {
		ResponseData data = new ResponseData();
		data.setReturnCode("0000");
		data.setReturnMsg("成功");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "卫燕成");
		data.setReturnData(map);
		System.out.println(data.toString());

	}

}
