package com.tsbg.ecosys.config;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author prince
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

   @Bean
   public Docket createRestApi() {
       ParameterBuilder tokenPar = new ParameterBuilder();
       List<Parameter> pars = new ArrayList<>();
       tokenPar.name("Token").description("Token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
       pars.add(tokenPar.build());
       Predicate<RequestHandler> swaggerSelector = RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class);
       return new Docket(DocumentationType.SWAGGER_2)
               .host("localhost:80")
               .securitySchemes(newArrayList(new ApiKey[]{this.apiKey()}))
               //禁止使用开关
               .enable(true)
               .globalOperationParameters(pars)
               .apiInfo(apiInfo())
               .select()
               .apis(swaggerSelector)
               .paths(PathSelectors.any())
               .build();
   }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Muscat Restful API")
                .description("构建muscat restful api")
                .contact(new Contact("prince","官网地址url","email地址"))
                .version("1.0.0")
                .build();
    }

    ApiKey apiKey() {
        return new ApiKey("sessionId", "sessionId", "header");
    }
}
