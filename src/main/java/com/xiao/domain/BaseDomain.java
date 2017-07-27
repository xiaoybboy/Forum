package com.xiao.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * <b>类描述:所有PO类的基础父类</b>
 */
public class BaseDomain implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
