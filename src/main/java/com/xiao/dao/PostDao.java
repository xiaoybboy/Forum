package com.xiao.dao;

import org.springframework.stereotype.Repository;

import com.opera.core.systems.scope.protos.CookieMngProtos.GetCookieArg;
import com.xiao.domain.Post;

/**
 * Post操作
 * 
 * @author XIAO
 *
 */
@Repository
public class PostDao extends BaseDao<Post> {
	private static final String GET_PAGED_POSTS = "from Post where topic.topicId =? order by createTime desc";
	private static final String DELETE_TOPIC_POSTS = "delete from Post where topic.topicId=?";

	public Page getPagedPost(int topicId, int pageNo, int pageSize) {
		return pagedQuery(GET_PAGED_POSTS, pageNo, pageSize, topicId);
	}

	/**
	 * 删除主题下的所有帖子
	 * 
	 * @param topicId
	 *            主题ID
	 */
	public void deleteTopicPosts(int topicId) {
		getHibernateTemplate().bulkUpdate(DELETE_TOPIC_POSTS, topicId);
	}
}
