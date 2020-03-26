package com.example.view.entity;

public class MagazineNew extends LiteratureNew {
	

	private Integer numbersPerYear;
	
	public Integer getNumbersPerYear() {
		return numbersPerYear;
	}

	public void setNumbersPerYear(Integer numbersPerYear) {
		this.numbersPerYear = numbersPerYear;
	}

	public MagazineNew() {
		
	}
	
	@Override
	public String toString() {
		return "Magazine [publisher=" + getPublisher() + ", title=" + getTitle() + ", year=" + getYear() + ", numbersPerYear="
				+ getNumbersPerYear() + "]";
	}


}
