package app.security;

import static app.security.SecurityConstants.HEADER_STRING;
import static app.security.SecurityConstants.SECRET;
import static app.security.SecurityConstants.TOKEN_EXPIRATION_TIME;
import static app.security.SecurityConstants.TOKEN_PREFIX;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.forms.AdminLoginForm;
import app.forms.Login_Form;

/**
 * Sluzi da da JSON Web Token user-u koji pokusava da pristupi (user salje
 * username i password).
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			Login_Form user = null;
			InputStream bufferdInputStream = new BufferedInputStream(req.getInputStream());
		    bufferdInputStream.mark(0);
			try {
				user = new ObjectMapper().readValue(bufferdInputStream.readAllBytes(), Login_Form.class);
			}catch(Exception e) {
				
			}
			
		    //read your bufferdInputStream 
		    bufferdInputStream.reset();
		    //read it again
			if(user == null || user.getEmail() == null) {
				AdminLoginForm alf = new ObjectMapper().readValue(bufferdInputStream.readAllBytes(), AdminLoginForm.class);
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(alf.getUsername(),
						alf.getPassword(), Collections.emptyList());
				
				System.out.println("napravljen token");
				System.out.println(token);
				return authenticationManager.authenticate(token);
			}

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(),
					user.getPassword(), Collections.emptyList());

			return authenticationManager.authenticate(token);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) {

		String email = auth.getName();

		String token = JWT.create().withSubject(email)
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
				.sign(HMAC512(SECRET.getBytes()));

		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
}