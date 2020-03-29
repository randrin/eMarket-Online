package com.eMarket.online.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmarketSubCategory {

	@Id
	private String id;
	private String name;
	private String description;
	@DBRef
	private Collection<EmarketProduct> products = new ArrayList<EmarketProduct>();

	@Override
	public String toString() {
		return "Subcategory [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
}
