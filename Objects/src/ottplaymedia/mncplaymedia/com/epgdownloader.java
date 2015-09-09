package ottplaymedia.mncplaymedia.com;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class epgdownloader extends android.app.Service {
	public static class epgdownloader_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, epgdownloader.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static epgdownloader mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return epgdownloader.class;
	}
	@Override
	public void onCreate() {
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.epgdownloader");
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        processBA.setActivityPaused(false);
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "ottplaymedia.mncplaymedia.com.epgdownloader", processBA, _service);
		}
        BA.LogInfo("** Service (epgdownloader) Create **");
        processBA.raiseEvent(null, "service_create");
        processBA.runHook("oncreate", this, null);
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		handleStart(intent);
    }
    @Override
    public int onStartCommand(android.content.Intent intent, int flags, int startId) {
    	handleStart(intent);
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (epgdownloader) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = new anywheresoftware.b4a.objects.IntentWrapper();
    			if (intent != null) {
    				if (intent.hasExtra("b4a_internal_intent"))
    					iw.setObject((android.content.Intent) intent.getParcelableExtra("b4a_internal_intent"));
    				else
    					iw.setObject(intent);
    			}
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}
	@Override
	public void onDestroy() {
        BA.LogInfo("** Service (epgdownloader) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
        processBA.runHook("ondestroy", this, null);
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvvvvvvvvvvvvvvvvvvvv3 = null;
public static anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvvvvvvvvvvvvvvvvvvvv1 = null;
public static anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvvvvvvvvvvvvvvvvvvvv4 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvvvvvvvvvvvvv5 = null;
public b4a.example.dateutils _vvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.main _vvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.fungsi _vvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.utama _vvvvvvvvvvvvvvvv1 = null;
public ottplaymedia.mncplaymedia.com.epglist _vvvvvvvvvvvvvvvv2 = null;
public ottplaymedia.mncplaymedia.com.register _vvvvvvvvvvvvvvvv3 = null;
public ottplaymedia.mncplaymedia.com.pilihan _vvvvvvvvvvvvvvvv4 = null;
public ottplaymedia.mncplaymedia.com.imagedownloader _vvvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.content _vvvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.webview _vvvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.mncutils2service _vvvvvvvvvvvvvvvvv1 = null;
public static String  _activityispaused() throws Exception{
 //BA.debugLineNum = 73;BA.debugLine="Sub ActivityIsPaused";
 //BA.debugLineNum = 74;BA.debugLine="tasksx.Clear";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv1.Clear();
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvvvvvvvvvvvvvvvvvvvvvv2(anywheresoftware.b4a.objects.LabelWrapper _viewer,String _isilabel) throws Exception{
adr.stringfunctions.stringfunctions _sf = null;
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _mresult = null;
anywheresoftware.b4a.objects.collections.List _listepg = null;
anywheresoftware.b4a.objects.collections.List _listdesc = null;
anywheresoftware.b4a.objects.collections.List _lstprev = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.List _waktu = null;
anywheresoftware.b4a.agraham.richstring.RichStringBuilder.RichString _rsfont = null;
String _teks1 = "";
String _teks2 = "";
String _waktu1 = "";
String _waktu2 = "";
 //BA.debugLineNum = 77;BA.debugLine="Sub CacahEPG(Viewer As Label, IsiLabel As String)";
 //BA.debugLineNum = 78;BA.debugLine="Dim sf				As StringFunctions";
_sf = new adr.stringfunctions.stringfunctions();
 //BA.debugLineNum = 79;BA.debugLine="Dim parser 		As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 80;BA.debugLine="Dim mresult		As Map";
_mresult = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 81;BA.debugLine="Dim ListEPG		As List";
_listepg = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 82;BA.debugLine="Dim ListDesc	As List";
_listdesc = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 83;BA.debugLine="Dim lstPrev		As List";
_lstprev = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 85;BA.debugLine="For i=0 To 1";
{
final int step43 = 1;
final int limit43 = (int) (1);
for (_i = (int) (0); (step43 > 0 && _i <= limit43) || (step43 < 0 && _i >= limit43); _i = ((int)(0 + _i + step43))) {
 //BA.debugLineNum = 86;BA.debugLine="Dim waktu		As List";
_waktu = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 87;BA.debugLine="Dim rsFont	As RichString";
_rsfont = new anywheresoftware.b4a.agraham.richstring.RichStringBuilder.RichString();
 //BA.debugLineNum = 88;BA.debugLine="Dim Teks1		As String";
_teks1 = "";
 //BA.debugLineNum = 89;BA.debugLine="Dim Teks2		As String";
_teks2 = "";
 //BA.debugLineNum = 90;BA.debugLine="Dim Waktu1	As String";
_waktu1 = "";
 //BA.debugLineNum = 91;BA.debugLine="Dim Waktu2	As String";
_waktu2 = "";
 //BA.debugLineNum = 93;BA.debugLine="Try";
try { //BA.debugLineNum = 94;BA.debugLine="parser.Initialize(IsiLabel)";
_parser.Initialize(_isilabel);
 } 
       catch (Exception e53) {
			processBA.setLastException(e53); //BA.debugLineNum = 96;BA.debugLine="Viewer.Text = Fungsi.EPGFail";
_viewer.setText((Object)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv1));
 //BA.debugLineNum = 97;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 100;BA.debugLine="Try";
try { //BA.debugLineNum = 101;BA.debugLine="mresult.Initialize";
_mresult.Initialize();
 //BA.debugLineNum = 102;BA.debugLine="mresult = parser.NextObject";
_mresult = _parser.NextObject();
 } 
       catch (Exception e60) {
			processBA.setLastException(e60); //BA.debugLineNum = 104;BA.debugLine="Viewer.Text = Fungsi.EPGEmpty";
_viewer.setText((Object)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv2));
 //BA.debugLineNum = 105;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 108;BA.debugLine="If mresult.Get(\"returncode\") <> \"0\" Then";
if ((_mresult.Get((Object)("returncode"))).equals((Object)("0")) == false) { 
 //BA.debugLineNum = 109;BA.debugLine="Viewer.Text = Fungsi.EPGError";
_viewer.setText((Object)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv3));
 }else {
 //BA.debugLineNum = 111;BA.debugLine="ListEPG 	= mresult.Get(\"begintime\")";
_listepg.setObject((java.util.List)(_mresult.Get((Object)("begintime"))));
 //BA.debugLineNum = 112;BA.debugLine="ListDesc 	= mresult.Get(\"programdescription\")";
_listdesc.setObject((java.util.List)(_mresult.Get((Object)("programdescription"))));
 //BA.debugLineNum = 113;BA.debugLine="lstPrev 	= mresult.Get(\"prevuecode\")";
_lstprev.setObject((java.util.List)(_mresult.Get((Object)("prevuecode"))));
 //BA.debugLineNum = 115;BA.debugLine="For i=(ListEPG.Size-2) To (ListEPG.Size-1)";
{
final int step69 = 1;
final int limit69 = (int) ((_listepg.getSize()-1));
for (_i = (int) ((_listepg.getSize()-2)); (step69 > 0 && _i <= limit69) || (step69 < 0 && _i >= limit69); _i = ((int)(0 + _i + step69))) {
 //BA.debugLineNum = 116;BA.debugLine="waktu = sf.Split(ListEPG.Get(i), \" \")";
_waktu = _sf._vvvvvv5(BA.ObjectToString(_listepg.Get(_i))," ");
 //BA.debugLineNum = 118;BA.debugLine="If i = ListEPG.Size-2 Then";
if (_i==_listepg.getSize()-2) { 
 //BA.debugLineNum = 119;BA.debugLine="Waktu1 	= waktu.Get(1)";
_waktu1 = BA.ObjectToString(_waktu.Get((int) (1)));
 //BA.debugLineNum = 120;BA.debugLine="Teks1		= ListDesc.Get(i)";
_teks1 = BA.ObjectToString(_listdesc.Get(_i));
 }else {
 //BA.debugLineNum = 122;BA.debugLine="Waktu2 	= waktu.Get(1)";
_waktu2 = BA.ObjectToString(_waktu.Get((int) (1)));
 //BA.debugLineNum = 123;BA.debugLine="Teks2		= ListDesc.Get(i)";
_teks2 = BA.ObjectToString(_listdesc.Get(_i));
 };
 }
};
 //BA.debugLineNum = 127;BA.debugLine="rsFont.Initialize(\"{Putih}{Tebal}\" & Waktu1 & \"";
_rsfont.Initialize((java.lang.CharSequence)("{Putih}{Tebal}"+_waktu1+" | "+_teks1+"{Tebal}{Putih}"+anywheresoftware.b4a.keywords.Common.CRLF+_waktu2+" | "+_teks2));
 //BA.debugLineNum = 128;BA.debugLine="rsFont.Color2(Colors.White, \"{Putih}\")";
_rsfont.Color2(anywheresoftware.b4a.keywords.Common.Colors.White,"{Putih}");
 //BA.debugLineNum = 129;BA.debugLine="rsFont.Style2(rsFont.STYLE_BOLD, \"{Tebal}\")";
_rsfont.Style2(_rsfont.STYLE_BOLD,"{Tebal}");
 //BA.debugLineNum = 130;BA.debugLine="Viewer.Text = rsFont";
_viewer.setText((Object)(_rsfont.getObject()));
 };
 }
};
 //BA.debugLineNum = 133;BA.debugLine="End Sub";
return "";
}
public static String  _download(anywheresoftware.b4a.objects.collections.Map _imageviewsmap) throws Exception{
int _i = 0;
String _link = "";
anywheresoftware.b4a.samples.httputils2.httpjob _j = null;
 //BA.debugLineNum = 25;BA.debugLine="Sub Download (ImageViewsMap As Map)";
 //BA.debugLineNum = 26;BA.debugLine="For i = 0 To ImageViewsMap.Size - 1";
{
final int step15 = 1;
final int limit15 = (int) (_imageviewsmap.getSize()-1);
for (_i = (int) (0); (step15 > 0 && _i <= limit15) || (step15 < 0 && _i >= limit15); _i = ((int)(0 + _i + step15))) {
 //BA.debugLineNum = 28;BA.debugLine="Dim link As String = ImageViewsMap.GetValueAt(i)";
_link = BA.ObjectToString(_imageviewsmap.GetValueAt(_i));
 //BA.debugLineNum = 30;BA.debugLine="Dim j As HttpJob";
_j = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 31;BA.debugLine="j.Initialize(\"AmbilEPG\", Me)";
_j._initialize(processBA,"AmbilEPG",epgdownloader.getObject());
 //BA.debugLineNum = 32;BA.debugLine="j.Download(link)";
_j._download(_link);
 //BA.debugLineNum = 33;BA.debugLine="j.Tag = ImageViewsMap.GetKeyAt(i)";
_j._tag = _imageviewsmap.GetKeyAt(_i);
 //BA.debugLineNum = 34;BA.debugLine="j.GetRequest.SetHeader(\"Cache-Control\", \"no-cach";
_j._getrequest().SetHeader("Cache-Control","no-cache, must-revalidate");
 //BA.debugLineNum = 35;BA.debugLine="j.GetRequest.Timeout = DateTime.TicksPerSecond *";
_j._getrequest().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*120));
 }
};
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _iv = null;
 //BA.debugLineNum = 49;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 51;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 52;BA.debugLine="Dim iv As Label = Job.Tag";
_iv = new anywheresoftware.b4a.objects.LabelWrapper();
_iv.setObject((android.widget.TextView)(_job._tag));
 //BA.debugLineNum = 54;BA.debugLine="CacahEPG(iv, Job.GetString)";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv2(_iv,_job._getstring());
 }else {
 };
 //BA.debugLineNum = 70;BA.debugLine="Job.Release";
_job._release();
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private cachex As Map";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv3 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 7;BA.debugLine="Private tasksx As Map";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 8;BA.debugLine="Private ongoingTasksx As Map";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv4 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 12;BA.debugLine="tasksx.Initialize";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv1.Initialize();
 //BA.debugLineNum = 13;BA.debugLine="cachex.Initialize";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv3.Initialize();
 //BA.debugLineNum = 14;BA.debugLine="ongoingTasksx.Initialize";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv4.Initialize();
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 17;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
}
