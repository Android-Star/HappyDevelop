package com.unisound.sdk.service.utils.nlu;

public class Mixture<I extends Intent, R extends Result> extends Mixture2 {
  private Semantic<I> semantic;
  private Data<R> data;

  public Semantic<I> getSemantic() {
    return semantic;
  }

  public void setSemantic(Semantic<I> semantic) {
    this.semantic = semantic;
  }

  public Data<R> getData() {
    return data;
  }

  public void setData(Data<R> data) {
    this.data = data;
  }
}
