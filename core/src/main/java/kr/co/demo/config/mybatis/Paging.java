package kr.co.demo.config.mybatis;

public class Paging {
	public static ThreadLocal<Integer> currentPage = new ThreadLocal();
	public static ThreadLocal<Integer> dataPerPage = new ThreadLocal();
	public static ThreadLocal<Integer> totalCnt = new ThreadLocal();
	public static ThreadLocal<String> queryString = new ThreadLocal();
	public static ThreadLocal<Boolean> skipPaging = new ThreadLocal();

	public static void resetAll() {
		Paging.currentPage.set(null);
		Paging.dataPerPage.set(null);
		Paging.totalCnt.set(null);
		Paging.queryString.set(null);
		Paging.skipPaging.set(null);
	}
}
