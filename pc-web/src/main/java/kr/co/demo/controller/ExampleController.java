package kr.co.demo.controller;

import kr.co.demo.common.PaginationUtil;
import kr.co.demo.example.domain.Example;
import kr.co.demo.example.service.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Class Name : ExampleController.java
 * Description : 예제 게시판 컨트롤러 클래스
 * Modification Information
 */

@Controller
public class ExampleController extends BaseFormController {

  private static final Logger log = LoggerFactory.getLogger(ExampleController.class);

  @Autowired
  private ExampleService exampleService;

  /**
   * 예제 게시판 리스트
   * @return
   */
  @RequestMapping(value = "/example/list", method = RequestMethod.GET)
  public String list() {
    return "/example/list";
  }

  /**
   * 예제 게시판 입력 폼
   * @return
   */
  @RequestMapping(value = "/example/input", method = RequestMethod.GET)
  public String input() { return "/example/detail";}

  /**
   * 예제 게시판 리스트
   * @param param
   * @return
   */
  @RequestMapping(value = "/api/example/list", method = RequestMethod.GET)
  public ResponseEntity<List<Example>> list(@RequestParam Map<String, Object> param) {
    List<Example> exampleList = exampleService.getExampleList(param);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders();
    return createResponseEntity(true, headers, exampleList);
  }

  /**
   * 예제 게시판 입력 프로세스
   * @param example
   * @return
   */
  @RequestMapping(value = "/api/example/insert", method = RequestMethod.POST)
  public ResponseEntity<Void> insert(@RequestBody Example example) throws Exception {
    return createResponseEntity(exampleService.insertExample(example) > 0);
  }

  /**
   * 예제 상세조회
   * @param idx
   * @return
   */
  @RequestMapping(value = "/api/detail/{idx}", method = RequestMethod.GET)
  public ResponseEntity<List<Example>> detail(@PathVariable("idx") Long idx) {
    return createResponseEntity(true, exampleService.getExample(idx));
  }

  /**
   * 예제 게시판 수정 프로세스
   * @param example
   * @return
   */
  @RequestMapping(value = "/api/update", method = RequestMethod.PUT)
  public ResponseEntity<Void> update(@RequestBody Example example) throws Exception {
    return createResponseEntity(exampleService.updateExample(example) > 0);
  }

  /**
   * 예제 게시판 삭제 프로세스
   * @param idx
   * @return
   */
  @RequestMapping(value = "/api/delete/{idx}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable("idx") Long idx) throws Exception {
    return createResponseEntity(exampleService.deleteExample(idx) > 0);
  }
}
