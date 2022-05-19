package kr.co.demo.example.repository;


import kr.co.demo.example.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExampleRepository {

	/**
	 * 예제 게시판 카운트 조회
	 * @param paramMap
	 * @return int
	 */
	int selectExampleCount(Map paramMap);

	/**
	 * 예제 게시판 목록 조회
	 * @param paramMap
	 * @return 조건에 해당하는 예제 게시판 리스트
	 */
	List<Example> selectExampleList(Map paramMap);

	/**
	 * 예제 게시판 조회
	 * @param id
	 * @return 조건에 해당하는 예제 게시판
	 */
	Example selectExample(Long id);

	/**
	 * 예제 게시판 등록
	 * @param example
	 * @return int
	 */
	int insertExample(Example example) throws Exception;

	/**
	 * 예제 게시판 수정
	 * @param example
	 * @return int
	 */
	int updateExample(Example example) throws Exception;

	/**
	 * 예제 게시판 삭제
	 * @param idx
	 * @return int
	 */
	int deleteExample(Long idx) throws Exception;

}
