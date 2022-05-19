package kr.co.demo.security;

import kr.co.demo.admin.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class Name : JwtRequestFilter.java
 * Description : jwt filter
 * Writer : lee.j
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private static final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);

  @Autowired
  private AdminService adminService;

  @Autowired
  private JwtUserDetailService jwtUserDetailService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  RedisTemplate<String, Object> redisTemplate;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

    final String requestTokenHeader = request.getHeader("Authorization");

    String userId = null;
    String jwtToken = null;
    // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      jwtToken = requestTokenHeader.substring(7);
      try {
        userId = jwtTokenUtil.getIdFromToken(jwtToken);

        String redisToken =  (String)redisTemplate.opsForValue().get(userId);

        // 토근 만료 or 로그아웃
        if(redisToken == null || !jwtToken.equals(redisToken)) {
          response.setStatus(401);
        }
      } catch (Exception e) {
        log.debug("doFilterInternal Token error : " + e);
      }
    }

    //Once we get the token validate it.
    if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      UserDetails userDetails = jwtUserDetailService.loadUserByUsername(userId);

      // if token is valid configure Spring Security to manually set authentication
      if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    chain.doFilter(request, response);
  }
}
