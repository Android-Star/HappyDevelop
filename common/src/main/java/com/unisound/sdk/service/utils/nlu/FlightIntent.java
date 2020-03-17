package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class FlightIntent implements Intent, Serializable {
  public static final String CURRENT_CITY = "CURRENT_CITY";

  public static final String ECONOMY_CLASS = "ECONOMY_CLASS";
  public static final String BIZ_CLASS = "BIZ_CLASS";
  public static final String FIRST_CLASS = "FIRST_CLASS";

  public static final String PRICE_ASC = "PRICE_ASC";
  public static final String PRICE_DESC = "PRICE_DESC";
  public static final String DEPART_TIME_ASC = "DEPART_TIME_ASC";
  public static final String DEPART_TIME_DESC = "DEPART_TIME_DESC";

  private String flightNo;
  private String origin;
  private String destination;
  private String departDate;
  private String returnDate;
  private String departT;
  private String airlineCode;
  private String seat;
  private String sort;

  public String getFlightNo() {
    return flightNo;
  }

  public void setFlightNo(String flightNo) {
    this.flightNo = flightNo;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getDepartDate() {
    return departDate;
  }

  public void setDepartDate(String departDate) {
    this.departDate = departDate;
  }

  public String getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(String returnDate) {
    this.returnDate = returnDate;
  }

  public String getDepartT() {
    return departT;
  }

  public void setDepartT(String departT) {
    this.departT = departT;
  }

  public String getAirlineCode() {
    return airlineCode;
  }

  public void setAirlineCode(String airlineCode) {
    this.airlineCode = airlineCode;
  }

  public String getSeat() {
    return seat;
  }

  public void setSeat(String seat) {
    this.seat = seat;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }
}
