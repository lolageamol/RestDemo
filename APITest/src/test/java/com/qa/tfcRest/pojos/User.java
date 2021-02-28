package com.qa.tfcRest.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "job" })
public class User {

	@JsonProperty("name")
	private String name;
	@JsonProperty("job")
	private String job;
	
	@JsonProperty("id")
	private String id;
	@JsonProperty("createdAt")
	private String createdAt;
	
	/**
	 *
	 * @param name
	 * @param job
	 */
	public User(String name, String job) {
		super();
		this.name = name;
		this.job = job;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("job")
	public String getJob() {
		return job;
	}

	@JsonProperty("job")
	public void setJob(String job) {
		this.job = job;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("createdAt")
	public String getCreatedAt() {
		return createdAt;
	}

	@JsonProperty("createdAt")
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public User() {
	}



}
