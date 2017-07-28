package com.xiao.dao;

import java.util.Iterator;

import org.springframework.stereotype.Repository;

import com.xiao.domain.Board;

/**
 * 对于Board PO的操作DAO
 * 
 * @author XIAO
 *
 */
@Repository
public class BoardDao extends BaseDao<Board> {

	/**
	 * 查询board表中所有记录的条数的sql语句
	 */
	private static final String GET_BOARD_NUM = "select count(f.boardId) from Board f";

	public long getBoardNum() {
		// Iterator iter = getHibernateTemplate().iterate(GET_BOARD_NUM);
		Long res = ((Long) getHibernateTemplate().iterate(GET_BOARD_NUM).next()).longValue();
		return res.longValue();
	}

}
