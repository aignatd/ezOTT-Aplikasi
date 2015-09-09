package ottplaymedia.mncplaymedia.com;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class mnchttpjob extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "ottplaymedia.mncplaymedia.com.mnchttpjob");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            if (BA.isShellModeRuntimeCheck(ba)) {
			    ba.raiseEvent2(null, true, "CREATE", true, "ottplaymedia.mncplaymedia.com.mnchttpjob",
                    ba);
                return;
		    }
        }
        ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public String _vvvvvvvvvvvvvvvvvvvvvv1 = "";
public boolean _vvvvvvvvvvvvvvvvvvvvvv2 = false;
public String _vvvvvvvvvvvvvvvvvvvvvv3 = "";
public String _vvvvvvvvvvvvvvvvvvvvvv4 = "";
public String _vvvvvvvvvvvvvvvvvvvvvv5 = "";
public Object _vvvvvvvvvvvvvvvvvvvvvv6 = null;
public String _vvvvvvvvvvvvvvvvvvvvvv7 = "";
public anywheresoftware.b4a.http.HttpClientWrapper.HttpUriRequestWrapper _vvvvvvvvvvvvvvvvvvvvvv0 = null;
public Object _vvvvvvvvvvvvvvvvvvvvvvv1 = null;
public anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvvvvvvvvvvvvvv2 = null;
public anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvvvvvvvvvvvvvv3 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvvvvvvvvvvvvv5 = null;
public b4a.example.dateutils _vvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.main _vvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.fungsi _vvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.utama _vvvvvvvvvvvvvvvv1 = null;
public ottplaymedia.mncplaymedia.com.epglist _vvvvvvvvvvvvvvvv2 = null;
public ottplaymedia.mncplaymedia.com.register _vvvvvvvvvvvvvvvv3 = null;
public ottplaymedia.mncplaymedia.com.pilihan _vvvvvvvvvvvvvvvv4 = null;
public ottplaymedia.mncplaymedia.com.epgdownloader _vvvvvvvvvvvvvvvv5 = null;
public ottplaymedia.mncplaymedia.com.imagedownloader _vvvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.content _vvvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.webview _vvvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.mncutils2service _vvvvvvvvvvvvvvvvv1 = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Public JobName 			As String";
_vvvvvvvvvvvvvvvvvvvvvv1 = "";
 //BA.debugLineNum = 5;BA.debugLine="Public Success 			As Boolean";
_vvvvvvvvvvvvvvvvvvvvvv2 = false;
 //BA.debugLineNum = 6;BA.debugLine="Public Username 		As String";
_vvvvvvvvvvvvvvvvvvvvvv3 = "";
 //BA.debugLineNum = 7;BA.debugLine="Public Password 		As String";
_vvvvvvvvvvvvvvvvvvvvvv4 = "";
 //BA.debugLineNum = 8;BA.debugLine="Public ErrorMessage As String";
_vvvvvvvvvvvvvvvvvvvvvv5 = "";
 //BA.debugLineNum = 9;BA.debugLine="Private target 			As Object";
_vvvvvvvvvvvvvvvvvvvvvv6 = new Object();
 //BA.debugLineNum = 10;BA.debugLine="Private taskId 			As String";
_vvvvvvvvvvvvvvvvvvvvvv7 = "";
 //BA.debugLineNum = 11;BA.debugLine="Private req 				As HttpRequest";
_vvvvvvvvvvvvvvvvvvvvvv0 = new anywheresoftware.b4a.http.HttpClientWrapper.HttpUriRequestWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Public Tag 					As Object";
_vvvvvvvvvvvvvvvvvvvvvvv1 = new Object();
 //BA.debugLineNum = 13;BA.debugLine="Public ResMap				As Map";
_vvvvvvvvvvvvvvvvvvvvvvv2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 14;BA.debugLine="Public ProMap				As Map";
_vvvvvvvvvvvvvvvvvvvvvvv3 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvv6(int _id) throws Exception{
 //BA.debugLineNum = 90;BA.debugLine="Public Sub Complete (id As Int)";
 //BA.debugLineNum = 91;BA.debugLine="taskId = id";
_vvvvvvvvvvvvvvvvvvvvvv7 = BA.NumberToString(_id);
 //BA.debugLineNum = 92;BA.debugLine="CallSubDelayed2(target, \"JobDone\", Me)";
__c.CallSubDelayed2(getActivityBA(),_vvvvvvvvvvvvvvvvvvvvvv6,"JobDone",this);
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}
public String  _download(String _link) throws Exception{
 //BA.debugLineNum = 60;BA.debugLine="Public Sub Download(Link As String)";
 //BA.debugLineNum = 61;BA.debugLine="req.InitializeGet(Link)";
_vvvvvvvvvvvvvvvvvvvvvv0.InitializeGet(_link);
 //BA.debugLineNum = 62;BA.debugLine="CallSubDelayed2(MNCUtils2Service, \"SubmitJob\", Me";
__c.CallSubDelayed2(getActivityBA(),(Object)(_vvvvvvvvvvvvvvvvv1.getObject()),"SubmitJob",this);
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvvvvvvvvvv7(String _link,String[] _parameters) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
int _i = 0;
 //BA.debugLineNum = 69;BA.debugLine="Public Sub Download2(Link As String, Parameters()";
 //BA.debugLineNum = 70;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 71;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 72;BA.debugLine="sb.Append(Link)";
_sb.Append(_link);
 //BA.debugLineNum = 73;BA.debugLine="If Parameters.Length > 0 Then sb.Append(\"?\")";
if (_parameters.length>0) { 
_sb.Append("?");};
 //BA.debugLineNum = 74;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 75;BA.debugLine="For i = 0 To Parameters.Length - 1 Step 2";
{
final int step53 = (int) (2);
final int limit53 = (int) (_parameters.length-1);
for (_i = (int) (0); (step53 > 0 && _i <= limit53) || (step53 < 0 && _i >= limit53); _i = ((int)(0 + _i + step53))) {
 //BA.debugLineNum = 76;BA.debugLine="If i > 0 Then sb.Append(\"&\")";
if (_i>0) { 
_sb.Append("&");};
 //BA.debugLineNum = 77;BA.debugLine="sb.Append(su.EncodeUrl(Parameters(i), \"UTF8\")).A";
_sb.Append(_su.EncodeUrl(_parameters[_i],"UTF8")).Append("=");
 //BA.debugLineNum = 78;BA.debugLine="sb.Append(su.EncodeUrl(Parameters(i + 1), \"UTF8\"";
_sb.Append(_su.EncodeUrl(_parameters[(int) (_i+1)],"UTF8"));
 }
};
 //BA.debugLineNum = 80;BA.debugLine="req.InitializeGet(sb.ToString)";
_vvvvvvvvvvvvvvvvvvvvvv0.InitializeGet(_sb.ToString());
 //BA.debugLineNum = 81;BA.debugLine="CallSubDelayed2(MNCUtils2Service, \"SubmitJob\", Me";
__c.CallSubDelayed2(getActivityBA(),(Object)(_vvvvvvvvvvvvvvvvv1.getObject()),"SubmitJob",this);
 //BA.debugLineNum = 82;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _vvvvvvvvvvvvv2() throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _b = null;
 //BA.debugLineNum = 138;BA.debugLine="Public Sub GetBitmap As Bitmap";
 //BA.debugLineNum = 139;BA.debugLine="Dim b As Bitmap";
_b = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 141;BA.debugLine="b.Initialize(MNCUtils2Service.TempFolder, taskId)";
_b.Initialize(_vvvvvvvvvvvvvvvvv1._vvvvvvvvvvvv4,_vvvvvvvvvvvvvvvvvvvvvv7);
 //BA.debugLineNum = 142;BA.debugLine="Return b";
if (true) return _b;
 //BA.debugLineNum = 143;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _vvvvvvvvvvvvvvvvvvvv0(int _width,int _height) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _b = null;
 //BA.debugLineNum = 151;BA.debugLine="Public Sub GetBitmapSampled(Width As Int, Height A";
 //BA.debugLineNum = 152;BA.debugLine="Dim b As Bitmap";
_b = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 153;BA.debugLine="b = LoadBitmapSample(MNCUtils2Service.TempFolder,";
_b = __c.LoadBitmapSample(_vvvvvvvvvvvvvvvvv1._vvvvvvvvvvvv4,_vvvvvvvvvvvvvvvvvvvvvv7,_width,_height);
 //BA.debugLineNum = 154;BA.debugLine="Return b";
if (true) return _b;
 //BA.debugLineNum = 155;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.streams.File.InputStreamWrapper  _vvvvvvvvvvvvv5() throws Exception{
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
 //BA.debugLineNum = 145;BA.debugLine="Public Sub GetInputStream As InputStream";
 //BA.debugLineNum = 146;BA.debugLine="Dim In As InputStream";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
 //BA.debugLineNum = 147;BA.debugLine="In = File.OpenInput(MNCUtils2Service.TempFolder,";
_in = __c.File.OpenInput(_vvvvvvvvvvvvvvvvv1._vvvvvvvvvvvv4,_vvvvvvvvvvvvvvvvvvvvvv7);
 //BA.debugLineNum = 148;BA.debugLine="Return In";
if (true) return _in;
 //BA.debugLineNum = 149;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.http.HttpClientWrapper.HttpUriRequestWrapper  _vvvvvvvvvvvvvvvvvvvvv1() throws Exception{
 //BA.debugLineNum = 85;BA.debugLine="Public Sub GetRequest As HttpRequest";
 //BA.debugLineNum = 86;BA.debugLine="Return req";
if (true) return _vvvvvvvvvvvvvvvvvvvvvv0;
 //BA.debugLineNum = 87;BA.debugLine="End Sub";
return null;
}
public String  _vvvvvvvvvvvvvvvvvvvvv2() throws Exception{
 //BA.debugLineNum = 101;BA.debugLine="Public Sub GetString As String";
 //BA.debugLineNum = 102;BA.debugLine="Return GetString2(\"UTF8\")";
if (true) return _vvvvvvvvvvvvvvvvvvvvv3("UTF8");
 //BA.debugLineNum = 103;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvvvvvvvvvvv3(String _encoding) throws Exception{
anywheresoftware.b4a.objects.streams.File.TextReaderWrapper _tr = null;
String _res = "";
 //BA.debugLineNum = 106;BA.debugLine="Public Sub GetString2(Encoding As String) As Strin";
 //BA.debugLineNum = 107;BA.debugLine="Dim tr As TextReader";
_tr = new anywheresoftware.b4a.objects.streams.File.TextReaderWrapper();
 //BA.debugLineNum = 109;BA.debugLine="Try";
try { //BA.debugLineNum = 110;BA.debugLine="tr.Initialize2(File.OpenInput(MNCUtils2Service.T";
_tr.Initialize2((java.io.InputStream)(__c.File.OpenInput(_vvvvvvvvvvvvvvvvv1._vvvvvvvvvvvv4,_vvvvvvvvvvvvvvvvvvvvvv7).getObject()),_encoding);
 } 
       catch (Exception e79) {
			ba.setLastException(e79); //BA.debugLineNum = 112;BA.debugLine="Return Null";
if (true) return BA.ObjectToString(__c.Null);
 };
 //BA.debugLineNum = 115;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 116;BA.debugLine="res = tr.ReadAll";
_res = _tr.ReadAll();
 //BA.debugLineNum = 117;BA.debugLine="tr.Close";
_tr.Close();
 //BA.debugLineNum = 118;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 119;BA.debugLine="End Sub";
return "";
}
public boolean  _vvvvvvvvvvvvvvvvvvvvv4(String _namadir,String _namafile) throws Exception{
anywheresoftware.b4a.objects.streams.File.TextReaderWrapper _tr = null;
String _res = "";
 //BA.debugLineNum = 121;BA.debugLine="Public Sub GetString3(NamaDir As String, Namafile";
 //BA.debugLineNum = 122;BA.debugLine="Dim tr As TextReader";
_tr = new anywheresoftware.b4a.objects.streams.File.TextReaderWrapper();
 //BA.debugLineNum = 124;BA.debugLine="Try";
try { //BA.debugLineNum = 125;BA.debugLine="tr.Initialize(File.OpenInput(MNCUtils2Service.Te";
_tr.Initialize((java.io.InputStream)(__c.File.OpenInput(_vvvvvvvvvvvvvvvvv1._vvvvvvvvvvvv4,_vvvvvvvvvvvvvvvvvvvvvv7).getObject()));
 } 
       catch (Exception e91) {
			ba.setLastException(e91); //BA.debugLineNum = 127;BA.debugLine="Return False";
if (true) return __c.False;
 };
 //BA.debugLineNum = 130;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 131;BA.debugLine="res = tr.ReadAll";
_res = _tr.ReadAll();
 //BA.debugLineNum = 132;BA.debugLine="tr.Close";
_tr.Close();
 //BA.debugLineNum = 133;BA.debugLine="File.WriteString(NamaDir, Namafile, res)";
__c.File.WriteString(_namadir,_namafile,_res);
 //BA.debugLineNum = 134;BA.debugLine="Return True";
if (true) return __c.True;
 //BA.debugLineNum = 135;BA.debugLine="End Sub";
return false;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,String _name,Object _targetmodule) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 20;BA.debugLine="Public Sub Initialize (Name As String, TargetModul";
 //BA.debugLineNum = 21;BA.debugLine="JobName = Name";
_vvvvvvvvvvvvvvvvvvvvvv1 = _name;
 //BA.debugLineNum = 22;BA.debugLine="target = TargetModule";
_vvvvvvvvvvvvvvvvvvvvvv6 = _targetmodule;
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvvvvvvvvvvv5(String _link,byte[] _data) throws Exception{
 //BA.debugLineNum = 30;BA.debugLine="Public Sub PostBytes(Link As String, Data() As Byt";
 //BA.debugLineNum = 31;BA.debugLine="req.InitializePost2(Link, Data)";
_vvvvvvvvvvvvvvvvvvvvvv0.InitializePost2(_link,_data);
 //BA.debugLineNum = 32;BA.debugLine="CallSubDelayed2(MNCUtils2Service, \"SubmitJob\", Me";
__c.CallSubDelayed2(getActivityBA(),(Object)(_vvvvvvvvvvvvvvvvv1.getObject()),"SubmitJob",this);
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvvvvvvvvvvv6(String _link,String _dir,String _filename) throws Exception{
int _length = 0;
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
 //BA.debugLineNum = 37;BA.debugLine="Public Sub PostFile(Link As String, Dir As String,";
 //BA.debugLineNum = 38;BA.debugLine="Dim length As Int";
_length = 0;
 //BA.debugLineNum = 39;BA.debugLine="If Dir = File.DirAssets Then";
if ((_dir).equals(__c.File.getDirAssets())) { 
 //BA.debugLineNum = 40;BA.debugLine="Log(\"Cannot send files from the assets folder.\")";
__c.Log("Cannot send files from the assets folder.");
 //BA.debugLineNum = 41;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 43;BA.debugLine="length = File.Size(Dir, FileName)";
_length = (int) (__c.File.Size(_dir,_filename));
 //BA.debugLineNum = 44;BA.debugLine="Dim In As InputStream";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
 //BA.debugLineNum = 45;BA.debugLine="In = File.OpenInput(Dir, FileName)";
_in = __c.File.OpenInput(_dir,_filename);
 //BA.debugLineNum = 46;BA.debugLine="If length < 1000000 Then '1mb";
if (_length<1000000) { 
 //BA.debugLineNum = 49;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 50;BA.debugLine="out.InitializeToBytesArray(length)";
_out.InitializeToBytesArray(_length);
 //BA.debugLineNum = 51;BA.debugLine="File.Copy2(In, out)";
__c.File.Copy2((java.io.InputStream)(_in.getObject()),(java.io.OutputStream)(_out.getObject()));
 //BA.debugLineNum = 52;BA.debugLine="PostBytes(Link, out.ToBytesArray)";
_vvvvvvvvvvvvvvvvvvvvv5(_link,_out.ToBytesArray());
 }else {
 //BA.debugLineNum = 54;BA.debugLine="req.InitializePost(Link, In, length)";
_vvvvvvvvvvvvvvvvvvvvvv0.InitializePost(_link,(java.io.InputStream)(_in.getObject()),_length);
 //BA.debugLineNum = 55;BA.debugLine="CallSubDelayed2(MNCUtils2Service, \"SubmitJob\", M";
__c.CallSubDelayed2(getActivityBA(),(Object)(_vvvvvvvvvvvvvvvvv1.getObject()),"SubmitJob",this);
 };
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvvvvvvvvvvv7(String _link,String _text) throws Exception{
 //BA.debugLineNum = 25;BA.debugLine="Public Sub PostString(Link As String, Text As Stri";
 //BA.debugLineNum = 26;BA.debugLine="PostBytes(Link, Text.GetBytes(\"UTF8\"))";
_vvvvvvvvvvvvvvvvvvvvv5(_link,_text.getBytes("UTF8"));
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvvvvvvvvvvv0() throws Exception{
 //BA.debugLineNum = 96;BA.debugLine="Public Sub Release";
 //BA.debugLineNum = 97;BA.debugLine="File.Delete(MNCUtils2Service.TempFolder, taskId)";
__c.File.Delete(_vvvvvvvvvvvvvvvvv1._vvvvvvvvvvvv4,_vvvvvvvvvvvvvvvvvvvvvv7);
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
if (BA.fastSubCompare(sub, "DOWNLOAD"))
	return _download((String) args[0]);
return BA.SubDelegator.SubNotFound;
}
}
