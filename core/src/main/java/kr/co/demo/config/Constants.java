package kr.co.demo.config;

public class Constants {

  /**
   * Spring Profile 관련 상수 Class
   */
  public final class SpringProfileConst {

      /** 로컬 */
      public static final String LOCAL = "local";

      /** 개발 */
      public static final String DEVELOPMENT = "dev";

      /** 운영 */
      public static final String PRODUCTION = "prod";

  }

  /**
   * Spring Security Role 관련 상수 Class
   * (홈페이지에서 회원/비회원 구분용)
   */
  public final class AuthoritiesConsf {
    public static final String ADMIN = "ROLE_ADMIN";

    public static final String MEMBER = "ROLE_MEMBER";

    public static final String NON_MEMBER = "ROLE_NON_MEMBER";
  }

  /**
   * Paging 관련 상수 Class
   */
  public final class PaginationConst {

    /** Paging 사용 안할 경우 */
    public static final String SKIP_PAGING = "SKIP_PAGING";

    /** 조회 메소드명 공통 접두사 */
    public static final String SELECT_METHOD_PREFIX = "select";
    /** 건수 조회 메소드명 공통 접두사 */
    public static final String COUNT_METHOD_PREFIX = "count";
    /** 목록 조회 메소드명 공통 접미사 */
    public static final String LIST_METHOD_SUFFIX = "List";

    /** 현재 페이지 변수명 */
    public static final String CURRENT_PAGE = "currentPage";
    /** 한 페이지당 출력될 데이터 수 변수명  */
    public static final String DATA_PER_PAGE = "dataPerPage";

    /** 검색 호출 시 입력받는 파라미터 키 */
    public static final String CONDITION_PARAM_KEY = "q.";

    /** 전체 건수 페이징 헤더값 이름 */
    public static final String TOTAL_COUNT_HEADER_NAME = "X-Phoenix-Total-Count";
    /** 현제 페이지 페이징 헤더값 이름 */
    public static final String CURRENT_PAGE_HEADER_NAME = "X-Phoenix-Current-Page";
    /** 한 페이지당 출력될 데이터 헤더값 이름 */
    public static final String DATA_PER_PAGE_HEADER_NAME = "X-Phoenix-Data-Per-Page";

    /** 한 페이지당 출력될 데이터 수 기본값  */
    public static final int DEFAULT_DATA_PER_PAGE = 10;

  }


  /**
   * Excel 관련 상수 Class
   */
  public final class ExcelConst {

    /** 다운로드시 엑셀 컨텐츠 타입 헤더값 */
    public static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    /** 다운로드시 엑셀 파일명(확장자 제외) */
    public static final String FILE_NAME = "fileName";

    /** ExcelView로 넘겨줄 데이터 Model 이름 */
    public static final String EXCEL_DATA = "excelData";

    /** 컬럼 헤더 배경색 값 */
    public static final String HEADER_BG_COLOR = "bgColor";

    /** 컬럼 헤더 및 데이터 정보 */
    public static final String HEADER_LIST = "headerList";

    /** 데이터 목록 */
    public static final String ROW_LIST = "rowList";

  }

  private Constants() {
  }
}

