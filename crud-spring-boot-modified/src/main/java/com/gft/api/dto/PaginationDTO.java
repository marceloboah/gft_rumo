package com.gft.api.dto;

public class PaginationDTO {
	
	private Integer pagelessten;
	private Integer pagelessone;
	private Integer pagenumber;
	private Integer pagemoreone;
	private Integer pagemoreten;
	private boolean showbackten;
	private boolean showbackone;
	private boolean showatual;
	private boolean shownextone;
	private boolean shownextten;
	private Integer totallines;
	private Integer totalpages;
	private Integer maxperpage;
	
	
	public PaginationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaginationDTO(Integer pagelessten, Integer pagelessone, Integer pagenumber, Integer pagemoreone,
			Integer pagemoreten, boolean showbackten, boolean showbackone, boolean showatual, boolean shownextone,
			boolean shownextten, Integer totallines, Integer totalpages, Integer maxperpage) {
		super();
		this.pagelessten = pagelessten;
		this.pagelessone = pagelessone;
		this.pagenumber = pagenumber;
		this.pagemoreone = pagemoreone;
		this.pagemoreten = pagemoreten;
		this.showbackten = showbackten;
		this.showbackone = showbackone;
		this.showatual = showatual;
		this.shownextone = shownextone;
		this.shownextten = shownextten;
		this.totallines = totallines;
		this.totalpages = totalpages;
		this.maxperpage = maxperpage;
	}
	public Integer getPagelessten() {
		return pagelessten;
	}
	public void setPagelessten(Integer pagelessten) {
		this.pagelessten = pagelessten;
	}
	public Integer getPagelessone() {
		return pagelessone;
	}
	public void setPagelessone(Integer pagelessone) {
		this.pagelessone = pagelessone;
	}
	public Integer getPagenumber() {
		return pagenumber;
	}
	public void setPagenumber(Integer pagenumber) {
		this.pagenumber = pagenumber;
	}
	public Integer getPagemoreone() {
		return pagemoreone;
	}
	public void setPagemoreone(Integer pagemoreone) {
		this.pagemoreone = pagemoreone;
	}
	public Integer getPagemoreten() {
		return pagemoreten;
	}
	public void setPagemoreten(Integer pagemoreten) {
		this.pagemoreten = pagemoreten;
	}
	public boolean isShowbackten() {
		return showbackten;
	}
	public void setShowbackten(boolean showbackten) {
		this.showbackten = showbackten;
	}
	public boolean isShowbackone() {
		return showbackone;
	}
	public void setShowbackone(boolean showbackone) {
		this.showbackone = showbackone;
	}
	public boolean isShowatual() {
		return showatual;
	}
	public void setShowatual(boolean showatual) {
		this.showatual = showatual;
	}
	public boolean isShownextone() {
		return shownextone;
	}
	public void setShownextone(boolean shownextone) {
		this.shownextone = shownextone;
	}
	public boolean isShownextten() {
		return shownextten;
	}
	public void setShownextten(boolean shownextten) {
		this.shownextten = shownextten;
	}
	public Integer getTotallines() {
		return totallines;
	}
	public void setTotallines(Integer totallines) {
		this.totallines = totallines;
	}
	public Integer getTotalpages() {
		return totalpages;
	}
	public void setTotalpages(Integer totalpages) {
		this.totalpages = totalpages;
	}
	public Integer getMaxperpage() {
		return maxperpage;
	}
	public void setMaxperpage(Integer maxperpage) {
		this.maxperpage = maxperpage;
	}
	
	
	
	
	
	

}
