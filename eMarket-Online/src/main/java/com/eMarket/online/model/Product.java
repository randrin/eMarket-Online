package com.eMarket.online.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
	
	@Id
	private String id;
	private String code;
	private String name;
	private String brand;
	private String description;
	private double unitPrice;
	private int categoryId;
	private String imageURl;
	private int supplierId;
	private boolean active;
	private int purchases;
	private int views;
	@DBRef
	private Category caterory;
}
