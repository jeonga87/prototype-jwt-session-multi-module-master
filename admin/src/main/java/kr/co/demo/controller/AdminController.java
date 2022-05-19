package kr.co.demo.controller;

import kr.co.demo.admin.domain.Admin;
import kr.co.demo.admin.domain.JwtResponse;
import kr.co.demo.admin.service.AdminService;
import kr.co.demo.common.PaginationUtil;
import kr.co.demo.security.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Class Name : AdminController.java
 * Description : admin 컨트롤러 클래스
 * Modification Information
 */

@RestController
public class AdminController extends BaseFormController {

  private static final Logger log = LoggerFactory.getLogger(AdminController.class);

  @Autowired
  private AdminService adminService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  RedisTemplate<String, Object> redisTemplate;

  //redis 만료시간 7일
  public static final long JWT_REDIS_VALIDITY = 1000 * 6 * 10 * 60 * 24 * 7;

  /**
   * admin 인증
   * @param admin
   * @return
   */
  @PostMapping("/api/admin/login")
  public ResponseEntity<?> auth(@RequestBody Admin admin) {
    final UserDetails userDetails = adminService.loadUserByUsername(admin.getId());

    final String token = jwtTokenUtil.generateToken(userDetails);

    redisTemplate.opsForValue().set(admin.getId(), token);
    redisTemplate.expire(admin.getId(), JWT_REDIS_VALIDITY, TimeUnit.MILLISECONDS);

    return new ResponseEntity(new JwtResponse(token), HttpStatus.OK);
  }

   /**
   * admin 리스트
   * @param param
   * @return
   */
  @GetMapping(value = "/api/admin/list")
  public ResponseEntity<List<Admin>> list(@RequestParam Map<String, Object> param) {
    List<Admin> adminList = adminService.getAdminList(param);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders();
    return createResponseEntity(true, headers, adminList);
  }

  /**
   * 관리자 아이디 중복확인
   * @param id
   */
  @GetMapping(value="/api/admin/overlap/{id}")
  public ResponseEntity<Integer> overlap(@PathVariable(value="id") String id) {
    int result = adminService.getAdminIdCount(id);
    return createResponseEntity(true, result);
  }

   /**
   * admin 입력 프로세스
   * @param admin
   * @return
   */
  @PostMapping(value = "/api/admin/insert")
  public ResponseEntity<Void> insert(@RequestBody Admin admin) throws Exception {
    return createResponseEntity(adminService.insertAdmin(admin) > 0);
  }

   /**
   * admin 상세조회
   * @param idx
   * @return
   */
  @GetMapping(value = "/api/admin/detail/{idx}")
  public ResponseEntity<List<Admin>>  detail(@PathVariable("idx") Long idx) {
    return createResponseEntity(true, adminService.getAdmin(idx));
  }

   /**
   * admin 수정 프로세스
   * @param admin
   * @return
   */
  @PutMapping(value = "/api/admin/update")
  public ResponseEntity<Void> update(@RequestBody Admin admin) throws Exception {
    return createResponseEntity(adminService.updateAdmin(admin) > 0);
  }

  /**
   * admin 삭제 프로세스
   * @param idx
   * @return
   */
  @DeleteMapping(value = "/api/admin/delete/{idx}")
  public ResponseEntity<Void> delete(@PathVariable("idx") Long idx) throws Exception {
    return createResponseEntity(adminService.deleteAdmin(idx) > 0);
  }
}
