package pins.philips.swagger.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pins.philips.swagger.entity.CompanyEntity;
import pins.philips.swagger.json.ReturnJson;
import pins.philips.swagger.service.CompanyService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by 310231492 on 2016/4/5.
 */
@Controller
@RequestMapping("/company")
@Api(value = "管理公司类的后台逻辑")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/get/{companyId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get company by id", httpMethod = "GET", response = ReturnJson.class, notes = "根据公司Id获取公司信息")
    public @ResponseBody ReturnJson getCompany(@ApiParam(value = "Company's Id")@PathVariable("companyId") int companyId) {
        ReturnJson returnJson = new ReturnJson(false);
        CompanyEntity companyEntity = companyService.getCompany(companyId);
        if(companyEntity != null){
            returnJson.setJsonString(companyEntity);
        }else{
            returnJson.setErrMsg("id有误，未查询到数据");
            return returnJson;
        }
        returnJson.setStatus(true);
        return returnJson;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "Get all companies", httpMethod = "GET", response = ReturnJson.class, notes = "获取全部公司信息")
    public @ResponseBody ReturnJson getAllCompanies() {
        ReturnJson returnJson = new ReturnJson(false);
        ArrayList<CompanyEntity> list = new ArrayList<CompanyEntity>();
        list = (ArrayList<CompanyEntity>) companyService.getAllCompanies();
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
    @ApiOperation(value = "Add new company", httpMethod = "POST", response = ReturnJson.class, notes ="新增一个公司")
    public @ResponseBody ReturnJson add(@RequestBody CompanyEntity companyEntity){
        ReturnJson returnJson = new ReturnJson(false);
        if (companyEntity.getCompany() == null || companyEntity.getType() == null) {
            returnJson.setErrMsg("参数不足!");
            return returnJson;
        }
        try {
            companyEntity.setCompany(new String(companyEntity.getCompany().getBytes("iso-8859-1"),"utf-8"));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println(companyEntity.getCompany());
        try {
            companyService.addCompany(companyEntity);
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
    @ApiOperation(value = "Update a company's info", httpMethod = "POST", response = ReturnJson.class, notes = "更改某个公司的信息")
    public @ResponseBody ReturnJson update(@RequestBody CompanyEntity companyEntity){
        ReturnJson returnJson = new ReturnJson(false);
        if (companyEntity.getCompany() == null || companyEntity.getType() == null ||companyEntity.getId() == 0) {
            returnJson.setErrMsg("参数不足!");
            return returnJson;
        }
        try {
            companyEntity.setCompany(new String(companyEntity.getCompany().getBytes("iso-8859-1"),"utf-8"));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            companyService.updateCompany(companyEntity.getId(), companyEntity);
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
