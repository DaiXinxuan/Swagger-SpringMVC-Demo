package pins.philips.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import pins.philips.swagger.entity.UsersEntity;
import pins.philips.swagger.json.ReturnJson;
import pins.philips.swagger.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/user")
@Api(value = "管理用户类的后台逻辑")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	@ApiOperation(value = "Upload image", httpMethod = "POST", response = ReturnJson.class, notes = "上传图片")
	public @ResponseBody ReturnJson uploadFile(HttpServletRequest request){
		ReturnJson returnJson = new ReturnJson(false);
		
		//图片上传约束定义
        long maxSize = 2100000;
        String allowExts="jpg,png,gif";
        String basePath="d:/testFileUpload";
		
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){
        	MultipartHttpServletRequest mutiRequest = (MultipartHttpServletRequest) request;
        	Map<String,MultipartFile> map = mutiRequest.getFileMap();
        	for(String key:map.keySet()){
        		MultipartFile multipartFile = map.get(key);
        		String filename = multipartFile.getOriginalFilename();
        		String fileExt = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        		if(multipartFile.getSize()>maxSize){
        			returnJson.setErrMsg("文件大小超过上传限制！");
                	return returnJson;
        		}else if(!Arrays.asList(allowExts.split(",")).contains(fileExt)){
        			returnJson.setErrMsg("文件格式不符合要求!");
                	return returnJson;
        		}
        		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                try {
					FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(basePath, newFileName));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					returnJson.setErrMsg("文件写入失败");
                	return returnJson;
				}
        	}
        }else{
        	returnJson.setErrMsg("没有检查到文件！");
        	return returnJson;
        }
		returnJson.setStatus(true);
		return returnJson;
	}
	
	@RequestMapping(value="/get/{userId}", method = RequestMethod.GET)
	@ApiOperation(value = "Get user by id", httpMethod = "GET", response = ReturnJson.class, notes = "根据用户Id获取用户信息")
	public @ResponseBody ReturnJson getById(@ApiParam(value = "user id", required = true)@PathVariable("userId") int id){
		ReturnJson returnJson = new ReturnJson(false);
		UsersEntity userEntity = userService.get(id);
		if(userEntity != null){
			returnJson.setJsonString(userEntity);
		}else{
			returnJson.setErrMsg("id有误，未查询到数据");
			return returnJson;
		}
		returnJson.setStatus(true);
		return returnJson;
	}
	
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	@ApiOperation(value = "Get all users", httpMethod = "GET", response = ReturnJson.class, notes = "获取全部用户信息")
	public @ResponseBody ReturnJson getAll(){
		ReturnJson returnJson = new ReturnJson(false);
		ArrayList<UsersEntity> list = new ArrayList<UsersEntity>();
		list = (ArrayList<UsersEntity>) userService.getAll();
		if(list!=null){
			returnJson.setJsonString(list);
		}else{
			returnJson.setErrMsg("数据库查询出错!");
			return returnJson;
		}
		returnJson.setStatus(true);
		return returnJson;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	@ApiOperation(value = "Add new user", httpMethod = "POST", response = ReturnJson.class, notes ="新增一个用户")
	public @ResponseBody ReturnJson add(@ApiParam(value = "Just add name and password, do not add id!") @RequestBody UsersEntity userEntity){
		ReturnJson returnJson = new ReturnJson(false);
		if (userEntity.getName() == null || userEntity.getPassword() == null) {
			returnJson.setErrMsg("参数不足");
			return returnJson;
		}
		try {
			userEntity.setName(new String(userEntity.getName().getBytes("iso-8859-1"),"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(userEntity.getName());
		try {
			userService.add(userEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnJson.setErrMsg(e.toString());
			return returnJson;
		}
		returnJson.setStatus(true);
		return returnJson;
	}

	@RequestMapping(value="/delete", method = RequestMethod.GET)
	@ApiOperation(value = "Delete a user", httpMethod = "GET", response = ReturnJson.class, notes = "删除一个用户")
	public @ResponseBody ReturnJson delete(@ApiParam("User's id") @RequestParam int index){
		ReturnJson returnJson = new ReturnJson(false);
		try {
			userService.delete(index);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnJson.setErrMsg(e.toString());
			return returnJson;
		}
		returnJson.setStatus(true);
		return returnJson;
	}

	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ApiOperation(value = "Update a user's info", httpMethod = "POST", response = ReturnJson.class, notes = "更改某个用户的信息")
	public @ResponseBody ReturnJson update(@ApiParam("Add id, name and password") @RequestBody UsersEntity userEntity){
		ReturnJson returnJson = new ReturnJson(false);
		if (userEntity.getPassword() == null||userEntity.getName() == null||userEntity.getId() == 0){
			returnJson.setErrMsg("参数不足");
			return returnJson;
		}
		try {
			userEntity.setName(new String(userEntity.getName().getBytes("iso-8859-1"),"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			userService.update(userEntity.getId(), userEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnJson.setErrMsg("更新失败！"+e.toString());
			return returnJson;
		}
		returnJson.setStatus(true);
		return returnJson;
	}
}
