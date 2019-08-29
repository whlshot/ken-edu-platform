package com.ken.common.model;

import java.util.HashMap;
import java.util.Map;

public class QueryParam {

	protected Integer pageNum;
	protected Integer pageSize;
	protected String column;
	protected String orderType;
	protected Integer total;
	protected Map<String, Object> paramMap = new HashMap<>();

	public Integer getPageNum() {
		return pageNum != null && pageNum != 0 ? pageNum : 1;
	}

	public void setPageNum(Integer pageNum) {
		if (pageNum == null || pageNum <= 0) {
			pageNum = 1;
		} else {
			this.pageNum = pageNum;
		}
	}

	public Integer getPageSize() {
		return pageSize != null && pageSize != 0 ? pageSize : 10;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null || pageSize <= 0) {
			pageSize = 10; // 默认10条
		} else {
			this.pageSize = pageSize;
		}
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * 计算分页
	 *
	 * @param total
	 * @return
	 */
	public int[] getPageParams(Integer total) {
		if (this.pageNum == null || this.pageNum < 1) {
			this.pageNum = 1;
		}
		if (this.pageSize == null || this.pageSize < 1) {
			this.pageSize = 10;
		}
		int firstResult = (this.pageNum - 1) * this.pageSize;
		int maxResult = this.pageSize;
		this.total = total;

		// 校验分页情况
		if (firstResult >= total || firstResult < 0) {
			firstResult = 0;
			this.pageNum = 1;
		}
		return new int[] { firstResult, maxResult };
	}

}
