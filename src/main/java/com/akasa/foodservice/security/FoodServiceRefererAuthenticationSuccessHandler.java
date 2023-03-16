package com.akasa.foodservice.security;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class FoodServiceRefererAuthenticationSuccessHandler  extends
	SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	public FoodServiceRefererAuthenticationSuccessHandler() {
		super();
		setUseReferer(true);
	}

}
