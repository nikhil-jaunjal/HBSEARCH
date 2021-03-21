package com.nikhil.model;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Table;

import org.hibernate.search.engine.spatial.GeoPoint;

import lombok.Data;

@Data
@Embeddable
@Table(name = "Coordinate")
public class CoordinatesEntity implements GeoPoint{
	
	@Basic
    private Double latitude;

    @Basic
    private Double longitude;
    
    protected CoordinatesEntity() { }

	@Override
	public double latitude() {
		return latitude;
	}

	@Override
	public double longitude() {
		return longitude;
	}

}
