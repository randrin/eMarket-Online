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
public class EmarketCategory {

	@Id
	private String id;
	private String name;
	private String description;
	private String imageIcon;
	private boolean activation;
	@DBRef
	private Collection<EmarketSubCategory> subCategories = new ArrayList<EmarketSubCategory>();

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", imageIcon=" + imageIcon
				+ ", activation=" + activation + "]";
	}
	
	
}
