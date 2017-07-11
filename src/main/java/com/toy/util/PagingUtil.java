package com.toy.util;

/**
 * 작성일 :  2017. 07. 10
 * 작성자 : 송하람
 * 설  명 : 페이지 공통 기능
 */

public class PagingUtil {
	//한 화면에 출력하고 싶은 목록 갯수
	private int countPerPage;
	// 한 화면에 출력하고 싶은 페이지 갯수		
	private int pageNumber;
	//페이지 갯수 (맨 마지막페이지)
	private int pageCount;
	//이전페이지
	private int nextPage;
	//다음페이지
	private int prevPage;
	//페이지 블록의 첫번째
	private int nowBlockFirst;
	//페이지 블록의 마지막
	private int nowBlockLast;
	//현재페이지
	private Double seq;
	//현재블록
	private int nowBlock;
	
	private int totalCount;
	
	//페이지에 필요한 변수 만드는 생성자
	public PagingUtil(int countPerPage, int pageNumber, Double seq, int totalCount) {
		this.countPerPage = countPerPage;
		this.pageNumber = pageNumber;
		this.seq = seq;
		this.totalCount = totalCount;
		
		this.pageCount = (int) Math.ceil((double)totalCount / countPerPage);
		this.nextPage = seq.intValue() + 1;
		this.prevPage = seq.intValue() - 1;
		//현재 블록
		this.nowBlock = (int)Math.ceil(seq/pageNumber);
		//현재 블록의 첫번째 페이지
		this.nowBlockFirst = (nowBlock - 1) * pageNumber + 1;
		//현재 블록의 마지막 페이지
		this.nowBlockLast = (nowBlock) * pageNumber;
		
		if (nowBlockLast > pageCount) {
			nowBlockLast = pageCount;
		}
				
		//다음 페이지
		if(seq == pageCount) {
			this.nextPage = pageCount;
		}
		if(seq == 1) {
			this.prevPage = 1;
		}
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getNowBlock() {
		return nowBlock;
	}
	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}
	public Double getSeq() {
		return seq;
	}
	public void setSeq(Double seq) {
		this.seq = seq;
	}
	public int getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNowBlockFirst() {
		return nowBlockFirst;
	}
	public void setNowBlockFirst(int nowBlockFirst) {
		this.nowBlockFirst = nowBlockFirst;
	}
	public int getNowBlockLast() {
		return nowBlockLast;
	}
	public void setNowBlockLast(int nowBlockLast) {
		this.nowBlockLast = nowBlockLast;
	}
	
	
}
