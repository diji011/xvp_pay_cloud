package com.maming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class HtAuthFilter extends WebMvcConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(HtAuthFilter.class);
    @Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowCredentials(true)// 就是这个啦
				.allowedMethods("GET", "POST", "OPTIONS").maxAge(7200);
	}

	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration authInterceptor = registry.addInterceptor(new AuthInterceptor());
		// 拦截配置
		authInterceptor.addPathPatterns("/user/**");
		authInterceptor.addPathPatterns("/szking/**");
		authInterceptor.addPathPatterns("/userInfo/**");
		authInterceptor.addPathPatterns("/common/**");
		authInterceptor.addPathPatterns("/pri_fund/**");
		authInterceptor.addPathPatterns("/demo/**");
		authInterceptor.addPathPatterns("/pub_fund/pubCollectList");
		// 排除配置
		authInterceptor.excludePathPatterns("/user/getAuthCode");
		authInterceptor.excludePathPatterns("/user/login");
		authInterceptor.excludePathPatterns("/user/registry");
		authInterceptor.excludePathPatterns("/user/passwordReset");
		authInterceptor.excludePathPatterns("/user/getHotSubjectFund");
		authInterceptor.excludePathPatterns("/user/getHotSaleFund");
		authInterceptor.excludePathPatterns("/user/newsList");
		authInterceptor.excludePathPatterns("/user/getLoginInfo");
		authInterceptor.excludePathPatterns("/user/checkIsLogin");
		authInterceptor.excludePathPatterns("/user/newsRecommend");
		authInterceptor.excludePathPatterns("/user/getNewsDetail");
		authInterceptor.excludePathPatterns("/user/getAgreement");
		authInterceptor.excludePathPatterns("/user/chekPhoneCode");
		authInterceptor.excludePathPatterns("/user/getFundShow");
		authInterceptor.excludePathPatterns("/common/getSystemConfig");
		authInterceptor.excludePathPatterns("/common/getBanner");
		authInterceptor.excludePathPatterns("/common/getTemplateFileList");
		authInterceptor.excludePathPatterns("/common/getPublicKey");
		authInterceptor.excludePathPatterns("/common/getPrivateFileUrl");
		authInterceptor.excludePathPatterns("/common/upload");
		authInterceptor.excludePathPatterns("/common/market_data");
		authInterceptor.excludePathPatterns("/userInfo/sendPhoneCode");
//		authInterceptor.excludePathPatterns("/szking/account/bankList");
//		authInterceptor.excludePathPatterns("/szking/account/bankSendCode");
//		authInterceptor.excludePathPatterns("/szking/account/appCreateAccount");
		authInterceptor.excludePathPatterns("/userInfo/emailAuthSubmit");
		authInterceptor.excludePathPatterns("/userInfo/emailAuthConfirm");
//		authInterceptor.excludePathPatterns("/seller/seller/login/check");
//		authInterceptor.excludePathPatterns("/seller/index.html");
//		InterceptorRegistration paramInterceptor = registry.addInterceptor(new ParamInterceptor());
//		paramInterceptor.addPathPatterns("/pri_fund/**");
	}

	private class AuthInterceptor extends HandlerInterceptorAdapter {
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			if (request.getMethod().equals("OPTIONS")) {
				return true;
			}
			return true;
		}
	}
	private void returnJson(HttpServletResponse response, String json) throws Exception{
		PrintWriter writer = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			writer = response.getWriter();
			writer.print(json);

		} catch (IOException e) {
			logger.error("response error",e);
		} finally {
			if (writer != null)
				writer.close();
		}
	}
}