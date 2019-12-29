package com.eMarket.online.model;

import java.util.ArrayList;
import java.util.Collection;

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
public class Category {

	@Id
	private String id;
	private String name;
	private String description;
	private String imageURl;
	private boolean active;
	@DBRef
	private Collection<Product> products = new ArrayList<Product>();
}