package ottplaymedia.mncplaymedia.com;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class fungsi {
private static fungsi mostCurrent = new fungsi();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.collections.Map _v5 = null;
public static String _v6 = "";
public static String _v7 = "";
public static ottplaymedia.mncplaymedia.com.fungsi._formatconfig _v0 = null;
public static anywheresoftware.b4a.randomaccessfile.RandomAccessFile _vv1 = null;
public static String _vv2 = "";
public static String _vv3 = "";
public static String _vv4 = "";
public static String _vv5 = "";
public static String _vv6 = "";
public static String _vv7 = "";
public static String _vv0 = "";
public static String _vvv1 = "";
public static int _vvv2 = 0;
public static anywheresoftware.b4a.agraham.reflection.Reflection _vvv3 = null;
public static anywheresoftware.b4a.phone.Phone _vvv4 = null;
public static anywheresoftware.b4a.phone.Phone.PhoneId _vvv5 = null;
public static anywheresoftware.b4a.objects.collections.Map _vvv6 = null;
public static String _vvv7 = "";
public static String _vvv0 = "";
public static String _vvvv1 = "";
public static String _vvvv2 = "";
public static String _btncancel = "";
public static String _vvvv3 = "";
public static String _vvvv4 = "";
public static String _vvvv5 = "";
public static String _vvvv6 = "";
public static String _vvvv7 = "";
public static String _vvvv0 = "";
public static String _vvvvv1 = "";
public static String _vvvvv2 = "";
public static String _vvvvv3 = "";
public static String _vvvvv4 = "";
public static String _vvvvv5 = "";
public static String _vvvvv6 = "";
public static String _vvvvv7 = "";
public static String _vvvvv0 = "";
public static String _vvvvvv1 = "";
public static String _vvvvvv2 = "";
public static String _vvvvvv3 = "";
public static String _vvvvvv4 = "";
public static String _vvvvvv5 = "";
public static String _vvvvvv6 = "";
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvvvvvvvvvvvvv5 = null;
public b4a.example.dateutils _vvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.main _vvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.utama _vvvvvvvvvvvvvvvv1 = null;
public ottplaymedia.mncplaymedia.com.epglist _vvvvvvvvvvvvvvvv2 = null;
public ottplaymedia.mncplaymedia.com.register _vvvvvvvvvvvvvvvv3 = null;
public ottplaymedia.mncplaymedia.com.pilihan _vvvvvvvvvvvvvvvv4 = null;
public ottplaymedia.mncplaymedia.com.epgdownloader _vvvvvvvvvvvvvvvv5 = null;
public ottplaymedia.mncplaymedia.com.imagedownloader _vvvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.content _vvvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.webview _vvvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.mncutils2service _vvvvvvvvvvvvvvvvv1 = null;
public static class _formatconfig{
public boolean IsInitialized;
public String aid;
public String rsn;
public String imei;
public String sTab;
public String MacAddr;
public String FolderDBS;
public String Username;
public String Password;
public String FolderMedia;
public void Initialize() {
IsInitialized = true;
aid = "";
rsn = "";
imei = "";
sTab = "";
MacAddr = "";
FolderDBS = "";
Username = "";
Password = "";
FolderMedia = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _filedata{
public boolean IsInitialized;
public String Dir;
public String FileName;
public String KeyName;
public String ContentType;
public void Initialize() {
IsInitialized = true;
Dir = "";
FileName = "";
KeyName = "";
ContentType = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _vvvvvv7(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp,int _width,int _height) throws Exception{
float _ratiobmp = 0f;
float _ratioiv = 0f;
float _diviseur = 0f;
 //BA.debugLineNum = 284;BA.debugLine="Public Sub AdjustBitmap(bmp As Bitmap, Width As In";
 //BA.debugLineNum = 285;BA.debugLine="Dim RatioBmp, RatioIV As Float";
_ratiobmp = 0f;
_ratioiv = 0f;
 //BA.debugLineNum = 286;BA.debugLine="RatioBmp = bmp.Width / bmp.Height";
_ratiobmp = (float) (_bmp.getWidth()/(double)_bmp.getHeight());
 //BA.debugLineNum = 287;BA.debugLine="RatioIV = Width / Height";
_ratioiv = (float) (_width/(double)_height);
 //BA.debugLineNum = 288;BA.debugLine="Dim Diviseur As Float";
_diviseur = 0f;
 //BA.debugLineNum = 289;BA.debugLine="If RatioIV > RatioBmp Then";
if (_ratioiv>_ratiobmp) { 
 //BA.debugLineNum = 290;BA.debugLine="Diviseur = bmp.Height / Height";
_diviseur = (float) (_bmp.getHeight()/(double)_height);
 //BA.debugLineNum = 291;BA.debugLine="bmp = CreateScaledBitmap(bmp, Round(bmp.Width /";
_bmp = _vvvvvvv4(_ba,_bmp,(int) (anywheresoftware.b4a.keywords.Common.Round(_bmp.getWidth()/(double)_diviseur/(double)anywheresoftware.b4a.keywords.Common.Density)),(int) (anywheresoftware.b4a.keywords.Common.Round(_height/(double)anywheresoftware.b4a.keywords.Common.Density)));
 }else {
 //BA.debugLineNum = 293;BA.debugLine="Diviseur = bmp.Width / Width";
_diviseur = (float) (_bmp.getWidth()/(double)_width);
 //BA.debugLineNum = 294;BA.debugLine="bmp = CreateScaledBitmap(bmp, Round(Width / Dens";
_bmp = _vvvvvvv4(_ba,_bmp,(int) (anywheresoftware.b4a.keywords.Common.Round(_width/(double)anywheresoftware.b4a.keywords.Common.Density)),(int) (anywheresoftware.b4a.keywords.Common.Round(_bmp.getHeight()/(double)_diviseur/(double)anywheresoftware.b4a.keywords.Common.Density)));
 };
 //BA.debugLineNum = 296;BA.debugLine="Return bmp";
if (true) return _bmp;
 //BA.debugLineNum = 297;BA.debugLine="End Sub";
return null;
}
public static String  _vvvvvv0(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ImageViewWrapper _imv,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _img) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
int _delta = 0;
int _height = 0;
int _width = 0;
 //BA.debugLineNum = 299;BA.debugLine="Sub AdjustImageView(Imv As ImageView, Img As Bitma";
 //BA.debugLineNum = 300;BA.debugLine="Private bmp As Bitmap = Img";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp = _img;
 //BA.debugLineNum = 302;BA.debugLine="Dim Delta, Height, Width As Int";
_delta = 0;
_height = 0;
_width = 0;
 //BA.debugLineNum = 303;BA.debugLine="If bmp.Width / bmp.Height > Imv.Width / Imv.Heig";
if (_bmp.getWidth()/(double)_bmp.getHeight()>_imv.getWidth()/(double)_imv.getHeight()) { 
 //BA.debugLineNum = 304;BA.debugLine="Height = bmp.Height / bmp.Width * Imv.Width";
_height = (int) (_bmp.getHeight()/(double)_bmp.getWidth()*_imv.getWidth());
 //BA.debugLineNum = 305;BA.debugLine="Delta = (Imv.Height - Height) / 2";
_delta = (int) ((_imv.getHeight()-_height)/(double)2);
 //BA.debugLineNum = 306;BA.debugLine="Imv.Height = Height";
_imv.setHeight(_height);
 //BA.debugLineNum = 307;BA.debugLine="Imv.Top = Imv.Top + Delta";
_imv.setTop((int) (_imv.getTop()+_delta));
 }else {
 //BA.debugLineNum = 309;BA.debugLine="Width = bmp.Width / bmp.Height * Imv.Height";
_width = (int) (_bmp.getWidth()/(double)_bmp.getHeight()*_imv.getHeight());
 //BA.debugLineNum = 310;BA.debugLine="Delta = (Imv.Width - Width) / 2";
_delta = (int) ((_imv.getWidth()-_width)/(double)2);
 //BA.debugLineNum = 311;BA.debugLine="Imv.Width = Width";
_imv.setWidth(_width);
 //BA.debugLineNum = 312;BA.debugLine="Imv.Left = Imv.Left + Delta";
_imv.setLeft((int) (_imv.getLeft()+_delta));
 };
 //BA.debugLineNum = 314;BA.debugLine="Imv.Gravity = Gravity.FILL";
_imv.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 315;BA.debugLine="Imv.Bitmap = bmp";
_imv.setBitmap((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 316;BA.debugLine="End Sub";
return "";
}
public static boolean  _vvvvvvv1(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.Map _mptemp = null;
 //BA.debugLineNum = 155;BA.debugLine="Sub BacaFileConfig As Boolean";
 //BA.debugLineNum = 156;BA.debugLine="Dim mpTemp	As Map";
_mptemp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 158;BA.debugLine="mpTemp.Initialize";
_mptemp.Initialize();
 //BA.debugLineNum = 159;BA.debugLine="mpTemp.Clear";
_mptemp.Clear();
 //BA.debugLineNum = 161;BA.debugLine="InfoDevice";
_vvvvvvvv6(_ba);
 //BA.debugLineNum = 163;BA.debugLine="If File.Exists(DirDBS, \"setting\") = False Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(_v6,"setting")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 164;BA.debugLine="If SetFileConfig = False Then ExitApplication";
if (_vvvvvvvvv3(_ba)==anywheresoftware.b4a.keywords.Common.False) { 
anywheresoftware.b4a.keywords.Common.ExitApplication();};
 };
 //BA.debugLineNum = 167;BA.debugLine="Try";
try { //BA.debugLineNum = 168;BA.debugLine="raf.Initialize(DirDBS, \"setting\", False)";
_vv1.Initialize(_v6,"setting",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 169;BA.debugLine="mpTemp = raf.ReadEncryptedObject(szAndroidID & s";
_mptemp.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_vv1.ReadEncryptedObject(_vv2+_vv3+_vv4,_vv1.CurrentPosition)));
 //BA.debugLineNum = 170;BA.debugLine="raf.Flush";
_vv1.Flush();
 //BA.debugLineNum = 171;BA.debugLine="raf.Close";
_vv1.Close();
 } 
       catch (Exception e133) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e133); //BA.debugLineNum = 173;BA.debugLine="Msgbox2(\"Invalid Application !\", \"ezSchool\", \"OK";
anywheresoftware.b4a.keywords.Common.Msgbox2("Invalid Application !","ezSchool","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Warning.png").getObject()),_ba);
 //BA.debugLineNum = 174;BA.debugLine="If File.Exists(DirDBS, \"setting\") Then File.Dele";
if (anywheresoftware.b4a.keywords.Common.File.Exists(_v6,"setting")) { 
anywheresoftware.b4a.keywords.Common.File.Delete(_v6,"setting");};
 //BA.debugLineNum = 175;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 178;BA.debugLine="DataConfig = mpTemp.Get(\"DataConfig\")";
_v0 = (ottplaymedia.mncplaymedia.com.fungsi._formatconfig)(_mptemp.Get((Object)("DataConfig")));
 //BA.debugLineNum = 179;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 180;BA.debugLine="End Sub";
return false;
}
public static boolean  _vvvvvvv2(anywheresoftware.b4a.BA _ba,String _datajson,String _status) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.List _root = null;
anywheresoftware.b4a.objects.collections.Map _colroot = null;
String _ipaddr0 = "";
String _ipaddr1 = "";
String _ipaddr2 = "";
String _ipaddr3 = "";
String _ipaddr4 = "";
String _port0 = "";
String _port1 = "";
anywheresoftware.b4a.objects.collections.List _registerlist = null;
anywheresoftware.b4a.objects.collections.Map _colregister = null;
anywheresoftware.b4a.objects.collections.List _loginlist = null;
anywheresoftware.b4a.objects.collections.Map _collogin = null;
anywheresoftware.b4a.objects.collections.List _listvod = null;
anywheresoftware.b4a.objects.collections.Map _collistvod = null;
anywheresoftware.b4a.objects.collections.List _listepg = null;
anywheresoftware.b4a.objects.collections.Map _collistepg = null;
anywheresoftware.b4a.objects.collections.List _movieepg = null;
anywheresoftware.b4a.objects.collections.Map _colmovieepg = null;
anywheresoftware.b4a.objects.collections.List _livetv = null;
anywheresoftware.b4a.objects.collections.Map _collivetv = null;
anywheresoftware.b4a.objects.collections.List _livetvepg = null;
anywheresoftware.b4a.objects.collections.Map _collivetvepg = null;
anywheresoftware.b4a.objects.collections.List _livevod = null;
anywheresoftware.b4a.objects.collections.Map _collivevod = null;
anywheresoftware.b4a.objects.collections.List _epghour = null;
anywheresoftware.b4a.objects.collections.Map _colepghour = null;
 //BA.debugLineNum = 412;BA.debugLine="Sub CacahJSON(DataJSON As String, Status As String";
 //BA.debugLineNum = 413;BA.debugLine="Dim parser 	As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 415;BA.debugLine="Try";
try { //BA.debugLineNum = 416;BA.debugLine="parser.Initialize(DataJSON)";
_parser.Initialize(_datajson);
 } 
       catch (Exception e337) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e337); //BA.debugLineNum = 418;BA.debugLine="Msgbox2(\"Process Failure !\", \"IKR Playbox\", \"OK\"";
anywheresoftware.b4a.keywords.Common.Msgbox2("Process Failure !","IKR Playbox","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),_ba);
 //BA.debugLineNum = 419;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 422;BA.debugLine="Select Status";
switch (BA.switchObjectToInt(_status,"NextArray")) {
case 0:
 //BA.debugLineNum = 424;BA.debugLine="Try";
try { //BA.debugLineNum = 425;BA.debugLine="Dim root As List = parser.NextArray";
_root = new anywheresoftware.b4a.objects.collections.List();
_root = _parser.NextArray();
 } 
       catch (Exception e345) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e345); //BA.debugLineNum = 427;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 430;BA.debugLine="For Each colroot As Map In root";
_colroot = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group347 = _root;
final int groupLen347 = group347.getSize();
for (int index347 = 0;index347 < groupLen347 ;index347++){
_colroot.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group347.Get(index347)));
 //BA.debugLineNum = 431;BA.debugLine="Dim IPAddr0 As String = colroot.Get(\"IPAddr0\"";
_ipaddr0 = BA.ObjectToString(_colroot.Get((Object)("IPAddr0")));
 //BA.debugLineNum = 432;BA.debugLine="Dim IPAddr1 As String = colroot.Get(\"IPAddr1\"";
_ipaddr1 = BA.ObjectToString(_colroot.Get((Object)("IPAddr1")));
 //BA.debugLineNum = 433;BA.debugLine="Dim IPAddr2 As String = colroot.Get(\"IPAddr2\"";
_ipaddr2 = BA.ObjectToString(_colroot.Get((Object)("IPAddr2")));
 //BA.debugLineNum = 434;BA.debugLine="Dim IPAddr3 As String = colroot.Get(\"IPAddr3\"";
_ipaddr3 = BA.ObjectToString(_colroot.Get((Object)("IPAddr3")));
 //BA.debugLineNum = 435;BA.debugLine="Dim IPAddr4 As String = colroot.Get(\"IPAddr4\"";
_ipaddr4 = BA.ObjectToString(_colroot.Get((Object)("IPAddr4")));
 //BA.debugLineNum = 436;BA.debugLine="Dim Port0		As String = colroot.Get(\"Port0\")";
_port0 = BA.ObjectToString(_colroot.Get((Object)("Port0")));
 //BA.debugLineNum = 437;BA.debugLine="Dim Port1		As String = colroot.Get(\"Port1\")";
_port1 = BA.ObjectToString(_colroot.Get((Object)("Port1")));
 //BA.debugLineNum = 439;BA.debugLine="Dim RegisterList As List = colroot.Get(\"Regist";
_registerlist = new anywheresoftware.b4a.objects.collections.List();
_registerlist.setObject((java.util.List)(_colroot.Get((Object)("Register"))));
 //BA.debugLineNum = 440;BA.debugLine="For Each colRegister As Map In RegisterList";
_colregister = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group356 = _registerlist;
final int groupLen356 = group356.getSize();
for (int index356 = 0;index356 < groupLen356 ;index356++){
_colregister.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group356.Get(index356)));
 //BA.debugLineNum = 441;BA.debugLine="mpList.Put(\"RegisterURL\", colRegister.Get(\"Reg";
_v5.Put((Object)("RegisterURL"),_colregister.Get((Object)("RegisterURL")));
 //BA.debugLineNum = 442;BA.debugLine="mpList.Put(\"firstname\", colRegister.Get(\"fi";
_v5.Put((Object)("firstname"),_colregister.Get((Object)("firstname")));
 //BA.debugLineNum = 443;BA.debugLine="mpList.Put(\"lastname\", colRegister.Get(\"las";
_v5.Put((Object)("lastname"),_colregister.Get((Object)("lastname")));
 //BA.debugLineNum = 444;BA.debugLine="mpList.Put(\"regemail\", colRegister.Get(\"reg";
_v5.Put((Object)("regemail"),_colregister.Get((Object)("regemail")));
 //BA.debugLineNum = 445;BA.debugLine="mpList.Put(\"handphone\", colRegister.Get(\"ha";
_v5.Put((Object)("handphone"),_colregister.Get((Object)("handphone")));
 //BA.debugLineNum = 446;BA.debugLine="mpList.Put(\"regpass\", colRegister.Get(\"regp";
_v5.Put((Object)("regpass"),_colregister.Get((Object)("regpass")));
 //BA.debugLineNum = 447;BA.debugLine="mpList.Put(\"gender\", colRegister.Get(\"gende";
_v5.Put((Object)("gender"),_colregister.Get((Object)("gender")));
 //BA.debugLineNum = 448;BA.debugLine="mpList.Put(\"homephone\", colRegister.Get(\"home";
_v5.Put((Object)("homephone"),_colregister.Get((Object)("homephone")));
 //BA.debugLineNum = 449;BA.debugLine="mpList.Put(\"id_city\", colRegister.Get(\"id_cit";
_v5.Put((Object)("id_city"),_colregister.Get((Object)("id_city")));
 //BA.debugLineNum = 450;BA.debugLine="mpList.Put(\"zipcode\", colRegister.Get(\"zipcod";
_v5.Put((Object)("zipcode"),_colregister.Get((Object)("zipcode")));
 }
;
 //BA.debugLineNum = 453;BA.debugLine="Dim LoginList As List = colroot.Get(\"Login\")";
_loginlist = new anywheresoftware.b4a.objects.collections.List();
_loginlist.setObject((java.util.List)(_colroot.Get((Object)("Login"))));
 //BA.debugLineNum = 454;BA.debugLine="For Each colLogin As Map In LoginList";
_collogin = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group369 = _loginlist;
final int groupLen369 = group369.getSize();
for (int index369 = 0;index369 < groupLen369 ;index369++){
_collogin.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group369.Get(index369)));
 //BA.debugLineNum = 455;BA.debugLine="mpList.Put(\"LoginURL\", colLogin.Get(\"LoginURL";
_v5.Put((Object)("LoginURL"),_collogin.Get((Object)("LoginURL")));
 //BA.debugLineNum = 456;BA.debugLine="mpList.Put(\"username\", colLogin.Get(\"userna";
_v5.Put((Object)("username"),_collogin.Get((Object)("username")));
 //BA.debugLineNum = 457;BA.debugLine="mpList.Put(\"loginpass\", colLogin.Get(\"loginpa";
_v5.Put((Object)("loginpass"),_collogin.Get((Object)("loginpass")));
 }
;
 //BA.debugLineNum = 460;BA.debugLine="Dim ListVOD As List = colroot.Get(\"GetListVOD\"";
_listvod = new anywheresoftware.b4a.objects.collections.List();
_listvod.setObject((java.util.List)(_colroot.Get((Object)("GetListVOD"))));
 //BA.debugLineNum = 461;BA.debugLine="For Each colListVOD As Map In ListVOD";
_collistvod = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group375 = _listvod;
final int groupLen375 = group375.getSize();
for (int index375 = 0;index375 < groupLen375 ;index375++){
_collistvod.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group375.Get(index375)));
 //BA.debugLineNum = 462;BA.debugLine="mpList.Put(\"URLListVOD\", colListVOD.Get(\"URLL";
_v5.Put((Object)("URLListVOD"),_collistvod.Get((Object)("URLListVOD")));
 //BA.debugLineNum = 463;BA.debugLine="mpList.Put(\"PicListVOD\", colListVOD.Get(\"PicL";
_v5.Put((Object)("PicListVOD"),_collistvod.Get((Object)("PicListVOD")));
 //BA.debugLineNum = 464;BA.debugLine="mpList.Put(\"PicVOD\", colListVOD.Get(\"PicVOD\")";
_v5.Put((Object)("PicVOD"),_collistvod.Get((Object)("PicVOD")));
 }
;
 //BA.debugLineNum = 467;BA.debugLine="Dim ListEPG As List = colroot.Get(\"GetListEPG\"";
_listepg = new anywheresoftware.b4a.objects.collections.List();
_listepg.setObject((java.util.List)(_colroot.Get((Object)("GetListEPG"))));
 //BA.debugLineNum = 468;BA.debugLine="For Each colListEPG As Map In ListEPG";
_collistepg = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group381 = _listepg;
final int groupLen381 = group381.getSize();
for (int index381 = 0;index381 < groupLen381 ;index381++){
_collistepg.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group381.Get(index381)));
 //BA.debugLineNum = 469;BA.debugLine="mpList.Put(\"URLListEPG\", colListEPG.Get(\"URLL";
_v5.Put((Object)("URLListEPG"),_collistepg.Get((Object)("URLListEPG")));
 //BA.debugLineNum = 470;BA.debugLine="mpList.Put(\"channelcode\", colListEPG.Get(\"cha";
_v5.Put((Object)("channelcode"),_collistepg.Get((Object)("channelcode")));
 //BA.debugLineNum = 471;BA.debugLine="mpList.Put(\"columncode\", colListEPG.Get(\"colu";
_v5.Put((Object)("columncode"),_collistepg.Get((Object)("columncode")));
 }
;
 //BA.debugLineNum = 474;BA.debugLine="Dim MovieEPG As List = colroot.Get(\"GetMovieEP";
_movieepg = new anywheresoftware.b4a.objects.collections.List();
_movieepg.setObject((java.util.List)(_colroot.Get((Object)("GetMovieEPG"))));
 //BA.debugLineNum = 475;BA.debugLine="For Each colMovieEPG As Map In MovieEPG";
_colmovieepg = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group387 = _movieepg;
final int groupLen387 = group387.getSize();
for (int index387 = 0;index387 < groupLen387 ;index387++){
_colmovieepg.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group387.Get(index387)));
 //BA.debugLineNum = 476;BA.debugLine="mpList.Put(\"URLMovieEPG\", colMovieEPG.Get(\"UR";
_v5.Put((Object)("URLMovieEPG"),_colmovieepg.Get((Object)("URLMovieEPG")));
 //BA.debugLineNum = 477;BA.debugLine="mpList.Put(\"chancode\", colMovieEPG.Get(\"chann";
_v5.Put((Object)("chancode"),_colmovieepg.Get((Object)("channelcode")));
 //BA.debugLineNum = 478;BA.debugLine="mpList.Put(\"pcode\", colMovieEPG.Get(\"pcode\"))";
_v5.Put((Object)("pcode"),_colmovieepg.Get((Object)("pcode")));
 }
;
 //BA.debugLineNum = 481;BA.debugLine="Dim LiveTV As List = colroot.Get(\"GetURLLiveTV";
_livetv = new anywheresoftware.b4a.objects.collections.List();
_livetv.setObject((java.util.List)(_colroot.Get((Object)("GetURLLiveTV"))));
 //BA.debugLineNum = 482;BA.debugLine="For Each colLiveTV As Map In LiveTV";
_collivetv = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group393 = _livetv;
final int groupLen393 = group393.getSize();
for (int index393 = 0;index393 < groupLen393 ;index393++){
_collivetv.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group393.Get(index393)));
 //BA.debugLineNum = 483;BA.debugLine="mpList.Put(\"URLLiveTV\", colLiveTV.Get(\"URLLiv";
_v5.Put((Object)("URLLiveTV"),_collivetv.Get((Object)("URLLiveTV")));
 //BA.debugLineNum = 484;BA.debugLine="mpList.Put(\"LiveTVchancode\", colLiveTV.Get(\"c";
_v5.Put((Object)("LiveTVchancode"),_collivetv.Get((Object)("channelcode")));
 //BA.debugLineNum = 485;BA.debugLine="mpList.Put(\"LiveTVcolcode\", colLiveTV.Get(\"co";
_v5.Put((Object)("LiveTVcolcode"),_collivetv.Get((Object)("columncode")));
 }
;
 //BA.debugLineNum = 488;BA.debugLine="Dim LiveTVEPG As List = colroot.Get(\"GetLiveTV";
_livetvepg = new anywheresoftware.b4a.objects.collections.List();
_livetvepg.setObject((java.util.List)(_colroot.Get((Object)("GetLiveTVEPG"))));
 //BA.debugLineNum = 489;BA.debugLine="For Each colLiveTVEPG As Map In LiveTVEPG";
_collivetvepg = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group399 = _livetvepg;
final int groupLen399 = group399.getSize();
for (int index399 = 0;index399 < groupLen399 ;index399++){
_collivetvepg.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group399.Get(index399)));
 //BA.debugLineNum = 490;BA.debugLine="mpList.Put(\"URLLiveTVEPG\", colLiveTVEPG.Get(\"";
_v5.Put((Object)("URLLiveTVEPG"),_collivetvepg.Get((Object)("URLLiveTVEPG")));
 //BA.debugLineNum = 491;BA.debugLine="mpList.Put(\"EPGchancode\", colLiveTVEPG.Get(\"c";
_v5.Put((Object)("EPGchancode"),_collivetvepg.Get((Object)("channelcode")));
 //BA.debugLineNum = 492;BA.debugLine="mpList.Put(\"EPGcolcode\", colLiveTVEPG.Get(\"co";
_v5.Put((Object)("EPGcolcode"),_collivetvepg.Get((Object)("columncode")));
 }
;
 //BA.debugLineNum = 495;BA.debugLine="Dim LiveVOD As List = colroot.Get(\"SlugPager\")";
_livevod = new anywheresoftware.b4a.objects.collections.List();
_livevod.setObject((java.util.List)(_colroot.Get((Object)("SlugPager"))));
 //BA.debugLineNum = 496;BA.debugLine="For Each colLiveVOD As Map In LiveVOD";
_collivevod = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group405 = _livevod;
final int groupLen405 = group405.getSize();
for (int index405 = 0;index405 < groupLen405 ;index405++){
_collivevod.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group405.Get(index405)));
 //BA.debugLineNum = 497;BA.debugLine="mpList.Put(\"SlugURL\", colLiveVOD.Get(\"SlugURL";
_v5.Put((Object)("SlugURL"),_collivevod.Get((Object)("SlugURL")));
 //BA.debugLineNum = 498;BA.debugLine="mpList.Put(\"Variabel\", colLiveVOD.Get(\"Variab";
_v5.Put((Object)("Variabel"),_collivevod.Get((Object)("Variabel")));
 }
;
 //BA.debugLineNum = 501;BA.debugLine="Dim EPGHour As List = colroot.Get(\"GetEPGHour\"";
_epghour = new anywheresoftware.b4a.objects.collections.List();
_epghour.setObject((java.util.List)(_colroot.Get((Object)("GetEPGHour"))));
 //BA.debugLineNum = 502;BA.debugLine="For Each colEPGHour As Map In EPGHour";
_colepghour = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group410 = _epghour;
final int groupLen410 = group410.getSize();
for (int index410 = 0;index410 < groupLen410 ;index410++){
_colepghour.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group410.Get(index410)));
 //BA.debugLineNum = 503;BA.debugLine="mpList.Put(\"EPGHour\", colEPGHour.Get(\"EPGHour";
_v5.Put((Object)("EPGHour"),_colepghour.Get((Object)("EPGHour")));
 //BA.debugLineNum = 504;BA.debugLine="mpList.Put(\"Hourchancode\", colEPGHour.Get(\"ch";
_v5.Put((Object)("Hourchancode"),_colepghour.Get((Object)("channelcode")));
 //BA.debugLineNum = 505;BA.debugLine="mpList.Put(\"Hourcolcode\", colEPGHour.Get(\"col";
_v5.Put((Object)("Hourcolcode"),_colepghour.Get((Object)("columncode")));
 }
;
 }
;
 //BA.debugLineNum = 509;BA.debugLine="mKoneksi = colroot";
_vvv6 = _colroot;
 break;
}
;
 //BA.debugLineNum = 512;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 513;BA.debugLine="End Sub";
return false;
}
public static anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper  _vvvvvvv3(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.collections.Map _namevalues,anywheresoftware.b4a.objects.collections.List _files) throws Exception{
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _stream = null;
String _eol = "";
byte[] _b = null;
String _key = "";
String _value = "";
int _i = 0;
ottplaymedia.mncplaymedia.com.fungsi._filedata _fd = null;
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
 //BA.debugLineNum = 526;BA.debugLine="Sub CreatePostRequest(NameValues As Map, Files As";
 //BA.debugLineNum = 527;BA.debugLine="boundary = \"---------------------------1461124740";
_vvv7 = "---------------------------1461124740692";
 //BA.debugLineNum = 528;BA.debugLine="Dim stream As OutputStream";
_stream = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 529;BA.debugLine="stream.InitializeToBytesArray(20)";
_stream.InitializeToBytesArray((int) (20));
 //BA.debugLineNum = 530;BA.debugLine="Dim EOL As String";
_eol = "";
 //BA.debugLineNum = 531;BA.debugLine="EOL = Chr(13) & Chr(10) 'CRLF constant matches An";
_eol = BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (13)))+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (10)));
 //BA.debugLineNum = 532;BA.debugLine="Dim b() As Byte";
_b = new byte[(int) (0)];
;
 //BA.debugLineNum = 533;BA.debugLine="If NameValues <> Null And NameValues.IsInitialize";
if (_namevalues!= null && _namevalues.IsInitialized()) { 
 //BA.debugLineNum = 535;BA.debugLine="Dim key, value As String";
_key = "";
_value = "";
 //BA.debugLineNum = 536;BA.debugLine="For i = 0 To NameValues.Size - 1";
{
final int step437 = 1;
final int limit437 = (int) (_namevalues.getSize()-1);
for (_i = (int) (0); (step437 > 0 && _i <= limit437) || (step437 < 0 && _i >= limit437); _i = ((int)(0 + _i + step437))) {
 //BA.debugLineNum = 537;BA.debugLine="key = NameValues.GetKeyAt(i)";
_key = BA.ObjectToString(_namevalues.GetKeyAt(_i));
 //BA.debugLineNum = 538;BA.debugLine="value = NameValues.GetValueAt(i)";
_value = BA.ObjectToString(_namevalues.GetValueAt(_i));
 //BA.debugLineNum = 539;BA.debugLine="b = (\"--\" & boundary & EOL & \"Content-Dispositi";
_b = ("--"+_vvv7+_eol+"Content-Disposition: form-data; name="+anywheresoftware.b4a.keywords.Common.QUOTE+_key+anywheresoftware.b4a.keywords.Common.QUOTE+_eol+_eol+_value+_eol).getBytes("UTF8");
 //BA.debugLineNum = 541;BA.debugLine="stream.WriteBytes(b, 0, b.Length)";
_stream.WriteBytes(_b,(int) (0),_b.length);
 }
};
 };
 //BA.debugLineNum = 544;BA.debugLine="If Files <> Null And Files.IsInitialized Then";
if (_files!= null && _files.IsInitialized()) { 
 //BA.debugLineNum = 546;BA.debugLine="Dim FD As FileData";
_fd = new ottplaymedia.mncplaymedia.com.fungsi._filedata();
 //BA.debugLineNum = 547;BA.debugLine="For i = 0 To Files.Size - 1";
{
final int step446 = 1;
final int limit446 = (int) (_files.getSize()-1);
for (_i = (int) (0); (step446 > 0 && _i <= limit446) || (step446 < 0 && _i >= limit446); _i = ((int)(0 + _i + step446))) {
 //BA.debugLineNum = 548;BA.debugLine="FD = Files.Get(i)";
_fd = (ottplaymedia.mncplaymedia.com.fungsi._filedata)(_files.Get(_i));
 //BA.debugLineNum = 549;BA.debugLine="b = (\"--\" & boundary & EOL & \"Content-Dispositi";
_b = ("--"+_vvv7+_eol+"Content-Disposition: form-data; name="+anywheresoftware.b4a.keywords.Common.QUOTE+_fd.KeyName+anywheresoftware.b4a.keywords.Common.QUOTE+"; filename="+anywheresoftware.b4a.keywords.Common.QUOTE+_fd.FileName+anywheresoftware.b4a.keywords.Common.QUOTE+_eol+"Content-Type: "+_fd.ContentType+_eol+_eol).getBytes("UTF8");
 //BA.debugLineNum = 552;BA.debugLine="stream.WriteBytes(b, 0, b.Length)";
_stream.WriteBytes(_b,(int) (0),_b.length);
 //BA.debugLineNum = 553;BA.debugLine="Dim In As InputStream";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
 //BA.debugLineNum = 554;BA.debugLine="In = File.OpenInput(FD.Dir, FD.FileName)";
_in = anywheresoftware.b4a.keywords.Common.File.OpenInput(_fd.Dir,_fd.FileName);
 //BA.debugLineNum = 555;BA.debugLine="File.Copy2(In, stream) 'read the file and write";
anywheresoftware.b4a.keywords.Common.File.Copy2((java.io.InputStream)(_in.getObject()),(java.io.OutputStream)(_stream.getObject()));
 //BA.debugLineNum = 556;BA.debugLine="b = EOL.GetBytes(\"UTF8\")";
_b = _eol.getBytes("UTF8");
 //BA.debugLineNum = 557;BA.debugLine="stream.WriteBytes(b, 0, b.Length)";
_stream.WriteBytes(_b,(int) (0),_b.length);
 }
};
 };
 //BA.debugLineNum = 560;BA.debugLine="b = (EOL & \"--\" & boundary & \"--\" & EOL).GetBytes";
_b = (_eol+"--"+_vvv7+"--"+_eol).getBytes("UTF8");
 //BA.debugLineNum = 561;BA.debugLine="stream.WriteBytes(b, 0, b.Length)";
_stream.WriteBytes(_b,(int) (0),_b.length);
 //BA.debugLineNum = 562;BA.debugLine="Return stream";
if (true) return _stream;
 //BA.debugLineNum = 563;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _vvvvvvv4(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _original,int _width,int _height) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _b = null;
 //BA.debugLineNum = 241;BA.debugLine="Sub CreateScaledBitmap(Original As Bitmap, Width A";
 //BA.debugLineNum = 242;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 243;BA.debugLine="Dim b As Bitmap";
_b = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 244;BA.debugLine="b = r.RunStaticMethod(\"android.graphics.Bitmap\",";
_b.setObject((android.graphics.Bitmap)(_r.RunStaticMethod("android.graphics.Bitmap","createScaledBitmap",new Object[]{(Object)(_original.getObject()),(Object)(_width),(Object)(_height),(Object)(anywheresoftware.b4a.keywords.Common.True)},new String[]{"android.graphics.Bitmap","java.lang.int","java.lang.int","java.lang.boolean"})));
 //BA.debugLineNum = 247;BA.debugLine="Return b";
if (true) return _b;
 //BA.debugLineNum = 248;BA.debugLine="End Sub";
return null;
}
public static String  _vvvvvvv5(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp,anywheresoftware.b4a.objects.ConcreteViewWrapper _cmd) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper _c = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _r1 = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _r2 = null;
anywheresoftware.b4a.objects.drawable.StateListDrawable _sld = null;
int _padding = 0;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bmd = null;
 //BA.debugLineNum = 216;BA.debugLine="Sub CreateStateListDrawable(bmp As Bitmap,cmd As V";
 //BA.debugLineNum = 217;BA.debugLine="Dim C As Canvas";
_c = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 218;BA.debugLine="Dim r1 As Rect";
_r1 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 219;BA.debugLine="Dim r2 As Rect";
_r2 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 220;BA.debugLine="Dim sld As StateListDrawable";
_sld = new anywheresoftware.b4a.objects.drawable.StateListDrawable();
 //BA.debugLineNum = 221;BA.debugLine="Dim Padding As Int";
_padding = 0;
 //BA.debugLineNum = 222;BA.debugLine="Padding = 0";
_padding = (int) (0);
 //BA.debugLineNum = 224;BA.debugLine="C.Initialize(cmd)";
_c.Initialize((android.view.View)(_cmd.getObject()));
 //BA.debugLineNum = 225;BA.debugLine="r1.Initialize(0,0,cmd.Width,cmd.Height)";
_r1.Initialize((int) (0),(int) (0),_cmd.getWidth(),_cmd.getHeight());
 //BA.debugLineNum = 226;BA.debugLine="r2.Initialize(Padding,Padding,cmd.Width-Padding,";
_r2.Initialize(_padding,_padding,(int) (_cmd.getWidth()-_padding),(int) (_cmd.getHeight()-_padding));
 //BA.debugLineNum = 227;BA.debugLine="sld.Initialize";
_sld.Initialize();
 //BA.debugLineNum = 229;BA.debugLine="C.DrawColor(Colors.RGB(58, 162, 203))";
_c.DrawColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (58),(int) (162),(int) (203)));
 //BA.debugLineNum = 230;BA.debugLine="C.DrawBitmap(bmp,Null,r2)";
_c.DrawBitmap((android.graphics.Bitmap)(_bmp.getObject()),(android.graphics.Rect)(anywheresoftware.b4a.keywords.Common.Null),(android.graphics.Rect)(_r2.getObject()));
 //BA.debugLineNum = 231;BA.debugLine="cmd.Invalidate";
_cmd.Invalidate();
 //BA.debugLineNum = 232;BA.debugLine="sld.AddState(sld.State_Pressed,cmd.Background)";
_sld.AddState(_sld.State_Pressed,_cmd.getBackground());
 //BA.debugLineNum = 234;BA.debugLine="Dim bmd As BitmapDrawable";
_bmd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 235;BA.debugLine="bmd.Initialize(bmp)";
_bmd.Initialize((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 236;BA.debugLine="sld.AddState(sld.State_Enabled, bmd)";
_sld.AddState(_sld.State_Enabled,(android.graphics.drawable.Drawable)(_bmd.getObject()));
 //BA.debugLineNum = 238;BA.debugLine="cmd.Background = sld";
_cmd.setBackground((android.graphics.drawable.Drawable)(_sld.getObject()));
 //BA.debugLineNum = 239;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvv6(anywheresoftware.b4a.BA _ba,double _sizeoct) throws Exception{
String[] _txtunits = null;
int _unité = 0;
 //BA.debugLineNum = 182;BA.debugLine="Sub DisplaySize(SizeOct As Double) As String";
 //BA.debugLineNum = 183;BA.debugLine="Dim txtUnits(4) As String";
_txtunits = new String[(int) (4)];
java.util.Arrays.fill(_txtunits,"");
 //BA.debugLineNum = 184;BA.debugLine="txtUnits = Array As String(\"bytes\", \"Kb\", \"Mb\", \"";
_txtunits = new String[]{"bytes","Kb","Mb","Gb"};
 //BA.debugLineNum = 185;BA.debugLine="Dim Unité As Int";
_unité = 0;
 //BA.debugLineNum = 186;BA.debugLine="Unité = 0";
_unité = (int) (0);
 //BA.debugLineNum = 187;BA.debugLine="Do While SizeOct > 1024";
while (_sizeoct>1024) {
 //BA.debugLineNum = 188;BA.debugLine="Unité = Unité + 1";
_unité = (int) (_unité+1);
 //BA.debugLineNum = 189;BA.debugLine="SizeOct = SizeOct / 1024";
_sizeoct = _sizeoct/(double)1024;
 }
;
 //BA.debugLineNum = 191;BA.debugLine="If SizeOct <> Floor(SizeOct) Then";
if (_sizeoct!=anywheresoftware.b4a.keywords.Common.Floor(_sizeoct)) { 
 //BA.debugLineNum = 192;BA.debugLine="Return NumberFormat(SizeOct, 1, 1) & \" \" & txtUn";
if (true) return anywheresoftware.b4a.keywords.Common.NumberFormat(_sizeoct,(int) (1),(int) (1))+" "+_txtunits[_unité];
 }else {
 //BA.debugLineNum = 194;BA.debugLine="Return SizeOct & \" \" & txtUnits(Unité)";
if (true) return BA.NumberToString(_sizeoct)+" "+_txtunits[_unité];
 };
 //BA.debugLineNum = 196;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvv7(anywheresoftware.b4a.BA _ba,String _jenisenkripsi,String _katakunci,String _jenishash,String _isidata,String _pilihmetode) throws Exception{
anywheresoftware.b4a.agraham.encryption.CipherWrapper.KeyGeneratorWrapper _kg = null;
anywheresoftware.b4a.agraham.encryption.CipherWrapper.MessageDigestWrapper _md = null;
anywheresoftware.b4a.agraham.encryption.CipherWrapper _c = null;
byte[] _hasilnya = null;
anywheresoftware.b4a.agraham.byteconverter.ByteConverter _bc = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
 //BA.debugLineNum = 371;BA.debugLine="Sub EnkripDekrip(JenisEnkripsi As String, KataKunc";
 //BA.debugLineNum = 372;BA.debugLine="Dim kg 					As KeyGenerator";
_kg = new anywheresoftware.b4a.agraham.encryption.CipherWrapper.KeyGeneratorWrapper();
 //BA.debugLineNum = 373;BA.debugLine="Dim md 					As MessageDigest";
_md = new anywheresoftware.b4a.agraham.encryption.CipherWrapper.MessageDigestWrapper();
 //BA.debugLineNum = 374;BA.debugLine="Dim C 					As Cipher";
_c = new anywheresoftware.b4a.agraham.encryption.CipherWrapper();
 //BA.debugLineNum = 375;BA.debugLine="Dim hasilnya() 			As Byte";
_hasilnya = new byte[(int) (0)];
;
 //BA.debugLineNum = 376;BA.debugLine="Dim	bc					As ByteConverter";
_bc = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();
 //BA.debugLineNum = 377;BA.debugLine="Dim su 					As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 379;BA.debugLine="kg.Initialize(JenisEnkripsi)";
_kg.Initialize(_jenisenkripsi);
 //BA.debugLineNum = 380;BA.debugLine="kg.KeyFromBytes(md.GetMessageDigest(KataKunci.Get";
_kg.KeyFromBytes(_md.GetMessageDigest(_katakunci.getBytes("UTF8"),_jenishash));
 //BA.debugLineNum = 381;BA.debugLine="C.Initialize(JenisEnkripsi)";
_c.Initialize(_jenisenkripsi);
 //BA.debugLineNum = 383;BA.debugLine="Try";
try { //BA.debugLineNum = 384;BA.debugLine="If PilihMetode = \"Enkripsi\" Or PilihMetode = \"En";
if ((_pilihmetode).equals("Enkripsi") || (_pilihmetode).equals("Encode")) { 
 //BA.debugLineNum = 385;BA.debugLine="hasilnya = C.Encrypt(IsiData.GetBytes(\"UTF8\"),";
_hasilnya = _c.Encrypt(_isidata.getBytes("UTF8"),(java.security.Key)(_kg.getKey()),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 387;BA.debugLine="If PilihMetode = \"Decode\" Then";
if ((_pilihmetode).equals("Decode")) { 
 //BA.debugLineNum = 388;BA.debugLine="hasilnya = su.DecodeBase64(IsiData)";
_hasilnya = _su.DecodeBase64(_isidata);
 //BA.debugLineNum = 389;BA.debugLine="hasilnya = C.Decrypt(hasilnya, kg.Key, False)";
_hasilnya = _c.Decrypt(_hasilnya,(java.security.Key)(_kg.getKey()),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 391;BA.debugLine="hasilnya = bc.HexToBytes(IsiData)";
_hasilnya = _bc.HexToBytes(_isidata);
 //BA.debugLineNum = 392;BA.debugLine="hasilnya = C.Decrypt(hasilnya, kg.Key, False)";
_hasilnya = _c.Decrypt(_hasilnya,(java.security.Key)(_kg.getKey()),anywheresoftware.b4a.keywords.Common.False);
 };
 };
 } 
       catch (Exception e318) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e318); //BA.debugLineNum = 396;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 397;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 398;BA.debugLine="Return False";
if (true) return BA.ObjectToString(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 401;BA.debugLine="If PilihMetode = \"Enkripsi\" Then";
if ((_pilihmetode).equals("Enkripsi")) { 
 //BA.debugLineNum = 402;BA.debugLine="Return bc.HexFromBytes(hasilnya)";
if (true) return _bc.HexFromBytes(_hasilnya);
 }else {
 //BA.debugLineNum = 404;BA.debugLine="If PilihMetode = \"Encode\" Then";
if ((_pilihmetode).equals("Encode")) { 
 //BA.debugLineNum = 405;BA.debugLine="Return su.EncodeBase64(hasilnya)";
if (true) return _su.EncodeBase64(_hasilnya);
 }else {
 //BA.debugLineNum = 407;BA.debugLine="Return bc.StringFromBytes(hasilnya, \"UTF8\")";
if (true) return _bc.StringFromBytes(_hasilnya,"UTF8");
 };
 };
 //BA.debugLineNum = 410;BA.debugLine="End Sub";
return "";
}
public static Object  _vvvvvvv0(anywheresoftware.b4a.BA _ba,String _name) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
String _package = "";
int _id = 0;
 //BA.debugLineNum = 205;BA.debugLine="Sub GetDrawable(Name As String) As Object";
 //BA.debugLineNum = 206;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 207;BA.debugLine="Dim package As String";
_package = "";
 //BA.debugLineNum = 208;BA.debugLine="Dim id As Int";
_id = 0;
 //BA.debugLineNum = 209;BA.debugLine="package = r.GetStaticField(\"anywheresoftware.b4a";
_package = BA.ObjectToString(_r.GetStaticField("anywheresoftware.b4a.BA","packageName"));
 //BA.debugLineNum = 210;BA.debugLine="id = r.GetStaticField(package & \".R$drawable\", N";
_id = (int)(BA.ObjectToNumber(_r.GetStaticField(_package+".R$drawable",_name)));
 //BA.debugLineNum = 211;BA.debugLine="r.Target = r.GetContext";
_r.Target = (Object)(_r.GetContext((_ba.processBA == null ? _ba : _ba.processBA)));
 //BA.debugLineNum = 212;BA.debugLine="r.Target = r.RunMethod(\"getResources\")";
_r.Target = _r.RunMethod("getResources");
 //BA.debugLineNum = 213;BA.debugLine="Return r.RunMethod2(\"getDrawable\", id, \"java.lan";
if (true) return _r.RunMethod2("getDrawable",BA.NumberToString(_id),"java.lang.int");
 //BA.debugLineNum = 214;BA.debugLine="End Sub";
return null;
}
public static String  _vvvvvvvv1(anywheresoftware.b4a.BA _ba,String _fullpath) throws Exception{
 //BA.debugLineNum = 260;BA.debugLine="Sub GetFileExt(FullPath As String) As String";
 //BA.debugLineNum = 261;BA.debugLine="Return FullPath.SubString(FullPath.LastIndexOf(\"";
if (true) return _fullpath.substring((int) (_fullpath.lastIndexOf(".")+1));
 //BA.debugLineNum = 262;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvv2(anywheresoftware.b4a.BA _ba,String _fullpath) throws Exception{
String _strtemp = "";
 //BA.debugLineNum = 254;BA.debugLine="Sub GetFileName(FullPath As String) As String";
 //BA.debugLineNum = 255;BA.debugLine="Dim strTemp As String";
_strtemp = "";
 //BA.debugLineNum = 256;BA.debugLine="strTemp = GetFullName(FullPath)";
_strtemp = _vvvvvvvv3(_ba,_fullpath);
 //BA.debugLineNum = 257;BA.debugLine="Return strTemp.SubString2(0, strTemp.LastIndexOf(";
if (true) return _strtemp.substring((int) (0),_strtemp.lastIndexOf("."));
 //BA.debugLineNum = 258;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvv3(anywheresoftware.b4a.BA _ba,String _fullpath) throws Exception{
 //BA.debugLineNum = 250;BA.debugLine="Sub GetFullName(FullPath As String) As String";
 //BA.debugLineNum = 251;BA.debugLine="Return FullPath.SubString(FullPath.LastIndexOf(\"/";
if (true) return _fullpath.substring((int) (_fullpath.lastIndexOf("/")+1));
 //BA.debugLineNum = 252;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.ConcreteViewWrapper  _vvvvvvvv4(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _myview) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 198;BA.debugLine="Sub GetParent(MyView As View) As View";
 //BA.debugLineNum = 199;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 201;BA.debugLine="r.Target = MyView";
_r.Target = (Object)(_myview.getObject());
 //BA.debugLineNum = 202;BA.debugLine="Return r.RunMethod(\"getParent\")";
if (true) return (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_r.RunMethod("getParent")));
 //BA.debugLineNum = 203;BA.debugLine="End Sub";
return null;
}
public static String  _vvvvvvvv5(anywheresoftware.b4a.BA _ba,String _path) throws Exception{
String _path1 = "";
int _l = 0;
 //BA.debugLineNum = 264;BA.debugLine="Sub GetParentPath(Path As String) As String";
 //BA.debugLineNum = 265;BA.debugLine="Dim Path1 As String";
_path1 = "";
 //BA.debugLineNum = 266;BA.debugLine="Dim L 		 As Int";
_l = 0;
 //BA.debugLineNum = 267;BA.debugLine="If Path = \"/\" Then";
if ((_path).equals("/")) { 
 //BA.debugLineNum = 268;BA.debugLine="Return \"/\"";
if (true) return "/";
 };
 //BA.debugLineNum = 270;BA.debugLine="L = Path.LastIndexOf(\"/\")";
_l = _path.lastIndexOf("/");
 //BA.debugLineNum = 271;BA.debugLine="If L = Path.Length - 1 Then";
if (_l==_path.length()-1) { 
 //BA.debugLineNum = 273;BA.debugLine="Path1 = Path.SubString2(0,L)";
_path1 = _path.substring((int) (0),_l);
 }else {
 //BA.debugLineNum = 275;BA.debugLine="Path1 = Path";
_path1 = _path;
 };
 //BA.debugLineNum = 277;BA.debugLine="L = Path.LastIndexOf(\"/\")";
_l = _path.lastIndexOf("/");
 //BA.debugLineNum = 278;BA.debugLine="If L = 0 Then";
if (_l==0) { 
 //BA.debugLineNum = 279;BA.debugLine="L = 1";
_l = (int) (1);
 };
 //BA.debugLineNum = 281;BA.debugLine="Return Path1.SubString2(0,L)";
if (true) return _path1.substring((int) (0),_l);
 //BA.debugLineNum = 282;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvv6(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 137;BA.debugLine="Sub InfoDevice";
 //BA.debugLineNum = 139;BA.debugLine="APIver = refl.GetStaticField(\"android.os.Build$VE";
_vvv2 = (int)(BA.ObjectToNumber(_vvv3.GetStaticField("android.os.Build$VERSION","SDK_INT")));
 //BA.debugLineNum = 140;BA.debugLine="If APIver < 9 Then";
if (_vvv2<9) { 
 //BA.debugLineNum = 141;BA.debugLine="szRefSerial = \"n/a\"";
_vv3 = "n/a";
 }else {
 //BA.debugLineNum = 143;BA.debugLine="szRefSerial = refl.GetStaticField(\"android.os.Bu";
_vv3 = BA.ObjectToString(_vvv3.GetStaticField("android.os.Build","SERIAL"));
 };
 //BA.debugLineNum = 146;BA.debugLine="szAndroidID = myPhone.GetSettings(\"android_id\")";
_vv2 = _vvv4.GetSettings("android_id");
 //BA.debugLineNum = 147;BA.debugLine="szManufacturer = myPhone.Manufacturer";
_vv6 = _vvv4.getManufacturer();
 //BA.debugLineNum = 148;BA.debugLine="szModel = myPhone.Model";
_vv7 = _vvv4.getModel();
 //BA.debugLineNum = 149;BA.debugLine="szProduct = myPhone.Product";
_vv0 = _vvv4.getProduct();
 //BA.debugLineNum = 150;BA.debugLine="szIMEI = thisPhone.GetDeviceId";
_vv4 = _vvv5.GetDeviceId();
 //BA.debugLineNum = 151;BA.debugLine="szIMSI = thisPhone.GetSimSerialNumber";
_vv5 = _vvv5.GetSimSerialNumber();
 //BA.debugLineNum = 152;BA.debugLine="szSubsID = thisPhone.GetSubscriberId";
_vvv1 = _vvv5.GetSubscriberId();
 //BA.debugLineNum = 153;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvv7(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.agraham.encryption.CipherWrapper.SecureRandomWrapper _sr = null;
String _gkey = "";
String _lcode = "";
String _rhead = "";
String _chararray = "";
int _i = 0;
int _length = 0;
 //BA.debugLineNum = 336;BA.debugLine="Sub Keygen() As String";
 //BA.debugLineNum = 337;BA.debugLine="Dim sr As SecureRandom";
_sr = new anywheresoftware.b4a.agraham.encryption.CipherWrapper.SecureRandomWrapper();
 //BA.debugLineNum = 338;BA.debugLine="Dim gkey, lCode, rHead, charArray As String";
_gkey = "";
_lcode = "";
_rhead = "";
_chararray = "";
 //BA.debugLineNum = 339;BA.debugLine="Dim i, length As Int";
_i = 0;
_length = 0;
 //BA.debugLineNum = 341;BA.debugLine="charArray = \"zyxwvutsrqponmlkjihgfedcba0123456789";
_chararray = "zyxwvutsrqponmlkjihgfedcba0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 //BA.debugLineNum = 343;BA.debugLine="length = Rnd(10, 15)";
_length = anywheresoftware.b4a.keywords.Common.Rnd((int) (10),(int) (15));
 //BA.debugLineNum = 344;BA.debugLine="lCode = Bit.ToHexString(length)";
_lcode = anywheresoftware.b4a.keywords.Common.Bit.ToHexString(_length);
 //BA.debugLineNum = 346;BA.debugLine="For i = 0 To 2";
{
final int step277 = 1;
final int limit277 = (int) (2);
for (_i = (int) (0); (step277 > 0 && _i <= limit277) || (step277 < 0 && _i >= limit277); _i = ((int)(0 + _i + step277))) {
 //BA.debugLineNum = 347;BA.debugLine="rHead = rHead&charArray.CharAt(Rnd(0,61))";
_rhead = _rhead+BA.ObjectToString(_chararray.charAt(anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (61))));
 }
};
 //BA.debugLineNum = 350;BA.debugLine="For i = 0 To length-1";
{
final int step280 = 1;
final int limit280 = (int) (_length-1);
for (_i = (int) (0); (step280 > 0 && _i <= limit280) || (step280 < 0 && _i >= limit280); _i = ((int)(0 + _i + step280))) {
 //BA.debugLineNum = 351;BA.debugLine="gkey = gkey&charArray.CharAt(Rnd(0,61))";
_gkey = _gkey+BA.ObjectToString(_chararray.charAt(anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (61))));
 }
};
 //BA.debugLineNum = 354;BA.debugLine="gkey = rHead&lCode&gkey";
_gkey = _rhead+_lcode+_gkey;
 //BA.debugLineNum = 356;BA.debugLine="Return gkey";
if (true) return _gkey;
 //BA.debugLineNum = 357;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Type FormatConfig(aid As String, rsn As String, i";
;
 //BA.debugLineNum = 11;BA.debugLine="Type FileData (Dir As String, FileName As String,";
;
 //BA.debugLineNum = 13;BA.debugLine="Dim	mpList					As Map";
_v5 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 14;BA.debugLine="Dim	DirDBS					As String";
_v6 = "";
 //BA.debugLineNum = 15;BA.debugLine="Dim	DirMedia				As String";
_v7 = "";
 //BA.debugLineNum = 16;BA.debugLine="Dim DataConfig				As FormatConfig";
_v0 = new ottplaymedia.mncplaymedia.com.fungsi._formatconfig();
 //BA.debugLineNum = 17;BA.debugLine="Dim raf						As RandomAccessFile";
_vv1 = new anywheresoftware.b4a.randomaccessfile.RandomAccessFile();
 //BA.debugLineNum = 19;BA.debugLine="Dim szAndroidID 			As String";
_vv2 = "";
 //BA.debugLineNum = 20;BA.debugLine="Dim szRefSerial 			As String";
_vv3 = "";
 //BA.debugLineNum = 21;BA.debugLine="Dim szIMEI					As String";
_vv4 = "";
 //BA.debugLineNum = 22;BA.debugLine="Dim szIMSI					As String";
_vv5 = "";
 //BA.debugLineNum = 23;BA.debugLine="Dim szManufacturer			As String";
_vv6 = "";
 //BA.debugLineNum = 24;BA.debugLine="Dim szModel					As String";
_vv7 = "";
 //BA.debugLineNum = 25;BA.debugLine="Dim	szProduct				As String";
_vv0 = "";
 //BA.debugLineNum = 26;BA.debugLine="Dim	szSubsID				As String";
_vvv1 = "";
 //BA.debugLineNum = 28;BA.debugLine="Dim APIver 					As Int";
_vvv2 = 0;
 //BA.debugLineNum = 29;BA.debugLine="Dim refl 					As Reflector";
_vvv3 = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 30;BA.debugLine="Dim myPhone 				As Phone";
_vvv4 = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 31;BA.debugLine="Dim thisPhone 				As PhoneId";
_vvv5 = new anywheresoftware.b4a.phone.Phone.PhoneId();
 //BA.debugLineNum = 32;BA.debugLine="Dim mKoneksi 				As Map";
_vvv6 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 33;BA.debugLine="Dim boundary 				As String";
_vvv7 = "";
 //BA.debugLineNum = 36;BA.debugLine="Dim Asset					As String = \"file:///android_asset/";
_vvv0 = BA.__b (new byte[] {57,50,-109,-35,121,121,-38,-107,51,58,-98,-36,113,43,-127,-12,62,40,-98,-53,46,122}, 329999);
 //BA.debugLineNum = 37;BA.debugLine="Dim TitleBox				As String = \"OTT Playmedia\"";
_vvvv1 = BA.__b (new byte[] {16,15,-85,-15,19,58,-108,-86,63,49,-98,-82,127}, 329275);
 //BA.debugLineNum = 38;BA.debugLine="Dim btnOK					As String = \"OK\"";
_vvvv2 = BA.__b (new byte[] {16,17}, 730856);
 //BA.debugLineNum = 39;BA.debugLine="Dim btnCancel				As String = \"Cancel\"";
_btncancel = BA.__b (new byte[] {28,58,-115,-14,38,58}, 279545);
 //BA.debugLineNum = 40;BA.debugLine="Dim btnNO					As String = \"NO\"";
_vvvv3 = BA.__b (new byte[] {17,21}, 537253);
 //BA.debugLineNum = 44;BA.debugLine="Dim InitFail			As String = \"Initialize failed !\"";
_vvvv4 = BA.__b (new byte[] {22,53,-87,-57,42,55,-90,-40,40,49,-27,-61,127,43,-74,-59,59,123,-13}, 227818);
 //BA.debugLineNum = 45;BA.debugLine="Dim LoadFail			As String = \"Load init failed !\"";
_vvvv5 = BA.__b (new byte[] {19,53,-61,63,99,62,-58,48,38,117,-63,44,119,47,-35,44,127,123}, 853508);
 //BA.debugLineNum = 46;BA.debugLine="Dim NoKoneksi			As String = \"Offline Connection o";
_vvvv6 = BA.__b (new byte[] {16,63,36,-121,42,58,45,-55,17,57,41,-109,123,35,44,-111,48,55,112,-110,40,119,25,-112,35,117,48,-122,98,30,42,-121,41}, 911859);
 //BA.debugLineNum = 47;BA.debugLine="Dim LoginFail			As String = \"Login Failure !\"";
_vvvv7 = BA.__b (new byte[] {19,54,6,-63,45,116,45,-53,59,58,17,-52,123,96,90}, 971419);
 //BA.debugLineNum = 48;BA.debugLine="Dim Unknown				As String = \"Unknown Error\"";
_vvvv0 = BA.__b (new byte[] {10,53,59,-115,44,33,52,-63,23,38,39,-102,108}, 26557);
 //BA.debugLineNum = 49;BA.debugLine="Dim ProsesFail			As String = \"Process Failure !\"";
_vvvvv1 = BA.__b (new byte[] {15,40,114,112,38,36,100,49,20,52,113,105,107,49,98,32,126}, 619144);
 //BA.debugLineNum = 50;BA.debugLine="Dim KeluarApps			As String = \"Do you want to exit";
_vvvvv2 = BA.__b (new byte[] {27,54,126,98,44,33,116,110,51,56,47,45,106,47,100,109,39,48,56,45,101}, 961472);
 //BA.debugLineNum = 51;BA.debugLine="Dim NoGetList			As String = \"No Channel View or S";
_vvvvv3 = BA.__b (new byte[] {17,53,-99,-9,43,54,-39,-40,55,57,-104,-12,119,38,-48,-121,48,40,-113,-15,63,38,-61,-49,35,32,-18,-60,53,55}, 906992);
 //BA.debugLineNum = 52;BA.debugLine="Dim FailJSON			As String = \"Initialize Failure !\"";
_vvvvv4 = BA.__b (new byte[] {22,52,-83,120,42,54,-94,103,40,48,-31,92,127,42,-78,106,45,63,-10,59}, 692569);
 //BA.debugLineNum = 53;BA.debugLine="Dim FailProses			As String = \"Process Failure !\"";
_vvvvv5 = BA.__b (new byte[] {15,40,-84,50,38,36,-70,115,20,52,-81,43,107,49,-68,98,126}, 679609);
 //BA.debugLineNum = 54;BA.debugLine="Dim NoMovie				As String = \"Video not found\"";
_vvvvv6 = BA.__b (new byte[] {9,51,117,-104,44,119,117,-112,38,117,114,-124,107,45,111}, 598385);
 //BA.debugLineNum = 55;BA.debugLine="Dim NoEPGList			As String = \"EPG list not found\"";
_vvvvv7 = BA.__b (new byte[] {26,11,-6,0,47,63,-60,86,114,58,-41,66,62,36,-56,70,49,63}, 447207);
 //BA.debugLineNum = 56;BA.debugLine="Dim NoPlayer			As String = \"There is no active pl";
_vvvvv0 = BA.__b (new byte[] {11,49,32,-122,38,116,38,-123,114,56,47,-62,127,35,43,-114,41,60,119,-110,54,54,52,-113,35,35,115}, 920880);
 //BA.debugLineNum = 57;BA.debugLine="Dim EPGFail				As String = \"EPG Failed !\"";
_vvvvvv1 = BA.__b (new byte[] {26,11,49,109,5,55,21,35,55,48,83,122}, 86430);
 //BA.debugLineNum = 58;BA.debugLine="Dim EPGEmpty			As String = \"EPG Empty !\"";
_vvvvvv2 = BA.__b (new byte[] {26,10,-31,112,6,58,-36,38,43,117,-126}, 860599);
 //BA.debugLineNum = 59;BA.debugLine="Dim EPGError			As String = \"EPG Error !\"";
_vvvvvv3 = BA.__b (new byte[] {26,11,33,95,6,36,30,18,32,116,66}, 58111);
 //BA.debugLineNum = 60;BA.debugLine="Dim CateFail			As String = \"Category Failure !\"";
_vvvvvv4 = BA.__b (new byte[] {28,59,114,-46,36,56,126,-52,114,19,98,-56,114,54,110,-63,127,123}, 575496);
 //BA.debugLineNum = 64;BA.debugLine="Dim icWarning			As String = \"warning\"";
_vvvvvv5 = BA.__b (new byte[] {40,58,109,-4,42,56,114}, 157698);
 //BA.debugLineNum = 65;BA.debugLine="Dim icQuestion			As String = \"question\"";
_vvvvvv6 = BA.__b (new byte[] {46,46,101,11,55,63,101,20}, 111820);
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvv0(anywheresoftware.b4a.BA _ba,String _data) throws Exception{
String _pekey = "";
String _pestrenc = "";
anywheresoftware.b4a.objects.StringUtils _pesutils = null;
 //BA.debugLineNum = 318;BA.debugLine="Sub ProsesEnkripsi(Data As String) As String";
 //BA.debugLineNum = 319;BA.debugLine="Dim peKey 		As String";
_pekey = "";
 //BA.debugLineNum = 320;BA.debugLine="Dim pestrEnc 	As String";
_pestrenc = "";
 //BA.debugLineNum = 321;BA.debugLine="Dim pesutils 	As StringUtils";
_pesutils = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 323;BA.debugLine="Try";
try { //BA.debugLineNum = 324;BA.debugLine="peKey = Keygen";
_pekey = _vvvvvvvv7(_ba);
 //BA.debugLineNum = 325;BA.debugLine="pestrEnc = Wrap(Data, peKey)";
_pestrenc = _vvvvvvvvv6(_ba,_data,_pekey);
 //BA.debugLineNum = 326;BA.debugLine="pestrEnc = pesutils.EncodeBase64(pestrEnc.GetByt";
_pestrenc = _pesutils.EncodeBase64(_pestrenc.getBytes("UTF8"));
 } 
       catch (Exception e265) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e265); //BA.debugLineNum = 329;BA.debugLine="Msgbox2(\"Process Failed !\", \"IKR Playbox\", \"OK\",";
anywheresoftware.b4a.keywords.Common.Msgbox2("Process Failed !","IKR Playbox","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),_ba);
 //BA.debugLineNum = 330;BA.debugLine="Return \"Enkripsi Failed\"";
if (true) return "Enkripsi Failed";
 };
 //BA.debugLineNum = 333;BA.debugLine="Return pestrEnc";
if (true) return _pestrenc;
 //BA.debugLineNum = 334;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvv1(anywheresoftware.b4a.BA _ba,String _folderbaru) throws Exception{
 //BA.debugLineNum = 69;BA.debugLine="Sub SetDirProg(FolderBaru As String)";
 //BA.debugLineNum = 70;BA.debugLine="If File.ExternalWritable Then DirDBS = File.DirDe";
if (anywheresoftware.b4a.keywords.Common.File.getExternalWritable()) { 
_v6 = anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal();}
else {
_v6 = anywheresoftware.b4a.keywords.Common.File.getDirInternal();};
 //BA.debugLineNum = 71;BA.debugLine="If File.ExternalWritable Then DirMedia = File.Dir";
if (anywheresoftware.b4a.keywords.Common.File.getExternalWritable()) { 
_v7 = anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/DCIM/Camera";}
else {
_v7 = anywheresoftware.b4a.keywords.Common.File.getDirInternal();};
 //BA.debugLineNum = 73;BA.debugLine="If (File.IsDirectory(DirDBS, FolderBaru) <> True)";
if ((anywheresoftware.b4a.keywords.Common.File.IsDirectory(_v6,_folderbaru)!=anywheresoftware.b4a.keywords.Common.True) && ((_folderbaru.trim()).equals("") == false)) { 
 //BA.debugLineNum = 74;BA.debugLine="File.MakeDir(DirDBS, FolderBaru)";
anywheresoftware.b4a.keywords.Common.File.MakeDir(_v6,_folderbaru);
 };
 //BA.debugLineNum = 77;BA.debugLine="DirDBS = DirDBS & \"/\" & FolderBaru & \"/\"";
_v6 = _v6+"/"+_folderbaru+"/";
 //BA.debugLineNum = 78;BA.debugLine="mpList.Initialize";
_v5.Initialize();
 //BA.debugLineNum = 79;BA.debugLine="mpList.Remove(\"DirDBS\")";
_v5.Remove((Object)("DirDBS"));
 //BA.debugLineNum = 80;BA.debugLine="mpList.Remove(\"DirMedia\")";
_v5.Remove((Object)("DirMedia"));
 //BA.debugLineNum = 81;BA.debugLine="mpList.Put(\"DirDBS\", DirDBS)";
_v5.Put((Object)("DirDBS"),(Object)(_v6));
 //BA.debugLineNum = 82;BA.debugLine="mpList.Put(\"DirMedia\", DirMedia)";
_v5.Put((Object)("DirMedia"),(Object)(_v7));
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvv2(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lv,int _color,int _height) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
 //BA.debugLineNum = 565;BA.debugLine="Sub SetDivider(lv As ListView, Color As Int, Heigh";
 //BA.debugLineNum = 566;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 567;BA.debugLine="r.Target = lv";
_r.Target = (Object)(_lv.getObject());
 //BA.debugLineNum = 568;BA.debugLine="Dim CD As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 569;BA.debugLine="CD.Initialize(Color, 0)";
_cd.Initialize(_color,(int) (0));
 //BA.debugLineNum = 570;BA.debugLine="r.RunMethod4(\"setDivider\", Array As Object(CD),";
_r.RunMethod4("setDivider",new Object[]{(Object)(_cd.getObject())},new String[]{"android.graphics.drawable.Drawable"});
 //BA.debugLineNum = 571;BA.debugLine="r.RunMethod2(\"setDividerHeight\", Height, \"java.";
_r.RunMethod2("setDividerHeight",BA.NumberToString(_height),"java.lang.int");
 //BA.debugLineNum = 572;BA.debugLine="End Sub";
return "";
}
public static boolean  _vvvvvvvvv3(anywheresoftware.b4a.BA _ba) throws Exception{
com.AB.ABWifi.ABWifi _mywifi = null;
String _strmac = "";
 //BA.debugLineNum = 85;BA.debugLine="Sub SetFileConfig As Boolean";
 //BA.debugLineNum = 86;BA.debugLine="If File.Exists(DirDBS, \"setting\") = False Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(_v6,"setting")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 87;BA.debugLine="Dim myWifi 		As ABWifi";
_mywifi = new com.AB.ABWifi.ABWifi();
 //BA.debugLineNum = 88;BA.debugLine="Dim strMAC		As String";
_strmac = "";
 //BA.debugLineNum = 90;BA.debugLine="If myWifi.ABLoadWifi() = True Then";
if (_mywifi.ABLoadWifi((_ba.processBA == null ? _ba : _ba.processBA))==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 91;BA.debugLine="Try";
try { //BA.debugLineNum = 92;BA.debugLine="strMAC = myWifi.ABGetCurrentWifiInfo().MacAddr";
_strmac = _mywifi.ABGetCurrentWifiInfo().MacAddress;
 //BA.debugLineNum = 93;BA.debugLine="strMAC = strMAC.Replace(\":\", \"-\")";
_strmac = _strmac.replace(":","-");
 } 
       catch (Exception e69) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e69); //BA.debugLineNum = 95;BA.debugLine="strMAC = \"00-00-00-00-00-00\"";
_strmac = "00-00-00-00-00-00";
 };
 }else {
 //BA.debugLineNum = 98;BA.debugLine="strMAC = \"00-00-00-00-00-00\"";
_strmac = "00-00-00-00-00-00";
 };
 //BA.debugLineNum = 101;BA.debugLine="DataConfig.aid  				= szAndroidID";
_v0.aid = _vv2;
 //BA.debugLineNum = 102;BA.debugLine="DataConfig.rsn  				= szRefSerial";
_v0.rsn = _vv3;
 //BA.debugLineNum = 103;BA.debugLine="DataConfig.imei 				= szIMEI";
_v0.imei = _vv4;
 //BA.debugLineNum = 104;BA.debugLine="DataConfig.Username	 		= \"Guest\"";
_v0.Username = "Guest";
 //BA.debugLineNum = 105;BA.debugLine="DataConfig.Password	 		= \"None\"";
_v0.Password = "None";
 //BA.debugLineNum = 106;BA.debugLine="DataConfig.MacAddr 			= strMAC";
_v0.MacAddr = _strmac;
 //BA.debugLineNum = 107;BA.debugLine="DataConfig.sTab					= \"LiveTV\"";
_v0.sTab = "LiveTV";
 //BA.debugLineNum = 108;BA.debugLine="DataConfig.FolderDBS		= DirDBS";
_v0.FolderDBS = _v6;
 //BA.debugLineNum = 109;BA.debugLine="DataConfig.FolderMedia	= DirDBS";
_v0.FolderMedia = _v6;
 //BA.debugLineNum = 110;BA.debugLine="Return TulisFileConfig(DataConfig)";
if (true) return _vvvvvvvvv4(_ba,_v0);
 }else {
 //BA.debugLineNum = 113;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 115;BA.debugLine="End Sub";
return false;
}
public static boolean  _vvvvvvvvv4(anywheresoftware.b4a.BA _ba,ottplaymedia.mncplaymedia.com.fungsi._formatconfig _isiconfig) throws Exception{
anywheresoftware.b4a.objects.collections.Map _mptemp = null;
 //BA.debugLineNum = 117;BA.debugLine="Sub TulisFileConfig(IsiConfig As FormatConfig) As";
 //BA.debugLineNum = 118;BA.debugLine="Dim mpTemp		As Map";
_mptemp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 120;BA.debugLine="mpTemp.Initialize";
_mptemp.Initialize();
 //BA.debugLineNum = 121;BA.debugLine="mpTemp.Clear";
_mptemp.Clear();
 //BA.debugLineNum = 122;BA.debugLine="mpTemp.Put(\"DataConfig\", IsiConfig)";
_mptemp.Put((Object)("DataConfig"),(Object)(_isiconfig));
 //BA.debugLineNum = 124;BA.debugLine="Try";
try { //BA.debugLineNum = 125;BA.debugLine="File.Delete(DirDBS, \"setting\")";
anywheresoftware.b4a.keywords.Common.File.Delete(_v6,"setting");
 //BA.debugLineNum = 126;BA.debugLine="raf.Initialize(DirDBS, \"setting\", False)";
_vv1.Initialize(_v6,"setting",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 127;BA.debugLine="raf.WriteEncryptedObject(mpTemp, szAndroidID & s";
_vv1.WriteEncryptedObject((Object)(_mptemp.getObject()),_vv2+_vv3+_vv4,_vv1.CurrentPosition);
 //BA.debugLineNum = 128;BA.debugLine="raf.Close";
_vv1.Close();
 } 
       catch (Exception e99) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e99); //BA.debugLineNum = 130;BA.debugLine="Msgbox2(\"Init data failed\", \"ezSchool\", \"OK\", \"\"";
anywheresoftware.b4a.keywords.Common.Msgbox2("Init data failed","ezSchool","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Warning.png").getObject()),_ba);
 //BA.debugLineNum = 131;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 134;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 135;BA.debugLine="End Sub";
return false;
}
public static String  _vvvvvvvvv5(anywheresoftware.b4a.BA _ba,String _text) throws Exception{
String _truekey = "";
String _unwrapped = "";
int _len = 0;
 //BA.debugLineNum = 515;BA.debugLine="Sub UnWrap(Text As String) As String";
 //BA.debugLineNum = 516;BA.debugLine="Dim truekey, unwrapped As String";
_truekey = "";
_unwrapped = "";
 //BA.debugLineNum = 517;BA.debugLine="Dim len As Int";
_len = 0;
 //BA.debugLineNum = 519;BA.debugLine="len = Bit.ParseInt(Text.CharAt(3),16)";
_len = anywheresoftware.b4a.keywords.Common.Bit.ParseInt(BA.ObjectToString(_text.charAt((int) (3))),(int) (16));
 //BA.debugLineNum = 520;BA.debugLine="truekey = Text.SubString2(4,4+len)";
_truekey = _text.substring((int) (4),(int) (4+_len));
 //BA.debugLineNum = 521;BA.debugLine="unwrapped = EnkripDekrip(\"AES\", truekey, \"MD5\", T";
_unwrapped = _vvvvvvv7(_ba,"AES",_truekey,"MD5",_text.substring((int) (4+_len)),"Decode");
 //BA.debugLineNum = 523;BA.debugLine="Return unwrapped";
if (true) return _unwrapped;
 //BA.debugLineNum = 524;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvv6(anywheresoftware.b4a.BA _ba,String _text,String _vkey) throws Exception{
String _truekey = "";
String _encrypted = "";
String _wrapped = "";
int _len = 0;
 //BA.debugLineNum = 359;BA.debugLine="Sub Wrap(Text As String, vkey As String) As String";
 //BA.debugLineNum = 360;BA.debugLine="Dim trueKey, encrypted, wrapped As String";
_truekey = "";
_encrypted = "";
_wrapped = "";
 //BA.debugLineNum = 361;BA.debugLine="Dim len As Int";
_len = 0;
 //BA.debugLineNum = 363;BA.debugLine="len = Bit.ParseInt(vkey.CharAt(3),16)";
_len = anywheresoftware.b4a.keywords.Common.Bit.ParseInt(BA.ObjectToString(_vkey.charAt((int) (3))),(int) (16));
 //BA.debugLineNum = 364;BA.debugLine="trueKey = vkey.SubString2(4,4+len)";
_truekey = _vkey.substring((int) (4),(int) (4+_len));
 //BA.debugLineNum = 365;BA.debugLine="encrypted = EnkripDekrip(\"AES\", trueKey, \"MD5\", T";
_encrypted = _vvvvvvv7(_ba,"AES",_truekey,"MD5",_text,"Encode");
 //BA.debugLineNum = 366;BA.debugLine="wrapped = vkey & encrypted";
_wrapped = _vkey+_encrypted;
 //BA.debugLineNum = 368;BA.debugLine="Return wrapped";
if (true) return _wrapped;
 //BA.debugLineNum = 369;BA.debugLine="End Sub";
return "";
}
}
