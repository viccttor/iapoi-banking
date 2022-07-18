//package br.com.iapoiBankingApi.doc;
//
//import org.springframework.context.annotation.Bean;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.Contact;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Arrays;
//import java.util.HashSet;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    private Contact contact() {
//        return new Contact(
//                "Victor Henrique",
//                "https://www.linkedin.com/in/viccttor/",
//                "vhsdm@gmail.com");
//    }
//
//    private ApiInfoBuilder apiInformation(){
//
//        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
//        apiInfoBuilder.title("IAPOI BANKING");
//        apiInfoBuilder.description("API voltada para estudos de Java e suas tecnologias.");
//        apiInfoBuilder.version("2.0");
//        apiInfoBuilder.termsOfServiceUrl("Termo de uso: Open Source");
//        apiInfoBuilder.license("Livre ");
//        apiInfoBuilder.licenseUrl("https://github.com/viccttor");
//        apiInfoBuilder.contact(this.contact());
//
//        return apiInfoBuilder;
//    }
// Erro
    /*
    Failed to start bean 'documentationPluginsBootstrapper'; nested exception is java.lang.NullPointerException:
    Cannot invoke "org.springframework.web.servlet.mvc.condition.PatternsRequestCondition.toString()" because the
    return value of "springfox.documentation.spi.service.contexts.Orderings.patternsCondition(springfox.documentation.
    RequestHandler)" is null
     */

//    @Bean
//    public Docket apiDetail(){
//        Docket docket = new Docket(DocumentationType.SWAGGER_2);
//        docket
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("br.com.iapoiBankingApi.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(this.apiInformation().build())
//                .consumes(new HashSet<String>(Arrays.asList("appication/json")))
//                .produces(new HashSet<String>(Arrays.asList("appication/json")));
//        return docket;
//    }

//}
