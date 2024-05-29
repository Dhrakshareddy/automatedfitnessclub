package com.training.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProgressBean {
	
	private Long progressId;
	private String username;
	private Date progressDate;
	private Double calculateProgress;
   
	

}
