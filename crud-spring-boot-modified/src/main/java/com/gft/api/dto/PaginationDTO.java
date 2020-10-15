package com.gft.api.dto;

public class PaginationDTO {
	
	private Integer pageLessTen;
	private Integer pageLessOne;
	private Integer pageNumber;
	private Integer pageMoreOne;
	private Integer pageMoreTen;
	private boolean showBackTen;
	private boolean showBackOne;
	private boolean showAtual;
	private boolean showNextOne;
	private boolean showNextTen;
	private Integer totalLines;
	private Integer totalPages;
	private Integer maxPerPage;
	
	
	public PaginationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PaginationDTO(Integer pageLessTen, Integer pageLessOne, Integer pageNumber, Integer pageMoreOne,
			Integer pageMoreTen, boolean showBackTen, boolean showBackOne, boolean showAtual, boolean showNextOne,
			boolean showNextTen, Integer totalLines, Integer totalPages, Integer maxPerPage) {
		super();
		this.pageLessTen = pageLessTen;
		this.pageLessOne = pageLessOne;
		this.pageNumber = pageNumber;
		this.pageMoreOne = pageMoreOne;
		this.pageMoreTen = pageMoreTen;
		this.showBackTen = showBackTen;
		this.showBackOne = showBackOne;
		this.showAtual = showAtual;
		this.showNextOne = showNextOne;
		this.showNextTen = showNextTen;
		this.totalLines = totalLines;
		this.totalPages = totalPages;
		this.maxPerPage = maxPerPage;
	}


	public Integer getPageLessTen() {
		return pageLessTen;
	}


	public void setPageLessTen(Integer pageLessTen) {
		this.pageLessTen = pageLessTen;
	}


	public Integer getPageLessOne() {
		return pageLessOne;
	}


	public void setPageLessOne(Integer pageLessOne) {
		this.pageLessOne = pageLessOne;
	}


	public Integer getPageNumber() {
		return pageNumber;
	}


	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}


	public Integer getPageMoreOne() {
		return pageMoreOne;
	}


	public void setPageMoreOne(Integer pageMoreOne) {
		this.pageMoreOne = pageMoreOne;
	}


	public Integer getPageMoreTen() {
		return pageMoreTen;
	}


	public void setPageMoreTen(Integer pageMoreTen) {
		this.pageMoreTen = pageMoreTen;
	}


	public boolean isShowBackTen() {
		return showBackTen;
	}


	public void setShowBackTen(boolean showBackTen) {
		this.showBackTen = showBackTen;
	}


	public boolean isShowBackOne() {
		return showBackOne;
	}


	public void setShowBackOne(boolean showBackOne) {
		this.showBackOne = showBackOne;
	}


	public boolean isShowAtual() {
		return showAtual;
	}


	public void setShowAtual(boolean showAtual) {
		this.showAtual = showAtual;
	}


	public boolean isShowNextOne() {
		return showNextOne;
	}


	public void setShowNextOne(boolean showNextOne) {
		this.showNextOne = showNextOne;
	}


	public boolean isShowNextTen() {
		return showNextTen;
	}


	public void setShowNextTen(boolean showNextTen) {
		this.showNextTen = showNextTen;
	}


	public Integer getTotalLines() {
		return totalLines;
	}


	public void setTotalLines(Integer totalLines) {
		this.totalLines = totalLines;
	}


	public Integer getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}


	public Integer getMaxPerPage() {
		return maxPerPage;
	}


	public void setMaxPerPage(Integer maxPerPage) {
		this.maxPerPage = maxPerPage;
	}
	
	
	
	
	
	
	

}
