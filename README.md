# Swagger Demo with Spring MVC
## 1.Swagger

[Swagger](http://swagger.io/)
### 1.Add maven dependencies
```sh
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.4.4</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.4.4</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.4.4</version>
        </dependency>

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.2.2</version>
        </dependency>

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.2.2</version>
        </dependency>

        <dependency>
            <groupId>io.swagger</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>1.5.3</version>
        </dependency>

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-staticdocs</artifactId>
            <version>2.2.2</version>
            <scope>test</scope>
        </dependency>
}
```
## 2.Create config class and add bean
e.g: SwaggerConfig.java
```sh
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"pins.philips.swagger"})
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "Swagger Demo",
                "SwaggerDemo controller description",
                "1.0",
                "xinxuan.dai@philips.com",
                "My Apps API Licence Type",
                "My Apps API License URL","");
        return apiInfo;
    }
}
```
```sh
<bean class="pins.philips.swagger.config.SwaggerConfig"></bean>
```
## 3.Copy dist directory in swagger-ui to project and modify index.html
[swagger-ui](https://github.com/swagger-api/swagger-ui)

Change url in index.html

e.g
```sh
url = "/{projectname}/v2/api-docs"
```

Don't forget to add static resources mapping!
```sh
<!--//所有swagger目录的访问，直接访问location指定的目录-->
    <mvc:resources mapping="/swagger/**" location="/WEB-INF/swagger/"/>
```
## 4.Use annotation in Controller class
e.g
```sh
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
```
## 5.Start server and explore index.html





