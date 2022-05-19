package kr.co.demo.common;

import org.springframework.http.HttpHeaders;
import kr.co.demo.config.Constants;
import kr.co.demo.config.mybatis.Paging;

public class PaginationUtil {
    public static HttpHeaders generatePaginationHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.add(Constants.PaginationConst.TOTAL_COUNT_HEADER_NAME, "" + Paging.totalCnt.get());
        headers.add(Constants.PaginationConst.DATA_PER_PAGE_HEADER_NAME, "" + Paging.dataPerPage.get());
        headers.add(Constants.PaginationConst.CURRENT_PAGE_HEADER_NAME, "" + Paging.currentPage.get());

        return headers;
    }

}
