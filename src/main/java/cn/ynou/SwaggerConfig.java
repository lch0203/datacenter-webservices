package cn.ynou;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				.build()
				.tags(new Tag("untagged", "This is untagged"), tags())
				.apiInfo(apiInfo())
				.protocols(protocols())
				.securitySchemes(securitySchemes())
				.securityContexts(securityContexts());
	}
	
	private Tag[] tags() {
		List<Tag> tags = new ArrayList<>();
		tags.add(new Tag("schoolrolls", "schoolrolls operation"));
		tags.add(new Tag("users", "users operation"));
		tags.add(new Tag("listschoolinfo", "获取各分校学习点坐标及描述等信息"));
		return tags.toArray(new Tag[tags.size()]);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Datacenter-API Swagger Documentation")
				.description("Datacenter-API Swagger Documentation")
				.termsOfServiceUrl("http://liuchunhua.com")
				.contact(new Contact("liuchunhua", "liuchunhua.com", "lch_0203@163.com"))
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.version("2.0")
				.build();
	}
	
	private Set<String> protocols() {
		Set<String> protocols = new HashSet<>();
		protocols.add("http");
		return protocols;
	}
	
	private List<? extends SecurityScheme> securitySchemes() {
        List<SecurityScheme> authorizationTypes = Arrays.asList(new ApiKey("token", "token", "query"));
        return authorizationTypes;
    }
	
	private List<SecurityContext> securityContexts() {
		List<SecurityContext> securityContexts   = Arrays.asList(SecurityContext.builder().forPaths(Predicates.not(PathSelectors.regex("^(/error.*|/api/auth/login)$"))).securityReferences(securityReferences()).build());
		return securityContexts;
	}
	
	private List<SecurityReference> securityReferences() {
		List<SecurityReference> securityReferences = Arrays.asList(SecurityReference.builder().reference("token").scopes(new AuthorizationScope[0]).build());
		return securityReferences;
	}
	
}