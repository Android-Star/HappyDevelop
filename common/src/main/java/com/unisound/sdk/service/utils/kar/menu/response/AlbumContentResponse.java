package com.unisound.sdk.service.utils.kar.menu.response;

import java.io.Serializable;
import java.util.List;

import static com.unisound.sdk.service.utils.constants.Constant.CODE_SUCCESS;

public class AlbumContentResponse implements Serializable {

  private int counter;
  private int err;
  private List<ListBean> list;

  public int getCounter() {
    return counter;
  }

  public void setCounter(int counter) {
    this.counter = counter;
  }

  public int getErr() {
    return err;
  }

  public void setErr(int err) {
    this.err = err;
  }

  public List<ListBean> getList() {
    return list;
  }

  public void setList(List<ListBean> list) {
    this.list = list;
  }

  public boolean isSuccess() {
    return err == CODE_SUCCESS;
  }

  public static class ListBean implements Serializable {

    private long fid;  //文件id
    private long ds; //数据源
    private long c;  //播放量
    private String artist;  //作者名
    private String n; //文件名
    private String imgUrl;  //音频文件对应的图片文件的URL，不一定有值。
    private int paidContent;  //0 默认值，代表未知1 是付费内容2不是付费内容
    private int alreadyPaid;  //0 默认值，代表未知1 设备管理员已经付费2 设备管理员未付费
    private int audition; //0 默认值，代表未知1, 可以试听2,不可以试听
    private String dsUrl;
    private String price;
    private String dataType;
    private String dataSourceCode;

    public long getFid() {
      return fid;
    }

    public void setFid(long fid) {
      this.fid = fid;
    }

    public long getDs() {
      return ds;
    }

    public void setDs(long ds) {
      this.ds = ds;
    }

    public long getC() {
      return c;
    }

    public void setC(long c) {
      this.c = c;
    }

    public String getArtist() {
      return artist;
    }

    public void setArtist(String artist) {
      this.artist = artist;
    }

    public String getN() {
      return n;
    }

    public void setN(String n) {
      this.n = n;
    }

    public int getPaidContent() {
      return paidContent;
    }

    public void setPaidContent(int paidContent) {
      this.paidContent = paidContent;
    }

    public int getAlreadyPaid() {
      return alreadyPaid;
    }

    public void setAlreadyPaid(int alreadyPaid) {
      this.alreadyPaid = alreadyPaid;
    }

    public int getAudition() {
      return audition;
    }

    public void setAudition(int audition) {
      this.audition = audition;
    }

    public String getImgUrl() {
      return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
      this.imgUrl = imgUrl;
    }

    public String getDsUrl() {
      return dsUrl;
    }

    public void setDsUrl(String dsUrl) {
      this.dsUrl = dsUrl;
    }

    public String getPrice() {
      return price;
    }

    public void setPrice(String price) {
      this.price = price;
    }

    public String getDataType() {
      return dataType;
    }

    public void setDataType(String dataType) {
      this.dataType = dataType;
    }

    public String getDataSourceCode() {
      return dataSourceCode;
    }

    public void setDataSourceCode(String dataSourceCode) {
      this.dataSourceCode = dataSourceCode;
    }
  }
}
