package com.bit.model;

import java.util.Date;

public class CommunityVo {
	private int communityNo;
	private String id;
	private String pw;
	private String title;
	private String content;
	private Date hiredate;
	private int counts;
	private String state;
	private int pageCount;
	private int page;
	
	
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
//		return "{\"communityNo\": "+communityNo+", \"id\": \""+id+"\", \"pw\": \""+pw+"\", \"title\": \""+title+"\", \"content\":\""+content+"\", \"hiredate\": \""+hiredate+"\", \"counts\": "+counts+", \"state\":\""+state+"\"}";
		return "{\"communityNo\": "+communityNo+", \"id\": \""+id+"\", \"pw\": \""+pw+"\", \"title\": \""+title+"\", \"content\":\""+content+"\", \"hiredate\": \""+hiredate+"\", \"counts\": "+counts+", \"state\":\""+state+"\", \"pageCount\":"+pageCount+", \"page\":"+page+"}";
	}

	public int getCommunityNo() {
		return communityNo;
	}
	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	
	public CommunityVo(int communityNo, String id, String pw, String title, String content, Date hiredate, int counts,
			String state, int pageCount, int page) {
		super();
		this.communityNo = communityNo;
		this.id = id;
		this.pw = pw;
		this.title = title;
		this.content = content;
		this.hiredate = hiredate;
		this.counts = counts;
		this.state = state;
		this.pageCount = pageCount;
		this.page = page;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + communityNo;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + counts;
		result = prime * result + ((hiredate == null) ? 0 : hiredate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + page;
		result = prime * result + pageCount;
		result = prime * result + ((pw == null) ? 0 : pw.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommunityVo other = (CommunityVo) obj;
		if (communityNo != other.communityNo)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (counts != other.counts)
			return false;
		if (hiredate == null) {
			if (other.hiredate != null)
				return false;
		} else if (!hiredate.equals(other.hiredate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (page != other.page)
			return false;
		if (pageCount != other.pageCount)
			return false;
		if (pw == null) {
			if (other.pw != null)
				return false;
		} else if (!pw.equals(other.pw))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public CommunityVo() {
		// TODO Auto-generated constructor stub
	}
}
