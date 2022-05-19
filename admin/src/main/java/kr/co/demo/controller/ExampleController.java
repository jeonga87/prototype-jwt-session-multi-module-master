package kr.co.demo.controller;

import kr.co.demo.common.PaginationUtil;
import kr.co.demo.example.domain.Example;
import kr.co.demo.example.service.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Class Name : ExampleController.java
 * Description : 예제 게시판 컨트롤러 클래스
 * Modification Information
 */

@RestController
public class ExampleController extends BaseFormController {

  private static final Logger log = LoggerFactory.getLogger(ExampleController.class);

  @Autowired
  private ExampleService exampleService;

  /**
   * 예제 게시판 리스트
   * @param param
   * @return
   */
  @GetMapping(value = "/api/example/list")
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
  @PostMapping(value = "/api/example/insert")
  public ResponseEntity<Void> insert(@RequestBody Example example) throws Exception {
    return createResponseEntity(exampleService.insertExample(example) > 0);
  }

  /**
   * 예제 상세조회
   * @param idx
   * @return
   */
  @GetMapping(value = "/api/example/detail/{idx}")
  public ResponseEntity<List<Example>> detail(@PathVariable("idx") Long idx) {
    return createResponseEntity(true, exampleService.getExample(idx));
  }

  /**
   * 예제 게시판 수정 프로세스
   * @param example
   * @return
   */
  @PutMapping(value = "/api/example/update")
  public ResponseEntity<Void> update(@RequestBody Example example) throws Exception {
    return createResponseEntity(exampleService.updateExample(example) > 0);
  }

  /**
   * 예제 게시판 삭제 프로세스
   * @param idx
   * @return
   */
  @DeleteMapping(value = "/api/example/delete/{idx}")
  public ResponseEntity<Void> delete(@PathVariable("idx") Long idx) throws Exception {
    return createResponseEntity(exampleService.deleteExample(idx) > 0);
  }
}
