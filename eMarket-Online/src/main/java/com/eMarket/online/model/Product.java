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
	private String description;
	private double unitPriceNew;
	private double unitPriceSold;
	private String color;
	private String size;
	private boolean sale;
	private int reductionPercent;
	private String imagePath;
	private int supplierId;
	private boolean activation;
	private int totalStock;
	private int totalPucharsed;
	private int numberOfViews;
	@DBRef
	private Subcategory subCategory;
}
