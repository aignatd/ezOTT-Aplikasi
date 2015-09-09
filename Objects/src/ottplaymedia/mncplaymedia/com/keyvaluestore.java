package ottplaymedia.mncplaymedia.com;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class keyvaluestore extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "ottplaymedia.mncplaymedia.com.keyvaluestore");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            if (BA.isShellModeRuntimeCheck(ba)) {
			    ba.raiseEvent2(null, true, "CREATE", true, "ottplaymedia.mncplaymedia.com.keyvaluestore",
                    ba);
                return;
		    }
        }
        ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.sql.SQL _vvvvvvvvvvvvvvv3 = null;
public String _vvvvvvvvvvvv4 = "";
public String _vvvvvvvvvvvvvvv4 = "";
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
 //BA.debugLineNum = 2;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private sql1 As SQL";
_vvvvvvvvvvvvvvv3 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 4;BA.debugLine="Private tempFolder = File.DirInternalCache, tempF";
_vvvvvvvvvvvv4 = __c.File.getDirInternalCache();
_vvvvvvvvvvvvvvv4 = "key_value_temp.dat";
 //BA.debugLineNum = 5;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvv5() throws Exception{
 //BA.debugLineNum = 157;BA.debugLine="Public Sub Close";
 //BA.debugLineNum = 158;BA.debugLine="sql1.Close";
_vvvvvvvvvvvvvvv3.Close();
 //BA.debugLineNum = 159;BA.debugLine="End Sub";
return "";
}
public boolean  _vvvvvvvvvvvv6(boolean _success) throws Exception{
 //BA.debugLineNum = 200;BA.debugLine="Private Sub complete(success As Boolean) As Boolea";
 //BA.debugLineNum = 201;BA.debugLine="If success Then";
if (_success) { 
 //BA.debugLineNum = 202;BA.debugLine="sql1.TransactionSuccessful";
_vvvvvvvvvvvvvvv3.TransactionSuccessful();
 }else {
 //BA.debugLineNum = 204;BA.debugLine="Log(\"Error saving object: \" & LastException)";
__c.Log("Error saving object: "+BA.ObjectToString(__c.LastException(getActivityBA())));
 };
 //BA.debugLineNum = 206;BA.debugLine="sql1.EndTransaction";
_vvvvvvvvvvvvvvv3.EndTransaction();
 //BA.debugLineNum = 207;BA.debugLine="Return success";
if (true) return _success;
 //BA.debugLineNum = 208;BA.debugLine="End Sub";
return false;
}
public boolean  _vvvvvvvvvvvv7(String _key) throws Exception{
 //BA.debugLineNum = 89;BA.debugLine="Public Sub ContainsKey(Key As String) As Boolean";
 //BA.debugLineNum = 90;BA.debugLine="Return sql1.ExecQuerySingleResult2(\"SELECT count(";
if (true) return (double)(Double.parseDouble(_vvvvvvvvvvvvvvv3.ExecQuerySingleResult2("SELECT count(key) FROM main WHERE key = ?",new String[]{_key})))>0;
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return false;
}
public String  _vvvvvvvvvvvv0() throws Exception{
 //BA.debugLineNum = 191;BA.debugLine="Private Sub CreateTable";
 //BA.debugLineNum = 192;BA.debugLine="sql1.ExecNonQuery(\"CREATE TABLE IF NOT EXISTS mai";
_vvvvvvvvvvvvvvv3.ExecNonQuery("CREATE TABLE IF NOT EXISTS main(key TEXT PRIMARY KEY, value NONE)");
 //BA.debugLineNum = 193;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvvv1() throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Public Sub DeleteAll";
 //BA.debugLineNum = 96;BA.debugLine="sql1.ExecNonQuery(\"DROP TABLE main\")";
_vvvvvvvvvvvvvvv3.ExecNonQuery("DROP TABLE main");
 //BA.debugLineNum = 97;BA.debugLine="CreateTable";
_vvvvvvvvvvvv0();
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _vvvvvvvvvvvvv2(String _key) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _c = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _b = null;
byte[] _buffer = null;
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
 //BA.debugLineNum = 129;BA.debugLine="Public Sub GetBitmap(Key As String) As Bitmap";
 //BA.debugLineNum = 130;BA.debugLine="Dim c As Cursor = getCursor(Key)";
_c = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_c = _vvvvvvvvvvvvv3(_key);
 //BA.debugLineNum = 131;BA.debugLine="Dim b As Bitmap";
_b = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 132;BA.debugLine="If c.RowCount = 0 Then";
if (_c.getRowCount()==0) { 
 //BA.debugLineNum = 133;BA.debugLine="c.Close";
_c.Close();
 //BA.debugLineNum = 134;BA.debugLine="Return b";
if (true) return _b;
 };
 //BA.debugLineNum = 136;BA.debugLine="c.Position = 0";
_c.setPosition((int) (0));
 //BA.debugLineNum = 137;BA.debugLine="Dim buffer() As Byte = c.GetBlob2(0)";
_buffer = _c.GetBlob2((int) (0));
 //BA.debugLineNum = 138;BA.debugLine="Dim In As InputStream";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
 //BA.debugLineNum = 139;BA.debugLine="In.InitializeFromBytesArray(buffer, 0, buffer.Len";
_in.InitializeFromBytesArray(_buffer,(int) (0),_buffer.length);
 //BA.debugLineNum = 140;BA.debugLine="b.Initialize2(In)";
_b.Initialize2((java.io.InputStream)(_in.getObject()));
 //BA.debugLineNum = 141;BA.debugLine="In.Close";
_in.Close();
 //BA.debugLineNum = 142;BA.debugLine="c.Close";
_c.Close();
 //BA.debugLineNum = 143;BA.debugLine="Return b";
if (true) return _b;
 //BA.debugLineNum = 144;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.sql.SQL.CursorWrapper  _vvvvvvvvvvvvv3(String _key) throws Exception{
 //BA.debugLineNum = 182;BA.debugLine="Private Sub getCursor(Key As String) As Cursor";
 //BA.debugLineNum = 183;BA.debugLine="Return sql1.ExecQuery2(\"SELECT value FROM main WH";
if (true) return (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_vvvvvvvvvvvvvvv3.ExecQuery2("SELECT value FROM main WHERE key = ?",new String[]{_key})));
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return null;
}
public Object  _vvvvvvvvvvvvv4(String _key,String _password) throws Exception{
 //BA.debugLineNum = 152;BA.debugLine="Public Sub GetEncryptedObject(Key As String, Passw";
 //BA.debugLineNum = 153;BA.debugLine="Return getObjectInternal(Key, True, Password)";
if (true) return _vvvvvvvvvvvvv7(_key,__c.True,_password);
 //BA.debugLineNum = 154;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.streams.File.InputStreamWrapper  _vvvvvvvvvvvvv5(String _key) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _c = null;
byte[] _buffer = null;
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
 //BA.debugLineNum = 114;BA.debugLine="Public Sub GetInputStream(Key As String) As InputS";
 //BA.debugLineNum = 115;BA.debugLine="Dim c As Cursor = getCursor(Key)";
_c = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_c = _vvvvvvvvvvvvv3(_key);
 //BA.debugLineNum = 116;BA.debugLine="If c.RowCount = 0 Then";
if (_c.getRowCount()==0) { 
 //BA.debugLineNum = 117;BA.debugLine="c.Close";
_c.Close();
 //BA.debugLineNum = 118;BA.debugLine="Return Null";
if (true) return (anywheresoftware.b4a.objects.streams.File.InputStreamWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper(), (java.io.InputStream)(__c.Null));
 };
 //BA.debugLineNum = 120;BA.debugLine="c.Position = 0";
_c.setPosition((int) (0));
 //BA.debugLineNum = 121;BA.debugLine="Dim buffer() As Byte = c.GetBlob2(0)";
_buffer = _c.GetBlob2((int) (0));
 //BA.debugLineNum = 122;BA.debugLine="Dim In As InputStream";
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();
 //BA.debugLineNum = 123;BA.debugLine="In.InitializeFromBytesArray(buffer, 0, buffer.Len";
_in.InitializeFromBytesArray(_buffer,(int) (0),_buffer.length);
 //BA.debugLineNum = 124;BA.debugLine="c.Close";
_c.Close();
 //BA.debugLineNum = 125;BA.debugLine="Return In";
if (true) return _in;
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return null;
}
public Object  _vvvvvvvvvvvvv6(String _key) throws Exception{
 //BA.debugLineNum = 147;BA.debugLine="Public Sub GetObject(Key As String) As Object";
 //BA.debugLineNum = 148;BA.debugLine="Return getObjectInternal(Key, False, \"\")";
if (true) return _vvvvvvvvvvvvv7(_key,__c.False,"");
 //BA.debugLineNum = 149;BA.debugLine="End Sub";
return null;
}
public Object  _vvvvvvvvvvvvv7(String _key,boolean _decrypt,String _password) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _c = null;
byte[] _buffer = null;
anywheresoftware.b4a.randomaccessfile.RandomAccessFile _raf = null;
Object _res = null;
 //BA.debugLineNum = 161;BA.debugLine="Private Sub getObjectInternal(Key As String, decry";
 //BA.debugLineNum = 162;BA.debugLine="Dim c As Cursor = getCursor(Key)";
_c = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_c = _vvvvvvvvvvvvv3(_key);
 //BA.debugLineNum = 163;BA.debugLine="If c.RowCount = 0 Then";
if (_c.getRowCount()==0) { 
 //BA.debugLineNum = 164;BA.debugLine="c.Close";
_c.Close();
 //BA.debugLineNum = 165;BA.debugLine="Return Null";
if (true) return __c.Null;
 };
 //BA.debugLineNum = 167;BA.debugLine="c.Position = 0";
_c.setPosition((int) (0));
 //BA.debugLineNum = 168;BA.debugLine="Dim buffer() As Byte = c.GetBlob2(0)";
_buffer = _c.GetBlob2((int) (0));
 //BA.debugLineNum = 169;BA.debugLine="Dim raf As RandomAccessFile";
_raf = new anywheresoftware.b4a.randomaccessfile.RandomAccessFile();
 //BA.debugLineNum = 170;BA.debugLine="raf.Initialize3(buffer, False)";
_raf.Initialize3(_buffer,__c.False);
 //BA.debugLineNum = 171;BA.debugLine="Dim res As Object";
_res = new Object();
 //BA.debugLineNum = 172;BA.debugLine="If decrypt Then";
if (_decrypt) { 
 //BA.debugLineNum = 173;BA.debugLine="res = raf.ReadEncryptedObject(password, raf.Curr";
_res = _raf.ReadEncryptedObject(_password,_raf.CurrentPosition);
 }else {
 //BA.debugLineNum = 175;BA.debugLine="res = raf.ReadObject(raf.CurrentPosition)";
_res = _raf.ReadObject(_raf.CurrentPosition);
 };
 //BA.debugLineNum = 177;BA.debugLine="raf.Close";
_raf.Close();
 //BA.debugLineNum = 178;BA.debugLine="c.Close";
_c.Close();
 //BA.debugLineNum = 179;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 180;BA.debugLine="End Sub";
return null;
}
public String  _vvvvvvvvvvvvv0(String _key) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _c = null;
String _res = "";
 //BA.debugLineNum = 101;BA.debugLine="Public Sub GetSimple(Key As String) As String";
 //BA.debugLineNum = 102;BA.debugLine="Dim c As Cursor = getCursor(Key)";
_c = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_c = _vvvvvvvvvvvvv3(_key);
 //BA.debugLineNum = 103;BA.debugLine="If c.RowCount = 0 Then";
if (_c.getRowCount()==0) { 
 //BA.debugLineNum = 104;BA.debugLine="c.Close";
_c.Close();
 //BA.debugLineNum = 105;BA.debugLine="Return \"\"";
if (true) return "";
 };
 //BA.debugLineNum = 107;BA.debugLine="c.Position = 0";
_c.setPosition((int) (0));
 //BA.debugLineNum = 108;BA.debugLine="Dim res As String = c.GetString2(0)";
_res = _c.GetString2((int) (0));
 //BA.debugLineNum = 109;BA.debugLine="c.Close";
_c.Close();
 //BA.debugLineNum = 110;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,String _dir,String _filename) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 8;BA.debugLine="Public Sub Initialize (Dir As String, FileName As";
 //BA.debugLineNum = 9;BA.debugLine="If sql1.IsInitialized Then sql1.Close";
if (_vvvvvvvvvvvvvvv3.IsInitialized()) { 
_vvvvvvvvvvvvvvv3.Close();};
 //BA.debugLineNum = 10;BA.debugLine="sql1.Initialize(Dir, FileName, True)";
_vvvvvvvvvvvvvvv3.Initialize(_dir,_filename,__c.True);
 //BA.debugLineNum = 11;BA.debugLine="CreateTable";
_vvvvvvvvvvvv0();
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvvvv1(String _key,Object _value) throws Exception{
 //BA.debugLineNum = 186;BA.debugLine="Private Sub insertQuery(Key As String, Value As Ob";
 //BA.debugLineNum = 187;BA.debugLine="sql1.ExecNonQuery2(\"INSERT INTO main VALUES(?, ?)";
_vvvvvvvvvvvvvvv3.ExecNonQuery2("INSERT INTO main VALUES(?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_key),_value}));
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.collections.List  _vvvvvvvvvvvvvv2() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _c = null;
anywheresoftware.b4a.objects.collections.List _res = null;
int _row = 0;
 //BA.debugLineNum = 77;BA.debugLine="Public Sub ListKeys As List";
 //BA.debugLineNum = 78;BA.debugLine="Dim c As Cursor = sql1.ExecQuery(\"SELECT key FROM";
_c = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_c.setObject((android.database.Cursor)(_vvvvvvvvvvvvvvv3.ExecQuery("SELECT key FROM main")));
 //BA.debugLineNum = 79;BA.debugLine="Dim res As List";
_res = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 80;BA.debugLine="res.Initialize";
_res.Initialize();
 //BA.debugLineNum = 81;BA.debugLine="For row = 0 To c.RowCount - 1";
{
final int step59 = 1;
final int limit59 = (int) (_c.getRowCount()-1);
for (_row = (int) (0); (step59 > 0 && _row <= limit59) || (step59 < 0 && _row >= limit59); _row = ((int)(0 + _row + step59))) {
 //BA.debugLineNum = 82;BA.debugLine="c.Position = row";
_c.setPosition(_row);
 //BA.debugLineNum = 83;BA.debugLine="res.Add(c.GetString2(0))";
_res.Add((Object)(_c.GetString2((int) (0))));
 }
};
 //BA.debugLineNum = 85;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return null;
}
public boolean  _vvvvvvvvvvvvvv3(String _key,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _value) throws Exception{
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _b = null;
 //BA.debugLineNum = 40;BA.debugLine="Public Sub PutBitmap(Key As String, Value As Bitma";
 //BA.debugLineNum = 41;BA.debugLine="Try";
try { //BA.debugLineNum = 42;BA.debugLine="start(Key)";
_vvvvvvvvvvvvvvv2(_key);
 //BA.debugLineNum = 43;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 44;BA.debugLine="out.InitializeToBytesArray(100)";
_out.InitializeToBytesArray((int) (100));
 //BA.debugLineNum = 45;BA.debugLine="Dim b As Bitmap = Value";
_b = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_b = _value;
 //BA.debugLineNum = 46;BA.debugLine="b.WriteToStream(out, 100, \"PNG\")";
_b.WriteToStream((java.io.OutputStream)(_out.getObject()),(int) (100),BA.getEnumFromString(android.graphics.Bitmap.CompressFormat.class,"PNG"));
 //BA.debugLineNum = 47;BA.debugLine="insertQuery(Key, out.ToBytesArray)";
_vvvvvvvvvvvvvv1(_key,(Object)(_out.ToBytesArray()));
 //BA.debugLineNum = 48;BA.debugLine="out.Close";
_out.Close();
 //BA.debugLineNum = 49;BA.debugLine="Return complete(True)";
if (true) return _vvvvvvvvvvvv6(__c.True);
 } 
       catch (Exception e35) {
			ba.setLastException(e35); //BA.debugLineNum = 51;BA.debugLine="Return complete(False)";
if (true) return _vvvvvvvvvvvv6(__c.False);
 };
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return false;
}
public boolean  _vvvvvvvvvvvvvv4(String _key,Object _value,String _password) throws Exception{
 //BA.debugLineNum = 35;BA.debugLine="Public Sub PutEncyptedObject(Key As String, Value";
 //BA.debugLineNum = 36;BA.debugLine="Return putObjectInternal(Key, Value, True, Passwo";
if (true) return _vvvvvvvvvvvvvv7(_key,_value,__c.True,_password);
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return false;
}
public boolean  _vvvvvvvvvvvvvv5(String _key,anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _value) throws Exception{
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
 //BA.debugLineNum = 56;BA.debugLine="Public Sub PutInputStream(Key As String, Value As";
 //BA.debugLineNum = 57;BA.debugLine="Try";
try { //BA.debugLineNum = 58;BA.debugLine="start(Key)";
_vvvvvvvvvvvvvvv2(_key);
 //BA.debugLineNum = 59;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 60;BA.debugLine="out.InitializeToBytesArray(100)";
_out.InitializeToBytesArray((int) (100));
 //BA.debugLineNum = 61;BA.debugLine="File.Copy2(Value, out)";
__c.File.Copy2((java.io.InputStream)(_value.getObject()),(java.io.OutputStream)(_out.getObject()));
 //BA.debugLineNum = 62;BA.debugLine="Value.Close";
_value.Close();
 //BA.debugLineNum = 63;BA.debugLine="insertQuery(Key, out.ToBytesArray)";
_vvvvvvvvvvvvvv1(_key,(Object)(_out.ToBytesArray()));
 //BA.debugLineNum = 64;BA.debugLine="out.Close";
_out.Close();
 //BA.debugLineNum = 65;BA.debugLine="Return complete(True)";
if (true) return _vvvvvvvvvvvv6(__c.True);
 } 
       catch (Exception e49) {
			ba.setLastException(e49); //BA.debugLineNum = 67;BA.debugLine="Return complete(False)";
if (true) return _vvvvvvvvvvvv6(__c.False);
 };
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return false;
}
public boolean  _vvvvvvvvvvvvvv6(String _key,Object _value) throws Exception{
 //BA.debugLineNum = 30;BA.debugLine="Public Sub PutObject(Key As String, Value As Objec";
 //BA.debugLineNum = 31;BA.debugLine="Return putObjectInternal(Key, Value, False, \"\")";
if (true) return _vvvvvvvvvvvvvv7(_key,_value,__c.False,"");
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return false;
}
public boolean  _vvvvvvvvvvvvvv7(String _key,Object _value,boolean _encrypt,String _password) throws Exception{
anywheresoftware.b4a.randomaccessfile.RandomAccessFile _raf = null;
byte[] _buffer = null;
 //BA.debugLineNum = 210;BA.debugLine="Private Sub putObjectInternal(Key As String, Value";
 //BA.debugLineNum = 211;BA.debugLine="Try";
try { //BA.debugLineNum = 212;BA.debugLine="start(Key)";
_vvvvvvvvvvvvvvv2(_key);
 //BA.debugLineNum = 213;BA.debugLine="Dim raf As RandomAccessFile";
_raf = new anywheresoftware.b4a.randomaccessfile.RandomAccessFile();
 //BA.debugLineNum = 214;BA.debugLine="File.Delete(tempFolder, tempFile)";
__c.File.Delete(_vvvvvvvvvvvv4,_vvvvvvvvvvvvvvv4);
 //BA.debugLineNum = 215;BA.debugLine="raf.Initialize(tempFolder, tempFile, False)";
_raf.Initialize(_vvvvvvvvvvvv4,_vvvvvvvvvvvvvvv4,__c.False);
 //BA.debugLineNum = 216;BA.debugLine="If encrypt Then";
if (_encrypt) { 
 //BA.debugLineNum = 217;BA.debugLine="raf.WriteEncryptedObject(Value, password, raf.C";
_raf.WriteEncryptedObject(_value,_password,_raf.CurrentPosition);
 }else {
 //BA.debugLineNum = 219;BA.debugLine="raf.WriteObject(Value, True, raf.CurrentPositio";
_raf.WriteObject(_value,__c.True,_raf.CurrentPosition);
 };
 //BA.debugLineNum = 221;BA.debugLine="raf.Flush";
_raf.Flush();
 //BA.debugLineNum = 222;BA.debugLine="Dim buffer(raf.CurrentPosition) As Byte";
_buffer = new byte[(int) (_raf.CurrentPosition)];
;
 //BA.debugLineNum = 223;BA.debugLine="raf.ReadBytes(buffer, 0, buffer.Length, 0)";
_raf.ReadBytes(_buffer,(int) (0),_buffer.length,(long) (0));
 //BA.debugLineNum = 224;BA.debugLine="raf.Close";
_raf.Close();
 //BA.debugLineNum = 225;BA.debugLine="insertQuery(Key, buffer)";
_vvvvvvvvvvvvvv1(_key,(Object)(_buffer));
 //BA.debugLineNum = 226;BA.debugLine="Return complete(True)";
if (true) return _vvvvvvvvvvvv6(__c.True);
 } 
       catch (Exception e181) {
			ba.setLastException(e181); //BA.debugLineNum = 228;BA.debugLine="Return complete(False)";
if (true) return _vvvvvvvvvvvv6(__c.False);
 };
 //BA.debugLineNum = 230;BA.debugLine="End Sub";
return false;
}
public boolean  _vvvvvvvvvvvvvv0(String _key,Object _value) throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Public Sub PutSimple(Key As String, Value As Objec";
 //BA.debugLineNum = 17;BA.debugLine="Try";
try { //BA.debugLineNum = 18;BA.debugLine="start(Key)";
_vvvvvvvvvvvvvvv2(_key);
 //BA.debugLineNum = 19;BA.debugLine="insertQuery(Key, Value)";
_vvvvvvvvvvvvvv1(_key,_value);
 //BA.debugLineNum = 20;BA.debugLine="Return complete(True)";
if (true) return _vvvvvvvvvvvv6(__c.True);
 } 
       catch (Exception e15) {
			ba.setLastException(e15); //BA.debugLineNum = 22;BA.debugLine="Return complete(False)";
if (true) return _vvvvvvvvvvvv6(__c.False);
 };
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return false;
}
public String  _vvvvvvvvvvvvvvv1(String _key) throws Exception{
 //BA.debugLineNum = 72;BA.debugLine="Public Sub Remove(Key As String)";
 //BA.debugLineNum = 73;BA.debugLine="sql1.ExecNonQuery2(\"DELETE FROM main WHERE key =";
_vvvvvvvvvvvvvvv3.ExecNonQuery2("DELETE FROM main WHERE key = ?",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_key)}));
 //BA.debugLineNum = 74;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvvvvv2(String _key) throws Exception{
 //BA.debugLineNum = 195;BA.debugLine="Private Sub start (Key As String)";
 //BA.debugLineNum = 196;BA.debugLine="sql1.BeginTransaction";
_vvvvvvvvvvvvvvv3.BeginTransaction();
 //BA.debugLineNum = 197;BA.debugLine="sql1.ExecNonQuery2(\"DELETE FROM main WHERE key =";
_vvvvvvvvvvvvvvv3.ExecNonQuery2("DELETE FROM main WHERE key = ?",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_key)}));
 //BA.debugLineNum = 198;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
