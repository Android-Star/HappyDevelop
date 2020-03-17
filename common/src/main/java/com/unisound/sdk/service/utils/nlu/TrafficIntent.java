package com.unisound.sdk.service.utils.nlu;

public class TrafficIntent implements Intent {
  public static final String CURRENT_CITY = "CURRENT_CITY";
  public static final String ROAD_AHEAD = "ROAD_AHEAD";
  public static final String ROAD_SURROUNDING = "ROAD_SURROUNDING";
  public static final String INTO_TOWN = "INTO_TOWN";
  public static final String OUT_OF_TOWN = "OUT_OF_TOWN";
  public static final String OUTER_RING = "OUTER_RING";
  public static final String INNER_CIRCLE = "INNER_CIRCLE";
  public static final String EAST_TO_WEST = "EAST_TO_WEST";
  public static final String WEST_TO_EAST = "WEST_TO_EAST";
  public static final String NORTH_TO_SOUTH = "NORTH_TO_SOUTH";
  public static final String SOUTH_TO_NORTH = "SOUTH_TO_NORTH";

  private String city;
  private String road;
  private String direction;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getRoad() {
    return road;
  }

  public void setRoad(String road) {
    this.road = road;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }
}
