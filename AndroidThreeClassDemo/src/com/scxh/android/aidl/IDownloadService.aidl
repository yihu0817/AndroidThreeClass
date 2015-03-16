package com.scxh.android.aidl;
import com.scxh.android.bean.UserBean;
interface IDownloadService {
  void download(String path);
  void playMusic(String path);
  void pauseMusic();
  void setUser(in UserBean user);
  UserBean getUser();
  List<String> getList();
}