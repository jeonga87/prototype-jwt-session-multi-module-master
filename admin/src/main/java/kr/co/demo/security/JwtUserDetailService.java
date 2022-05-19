package kr.co.demo.security;

import kr.co.demo.admin.domain.Admin;
import kr.co.demo.admin.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDetailsService")
public class JwtUserDetailService implements UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(JwtUserDetailService.class);

	@Autowired
	private AdminService adminService;

	/**
	 * username(=ID)로 정보 조회 (for Spring Security)
	 * @param username 로그인 아이디
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminService.getAdminId(username);

		// 없는 계정일 경우
		if(admin == null) {
			log.info("존재하는 계정이 없음(ID: " + username + ")");
			throw new UsernameNotFoundException("NO ACCOUNT");
		}

		return admin;
	}
}
