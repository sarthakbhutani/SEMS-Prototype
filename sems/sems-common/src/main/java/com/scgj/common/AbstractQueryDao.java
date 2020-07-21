package com.scgj.common;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class AbstractQueryDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * @return the jdbcTemplate
	 */
	protected NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	@Autowired
	@Qualifier(ApplicationDatasource.NAME)
	public void setJdbcTemplate(DataSource applicationDataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(
				applicationDataSource);
		
		
	}

}
